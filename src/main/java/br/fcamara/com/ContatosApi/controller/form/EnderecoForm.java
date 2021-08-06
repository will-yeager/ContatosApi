package br.fcamara.com.ContatosApi.controller.form;

import br.fcamara.com.ContatosApi.model.Contato;
import br.fcamara.com.ContatosApi.model.Endereco;
import br.fcamara.com.ContatosApi.repository.ContatoRepository;
import br.fcamara.com.ContatosApi.repository.EnderecoRepository;

import java.util.Optional;

public class EnderecoForm {

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String cep;

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



    public Endereco criarEndereco() {
        Endereco endereco = new Endereco();
        endereco.setLogradouro(this.logradouro);
        endereco.setBairro(this.bairro);
        endereco.setComplemento(this.complemento);
        endereco.setCep(this.cep);
        endereco.setNumero(this.numero);
        endereco.setCidade(this.cidade);

        return endereco;
    }

    public Endereco atualizar(Contato contato) {
        Endereco endereco = contato.getEndereco();
        endereco.setLogradouro(this.logradouro);
        endereco.setBairro(this.bairro);
        endereco.setComplemento(this.complemento);
        endereco.setCep(this.cep);
        endereco.setNumero(this.numero);
        endereco.setCidade(this.cidade);

        return endereco;
    }
}
