package com.glauberson.desafio_sicred.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OperacaoResponse(
	    Long id,
	    Long idAssociado,
	    BigDecimal valorOperacao,
	    String segmento,
	    String codigoProdutoCredito,
	    String codigoConta,
	    BigDecimal areaBeneficiadaHa,
	    LocalDateTime dataContratacao
	) {}
