package com.example.portalegresso.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CursoDTO {
    private String nome;
    private String nivel;
    private Integer id_coordenador;
}
