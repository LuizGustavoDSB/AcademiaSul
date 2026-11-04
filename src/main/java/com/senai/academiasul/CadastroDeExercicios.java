package com.senai.academiasul;


import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class CadastroDeExercicios {


    public void abrirCadastroExercicios() {
        Stage stage = new Stage();
        stage.setTitle("Cadastro de Exercicios");


        Label labelExercicio = new Label("Tipo de Exercicio:");
        ComboBox<String> comboExercicio = new ComboBox<>();
        comboExercicio.getItems().addAll("Peito: Supino, remada, agachamento livre.", "Tríceps: Rosca direta, agachamento livre.", "Costas: Puxadas verticais, remadas.", "Glúteos: Agachamento livre, remada, inclinação lateral.", "Pernas: Agachamento livre, remada, inclinação lateral.");
        comboExercicio.setPromptText("Selecione o tipo de Exercicio");
        comboExercicio.setMaxWidth(Double.MAX_VALUE);


        Label labelValor = new Label("Valor do Exercicio:");
        TextField valorField = new TextField();
        valorField.setPromptText("Digite o valor (ex.: 99,90)");


        Button salvarBtn = new Button("Salvar Exercicios");
        salvarBtn.setOnAction(e -> {
            String tipo = comboExercicio.getValue();
            String textoValor = valorField.getText().trim();
            if (tipo == null || textoValor.isEmpty()) {
                mostrarAlerta(Alert.AlertType.WARNING, "Preencha o tipo e o valor do exercicio.");
                return;
            }
            try {
                double valor = Double.parseDouble(textoValor.replace(",", "."));
                if (valor <= 0) {
                    mostrarAlerta(Alert.AlertType.WARNING, "O valor precisa ser maior que zero.");
                    return;
                }
                BancoDeDados.getInstance().adicionarTreino(new Treino(tipo, valor));
                mostrarAlerta(Alert.AlertType.INFORMATION, "exercicio cadastrado: " + tipo + " - R$ " + String.format(java.util.Locale.forLanguageTag("pt-BR"), "%.2f", valor));stage.close();
            } catch (NumberFormatException ex) {
                mostrarAlerta(Alert.AlertType.WARNING, "Digite um valor válido, por exemplo 99,90.");
            }
        });

        VBox layout = new VBox(12, labelExercicio, comboExercicio, labelValor, valorField, salvarBtn);
        layout.setStyle("-fx-padding: 22; -fx-background-color: #fff0f6;");
        stage.setScene(new Scene(layout, 1400, 800));
        stage.show();
    }
    private void mostrarAlerta(Alert.AlertType tipo, String mensagem) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle("Cadastro de exercicio");
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}