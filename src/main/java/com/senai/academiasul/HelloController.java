package com.senai.academiasul;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class HelloController {

    @FXML
    private void abrirAlunos() {
        abrirJanela("cadastro-aluno.fxml", "Cadastro de Alunos");
    }

    @FXML
    private void abrirProfessores() {
        abrirJanela(
                "/com/senai/academiasul/cadastrar-professor-maldito.fxml",
                "Cadastro de Professores");
    }

    @FXML
    private void abrirPlanos() {
        new CadastroDePlanos().abrirCadastroPlano();
    }

    @FXML
    private void abrirExercicios() {
        new CadastroDeExercicios().abrirCadastroExercicios();
    }

    @FXML
    private void abrirTreinos() {
        new TelaAssociacaoTreinos().abrirTela();
    }

    @FXML
    private void abrirAssociacaoTreinos() {
        new TelaAssociacaoTreinos().abrirTela();
    }

    private void abrirJanela(String arquivo, String titulo) {
        try {
            URL recurso = getClass().getResource(arquivo);
            if (recurso == null) {
                throw new IOException(
                        "Arquivo FXML não encontrado: " + arquivo
                );
            }

            FXMLLoader loader = new FXMLLoader(recurso);
            Parent tela = loader.load();
            Scene cena = new Scene(tela);

            URL css = HelloApplication.class.getResource("Style.css");

            if (css != null) {
                cena.getStylesheets().add(css.toExternalForm());
                System.out.println("CSS carregado!");
            } else {
                System.out.println("CSS NÃO ENCONTRADO!");
            }

            Stage janela = new Stage();
            janela.setTitle(titulo);
            janela.setMinWidth(420);
            janela.setMinHeight(420);
            janela.setScene(cena);
            janela.show();

        } catch (IOException | RuntimeException e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro ao abrir tela");
            alerta.setHeaderText("Não foi possível abrir " + titulo);

            alerta.setContentText(e.getMessage());
            alerta.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirAssociacaoProfessor() {
        new TelaAssociacaoProfessor().abrirTela();
    }

    @FXML
    private void abrirConsultas() {
        new TelaConsultas().abrirTela();
    }
}
