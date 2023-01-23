package br.com.limac.gerprojetos2.domain.service;

import br.com.limac.gerprojetos2.domain.model.*;
import br.com.limac.gerprojetos2.domain.repository.CaixaRepository;
import br.com.limac.gerprojetos2.domain.repository.ContaRepository;
import br.com.limac.gerprojetos2.domain.repository.ProjetoLancamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class RegistraLancamentoCaixaService {

    private final CaixaRepository caixaRepository;

    public Caixa registrarLancamentoCaixa(ProjetoLancamento lancamento) {
        Caixa caixa = new Caixa();

        caixa.setConta(lancamento.getConta());
        caixa.setData(OffsetDateTime.now());
        caixa.setValor(lancamento.getValor());

        if (lancamento.getTipoLancamento().equals(ETipoProjetoLancamento.SAIDA)) {
            caixa.setTipoLancamento(ETipoCaixaLancamento.SAIDA);
            caixa.registrar();
        } else if (lancamento.getTipoLancamento().equals(ETipoProjetoLancamento.ENTRADA)) {
            caixa.setTipoLancamento(ETipoCaixaLancamento.ENTRADA);
            caixa.registrar();
        }

        return caixaRepository.save(caixa);
    }

    public void estornarLancamentoCaixa(ProjetoLancamento lancamento){
        lancamento.getCaixa().estornar();

        caixaRepository.delete(lancamento.getCaixa());
    }
}
