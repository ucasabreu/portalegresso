package com.example.portalegresso.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.portalegresso.dto.CoordenadorDTO;
import com.example.portalegresso.model.entidades.Coordenador;
import com.example.portalegresso.service.CoordenadorService;
import com.fasterxml.jackson.databind.ObjectMapper;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(CoordenadorController.class)
public class CoordenadorControllerTest {

    static final String API = "/api/coordenadores";
    static final String SALVAR_COORDENADOR = API + "/salvar/coordenador";
    static final String AUTENTICAR_COORDENADOR = API + "/autenticar/coordenador";
    static final String SALVAR_CURSO = API + "/salvar/curso";
    static final String DELETAR_COORDENADOR = API + "/deletar/coordenador";

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private CoordenadorService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void deveSalvarCoordenador() throws Exception {
        // cenario
        CoordenadorDTO dto = CoordenadorDTO.builder().login("login").senha("senha").tipo("tipo").build();
        Coordenador coordenador = Coordenador.builder().login("login").senha("senha").tipo("tipo").build();

        // mock salvar
        Mockito.when(service.salvar(Mockito.any(Coordenador.class))).thenReturn(coordenador);

        // converte DTO para json
        String json = new ObjectMapper().writeValueAsString(dto);

        // execucao
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(SALVAR_COORDENADOR)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        // acao e verificacao
        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.login").value(dto.getLogin()))
                .andExpect(jsonPath("$.senha").value(dto.getSenha()))
                .andExpect(jsonPath("$.tipo").value(dto.getTipo()));
    }
}