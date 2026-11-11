package com.senai.academiasul;

import java.util.ArrayList;
import java.util.List;

public class BancoDeDados {

    private static final BancoDeDados INSTANCIA = new BancoDeDados();

    private final List<Aluno> alunos = new ArrayList<>();
    private final List<Professor> professores = new ArrayList<>();
    private final List<Plano> planos = new ArrayList<>();
    private final List<Treino> treinos = new ArrayList<>();
    private final List<AssociacaoAlunoTreinos> associacoes = new ArrayList<>();

    private BancoDeDados() {
    }

    public static BancoDeDados getInstance() {
        return INSTANCIA;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public List<Plano> getPlanos() {
        return planos;
    }

    public List<Treino> getTreinos() {
        return treinos;
    }

    public List<AssociacaoAlunoTreinos> getAssociacoes() {
        return associacoes;
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public void adicionarProfessor(Professor professor) {
        professores.add(professor);
    }

    public void associarProfessor(Aluno aluno, Professor professor) {

        for (AssociacaoAlunoProfessor associacao : associacoesAlunosProfessores) {

            if (associacao.getAluno() == aluno) {
                associacao.setProfessor(professor);
                return;
            }
        }

        AssociacaoAlunoProfessor novaAssociacao =
                new AssociacaoAlunoProfessor(aluno, professor);

        associacoesAlunosProfessores.add(novaAssociacao);
    }

    public Professor getProfessorDoAluno(Aluno aluno) {

        for (AssociacaoAlunoProfessor associacao : associacoesAlunosProfessores) {

            if (associacao.getAluno() == aluno) {
                return associacao.getProfessor();
            }
        }

        return null;
    }

    public List<AssociacaoAlunoProfessor> getAssociacoesAlunosProfessores() {
        return associacoesAlunosProfessores;
    }

    public void adicionarPlano(Plano plano) {
        planos.add(plano);
    }

    public void adicionarTreino(Treino treino) {
        treinos.add(treino);
    }

    public void associarTreino(Aluno aluno, Treino treino) {

        for (AssociacaoAlunoTreinos associacao : associacoes) {

            if (associacao.getAluno() == aluno) {
                associacao.adicionarTreino(treino);
                return;
            }
        }

        AssociacaoAlunoTreinos novaAssociacao =
                new AssociacaoAlunoTreinos(aluno);

        novaAssociacao.adicionarTreino(treino);
        associacoes.add(novaAssociacao);
    }

    private final List<AssociacaoAlunoProfessor> associacoesAlunosProfessores = new ArrayList<>();

}
