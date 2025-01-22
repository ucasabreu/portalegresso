package com.example.portalegresso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.portalegresso.dto.CoordenadorDTO;
import com.example.portalegresso.dto.CursoDTO;
import com.example.portalegresso.model.entidades.Coordenador;
import com.example.portalegresso.model.entidades.Curso;
import com.example.portalegresso.service.CoordenadorService;
import com.example.portalegresso.service.RegraNegocioRunTime;


@RestController
@RequestMapping("/api/coordenadores")
public class CoordenadorController {
    @Autowired
    CoordenadorService coordenadorService;


    // POST
    @PostMapping("/salvar/coordenador")
    public ResponseEntity<?> salvar(@RequestBody CoordenadorDTO dto){
        Coordenador coordenador = Coordenador.builder()
                //.id_coordenador(dto.getId_coordenador())
                .login(dto.getLogin())
                .senha(dto.getSenha())
                .tipo(dto.getTipo())
                .build();
        
        try{
            Coordenador salvo = coordenadorService.salvar(coordenador);
            return new ResponseEntity<>(salvo, HttpStatus.CREATED);
        }
        catch(RegraNegocioRunTime e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/autenticar/coordenador")
    public ResponseEntity<?> autenticar(@RequestBody CoordenadorDTO dto){
        try {
            coordenadorService.efetuarLogin(dto.getLogin(), dto.getSenha());
            return ResponseEntity.ok(true);
        } catch (RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/salvar/curso")
    public ResponseEntity<?> salvarCurso(@RequestBody CursoDTO dto) {
        try {
            Coordenador coordenador = coordenadorService.buscarCoordenadorPorId(dto.getId_coordenador());
            if (coordenador == null) {
                return new ResponseEntity<>("Coordenador não encontrado", HttpStatus.BAD_REQUEST);
            }
    
            Curso curso = Curso.builder()
                    .nome(dto.getNome())
                    .nivel(dto.getNivel())
                    .coordenador(coordenador)
                    .build();
    
            Curso salvo = coordenadorService.salvar(curso);
            return new ResponseEntity<>(salvo, HttpStatus.CREATED);
        } catch (RegraNegocioRunTime e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // DELETE
    @DeleteMapping("/deletar/coordenador/{id}")
    public ResponseEntity<?> remover(@PathVariable("id") Integer idCoordenador){
        try {
            Coordenador coor = Coordenador.builder().id_coordenador(idCoordenador).build();
            coordenadorService.remover(coor);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(RegraNegocioRunTime e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/curso/deletar/{id}")
    public ResponseEntity<?> removerCurso(@PathVariable("id") Integer idCurso){
        try {
            Curso curso = Curso.builder().id_curso(idCurso).build();
            coordenadorService.remover(curso);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(RegraNegocioRunTime e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
