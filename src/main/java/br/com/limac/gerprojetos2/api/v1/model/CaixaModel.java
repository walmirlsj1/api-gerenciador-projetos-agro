package br.com.limac.gerprojetos2.api.v1.model;

import br.com.limac.gerprojetos2.domain.model.ETipoCaixaLancamento;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Setter @Getter
public class CaixaModel {

    private String descricao;

    private BigDecimal valor;

    private ETipoCaixaLancamento tipoCaixaLancamento;

    private OffsetDateTime data;

    private ContaApenasIdENomeEAgenciaEConta conta;
}
