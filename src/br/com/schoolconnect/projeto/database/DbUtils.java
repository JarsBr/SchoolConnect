package br.com.schoolconnect.projeto.database;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

import br.com.schoolconnect.projeto.model.Global;
import br.com.schoolconnect.projeto.model.Aluno;
import br.com.schoolconnect.projeto.model.Professor;

/*
 * @author italo and jars
 * Uma classe inteira para funções e métodos que utilizarmos para o Banco de Dados e sua conexão.
 * 
 */

public class DbUtils {


	public static <LoggedInController> void mudarTela(ActionEvent event, String fxmlFile, String title, String matricula){
		Parent root = null;

		if (matricula != null) {
			try {
				FXMLLoader loader = new FXMLLoader(DbUtils.class.getResource(fxmlFile));
				root = loader.load();
				loader.getController();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				root = FXMLLoader.load(DbUtils.class.getResource(fxmlFile));
			} catch (IOException e){
				e.printStackTrace();
			}
		}
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle(title);
		stage.setScene(new Scene(root));
		stage.show();
	}

	// método para cadastrar um professor
	public static void fazerCadastroProfessor(ActionEvent event, String matricula, String password, String nome, String email) {
		Connection connection = null;
		PreparedStatement psInsert = null;
		PreparedStatement psCheckUserExists = null;
		ResultSet resultSet = null;


		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/connectschool", "root", "");
			psCheckUserExists = connection.prepareStatement("SELECT password FROM (SELECT password, matricula FROM professor UNION ALL SELECT password, matricula FROM aluno) AS users WHERE matricula = ?"); 
			psCheckUserExists .setString(1, matricula);
			psCheckUserExists .setString(1, matricula);
			resultSet = psCheckUserExists.executeQuery();

			if (resultSet.isBeforeFirst()) {
				System.out.println("Usuário já existe.");
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Você nao pode usar essa matricula");
				alert.show();
			} else{
				psInsert = connection.prepareStatement("INSERT INTO professor (matricula, password, nome, email) VALUES (?, ?, ?, ?)");
				psInsert.setString(1, matricula);
				psInsert.setString(2, password);
				psInsert.setString(3, nome);
				psInsert.setString(4, email);
				psInsert.executeUpdate();

				mudarTela(event, "../view/Login.fxml", "SchoolConnect", matricula);

			}
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (psCheckUserExists != null) {
				try {
					psCheckUserExists.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (psInsert != null) {
				try {
					psInsert.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// método para cadastrar um aluno
	public static void fazerCadastroAluno(ActionEvent event, String matricula, String password, String nome, String email) {
		Connection connection = null;
		PreparedStatement psInsert = null;
		PreparedStatement psCheckUserExists = null;
		ResultSet resultSet = null;


		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/connectschool", "root", "");
			psCheckUserExists = connection.prepareStatement("SELECT password FROM (SELECT password, matricula FROM professor UNION ALL SELECT password, matricula FROM aluno) AS users WHERE matricula = ?"); 
			psCheckUserExists .setString(1, matricula);
			psCheckUserExists .setString(1, matricula);
			resultSet = psCheckUserExists.executeQuery();

			if (resultSet.isBeforeFirst()) {
				System.out.println("Usuário já existe.");
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Você nao pode usar essa matricula");
				alert.show();
			} else{
				psInsert = connection.prepareStatement("INSERT INTO aluno (matricula, password, nome, email) VALUES (?, ?, ?, ?)");
				psInsert.setString(1, matricula);
				psInsert.setString(2, password);
				psInsert.setString(3, nome);
				psInsert.setString(4, email);
				psInsert.executeUpdate();

				mudarTela(event, "../view/Login.fxml", "SchoolConnect", matricula);

			}
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (psCheckUserExists != null) {
				try {
					psCheckUserExists.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (psInsert != null) {
				try {
					psInsert.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	//conecta e verifica se o usuario está presente no banco de dados
	public static void conectarUser(ActionEvent event, String matricula, String password) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/connectschool", "root", "");

			// Aqui fiz uma subquery para funcionar a verificação da tabela aluno e professor ao mesmo tempo
			preparedStatement = connection
					.prepareStatement("SELECT password, tipo FROM (SELECT password, matricula, 'professor' AS tipo FROM professor UNION ALL SELECT password, matricula, 'aluno' AS tipo FROM aluno) AS users WHERE matricula = ?");
			preparedStatement.setString(1, matricula);
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				System.out.println("Usuário não encontrado no banco de dados!");
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Credenciais incorretas.");
				alert.show();
			} else {
				while (resultSet.next()) {
					String retrievedPassword = resultSet.getString("password");
					String userType = resultSet.getString("tipo");
					if (retrievedPassword.equals(password)) {
						if (userType.equals("professor")) {

							// Recuperar as informações do professor do banco de dados
							String query = "SELECT nome, email, graus_academico, curriculo FROM professor WHERE matricula = ?";
							preparedStatement = connection.prepareStatement(query);
							preparedStatement.setString(1, matricula);
							ResultSet professorResultSet = preparedStatement.executeQuery();
							if (professorResultSet.next()) {
								Professor professor = new Professor();
								professor.setMatricula(matricula);
								professor.setNome(professorResultSet.getString("nome"));
								professor.setEmail(professorResultSet.getString("email"));
								professor.setGraus(professorResultSet.getString("graus_academico"));
								professor.setCurriculo(professorResultSet.getString("curriculo"));

								// Salvar o objeto professor em uma variável Global
								Global.professor = professor;
								//Global.username = professor.getNome();
							}
							// Abrir interface do professor
							mudarTela(event, "../view/InterfaceProfessor.fxml", "SchoolConnect", matricula);
						} else if (userType.equals("aluno")) {

							// Recuperar as informações do aluno do banco de dados
							String query = "SELECT nome, email, id_curso_periodo, data_inicio, situacao FROM aluno WHERE matricula = ?";
							preparedStatement = connection.prepareStatement(query);
							preparedStatement.setString(1, matricula);
							ResultSet alunoResultSet = preparedStatement.executeQuery();
							if (alunoResultSet.next()) {
								Aluno aluno = new Aluno();
								aluno.setMatricula(matricula);
								aluno.setNome(alunoResultSet.getString("nome"));
								aluno.setEmail(alunoResultSet.getString("email"));
								aluno.setId_curso_periodo(alunoResultSet.getString("id_curso_periodo"));
								aluno.setData_inicio(alunoResultSet.getString("data_inicio"));
								aluno.setSituacao(alunoResultSet.getString("situacao"));

								// Salvar o objeto aluno em uma variável Global
								Global.aluno = aluno;

							}
							// Abrir interface do aluno
							mudarTela(event, "../view/InterfaceAluno.fxml", "SchoolConnect", matricula);
						}
					} else {
						System.out.println("A senha não confere.");
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setContentText("As credenciais estão incorretas.");
						alert.show();
					}
				}
			}
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
	}


}
