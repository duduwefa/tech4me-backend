package br.com.tech4me.pessoasms.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech4me.pessoasms.compartilhado.PessoaDto;
import br.com.tech4me.pessoasms.model.Pessoa;
import br.com.tech4me.pessoasms.service.PessoaService;
import br.com.tech4me.pessoasms.view.model.PessoaRequest;
import br.com.tech4me.pessoasms.view.model.PessoaResponse;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {
    
    @Autowired
    private PessoaService servico;

    ModelMapper mapper = new ModelMapper();
    
    @GetMapping(value ="/status")
    public String statusServico(@Value("${local.server.port}") String porta) {
        return String.format("Servico funcionando na porta %s", porta);
    }
    
    @GetMapping
    public ResponseEntity<List<PessoaResponse>> obterTodos() {
        List<PessoaDto> pessoaDto = servico.obterTodos();
        List<PessoaResponse> response = 
        pessoaDto.
        stream().
        map(p -> mapper.map(p, PessoaResponse.class)).
        collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

     @PostMapping
    public ResponseEntity<PessoaResponse> criarPessoa(@RequestBody @Valid PessoaRequest request) {
        PessoaDto pessoaDto = mapper.map(request, PessoaDto.class);
        pessoaDto = servico.criarPessoa(pessoaDto);
        PessoaResponse response = mapper.map(pessoaDto, PessoaResponse.class);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping(value="/{id2}")
    public ResponseEntity<PessoaResponse> obterPorId(@PathVariable String id) {
        Optional<PessoaDto> pessoaDto = servico.obterPorId(id);

        if(pessoaDto.isPresent()) {
            PessoaResponse response = mapper.map(pessoaDto.get(), PessoaResponse.class);
            return new ResponseEntity<>(response, HttpStatus.FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    
    @PutMapping(value="/{id}")
    public ResponseEntity<PessoaResponse> atualizarPessoa(@PathVariable String id, @RequestBody PessoaRequest request) {
        PessoaDto pessoaDto = mapper.map(request, PessoaDto.class);
        pessoaDto = servico.atualizarPessoa(id, pessoaDto);
        PessoaResponse response = mapper.map(pessoaDto, PessoaResponse.class);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }   
    
    
    @DeleteMapping(value="/{id}")
    public ResponseEntity<String> removerPessoa(@PathVariable String id) {
        servico.removerPessoa(id);
        return new ResponseEntity<String>("Removido com sucesso!", HttpStatus.OK);
    } 

    //-------------------

    @GetMapping(value="/optional/{id}")
    public ResponseEntity<Pessoa> obterPorIdComOptional(@PathVariable String id) {
        Optional<Pessoa> pessoa = servico.obterPorIdComOptional(id);

        if(pessoa.isPresent()) {
            return new ResponseEntity<>(pessoa.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);        
    }

}
