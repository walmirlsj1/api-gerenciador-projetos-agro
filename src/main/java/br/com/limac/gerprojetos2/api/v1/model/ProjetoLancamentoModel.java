package br.com.limac.gerprojetos2.api.v1.model;

import br.com.limac.gerprojetos2.domain.model.ETipoProjetoLancamento;
import br.com.limac.gerprojetos2.domain.model.Projeto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Setter
@Getter
public class ProjetoLancamentoModel {
    private Long id;

    private String descricao;
    private ETipoProjetoLancamento tipoLancamento;
    private BigDecimal valor;
    private boolean pago;
    private ProjetoApenasIdEDescricaoModel projeto;
//    private CaixaModel caixa;

    private OffsetDateTime dataCriacao;

    private OffsetDateTime dataAtualizacao;
}
