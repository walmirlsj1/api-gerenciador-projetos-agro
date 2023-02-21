package br.com.limac.gerprojetos2.api.v1.assembler;

import br.com.limac.gerprojetos2.api.v1.model.BancoModel;
import br.com.limac.gerprojetos2.api.v1.model.ContaModel;
import br.com.limac.gerprojetos2.domain.model.Banco;
import br.com.limac.gerprojetos2.domain.model.Conta;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ContaModelAssembler {
    private ModelMapper modelMapper;

    public ContaModel toModel(Conta conta) {
        ContaModel contaModel = new ContaModel();

        modelMapper.map(conta, contaModel);

        return contaModel;
    }

    public List<ContaModel> toModelList(List<Conta> contas) {
        return contas
                .stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
