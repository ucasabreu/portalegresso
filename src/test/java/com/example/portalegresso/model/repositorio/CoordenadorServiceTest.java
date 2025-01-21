package com.example.portalegresso.model.repositorio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.example.portalegresso.model.entidades.Coordenador;
import com.example.portalegresso.model.entidades.Curso;
import com.example.portalegresso.service.CoordenadorService;
import com.example.portalegresso.service.RegraNegocioRunTime;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class CoordenadorServiceTest {
    
    @Autowired
    CoordenadorService coordenadorService;

    @Autowired
    CoordenadorRepositorio repositorio;

    @Autowired
    CursoRepositorio cursoRepositorio;

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

    
     
   
     /* 
     * Teste de Funcionalidades
     * 
     *      -> deveEfetuarLoginCoordenador
     *      -> deveSalvarCoordenador
     *      -> deveSalvarCurso
     * 
     */
    
}
