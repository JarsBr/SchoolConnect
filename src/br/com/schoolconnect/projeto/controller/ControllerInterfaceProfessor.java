package br.com.schoolconnect.projeto.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.schoolconnect.projeto.model.Global;
import br.com.schoolconnect.projeto.model.Aluno;
import br.com.schoolconnect.projeto.model.Disciplina;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.image.ImageView;
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
import javafx.scene.layout.VBox;
import java.util.ArrayList;


public class ControllerInterfaceProfessor {

	@FXML
	private Label alunosTela;

	@FXML
	private Button cadastroDados;

	@FXML
	private Button cadastroDisciplina;

	@FXML
	private Button consultaAluno;

	@FXML
	private Label curriculo;

	@FXML
	private Label dadosTela;

	@FXML
	private Label disciplinaTela;

	@FXML
	private Label email;

	@FXML
	private Label graus_academicos;

	@FXML
	private Label horarioTela;

	@FXML
	private ListView<String> listAluno;

	@FXML
	private ListView<String> listDisciplina;

	@FXML
	private Label matricula;

	@FXML
	private Label nome;

	@FXML
	private Label notasTela;

	@FXML
	private Pane panel;

	@FXML
	private Pane panelAlunos;

	@FXML
	private Pane panelAlunos1;

	@FXML
	private Pane panelDados;

	@FXML
	private Pane panelDisciplina;

	@FXML
	private Pane panelHorario;

	@FXML
	private Pane panelNotas;

	@FXML
	private Button removerDisciplina;

	@FXML
	private Button sair;

	@FXML
	private TableView<?> tableHorario;

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
	void cadastroDados(ActionEvent event) {

	}

	@FXML
	void cadastroDisciplina(ActionEvent event) {

	}

	@FXML
	void removerDisciplina(ActionEvent event) {

	}

	@FXML
	void consultaAluno(ActionEvent event) {

	}

	@FXML
	void telaClicada(MouseEvent event) {
		Label labelClicado = (Label) event.getSource();

		panelDados.setVisible(labelClicado == dadosTela);

		matricula.setText(Global.professor.getMatricula());
		nome.setText(Global.professor.getNome());
		email.setText(Global.professor.getEmail());
		graus_academicos.setText(Global.professor.getGraus());
		curriculo.setText(Global.professor.getCurriculo());


		panelAlunos.setVisible(labelClicado == alunosTela);

		ObservableList<String> itemsAlunos = FXCollections.observableArrayList(pegarTodosAlunos());



		// Adiciona o cabeçalho
		itemsAlunos.add(0, "Matricula\tNome\tEmail");

		listAluno.setItems(itemsAlunos);

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
		panelHorario.setVisible(labelClicado == horarioTela);
		panelDisciplina.setVisible(labelClicado == disciplinaTela);

		ObservableList<String> itemsDisciplina = FXCollections.observableArrayList(pegarDisciplinasProfessor());

		// Adiciona o cabeçalho
		itemsDisciplina.add(0, "Codigo-Disciplina\tNome\tDescrição");

		listDisciplina.setItems(itemsDisciplina);

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

	public ArrayList<String> pegarTodosAlunos() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		// Criar uma ArrayList para armazenar os alunos
		ArrayList<String> listaAlunos = new ArrayList<>();
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/connectschool", "root", "");

			// Aqui fiz uma subquery para funcionar a verificação da tabela aluno e professor ao mesmo tempo

			// Recuperar as informações dos alunos do banco de dados
			String query = "SELECT matricula, nome, email, id_curso_periodo, data_inicio, situacao FROM aluno";
			preparedStatement = connection.prepareStatement(query);
			ResultSet alunoResultSet = preparedStatement.executeQuery();

			// Percorrer os resultados do banco de dados
			while (alunoResultSet.next()) {
				Aluno aluno = new Aluno();
				aluno.setMatricula(alunoResultSet.getString("matricula"));
				aluno.setNome(alunoResultSet.getString("nome"));
				aluno.setEmail(alunoResultSet.getString("email"));
				aluno.setId_curso_periodo(alunoResultSet.getString("id_curso_periodo"));
				aluno.setData_inicio(alunoResultSet.getString("data_inicio"));
				aluno.setSituacao(alunoResultSet.getString("situacao"));

				// Adicionar o aluno à lista
				listaAlunos.add(aluno.toString());
			}

			// Agora, a listaAlunos contém todos os alunos do banco de dados
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return listaAlunos;

	}

	public ArrayList<String> pegarDisciplinasProfessor() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		// Criar uma ArrayList para armazenar os alunos
		ArrayList<String> listaDisciplina = new ArrayList<>();
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/connectschool", "root", "");


			String query = "SELECT cod_disciplina, nome, descricao, conteudo, carga_horaria FROM disciplina WHERE cod_disciplina IN (SELECT cod_disciplina FROM disciplina_ofertada WHERE matricula_professor = ?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, Global.professor.getMatricula());
			ResultSet disciplinaResultSet = preparedStatement.executeQuery();

			// Percorrer os resultados do banco de dados
			while (disciplinaResultSet.next()) {
			    Disciplina disciplina = new Disciplina();
			    disciplina.setCodDisciplina(disciplinaResultSet.getString("cod_disciplina"));
			    disciplina.setNome(disciplinaResultSet.getString("nome"));
			    disciplina.setDescricao(disciplinaResultSet.getString("descricao"));
			    disciplina.setConteudo(disciplinaResultSet.getString("conteudo"));

			    // Adicionar o disciplina à lista
			    listaDisciplina.add(disciplina.toString());
			}


			// Agora, a listaAlunos contém todos os alunos do banco de dados
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return listaDisciplina;

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
