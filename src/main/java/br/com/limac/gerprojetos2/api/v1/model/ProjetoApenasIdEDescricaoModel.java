package br.com.limac.gerprojetos2.api.v1.model;

import br.com.limac.gerprojetos2.domain.model.EStatusProjeto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;


@Setter
@Getter
public class ProjetoApenasIdEDescricaoModel {
    private Long id;
    private String descricao;

}
