package br.com.limac.gerprojetos2.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "TB_PROJETO_LANCAMENTO")
@SequenceGenerator(name = "projeto_lancamento_seq", sequenceName = "projeto_lancamento_seq", initialValue = 1, allocationSize = 1)
public class ProjetoLancamento {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projeto_lancamento_seq")
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 180)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private ETipoProjetoLancamento tipoLancamento;

    @NotNull
    @Column(nullable = false)
    private BigDecimal valor;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private boolean pago;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Projeto projeto;

    @OneToOne
    @JoinColumn
    private Caixa caixa;

    @Column(nullable = false, updatable = false)
    private OffsetDateTime dataCriacao;

    private OffsetDateTime dataAtualizacao;


    @PrePersist
    public void onInsert() {
        this.dataCriacao = OffsetDateTime.now();
        this.dataAtualizacao = this.dataCriacao;
    }

    @PreUpdate
    public void onUpdate() {
        this.dataAtualizacao = OffsetDateTime.now();
    }
}
