package com.example.portalegresso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.portalegresso.model.entidades.Egresso;
import com.example.portalegresso.model.repositorio.EgressoRepositorio;

@Service
public class EgressoService {
    @Autowired
    EgressoRepositorio repositorio;

    public Egresso salvar(Egresso egresso){
        verificarEgresso(egresso);
        return repositorio.save(egresso);
    }

    public Egresso atualizar(Egresso egresso){
        verificarId(egresso);
        verificarEgresso(egresso);
        if(!repositorio.existsById(egresso.getId_egresso())){
            throw new RegraNegocioRunTime("Egresso não encontrato.");
        }
        return repositorio.save(egresso);
    }

    public void remover(Egresso egresso){
        verificarId(egresso);
        repositorio.deleteById(egresso.getId_egresso());
    }
        
    private void verificarId(Egresso egresso){
        if((egresso == null) || (egresso.getId_egresso() == null)){
            throw new RegraNegocioRunTime("Um egresso válido deve ser informado");
        }
    }

    private void verificarEgresso(Egresso egresso){
        if(egresso == null){
            throw new RegraNegocioRunTime("Um egresso válido deve ser informado.");
        }

        if((egresso.getNome() == null) || (egresso.getNome().isEmpty())){
            throw new RegraNegocioRunTime("O nome do egresso deve ser informado.");
        }

        if((egresso.getEmail() == null) || (egresso.getEmail().isEmpty())){
            throw new RegraNegocioRunTime("O email do egresso deve ser informado.");
        }

        /* Verificação para garantir o formato correto das contas de e-mail,instagram e linkedin */
        if (!egresso.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new RegraNegocioRunTime("O email do egresso é inválido.");
        }
    
        // Verifica se o e-mail ja foi usado
        if (repositorio.existsByEmail(egresso.getEmail())) {
            throw new RegraNegocioRunTime("O email informado já está em uso por outro egresso.");
        }
    
        if (egresso.getLinkedin() != null && !egresso.getLinkedin().isEmpty()) {
            if (!egresso.getLinkedin().matches("^(https?:\\/\\/)?([\\w.-]+\\.)?linkedin\\.com\\/.*$")) {
                throw new RegraNegocioRunTime("O link do LinkedIn é inválido.");
            }
        }
    
        if (egresso.getInstagram() != null && !egresso.getInstagram().isEmpty()) {
            if (!egresso.getInstagram().matches("^(https?:\\/\\/)?(www\\.)?instagram\\.com\\/.*$")) {
                throw new RegraNegocioRunTime("O link do Instagram é inválido.");
            }
        }
        /*fim de verificação */
    }

    

}
