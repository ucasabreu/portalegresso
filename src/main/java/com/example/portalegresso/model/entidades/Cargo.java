package com.example.portalegresso.model.entidades;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @Id
    @Column(name="id_cargo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_cargo;

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
