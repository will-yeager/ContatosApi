package br.fcamara.com.ContatosApi.config.validacao;

import br.fcamara.com.ContatosApi.controller.ContatoExistenteException;
import br.fcamara.com.ContatosApi.model.Contato;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
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
