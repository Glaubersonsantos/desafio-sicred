package com.glauberson.desafio_sicred.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.glauberson.desafio_sicred.application.service.ConsultaOperacaoService;
import com.glauberson.desafio_sicred.application.service.ContratacaoCreditoService;
import com.glauberson.desafio_sicred.dto.OperacaoResponse;

@WebMvcTest(OperacaoController.class)
class OperacaoControllerTest {

	@Autowired
    private MockMvc mockMvc;

	@MockBean
    private ContratacaoCreditoService contratacaoService;

	@MockBean
    private ConsultaOperacaoService consultaService;

    @Test
    void deveContratar() throws Exception {
        when(contratacaoService.contratar(any())).thenReturn(1L);

        mockMvc.perform(post("/api/v1/operacoes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                      "idAssociado": 1,
                      "valorOperacao": 1000,
                      "segmento": "PF",
                      "codigoProdutoCredito": "101A",
                      "codigoConta": "123"
                    }
                """))
                .andExpect(status().isCreated());
    }

    @Test
    void deveBuscar() throws Exception {
        var response = new OperacaoResponse(1L, 1L, BigDecimal.TEN, "PF", "101A", "123", null, null);

        when(consultaService.buscar(1L)).thenReturn(response);

        mockMvc.perform(get("/api/v1/operacoes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
}