package br.com.limac.gerprojetos2.domain.exception;

public class NegocioException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NegocioException(String mensagem){
        super(mensagem);
    }
}
