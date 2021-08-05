package br.fcamara.com.ContatosApi.repository;

import br.fcamara.com.ContatosApi.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
}
