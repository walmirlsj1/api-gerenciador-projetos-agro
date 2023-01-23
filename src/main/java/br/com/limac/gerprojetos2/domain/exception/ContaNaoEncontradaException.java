package br.com.limac.gerprojetos2.domain.exception;

public class ContaNaoEncontradaException extends EntidateNaoEncontradaException {
    public ContaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public ContaNaoEncontradaException(Long contaId) {
        this(String.format("Não existe um cadastro de conta com código %d", contaId));
    }
}
