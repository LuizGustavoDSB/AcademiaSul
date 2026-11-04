
package com.senai.academiasul;

public class AssociacaoAlunoProfessor {

    private Aluno aluno;
    private Professor professor;

    public AssociacaoAlunoProfessor(Aluno aluno, Professor professor) {
        this.aluno = aluno;
        this.professor = professor;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
