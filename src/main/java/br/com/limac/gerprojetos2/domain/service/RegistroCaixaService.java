package br.com.limac.gerprojetos2.domain.service;

import br.com.limac.gerprojetos2.domain.exception.ClienteNaoEncontradoException;
import br.com.limac.gerprojetos2.domain.exception.NegocioException;
import br.com.limac.gerprojetos2.domain.model.*;
import br.com.limac.gerprojetos2.domain.repository.CaixaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;

@Service
@AllArgsConstructor
public class RegistroCaixaService {
    private final CaixaRepository caixaRepository;
    private final CadastroContaService cadastroContaService;

    @Transactional
    public Caixa registrarCaixa(Conta conta, BigDecimal valor, ETipoCaixaLancamento eTipoCaixaLancamento, String descricao) {

        Caixa caixa = new Caixa();

        caixa.setConta(conta);
        caixa.setDescricao(descricao);
        caixa.setData(OffsetDateTime.now());
        caixa.setValor(valor);
        caixa.setTipoLancamento(eTipoCaixaLancamento);

        System.out.printf("\nContaId: %d\nData: %s\nValor: %s\nTipo: %s%n", conta.getId(), caixa.getData().toString(), caixa.getValor().toString(), caixa.getTipoLancamento());
        caixa.registrar();

        return caixaRepository.save(caixa);
    }

    @Transactional
    public void estornarCaixa(ProjetoLancamento lancamento) {
        if (Objects.isNull(lancamento.getCaixa()))
            throw new NegocioException("Este lancamento ainda não foi registrado no caixa");


        Caixa caixa = lancamento.getCaixa();
        caixa.estornar();

        lancamento.setCaixa(null);

        caixaRepository.delete(caixa);
    }

    public Caixa buscarOuFalhar(Long caixaId) {
        return caixaRepository.findById(caixaId).orElseThrow(() -> new NegocioException(" Registro no caixa não encontrado: " + caixaId));
    }
}
