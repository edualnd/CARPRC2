package com.eduarda.bancoAula01.app;



import com.eduarda.bancoAula01.exception.SaldoInsuficienteException;
import com.eduarda.bancoAula01.model.ContaCorrente;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> linhas = lerDados();
        int linha = 0;
        String ent = "";
        do{
            String[] dados = linhas.get(linha).split(",");
            int numero = Integer.parseInt(dados[0]);
            double saldo = Double.parseDouble(dados[2]);
            ContaCorrente c = new ContaCorrente(numero, dados[1], saldo);
            c.imprimirDados();

            solicitarSaque(c);
            salvarDadosAtualizados(c);
            System.out.print("Deseja continuar com a proxima conta? (y/n)\n>>");
            ent = sc.nextLine();
            linha++;
        }while(Objects.equals(ent, "y"));

    }

    private static List<String> lerDados(){
        Path caminho = Paths.get("src/com/eduarda/banco/doc/conta.txt");
        List<String> linhas = null;
        try {
            linhas = Files.readAllLines(caminho);
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo");
        }
        return linhas;
    }
    private static void solicitarSaque(ContaCorrente c){
        Scanner sc = new Scanner(System.in);
        System.out.print("Deseja fazer um saque? (y/n)\n>> ");
        String ent = sc.nextLine();

        if(ent.equals("y")){
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
        }
    }
    private static void salvarDadosAtualizados(ContaCorrente c) {
        Path caminho = Paths.get("src/com/eduarda/banco/doc/conta_atualizada.txt");
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
