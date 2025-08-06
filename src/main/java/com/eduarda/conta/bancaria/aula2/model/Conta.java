package com.eduarda.conta.bancaria.aula2.model;

public abstract class Conta {

    private int numero;
    private String titular;
    private double saldo;

    public Conta(int numero, String titular, double saldo) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public abstract void sacar(double valor);

    public void depositar(double valor){
        saldo+=valor;
    }

    public void imprimirDados(){
        System.out.printf("Numero: %d" +
                "\nTitular: %s" +
                "\nSaldo: R$%.2f%n",numero, titular, saldo);
    }


}
