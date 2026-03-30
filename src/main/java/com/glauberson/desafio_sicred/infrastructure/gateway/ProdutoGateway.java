package com.glauberson.desafio_sicred.infrastructure.gateway;

import java.math.BigDecimal;

public interface ProdutoGateway {
    boolean permiteContratacao(String codigo, String segmento, BigDecimal valor);
}
