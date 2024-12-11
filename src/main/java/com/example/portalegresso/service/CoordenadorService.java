package com.example.portalegresso.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.portalegresso.model.entidades.Coordenador;
import com.example.portalegresso.model.repositorio.CoordenadorRepositorio;

import jakarta.transaction.Transactional;

@Service
public class CoordenadorService {

    @Autowired
    CoordenadorRepositorio repositorio;

    public boolean efetuarLogin(String login, String senha){
        Optional<Coordenador> coord = repositorio.findByLogin(login);
        
        if(!coord.isPresent())
            throw new RegraNegocioRunTime("Erro de autenticação. Login não encontrado.");
        if(!coord.get().getSenha().equals(senha))
            throw new RegraNegocioRunTime("Erro de autenticação. Senha incorreta.");
 
        return true;
    }

    @Transactional
    public Coordenador salvar(Coordenador coordenador){
        verificarCoordenador(coordenador);
        return repositorio.save(coordenador);
    }
    

    private void verificarId(Coordenador coordenador){
        if((coordenador == null) || (coordenador.getId_coordenador() == null)){
            throw new RegraNegocioRunTime("Coordenador invalido");
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

        boolean teste = repositorio.existsByLogin(coordenador.getLogin());

        if(teste){
            throw new RegraNegocioRunTime("Login informado já existe");
        }

        if((coordenador.getSenha() == null) || (coordenador.getSenha().isEmpty())){
            throw new RegraNegocioRunTime("Usuario deve possuir senha.");
        }

    }

    
}
