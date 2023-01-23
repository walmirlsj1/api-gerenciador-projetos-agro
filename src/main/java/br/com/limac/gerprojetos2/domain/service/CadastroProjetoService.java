package br.com.limac.gerprojetos2.domain.service;

import br.com.limac.gerprojetos2.domain.exception.ClienteNaoEncontradoException;
import br.com.limac.gerprojetos2.domain.exception.EntidadeEmUsoException;
import br.com.limac.gerprojetos2.domain.exception.ProjetoNaoEncontradoException;
import br.com.limac.gerprojetos2.domain.model.Cliente;
import br.com.limac.gerprojetos2.domain.model.Projeto;
import br.com.limac.gerprojetos2.domain.repository.ProjetoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CadastroProjetoService {
    private static final String MSG_PROJETO_EM_USO
            = "Projeto com código %d não pode ser removido, pois está em uso.";
    private final ProjetoRepository projetoRepository;

    @Transactional
    public Projeto inserir(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    @Transactional
    public Projeto atualizar(Projeto cliente) {
        return projetoRepository.save(cliente);
    }

    @Transactional
    public void excluir(Long projetoId) {
        try {
            projetoRepository.deleteById(projetoId);
            projetoRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new ProjetoNaoEncontradoException(projetoId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_PROJETO_EM_USO, projetoId));
        }

    }

    public Projeto buscarOuFalhar(Long projetoId) {
        return projetoRepository.findById(projetoId).orElseThrow(() -> new ClienteNaoEncontradoException(projetoId));
    }


}
