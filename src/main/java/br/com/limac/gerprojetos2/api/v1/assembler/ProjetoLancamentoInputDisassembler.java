package br.com.limac.gerprojetos2.api.v1.assembler;

import br.com.limac.gerprojetos2.api.v1.model.input.ProjetoLancamentoInput;
import br.com.limac.gerprojetos2.domain.model.ProjetoLancamento;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProjetoLancamentoInputDisassembler {
    private ModelMapper modelMapper;

    public ProjetoLancamento toDomainObject(ProjetoLancamentoInput projetoLancamentoInput) {
        return modelMapper.map(projetoLancamentoInput, ProjetoLancamento.class);
    }

    public void copyToDomainObject(ProjetoLancamentoInput projetoLancamentoInput, ProjetoLancamento projetoLancamento) {

        modelMapper.map(projetoLancamentoInput, projetoLancamento);
    }
}
