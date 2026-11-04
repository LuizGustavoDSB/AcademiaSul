package com.senai.academiasul;

import java.util.Locale;

public class Treino {

    private String tipo;
    private double valor;

    public Treino(String tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return tipo + " - R$ " +
                String.format(Locale.forLanguageTag("pt-BR"), "%.2f", valor);
    }
}
