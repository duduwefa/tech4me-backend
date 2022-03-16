package br.com.tech4me.aula5crudpt1.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.tech4me.aula5crudpt1.model.Produto;

public interface ProdutoRepository extends MongoRepository<Produto, String> {
    
}
