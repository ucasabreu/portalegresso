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

import com.example.portalegresso.dto.CargoDTO;
import com.example.portalegresso.dto.CursoEgressoDTO;
import com.example.portalegresso.dto.DepoimentoDTO;
import com.example.portalegresso.dto.EgressoDTO;
import com.example.portalegresso.model.entidades.Cargo;
import com.example.portalegresso.model.entidades.Curso;
import com.example.portalegresso.model.entidades.CursoEgresso;
import com.example.portalegresso.model.entidades.Depoimento;
import com.example.portalegresso.model.entidades.Egresso;
import com.example.portalegresso.service.EgressoService;
import com.example.portalegresso.service.RegraNegocioRunTime;

@RestController
@RequestMapping("/api/egressos")
public class EgressoController {
    @Autowired
    EgressoService egressoService;


    // POST
    /*
     * -> POST /api/egressos/salvar/egresso
     * -> POST /api/egressos/salvar/depoimento
     * -> POST /api/egressos/salvar/cargo
     * 
     */

    @PostMapping("/salvar/egresso") //ok
    public ResponseEntity<?> salvar(@RequestBody EgressoDTO dto){
        Egresso egresso = Egresso.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .descricao(dto.getDescricao())
                .foto(dto.getFoto())
                .linkedin(dto.getLinkedin())
                .instagram(dto.getInstagram())
                .curriculo(dto.getCurriculo())
                .build();
        
        try{
            Egresso salvo = egressoService.salvar(egresso);
            return new ResponseEntity<>(salvo, HttpStatus.CREATED);
        }
        catch(RegraNegocioRunTime e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/salvar/depoimento")//ok
    public ResponseEntity<?> salvarDepoimento(@RequestBody DepoimentoDTO dto){
       Depoimento depoimento = Depoimento.builder()
                .egresso(Egresso.builder().id_egresso(dto.getId_egresso()).build())
                .texto(dto.getTexto())
                .build();
        try{
            Depoimento salvo = egressoService.salvar(depoimento);
            return new ResponseEntity<>(salvo, HttpStatus.CREATED);
        }
        catch(RegraNegocioRunTime e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
    }

    @PostMapping("/salvar/cargo") //OK
    public ResponseEntity<?> salvarCargo(@RequestBody CargoDTO dto){
        Cargo cargo = Cargo.builder()
                    .egresso(Egresso.builder().id_egresso(dto.getId_egresso()).build())
                    .descricao(dto.getDescricao())
                    .local(dto.getLocal())
                    .ano_inicio(dto.getAno_inicio())
                    .ano_fim(dto.getAno_fim())
                    .build();  
                    
        try{
            Cargo salvo = egressoService.salvar(cargo);
            return new ResponseEntity<>(salvo, HttpStatus.CREATED);
        }
        catch(RegraNegocioRunTime e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/salvar/curso_egresso")//ok
    public ResponseEntity<?> salvarCursoEgresso(@RequestBody CursoEgressoDTO dto){
       CursoEgresso cursoEgresso = CursoEgresso.builder()
                .egresso(Egresso.builder().id_egresso(dto.getId_egresso()).build())
                .curso(Curso.builder().id_curso(dto.getId_curso()).build())
                .ano_inicio(dto.getAno_inicio())
                .ano_fim(dto.getAno_fim())
                .build();
        try{
            CursoEgresso salvo = egressoService.salvar(cursoEgresso);
            return new ResponseEntity<>(salvo, HttpStatus.CREATED);
        }
        catch(RegraNegocioRunTime e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
    }
    // GET

    // DELETE
    /*
     * -> DELETE /api/egressos/deletar/egresso
     * -> DELETE /api/egressos/deletar/depoimento
     * -> DELETE /api/egressos/deletar/cargo
     */

    @DeleteMapping("/deletar/egresso/{id}")//OK
    public ResponseEntity<?> removerEgresso(@PathVariable("id") Integer idEgresso){
        try{
            Egresso egresso = Egresso.builder().id_egresso(idEgresso).build();
            egressoService.remover(egresso);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(RegraNegocioRunTime e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deletar/depoimento/{id}")//ok
    public ResponseEntity<?> removerDepoimento(@PathVariable("id") Integer idDepoimento){
        try{
            Depoimento depoimento = Depoimento.builder().id_depoimento(idDepoimento).build();
            egressoService.remover(depoimento);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(RegraNegocioRunTime e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/deletar/cargo/{id}")//ok
    public ResponseEntity<?> removerCargo(@PathVariable("id") Integer idCargo){
        try{
            Cargo cargo = Cargo.builder().id_cargo(idCargo).build();
            egressoService.remover(cargo);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(RegraNegocioRunTime e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
