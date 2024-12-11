package com.example.portalegresso.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.portalegresso.model.entidades.Cargo;
import com.example.portalegresso.model.repositorio.CargoRepositorio;

@Service
public class CargoService {
    @Autowired
    CargoRepositorio repositorio;


    public Cargo salvar(Cargo cargo){
        verificarCargo(cargo); // validação antes de salvar
        return repositorio.save(cargo);
    }

    public Cargo atualizar(Cargo cargo){
        verificarId(cargo); // Verifica se o cargo possui um ID válido para atualizar
        verificarCargo(cargo);// Valida os dados do cargo
        if(!repositorio.existsById(cargo.getId_cargo())){
            throw new RegraNegocioRunTime("Cargo não encontrado para atualização.");
        }
        return null;
    }

    public void remover(Cargo cargo){
        verificarId(cargo);
        repositorio.deleteById(cargo.getId_cargo());
    }

    public void verificarId(Cargo cargo){
        if((cargo == null) || (cargo.getId_cargo() == null)){
            throw new RegraNegocioRunTime("Um cargo valido deve ser informado.");
        }
    }

    public void verificarCargo(Cargo cargo){
        if(cargo == null){
            throw new RegraNegocioRunTime("Um cargo válido deve ser informado.");
        }

        if((cargo.getDescricao() == null) || cargo.getDescricao().isEmpty()){
            throw new RegraNegocioRunTime("A descrição do cargo deve ser informada.");
        }

        if((cargo.getLocal() == null) || cargo.getLocal().isEmpty()){
            throw new RegraNegocioRunTime("O local deve ser informado.");
        }
        
       if((cargo.getAno_inicio() <= 1990) || (cargo.getAno_inicio() > LocalDate.now().getYear())){
            throw new RegraNegocioRunTime("O ano de inicio deve ser válido.");
       }

       if((cargo.getAno_fim() < cargo.getAno_inicio()) || (cargo.getAno_fim() > LocalDate.now().getYear())){
            throw new RegraNegocioRunTime("O ano de fim deve ser maior ou igual ao ano de inicio e válido.");
       }
        
        //Verificar se o egresso associado é valido
        if((cargo.getEgresso() == null) == (cargo.getEgresso().getId_egresso() == null)){
            throw new RegraNegocioRunTime("Um egresso válido deve estar associado ao cargo.");
        }

        //Devo verificar se o Egresso existe no Banco de dados pelo repositorio Egresso?
        

    }

}
