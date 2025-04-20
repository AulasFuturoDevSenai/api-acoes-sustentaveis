package br.com.senai.projetoSustentavel.model.exceptions;

public class CategoriaInvalidaException extends RuntimeException {
    public CategoriaInvalidaException(String mensagem) {
        super(mensagem);
    }
}
