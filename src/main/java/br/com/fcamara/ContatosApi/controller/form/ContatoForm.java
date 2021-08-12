package br.com.fcamara.ContatosApi.controller.form;

import br.com.fcamara.ContatosApi.repository.ContatoRepository;
import br.com.fcamara.ContatosApi.model.Contato;
import br.com.fcamara.ContatosApi.model.Endereco;
import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

public class ContatoForm {

    @NotNull @Length(min = 4)
    private String nome;

    @NotNull @Pattern(regexp="^((\\(\\d{2}\\))|\\d{2})[- .]?\\d{5}[- .]?\\d{4}$")
    private String telefone;

    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public Contato converter(Endereco endereco) {
        return new Contato(this.nome, this.telefone, this.email, endereco);
    }

    public Contato atualizar(String nome, ContatoRepository contatoRepository) {
        Contato contato = contatoRepository.getByNome(nome);
        contato.setNome(this.nome);
        contato.setTelefone(this.telefone);
        contato.setEmail(this.email);
        return contato;
    }
}
