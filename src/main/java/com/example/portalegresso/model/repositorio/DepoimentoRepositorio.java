package com.example.portalegresso.model.repositorio;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.portalegresso.model.entidades.Depoimento;
import com.example.portalegresso.model.entidades.Egresso;

@Repository
public interface DepoimentoRepositorio extends JpaRepository<Depoimento,Integer>{
    // Para resultados limitados usando paginação
    @Query("SELECT d FROM Depoimento d ORDER BY d.date DESC")
    List<Depoimento> findRecentes(Pageable pageable);

    // Usando JPQL para filtrar pelo ano
    @Query("SELECT d FROM Depoimento d WHERE FUNCTION('YEAR', d.date) = :ano")
    List<Depoimento> findByAno(@Param("ano") int ano);
    
    // Para retornar todos os depoimentos ordenados por data
    List<Depoimento> findAllByOrderByDateDesc();

    List<Depoimento> findByEgresso(Egresso egresso);

    
}
