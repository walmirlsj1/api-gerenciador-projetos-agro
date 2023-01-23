package br.com.limac.gerprojetos2.domain.service;

import br.com.limac.gerprojetos2.domain.exception.ClienteNaoEncontradoException;
import br.com.limac.gerprojetos2.domain.exception.EntidadeEmUsoException;
import br.com.limac.gerprojetos2.domain.model.Cliente;
import br.com.limac.gerprojetos2.domain.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CadastroClienteService {
    private static final String MSG_CLIENTE_EM_USO
            = "Cliente com código %d não pode ser removida, pois está em uso.";
    private final ClienteRepository clienteRepository;

    @Transactional
    public Cliente inserir(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Transactional
    public Cliente atualizar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Transactional
    public void excluir(Long clienteId) {
        try {
            clienteRepository.deleteById(clienteId);
            clienteRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new ClienteNaoEncontradoException(clienteId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_CLIENTE_EM_USO, clienteId));
        }

    }

    public Cliente buscarOuFalhar(Long clienteId) {
        return clienteRepository.findById(clienteId).orElseThrow(() -> new ClienteNaoEncontradoException(clienteId));
    }


}
