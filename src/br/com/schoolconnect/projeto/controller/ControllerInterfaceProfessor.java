package br.com.schoolconnect.projeto.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.schoolconnect.projeto.model.Global;
import br.com.schoolconnect.projeto.database.DbUtils;
import br.com.schoolconnect.projeto.model.Aluno;
import br.com.schoolconnect.projeto.model.AlunoNota;
import br.com.schoolconnect.projeto.model.Disciplina;
import br.com.schoolconnect.projeto.model.DisciplinaNota;
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
import java.util.ArrayList;

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
	    ObservableList<String> itemsBoletim = FXCollections.observableArrayList(pegarDisciplinasNotas());

	    // Adiciona o cabeçalho
	    itemsBoletim.add(0, "Nota\t\tCodigo\t\t\tDisciplina");

	    listAlunoNotas.setItems(itemsBoletim);

	    listAlunoNotas.setOnMouseClicked(e -> {
	        if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 1) {
	            int selectedIndex = listAlunoNotas.getSelectionModel().getSelectedIndex();
	            if (selectedIndex > 0) {
	                String selectedAluno = listAlunoNotas.getItems().get(selectedIndex);
	                System.out.println(selectedAluno); // Imprime a linha selecionada no console
	                Global.alunoNotaSelecionado = getAlunoFromSelectedString(selectedAluno);
	                
	                String campoInformacao = nota.getText();
	                if (!campoInformacao.isEmpty()) {
	                    float notaNova = Float.parseFloat(campoInformacao);
	                    alterarNota(selectedAluno, notaNova);
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

	public void alterarNota(String linhaSelecionada, float novaNota) {
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


	public int getIdDisciplinaOfertadaFromSelectedString(String selectedString) {
	    String[] parts = selectedString.split("\t\t"); // Divide a string com base no caractere de tabulação
	    String idDisciplinaOfertadaString = parts[0].trim(); // Obtém a parte correspondente ao ID da disciplina ofertada
	    return Integer.parseInt(idDisciplinaOfertadaString); // Converte para inteiro e retorna o ID
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
		
		ObservableList<String> itemsAlunos = FXCollections.observableArrayList(pegarTodosAlunos());

		// Adiciona o cabeçalho
		itemsAlunos.add(0, "Matricula\t Nome\t\t\tEmail");

		listAluno.setItems(itemsAlunos);
		listAluno.setOnMouseClicked(e -> {
		    if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 1) {
		        int selectedIndex = listAluno.getSelectionModel().getSelectedIndex();
		        if (selectedIndex > 0) {
		            String selectedAluno = listAluno.getItems().get(selectedIndex);
		            //System.out.println(selectedAluno);
		            Global.alunoSelecionado = getAlunoFromSelectedString(selectedAluno);
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
		

		ObservableList<String> itemsBoletim = FXCollections.observableArrayList(pegarDisciplinasNotas());

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

		ObservableList<String> itemsDisciplina = FXCollections.observableArrayList(pegarDisciplinasProfessor());

		// Adiciona o cabeçalho
		itemsDisciplina.add(0, "Codigo\t\tNome\t\t\t\tDescrição");

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
	
	public ArrayList<String> pegarDisciplinasNotas() {
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
	
	private Disciplina getDisciplinaFromSelectedString(String selectedDisciplina) {
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
	
	private Aluno getAlunoFromSelectedString(String selectedAluno) {
	    String[] parts = selectedAluno.split("\t\t");
	    Aluno aluno = new Aluno();
	    aluno.setMatricula(parts[0]);
	    aluno.setNome(parts[1]);
	    aluno.setEmail(parts[2]);
	    aluno.setData_inicio(obterDataInicioAluno(parts[0]));
	    aluno.setSituacao(obterSituacaoAluno(parts[0]));
	    // Preencha os outros atributos do aluno se necessário

	    return aluno;
	}

	public String obterDataInicioAluno(String matriculaAluno) {
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
	
	public String obterSituacaoAluno(String matriculaAluno) {
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
