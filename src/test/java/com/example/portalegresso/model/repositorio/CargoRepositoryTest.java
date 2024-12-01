package com.example.portalegresso.model.repositorio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.portalegresso.model.entidades.Cargo;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CargoRepositoryTest {
    @Autowired
    CargoRepositorio repositorio;

    @Test
    public void deveVerificarSalvarCurso(){
        //cenario
        Cargo cargo = Cargo.builder().id_cargo(8888).build();

        //acao

        //verificação
    }
}
