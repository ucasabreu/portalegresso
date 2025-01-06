package com.example.portalegresso.model.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.portalegresso.model.entidades.Egresso;

@Repository
public interface EgressoRepositorio extends JpaRepository<Egresso,Integer>{
    boolean existsByEmail(String email);
    
    List<Egresso> findByNomeContainingIgnoreCase(String nome);
    
   


}
