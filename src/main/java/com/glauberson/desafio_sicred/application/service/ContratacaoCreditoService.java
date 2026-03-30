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
    	
    	/*
    	 	Executa a cadeia de validações (Strategy + Chain of Responsibility)
        	Aqui são aplicadas todas as regras de negócio necessárias antes da contratação,
        	como validação de segmento, produto e regras específicas (ex: AGRO e PJ) 
        */
        validatorChain.executar(request);
        
        
        /*
          	Cria a entidade de domínio a partir do request (Factory Pattern)
        	Centraliza a lógica de construção do objeto, evitando acoplamento no service
        */
        OperacaoCredito operacao = operacaoFactory.criar(request);
        operacao = operacaoRepository.save(operacao);
        
        
        
        /* 	
         	Regra específica para operações do tipo PJ
        	Caso o segmento seja PJ, deve ser criado um vínculo em tabela separada
        */
        if ("PJ".equals(request.segmento())) {
            OperacaoCreditoPJ operacaoPJ = pjRepository.save(new OperacaoCreditoPJ(operacao));
        }

        return operacao.getId();
    }
}
