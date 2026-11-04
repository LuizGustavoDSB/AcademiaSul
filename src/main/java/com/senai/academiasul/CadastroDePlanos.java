package com.senai.academiasul;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CadastroDePlanos {
    public void abrirCadastroPlano() {
        Stage stage = new Stage();
        stage.setTitle("Cadastro de Plano");

        Label labelPlano = new Label("Tipo de plano:");
        ComboBox<String> comboPlano = new ComboBox<>();
        comboPlano.getItems().addAll("Plano para família", "Plano anual", "Plano semanal", "Plano trimestral", "Plano bianual");
        comboPlano.setPromptText("Selecione o tipo de plano");
        comboPlano.setMaxWidth(Double.MAX_VALUE);

        Label labelValor = new Label("Valor do plano:");
        TextField valorField = new TextField();
        valorField.setPromptText("Digite o valor (ex.: 99,90)");

        Button salvarBtn = new Button("Salvar plano");
        salvarBtn.setOnAction(e -> {
            String tipo = comboPlano.getValue();
            String textoValor = valorField.getText().trim();
            if (tipo == null || textoValor.isEmpty()) {
                mostrarAlerta(Alert.AlertType.WARNING, "Preencha o tipo e o valor do plano.");
                return;
            }
            try {
                double valor = Double.parseDouble(textoValor.replace(",", "."));
                if (valor <= 0) {
                    mostrarAlerta(Alert.AlertType.WARNING, "O valor precisa ser maior que zero.");
                    return;
                }
                BancoDeDados.getInstance().adicionarPlano(new Plano(tipo, valor));
                mostrarAlerta(Alert.AlertType.INFORMATION, "Plano cadastrado: " + tipo + " - R$ " + String.format(java.util.Locale.forLanguageTag("pt-BR"), "%.2f", valor));stage.close();
            } catch (NumberFormatException ex) {
                mostrarAlerta(Alert.AlertType.WARNING, "Digite um valor válido, por exemplo 99,90.");
            }
        });

        VBox layout = new VBox(12, labelPlano, comboPlano, labelValor, valorField, salvarBtn);
        layout.setStyle("-fx-padding: 22; -fx-background-color: #fff0f6;");
        stage.setScene(new Scene(layout, 1400, 800));
        stage.show();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String mensagem) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle("Cadastro de plano");
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
