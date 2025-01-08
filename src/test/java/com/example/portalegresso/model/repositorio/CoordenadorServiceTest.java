package com.example.portalegresso.model.repositorio;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.portalegresso.model.entidades.Cargo;
import com.example.portalegresso.model.entidades.Coordenador;
import com.example.portalegresso.model.entidades.Curso;
import com.example.portalegresso.model.entidades.Depoimento;
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
                .ano_inicio(19)
                .ano_fim(2024)
                .build();
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> coordenadorService.salvar(cargo), "Deve inserir um valor inicial válido.");
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
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> coordenadorService.salvar(cargo), "Deve inserir um valor final válido.");
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
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> coordenadorService.salvar(depoimento), "Deve informar um depoimento válido.");
    }

    @Test
    public void deveGerarErroAoTentarSalvarDepoimentoSemTexto(){
        Depoimento depoimento = Depoimento.builder()
                .date(LocalDate.now())
                .egresso(new Egresso(1, "lucas", "meuemail.com", "novadescricao", "novafoto", "linkparalinkedin", "linkparainstagram", "meucurriculo"))
                .build();
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> coordenadorService.salvar(depoimento), "Deve inserir um texto válido.");
    }

    @Test
    public void deveGerarErroAoTentarSalvarDepoimentoSemData(){
        Depoimento depoimento = Depoimento.builder()
                .texto("Texto do depoimento")
                .egresso(new Egresso(1, "lucas", "meuemail.com", "novadescricao", "novafoto", "linkparalinkedin", "linkparainstagram", "meucurriculo"))
                .build();
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> coordenadorService.salvar(depoimento), "Deve inserir uma data válida.");
    }

    @Test
    public void deveGerarErroAoTentarSalvarDepoimentoSemEgresso(){
        Depoimento depoimento = Depoimento.builder()
                .texto("Texto do depoimento")
                .date(LocalDate.now())
                .build();
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> coordenadorService.salvar(depoimento), "Deve associar um egresso válido.");
    }

     /* 
     * - CURSO
     *      -> deveGerarErroAoTentarSalvarCursoNulo
     *      -> deveGerarErroAoTentarSalvarCursoSemNome
     *      -> deveGerarErroAoTentarSalvarCursoSemNivel
     *      -> deveGerarErroAoTentarSalvarCursoSemCoordenador
     */

    @Test
    public void deveGerarErroAoTentarSalvarCursoNulo(){
        Curso curso = Curso.builder().build();
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> coordenadorService.salvar(curso), "Deve informar um curso válido.");
    }

    @Test
    public void deveGerarErroAoTentarSalvarCursoSemNome(){
        Curso curso = Curso.builder()
                .nivel("Graduação")
                .coordenador(new Coordenador(1, "login", "senha", "tipo"))
                .build();
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> coordenadorService.salvar(curso), "Deve inserir um nome válido.");
    }

    @Test
    public void deveGerarErroAoTentarSalvarCursoSemNivel(){
        Curso curso = Curso.builder()
                .nome("Curso de Teste")
                .coordenador(new Coordenador(1, "login", "senha", "tipo"))
                .build();
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> coordenadorService.salvar(curso), "Deve inserir um nível válido.");
    }

    @Test
    public void deveGerarErroAoTentarSalvarCursoSemCoordenador(){
        Curso curso = Curso.builder()
                .nome("Curso de Teste")
                .nivel("Graduação")
                .build();
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> coordenadorService.salvar(curso), "Deve associar um coordenador válido.");
    }

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
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> coordenadorService.salvar(egresso), "Deve informar um egresso válido.");
    }

    @Test
    public void deveGerarErroAoTentarSalvarEgressoSemNome(){
        Egresso egresso = Egresso.builder()
                .email("meuemail.com")
                .build();
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> coordenadorService.salvar(egresso), "Deve inserir um nome válido.");
    }

    @Test
    public void deveGerarErroAoTentarSalvarEgressoSemEmail(){
        Egresso egresso = Egresso.builder()
                .nome("Lucas")
                .build();
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> coordenadorService.salvar(egresso), "Deve inserir um email válido.");
    }

    @Test
    public void deveGerarErroAoTentarSalvarFormatoEmailIncorreto(){
        Egresso egresso = Egresso.builder()
                .nome("Lucas")
                .email("email_incorreto")
                .build();
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> coordenadorService.salvar(egresso), "Deve inserir um email no formato correto.");
    }

    @Test
    public void deveGerarErroAoTentartSalvarComMesmoEmail(){
        Egresso egresso = Egresso.builder()
                .nome("Lucas")
                .email("meuemail.com")
                .build();
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> coordenadorService.salvar(egresso), "Esse email já está em uso.");
        
        egressoRepositorio.delete(egresso);
    }

    @Test
    public void deveGerarErroAoTentarSalvarFormatoLinkedinIncorreto(){
        Egresso egresso = Egresso.builder()
                .nome("Lucas")
                .email("meuemail.com")
                .linkedin("linkedin_incorreto")
                .build();
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> coordenadorService.salvar(egresso), "Deve inserir um link do LinkedIn no formato correto.");
    }

    @Test
    public void deveGerarErroAoTentarSalvarFormatoInstagramIncorreto(){
        Egresso egresso = Egresso.builder()
                .nome("Lucas")
                .email("meuemail.com")
                .instagram("instagram_incorreto")
                .build();
        Assertions.assertThrows(RegraNegocioRunTime.class, () -> coordenadorService.salvar(egresso), "Deve inserir um link do Instagram no formato correto.");
    }
     
   
     /* 
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
