package br.com.limac.gerprojetos2.api.v1.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BancoIdInput {
    @NotNull
    private Long id;
}
