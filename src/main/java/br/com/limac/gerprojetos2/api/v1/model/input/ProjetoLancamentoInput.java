package br.com.limac.gerprojetos2.api.v1.model.input;

import br.com.limac.gerprojetos2.domain.model.ETipoProjetoLancamento;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProjetoLancamentoInput {
    @NotBlank
    private String descricao;
    @NotNull
    private ETipoProjetoLancamento tipoLancamento;
    @NotNull
    private BigDecimal valor;

    private boolean pago = false;

}
