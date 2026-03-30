package com.glauberson.desafio_sicred.application.validator;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.glauberson.desafio_sicred.dto.ContratacaoRequest;

class ValidatorChainTest {

    @Test
    void deveExecutarTodosValidators() {
        ContratacaoValidator v1 = mock(ContratacaoValidator.class);
        ContratacaoValidator v2 = mock(ContratacaoValidator.class);

        var chain = new ContratacaoValidatorChain(List.of(v1, v2));

        var request = new ContratacaoRequest(1L, BigDecimal.TEN, "PF", "101A", "123", null);

        chain.executar(request);

        verify(v1).validar(request);
        verify(v2).validar(request);
    }
}