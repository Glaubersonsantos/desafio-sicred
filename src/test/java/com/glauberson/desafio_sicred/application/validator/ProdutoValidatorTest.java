package com.glauberson.desafio_sicred.application.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.glauberson.desafio_sicred.dto.ContratacaoRequest;
import com.glauberson.desafio_sicred.exception.BusinessException;
import com.glauberson.desafio_sicred.infrastructure.gateway.ProdutoGateway;

@ExtendWith(MockitoExtension.class)
class ProdutoValidatorTest {

    @Mock
    private ProdutoGateway gateway;

    @InjectMocks
    private ProdutoValidator validator;

    @Test
    void devePermitirContratacao() {
        when(gateway.permiteContratacao(any(), any(), any())).thenReturn(true);

        var request = new ContratacaoRequest(1L, BigDecimal.TEN, "PF", "101A", "123", null);

        assertDoesNotThrow(() -> validator.validar(request));
    }

    @Test
    void deveNegarContratacao() {
        when(gateway.permiteContratacao(any(), any(), any())).thenReturn(false);

        var request = new ContratacaoRequest(1L, BigDecimal.TEN, "PF", "101A", "123", null);

        assertThrows(BusinessException.class, () -> validator.validar(request));
    }
}
