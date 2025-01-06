package com.example.portalegresso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.portalegresso.model.entidades.Curso;
import com.example.portalegresso.model.entidades.Depoimento;
import com.example.portalegresso.model.entidades.Egresso;
import com.example.portalegresso.model.repositorio.CargoRepositorio;
import com.example.portalegresso.model.repositorio.CursoEgressoRepositorio;
import com.example.portalegresso.model.repositorio.CursoRepositorio;
import com.example.portalegresso.model.repositorio.DepoimentoRepositorio;
import com.example.portalegresso.model.repositorio.EgressoRepositorio;

public class ConsultasService {
    @Autowired
    CursoRepositorio cursoRepositorio;

    @Autowired
    DepoimentoRepositorio depoimentoRepositorio;

    @Autowired
    private EgressoRepositorio egressoRepositorio;

    @Autowired
    private CursoEgressoRepositorio cursoEgressoRepositorio;

    @Autowired
    private CargoRepositorio cargoRepositorio;


/* ------- Consulta cursos ------- */    
    public List<Curso> listarTodosCursos(){
        return cursoRepositorio.findAll();
    }

    public List<Curso> listarPorFiltros(String nome, String nivel){
        return cursoRepositorio.filtrarCursos(nome, nivel);
    }


/* ------- Consulta depoimentos ------- */
//opcao para adicionar o limite de depoimentos maximo (opcional), caso para sem limite == null.
    public List<Depoimento> consultarRecentes(Integer limite){
        
        if(limite > 0){
            //Define a paginação para limitar os resultados
            Pageable pageable = PageRequest.of(0,limite);
            return depoimentoRepositorio.findRecentes(pageable);
        } 
        else {
            throw new RegraNegocioRunTime("Valor limite deve ser válido.");
        }     
    }

    public List<Depoimento> consultarRecentes(){
        return depoimentoRepositorio.findAllByOrderByDateDesc();
    }

    public List<Depoimento> consultarPorAno(int ano){
        if(ano <= 0){
            throw new RegraNegocioRunTime("o ano deve ser maior que zero.");
        }

        return depoimentoRepositorio.findByAno(ano);
    }

/* fim consulta depoimentos */

/* ------- Consulta egressos ------- */
     // Consulta egressos por nome (busca parcial, case insensitive)
    public List<Egresso> consultarEgressosPorNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new RegraNegocioRunTime("Nome não pode ser vazio.");
        }
        return egressoRepositorio.findByNomeContainingIgnoreCase(nome);
    }

    // Consulta egressos por cargo
    public List<Egresso> consultarEgressosPorCargo(String cargo) {
        if (cargo == null || cargo.isEmpty()) {
            throw new RegraNegocioRunTime("Cargo não pode ser vazio.");
        }
        return cargoRepositorio.findEgressosByCargoDescricao(cargo);
    }

    // Consulta egressos por curso
    public List<Egresso> consultarEgressosPorCurso(String curso) {
        if (curso == null || curso.isEmpty()) {
            throw new RegraNegocioRunTime("Curso não pode ser vazio.");
        }
        return cursoEgressoRepositorio.findEgressosByCursoNome(curso);
    }

    // Consulta egressos por ano de início ou conclusão do curso
    public List<Egresso> consultarEgressosPorAno(int ano) {
        if (ano <= 0) {
            throw new RegraNegocioRunTime("O ano deve ser maior que zero.");
        }
        return cursoEgressoRepositorio.findEgressosByAno(ano);
    }
}
