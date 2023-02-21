package br.com.limac.gerprojetos2.api.v1.assembler;

import br.com.limac.gerprojetos2.api.v1.model.ClienteModel;
import br.com.limac.gerprojetos2.domain.model.Cliente;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ClienteModelAssembler {
    private ModelMapper modelMapper;

    public ClienteModel toModel(Cliente cliente) {
        ClienteModel clienteModel = new ClienteModel();

        modelMapper.map(cliente, clienteModel);


        return clienteModel;
    }

    public List<ClienteModel> toModelList(List<Cliente> clientes) {
        return clientes
                .stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
