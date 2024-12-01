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
@Table(name="curso")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Curso {
    @Id
    @Column(name="id_curso")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_curso;

    @Column(name="nome")
    private String nome;

    @Column(name="nivel")
    private String nivel;
    

}
