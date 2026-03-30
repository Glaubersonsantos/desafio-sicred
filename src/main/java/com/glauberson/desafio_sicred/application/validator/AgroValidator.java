package com.glauberson.desafio_sicred.application.validator;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.glauberson.desafio_sicred.dto.ContratacaoRequest;
import com.glauberson.desafio_sicred.exception.BusinessException;

@Component
public class AgroValidator implements ContratacaoValidator {

    @Override
    public void validar(ContratacaoRequest request) {
        if ("AGRO".equals(request.segmento())) {
            if (request.areaBeneficiadaHa() == null ||
                request.areaBeneficiadaHa().compareTo(BigDecimal.ZERO) <= 0) {
                throw new BusinessException("Área obrigatória para AGRO");
            }
        }
    }
}
