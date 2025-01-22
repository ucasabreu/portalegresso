package com.example.portalegresso.model.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.portalegresso.model.entidades.Coordenador;
import java.util.List;




@Repository
public interface CoordenadorRepositorio extends JpaRepository<Coordenador,Integer>{
    Optional<Coordenador> findByLogin(String login); // deve ser testado
    boolean existsByLogin(String login);
    
}
