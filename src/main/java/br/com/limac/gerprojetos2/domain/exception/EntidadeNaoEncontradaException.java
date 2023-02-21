package br.com.limac.gerprojetos2.domain.exception;

public class EntidadeNaoEncontradaException extends NegocioException {
    public EntidadeNaoEncontradaException(String mensagem){
        super((mensagem));
    }
}
