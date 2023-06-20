package br.com.schoolconnect.projeto.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.schoolconnect.projeto.model.Aluno;
import br.com.schoolconnect.projeto.model.Disciplina;
import br.com.schoolconnect.projeto.model.Global;
import br.com.schoolconnect.projeto.model.Professor;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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


public class ControllerInterfaceAluno {

	  @FXML
	    private Label boletimTela;

	    @FXML
	    private Button consultaDisciplina;

	    @FXML
	    private Button consultaProfessor;

	    @FXML
	    private Label curriculo;

	    @FXML
	    private Label dadosTela;

	    @FXML
	    private Label data_inicio;

	    @FXML
	    private Label disciplinaTela;

	    @FXML
	    private Label email;

	    @FXML
	    private Label emailProfessor;

	    @FXML
	    private Button fechaPanelConsultaDisciplina;

	    @FXML
	    private Button fechaPanelConsultaProfessores;

	    @FXML
	    private Label graus_academicos;

	    @FXML
	    private Label lbl_carga_horaria;

	    @FXML
	    private Label lbl_cod_disciplina;

	    @FXML
	    private Label lbl_conteudo;

	    @FXML
	    private Label lbl_descricao;

	    @FXML
	    private Label lbl_nomeDisciplina;

	    @FXML
	    private ListView<String> listBoletim;

	    @FXML
	    private ListView<String> listDisciplina;

	    @FXML
	    private ListView<String> listProfessor;

	    @FXML
	    private Label matricula;

	    @FXML
	    private Label matriculaProfessor;

	    @FXML
	    private Button matricularDisciplina;

	    @FXML
	    private Label nome;

	    @FXML
	    private Label nomeProfessor;

	    @FXML
	    private Pane panel;

	    @FXML
	    private Pane panelBoletim;

	    @FXML
	    private Pane panelDados;

	    @FXML
	    private Pane panelDadosDisciplina;

	    @FXML
	    private Pane panelDadosProfessores;

	    @FXML
	    private Pane panelDisciplina;

	    @FXML
	    private Pane panelProfessores;

	    @FXML
	    private Label professoresTela;

	    @FXML
	    private Button sair;

	    @FXML
	    private Label situacao;

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
	void fechaPanelConsultaProfessores(ActionEvent event) {
	    panelDadosProfessores.setVisible(false);
	}

