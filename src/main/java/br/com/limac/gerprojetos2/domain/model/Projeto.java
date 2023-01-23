package br.com.limac.gerprojetos2.domain.model;

import br.com.limac.gerprojetos2.domain.exception.NegocioException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "TB_PROJETO")
@SequenceGenerator(name = "projeto_seq", sequenceName = "projeto_seq", initialValue = 1, allocationSize = 1)
public class Projeto {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projeto_seq")
    private Long id;

    private String codigo;

    @NotBlank
    @Column(nullable = false, length = 80)
    private String nome;

    @NotBlank
    @Column(nullable = false, length = 250)
    private String descricao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_inicio", nullable = false)
    private OffsetDateTime dataInicioProj;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_fim", nullable = false)
    private OffsetDateTime dataFinalProj;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_previsao_termino", nullable = false)
    private OffsetDateTime dataPrevisaoTerminoProj;

    @NotNull
    @Column(nullable = false)
    private BigDecimal valorTotalProjeto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private EStatusProjeto statusProjeto = EStatusProjeto.PENDENTE;

    @Column(nullable = false, length = 250)
    private String observacao;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Conta conta;

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL)
    private List<ProjetoLancamento> lancamentos = new ArrayList<>();

    @Column(columnDefinition = "boolean default true")
    private boolean estaAtivo;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dataCriacao;

    private OffsetDateTime dataAtualizacao;

    @Version
    @Column(name = "opt_lock")
    private Long version;


    public void Iniciar() {
        setStatus(EStatusProjeto.INICIADO);
    }

    public void Execucao() {
        setStatus(EStatusProjeto.EXECUCAO);
    }

    public void Finalizar() {
        setStatus(EStatusProjeto.FINALIZADO);
    }


    private void setStatus(EStatusProjeto novoStatus) {
        if (getStatusProjeto().naoPodeAlterarPara(novoStatus)) {
            throw new NegocioException(
                    String.format("Status do pedido %s não pode ser alterado de %s para %s",
                            getId(), getStatusProjeto().getDescricao(),
                            novoStatus.getDescricao()));
        }

        this.statusProjeto = novoStatus;
    }

    @PrePersist
    private void gerarCodigo() {
        setCodigo(UUID.randomUUID().toString());
    }
}
