package com.example.portalegresso.model.repositorio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.portalegresso.model.entidades.Depoimento;
import com.example.portalegresso.model.entidades.Egresso;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DepoimentoRepositorioTest {
    @Autowired
    DepoimentoRepositorio repositorio;

    @Test
    public void testVerificarSalvarDepoimento(){
        Depoimento depoimento = Depoimento.builder()
                            .texto("algumtexto")
                            .date(null)
                            .egresso(new Egresso(1, "lucas", "meuemail.com", null, null, null, null, null))
                            .build();

        Depoimento salvo = repositorio.save(depoimento);

        Assertions.assertNotNull(salvo);
        Assertions.assertEquals(depoimento.getTexto(),salvo.getTexto());
        Assertions.assertEquals(depoimento.getDate(), salvo.getDate());
    }
}
