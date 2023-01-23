package br.com.limac.gerprojetos2.domain.service;

import br.com.limac.gerprojetos2.domain.exception.BancoNaoEncontradoException;
import br.com.limac.gerprojetos2.domain.exception.EntidadeEmUsoException;
import br.com.limac.gerprojetos2.domain.model.Banco;
import br.com.limac.gerprojetos2.domain.repository.BancoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CadastroContaService {
    private static final String MSG_BANCO_EM_USO
            = "Banco com código %d não pode ser removida, pois está em uso.";
    private final BancoRepository bancoRepository;

    @Transactional
    public Banco inserir(Banco banco) {
        return bancoRepository.save(banco);
    }

    @Transactional
    public Banco atualizar(Banco banco) {
        return bancoRepository.save(banco);
    }

    @Transactional
    public void excluir(Long bancoId) {
        try {
            bancoRepository.deleteById(bancoId);
            bancoRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new BancoNaoEncontradoException(bancoId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_BANCO_EM_USO, bancoId));
        }

    }

    public Banco buscarOuFalhar(Long bancoId) {
        return bancoRepository.findById(bancoId).orElseThrow(() -> new BancoNaoEncontradoException(bancoId));
    }


}
