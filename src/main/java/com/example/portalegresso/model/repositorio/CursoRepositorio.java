package com.example.portalegresso.model.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.portalegresso.model.entidades.Curso;

@Repository
public interface CursoRepositorio extends JpaRepository<Curso,Integer>{
    // Buscar cursos pelo nome ou nivel (parcialmente ou completamente)
    @Query("SELECT c FROM Curso c WHERE " +
            "(:nome IS NULL OR LOWER(c.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
            "(:nivel IS NULL OR LOWER(c.nivel) = LOWER(:nivel))")
    List<Curso> filtrarCursos(@Param("nome") String nome, @Param("nivel") String nivel );
}
