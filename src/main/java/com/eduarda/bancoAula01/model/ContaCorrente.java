package com.eduarda.bancoAula01.model;

import com.eduarda.bancoAula01.exception.SaldoInsuficienteException;

public class ContaCorrente extends Conta{
    public ContaCorrente(int numero, String titular, double saldo) {
        super(numero, titular, saldo);
    }

    @Override
    public void sacar(double valor) throws SaldoInsuficienteException{
        if(super.getSaldo() < valor){
            throw new SaldoInsuficienteException();
        }
        double novoSaldo = super.getSaldo() - valor;
        super.setSaldo(novoSaldo);
    }
}
