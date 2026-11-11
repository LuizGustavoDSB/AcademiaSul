package com.senai.academiasul;

import java.util.ArrayList;
import java.util.List;

public class AssociacaoAlunoTreinos {

    private Aluno aluno;
    private final List<Treino> treinos = new ArrayList<>();

    public AssociacaoAlunoTreinos(Aluno aluno) {
        this.aluno = aluno;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public List<Treino> getTreinos() {
        return treinos;
    }

    public void adicionarTreino(Treino treino) {
        treinos.add(treino);
    }
}
