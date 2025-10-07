package com.leonardo.catalog.repo;
import com.leonardo.catalog.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
public interface LivroRepository extends JpaRepository<Livro, Long> {}
