package br.com.limac.gerprojetos2.domain.service;

import br.com.limac.gerprojetos2.domain.exception.ClienteNaoEncontradoException;
import br.com.limac.gerprojetos2.domain.exception.EntidadeEmUsoException;
import br.com.limac.gerprojetos2.domain.exception.ProjetoNaoEncontradoException;
import br.com.limac.gerprojetos2.domain.model.Cliente;
import br.com.limac.gerprojetos2.domain.model.Conta;
import br.com.limac.gerprojetos2.domain.model.Projeto;
import br.com.limac.gerprojetos2.domain.repository.ProjetoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class CadastroProjetoService {
    private static final String MSG_PROJETO_EM_USO
            = "Projeto com código %d não pode ser removido, pois está em uso.";
    private final ProjetoRepository projetoRepository;

    private final CadastroClienteService cadastroClienteService;

    private final CadastroContaService cadastroContaService;


    @Transactional
    public Projeto inserir(Projeto projeto) {
        validarProjeto(projeto);

        projeto.setEstaAtivo(true);

        return projetoRepository.save(projeto);
    }
    private void validarProjeto(Projeto projeto){
        Conta conta = cadastroContaService.buscarOuFalhar(projeto.getConta().getId());
        Cliente cliente = cadastroClienteService.buscarOuFalhar(projeto.getCliente().getId());

        projeto.setConta(conta);
        projeto.setCliente(cliente);


    }

    @Transactional
    public Projeto atualizar(Projeto projeto) {
        validarProjeto(projeto);

        projeto.setDataAtualizacao(OffsetDateTime.now());

        return projetoRepository.save(projeto);
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
