package br.com.limac.gerprojetos2.domain.service;

import br.com.limac.gerprojetos2.domain.exception.BancoNaoEncontradoException;
import br.com.limac.gerprojetos2.domain.exception.EntidadeEmUsoException;
import br.com.limac.gerprojetos2.domain.exception.ProjetoLancamentoNaoEncontradoException;
import br.com.limac.gerprojetos2.domain.model.Banco;
import br.com.limac.gerprojetos2.domain.model.Projeto;
import br.com.limac.gerprojetos2.domain.model.ProjetoLancamento;
import br.com.limac.gerprojetos2.domain.repository.BancoRepository;
import br.com.limac.gerprojetos2.domain.repository.ProjetoLancamentoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class CadastroLancamentoService {
    private static final String MSG_PROJETO_LANCAMENTO_EM_USO
            = "Lancamento com código %d não pode ser removida, pois está em uso.";
    private final ProjetoLancamentoRepository projetoLancamentoRepository;

    private final CadastroProjetoService cadastroProjetoService;

    @Transactional
    public ProjetoLancamento inserir(ProjetoLancamento lancamento) {
        validarLancamento(lancamento);
        return projetoLancamentoRepository.save(lancamento);
    }

    private void validarLancamento(ProjetoLancamento projetoLancamento){
        Projeto projeto = cadastroProjetoService.buscarOuFalhar(projetoLancamento.getProjeto().getId());
        projetoLancamento.setProjeto(projeto);
    }
    @Transactional
    public ProjetoLancamento atualizar(ProjetoLancamento lancamento) {
        lancamento.setDataAtualizacao(OffsetDateTime.now());

        return projetoLancamentoRepository.save(lancamento);
    }

    @Transactional
    public void excluir(Long lancamentoId) {
        try {
            projetoLancamentoRepository.deleteById(lancamentoId);
            projetoLancamentoRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new ProjetoLancamentoNaoEncontradoException(lancamentoId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_PROJETO_LANCAMENTO_EM_USO, lancamentoId));
        }

    }

    public ProjetoLancamento buscarOuFalhar(Long lancamentoId) {
        return projetoLancamentoRepository.findById(lancamentoId).orElseThrow(() -> new ProjetoLancamentoNaoEncontradoException(lancamentoId));
    }


}
