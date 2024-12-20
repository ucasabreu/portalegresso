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
    
    @Query("SELECT ce.egresso FROM CursoEgresso ce WHERE ce.curso.id_curso = :idCurso")
    List<Egresso> findEgressoByCursoId(@Param("idCurso") Integer idCurso);
}
