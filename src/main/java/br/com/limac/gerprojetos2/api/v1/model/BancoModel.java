package br.com.limac.gerprojetos2.api.v1.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Setter
@Getter
public class BancoModel {
    private Long id;
    private String nome;
//    private List<ContaModel> contas;
    private boolean estaAtivo;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataAtualizacao;
}
