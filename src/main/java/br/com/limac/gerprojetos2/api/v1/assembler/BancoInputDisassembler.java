package br.com.limac.gerprojetos2.api.v1.assembler;

import br.com.limac.gerprojetos2.api.v1.model.input.BancoInput;
import br.com.limac.gerprojetos2.domain.model.Banco;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BancoInputDisassembler {
    private ModelMapper modelMapper;

    public Banco toDomainObject(BancoInput bancoInput) {
        return modelMapper.map(bancoInput, Banco.class);
    }

        public void copyToDomainObject(BancoInput bancoInput, Banco banco) {
            modelMapper.map(bancoInput, banco);
        }
}
