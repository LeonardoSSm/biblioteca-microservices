package com.leonardo.catalog.web;
import com.leonardo.catalog.model.Livro;
import com.leonardo.catalog.repo.LivroRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivroController {
    private final LivroRepository repo;
    public LivroController(LivroRepository repo){this.repo=repo;}

    @GetMapping
    @Operation(summary="Lista livros")
    public List<Livro> list(){ return repo.findAll(); }

    @PostMapping
    @Operation(summary="Cria livro")
    public Livro create(@RequestBody Livro l){ return repo.save(l); }

    @GetMapping("/{id}")
    @Operation(summary="Busca por id")
    public Livro get(@PathVariable Long id){ return repo.findById(id).orElseThrow(); }

    @PutMapping("/{id}")
    @Operation(summary="Atualiza livro")
    public Livro put(@PathVariable Long id, @RequestBody Livro up){
        Livro l = repo.findById(id).orElseThrow();
        l.setTitulo(up.getTitulo()); l.setAutor(up.getAutor()); l.setAno(up.getAno()); l.setDisponivel(up.isDisponivel());
        return repo.save(l);
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Remove livro")
    public void del(@PathVariable Long id){ repo.deleteById(id); }
}
