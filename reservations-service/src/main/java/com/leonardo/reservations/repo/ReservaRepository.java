package com.leonardo.reservations.repo;
import com.leonardo.reservations.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByLivroIdAndStatusOrderByCriadaEmAsc(Long livroId, String status);
    List<Reserva> findByStatusAndExpiraEmBefore(String status, LocalDateTime before);
}
