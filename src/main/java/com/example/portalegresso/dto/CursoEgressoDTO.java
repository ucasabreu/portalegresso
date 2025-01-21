package com.example.portalegresso.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CursoEgressoDTO {
    private Integer id_egresso;
    private Integer id_curso;
    private int ano_inicio;
    private int ano_fim;
}
