package br.com.limac.gerprojetos2.api.v1.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Setter
@Getter
public class ContaApenasIdENomeEAgenciaEConta {
    private Long id;

    private String agencia;

    private Long conta;

    private String bancoNome;
}
