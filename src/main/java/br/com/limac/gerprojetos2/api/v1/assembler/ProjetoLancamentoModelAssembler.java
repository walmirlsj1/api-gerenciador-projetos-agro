package br.com.limac.gerprojetos2.api.v1.assembler;

import br.com.limac.gerprojetos2.api.v1.model.CaixaModel;
import br.com.limac.gerprojetos2.api.v1.model.ProjetoLancamentoModel;
import br.com.limac.gerprojetos2.api.v1.model.ProjetoModel;
import br.com.limac.gerprojetos2.domain.model.Caixa;
import br.com.limac.gerprojetos2.domain.model.Projeto;
import br.com.limac.gerprojetos2.domain.model.ProjetoLancamento;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProjetoLancamentoModelAssembler {
    private ModelMapper modelMapper;

    public ProjetoLancamentoModel toModel(ProjetoLancamento projetoLancamento) {
        ProjetoLancamentoModel projetoLancamentoModel= new ProjetoLancamentoModel();

        modelMapper.map(projetoLancamento, projetoLancamentoModel);


        return projetoLancamentoModel;
    }

    public List<ProjetoLancamentoModel> toModelList(List<ProjetoLancamento> projetoLancamentos) {
        return projetoLancamentos
                .stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}
