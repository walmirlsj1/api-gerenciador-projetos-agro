package br.com.limac.gerprojetos2.api.v1.assembler;

import br.com.limac.gerprojetos2.api.v1.model.input.ContaInput;
import br.com.limac.gerprojetos2.domain.model.Banco;
import br.com.limac.gerprojetos2.domain.model.Conta;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ContaInputDisassembler {
    private ModelMapper modelMapper;

    public Conta toDomainObject(ContaInput contaInput) {
        return modelMapper.map(contaInput, Conta.class);
    }

        public void copyToDomainObject(ContaInput contaInput, Conta conta) {
            conta.setBanco(new Banco());
            modelMapper.map(contaInput, conta);
        }
}
