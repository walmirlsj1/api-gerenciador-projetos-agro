package br.com.limac.gerprojetos2.domain.exception;

public class EntidateNaoEncontrada extends NegocioException {
    public EntidateNaoEncontrada(String mensagem){
        super((mensagem));
    }
}
