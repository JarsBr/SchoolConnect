package br.com.schoolconnect.projeto.controller;

import java.io.IOException;
import br.com.schoolconnect.projeto.dao.DisplinaDAO;
import br.com.schoolconnect.projeto.dao.ProfessorDAO;
import br.com.schoolconnect.projeto.database.DbUtils;
import br.com.schoolconnect.projeto.model.Global;
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

	@FXML
	void matricularDisciplina(ActionEvent event) {
		DbUtils.atualizarDisiciplinaAluno(Global.aluno.getMatricula(), Global.disciplinaSelecionada.getCodDisciplina(), "Teste", 0);
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

		ObservableList<String> items = FXCollections.observableArrayList(ProfessorDAO.pegarTodosProfessores());

		// Adiciona o cabeçalho
		items.add(0, "Matricula\tNome\t\t\tEmail");

		listProfessor.setItems(items);
		listProfessor.setOnMouseClicked(e -> {
		    if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 1) {
		        int selectedIndex = listProfessor.getSelectionModel().getSelectedIndex();
		        if (selectedIndex > 0) {
		            String selectedProf = listProfessor.getItems().get(selectedIndex);
		            //System.out.println(selectedProf);
		            Global.professorSelecionado = ProfessorDAO.getProfessorFromSelectedString(selectedProf);
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
		
		ObservableList<String> itemsBoletim = FXCollections.observableArrayList(DisplinaDAO.pegarNotasAluno());

		// Adiciona o cabeçalho
		itemsBoletim.add(0, "Nota\t\tCodigo\t\t\tDisciplina");

		listBoletim.setItems(itemsBoletim);
		
		// Define a célula personalizada para exibir os itens do ListView
		listBoletim.setCellFactory((Callback<ListView<String>, ListCell<String>>) new Callback<ListView<String>, ListCell<String>>() {
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

		ObservableList<String> itemsDisciplina = FXCollections.observableArrayList(DisplinaDAO.pegarDisciplinasAluno());

		// Adiciona o cabeçalho
		itemsDisciplina.add(0, "Codigo\t\tNome\t\tDescrição");

		listDisciplina.setItems(itemsDisciplina);
		listDisciplina.setOnMouseClicked(e -> {
	        if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 1) {
	            int selectedIndex = listDisciplina.getSelectionModel().getSelectedIndex();
	            if (selectedIndex > 0) {
	                String selectedDisciplina = listDisciplina.getItems().get(selectedIndex);
	                //System.out.println(selectedDisciplina);
	                Global.disciplinaSelecionada = DisplinaDAO.getDisciplinaFromSelectedStringAluno(selectedDisciplina);
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
