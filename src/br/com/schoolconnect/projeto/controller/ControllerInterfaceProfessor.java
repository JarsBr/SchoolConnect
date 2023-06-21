package br.com.schoolconnect.projeto.controller;

import java.io.IOException;
import br.com.schoolconnect.projeto.model.Global;
import br.com.schoolconnect.projeto.dao.AlunoDAO;
import br.com.schoolconnect.projeto.dao.DisplinaDAO;
import br.com.schoolconnect.projeto.database.DbUtils;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.util.Callback;
import javafx.util.Duration;


import javax.swing.JOptionPane;


public class ControllerInterfaceProfessor {


	@FXML
	private Label alunosTela;

	@FXML
	private Button atribuirNota;

	@FXML
	private Button cadastroDados;

	@FXML
	private Button cadastroDisciplina;

	@FXML
	private Button cadastroNotas;

	@FXML
	private Button consultaAluno;

	@FXML
	private Button consultaDisciplina;

	@FXML
	private TextArea curriculo;

	@FXML
	private Label dadosTela;

	@FXML
	private Label data_inicio;

	@FXML
	private Label disciplinaTela;

	@FXML
	private Label email;

	@FXML
	private Label emailAluno;

	@FXML
	private Button fechaPanelConsultaAluno;

	@FXML
	private Button fechaPanelDadosDisciplina;

	@FXML
	private TextArea graus_academicos;

	@FXML
	private Label lbl_carga_horaria;

	@FXML
	private Label lbl_cod_disciplina;

	@FXML
	private Label lbl_conteudo;

	@FXML
	private Label lbl_curriculo;

	@FXML
	private Label lbl_descricao;

	@FXML
	private Label lbl_graus_academicos;

	@FXML
	private Label lbl_nomeDisciplina;

	@FXML
	private ListView<String> listAluno;

	@FXML
	private ListView<String> listAlunoNotas;

	@FXML
	private ListView<String> listDisciplina;

	@FXML
	private ListView<String> listDisciplinaNotas;

	@FXML
	private Label matricula;

	@FXML
	private Label matriculaAluno;

	@FXML
	private Label nome;

	@FXML
	private Label nomeAluno;

	@FXML
	private TextArea nota;

	@FXML
	private Label notasTela;

	@FXML
	private Pane panel;

	@FXML
	private Pane panelAlunos;

	@FXML
	private Pane panelCadastroDados;

	@FXML
	private Pane panelCadastroDisciplina;

	@FXML
	private Pane panelCadastroNota;

	@FXML
	private Pane panelDados;

	@FXML
	private Pane panelDadosAluno;

	@FXML
	private Pane panelDadosDisciplina;

	@FXML
	private Pane panelDisciplina;

	@FXML
	private Pane panelNotas;

	@FXML
	private Button sair;

	@FXML
	private Label situacao;

	@FXML
	private TextArea tf_carga_horaria;

	@FXML
	private TextArea tf_cod_disciplina;

	@FXML
	private TextArea tf_conteudo;

	@FXML
	private TextArea tf_descricao;

	@FXML
	private TextArea tf_nome;

	@FXML
	private ImageView welcome;

