package com.example.portalegresso.model.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name="cargo")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Cargo {
    @Column(name="id_cargo")
    private int id_cargo;

    @Column(name="descricao")
    private String descricao;

    @Column(name="local")
    private String local;

    @Column(name="ano_inicio")
    private int ano_inicio;
    
    @Column(name="ano_fim")
    private int ano_fim;

    @ManyToOne
    @JoinColumn(name="id_egresso")
    private Egresso egresso;

    
}
