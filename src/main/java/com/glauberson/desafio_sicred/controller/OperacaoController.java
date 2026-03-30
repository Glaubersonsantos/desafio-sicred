package com.glauberson.desafio_sicred.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glauberson.desafio_sicred.application.service.ConsultaOperacaoService;
import com.glauberson.desafio_sicred.application.service.ContratacaoCreditoService;
import com.glauberson.desafio_sicred.dto.ContratacaoRequest;
import com.glauberson.desafio_sicred.dto.OperacaoResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/operacoes")
@Tag(name = "Operações de Crédito", description = "API responsável pela contratação e consulta de operações de crédito")
public class OperacaoController {

    private final ContratacaoCreditoService contratacaoService;
    private final ConsultaOperacaoService consultaService;

    public OperacaoController(
            ContratacaoCreditoService contratacaoService,
            ConsultaOperacaoService consultaService) {
        this.contratacaoService = contratacaoService;
        this.consultaService = consultaService;
    }

    @Operation(
            summary = "Contratar operação de crédito",
            description = "Realiza a contratação de uma nova operação de crédito para um associado, validando regras de negócio e integração com o serviço de produtos."
    )
    @PostMapping
    public ResponseEntity<Long> contratar(
            @RequestBody ContratacaoRequest request) {

        Long id = contratacaoService.contratar(request);

        return ResponseEntity
                .status(201)
                .header("Location", "/api/v1/operacoes/" + id)
                .body(id);
    }

    @Operation(
            summary = "Buscar operação de crédito",
            description = "Consulta uma operação de crédito a partir do seu identificador único"
    )
    @GetMapping("/{id}")
    public ResponseEntity<OperacaoResponse> buscar(
            @PathVariable Long id) {

        OperacaoResponse response = consultaService.buscar(id);
        return ResponseEntity.ok(response);
    }
}
