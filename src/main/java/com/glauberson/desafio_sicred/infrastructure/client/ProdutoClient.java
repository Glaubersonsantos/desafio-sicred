package com.glauberson.desafio_sicred.infrastructure.client;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.glauberson.desafio_sicred.dto.ProdutoResponse;

@FeignClient(
	    name = "produtoClient",
	    url = "https://desafio-credito-sicredi.wiremockapi.cloud"
	)
	public interface ProdutoClient {

	    @GetMapping("/produtos-credito/{codigo}/permite-contratacao")
	    ProdutoResponse permiteContratacao(
	        @PathVariable String codigo,
	        @RequestParam String segmento,
	        @RequestParam BigDecimal valorFinanciado
	    );
	}
