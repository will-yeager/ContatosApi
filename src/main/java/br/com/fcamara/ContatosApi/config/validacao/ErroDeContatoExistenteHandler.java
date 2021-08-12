package br.com.fcamara.ContatosApi.config.validacao;

import br.com.fcamara.ContatosApi.controller.ContatoExistenteException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroDeContatoExistenteHandler {

    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ExceptionHandler(ContatoExistenteException.class)
    public String handle(ContatoExistenteException ex) {
        return "Erro : Já existe um contato com esse nome";
    }
}