	@FXML
	void button_sair(ActionEvent event) {
		Stage currentStage = (Stage) sair.getScene().getWindow();
		currentStage.close();

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MenuPrincipal.fxml"));
			Parent root = loader.load();

			Stage newStage = new Stage();
			newStage.setScene(new Scene(root));
			newStage.show();
		} catch (IOException e) {
			System.out.println("Erro ao carregar a tela de menu");
		}
	}

	@FXML
	void cadastroNotas(ActionEvent event) {
		panelCadastroNota.setVisible(true);
		ObservableList<String> itemsBoletim = FXCollections.observableArrayList(DisplinaDAO.pegarDisciplinasNotas());

		// Adiciona o cabeçalho
		itemsBoletim.add(0, "Nota\t\tCodigo\t\t\tDisciplina");

		listAlunoNotas.setItems(itemsBoletim);

		listAlunoNotas.setOnMouseClicked(e -> {
			if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 1) {
				int selectedIndex = listAlunoNotas.getSelectionModel().getSelectedIndex();
				if (selectedIndex > 0) {
					String selectedAluno = listAlunoNotas.getItems().get(selectedIndex);
					System.out.println(selectedAluno); // Imprime a linha selecionada no console
					Global.alunoNotaSelecionado = AlunoDAO.getAlunoFromSelectedString(selectedAluno);

					String campoInformacao = nota.getText();
					if (!campoInformacao.isEmpty()) {
						float notaNova = Float.parseFloat(campoInformacao);
						DisplinaDAO.alterarNota(selectedAluno, notaNova);
					}else {
						JOptionPane.showMessageDialog(null, "Insira uma nota para ser atribuida");

					}

				}
			}
		});

		// Define a célula personalizada para exibir os itens do ListView
		listAlunoNotas.setCellFactory((Callback<ListView<String>, ListCell<String>>) new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> listView) {
				return new ListCell<String>() {
					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						setText(item);
						if (getIndex() == 0) {
							setStyle("-fx-font-weight: bold"); // Estilo para o cabeçalho
						}
					}
				};
			}
		});
	}

	@FXML
	void atribuirNota(ActionEvent event) {
		panelCadastroNota.setVisible(false);
	}

	@FXML
	void fechaPanelConsultaAluno(ActionEvent event) {
		panelDadosAluno.setVisible(false);
	}

	@FXML
	void consultaAluno(ActionEvent event) {
		panelDadosAluno.setVisible(true);
		matriculaAluno.setText(Global.alunoSelecionado.getMatricula());
		nomeAluno.setText(Global.alunoSelecionado.getNome());
		emailAluno.setText(Global.alunoSelecionado.getEmail());
		data_inicio.setText(Global.alunoSelecionado.getData_inicio());
		situacao.setText(Global.alunoSelecionado.getSituacao());
	}

	@FXML
	void cadastroDisciplina(ActionEvent event) {
		if (panelCadastroDisciplina.isVisible()) {
			String codDisciplina = tf_cod_disciplina.getText();
			String nome = tf_nome.getText();
			String descricao = tf_descricao.getText();
			String cargaHoraria = tf_carga_horaria.getText();
			String conteudo = tf_conteudo.getText();

			if (codDisciplina.isEmpty() || nome.isEmpty() || descricao.isEmpty() || cargaHoraria.isEmpty() || conteudo.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos.");
				return;
			}

			DbUtils.salvarDisciplina(codDisciplina, nome, descricao, cargaHoraria, conteudo);
			DbUtils.salvarDisciplinaOfertada("11" + codDisciplina, Global.professor.getMatricula(), codDisciplina);
			panelCadastroDisciplina.setVisible(false);
		} else {
			panelCadastroDisciplina.setVisible(true);
		}
	}

	@FXML
	void consultaDisciplina(ActionEvent event) {
		panelDadosDisciplina.setVisible(true);
		lbl_cod_disciplina.setText(Global.disciplinaSelecionada.getCodDisciplina());
		lbl_nomeDisciplina.setText(Global.disciplinaSelecionada.getNome());
		lbl_descricao.setText(Global.disciplinaSelecionada.getDescricao());
		lbl_conteudo.setText(Global.disciplinaSelecionada.getConteudo());
		lbl_carga_horaria.setText(Global.disciplinaSelecionada.getCargaHoraria());
	}

	@FXML
	void fechaPanelDadosDisciplina(ActionEvent event) {
		panelDadosDisciplina.setVisible(false);
	}

	@FXML
	void cadastroDados(ActionEvent event) {
		if (panelCadastroDados.isVisible()) {
			DbUtils.atualizarDados(Global.professor.getMatricula(), graus_academicos.getText(), curriculo.getText());
			panelCadastroDados.setVisible(false);
		} else {
			panelCadastroDados.setVisible(true);
		}
	}

	@FXML
	void telaClicada(MouseEvent event) {
		Label labelClicado = (Label) event.getSource();

		panelDados.setVisible(labelClicado == dadosTela);

		matricula.setText(Global.professor.getMatricula());
		nome.setText(Global.professor.getNome());
		email.setText(Global.professor.getEmail());
		graus_academicos.setText(Global.professor.getGraus());
		lbl_graus_academicos.setText(Global.professor.getGraus());
		curriculo.setText(Global.professor.getCurriculo());
		lbl_curriculo.setText(Global.professor.getCurriculo());


		panelAlunos.setVisible(labelClicado == alunosTela);

		ObservableList<String> itemsAlunos = FXCollections.observableArrayList(AlunoDAO.pegarTodosAlunos());

		// Adiciona o cabeçalho
		itemsAlunos.add(0, "Matricula\t Nome\t\t\tEmail");

		listAluno.setItems(itemsAlunos);
		listAluno.setOnMouseClicked(e -> {
			if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 1) {
				int selectedIndex = listAluno.getSelectionModel().getSelectedIndex();
				if (selectedIndex > 0) {
					String selectedAluno = listAluno.getItems().get(selectedIndex);
					//System.out.println(selectedAluno);
					Global.alunoSelecionado = AlunoDAO.getAlunoFromSelectedString(selectedAluno);
					//System.out.println(Global.alunoSelecionado);

					// Agora você pode salvar as informações do aluno na variável Global.alunoSelecionado
				}
			}
		});
		// Define a célula personalizada para exibir os itens do ListView
		listAluno.setCellFactory((Callback<ListView<String>, ListCell<String>>) new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> listView) {
				return new ListCell<String>() {
					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						setText(item);
						if (getIndex() == 0) {
							setStyle("-fx-font-weight: bold"); // Estilo para o cabeçalho
						}
					}
				};
			}
		});


		panelNotas.setVisible(labelClicado == notasTela);


		ObservableList<String> itemsBoletim = FXCollections.observableArrayList(DisplinaDAO.pegarDisciplinasNotas());

		// Adiciona o cabeçalho
		itemsBoletim.add(0, "Codigo\t\tAluno\t\t\tDisciplina\t\t\tNota");

		listDisciplinaNotas.setItems(itemsBoletim);

		// Define a célula personalizada para exibir os itens do ListView
		listDisciplinaNotas.setCellFactory((Callback<ListView<String>, ListCell<String>>) new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> listView) {
				return new ListCell<String>() {
					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						setText(item);
						if (getIndex() == 0) {
							setStyle("-fx-font-weight: bold"); // Estilo para o cabeçalho
						}
					}
				};
			}
		});


		panelDisciplina.setVisible(labelClicado == disciplinaTela);

		ObservableList<String> itemsDisciplina = FXCollections.observableArrayList(DisplinaDAO.pegarDisciplinasProfessor());

		// Adiciona o cabeçalho
		itemsDisciplina.add(0, "Codigo\t\tNome\t\t\t\tDescrição");

		listDisciplina.setItems(itemsDisciplina);

		listDisciplina.setOnMouseClicked(e -> {
			if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 1) {
				int selectedIndex = listDisciplina.getSelectionModel().getSelectedIndex();
				if (selectedIndex > 0) {
					String selectedDisciplina = listDisciplina.getItems().get(selectedIndex);
					//System.out.println(selectedDisciplina);
					Global.disciplinaSelecionada = DisplinaDAO.getDisciplinaFromSelectedString(selectedDisciplina);
					//System.out.println(Global.disciplinaSelecionada);

					// Agora você pode salvar as informações da disciplina na variável Global.disciplinaSelecionada
				}
			}
		});
		// Define a célula personalizada para exibir os itens do ListView
		listDisciplina.setCellFactory((Callback<ListView<String>, ListCell<String>>) new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> listView) {
				return new ListCell<String>() {
					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						setText(item);
						if (getIndex() == 0) {
							setStyle("-fx-font-weight: bold"); // Estilo para o cabeçalho
						}
					}
				};
			}
		});


	}

	@FXML
	void tableHorario(ActionEvent event) {

	}

	// animações bacanas

	public void initialize() {
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.ZERO, new KeyValue(welcome.translateYProperty(), 0)),
				new KeyFrame(Duration.seconds(3), new KeyValue(welcome.translateYProperty(), -50))
				);
		timeline.setAutoReverse(true);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();

	}

	@FXML
	private void labelMousePressed(MouseEvent event) {
		Label label = (Label) event.getSource();
		TranslateTransition transition = new TranslateTransition(Duration.seconds(0.2), label);
		transition.setToY(5); // Define o deslocamento vertical
		transition.play();
	}

	@FXML
	private void labelMouseReleased(MouseEvent event) {
		Label label = (Label) event.getSource();
		TranslateTransition transition = new TranslateTransition(Duration.seconds(0.2), label);
		transition.setToY(0); // Retorna à posição original
		transition.play();
	}


}
