package com.example.portalegresso.model.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.portalegresso.model.entidades.CursoEgresso;
import com.example.portalegresso.model.entidades.Egresso;

@Repository
public interface CursoEgressoRepositorio extends JpaRepository<CursoEgresso,Integer> {
    
    @Query("SELECT ce.egresso FROM CursoEgresso ce WHERE ce.curso.id_curso = :id_curso")
    List<Egresso> findEgressoByCursoId(@Param("id_curso") Integer id_curso);

    @Query("SELECT ce.egresso FROM CursoEgresso ce WHERE LOWER(ce.curso.nome) LIKE LOWER(CONCAT('%', :curso, '%'))")
    List<Egresso> findEgressosByCursoNome(@Param("curso") String curso);

    @Query("SELECT ce.egresso FROM CursoEgresso ce WHERE ce.ano_inicio = :ano")
    List<Egresso> findEgressosByAnoInicio(@Param("ano") int ano);

    @Query("SELECT ce.egresso FROM CursoEgresso ce WHERE ce.ano_fim = :ano")
    List<Egresso> findEgressosByAnoFim(@Param("ano") int ano);

    
}

