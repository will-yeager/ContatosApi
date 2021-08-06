package br.fcamara.com.ContatosApi.controller.form;

import br.fcamara.com.ContatosApi.controller.ContatoExistenteException;
import br.fcamara.com.ContatosApi.model.Contato;
import br.fcamara.com.ContatosApi.model.Endereco;
import br.fcamara.com.ContatosApi.repository.ContatoRepository;
import br.fcamara.com.ContatosApi.repository.EnderecoRepository;
import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import java.util.Optional;

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
