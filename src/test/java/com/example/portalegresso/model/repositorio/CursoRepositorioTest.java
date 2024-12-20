package com.example.portalegresso.model.repositorio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.portalegresso.model.entidades.Coordenador;
import com.example.portalegresso.model.entidades.Curso;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CursoRepositorioTest {
    @Autowired
    CursoRepositorio repositorio;


    @Test
    public void testVerificarSalvarCurso(){
       
        Curso curso = Curso.builder()
                      .nome("ciencia da computacao")
                      .coordenador(new Coordenador(1, "coordenadorlogin", null, "essetipo"))
                      .nivel("TRES")
                      .build();

        Curso salvo = repositorio.save(curso);


        Assertions.assertNotNull(salvo);
        Assertions.assertEquals(curso.getNome(),salvo.getNome());
        Assertions.assertEquals(curso.getNivel(), salvo.getNivel());
        Assertions.assertNotNull(salvo.getCoordenador());


    }
}
