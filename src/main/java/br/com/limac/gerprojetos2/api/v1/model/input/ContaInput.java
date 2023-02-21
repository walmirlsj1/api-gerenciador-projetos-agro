package br.com.limac.gerprojetos2.api.v1.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ContaInput {

    @NotNull
    private String agencia;


    @NotNull
    private Long conta;

    @NotNull
    private BigDecimal saldo;

    @Valid
    @NotNull
    private BancoIdInput banco;

    private boolean estaAtivo;
}
