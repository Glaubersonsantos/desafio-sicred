package com.glauberson.desafio_sicred.application.service;

import org.springframework.stereotype.Service;

import com.glauberson.desafio_sicred.domain.model.OperacaoCredito;
import com.glauberson.desafio_sicred.dto.OperacaoResponse;
import com.glauberson.desafio_sicred.exception.BusinessException;
import com.glauberson.desafio_sicred.infrastructure.repository.OperacaoRepository;

@Service
public class ConsultaOperacaoService {

    private final OperacaoRepository operacaoRepository;

    public ConsultaOperacaoService(OperacaoRepository repository) {
        this.operacaoRepository = repository;
    }

    public OperacaoResponse buscar(Long id) {

        OperacaoCredito op = operacaoRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Operação não encontrada"));

        return new OperacaoResponse(
                op.getId(),
                op.getIdAssociado(),
                op.getValorOperacao(),
                op.getSegmento(),
                op.getCodigoProdutoCredito(),
                op.getCodigoConta(),
                op.getAreaBeneficiadaHa(),
                op.getDataContratacao()
        );
    }
}
