package br.com.limac.gerprojetos2.domain.exception;

public class CaixaNaoEncontradoException extends EntidateNaoEncontradaException {
    public CaixaNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
    public CaixaNaoEncontradoException(Long caixaId) {
        this(String.format("Não existe um cadastro de caixa com código %d", caixaId));
    }
}
