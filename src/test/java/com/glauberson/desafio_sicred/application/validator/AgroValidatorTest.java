package com.glauberson.desafio_sicred.application.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.glauberson.desafio_sicred.dto.ContratacaoRequest;
import com.glauberson.desafio_sicred.exception.BusinessException;

class AgroValidatorTest {

    private final AgroValidator validator = new AgroValidator();

    @Test
    void devePassarQuandoValido() {
        var request = new ContratacaoRequest(1L, BigDecimal.TEN, "AGRO", "903C", "123", BigDecimal.ONE);

        assertDoesNotThrow(() -> validator.validar(request));
    }

    @Test
    void deveFalharQuandoAreaInvalida() {
        var request = new ContratacaoRequest(1L, BigDecimal.TEN, "AGRO", "903C", "123", BigDecimal.ZERO);

        assertThrows(BusinessException.class, () -> validator.validar(request));
    }
}