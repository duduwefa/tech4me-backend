package br.com.tech4me.aula5crudpt1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech4me.aula5crudpt1.model.Produto;
import br.com.tech4me.aula5crudpt1.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    
    @Autowired
    private ProdutoService service;

    @GetMapping
    public List<Produto> obterTodos() {
        return service.obterTodos();
    }

    @PostMapping
    public Produto criarProduto(@RequestBody Produto produto) {
        return service.criarProduto(produto);
    }

}
