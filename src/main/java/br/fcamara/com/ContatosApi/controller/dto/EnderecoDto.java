package br.fcamara.com.ContatosApi.controller.dto;

import br.fcamara.com.ContatosApi.model.Contato;
import br.fcamara.com.ContatosApi.model.Endereco;

public class EnderecoDto {

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String cep;

    public EnderecoDto(Endereco endereco) {
        this.logradouro = endereco.getLogradouro();
        this.complemento = endereco.getComplemento();
        this.numero = endereco.getNumero();
        this.bairro = endereco.getBairro();
        this.cidade = endereco.getCidade();
        this.cep = endereco.getCep();
    }
    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getCep() {
        return cep;
    }


}
