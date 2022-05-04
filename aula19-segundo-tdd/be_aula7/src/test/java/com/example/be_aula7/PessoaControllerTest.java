package com.example.be_aula7;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import com.example.be_aula7.compartilhado.PessoaDto;
import com.example.be_aula7.model.Pessoa;
import com.example.be_aula7.service.PessoaService;
import com.example.be_aula7.view.controller.PessoaController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Option;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType; 

@WebMvcTest
public class PessoaControllerTest {

    @MockBean
    private PessoaService pessoaService;
    
    @Autowired
    private PessoaController pessoaController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(pessoaController).build();
    }

    @Test
    public void tem_que_retornar_status_200_list_pessoas() throws Exception {
        List<PessoaDto> pessoas = new ArrayList<>();

        when(this.pessoaService.obterTodos()).thenReturn(pessoas);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/pessoas"))
                    .andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    @Test
    public void tem_que_retornar_pessoas_por_id() throws Exception {
        PessoaDto p1 = new PessoaDto();
        p1.setId("123");
        p1.setNome("Edu");
        p1.setSobrenome("Carvalho");

        Optional<PessoaDto> p1o = Optional.of(p1);

        when(pessoaService.obterPorId(anyString())).thenReturn(p1o);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/pessoas/{id}", 123))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Edu"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sobrenome").value("Carvalho"));

    }

    @Test
    public void deve_criar_pessoa_por_id() throws Exception {
        PessoaDto p1 = new PessoaDto();
        p1.setId("456");
        p1.setNome("Daniel");
        p1.setSobrenome("Carvalho");

        ObjectMapper map = new ObjectMapper();
        String body = map.writeValueAsString(p1);

        when(pessoaService.criarPessoa(any())).thenReturn(p1);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/pessoas")
                    .content(body)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void deve_atualizar_pessoas() throws Exception {
        PessoaDto p2 = new PessoaDto();
        p2.setId("789");
        p2.setNome("Ziza");
        p2.setSobrenome("Carvalho");

        ObjectMapper map = new ObjectMapper();
        String body = map.writeValueAsString(p2);

        when(pessoaService.atualizarPessoa(anyString(), any())).thenReturn(p2);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/pessoas/{id}", 123)
                    .content(body)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("789"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Ziza"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sobrenome").value("Carvalho"));
    }

    @Test
    public void deve_excluir() throws Exception {
        
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/pessoas/12341234"))
                .andExpect(MockMvcResultMatchers.content().string("Removido com sucesso!"));

    }

}
