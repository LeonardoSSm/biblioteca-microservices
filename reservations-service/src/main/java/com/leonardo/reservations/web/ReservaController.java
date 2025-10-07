package com.leonardo.reservations.web;
import com.leonardo.reservations.model.Reserva;
import com.leonardo.reservations.service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {
    private final ReservaService service;
    public ReservaController(ReservaService s){this.service=s;}

    @PostMapping("/{livroId}")
    @Operation(summary="Cria reserva (fila ou janela de retirada)")
    public Reserva reservar(@PathVariable Long livroId, @RequestParam String aluno){
        return service.reservar(livroId, aluno);
    }

    @PostMapping("/livros/{livroId}/processar-devolucao")
    @Operation(summary="Processa devolução: promove primeiro da fila e publica evento")
    public void processarDevolucao(@PathVariable Long livroId){
        service.processarDevolucao(livroId);
    }
}
