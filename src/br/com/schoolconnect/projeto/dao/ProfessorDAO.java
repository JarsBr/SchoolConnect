package br.com.schoolconnect.projeto.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.schoolconnect.projeto.model.Professor;

public class ProfessorDAO {
	/*
	 * 
	 *  Metodos Aluno
	 * 
	 */
	
	public static ArrayList<String> pegarTodosProfessores() {
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
	
	public static Professor getProfessorFromSelectedString(String selectedAluno) {
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
	
	public static String obterGrausProfessor(String matriculaProf) {
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
	
	public static String obterCurriculoProfessor(String matriculaProf) {
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
	

}
