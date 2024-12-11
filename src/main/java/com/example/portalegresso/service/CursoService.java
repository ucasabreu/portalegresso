package com.example.portalegresso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.portalegresso.model.entidades.Curso;
import com.example.portalegresso.model.repositorio.CursoRepositorio;

@Service
public class CursoService {
    @Autowired
    CursoRepositorio repositorio;

    public Curso salvar(Curso curso){
        verificarCurso(curso);
        return repositorio.save(curso);
    }

    public Curso atualizar(Curso curso){
        verificarId(curso);
        verificarCurso(curso);
        if(!repositorio.existsById(curso.getId_curso())){
            throw new RegraNegocioRunTime("Curso não encontrado para atualização.");
        }
        return repositorio.save(curso);
    }

    public void remover(Curso curso){
        verificarId(curso);
        repositorio.deleteById(curso.getId_curso());
     }

    public List<Curso> listarTodosCursos(){
        return repositorio.findAll();
    }

    public List<Curso> listarPorFiltros(String nome, String nivel){
        return repositorio.filtrarCursos(nome, nivel);
    }

    private void verificarId(Curso curso){
        if ((curso == null) || (curso.getId_curso() == null)){
            throw new RegraNegocioRunTime("Um curso válido deve ser informado.");
        }
    }

    private void verificarCurso(Curso curso){
        if(curso == null){
            throw new RegraNegocioRunTime("Um curso válido deve ser informado.");
        }

        if((curso.getNome() == null) || (curso.getNome().isEmpty())){
            throw new RegraNegocioRunTime("Um nome deve ser infomado."); 
        }

        if((curso.getNivel() == null) || (curso.getNivel().isEmpty())){
            throw new RegraNegocioRunTime("Um nivel deve ser informado.");
        }

        if((curso.getCoordenador() == null || (curso.getCoordenador().getId_coordenador() == null))){
            throw new RegraNegocioRunTime("Um coordenador válido deve ser associado.");
        }
    }
}
