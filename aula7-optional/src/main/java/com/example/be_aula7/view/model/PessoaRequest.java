package com.example.be_aula7.view.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PessoaRequest {
    
    @NotBlank(message = "O nome deve possuir caracteres não brancos")
    @NotEmpty(message = "O nome deve ser preenchido")
    @Size(min = 5, message = "O nome deve ter no minimo 5 caracteres")
    private String nome;

    @NotBlank(message = "O sobrenome deve possuir caracteres não brancos")
    @NotEmpty(message = "O sobrenome deve ser preenchido")
    private String sobrenome;
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSobrenome() {
        return sobrenome;
    }
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    
}
