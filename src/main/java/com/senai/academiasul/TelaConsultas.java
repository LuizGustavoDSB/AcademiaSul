package com.senai.academiasul;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Locale;
import java.util.stream.Collectors;

public class TelaConsultas {

    private final BancoDeDados banco = BancoDeDados.getInstance();

    private ListView<Aluno> listaAlunos;
    private Label tituloDetalhe;
    private Label conteudoDetalhe;
    private Aluno alunoSelecionado;

    public void abrirTela() {

        Stage stage = new Stage();
        stage.setTitle("Consultas - Academia Sul");

        Label titulo = new Label("Consulta de alunos");
        titulo.getStyleClass().add("titulo-menu");

        listaAlunos = new ListView<>();

        listaAlunos.setItems(
                FXCollections.observableArrayList(
                        banco.getAlunos()
                )
        );

        listaAlunos.setPrefWidth(300);

        listaAlunos.setPlaceholder(
                new Label("Nenhum aluno cadastrado.")
        );

        Label instrucao = new Label(
                "Selecione um aluno para consultar:"
        );

        instrucao.getStyleClass().add("subtitulo-menu");

        VBox painelAlunos = new VBox(
                12,
                instrucao,
                listaAlunos
        );

        painelAlunos.setPadding(new Insets(18));
        painelAlunos.setPrefWidth(330);

        tituloDetalhe = new Label("Selecione um aluno");

        tituloDetalhe.getStyleClass().add("titulo-associacao");

        conteudoDetalhe = new Label(
                "As informações do aluno aparecerão aqui."
        );

        conteudoDetalhe.setWrapText(true);

        conteudoDetalhe.setStyle(
                "-fx-font-size: 15px;" +
                        "-fx-line-spacing: 5px;"
        );

        VBox painelDetalhes = new VBox(
                18,
                tituloDetalhe,
                conteudoDetalhe
        );

        painelDetalhes.setPadding(new Insets(24));

        painelDetalhes.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 12px;" +
                        "-fx-border-color: #f3c4d6;" +
                        "-fx-border-radius: 12px;"
        );

        HBox.setHgrow(
                painelDetalhes,
                Priority.ALWAYS
        );
        Button btnProfessor =
                criarBotao("Ver professor responsável");

        Button btnTreinos =
                criarBotao("Ver seus treinos");

        Button btnPlano =
                criarBotao("Ver seu plano");

        Button btnResumo =
                criarBotao("Resumo completo");

        VBox painelBotoes = new VBox(13, new Label("CONSULTAR"),
                btnProfessor,
                btnTreinos,
                btnPlano,
                btnResumo
        );

        painelBotoes.setPadding(new Insets(18));
        painelBotoes.setPrefWidth(245);

        painelBotoes.setStyle(
                "-fx-background-color: #fff0f6;"
        );

        btnProfessor.setOnAction(e -> mostrarProfessor());

        btnTreinos.setOnAction(e -> mostrarTreinos());

        btnPlano.setOnAction(e -> mostrarPlano());

        btnResumo.setOnAction(e -> mostrarResumo());

