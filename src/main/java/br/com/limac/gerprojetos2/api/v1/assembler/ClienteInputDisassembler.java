package br.com.limac.gerprojetos2.api.v1.assembler;

import br.com.limac.gerprojetos2.api.v1.model.input.ClienteInput;
import br.com.limac.gerprojetos2.domain.model.Cliente;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ClienteInputDisassembler {
    private ModelMapper modelMapper;

    public Cliente toDomainObject(ClienteInput clienteInput) {
        return modelMapper.map(clienteInput, Cliente.class);
    }

        public void copyToDomainObject(ClienteInput clienteInput, Cliente cliente) {
            modelMapper.map(clienteInput, cliente);
        }
}
