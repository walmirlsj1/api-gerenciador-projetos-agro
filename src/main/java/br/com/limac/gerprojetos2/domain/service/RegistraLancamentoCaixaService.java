package br.com.limac.gerprojetos2.domain.service;

import br.com.limac.gerprojetos2.domain.exception.NegocioException;
import br.com.limac.gerprojetos2.domain.model.*;
import br.com.limac.gerprojetos2.domain.repository.CaixaRepository;
import br.com.limac.gerprojetos2.domain.repository.ContaRepository;
import br.com.limac.gerprojetos2.domain.repository.ProjetoLancamentoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Objects;

@Service
@AllArgsConstructor
public class RegistraLancamentoCaixaService {

    private final CaixaRepository caixaRepository;
    private final CadastroContaService cadastroContaService;

    private final CadastroLancamentoService cadastroLancamentoService;

    private final RegistroCaixaService registroCaixaService;

    @Transactional
    public Caixa registrarLancamentoCaixa(ProjetoLancamento lancamento) {

        if (!Objects.isNull(lancamento.getCaixa()))
            throw new NegocioException("Lancamento já efetuado");

        ETipoCaixaLancamento tipoCaixaLancamento;

        switch (lancamento.getTipoLancamento()) {
            case ENTRADA:
                tipoCaixaLancamento = ETipoCaixaLancamento.ENTRADA;
                break;
            case SAIDA:
                tipoCaixaLancamento = ETipoCaixaLancamento.SAIDA;
                break;
            default:
                throw new NegocioException("Falha ao definir tipo de lancamento");
        }

        Caixa caixa = registroCaixaService.registrarCaixa(lancamento.getProjeto().getConta(), lancamento.getValor(), tipoCaixaLancamento, lancamento.getDescricao());

        lancamento.setCaixa(caixa);

        return caixa;
    }


    @Transactional
    public Caixa registrarLancamentoCaixa1(ProjetoLancamento lancamento) {

        if (!Objects.isNull(lancamento.getCaixa()))
            throw new NegocioException("Lancamento já efetuado");


        Caixa caixa = new Caixa();

        caixa.setConta(lancamento.getProjeto().getConta());
        caixa.setLancamento(lancamento);
        /**
         * pode alterar a conta projeto:?
         *
         */

        caixa.setData(OffsetDateTime.now());
        caixa.setValor(lancamento.getValor());

        if (lancamento.getTipoLancamento().equals(ETipoProjetoLancamento.SAIDA)) {
            caixa.setTipoLancamento(ETipoCaixaLancamento.SAIDA);
            caixa.registrar();
        } else if (lancamento.getTipoLancamento().equals(ETipoProjetoLancamento.ENTRADA)) {
            caixa.setTipoLancamento(ETipoCaixaLancamento.ENTRADA);
            caixa.registrar();
        }
        cadastroContaService.atualizar(caixa.getConta());

        lancamento.setCaixa(caixa);

        return caixaRepository.save(caixa);
    }

    @Transactional
    public void estornarLancamentoCaixa(ProjetoLancamento lancamento) {
        if (Objects.isNull(lancamento.getCaixa()))
            throw new NegocioException("Este lancamento ainda não foi registrado no caixa");


        Caixa caixa = lancamento.getCaixa();
        caixa.estornar();

        lancamento.setCaixa(null);

        caixaRepository.delete(caixa);
    }
}
