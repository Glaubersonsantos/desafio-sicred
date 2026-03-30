package com.glauberson.desafio_sicred.application.validator;

import org.springframework.stereotype.Component;

import com.glauberson.desafio_sicred.dto.ContratacaoRequest;
import com.glauberson.desafio_sicred.exception.BusinessException;
import com.glauberson.desafio_sicred.infrastructure.gateway.ProdutoGateway;

@Component
public class ProdutoValidator implements ContratacaoValidator {

    private final ProdutoGateway gateway;

    public ProdutoValidator(ProdutoGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void validar(ContratacaoRequest request) {
        boolean permitido = gateway.permiteContratacao(
                request.codigoProdutoCredito(),
                request.segmento(),
                request.valorOperacao()
        );

        if (!permitido) {
            throw new BusinessException("Produto não permite contratação");
        }
    }
}
