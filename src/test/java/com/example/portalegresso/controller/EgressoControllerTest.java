package com.example.portalegresso.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.portalegresso.dto.EgressoDTO;
import com.example.portalegresso.model.entidades.Egresso;
import com.example.portalegresso.service.EgressoService;
import com.example.portalegresso.service.RegraNegocioRunTime;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(EgressoController.class)
@AutoConfigureMockMvc
public class EgressoControllerTest {

    static final String API = "/api/egressos";
    static final String SALVAR_EGRESSO = API + "/salvar/egresso";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @InjectMocks
    private EgressoService egressoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveSalvarEgresso() throws Exception {
        // cenario
        EgressoDTO dto = EgressoDTO.builder()
                .nome("Lucas")
                .email("lucas90@gmail.com")
                .descricao("novadescricao")
                .foto("novafoto")
                .linkedin("https://www.linkedin.com/in/lucas")
                .instagram("https://www.instagram.com/lucas")
                .curriculo("meucurriculo")
                .build();
        Egresso egresso = Egresso.builder()
                .nome("Lucas")
                .email("lucas90@gmail.com")
                .descricao("novadescricao")
                .foto("novafoto")
                .linkedin("https://www.linkedin.com/in/lucas")
                .instagram("https://www.instagram.com/lucas")
                .curriculo("meucurriculo")
                .build();

        // mock salvar
        Mockito.when(egressoService.salvar(Mockito.any(Egresso.class))).thenReturn(egresso);

        // converte DTO para json
        String json = new ObjectMapper().writeValueAsString(dto);

        // execucao
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(SALVAR_EGRESSO)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        // acao e verificacao
        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value(dto.getNome()))
                .andExpect(jsonPath("$.email").value(dto.getEmail()))
                .andExpect(jsonPath("$.descricao").value(dto.getDescricao()))
                .andExpect(jsonPath("$.foto").value(dto.getFoto()))
                .andExpect(jsonPath("$.linkedin").value(dto.getLinkedin()))
                .andExpect(jsonPath("$.instagram").value(dto.getInstagram()))
                .andExpect(jsonPath("$.curriculo").value(dto.getCurriculo()));
    }

    @Test
    public void deveRetornarErroAoSalvarEgresso() throws Exception {
        // cenario
        EgressoDTO dto = EgressoDTO.builder()
                .nome("Lucas")
                .email("lucas90@gmail.com")
                .descricao("novadescricao")
                .foto("novafoto")
                .linkedin("https://www.linkedin.com/in/lucas")
                .instagram("https://www.instagram.com/lucas")
                .curriculo("meucurriculo")
                .build();

        // mock salvar com exceção
        Mockito.when(egressoService.salvar(Mockito.any(Egresso.class)))
                .thenThrow(new RegraNegocioRunTime("Erro ao salvar egresso"));

        // converte DTO para json
        String json = new ObjectMapper().writeValueAsString(dto);

        // execucao
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(SALVAR_EGRESSO)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        // acao e verificacao
        mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Erro ao salvar egresso"));
    }
}
