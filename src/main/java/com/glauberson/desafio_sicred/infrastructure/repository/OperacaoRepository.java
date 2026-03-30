package com.glauberson.desafio_sicred.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glauberson.desafio_sicred.domain.model.OperacaoCredito;

public interface OperacaoRepository extends JpaRepository<OperacaoCredito, Long> {
}