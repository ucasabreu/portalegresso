package com.example.portalegresso.model.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.portalegresso.model.entidades.Cargo;

public interface CargoRepositorio extends JpaRepository<Cargo,Integer> {

}
