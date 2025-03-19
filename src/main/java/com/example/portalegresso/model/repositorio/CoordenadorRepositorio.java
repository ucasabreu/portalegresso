package com.example.portalegresso.model.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.portalegresso.model.entidades.Coordenador;

@Repository
public interface CoordenadorRepositorio extends JpaRepository<Coordenador, Integer> {
    Optional<Coordenador> findByLogin(String login); // deve ser testado
    boolean existsByLogin(String login);
    Optional<Coordenador> findBySenha(String senha);
    @Query("SELECT c FROM Coordenador c WHERE c.login = :login AND c.senha = :senha")
    Optional<Coordenador> findByLoginAndSenha(@Param("login") String login, @Param("senha") String senha);
}
