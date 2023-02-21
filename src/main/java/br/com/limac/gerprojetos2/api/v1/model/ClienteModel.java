package br.com.limac.gerprojetos2.api.v1.model;

import br.com.limac.gerprojetos2.domain.model.ETipoPessoa;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ClienteModel {


    private Long id;
    private String nome;
    private String fantasia;
    private String cpfCnpj;
    private String rgIe;
    private String email;
    private String telefone;
    private String celular;
    @Enumerated(EnumType.STRING)
    private ETipoPessoa tipoEmpresa;
    private List<ProjetoModel> projetos;
    private boolean estaAtivo;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataAtualizacao;


}