        listaAlunos.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, anterior, atual) -> {

                    alunoSelecionado = atual;

                    if (atual != null) {
                        tituloDetalhe.setText(atual.getNome());
                        mostrarResumo();
                    }
                });

        BorderPane raiz = new BorderPane();

        raiz.setTop(titulo);
        BorderPane.setMargin(
                titulo, new Insets(20, 20, 8, 20)
        );

        raiz.setLeft(painelAlunos);
        raiz.setCenter(painelDetalhes);
        raiz.setRight(painelBotoes);

        raiz.setStyle("-fx-background-color: #fff5f8;");

        Scene cena = new Scene(raiz, 1400, 800);

        URL css = getClass().getResource("Style.css");

        if (css != null) {
            cena.getStylesheets().add(
                    css.toExternalForm()
            );
        }

        stage.setMinWidth(850);
        stage.setMinHeight(520);

        stage.setScene(cena);
        stage.show();

        if (!listaAlunos.getItems().isEmpty()) {
            listaAlunos.getSelectionModel().selectFirst();
        }
    }

    private Button criarBotao(String texto) {

        Button botao = new Button(texto);

        botao.setMaxWidth(Double.MAX_VALUE);
        botao.setWrapText(true);

        return botao;
    }

    private boolean verificarAluno() {

        if (alunoSelecionado != null) {
            return true;
        }

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);

        alerta.setTitle("Consulta de alunos");
        alerta.setHeaderText(null);

        alerta.setContentText("Selecione um aluno na lista primeiro.");

        alerta.showAndWait();

        return false;
    }

    private void mostrarProfessor() {

        if (!verificarAluno()) return;

        Professor professor = banco.getProfessorDoAluno(alunoSelecionado);

        if (professor == null) {

            exibir("Professor responsável", "Nenhum professor foi associado a este aluno ainda.");

        } else {

            exibir("Professor responsável",

                    "Nome: " + professor.getNome() +
                            "\nContato: " + professor.getContato() +
                            "\nCPF: " + professor.getCpf() +
                            "\nEndereço: " + professor.getEndereco()
            );
        }
    }

    private void mostrarTreinos() {

        if (!verificarAluno()) return;

        AssociacaoAlunoTreinos associacao =
                banco.getAssociacoes().stream()

                        .filter(a -> a.getAluno() == alunoSelecionado)

                        .findFirst()
                        .orElse(null);

        if (associacao == null || associacao.getTreinos().isEmpty()) {

            exibir("Treinos", "Nenhum treino foi associado a este aluno ainda.");

            return;
        }

        String texto = associacao.getTreinos().stream()

                .map(t -> "• " + t.getTipo() + " — " + formatarValor(t.getValor()))

                .collect(Collectors.joining("\n"));

        exibir("Treinos do aluno", texto);
    }

    private void mostrarPlano() {

        if (!verificarAluno()) return;

        String tipo = alunoSelecionado.getTipoPlano();

        Plano plano = banco.getPlanos().stream()

                .filter(p ->
                        p.getTipo().equalsIgnoreCase(tipo)
                )

                .findFirst()
                .orElse(null);

        String texto = "Plano: " + tipo;

        if (plano != null) {

            texto += "\nValor: " + formatarValor(plano.getValor());

        } else {

            texto += "\nValor: não informado no cadastro de planos.";
        }

        exibir("Plano do aluno", texto);
    }

    private void mostrarResumo() {

        if (!verificarAluno()) return;

        Professor professor =
                banco.getProfessorDoAluno(alunoSelecionado);

        AssociacaoAlunoTreinos associacao =
                banco.getAssociacoes().stream()

                        .filter(a ->
                                a.getAluno() == alunoSelecionado
                        )

                        .findFirst()
                        .orElse(null);

        Plano plano = banco.getPlanos().stream()

                .filter(p ->
                        p.getTipo().equalsIgnoreCase(
                                alunoSelecionado.getTipoPlano()
                        )
                )

                .findFirst()
                .orElse(null);

        String treinos;

        if (associacao == null ||
                associacao.getTreinos().isEmpty()) {

            treinos = "Nenhum treino associado";

        } else {

            treinos = associacao.getTreinos().stream()

                    .map(t -> "• " + t.getTipo() + " (" + formatarValor(t.getValor()) + ")"
                    )

                    .collect(Collectors.joining("\n"));
        }

        String texto =

                "DADOS DO ALUNO\n" +

                        "Nome: " + alunoSelecionado.getNome() + "\n" +

                        "Matrícula: " +
                        alunoSelecionado.getMatricula() + "\n" +

                        "CPF: " +
                        alunoSelecionado.getCpf() + "\n\n" +

                        "PROFESSOR RESPONSÁVEL\n" +

                        (professor == null
                                ? "Não associado"
                                : professor.getNome() +
                                " — " + professor.getContato()) +

                        "\n\nPLANO\n" +

                        alunoSelecionado.getTipoPlano() +

                        (plano == null
                                ? " (valor não cadastrado)"
                                : " — " + formatarValor(plano.getValor())) +

                        "\n\nTREINOS\n" +

                        treinos;

        exibir("Resumo completo", texto);
    }

    private void exibir(String titulo, String texto) {

        tituloDetalhe.setText(titulo);
        conteudoDetalhe.setText(texto);
    }

    private String formatarValor(double valor) {

        return "R$ " + String.format(Locale.forLanguageTag("pt-BR"), "%.2f", valor);
    }
}