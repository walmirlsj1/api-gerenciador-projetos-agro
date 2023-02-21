package br.com.limac.gerprojetos2.api.v1.model.input;

import br.com.limac.gerprojetos2.domain.model.ETipoPessoa;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClienteInput {
    @NotBlank
    private String nome;
    private String fantasia;

    @NotBlank
    private String cpfCnpj;

    private String rgIe;
    private String email;
    private String telefone;

    private String celular;
    @Enumerated(EnumType.STRING)
    private ETipoPessoa tipoEmpresa;

}
