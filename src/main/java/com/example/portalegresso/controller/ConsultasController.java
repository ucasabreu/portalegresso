package com.example.portalegresso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.portalegresso.model.entidades.Cargo;
import com.example.portalegresso.model.entidades.Curso;
import com.example.portalegresso.model.entidades.Depoimento;
import com.example.portalegresso.model.entidades.Egresso;
import com.example.portalegresso.service.ConsultasService;
import com.example.portalegresso.service.RegraNegocioRunTime;

@RestController
@RequestMapping("/api/consultas")
public class ConsultasController {
    @Autowired
    ConsultasService consultasService;

    //GET

    // -> CURSOS
    @GetMapping("/listar/cursos")
    public ResponseEntity<?> listarTodosCursos(){
        try{
            List<Curso> cursos = consultasService.listarTodosCursos();
            return new ResponseEntity<>(cursos, HttpStatus.OK);
        }
        catch(RegraNegocioRunTime e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
   
    @GetMapping("/listar/cursos/nivel")
    public ResponseEntity<?> listarPorFiltros(String nivel) {
        try {
            List<Curso> cursos = consultasService.listarPorFiltros(nivel);
            return new ResponseEntity<>(cursos, HttpStatus.OK);
        } catch (RegraNegocioRunTime e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // -> DEPOIMENTOS

    @GetMapping("/listar/depoimentos")
    public ResponseEntity<?> listarDepoimentosRecentes(){
        try{
            List<Depoimento> depoimentos = consultasService.consultarRecentes();
            return new ResponseEntity<>(depoimentos, HttpStatus.OK);
        }
        catch(RegraNegocioRunTime e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listar/depoimentos/limite")
    public ResponseEntity<?> listarDepoimentosRecentesComLimite(Integer limite){
        try{
            List<Depoimento> depoimentos = consultasService.consultarRecentes(limite);
            return new ResponseEntity<>(depoimentos, HttpStatus.OK);
        }
        catch(RegraNegocioRunTime e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //TODO: verificar se a pesquisa por ano esta correta
    @GetMapping("/listar/depoimentos/ano")
    public ResponseEntity<?> listarDepoimentosPorAno(int ano){
        try{
            List<Depoimento> depoimentos = consultasService.consultarPorAno(ano);
            return new ResponseEntity<>(depoimentos, HttpStatus.OK);
        }
        catch(RegraNegocioRunTime e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listar/cargos")
    public ResponseEntity<?> listarCargos(){
        try{
            List<Cargo> cargos = consultasService.listarCargos();
            return new ResponseEntity<>(cargos, HttpStatus.OK);
        }
        catch(RegraNegocioRunTime e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    // -> EGRESSOS
    @GetMapping("/listar/egressos/nome")
    public ResponseEntity<?> listarEgressosPorNome(String nome){
        try{
            List<Egresso> egressos = consultasService.consultarEgressosPorNome(nome);
            return new ResponseEntity<>(egressos, HttpStatus.OK);
        }
        catch(RegraNegocioRunTime e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //TODO: verificar pesquisa por cargo esta correta
    @GetMapping("/listar/egressos/cargo")
    public ResponseEntity<?> listarEgressosPorCargo(String cargo){
        try{
            List<Egresso> egressos = consultasService.consultarEgressosPorCargo(cargo);
            return new ResponseEntity<>(egressos, HttpStatus.OK);
        }
        catch(RegraNegocioRunTime e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listar/egressos/curso")
    public ResponseEntity<?> listarEgressosPorCurso(String curso){
        try{
            List<Egresso> egressos = consultasService.consultarEgressosPorCurso(curso);
            return new ResponseEntity<>(egressos, HttpStatus.OK);
        }
        catch(RegraNegocioRunTime e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listar/egressos/ano_inicio")
    public ResponseEntity<?> listarEgressosPorAnoInicioCurso(Integer ano){
        try{
            List<Egresso> egressos = consultasService.consultarEgressosPorAnoInicio(ano);
            return new ResponseEntity<>(egressos, HttpStatus.OK);
        }
        catch(RegraNegocioRunTime e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listar/egressos/ano_fim")
    public ResponseEntity<?> listarEgressosPorAnoFimCurso(Integer ano){
        try{
            List<Egresso> egressos = consultasService.consultarEgressosPorAnoFim(ano);
            return new ResponseEntity<>(egressos, HttpStatus.OK);
        }
        catch(RegraNegocioRunTime e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
