package com.example.portalegresso.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.portalegresso.model.entidades.Coordenador;
import com.example.portalegresso.model.entidades.Curso;
import com.example.portalegresso.model.repositorio.CoordenadorRepositorio;
import com.example.portalegresso.model.repositorio.CursoEgressoRepositorio;
import com.example.portalegresso.model.repositorio.CursoRepositorio;

import jakarta.transaction.Transactional;

@Service
public class CoordenadorService {

    @Autowired
    CoordenadorRepositorio coordenadorRepositorio;

    @Autowired
    CursoRepositorio cursoRepositorio;
    
    @Autowired
    CursoEgressoRepositorio cursoEgressoRepositorio;
  

    public boolean efetuarLogin(String login, String senha){
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
        verificarId(curso);
        verificarCurso(curso);
        if(!cursoRepositorio.existsById(curso.getId_curso())){
            throw new RegraNegocioRunTime("Curso não encontrado para atualização.");
        }
        return cursoRepositorio.save(curso);
    }

   
    /*
     * Funcões de verificação
     */
    private void verificarId(Coordenador coordenador){
        if((coordenador == null) || (coordenador.getId_coordenador() == null)){
            throw new RegraNegocioRunTime("Coordenador invalido");
        }
    }

   

    private void verificarId(Curso curso){
        if ((curso == null) || (curso.getId_curso() == null)){
            throw new RegraNegocioRunTime("Um curso válido deve ser informado.");
        }
    }

  

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

   

    /*
     * Funcões para remover
     */
   

    public void remover(Curso curso){
        verificarId(curso);
        cursoRepositorio.deleteById(curso.getId_curso());
    }

   

    //remover depoimento

    

    
}
