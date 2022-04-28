package br.com.tech4me.filmes.telas;

import java.util.List;
import java.util.Scanner;

import org.springframework.data.repository.Repository;

import br.com.tech4me.filmes.model.Filme;
import br.com.tech4me.filmes.repositorios.FilmeRepositorio;

public class TelaListarFilmes implements Tela<Filme, Integer> {

    @Override
    public void executar(Scanner entrada, Repository<Filme, Integer> repositorio) {
        FilmeRepositorio repo = (FilmeRepositorio) repositorio;
        List<Filme> filmes = repo.obterTodosOsFilmesCompleto();

        if(filmes.isEmpty()) {
            System.out.println("Não há filmes cadastrados");
            return;
        }

        System.out.println("\n********************");
        System.out.println("Lista de filmes cadastrados:");
        System.out.println("\n********************");
        filmes.forEach(System.out::println);
    }
    
}