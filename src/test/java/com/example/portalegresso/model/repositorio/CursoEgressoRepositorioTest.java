package com.example.portalegresso.model.repositorio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.portalegresso.model.entidades.CursoEgresso;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CursoEgressoRepositorioTest {
    @Autowired
    CursoEgressoRepositorio repositorio;

    @Test
    public void testVerificarSalvarCursoEgresso(){
        CursoEgresso cursoEgresso = CursoEgresso.builder()
                                .ano_inicio(2004)
                                .ano_fim(2024)
                                
                                .build();
        
        CursoEgresso salvo = repositorio.save(cursoEgresso);

        Assertions.assertNotNull(salvo);
        Assertions.assertEquals(cursoEgresso.getAno_inicio(), salvo.getAno_inicio());
        Assertions.assertEquals(cursoEgresso.getAno_fim(), salvo.getAno_fim());
       
        

    }
}
