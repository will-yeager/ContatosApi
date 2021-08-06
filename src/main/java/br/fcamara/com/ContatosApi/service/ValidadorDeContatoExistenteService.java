package br.fcamara.com.ContatosApi.service;

import br.fcamara.com.ContatosApi.controller.ContatoExistenteException;
import br.fcamara.com.ContatosApi.model.Contato;
import br.fcamara.com.ContatosApi.repository.ContatoRepository;

import java.util.Optional;

public class ValidadorDeContatoExistenteService {

    public static void verificar(String nome, ContatoRepository contatoRepository) {
        Optional<Contato> optional = contatoRepository.findByNome(nome);
        if(optional.isPresent()){
            throw new ContatoExistenteException();
        }
    }
}
