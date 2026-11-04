package com.senai.academiasul;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ProfessorMaldito {
    @FXML private TextField campoNomeProfessor;
    @FXML private TextField campoCpfProfessor;
    @FXML private TextField campoContatoProfessor;
    @FXML private TextField campoEnderecoProfessor;
    @FXML private Label mensagem;

    @FXML
    private void cadastrarProfessor() {
        String nome = campoNomeProfessor.getText().trim();
        String cpf = campoCpfProfessor.getText().trim();
        String contato = campoContatoProfessor.getText().trim();
        String endereco = campoEnderecoProfessor.getText().trim();

        if (nome.isEmpty() || cpf.isEmpty() || contato.isEmpty() || endereco.isEmpty()) {
            mensagem.setText("Preencha todos os campos!");
            return;
        }

        Professor professor = new Professor(nome, cpf, contato, endereco);
        BancoDeDados.getInstance().adicionarProfessor(professor);
        mensagem.setText("Professor cadastrado com sucesso!\nNome: " + professor.getNome());

        campoNomeProfessor.clear();
        campoCpfProfessor.clear();
        campoContatoProfessor.clear();
        campoEnderecoProfessor.clear();
    }
}
