package com.glauberson.desafio_sicred.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "operacao_credito")
public class OperacaoCredito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="id_associado")
    private Long idAssociado;
    
    @Column(name="valor_operacao")
    private BigDecimal valorOperacao;
    
    private String segmento;
    
    @Column(name="codigo_produto_credito")
    private String codigoProdutoCredito;
    
    @Column(name="codigo_conta")
    private String codigoConta;
    
    @Column(name="area_beneficiada_ha")
    private BigDecimal areaBeneficiadaHa;
    
    @Column(name="data_contratacao")
    private LocalDateTime dataContratacao;
    
    @OneToOne(mappedBy = "operacaoCredito",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private OperacaoCreditoPJ operacaoCreditoPJ;

    public OperacaoCredito() {}

    public OperacaoCredito(Long idAssociado,
                           BigDecimal valorOperacao,
                           String segmento,
                           String codigoProdutoCredito,
                           String codigoConta,
                           BigDecimal areaBeneficiadaHa,
                           LocalDateTime dataContratacao) {
        this.idAssociado = idAssociado;
        this.valorOperacao = valorOperacao;
        this.segmento = segmento;
        this.codigoProdutoCredito = codigoProdutoCredito;
        this.codigoConta = codigoConta;
        this.areaBeneficiadaHa = areaBeneficiadaHa;
        this.dataContratacao = dataContratacao;
    }

    public Long getId() { return id; }
    public Long getIdAssociado() { return idAssociado; }
    public BigDecimal getValorOperacao() { return valorOperacao; }
    public String getSegmento() { return segmento; }
    public String getCodigoProdutoCredito() { return codigoProdutoCredito; }
    public String getCodigoConta() { return codigoConta; }
    public BigDecimal getAreaBeneficiadaHa() { return areaBeneficiadaHa; }
    public LocalDateTime getDataContratacao() { return dataContratacao; }
    public OperacaoCreditoPJ getOperacaoCreditoPJ() { return operacaoCreditoPJ; }
}
