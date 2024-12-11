package com.example.portalegresso.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.portalegresso.model.entidades.Depoimento;
import com.example.portalegresso.model.repositorio.DepoimentoRepositorio;

@Service
public class DepoimentoService {
    @Autowired
    DepoimentoRepositorio repositorio;

    public Depoimento salvar(Depoimento depoimento){
        verificarDados(depoimento); // validação antes de salvar
        return repositorio.save(depoimento);
    }

    public Depoimento atualizar(Depoimento depoimento){
        verificarId(depoimento);
        verificarDados(depoimento);
        if(!repositorio.existsById(depoimento.getId_depoimento())){
            throw new RegraNegocioRunTime("Depoimento não encontrado para atualização.");
        }
        return repositorio.save(depoimento);
    }

    /* ------- Consulta depoimentos ------- */
    //opcao para adicionar o limite de depoimentos maximo (opcional), caso para sem limite == null.
    public List<Depoimento> consultarRecentes(Integer limite){
        
        if(limite > 0){
            //Define a paginação para limitar os resultados
            Pageable pageable = PageRequest.of(0,limite);
            return repositorio.findRecentes(pageable);
        } 
        else {
            throw new RegraNegocioRunTime("Valor limite deve ser válido.");
        }     
    }

    public List<Depoimento> consultarRecentes(){
        return repositorio.findAllByOrderByDateDesc();
    }

    public List<Depoimento> consultarPorAno(int ano){
        if(ano <= 0){
            throw new RegraNegocioRunTime("o ano deve ser maior que zero.");
        }

        return repositorio.findByAno(ano);
    }

    /* fim consulta depoimentos */

    private void verificarId(Depoimento depoimento) {
        if((depoimento == null) || (depoimento.getId_depoimento() == null) ){
            throw new RegraNegocioRunTime("Um depoimento válido deve ser informado.");
        }
    }

    private void verificarDados(Depoimento depoimento) {
        if(depoimento == null){
            throw new RegraNegocioRunTime("Um depoimento válido deve ser informado.");
        }

        if((depoimento.getTexto() == null) || depoimento.getTexto().isEmpty()){
            throw new RegraNegocioRunTime("Um texto deve ser informado.");
        }

        //-------- Verificação de Date --------
        // Date não pode ser nula
        if(depoimento.getDate() == null){
            throw new RegraNegocioRunTime("Data do depoimento deve ser informada.");
        }
        // Date não pode ser no futuro
        LocalDate dataDepoimento = depoimento.getDate();
        LocalDate dataAtual = LocalDate.now();
        // Verifica se a data é no futuro
        if(dataDepoimento.isAfter(dataAtual)){
            throw new RegraNegocioRunTime("Data do depoimento não pode ser no futuro.");
        }
        //(Opcional) A data deve estar dentro de um intervalo específico, como não mais de 10 anos atrás?
        //-------------------------------------
        if(depoimento.getEgresso() == null || depoimento.getEgresso().getId_egresso() == null){
            throw new RegraNegocioRunTime("Um egresso válido deve estar associado ao depoimento.");
        }
    }

}
