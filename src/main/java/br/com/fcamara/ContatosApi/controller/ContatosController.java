package br.com.fcamara.ContatosApi.controller;

import br.com.fcamara.ContatosApi.controller.dto.EnderecoDto;
import br.com.fcamara.ContatosApi.controller.form.ContatoForm;
import br.com.fcamara.ContatosApi.repository.ContatoRepository;
import br.com.fcamara.ContatosApi.controller.dto.ContatoDto;
import br.com.fcamara.ContatosApi.controller.form.ContatoeEnderecoForm;
import br.com.fcamara.ContatosApi.controller.form.EnderecoForm;
import br.com.fcamara.ContatosApi.model.Contato;
import br.com.fcamara.ContatosApi.model.Endereco;
import br.com.fcamara.ContatosApi.repository.EnderecoRepository;
import br.com.fcamara.ContatosApi.service.ValidadorDeContatoExistenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping
    @Cacheable(value = "listaTelefonica")
    public List<ContatoDto> listaTelefonica() {
        List<Contato> contatos =  contatoRepository.findAll();
        return ContatoDto.converter(contatos);
    }

    @GetMapping("/{nome}")
    public ResponseEntity<ContatoDto> contato(@PathVariable String nome) {
        Optional<Contato> optional = contatoRepository.findByNome(nome);
        if(optional.isPresent()) {
            ContatoDto contatoDto = new ContatoDto(optional.get());
            return ResponseEntity.ok(contatoDto);
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/{nome}/endereco")
    public ResponseEntity<EnderecoDto> endereco(@PathVariable String nome) {
        Optional<Contato> optional = contatoRepository.findByNome(nome);
        if(optional.isPresent()) {
            EnderecoDto enderecoDto = new EnderecoDto(optional.get().getEndereco());
            return ResponseEntity.ok(enderecoDto);
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaTelefonica", allEntries = true)
    public ResponseEntity<ContatoDto> adicionar(@RequestBody @Valid ContatoeEnderecoForm form, UriComponentsBuilder uriBuilder) {
            Optional<Contato> optional = contatoRepository.findByNome(form.getContatoForm().getNome());
            if (optional.isEmpty()) {
                System.out.println(form.getEnderecoForm().getNumero());
                Endereco endereco = form.getEnderecoForm().criarEndereco();
                enderecoRepository.save(endereco);
                Contato contato = form.getContatoForm().converter(endereco);
                contatoRepository.save(contato);

                URI uri = uriBuilder.path("/contato/{nome}").buildAndExpand(contato.getNome()).toUri();
                return ResponseEntity.created(uri).body(new ContatoDto(contato));
            }
            throw new ContatoExistenteException();
    }

    /* ATUALIZA APENAS OS DADOOS DO USUARIO DO CONTATO */
    @PutMapping("/{nome}")
    @Transactional
    @CacheEvict(value = "listaTelefonica", allEntries = true)
    public ResponseEntity<ContatoDto> atualizar(@PathVariable String nome,@RequestBody @Valid ContatoForm form) {
        Optional<Contato> optional = contatoRepository.findByNome(nome);
        if(optional.isPresent()) {
            ValidadorDeContatoExistenteService.verificar(form.getNome(), contatoRepository);
            Contato contato = form.atualizar(nome, contatoRepository);
            return ResponseEntity.ok(new ContatoDto(contato));
        }
        return  ResponseEntity.notFound().build();
    }

    /* ATUALIZA APENAS O ENDERECO DO CONTATO */
    @PutMapping("/{nome}/endereco")
    @Transactional
    @CacheEvict(value = "listaTelefonica", allEntries = true)
    public ResponseEntity<EnderecoDto> atualizaroEndereco(@PathVariable String nome,@RequestBody @Valid EnderecoForm form) {
        Optional<Contato> optional = contatoRepository.findByNome(nome);
        if(optional.isPresent()) {
            Endereco endereco = form.atualizar(optional.get());

            return ResponseEntity.ok(new EnderecoDto(endereco));
        }
        return  ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{nome}")
    @Transactional
    @CacheEvict(value = "listaTelefonica", allEntries = true)
    public ResponseEntity<?> deletar(@PathVariable String nome) {
        Optional<Contato> optional = contatoRepository.findByNome(nome);
        if(optional.isPresent()) {
            enderecoRepository.deleteById(optional.get().getEndereco().getId());
            contatoRepository.deleteByNome(nome);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
