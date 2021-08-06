package br.fcamara.com.ContatosApi.controller.dto;

import br.fcamara.com.ContatosApi.model.Contato;
import br.fcamara.com.ContatosApi.model.Endereco;

import java.util.List;
import java.util.stream.Collectors;

public class ContatoDto {

    private String nome;

    private String telefone;

    private String email;

    private EnderecoDto enderecoDto;

    public ContatoDto(Contato contato) {
        this.nome = contato.getNome();
        this.telefone = contato.getTelefone();
        this.email = contato.getEmail();
        this.enderecoDto = new EnderecoDto(contato.getEndereco());
    }

    public static List<ContatoDto> converter(List<Contato> contatos) {
        return contatos.stream().map(ContatoDto::new).collect(Collectors.toList());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EnderecoDto getEnderecoDto() {
        return enderecoDto;
    }
}
