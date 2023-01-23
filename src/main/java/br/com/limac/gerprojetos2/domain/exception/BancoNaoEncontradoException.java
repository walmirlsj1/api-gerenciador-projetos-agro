package br.com.limac.gerprojetos2.domain.exception;

public class BancoNaoEncontradoException extends EntidateNaoEncontradaException {
    public BancoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
    public BancoNaoEncontradoException(Long bancoId) {
        this(String.format("Não existe um cadastro de banco com código %d", bancoId));
    }
}
