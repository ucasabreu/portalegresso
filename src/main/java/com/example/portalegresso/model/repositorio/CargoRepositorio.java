package com.example.portalegresso.model.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.portalegresso.model.entidades.Cargo;

@Repository
public interface CargoRepositorio extends JpaRepository<Cargo,Integer> {
}
