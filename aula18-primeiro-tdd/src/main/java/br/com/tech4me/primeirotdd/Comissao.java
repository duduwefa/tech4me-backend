package br.com.tech4me.primeirotdd;

public class Comissao {

    public Double calculaComissao(Double valorVenda) {
        double valorComissao;

        if (valorVenda < 1000) {
            valorComissao = valorVenda * 0.10;
        } else {
            valorComissao = valorVenda * 0.15;
        }

        return Math.round(valorComissao * 100) / 100d;
    }
    
}
