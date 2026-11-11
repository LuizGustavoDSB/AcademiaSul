package com.senai.academiasul;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;

public class TelaAssociacaoProfessor {

    public void abrirTela() {

        BancoDeDados banco = BancoDeDados.getInstance();

        Stage stage = new Stage();
        stage.setTitle("Associar Aluno e Professor");

        Label titulo = new Label("Professor responsável");
        titulo.getStyleClass().add("titulo-associacao");

        Label labelAluno = new Label("Selecione o aluno:");

        ComboBox<Aluno> comboAlunos = new ComboBox<>();
        comboAlunos.setItems(
                FXCollections.observableArrayList(banco.getAlunos())
        );
        comboAlunos.setPromptText("Escolha um aluno");
        comboAlunos.setMaxWidth(Double.MAX_VALUE);
        comboAlunos.getStyleClass().add("campo-associacao");

        Label labelProfessor = new Label("Selecione o professor:");

        ComboBox<Professor> comboProfessores = new ComboBox<>();
        comboProfessores.setItems(
                FXCollections.observableArrayList(banco.getProfessores())
        );
        
        comboProfessores.setPromptText("Escolha um professor");
        comboProfessores.setMaxWidth(Double.MAX_VALUE);
        comboProfessores.getStyleClass().add("campo-associacao");

        Button botaoAssociar = new Button("Associar Professor");
        botaoAssociar.getStyleClass().add("botao-associacao");

        botaoAssociar.setOnAction(event -> {

            Aluno aluno = comboAlunos.getValue();
            Professor professor = comboProfessores.getValue();

            if (aluno == null || professor == null) {
                mostrarAlerta(
                        Alert.AlertType.WARNING,
                        "Selecione um aluno e um professor."
                );
                return;
            }
            
            banco.associarProfessor(aluno, professor);

            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Professor associado ao aluno com sucesso!"
            );

            stage.close();
        });

        VBox layout = new VBox(
                15,
                titulo,
                labelAluno,
                comboAlunos,
                labelProfessor,
                comboProfessores,
                botaoAssociar
        );

        layout.getStyleClass().add("tela-associacao");

        Scene cena = new Scene(layout, 1400, 800);

        URL css = getClass().getResource("Style.css");

        if (css != null) {
            cena.getStylesheets().add(css.toExternalForm());
        }

        stage.setScene(cena);
        stage.show();
    }

    private void mostrarAlerta(
            Alert.AlertType tipo,
            String mensagem
    ){
        Alert alerta = new Alert(tipo);
        alerta.setTitle("Associação de Professor");
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
