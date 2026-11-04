package com.senai.academiasul;

public class Plano {
    private String tipo;
    private double valor;

    public Plano(String tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getTipo() { return tipo; }
    public double getValor() { return valor; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setValor(double valor) { this.valor = valor; }

    @Override
    public String toString() {
        return tipo + " - R$ " + String.format(java.util.Locale.forLanguageTag("pt-BR"), "%.2f", valor);
    }
}
