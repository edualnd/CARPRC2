package com.eduarda.conta.bancaria.aula2.exception;

public class SaldoInsuficienteException extends RuntimeException {

    public SaldoInsuficienteException() {
        super("Saldo insuficiente.");
    }
}
