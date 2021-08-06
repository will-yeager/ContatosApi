package br.fcamara.com.ContatosApi.repository;

import br.fcamara.com.ContatosApi.model.Contato;
import br.fcamara.com.ContatosApi.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
