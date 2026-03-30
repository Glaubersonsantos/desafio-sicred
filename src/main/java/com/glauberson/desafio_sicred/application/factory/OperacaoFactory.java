package com.glauberson.desafio_sicred.application.factory;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.glauberson.desafio_sicred.domain.model.OperacaoCredito;
import com.glauberson.desafio_sicred.dto.ContratacaoRequest;

@Component
public class OperacaoFactory {

    public OperacaoCredito criar(ContratacaoRequest request) {
        return new OperacaoCredito(
            request.idAssociado(),
            request.valorOperacao(),
            request.segmento(),
            request.codigoProdutoCredito(),
            request.codigoConta(),
            request.areaBeneficiadaHa(),
            LocalDateTime.now()
        );
    }
}
