package com.glauberson.desafio_sicred.infraestructure.gateway;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.glauberson.desafio_sicred.dto.ProdutoResponse;
import com.glauberson.desafio_sicred.infrastructure.client.ProdutoClient;
import com.glauberson.desafio_sicred.infrastructure.gateway.ProdutoGatewayImpl;

@ExtendWith(MockitoExtension.class)
class ProdutoGatewayImplTest {

    @Mock
    private ProdutoClient client;

    @InjectMocks
    private ProdutoGatewayImpl gateway;

    @Test
    void deveRetornarTrueQuandoPermite() {
        when(client.permiteContratacao(any(), any(), any()))
                .thenReturn(new ProdutoResponse(true));

        assertTrue(gateway.permiteContratacao("101A", "PF", BigDecimal.TEN));
    }

    @Test
    void deveRetornarFalseQuandoNaoPermite() {
        when(client.permiteContratacao(any(), any(), any()))
                .thenReturn(new ProdutoResponse(false));

        assertFalse(gateway.permiteContratacao("101A", "PF", BigDecimal.TEN));
    }
}