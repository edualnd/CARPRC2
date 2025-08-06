package com.eduarda.bancoAula01.exception;

public class SaldoInsuficienteException extends RuntimeException {

    public SaldoInsuficienteException() {
        super("Saldo insuficiente.");
    }
}
