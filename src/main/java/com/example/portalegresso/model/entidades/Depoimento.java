package com.example.portalegresso.model.entidades;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="depoimento")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Depoimento {
    @Id
    @Column(name="id_depoimento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_depoimento;

    @ManyToOne
    @JoinColumn(name="id_egresso")
    private Egresso egresso;

    @Column(name="texto")
    private String texto;
    
    @Temporal(TemporalType.DATE)    
    @Column(name="data")
    private Date date;
}
