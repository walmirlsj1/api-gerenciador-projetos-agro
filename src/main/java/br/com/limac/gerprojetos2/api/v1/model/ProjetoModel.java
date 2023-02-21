package br.com.limac.gerprojetos2.api.v1.model;

import br.com.limac.gerprojetos2.domain.model.EStatusProjeto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;


@Setter
@Getter
public class ProjetoModel {
    private Long id;

    private String codigo;

    private String nome;
    private String descricao;

    private OffsetDateTime dataInicioProj;

    private OffsetDateTime dataFinalProj;

    private OffsetDateTime dataPrevisaoTerminoProj;

    private BigDecimal valorTotalProjeto;

    private EStatusProjeto statusProjeto;

    private String observacao;

    private ClienteApenasIdENome cliente;

    private ContaApenasIdENomeEAgenciaEConta conta;

    private List<ProjetoLancamentoModel> lancamentos;

    private boolean estaAtivo;

    private OffsetDateTime dataCriacao;

    private OffsetDateTime dataAtualizacao;
}
