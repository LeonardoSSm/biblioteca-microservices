package com.leonardo.reservations.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "catalog-service")
public interface CatalogClient {
    @GetMapping("/api/livros/{id}")
    LivroDTO getLivro(@PathVariable Long id);

    record LivroDTO(Long id, String titulo, String autor, Integer ano, boolean disponivel) {}
}
