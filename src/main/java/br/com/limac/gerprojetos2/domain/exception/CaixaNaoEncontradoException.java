package br.com.limac.gerprojetos2.domain.exception;

public class BancoNaoEncontradoException extends EntidateNaoEncontradaException {
    public BancoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
