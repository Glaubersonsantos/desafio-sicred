package com.glauberson.desafio_sicred.application.service;

import org.springframework.stereotype.Service;

import com.glauberson.desafio_sicred.application.factory.OperacaoFactory;
import com.glauberson.desafio_sicred.application.validator.ContratacaoValidatorChain;
import com.glauberson.desafio_sicred.domain.model.OperacaoCredito;
import com.glauberson.desafio_sicred.domain.model.OperacaoCreditoPJ;
import com.glauberson.desafio_sicred.dto.ContratacaoRequest;
import com.glauberson.desafio_sicred.infrastructure.repository.OperacaoPJRepository;
import com.glauberson.desafio_sicred.infrastructure.repository.OperacaoRepository;

import jakarta.transaction.Transactional;

@Service
public class ContratacaoCreditoService {
	private final OperacaoRepository operacaoRepository;
    private final OperacaoPJRepository pjRepository;
    private final ContratacaoValidatorChain validatorChain;
    private final OperacaoFactory operacaoFactory;

    public ContratacaoCreditoService(
            OperacaoRepository operacaoRepository,
            OperacaoPJRepository pjRepository,
            ContratacaoValidatorChain validatorChain,
            OperacaoFactory operacaoFactory) {

        this.operacaoRepository = operacaoRepository;
        this.pjRepository = pjRepository;
        this.validatorChain = validatorChain;
        this.operacaoFactory = operacaoFactory;
    }

    @Transactional
    public Long contratar(ContratacaoRequest request) {

        validatorChain.executar(request);

        OperacaoCredito operacao = operacaoFactory.criar(request);
        operacao = operacaoRepository.save(operacao);

        if ("PJ".equals(request.segmento())) {
            OperacaoCreditoPJ operacaoPJ = pjRepository.save(new OperacaoCreditoPJ(operacao));
        }

        return operacao.getId();
    }
}
