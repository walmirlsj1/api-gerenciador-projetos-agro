package br.com.limac.gerprojetos2.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Date;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "TB_CAIXA")
@SequenceGenerator(name = "caixa_seq", sequenceName = "caixa_seq", initialValue = 1, allocationSize = 1)
public class Caixa {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "caixa_seq")
    private Long id;
    private OffsetDateTime data;
    private BigDecimal valor;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private ETipoCaixaLancamento tipoLancamento;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Conta conta;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dataCriacao;

    private OffsetDateTime dataAtualizacao;


    public void estornar() {
        if (tipoLancamento.equals(ETipoCaixaLancamento.ENTRADA))
            conta.saque(getValor());
        else if (tipoLancamento.equals(ETipoCaixaLancamento.SUPLIMENTO))
            conta.saque(getValor());
        else if (tipoLancamento.equals(ETipoCaixaLancamento.SAIDA))
            conta.depositar(getValor());
        else if (tipoLancamento.equals(ETipoCaixaLancamento.SANGRIA))
            conta.depositar(getValor());
    }

    public void registrar(){
        if (tipoLancamento.equals(ETipoCaixaLancamento.ENTRADA))
            conta.depositar(getValor());
        else if (tipoLancamento.equals(ETipoCaixaLancamento.SUPLIMENTO))
            conta.depositar(getValor());
        else if (tipoLancamento.equals(ETipoCaixaLancamento.SAIDA))
            conta.saque(getValor());
        else if (tipoLancamento.equals(ETipoCaixaLancamento.SANGRIA))
            conta.saque(getValor());
    }
}
