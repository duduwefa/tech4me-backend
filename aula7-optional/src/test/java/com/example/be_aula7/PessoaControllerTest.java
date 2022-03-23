package com.example.be_aula7;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebMvcTest
public class PessoaControllerTest {
    
    @Autowired
    private PessoaController pessoaController;

    @MockBean
    PessoaService pessoaService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(pessoaController).build();
    }

    @Test
    public void deve_retornar_status_200_ao_listar_pessoas() throws Exception {
        List<PessoaDto> pessoas = new ArrayList<>();

        when(this.pessoaService.obterTodos()).thenReturn(pessoas);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/pessoas"))
            .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void deve_retornar_pessoa_por_id() throws Exception {
        PessoaDto p1 = new PessoaDto();
        p1.setId("123");
        p1.setNome("Eduardo");
        p1.setSobrenome("Carvalho");

        Optional<PessoaDto> p1o = Optional.of(p1);

        when(this.pessoaService.obterPorId(p1.getId())).thenReturn(p1o);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/pessoas/123"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Eduardo"));

    }

    @Test
    public void deve_criar_nova_pessoa_usuario_por_id() throws Exception {
        PessoaDto p1 = new PessoaDto();
        p1.setId("456");
        p1.setNome("Daniel");
        p1.setSobrenome("Carvalho");

        ObjectMapper map = new ObjectMapper();
        String json = map.writeValueAsString(p1);

        when(pessoaService.criarPessoa(any())).thenReturn(p1);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/pessoas")
        .content(json)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    public void deve_atualizar_pessoa_por_id() throws Exception {
        PessoaDto p2 = new PessoaDto();
        p2.setId("456");
        p2.setNome("Ziza");
        p2.setSobrenome("Carvalho");

        ObjectMapper map = new ObjectMapper();
        String json = map.writeValueAsString(p2);

        when(this.pessoaService.atualizarPessoa(anyString(), any())).thenReturn(p2);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/pessoas/{id}", 456)
        .content(json)
        .contentType(MediaType.APPLICATION_JSON)
        // .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("456"));
        /* .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Ziza"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.sobrenome").value("Carvalho")); */
    }

    @Test
    public void deve_excluir_pessoa_por_id() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/pessoas/{id}", 456))
        .andExpect(MockMvcResultMatchers.status().isNoContent());

    }


}
