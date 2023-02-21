package br.com.limac.gerprojetos2.api.v1.model.input;

import br.com.limac.gerprojetos2.domain.model.ETipoCaixaLancamento;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter @Setter
public class CaixaInput {
    @NotBlank
    private String descricao;
    @NotNull
    private BigDecimal valor;
//    @Enumerated(EnumType.STRING)
//    @NotNull
//    private ETipoCaixaLancamento tipoCaixaLancamento;

}
