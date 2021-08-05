package br.fcamara.com.ContatosApi.controller.form;

import br.fcamara.com.ContatosApi.model.Contato;
import br.fcamara.com.ContatosApi.repository.ContatoRepository;
import br.fcamara.com.ContatosApi.service.ValidadorTelefoneService;
import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

public class ContatoForm {

    @NotNull @Length(min = 4)
    private String nome;

    @NotNull @Pattern(regexp="^((\\(\\d{2}\\))|\\d{2})[- .]?\\d{5}[- .]?\\d{4}$")
    private String telefone;

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

    public Contato converter() {
        return new Contato(this.nome, this.telefone);
    }

    public Contato atualizar(Long id, ContatoRepository contatoRepository) {
        Contato contato = contatoRepository.getById(id);
        ValidadorTelefoneService.validar(this.telefone);
        contato.setNome(this.nome);
        contato.setTelefone(this.telefone);
        return contato;
    }
}
