package br.com.limac.gerprojetos2.api.v1.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjetoLancamentoIdInput {
    @NotNull
    private Long id;
}
