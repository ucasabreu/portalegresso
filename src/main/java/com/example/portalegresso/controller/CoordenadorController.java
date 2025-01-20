package com.example.portalegresso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.portalegresso.dto.CoordenadorDTO;
import com.example.portalegresso.model.entidades.Coordenador;
import com.example.portalegresso.service.CoordenadorService;
import com.example.portalegresso.service.RegraNegocioRunTime;

@RestController
@RequestMapping("/api/coordenadores")
public class CoordenadorController {
    @Autowired
    CoordenadorService coordenadorService;


    @PostMapping("/salvar")
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

    @PostMapping("/autenticar")
    public ResponseEntity autenticar(@RequestBody CoordenadorDTO dto){
        try {
            coordenadorService.efetuarLogin(dto.getLogin(), dto.getSenha());
            return ResponseEntity.ok(true);
        } catch (RegraNegocioRunTime e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
