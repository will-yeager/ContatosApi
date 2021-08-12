package br.com.fcamara.ContatosApi.service;

import br.com.fcamara.ContatosApi.controller.ContatoExistenteException;
import br.com.fcamara.ContatosApi.model.Contato;
import br.com.fcamara.ContatosApi.repository.ContatoRepository;

import java.util.Optional;

public class ValidadorDeContatoExistenteService {

    public static void verificar(String nome, ContatoRepository contatoRepository) {
        Optional<Contato> optional = contatoRepository.findByNome(nome);
        if(optional.isPresent()){
            throw new ContatoExistenteException();
        }
    }
}
