package com.senai.academiasul;

public class Aluno {
    private String nome;
    private String cpf;
    private String matricula;
    private String tipoPlano;

    public Aluno(String nome, String cpf, String matricula, String tipoPlano) {
        this.nome = nome;
        this.cpf = cpf;
        this.matricula = matricula;
        this.tipoPlano = tipoPlano;
    }

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getCpf() {
        return cpf;
    }
    
    public void setCpf(String cpf){
        this.cpf = cpf;
    }
    
    public String getMatricula(){
        return matricula;
    }
    
    public void setMatricula(String matricula){
        this.matricula = matricula;
    }
    
    public String getTipoPlano() {
        return tipoPlano;
    }
    
    public void setTipoPlano(String tipoPlano) {
        this.tipoPlano = tipoPlano;
    }

    @Override
    public String toString() {
        return nome + " (matrícula: " + matricula + ")";
    }
}
