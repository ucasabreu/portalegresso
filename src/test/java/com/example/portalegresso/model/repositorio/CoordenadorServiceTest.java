package com.example.portalegresso.model.repositorio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.portalegresso.model.entidades.Cargo;
import com.example.portalegresso.model.entidades.Coordenador;
import com.example.portalegresso.model.entidades.Egresso;
import com.example.portalegresso.service.ConsultasService;
import com.example.portalegresso.service.CoordenadorService;
import com.example.portalegresso.service.RegraNegocioRunTime;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class CoordenadorServiceTest {
    
    @Autowired
    CoordenadorService coordenadorService;

    ConsultasService consultasService;

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
     *      -> deveGerarErroAoTentarSalvarCoordNulo
     *      -> deveGerarErroAoTentarSalvarCoordSemLogin
     *      -> deveGerarErroAoTentarSalvarCoordSemSenha
     *      -> degeGerarErroAoTentarSalvarCoordComMesmoLogin
     *      ->
     */

    @Test
    public void deveGerarErroAoTentarSalvarCoordNulo(){
         Coordenador coord = Coordenador.builder()
                             .build();
         Assertions.assertThrows(RegraNegocioRunTime.class, () -> coordenadorService.salvar(coord),"Deve informar coordenador valido");
    }
 
    @Test
    public void deveGerarErroAoTentarSalvarCoordSemLogin(){
        Coordenador coord = Coordenador.builder()
                            .senha("null")
                            .tipo("esseeotipo")
                            .build();
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> coordenadorService.salvar(coord),"Deve informar um login valido");
    }

    @Test
    public void deveGerarErroAoTentarSalvarCoordSemSenha(){
        Coordenador coord = Coordenador.builder()
                            .login("loginCoordenador")
                            .tipo("esseeotipo")
                            .build();
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> coordenadorService.salvar(coord),"Deve informar uma senha");
    }

    @Test
    public void deveGerarErroAoTentarSalvarCoordComMesmoLogin(){
        Coordenador coord = Coordenador.builder()
                            .login("coordenadorlogin")
                            .senha("null")
                            .tipo("esseeotipo")
                            .build();
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> coordenadorService.salvar(coord),"Esse login ja existe");
        
        repositorio.delete(coord);
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
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> coordenadorService.salvar(cargo), "Deve inserir uma descrição valida");
    }

    @Test
    public void deveGerarErroAoTentarSalvarCargoSemDataInicio(){
       Cargo cargo = Cargo.builder()
                .descricao("testeDescrição")
                .egresso(new Egresso(1, "lucas", "meuemail.com", "novadescricao", "novafoto", "linkparalinkedin", "linkparainstagram", "meucurriculo"))
                .local("esseeolocal")
                .ano_fim(2024)
                .build();
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> coordenadorService.salvar(cargo), "Deve inserir uma data para o inicio do cargo");
    }

    @Test
    public void deveGerarErroAoTentarSalvarCargoSemLocal(){
       Cargo cargo = Cargo.builder()
                .descricao("testeDescrição")
                .egresso(new Egresso(1, "lucas", "meuemail.com", "novadescricao", "novafoto", "linkparalinkedin", "linkparainstagram", "meucurriculo"))
                .ano_inicio(2006)
                .ano_fim(2024)
                .build();
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> coordenadorService.salvar(cargo), "Deve inserir qual é o local do cargo.");
    }

    @Test
    public void deveGerarErroAoTentarSalvarDataInicioIncorreto(){
       Cargo cargo = Cargo.builder()
                .descricao("descricaoTeste")
                .egresso(new Egresso(1, "lucas", "meuemail.com", "novadescricao", "novafoto", "linkparalinkedin", "linkparainstagram", "meucurriculo"))
                .local("esseeolocal")
                .ano_inicio(-1)
                .ano_fim(2024)
                .build();
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> coordenadorService.salvar(cargo), "Deve inserir um valor inicial válido.");
    }

    
     /* - DEPOIMENTO
     *      -> deveGerarErroAoTentarSalvarDepoimentoNulo
     *      -> deveGerarErroAoTentarSalvarDepoimentoSemTexto
     *      -> deveGerarErroAoTentarSalvarDepoimentoSemData
     *      -> deveGerarErroAoTentarSalvarDepoimentoIncorreto
     *      -> deveGerarErroAoTentarSalvarDepoimentoSemEgresso
     * - CURSO
     *      -> deveGerarErroAoTentarSalvarCursoNulo
     *      -> deveGerarErroAoTentarSalvarCursoSemNome
     *      -> deveGerarErroAoTentarSalvarCursoSemNivel
     *      -> deveGerarErroAoTentarSalvarCursoSemCoordenador
     * - EGRESSO
     *      -> deveGerarErroAoTentarSalvarEgressoNulo
     *      -> deveGerarErroAoTentarSalvarEgressoSemNome
     *      -> deveGerarErroAoTentarSalvarEgressoSemEmail
     *      -> deveGerarErroAoTentarSalvarFormatoEmailIncorreto
     *      -> deveGerarErroAoTentartSalvarComMesmoEmail
     *      -> deveGerarErroAoTentarSalvarFormatoLinkedinIncorreto
     *      ->
     *      -> deveGerarErroAoTentarSalvarFormatoInstagramIncorreto
     *      ->
     * 
     * Teste de Funcionalidades
     *      -> deveEfetuarLoginCoordenador
     *      -> deveSalvarCoordenador
     *      -> deveSalvarCargo
     *      -> deveSalvarDepoimento
     *      -> deveSalvarCurso
     *      -> deveSalvarEgresso
     *      -> deveListarCursos
     *      -> deveListarCursosPorFiltros
     *      -> deveConsultarDepoimentosRecentes
     *      -> deveConsultarDepoimentosRecentesPorUmLimite
     *      -> deveConsultarDepoimentosRecentesPorAno
     *      -> deveConsultarEgressosPorNome
     *      -> deveConsultarEgressosPorCargo
     *      -> deveConsultarEgressosPorAno
     * 
     */
    



    
}
