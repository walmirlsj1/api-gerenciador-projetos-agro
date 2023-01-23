package br.com.limac.gerprojetos2.domain.exception;

public class ProjetoNaoEncontradoException extends EntidateNaoEncontradaException {
    public ProjetoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public ProjetoNaoEncontradoException(Long projetoId) {
        this(String.format("Não existe um cadastro de projeto com código %d", projetoId));
    }
}
