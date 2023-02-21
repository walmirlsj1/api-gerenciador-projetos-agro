package br.com.limac.gerprojetos2.api.v1.assembler;

import br.com.limac.gerprojetos2.api.v1.model.CaixaModel;
import br.com.limac.gerprojetos2.domain.model.Caixa;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CaixaModelAssembler {
    private ModelMapper modelMapper;

    public CaixaModel toModel(Caixa caixa) {
        CaixaModel caixaModel = new CaixaModel();

//        projetoModel.setConta(new ContaModel());

        modelMapper.map(caixa, caixaModel);


        return caixaModel;
    }

    public List<CaixaModel> toModelList(List<Caixa> caixas) {
        return caixas
                .stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Page<CaixaModel> toPageModel(Page<Caixa> pageCaixa) {
        return new PageImpl<>(this.toModelList(pageCaixa.getContent()), pageCaixa.getPageable(), pageCaixa.getTotalElements());
    }
}
