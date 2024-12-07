package com.example.portalegresso.model.repositorio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.portalegresso.model.entidades.Cargo;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CargoRepositorioTest {
    @Autowired
    CargoRepositorio repositorio;

    @Test
    public void testVerificarSalvarCargo(){
         // Cenário
        Cargo cargo = Cargo.builder()
                .descricao("testedescricao")
                .local("esseeolocal")
                .ano_inicio(2006)
                .ano_fim(2024)
                .build();

        // Ação
        Cargo salvo = repositorio.save(cargo);

        // Verificação
        Assertions.assertNotNull(salvo);
        Assertions.assertEquals(cargo.getAno_inicio(), salvo.getAno_inicio()); 
        Assertions.assertEquals(cargo.getAno_fim(), salvo.getAno_fim());
        Assertions.assertEquals(cargo.getDescricao(), salvo.getDescricao());
        Assertions.assertEquals(cargo.getLocal(), salvo.getLocal());
       
    }
}
