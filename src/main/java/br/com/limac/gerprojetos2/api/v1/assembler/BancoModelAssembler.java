package br.com.limac.gerprojetos2.api.v1.assembler;

import br.com.limac.gerprojetos2.api.v1.model.BancoModel;
import br.com.limac.gerprojetos2.domain.model.Banco;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class BancoModelAssembler {
    private ModelMapper modelMapper;

    public BancoModel toModel(Banco banco) {
        BancoModel bancoModel = new BancoModel();

        modelMapper.map(banco, bancoModel);

        return bancoModel;
    }

    public List<BancoModel> toModelList(List<Banco> bancos) {
        return bancos
                .stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
