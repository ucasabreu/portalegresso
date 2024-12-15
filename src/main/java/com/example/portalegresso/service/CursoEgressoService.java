package com.example.portalegresso.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.portalegresso.model.entidades.CursoEgresso;
import com.example.portalegresso.model.repositorio.CursoEgressoRepositorio;

@Service
public class CursoEgressoService {
    @Autowired
    CursoEgressoRepositorio repositorio;

    public CursoEgresso salvar(CursoEgresso cursoEgresso){
        verificarCursoEgresso(cursoEgresso);
        return repositorio.save(cursoEgresso);
    }
    
    public CursoEgresso atualizar(CursoEgresso cursoEgresso){
        verificarId(cursoEgresso);
        verificarCursoEgresso(cursoEgresso);
        if(repositorio.existsById(cursoEgresso.getId_curso_egresso())){
            throw new RegraNegocioRunTime("Registro não encontrado para atualização.");
        }
        return repositorio.save(cursoEgresso);
    }
    
    private void verificarId(CursoEgresso cursoEgresso){
        if((cursoEgresso == null) || cursoEgresso.getId_curso_egresso() == null){
            throw new RegraNegocioRunTime("Um ID válido deve ser informado.");
        }
    }
    
    private void verificarCursoEgresso(CursoEgresso cursoEgresso){
        if(cursoEgresso == null){
            throw new RegraNegocioRunTime("Um curso e egresso válidos devem ser informados.");
        }

        if((cursoEgresso.getCurso() == null) || (cursoEgresso.getCurso().getId_curso() == null)){
            throw new RegraNegocioRunTime("O curso deve ser informado e válido.");
        }

        if((cursoEgresso.getEgresso() == null) || (cursoEgresso.getEgresso().getId_egresso() == null)){
            throw new RegraNegocioRunTime("Um egresso deve ser informado e válido.");
        }

        if((cursoEgresso.getAno_inicio() <= 1980) || (cursoEgresso.getAno_inicio() > LocalDate.now().getYear())){
            throw new RegraNegocioRunTime("o ano de inicio deve ser válido.");
        }

        if((cursoEgresso.getAno_fim() < cursoEgresso.getAno_inicio()) || (cursoEgresso.getAno_fim() > LocalDate.now().getYear())){
            throw new RegraNegocioRunTime("O ano de fim deve ser maior ou igual ao ano de início e válido.");
        }
    }

}
