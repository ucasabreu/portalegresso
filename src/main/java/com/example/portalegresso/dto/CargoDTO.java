package com.example.portalegresso.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CargoDTO {
    private String descricao;
    private String local;
    private int ano_inicio;
    private int ano_fim;
    private Integer id_egresso;
}
