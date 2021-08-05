package br.fcamara.com.ContatosApi.controller;

import br.fcamara.com.ContatosApi.controller.form.ContatoForm;
import br.fcamara.com.ContatosApi.model.Contato;
import br.fcamara.com.ContatosApi.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contatos")
public class ContatosController {

    @Autowired
    private ContatoRepository contatoRepository;

    @GetMapping
    public List<Contato> listaTelefonica() {
        return contatoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contato> contato(@PathVariable Long id) {
        Optional<Contato> optional = contatoRepository.findById(id);
        if(optional.isPresent()) {
            return ResponseEntity.ok(contatoRepository.getById(id));
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping
    @Transactional
    public ResponseEntity<Contato> adicionar(@RequestBody @Valid ContatoForm form, UriComponentsBuilder uriBuilder) {

        Contato contato = form.converter();
        contatoRepository.save(contato);

        URI uri = uriBuilder.path("/contato/{id}").buildAndExpand(contato.getId()).toUri();
        return ResponseEntity.created(uri).body(contato);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Contato> atualizar(@PathVariable Long id,@RequestBody @Valid ContatoForm form) {
        Optional<Contato> optional = contatoRepository.findById(id);
        if(optional.isPresent()) {
            Contato contato = form.atualizar(id, contatoRepository);
            return ResponseEntity.ok(contato);
        }
        return  ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        Optional<Contato> optional = contatoRepository.findById(id);
        if(optional.isPresent()) {
            contatoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
