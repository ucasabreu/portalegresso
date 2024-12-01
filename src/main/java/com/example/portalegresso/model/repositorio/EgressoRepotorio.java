package com.example.portalegresso.model.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.portalegresso.model.entidades.Egresso;

public interface EgressoRepotorio extends JpaRepository<Egresso,Integer>{
    
}
