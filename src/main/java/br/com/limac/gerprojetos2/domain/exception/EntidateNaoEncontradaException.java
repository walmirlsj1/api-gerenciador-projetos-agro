package br.com.limac.gerprojetos2.domain.exception;

public class EntidateNaoEncontradaException extends NegocioException {
    public EntidateNaoEncontradaException(String mensagem){
        super((mensagem));
    }
}
