package com.example.portalegresso.model.repositorio;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.portalegresso.model.entidades.Cargo;
import com.example.portalegresso.model.entidades.Depoimento;
import com.example.portalegresso.model.entidades.Egresso;
import com.example.portalegresso.service.EgressoService;
import com.example.portalegresso.service.RegraNegocioRunTime;

public class EgressoServiceTest {

    @Autowired
    EgressoService egressoService;

    @Autowired
    EgressoRepositorio egressoRepositorio;

    @Autowired
    CargoRepositorio cargoRepositorio;

    @Autowired
    DepoimentoRepositorio depoimentoRepositorio;



      /* - EGRESSO
     *      -> deveGerarErroAoTentarSalvarEgressoNulo
     *      -> deveGerarErroAoTentarSalvarEgressoSemNome
     *      -> deveGerarErroAoTentarSalvarEgressoSemEmail
     *      -> deveGerarErroAoTentarSalvarFormatoEmailIncorreto
     *      -> deveGerarErroAoTentartSalvarComMesmoEmail
     *      -> deveGerarErroAoTentarSalvarFormatoLinkedinIncorreto
     *      ->
     *      -> deveGerarErroAoTentarSalvarFormatoInstagramIncorreto
     *      ->
     */

    @Test
    public void deveGerarErroAoTentarSalvarEgressoNulo(){
        Egresso egresso = Egresso.builder().build();
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> egressoService.salvar(egresso), "Deve informar um egresso válido.");
    }

    @Test
    public void deveGerarErroAoTentarSalvarEgressoSemNome(){
        Egresso egresso = Egresso.builder()
                .email("meuemail.com")
                .build();
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> egressoService.salvar(egresso), "Deve inserir um nome válido.");
    }

    @Test
    public void deveGerarErroAoTentarSalvarEgressoSemEmail(){
        Egresso egresso = Egresso.builder()
                .nome("Lucas")
                .build();
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> egressoService.salvar(egresso), "Deve inserir um email válido.");
    }

    @Test
    public void deveGerarErroAoTentarSalvarFormatoEmailIncorreto(){
        Egresso egresso = Egresso.builder()
                .nome("Lucas")
                .email("email_incorreto")
                .build();
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> egressoService.salvar(egresso), "Deve inserir um email no formato correto.");
    }

    @Test
    public void deveGerarErroAoTentartSalvarComMesmoEmail(){
        Egresso egresso = Egresso.builder()
                .nome("Lucas")
                .email("meuemail.com")
                .build();
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> egressoService.salvar(egresso), "Esse email já está em uso.");
        
        egressoRepositorio.delete(egresso);
    }

    @Test
    public void deveGerarErroAoTentarSalvarFormatoLinkedinIncorreto(){
        Egresso egresso = Egresso.builder()
                .nome("Lucas")
                .email("meuemail.com")
                .linkedin("linkedin_incorreto")
                .build();
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> egressoService.salvar(egresso), "Deve inserir um link do LinkedIn no formato correto.");
    }

    @Test
    public void deveGerarErroAoTentarSalvarFormatoInstagramIncorreto(){
        Egresso egresso = Egresso.builder()
                .nome("Lucas")
                .email("meuemail.com")
                .instagram("instagram_incorreto")
                .build();
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> egressoService.salvar(egresso), "Deve inserir um link do Instagram no formato correto.");
    }

    /* - DEPOIMENTO
     *      -> deveGerarErroAoTentarSalvarDepoimentoNulo
     *      -> deveGerarErroAoTentarSalvarDepoimentoSemTexto
     *      -> deveGerarErroAoTentarSalvarDepoimentoSemData
     *      -> deveGerarErroAoTentarSalvarDepoimentoIncorreto
     *      -> deveGerarErroAoTentarSalvarDepoimentoSemEgresso
     */

    @Test
    public void deveGerarErroAoTentarSalvarDepoimentoNulo(){
        Depoimento depoimento = Depoimento.builder().build();
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> egressoService.salvar(depoimento), "Deve informar um depoimento válido.");
    }

    @Test
    public void deveGerarErroAoTentarSalvarDepoimentoSemTexto(){
        Depoimento depoimento = Depoimento.builder()
                .date(LocalDate.now())
                .egresso(new Egresso(1, "lucas", "meuemail.com", "novadescricao", "novafoto", "linkparalinkedin", "linkparainstagram", "meucurriculo"))
                .build();
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> egressoService.salvar(depoimento), "Deve inserir um texto válido.");
    }

    @Test
    public void deveGerarErroAoTentarSalvarDepoimentoSemData(){
        Depoimento depoimento = Depoimento.builder()
                .texto("Texto do depoimento")
                .egresso(new Egresso(1, "lucas", "meuemail.com", "novadescricao", "novafoto", "linkparalinkedin", "linkparainstagram", "meucurriculo"))
                .build();
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> egressoService.salvar(depoimento), "Deve inserir uma data válida.");
    }

    @Test
    public void deveGerarErroAoTentarSalvarDepoimentoSemEgresso(){
        Depoimento depoimento = Depoimento.builder()
                .texto("Texto do depoimento")
                .date(LocalDate.now())
                .build();
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> egressoService.salvar(depoimento), "Deve associar um egresso válido.");
    }

     /* 
     * - CARGO
     *      -> deveGerarErroAoTentarSalvarCargoSemNome
     *      -> deveGerarErroAoTentarSalvarCargoSemDataInicio
     *      -> deveGerarErroAoTentarSalvarCargoSemLocal
     *      -> deveGerarErroAoTentarSalvarDataInicioIncorreto
     *      -> deveGerarErroAoTentarSalvarDataFimIncorreto
     */
    
    @Test
    public void deveGerarErroAoTentarSalvarCargoSemDescricao(){
       Cargo cargo = Cargo.builder()
                .egresso(new Egresso(1, "lucas", "meuemail.com", "novadescricao", "novafoto", "linkparalinkedin", "linkparainstagram", "meucurriculo"))
                .local("esseeolocal")
                .ano_inicio(2006)
                .ano_fim(2024)
                .build();
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> egressoService.salvar(cargo), "Deve inserir uma descrição valida");
    }

    @Test
    public void deveGerarErroAoTentarSalvarCargoSemDataInicio(){
       Cargo cargo = Cargo.builder()
                .descricao("testeDescrição")
                .egresso(new Egresso(1, "lucas", "meuemail.com", "novadescricao", "novafoto", "linkparalinkedin", "linkparainstagram", "meucurriculo"))
                .local("esseeolocal")
                .ano_fim(2024)
                .build();
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> egressoService.salvar(cargo), "Deve inserir uma data para o inicio do cargo");
    }

    @Test
    public void deveGerarErroAoTentarSalvarCargoSemLocal(){
       Cargo cargo = Cargo.builder()
                .descricao("testeDescrição")
                .egresso(new Egresso(1, "lucas", "meuemail.com", "novadescricao", "novafoto", "linkparalinkedin", "linkparainstagram", "meucurriculo"))
                .ano_inicio(2006)
                .ano_fim(2024)
                .build();
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> egressoService.salvar(cargo), "Deve inserir qual é o local do cargo.");
    }

    @Test
    public void deveGerarErroAoTentarSalvarDataInicioIncorreto(){
       Cargo cargo = Cargo.builder()
                .descricao("descricaoTeste")
                .egresso(new Egresso(1, "lucas", "meuemail.com", "novadescricao", "novafoto", "linkparalinkedin", "linkparainstagram", "meucurriculo"))
                .local("esseeolocal")
                .ano_inicio(19)
                .ano_fim(2024)
                .build();
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> egressoService.salvar(cargo), "Deve inserir um valor inicial válido.");
    }

    @Test
    public void deveGerarErroAoTentarSalvarDataFimIncorreto(){
       Cargo cargo = Cargo.builder()
                .descricao("descricaoTeste")
                .egresso(new Egresso(1, "lucas", "meuemail.com", "novadescricao", "novafoto", "linkparalinkedin", "linkparainstagram", "meucurriculo"))
                .local("esseeolocal")
                .ano_inicio(2020)
                .ano_fim(2019)
                .build();
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> egressoService.salvar(cargo), "Deve inserir um valor final válido.");
    }


     /* 
     * Teste de Funcionalidades
     *      
     *      -> deveSalvarCargo
     *      -> deveSalvarDepoimento
     *      -> deveSalvarEgresso
     *    
     * 
     */

}
