package com.eduarda.conta.bancaria.aula2.service;

import com.eduarda.conta.bancaria.aula2.exception.SaldoInsuficienteException;
import com.eduarda.conta.bancaria.aula2.model.ContaCorrente;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContaService {

    public ContaService() {
    }

    public List<ContaCorrente> lerDados(){
        Path caminho = Paths.get("src/com/eduarda/conta/bancaria/aula2/doc/conta.txt");
        List<String> linhas = null;
        try {
            linhas = Files.readAllLines(caminho);
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo");
        }

        List<ContaCorrente> contas = new ArrayList<>();
        for(String l : linhas){
            String[] dados = l.split(",");
            int numero = Integer.parseInt(dados[0]);
            double saldo = Double.parseDouble(dados[2]);
            contas.add(new ContaCorrente(numero, dados[1], saldo));
        }
        return contas;
    }
    public void solicitarSaque(ContaCorrente c){
        Scanner sc = new Scanner(System.in);
        boolean isSaqueFeito = false;
        do{
            System.out.print("Digite o valor: ");
            double valor = sc.nextDouble();
            try{
                c.sacar(valor);
                isSaqueFeito = true;
            } catch (SaldoInsuficienteException e) {
                System.out.println(e.getMessage() + "  Tente novamente");
                System.out.println("Saldo atual: " + c.getSaldo());
            }
        }while(!isSaqueFeito);
        System.out.println("Saque realizado com sucesso");
    }
    public void solicitarDesposito(ContaCorrente c){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o valor: ");
        double valor = sc.nextDouble();
        c.depositar(valor);
        System.out.println("Deposito realizado com sucesso");

    }
    public void salvarDadosAtualizados(ContaCorrente c) {
        Path caminho = Paths.get("src/com/eduarda/conta/bancaria/aula2//doc/conta_atualizada.txt");
        String dadosAtualizados =  c.getNumero() + ","
                + c.getTitular() + ","
                + c.getSaldo() + "\n";
        try {
            Files.writeString(caminho, dadosAtualizados, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo");
        }
        System.out.println("Dados atualizados com sucesso");
    }


}
