package com.glauberson.desafio_sicred.infrastructure.gateway;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.glauberson.desafio_sicred.infrastructure.client.ProdutoClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Component
public class ProdutoGatewayImpl implements ProdutoGateway {

    private final ProdutoClient client;

    public ProdutoGatewayImpl(ProdutoClient client) {
        this.client = client;
    }

    @Override
    @CircuitBreaker(name = "produtoService", fallbackMethod = "fallbackPermiteContratacao")
    public boolean permiteContratacao(String codigoProduto, String segmento, BigDecimal valor) {

        return client.permiteContratacao(codigoProduto, segmento, valor)
                .permiteContratar();
    }

    public boolean fallbackPermiteContratacao(
            String codigoProduto,
            String segmento,
            BigDecimal valor,
            Throwable ex) {
    	
        return false;
    }
}
