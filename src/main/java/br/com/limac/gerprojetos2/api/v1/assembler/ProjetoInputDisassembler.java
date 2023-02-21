package br.com.limac.gerprojetos2.api.v1.assembler;

import br.com.limac.gerprojetos2.api.v1.model.input.ProjetoInput;
import br.com.limac.gerprojetos2.domain.model.Banco;
import br.com.limac.gerprojetos2.domain.model.Conta;
import br.com.limac.gerprojetos2.domain.model.Projeto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProjetoInputDisassembler {
    private ModelMapper modelMapper;

    public Projeto toDomainObject(ProjetoInput projetoInput) {
        return modelMapper.map(projetoInput, Projeto.class);
    }

    public void copyToDomainObject(ProjetoInput projetoInput, Projeto projeto) {
        Conta conta = new Conta();
        conta.setBanco(new Banco());
        projeto.setConta(new Conta());

        modelMapper.map(projetoInput, projeto);
    }
}
