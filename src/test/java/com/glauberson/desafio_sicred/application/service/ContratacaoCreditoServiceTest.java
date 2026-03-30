package com.glauberson.desafio_sicred.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.glauberson.desafio_sicred.application.factory.OperacaoFactory;
import com.glauberson.desafio_sicred.application.validator.ContratacaoValidatorChain;
import com.glauberson.desafio_sicred.domain.model.OperacaoCredito;
import com.glauberson.desafio_sicred.dto.ContratacaoRequest;
import com.glauberson.desafio_sicred.infrastructure.repository.OperacaoPJRepository;
import com.glauberson.desafio_sicred.infrastructure.repository.OperacaoRepository;

@ExtendWith(MockitoExtension.class)
class ContratacaoCreditoServiceTest {

    @Mock
    private OperacaoRepository repository;

    @Mock
    private OperacaoPJRepository pjRepository;

    @Mock
    private ContratacaoValidatorChain validatorChain;

    @Mock
    private OperacaoFactory factory;

    @InjectMocks
    private ContratacaoCreditoService service;

    @Test
    void deveContratarOperacaoComSucesso() {
        var request = new ContratacaoRequest(1L, BigDecimal.TEN, "PF", "101A", "123", null);

        var operacao = new OperacaoCredito();
        ReflectionTestUtils.setField(operacao, "id", 1L);

        when(factory.criar(request)).thenReturn(operacao);
        when(repository.save(any())).thenReturn(operacao);

        Long id = service.contratar(request);

        assertEquals(1L, id);
        verify(validatorChain).executar(request);
        verify(repository).save(any());
    }

    @Test
    void deveSalvarPJQuandoSegmentoForPJ() {
        var request = new ContratacaoRequest(1L, BigDecimal.TEN, "PJ", "202B", "123", null);

        var operacao = new OperacaoCredito();
        ReflectionTestUtils.setField(operacao, "id", 1L);

        when(factory.criar(request)).thenReturn(operacao);
        when(repository.save(any())).thenReturn(operacao);

        service.contratar(request);

        verify(pjRepository).save(any());
    }
}