package com.example.portalegresso.model.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="curso_egresso")//*verificar se deve ser o nome da tabela do banco == o nome da classe */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CursoEgresso {
    @Id
    @Column(name="id_curso_egresso")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_curso_egresso;

    @Column(name="id_egresso")
    private int id_egresso;

    @Column(name="id_curso")
    private int id_curso;

    @Column(name="ano_inicio")
    private int ano_inicio;

    @Column(name="ano_fim")
    private int ano_fim;



}