package com.glauberson.desafio_sicred.dto;

import java.math.BigDecimal;

public record ContratacaoRequest(
	    Long idAssociado,
	    BigDecimal valorOperacao,
	    String segmento,
	    String codigoProdutoCredito,
	    String codigoConta,
	    BigDecimal areaBeneficiadaHa
	) {}
