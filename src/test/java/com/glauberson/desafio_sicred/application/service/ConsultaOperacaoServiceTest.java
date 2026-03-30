package com.glauberson.desafio_sicred.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.glauberson.desafio_sicred.domain.model.OperacaoCredito;
import com.glauberson.desafio_sicred.dto.OperacaoResponse;
import com.glauberson.desafio_sicred.exception.BusinessException;
import com.glauberson.desafio_sicred.infrastructure.repository.OperacaoRepository;

@ExtendWith(MockitoExtension.class)
class ConsultaOperacaoServiceTest {

    @Mock
    private OperacaoRepository repository;

    @InjectMocks
    private ConsultaOperacaoService service;

    @Test
    void deveBuscarOperacaoComSucesso() {
        var operacao = new OperacaoCredito();
        ReflectionTestUtils.setField(operacao, "id", 1L);

        when(repository.findById(1L)).thenReturn(Optional.of(operacao));

        OperacaoResponse response = service.buscar(1L);

        assertEquals(1L, response.id());
    }

    @Test
    void deveLancarExcecaoQuandoNaoEncontrar() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BusinessException.class, () -> service.buscar(1L));
    }
}
