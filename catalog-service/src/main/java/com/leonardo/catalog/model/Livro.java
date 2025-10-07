package com.leonardo.catalog.model;
import jakarta.persistence.*;
@Entity
public class Livro {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;
    private Integer ano;
    private boolean disponivel = true;
    // getters/setters
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getTitulo(){return titulo;} public void setTitulo(String t){this.titulo=t;}
    public String getAutor(){return autor;} public void setAutor(String a){this.autor=a;}
    public Integer getAno(){return ano;} public void setAno(Integer a){this.ano=a;}
    public boolean isDisponivel(){return disponivel;} public void setDisponivel(boolean d){this.disponivel=d;}
}
