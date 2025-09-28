package br.edu.infnet.mono.domain.exception;

import java.io.Serial;
import java.util.Locale;

public class NaoEncontradoException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    public NaoEncontradoException(String mensagem) {
        super(mensagem.toUpperCase(Locale.ROOT));
    }
}
