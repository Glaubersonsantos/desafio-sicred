package com.glauberson.desafio_sicred.application.validator;

import java.util.List;

import org.springframework.stereotype.Component;

import com.glauberson.desafio_sicred.dto.ContratacaoRequest;

@Component
public class ContratacaoValidatorChain {

    private final List<ContratacaoValidator> validators;

    public ContratacaoValidatorChain(List<ContratacaoValidator> validators) {
        this.validators = validators;
    }

    public void executar(ContratacaoRequest request) {
        for (ContratacaoValidator validator : validators) {
            validator.validar(request);
        }
    }
}
