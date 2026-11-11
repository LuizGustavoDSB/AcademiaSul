package com.senai.academiasul;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CadastroAlunoController {
    @FXML private TextField campoNome;
    @FXML private TextField campoCpf;
    @FXML private TextField campoMatricula;
    @FXML private ComboBox<String> comboPlano;
    @FXML private Label mensagem;

    @FXML
    private void initialize() {
        comboPlano.getItems().setAll("Plano para família", "Plano anual", "Plano semanal", "Plano trimestral", "Plano bianual");
    }

    @FXML
    private void cadastrarAluno() {
        String nome = campoNome.getText().trim();
        String cpf = campoCpf.getText().trim();
        String matricula = campoMatricula.getText().trim();
        String tipoPlano = comboPlano.getValue();

        if (nome.isEmpty() || cpf.isEmpty() || matricula.isEmpty() || tipoPlano == null) {
            mensagem.setText("Preencha todos os campos e selecione um plano.");
            return;
        }

        boolean matriculaExiste = BancoDeDados.getInstance().getAlunos().stream().anyMatch(a -> a.getMatricula().equalsIgnoreCase(matricula));
        
        if (matriculaExiste) {
            mensagem.setText("Já existe um aluno com essa matrícula.");
            return;
        }

        Aluno aluno = new Aluno(nome, cpf, matricula, tipoPlano);
        BancoDeDados.getInstance().adicionarAluno(aluno);
        mensagem.setText("Aluno cadastrado com sucesso!\nNome: " + aluno.getNome() + "\nMatrícula: " + aluno.getMatricula() + "\nPlano: " + aluno.getTipoPlano());

        campoNome.clear();
        campoCpf.clear();
        campoMatricula.clear();
        comboPlano.getSelectionModel().clearSelection();
    }
}
