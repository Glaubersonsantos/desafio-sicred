package com.glauberson.desafio_sicred.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "operacao_credito_pj")
public class OperacaoCreditoPJ {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@OneToOne(optional = false)
    @JoinColumn(name = "operacao_credito_id", nullable = false, unique = true)
    private OperacaoCredito operacaoCredito;

    public OperacaoCreditoPJ() {}

    public OperacaoCreditoPJ(OperacaoCredito operacaoCredito) {
        this.operacaoCredito = operacaoCredito;
    }
}