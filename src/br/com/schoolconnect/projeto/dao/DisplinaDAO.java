package br.com.schoolconnect.projeto.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.com.schoolconnect.projeto.model.AlunoNota;
import br.com.schoolconnect.projeto.model.Disciplina;
import br.com.schoolconnect.projeto.model.DisciplinaNota;
import br.com.schoolconnect.projeto.model.Global;

public class DisplinaDAO {
	/*
	 * 
	 *  Metodos Aluno
	 * 
	 */
	public static Disciplina getDisciplinaFromSelectedStringAluno(String selectedDisciplina) {
	    String[] parts = selectedDisciplina.split("\t");
	    Disciplina disciplina = new Disciplina();
	    disciplina.setCodDisciplina(parts[0]);
	    disciplina.setNome(parts[1]);
	    disciplina.setDescricao(parts[2]);
	    disciplina.setConteudo(obterConteudoDisciplinaAluno(parts[0]));
	    disciplina.setCargaHoraria(obterCargaDisciplinaAluno(parts[0]));
	    // Preencha os outros atributos da disciplina se necessário

	    return disciplina;
	}
	
	public static String obterCargaDisciplinaAluno(String codDisciplina) {
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
	
	public static String obterConteudoDisciplinaAluno(String codDisciplina) {
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
	
	public static ArrayList<String> pegarNotasAluno() {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    ArrayList<String> listaNotas = new ArrayList<>();
	    try {
	        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/connectschool", "root", "");

	        String query = "SELECT disciplina.cod_disciplina, disciplina.nome, disciplina.descricao, disciplina.conteudo, disciplina.carga_horaria, aluno_has_disciplina.nota FROM disciplina INNER JOIN disciplina_ofertada ON disciplina.cod_disciplina = disciplina_ofertada.cod_disciplina INNER JOIN aluno_has_disciplina ON disciplina_ofertada.id_disciplina_ofertada = aluno_has_disciplina.id_disciplina_ofertada WHERE aluno_has_disciplina.id_aluno = ?";
	        preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, Global.aluno.getMatricula());

	        ResultSet disciplinaResultSet = preparedStatement.executeQuery();

	        while (disciplinaResultSet.next()) {
	            DisciplinaNota disciplinaNota = new DisciplinaNota();
	            disciplinaNota.setCodDisciplina(disciplinaResultSet.getString("cod_disciplina"));
	            disciplinaNota.setNome(disciplinaResultSet.getString("nome"));
	            disciplinaNota.setDescricao(disciplinaResultSet.getString("descricao"));
	            disciplinaNota.setConteudo(disciplinaResultSet.getString("conteudo"));
	            disciplinaNota.setCargaHoraria(disciplinaResultSet.getString("carga_horaria"));
	            disciplinaNota.setNota(disciplinaResultSet.getFloat("nota"));
	            listaNotas.add(disciplinaNota.toString());
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
	    
	    return listaNotas;
	}
	
	public static ArrayList<String> pegarDisciplinasAluno() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		// Criar uma ArrayList para armazenar os alunos
		ArrayList<String> listaDisciplina = new ArrayList<>();
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/connectschool", "root", "");


			String query = "SELECT cod_disciplina, nome, descricao, conteudo, carga_horaria FROM disciplina WHERE cod_disciplina IN (SELECT cod_disciplina FROM disciplina_ofertada)"; 
			preparedStatement = connection.prepareStatement(query);
			
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
	
	
	
	
	/*
	 * 
	 *  Metodos Professor
	 * 
	 */
	
	public static ArrayList<String> pegarDisciplinasNotas() {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    ArrayList<String> listaNotas = new ArrayList<>();
	    try {
	        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/connectschool", "root", "");

	     
	        String query = "SELECT disciplina.cod_disciplina, disciplina.nome AS nome_disciplina, disciplina.descricao, disciplina.conteudo, disciplina.carga_horaria, aluno_has_disciplina.nota, aluno.matricula, aluno.nome AS nome_aluno, disciplina_ofertada.id_disciplina_ofertada FROM disciplina INNER JOIN disciplina_ofertada ON disciplina.cod_disciplina = disciplina_ofertada.cod_disciplina INNER JOIN aluno_has_disciplina ON disciplina_ofertada.id_disciplina_ofertada = aluno_has_disciplina.id_disciplina_ofertada INNER JOIN aluno ON aluno_has_disciplina.id_aluno = aluno.matricula WHERE disciplina_ofertada.matricula_professor = ?";
	        preparedStatement = connection.prepareStatement(query);	  
	        preparedStatement.setString(1, Global.professor.getMatricula());

	        ResultSet disciplinaResultSet = preparedStatement.executeQuery();

	        while (disciplinaResultSet.next()) {
	            AlunoNota disciplinaNota = new AlunoNota();
	            disciplinaNota.setCodDisciplina(disciplinaResultSet.getString("cod_disciplina"));
	            disciplinaNota.setNomeDisciplina(disciplinaResultSet.getString("nome_disciplina"));
	            disciplinaNota.setCodAluno(disciplinaResultSet.getString("matricula"));
	            disciplinaNota.setNomeAluno(disciplinaResultSet.getString("nome_aluno"));
	            disciplinaNota.setNota(disciplinaResultSet.getFloat("nota"));
	            disciplinaNota.setCodDisciplinaOfertada(disciplinaResultSet.getString("id_disciplina_ofertada"));
	            listaNotas.add(disciplinaNota.toString());
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

	    return listaNotas;
	}
	
	public static ArrayList<String> pegarDisciplinasProfessor() {
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
	
	public static Disciplina getDisciplinaFromSelectedString(String selectedDisciplina) {
	    String[] parts = selectedDisciplina.split("\t\t");
	    Disciplina disciplina = new Disciplina();
	    disciplina.setCodDisciplina(parts[0]);
	    disciplina.setNome(parts[1]);
	    disciplina.setDescricao(parts[2]);
	    disciplina.setConteudo(obterConteudoDisciplina(parts[0]));
	    disciplina.setCargaHoraria(obterCargaDisciplina(parts[0]));
	    // Preencha os outros atributos da disciplina se necessário

	    return disciplina;
	}
	public static String obterConteudoDisciplina(String codDisciplina) {
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
	
	public static void alterarNota(String linhaSelecionada, float novaNota) {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    
	    try {
	        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/connectschool", "root", "");

	        String query = "UPDATE aluno_has_disciplina SET nota = ? WHERE id_disciplina_ofertada = ?";
	        preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setFloat(1, novaNota);
	        preparedStatement.setInt(2, getIdDisciplinaOfertadaFromSelectedString(linhaSelecionada));

	        int rowsAffected = preparedStatement.executeUpdate();
	        if (rowsAffected > 0) {
	            JOptionPane.showMessageDialog(null, "Nota alterada com sucesso!");
	        } else {
	            JOptionPane.showMessageDialog(null, "Falha ao alterar a nota.");
	        }	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
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
	
	
	public static int getIdDisciplinaOfertadaFromSelectedString(String selectedString) {
	    String[] parts = selectedString.split("\t\t"); // Divide a string com base no caractere de tabulação
	    String idDisciplinaOfertadaString = parts[0].trim(); // Obtém a parte correspondente ao ID da disciplina ofertada
	    return Integer.parseInt(idDisciplinaOfertadaString); // Converte para inteiro e retorna o ID
	}
	
	public static String obterCargaDisciplina(String codDisciplina) {
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
}
