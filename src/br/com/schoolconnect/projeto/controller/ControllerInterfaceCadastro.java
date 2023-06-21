package br.com.schoolconnect.projeto.controller;

import java.io.IOException;
import br.com.schoolconnect.projeto.database.DbUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/*
 * 
 *  Controller da Interface Cadastro
 * 
 */

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
    	Stage currentStage = (Stage) voltaInicio.getScene().getWindow();
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
