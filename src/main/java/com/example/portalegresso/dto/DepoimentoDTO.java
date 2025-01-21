package com.example.portalegresso.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepoimentoDTO {
   private Integer id_egresso;
   private String texto;
   private LocalDate date;
   
}   
