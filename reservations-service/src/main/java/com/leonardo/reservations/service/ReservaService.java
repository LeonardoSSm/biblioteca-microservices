package com.leonardo.reservations.service;
import com.leonardo.reservations.client.CatalogClient;
import com.leonardo.reservations.model.Reserva;
import com.leonardo.reservations.repo.ReservaRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.List;

@Service
public class ReservaService {
    private final ReservaRepository repo;
    private final CatalogClient catalog;
    private final AmqpTemplate amqp;
    private final String exchange;
    private final String routingKey;
    private static final Duration JANELA = Duration.ofHours(48);

    public ReservaService(ReservaRepository repo, CatalogClient catalog, AmqpTemplate amqp,
                          @Value("${app.amqp.exchange}") String exchange,
                          @Value("${app.amqp.routingKey}") String routingKey) {
        this.repo = repo; this.catalog = catalog; this.amqp = amqp;
        this.exchange = exchange; this.routingKey = routingKey;
    }

    @Transactional
    public Reserva reservar(Long livroId, String aluno) {
        var livro = catalog.getLivro(livroId); // via Eureka/Feign
        var now = LocalDateTime.now();
        var r = new Reserva();
        r.setLivroId(livroId);
        r.setAluno(aluno);
        r.setCriadaEm(now);

        if (livro.disponivel()) {
            r.setStatus("DISPONIVEL_RETIRADA");
            r.setExpiraEm(now.plus(JANELA));
        } else {
            r.setStatus("PENDENTE");
        }
        return repo.save(r);
    }

    @Transactional
    public Reserva processarDevolucao(Long livroId) {
        List<Reserva> fila = repo.findByLivroIdAndStatusOrderByCriadaEmAsc(livroId, "PENDENTE");
        if (fila.isEmpty()) return null;
        var primeiro = fila.get(0);
        primeiro.setStatus("DISPONIVEL_RETIRADA");
        primeiro.setExpiraEm(LocalDateTime.now().plus(JANELA));
        var saved = repo.save(primeiro);
        amqp.convertAndSend(exchange, routingKey, saved.getId()); // evento
        return saved;
    }
}
