package com.senai.academiasul;

public class Professor {

    private String nome;
    private String cpf;
    private String contato;
    private String endereco;

    public Professor(String nome, String cpf, String contato, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.contato = contato;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getContato() {
        return contato;
    }

    public String getEndereco() {
        return endereco;
    }

    @Override
    public String toString() {
        return nome;
    }
}