package com.senai.academiasul;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaAssociacaoTreinos {

    public void abrirTela() {

        BancoDeDados banco = BancoDeDados.getInstance();

        Stage stage = new Stage();
        stage.setTitle("Associar Treinos aos Alunos");

        Label labelAluno = new Label("Selecione o aluno:");

        ComboBox<Aluno> comboAlunos = new ComboBox<>();
        comboAlunos.setItems(
                FXCollections.observableArrayList(banco.getAlunos())
        );
        
        comboAlunos.setPromptText("Escolha um aluno");
        comboAlunos.setMaxWidth(Double.MAX_VALUE);

        Label labelTreinos = new Label("Selecione os treinos:");

        ListView<Treino> listaTreinos = new ListView<>();
        listaTreinos.setItems(
                FXCollections.observableArrayList(banco.getTreinos())
        );

        listaTreinos.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );

        Button botaoAssociar = new Button("Associar Treinos");

        botaoAssociar.setOnAction(event -> {

            Aluno alunoSelecionado = comboAlunos.getValue();

            ObservableList<Treino> treinosSelecionados =
                    listaTreinos.getSelectionModel().getSelectedItems();

            if (alunoSelecionado == null) {
                mostrarAlerta(
                        Alert.AlertType.WARNING,
                        "Selecione um aluno."
                );
                return;
            }

            if (treinosSelecionados.isEmpty()) {
                mostrarAlerta(
                        Alert.AlertType.WARNING,
                        "Selecione pelo menos um treino."
                );
                return;
            }

            for (Treino treino : treinosSelecionados) {
                banco.associarTreino(alunoSelecionado, treino);
            }

            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Treinos associados ao aluno com sucesso!"
            );

            stage.close();
        });

        VBox layout = new VBox(
                12,
                labelAluno,
                comboAlunos,
                labelTreinos,
                listaTreinos,
                botaoAssociar
        );

        layout.setStyle(
                "-fx-padding: 22; -fx-background-color: #fff0f6;"
        );

        stage.setScene(new Scene(layout, 1400, 800));
        stage.show();
    }

    private void mostrarAlerta(
            Alert.AlertType tipo,
            String mensagem
    ){
        Alert alerta = new Alert(tipo);
        alerta.setTitle("Associação de Treinos");
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
