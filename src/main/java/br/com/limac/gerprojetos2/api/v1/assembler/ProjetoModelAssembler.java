package br.com.limac.gerprojetos2.api.v1.assembler;

import br.com.limac.gerprojetos2.api.v1.model.ProjetoModel;
import br.com.limac.gerprojetos2.domain.model.Projeto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProjetoModelAssembler {
    private ModelMapper modelMapper;

    public ProjetoModel toModel(Projeto projeto) {
        ProjetoModel projetoModel = new ProjetoModel();

//        projetoModel.setConta(new ContaModel());

        modelMapper.map(projeto, projetoModel);


        return projetoModel;
    }

    public List<ProjetoModel> toModelList(List<Projeto> projetos) {
        return projetos
                .stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }


    public Page<ProjetoModel> toPageModel(Page<Projeto> projetos) {
        return new PageImpl<>(this.toModelList(projetos.getContent()), projetos.getPageable(), projetos.getTotalElements());
    }
}
