package br.com.limac.gerprojetos2.domain.exception;

public class ProjetoLancamentoNaoEncontradoException extends EntidateNaoEncontradaException {
    public ProjetoLancamentoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public ProjetoLancamentoNaoEncontradoException(Long lancamentoId) {
        this(String.format("Não existe um cadastro de lancamento com código %d", lancamentoId));
    }
}
