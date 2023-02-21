package br.com.limac.gerprojetos2.api.v1.assembler;

import br.com.limac.gerprojetos2.api.v1.model.input.CaixaInput;
import br.com.limac.gerprojetos2.domain.model.Caixa;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CaixaInputDisassembler {
    private ModelMapper modelMapper;

    public Caixa toDomainObject(CaixaInput caixaInput) {
        return modelMapper.map(caixaInput, Caixa.class);
    }

    public void copyToDomainObject(CaixaInput caixaInput, Caixa caixa) {
        modelMapper.map(caixaInput, caixa);
    }
}
