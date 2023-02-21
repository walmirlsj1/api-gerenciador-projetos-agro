package br.com.limac.gerprojetos2.domain.service;

import br.com.limac.gerprojetos2.domain.exception.ContaNaoEncontradaException;
import br.com.limac.gerprojetos2.domain.exception.EntidadeEmUsoException;
import br.com.limac.gerprojetos2.domain.model.Banco;
import br.com.limac.gerprojetos2.domain.model.Conta;
import br.com.limac.gerprojetos2.domain.repository.ContaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class CadastroContaService {
    private static final String MSG_CONTA_EM_USO
            = "Conta com código %d não pode ser removida, pois está em uso.";
    private final ContaRepository contaRepository;

    private final CadastroBancoService cadastroBancoService;

    @Transactional
    public Conta inserir(Conta conta) {
        Banco banco = cadastroBancoService.buscarOuFalhar(conta.getBanco().getId());
        conta.setBanco(banco);
        conta.setEstaAtivo(true);
        return contaRepository.save(conta);
    }

    @Transactional
    public Conta atualizar(Conta conta) {
        conta.setDataAtualizacao(OffsetDateTime.now());

        return contaRepository.save(conta);
    }

    @Transactional
    public void excluir(Long contaId) {
        try {
            contaRepository.deleteById(contaId);
            contaRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new ContaNaoEncontradaException(contaId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_CONTA_EM_USO, contaId));
        }

    }

    public Conta buscarOuFalhar(Long contaId) {
        return contaRepository.findById(contaId).orElseThrow(() -> new ContaNaoEncontradaException(contaId));
    }


}
