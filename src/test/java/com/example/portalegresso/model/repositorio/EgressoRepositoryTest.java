package com.example.portalegresso.model.repositorio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.portalegresso.model.entidades.Egresso;
import com.example.portalegresso.model.repositorio.EgressoRepotorio;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EgressoRepositoryTest {
    @Autowired
    EgressoRepotorio repositorio;

    @Test
    public void verificarSalvarEgresso(){
        //cenario
        Egresso egresso = Egresso.builder()
                            .id_egresso(000).nome("lucas")
                            .email("teste@gmail.com").descricao("descricao")
                            .foto("fototeste").linkedin("meulink")
                            .instagram("meuinstagram").curriculo("meudocumentocurriculo").build();

        //ação
        Egresso salvo = repositorio.save(egresso); // verificando se os dados instacioados em "egresso" são salvos

        //verificação
        Assertions.assertNotNull(salvo);
        Assertions.assertEquals(egresso.getId_egresso(), salvo.getId_egresso());
        Assertions.assertEquals(egresso.getNome(), salvo.getNome());
        Assertions.assertEquals(egresso.getEmail(),salvo.getEmail());
        Assertions.assertEquals(egresso.getDescricao(),salvo.getDescricao());
        Assertions.assertEquals(egresso.getFoto(), salvo.getFoto());
        Assertions.assertEquals(egresso.getLinkedin(), salvo.getLinkedin());
        Assertions.assertEquals(egresso.getInstagram(), salvo.getInstagram());
        Assertions.assertEquals(egresso.getCurriculo(), salvo.getCurriculo());
    }

}
