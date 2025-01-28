package com.example.portalegresso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.portalegresso.model.entidades.Coordenador;
import com.example.portalegresso.model.entidades.Curso;
import com.example.portalegresso.model.repositorio.CoordenadorRepositorio;
import com.example.portalegresso.model.repositorio.CursoRepositorio;

import jakarta.transaction.Transactional;

@Service
public class CoordenadorService {

    @Autowired
    CoordenadorRepositorio coordenadorRepositorio;

    @Autowired
    CursoRepositorio cursoRepositorio;
    
   
  

    public boolean efetuarLogin(String login, String senha){
        if((login == null) || (login.isEmpty()))
            throw new RegraNegocioRunTime("Login deve ser informado.");
        if((senha == null) || (senha.isEmpty()))
            throw new RegraNegocioRunTime("Senha deve ser informada.");
        
        Optional<Coordenador> coord = coordenadorRepositorio.findByLogin(login);
        
        if(!coord.isPresent())
            throw new RegraNegocioRunTime("Erro de autenticação. Login não encontrado.");
        if(!coord.get().getSenha().equals(senha))
            throw new RegraNegocioRunTime("Erro de autenticação. Senha incorreta.");
 
        return true;
    }

    /*
     * Funcões para salvar
     */
    @Transactional
    public Coordenador salvar(Coordenador coordenador){
        verificarCoordenador(coordenador);
        return coordenadorRepositorio.save(coordenador);
    }

   

    public Curso salvar(Curso curso){
        verificarCurso(curso);
        return cursoRepositorio.save(curso);
    }

   
    /*
     * Funcões para atualizar
     */

    public Curso atualizar(Curso curso){
        buscarCoordenadorPorId(curso.getId_curso());
        verificarCurso(curso);
        if(!cursoRepositorio.existsById(curso.getId_curso())){
            throw new RegraNegocioRunTime("Curso não encontrado para atualização.");
        }
        return cursoRepositorio.save(curso);
    }

   
    /*
     * Funcões de verificação
     */    

    private void verificarCoordenador(Coordenador coordenador){
        if(coordenador == null){
            throw new RegraNegocioRunTime("Um usuário válido deve ser informado.");
        }

        if((coordenador.getLogin() == null) || (coordenador.getLogin().isEmpty())){
            throw new RegraNegocioRunTime("Login deve ser informado.");
        } 

        // verificar tipo?

        boolean teste = coordenadorRepositorio.existsByLogin(coordenador.getLogin());

        if(teste){
            throw new RegraNegocioRunTime("Login informado já existe");
        }

        if((coordenador.getSenha() == null) || (coordenador.getSenha().isEmpty())){
            throw new RegraNegocioRunTime("Usuario deve possuir senha.");
        }

    }


    private void verificarCurso(Curso curso){
        buscarCoordenadorPorId(curso.getCoordenador().getId_coordenador());
        
        

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

    /*
     * Funcões para remover
     */
   

    public void remover(Curso curso){
        buscarCursoPorId(curso.getId_curso());
        cursoRepositorio.deleteById(curso.getId_curso());
    }

    public void remover(Coordenador coordenador){
        Coordenador coordenadorExistente = buscarCoordenadorPorId(coordenador.getId_coordenador());

        // Remover todos os cursos relacionados ao coordenador
        List<Curso> cursos = cursoRepositorio.findByCoordenador(coordenadorExistente);
        for(Curso curso : cursos){
            cursoRepositorio.delete(curso);
        }
        
        // Deletar dados do coordenador
        coordenadorRepositorio.deleteById(coordenador.getId_coordenador());
    }

    /*
     * Funcões para buscar
     */
   
    public Coordenador buscarCoordenadorPorId(Integer id) {
        return coordenadorRepositorio.findById(id).orElseThrow(() -> new RegraNegocioRunTime("Coordenador não encontrado com o ID: " + id));
    }

    public Curso buscarCursoPorId(Integer id) {
        return cursoRepositorio.findById(id).orElseThrow(() -> new RegraNegocioRunTime("Curso não encontrado com o ID: " + id));
    }
    

    
}
