package br.com.schoolconnect.projeto.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.schoolconnect.projeto.database.DbUtils;
import br.com.schoolconnect.projeto.view.FXML_Cadastro;
import br.com.schoolconnect.projeto.view.FXML_MenuPrincipal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ControllerInterfaceCadastro {

    @FXML
    private Button cadastrar;

	@FXML
	private Button voltaInicio;
	
	@FXML
	private AnchorPane telaCadastro;
    
    @FXML
    private TextField email;

    @FXML
    private RadioButton escolhaAluno;

    @FXML
    private RadioButton escolhaProfessor;

    @FXML
    private ToggleGroup função;

    @FXML
    private TextField matricula;

    @FXML
    private TextField nome;

    @FXML
    private Pane panel;

    @FXML
    private PasswordField password;

    @FXML
    public void voltaInicio(ActionEvent event) {
        FXML_MenuPrincipal tela = new FXML_MenuPrincipal();
        try {
            tela.start(new Stage());
            FXML_MenuPrincipal.getStage().show();
        } catch (Exception e) {
            System.out.println("Erro ao executar a tela de menu");
        }

        // Ao abrir a nova tela, fecha a tela de Cadastro
        Stage stage = (Stage) FXML_Cadastro.getScene().getWindow();
        stage.close();
    }

    @FXML
    void cadastrar(ActionEvent event) {

    }
    
    @FXML
    void escolhaAluno(ActionEvent event) {
    	cadastrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (!matricula.getText().trim().isEmpty() && !password.getText().trim().isEmpty() && !nome.getText().trim().isEmpty()&& !email.getText().trim().isEmpty()) {
                    DbUtils.fazerCadastroAluno(event, matricula.getText(), password.getText(), nome.getText(),email.getText());
                } else {
                    System.out.println("Por favor preencha os campos acima.");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Preencha todos os campos acima!");
                    alert.show();
                }
            }
        });
    }

    @FXML
    void escolhaProfessor(ActionEvent event) {
    	cadastrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                if (!matricula.getText().trim().isEmpty() && !password.getText().trim().isEmpty() && !nome.getText().trim().isEmpty()&& !email.getText().trim().isEmpty()) {
                    DbUtils.fazerCadastroProfessor(event, matricula.getText(), password.getText(),nome.getText(),email.getText());
                } else {
                    System.out.println("Por favor preencha os campos acima.");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Preencha todos os campos acima!");
                    alert.show();
                }
            }
        });
    }

}
