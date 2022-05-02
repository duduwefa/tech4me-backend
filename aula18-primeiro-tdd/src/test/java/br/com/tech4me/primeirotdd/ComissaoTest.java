package br.com.tech4me.primeirotdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootTest
public class ComissaoTest {
    
    @TestConfiguration
    static class ComissaoConfig {
        @Bean
        public Comissao comissao() {
            return new Comissao();
        }

    }

    @Autowired
    Comissao comissao;
    
    @Test
    public void calculaComissaoDezPorcento() {
        Double valorVenda = 200.0;
        Double comissaoEsperado = 20.0;

        Double comissaoCalculada = comissao.calculaComissao(valorVenda);

        Assertions.assertEquals(comissaoEsperado, comissaoCalculada);
    }

    @Test
    public void calculaComissaoDezPorcentoDouble() {
        Double valorVenda = 1560.70;
        Double comissaoEsperado = 234.11;

        Double comissaoCalculada = comissao.calculaComissao(valorVenda);

        Assertions.assertEquals(comissaoEsperado, comissaoCalculada);
    }

}
