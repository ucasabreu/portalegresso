package com.example.portalegresso.model.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.portalegresso.model.entidades.Coordenador;
import com.example.portalegresso.model.entidades.Curso;

@Repository
public interface CursoRepositorio extends JpaRepository<Curso,Integer>{
    // Buscar cursos pelo nivel 
    @Query("SELECT c FROM Curso c WHERE LOWER(c.nivel) = LOWER(:nivel)")
    List<Curso> filtrarCursosPorNivel(@Param("nivel") String nivel);

    List<Curso> findByCoordenador(Coordenador coordenador);
}
