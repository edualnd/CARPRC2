package com.eduarda.conta.bancaria.aula2.service;

import com.eduarda.conta.bancaria.aula2.model.Conta;
import com.eduarda.conta.bancaria.aula2.model.ContaCorrente;

import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.groupingBy;

public class BancoService {
    public BancoService() {
    }

    public void filtrarContas(List<ContaCorrente> contas){
         contas.stream()
                 .filter(c -> c.getSaldo() > 10000)
                 .forEach(Conta::imprimirDados);
    }
    public void saldoTotal(List<ContaCorrente> contas){
        double saldoTotal = contas.stream().map(Conta::getSaldo).reduce((double) 0, Double::sum);
        System.out.println(saldoTotal);
    }

    public void faixaSaldo(List<ContaCorrente> contas){
        Map<String, List<ContaCorrente>> res = contas.stream()
                .collect(groupingBy(c -> {
                    if(c.getSaldo() <= 5000) return "Até R$ 5000";
                    else if (c.getSaldo() >= 5001 && c.getSaldo() < 10000) return "De R$ 5001 a R$ 10000";
                    else return "Acima de R$ 10000";
                }));
        res.forEach((faixa, total) ->
        {
            System.out.println("-------------");
            System.out.println(faixa + ": ");
            System.out.println("-------------");
            total.forEach(c -> {
                System.out.println("N° - " + c.getNumero() + " / " + c.getTitular());
                System.out.println("----------");
            } );
        });
    }
}
