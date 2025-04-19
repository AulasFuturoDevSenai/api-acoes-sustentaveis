package br.com.senai.projetoSustentavel.model.exceptions;

public class RecursoNaoEncontradoException extends RuntimeException {
    public RecursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

}
