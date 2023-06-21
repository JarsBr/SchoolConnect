package br.com.schoolconnect.projeto.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.schoolconnect.projeto.model.Aluno;

public class AlunoDAO {

	/*
	 * 
	 *  Métodos de Aluno
	 * 
	 */
	
	public static ArrayList<String> pegarTodosAlunos() {
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
	
	public static String obterDataInicioAluno(String matriculaAluno) {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    String data = "";

	    try {
	        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/connectschool", "root", "");

	        String query = "SELECT data_inicio FROM aluno WHERE matricula = ?";
	        preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, matriculaAluno);
	        resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	        	data = resultSet.getString("data_inicio");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Feche as conexões, declarações e resultados aqui
	    }

	    return data;
	}
	
	public static Aluno getAlunoFromSelectedString(String selectedAluno) {
	    String[] parts = selectedAluno.split("\t\t");
	    Aluno aluno = new Aluno();
	    aluno.setMatricula(parts[0]);
	    aluno.setNome(parts[1]);
	    aluno.setEmail(parts[2]);
	    aluno.setData_inicio(AlunoDAO.obterDataInicioAluno(parts[0]));
	    aluno.setSituacao(AlunoDAO.obterSituacaoAluno(parts[0]));
	    // Preencha os outros atributos do aluno se necessário

	    return aluno;
	}
	
	public static String obterSituacaoAluno(String matriculaAluno) {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    String situacao = "";

	    try {
	        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/connectschool", "root", "");

	        String query = "SELECT situacao FROM aluno WHERE matricula = ?";
	        preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, matriculaAluno);
	        resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	        	situacao = resultSet.getString("situacao");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Feche as conexões, declarações e resultados aqui
	    }

	    return situacao;
	}
}
