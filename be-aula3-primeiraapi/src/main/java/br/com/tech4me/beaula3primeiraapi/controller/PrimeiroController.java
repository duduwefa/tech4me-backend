package br.com.tech4me.beaula3primeiraapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech4me.beaula3primeiraapi.model.Pessoa;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/primeiro")
public class PrimeiroController {
    
    @GetMapping
    public String requisicaoGet() {
        return "Alô, vida!";
    }

    @GetMapping(value="/{nome}")
    public String requisicaoGetComNome(@PathVariable String nome) {
        return String.format("Alô, mundo! Meu nome é %s", nome);
    }

    /* @PostMapping
    public String requisicaoComPost(@RequestBody String nome) {
        return String.format("Olá, %s! Eu respondi ao POST", nome);
    } */

    @PostMapping
    public String requisicaoComPost(@RequestBody Pessoa pessoa) {
        return String.format("Olá, %s! Eu respondi ao POST", pessoa);
    }

    @PutMapping
    public Pessoa requisicaoPut(@RequestBody Pessoa pessoa) {
        pessoa.setSobrenome(pessoa.getSobrenome() + "modificado");
        return pessoa;
    }
}
