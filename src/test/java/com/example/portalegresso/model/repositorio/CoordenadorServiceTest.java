package com.example.portalegresso.model.repositorio;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.portalegresso.service.CoordenadorService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class CoordenadorServiceTest {
    
    @Autowired
    CoordenadorService service;

    @Autowired
    CoordenadorRepositorio repositorio;

    @Autowired
    CargoRepositorio cargoRepositorio;

    @Autowired
    DepoimentoRepositorio depoimentoRepositorio;

    @Autowired
    CursoRepositorio cursoRepositorio;
    
    @Autowired
    EgressoRepositorio egressoRepositorio;

    /*
     * Teste de restrições
     * - COORDENADOR
     *      ->
     * - CARGO
     *      -> deveGerarErroAoTentarSalvarCargoSemNome
     *      -> deveGerarErroAoTentarSalvarCargoSemDataInicio
     *      -> deveGerarErroAoTentarSalvarCargoSemLocal
     */
    

    // Teste de funcionalidades
}
