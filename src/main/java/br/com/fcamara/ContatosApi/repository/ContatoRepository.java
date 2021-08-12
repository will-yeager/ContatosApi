package br.com.fcamara.ContatosApi.repository;

import br.com.fcamara.ContatosApi.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

    Optional<Contato> findByNome(String nome);

    Contato getByNome(String nome);

    void deleteByNome(String nome);
}
