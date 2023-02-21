package br.com.limac.gerprojetos2.api.v1.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class ProjetoInput {
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;

    @NotNull
    private OffsetDateTime dataInicioProj;

    @NotNull
    private OffsetDateTime dataPrevisaoTerminoProj;

    @NotNull
    private BigDecimal valorTotalProjeto;

    private String observacao;


    @Valid
    @NotNull
    private ContaIdInput conta;

    @Valid
    @NotNull
    private ClienteIdInput cliente;

}
