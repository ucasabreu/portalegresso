package com.example.portalegresso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.portalegresso.model.entidades.Curso;
import com.example.portalegresso.model.entidades.Depoimento;
import com.example.portalegresso.model.entidades.Egresso;
import com.example.portalegresso.model.repositorio.CargoRepositorio;
import com.example.portalegresso.model.repositorio.CursoEgressoRepositorio;
import com.example.portalegresso.model.repositorio.CursoRepositorio;
import com.example.portalegresso.model.repositorio.DepoimentoRepositorio;
import com.example.portalegresso.model.repositorio.EgressoRepositorio;

@Service
public class ConsultasService {

   
    @Autowired
    CursoRepositorio cursoRepositorio;

    @Autowired
    DepoimentoRepositorio depoimentoRepositorio;

    @Autowired
    EgressoRepositorio egressoRepositorio;

    @Autowired
    CursoEgressoRepositorio cursoEgressoRepositorio;

    @Autowired
    CargoRepositorio cargoRepositorio;


/* ------- Consulta cursos ------- */    
    public List<Curso> listarTodosCursos(){
        if(cursoRepositorio.count() == 0){
            throw new RegraNegocioRunTime("Não há cursos cadastrados.");
        }
        return cursoRepositorio.findAll();
    }

    public List<Curso> listarPorFiltros(String nome, String nivel){
        if(cursoRepositorio.count() == 0){
            throw new RegraNegocioRunTime("Não há cursos cadastrados.");
        }
        if(nome == null || nome.isEmpty()){
            throw new RegraNegocioRunTime("Nome do curso não pode ser vazio.");
        }
        if(nivel == null || nivel.isEmpty()){
            throw new RegraNegocioRunTime("Nível do curso não pode ser vazio.");
        }
        
        return cursoRepositorio.filtrarCursos(nome, nivel);
    }


/* ------- Consulta depoimentos ------- */
//opcao para adicionar o limite de depoimentos maximo (opcional), caso para sem limite == null.
    public List<Depoimento> consultarRecentes(Integer limite){
        if(depoimentoRepositorio.count() == 0){
            throw new RegraNegocioRunTime("Não há depoimentos cadastrados.");
        }
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
        if(depoimentoRepositorio.count() == 0){
            throw new RegraNegocioRunTime("Não há depoimentos cadastrados.");
        }
        return depoimentoRepositorio.findAllByOrderByDateDesc();
    }

    public List<Depoimento> consultarPorAno(int ano){
        if(depoimentoRepositorio.count() == 0){
            throw new RegraNegocioRunTime("Não há depoimentos cadastrados.");
        }
        if(ano <= 0){
            throw new RegraNegocioRunTime("o ano deve ser maior que zero.");
        }

        return depoimentoRepositorio.findByAno(ano);
    }

/* fim consulta depoimentos */

/* ------- Consulta egressos ------- */
     // Consulta egressos por nome (busca parcial, case insensitive)
    public List<Egresso> consultarEgressosPorNome(String nome) {
        if(egressoRepositorio.count() == 0){
            throw new RegraNegocioRunTime("Não há egressos cadastrados.");
        }
        if (nome == null || nome.isEmpty()) {
            throw new RegraNegocioRunTime("Nome não pode ser vazio.");
        }
        return egressoRepositorio.findByNomeContainingIgnoreCase(nome);
    }

    // Consulta egressos por cargo
    public List<Egresso> consultarEgressosPorCargo(String cargo) {
        if(cargoRepositorio.count() == 0){
            throw new RegraNegocioRunTime("Não há cargos cadastrados.");
        }
        if(egressoRepositorio.count() == 0){
            throw new RegraNegocioRunTime("Não há egressos cadastrados.");
        }
        if (cargo == null || cargo.isEmpty()) {
            throw new RegraNegocioRunTime("Cargo não pode ser vazio.");
        }
        return cargoRepositorio.findEgressosByCargoDescricao(cargo);
    }

    // Consulta egressos por curso
    public List<Egresso> consultarEgressosPorCurso(String curso) {
        if(egressoRepositorio.count() == 0){
            throw new RegraNegocioRunTime("Não há egressos cadastrados.");
        }
        if (curso == null || curso.isEmpty()) {
            throw new RegraNegocioRunTime("Curso não pode ser vazio.");
        }
        return cursoEgressoRepositorio.findEgressosByCursoNome(curso);
    }

    // Consulta egressos por ano de início ou conclusão do curso
    public List<Egresso> consultarEgressosPorAno(int ano) {
        if(egressoRepositorio.count() == 0){
            throw new RegraNegocioRunTime("Não há egressos cadastrados.");
        }
        if (ano <= 0) {
            throw new RegraNegocioRunTime("O ano deve ser maior que zero.");
        }
        return cursoEgressoRepositorio.findEgressosByAno(ano);
    }
}
