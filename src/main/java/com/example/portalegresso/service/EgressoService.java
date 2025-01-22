package com.example.portalegresso.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.portalegresso.model.entidades.Cargo;
import com.example.portalegresso.model.entidades.Depoimento;
import com.example.portalegresso.model.entidades.Egresso;
import com.example.portalegresso.model.repositorio.CargoRepositorio;
import com.example.portalegresso.model.repositorio.CursoEgressoRepositorio;
import com.example.portalegresso.model.repositorio.DepoimentoRepositorio;
import com.example.portalegresso.model.repositorio.EgressoRepositorio;


@Service
public class EgressoService {
    
    @Autowired
    EgressoRepositorio egressoRepositorio;

    @Autowired
    CargoRepositorio cargoRepositorio;

    @Autowired
    DepoimentoRepositorio depoimentoRepositorio;

    @Autowired
    CursoEgressoRepositorio cursoEgressoRepositorio;

    public Cargo salvar(Cargo cargo){
        verificarCargo(cargo); // validação antes de salvar
        return cargoRepositorio.save(cargo);
    }
    
    public Depoimento salvar(Depoimento depoimento){
        verificarDepoimento(depoimento); // validação antes de salvar
        return depoimentoRepositorio.save(depoimento);
    }

    public Egresso salvar(Egresso egresso){
        verificarEgresso(egresso);
        return egressoRepositorio.save(egresso);
    }

    public Cargo atualizar(Cargo cargo){
        buscarCargoPorId(cargo.getId_cargo()); // Verifica se o cargo possui um ID válido para atualizar
        verificarCargo(cargo);// Valida os dados do cargo
        if(!cargoRepositorio.existsById(cargo.getId_cargo())){
            throw new RegraNegocioRunTime("Cargo não encontrado para atualização.");
        }
        return null;
    }

    public Depoimento atualizar(Depoimento depoimento){
        buscarDepoimentoPorId(depoimento.getId_depoimento());
        verificarDepoimento(depoimento);
        if(!depoimentoRepositorio.existsById(depoimento.getId_depoimento())){
            throw new RegraNegocioRunTime("Depoimento não encontrado para atualização.");
        }
        return depoimentoRepositorio.save(depoimento);

    }

    public Egresso atualizar(Egresso egresso){
        buscarEgressoPorId(egresso.getId_egresso());
        verificarEgresso(egresso);
        if(!egressoRepositorio.existsById(egresso.getId_egresso())){
            throw new RegraNegocioRunTime("Egresso não encontrato.");
        }
        return egressoRepositorio.save(egresso);
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
        Egresso egresso = cargo.getEgresso();
        if(buscarEgressoPorId(egresso.getId_egresso()) == null){
            throw new RegraNegocioRunTime("Egresso não encontrado. Informe um egresso válido.");
        }       

    }

    private void verificarDepoimento(Depoimento depoimento) {
        if(depoimento == null){
            throw new RegraNegocioRunTime("Um depoimento válido deve ser informado.");
        }

        if((depoimento.getTexto() == null) || depoimento.getTexto().isEmpty()){
            throw new RegraNegocioRunTime("Um texto deve ser informado.");
        }
        //-------------------------------------
        if(depoimento.getEgresso() == null || depoimento.getEgresso().getId_egresso() == null){
            throw new RegraNegocioRunTime("Um egresso válido deve estar associado ao depoimento.");
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
        if (egressoRepositorio.existsByEmail(egresso.getEmail())) {
            throw new RegraNegocioRunTime("O email informado já está em uso por outro egresso.");
        }
    
        if (egresso.getLinkedin() != null && !egresso.getLinkedin().isEmpty()) {
            if (!egresso.getLinkedin().matches("^(https?:\\/\\/)?([\\w.-]+\\.)?linkedin\\.com\\/.*$")) {
                throw new RegraNegocioRunTime("O link do LinkedIn é inválido.");
            }
        }

        //verificar se o linkedin ja foi usado...
    
        if (egresso.getInstagram() != null && !egresso.getInstagram().isEmpty()) {
            if (!egresso.getInstagram().matches("^(https?:\\/\\/)?(www\\.)?instagram\\.com\\/.*$")) {
                throw new RegraNegocioRunTime("O link do Instagram é inválido.");
            }
        }

        //verificar se o instagram ja foi usado...
        
        /*fim de verificação */
    }


    public void remover(Cargo cargo){
        buscarCargoPorId(cargo.getId_cargo());
        cargoRepositorio.deleteById(cargo.getId_cargo());
    }

    public void remover(Egresso egresso){
       Egresso egressoExistente = buscarEgressoPorId(egresso.getId_egresso());
    
    // Remover todos os cargos vinculados ao egresso
        List<Cargo> cargos = cargoRepositorio.findByEgresso(egressoExistente);
        for (Cargo cargo : cargos) {
            cargoRepositorio.delete(cargo);
        }

        // Remover todos os depoimentos vinculados ao egresso
        List<Depoimento> depoimentos = depoimentoRepositorio.findByEgresso(egressoExistente);
        for (Depoimento depoimento : depoimentos) {
            depoimentoRepositorio.delete(depoimento);
        }

        // Remover todos os cursos (na tabela cursos egressos) vinculados ao egresso
        cursoEgressoRepositorio.deleteById(egressoExistente.getId_egresso());

        egressoRepositorio.deleteById(egresso.getId_egresso());
    }

    
    public void remover(Depoimento depoimento){
        buscarDepoimentoPorId(depoimento.getId_depoimento());
        depoimentoRepositorio.deleteById(depoimento.getId_depoimento());
    }

    /* buscar */

    public Egresso buscarEgressoPorId(Integer id) {
        return egressoRepositorio.findById(id)
            .orElseThrow(() -> new RegraNegocioRunTime("Egresso não encontrado com o ID: " + id));
    }

    public Cargo buscarCargoPorId(Integer id) {
        return cargoRepositorio.findById(id)
            .orElseThrow(() -> new RegraNegocioRunTime("Cargo não encontrado com o ID: " + id));
    }

    public Depoimento buscarDepoimentoPorId(Integer id) {
        return depoimentoRepositorio.findById(id)
            .orElseThrow(() -> new RegraNegocioRunTime("Depoimento não encontrado com o ID: " + id));
    }


}
