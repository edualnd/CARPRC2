package com.eduarda.conta.bancaria.aula2.app;




import com.eduarda.conta.bancaria.aula2.model.ContaCorrente;
import com.eduarda.conta.bancaria.aula2.service.ContaService;

import java.util.List;

import java.util.Scanner;


public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ContaService cs =  new ContaService();
        List<ContaCorrente> contas = cs.lerDados();

        for (ContaCorrente c : contas){
            System.out.println("Numero: " + c.getNumero());
            System.out.println("Titular: " + c.getTitular());
            System.out.println("------------------------------");
        }
        ContaCorrente contaEscolhida = null;
        int ent;
        System.out.println("O que deseja: ");
        do{
            System.out.println("Escolha a conta para efetuar as operações, e digite seu numero");
            int numero = sc.nextInt();
            for (ContaCorrente c : contas){
                if(c.getNumero() == numero){
                    contaEscolhida = c;
                    break;
                }
            }
            if(contaEscolhida == null){

            }
        }while(contaEscolhida == null);

        contaEscolhida.imprimirDados();
        int ent;
        do{
            System.out.print("Qual operação desenja realizar?\n[1]Saque [2]Deposito [0] Sair\n>> ");
            ent = sc.nextInt();
            if(ent == 1){
                cs.solicitarSaque(contaEscolhida);
            } else if (ent == 2) {
                cs.solicitarDesposito(contaEscolhida);
            }else if (ent == 0) {

            }
            else{
                System.out.println("Escolha Invalida. Tente novamente");
            }
        }while(ent<0 || ent >3);
        cs.salvarDadosAtualizados(contaEscolhida);

    }


}