	@FXML
	void fechaPanelConsultaDisciplina(ActionEvent event) {
	    panelDadosDisciplina.setVisible(false);
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
	void consultaProfessor(ActionEvent event) {
	    panelDadosProfessores.setVisible(true);
	    matriculaProfessor.setText(Global.professorSelecionado.getMatricula());
	    nomeProfessor.setText(Global.professorSelecionado.getNome());
	    emailProfessor.setText(Global.professorSelecionado.getEmail());
	    graus_academicos.setText(Global.professorSelecionado.getGraus());
	    curriculo.setText(Global.professorSelecionado.getCurriculo());
	}

	// falta método
	@FXML
	void matricularDisciplina(ActionEvent event) {

	}

	@FXML
	void tableHorario(ActionEvent event) {

	}

	@FXML
	void telaClicada(MouseEvent event) {
		Label labelClicado = (Label) event.getSource();

		panelDados.setVisible(labelClicado == dadosTela);

		matricula.setText(Global.aluno.getMatricula());
		nome.setText(Global.aluno.getNome());
		email.setText(Global.aluno.getEmail());
		data_inicio.setText(Global.aluno.getData_inicio());
		situacao.setText(Global.aluno.getSituacao());


		panelProfessores.setVisible(labelClicado == professoresTela);

		ObservableList<String> items = FXCollections.observableArrayList(pegarTodosProfessores());

		// Adiciona o cabeçalho
		items.add(0, "Matricula\tNome\tEmail");

		listProfessor.setItems(items);
		listProfessor.setOnMouseClicked(e -> {
		    if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 1) {
		        int selectedIndex = listProfessor.getSelectionModel().getSelectedIndex();
		        if (selectedIndex > 0) {
		            String selectedProf = listProfessor.getItems().get(selectedIndex);
		            //System.out.println(selectedProf);
		            Global.professorSelecionado = getProfessorFromSelectedString(selectedProf);
		            //System.out.println(Global.professorSelecionado);

		            // Agora você pode salvar as informações do aluno na variável Global.alunoSelecionado
		        }
		    }
		});
		// Define a célula personalizada para exibir os itens do ListView
		listProfessor.setCellFactory((Callback<ListView<String>, ListCell<String>>) new Callback<ListView<String>, ListCell<String>>() {
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


		panelBoletim.setVisible(labelClicado == boletimTela);
		panelDisciplina.setVisible(labelClicado == disciplinaTela);

		ObservableList<String> itemsDisciplina = FXCollections.observableArrayList(pegarDisciplinasAluno());

		// Adiciona o cabeçalho
		itemsDisciplina.add(0, "Codigo-Disciplina\tNome\tDescrição");

		listDisciplina.setItems(itemsDisciplina);
		listDisciplina.setOnMouseClicked(e -> {
	        if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 1) {
	            int selectedIndex = listDisciplina.getSelectionModel().getSelectedIndex();
	            if (selectedIndex > 0) {
	                String selectedDisciplina = listDisciplina.getItems().get(selectedIndex);
	                //System.out.println(selectedDisciplina);
	                Global.disciplinaSelecionada = getDisciplinaFromSelectedString(selectedDisciplina);
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
	
	private Professor getProfessorFromSelectedString(String selectedAluno) {
	    String[] parts = selectedAluno.split("\t");
	    Professor professor = new Professor();
	    professor.setMatricula(parts[0]);
	    professor.setNome(parts[1]);
	    professor.setEmail(parts[2]);
	    professor.setGraus(obterGrausProfessor(parts[0]));
	    professor.setCurriculo(obterCurriculoProfessor(parts[0]));
	    // Preencha os outros atributos do aluno se necessário

	    return professor;
	}
	public String obterGrausProfessor(String matriculaProf) {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    String graus = "";

	    try {
	        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/connectschool", "root", "");

	        String query = "SELECT graus_academico FROM professor WHERE matricula = ?";
	        preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, matriculaProf);
	        resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	        	graus = resultSet.getString("graus_academico");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Feche as conexões, declarações e resultados aqui
	    }

	    return graus;
	}
	
	public String obterCurriculoProfessor(String matriculaProf) {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    String curriculo = "";

	    try {
	        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/connectschool", "root", "");

	        String query = "SELECT curriculo FROM professor WHERE matricula = ?";
	        preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, matriculaProf);
	        resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	        	curriculo = resultSet.getString("curriculo");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Feche as conexões, declarações e resultados aqui
	    }

	    return curriculo;
	}

	public ArrayList<String> pegarTodosProfessores() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		// Criar uma ArrayList para armazenar os alunos
		ArrayList<String> listaProfessores = new ArrayList<>();
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/connectschool", "root", "");

			// Aqui fiz uma subquery para funcionar a verificação da tabela aluno e professor ao mesmo tempo




			// Recuperar as informações dos alunos do banco de dados
			String query = "SELECT matricula, nome, email, graus_academico, curriculo FROM professor";
			preparedStatement = connection.prepareStatement(query);
			ResultSet professorResultSet = preparedStatement.executeQuery();

			// Percorrer os resultados do banco de dados
			while (professorResultSet.next()) {
				Professor professor = new Professor();
				professor.setMatricula(professorResultSet.getString("matricula"));
				professor.setNome(professorResultSet.getString("nome"));
				professor.setEmail(professorResultSet.getString("email"));
				professor.setGraus(professorResultSet.getString("graus_academico"));
				professor.setCurriculo(professorResultSet.getString("curriculo"));

				// Adicionar o aluno à lista
				listaProfessores.add(professor.toString());
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
		return listaProfessores;

	}

	
	private Disciplina getDisciplinaFromSelectedString(String selectedDisciplina) {
	    String[] parts = selectedDisciplina.split("\t");
	    Disciplina disciplina = new Disciplina();
	    disciplina.setCodDisciplina(parts[0]);
	    disciplina.setNome(parts[1]);
	    disciplina.setDescricao(parts[2]);
	    disciplina.setConteudo(obterConteudoDisciplina(parts[0]));
	    disciplina.setCargaHoraria(obterCargaDisciplina(parts[0]));
	    // Preencha os outros atributos da disciplina se necessário

	    return disciplina;
	}
	public String obterConteudoDisciplina(String codDisciplina) {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    String conteudo = "";

	    try {
	        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/connectschool", "root", "");

	        String query = "SELECT conteudo FROM disciplina WHERE cod_disciplina = ?";
	        preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, codDisciplina);
	        resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            conteudo = resultSet.getString("conteudo");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Feche as conexões, declarações e resultados aqui
	    }

	    return conteudo;
	}
	public String obterCargaDisciplina(String codDisciplina) {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    String cargaHoraria = "";

	    try {
	        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/connectschool", "root", "");

	        String query = "SELECT carga_horaria FROM disciplina WHERE cod_disciplina = ?";
	        preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, codDisciplina);
	        resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	        	cargaHoraria = resultSet.getString("carga_horaria");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Feche as conexões, declarações e resultados aqui
	    }

	    return cargaHoraria;
	}
	public ArrayList<String> pegarDisciplinasAluno() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		// Criar uma ArrayList para armazenar os alunos
		ArrayList<String> listaDisciplina = new ArrayList<>();
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/connectschool", "root", "");


			String query = "SELECT cod_disciplina, nome, descricao, conteudo, carga_horaria FROM disciplina WHERE cod_disciplina IN (SELECT cod_disciplina FROM disciplina_ofertada WHERE id_disciplina_ofertada IN (SELECT id_disciplina_ofertada FROM aluno_has_disciplina WHERE id_aluno = ?))"; 
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, Global.aluno.getMatricula());
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
