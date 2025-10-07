package com.leonardo.reservations.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reserva {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long livroId;
    private String aluno; // simplificado
    private String status; // PENDENTE, DISPONIVEL_RETIRADA, EXPIRADA, CONCLUIDA
    private LocalDateTime criadaEm;
    private LocalDateTime expiraEm;
    // getters/setters
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public Long getLivroId(){return livroId;} public void setLivroId(Long v){this.livroId=v;}
    public String getAluno(){return aluno;} public void setAluno(String a){this.aluno=a;}
    public String getStatus(){return status;} public void setStatus(String s){this.status=s;}
    public LocalDateTime getCriadaEm(){return criadaEm;} public void setCriadaEm(LocalDateTime t){this.criadaEm=t;}
    public LocalDateTime getExpiraEm(){return expiraEm;} public void setExpiraEm(LocalDateTime t){this.expiraEm=t;}
}
