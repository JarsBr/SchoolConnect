package br.com.schoolconnect.projeto.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.schoolconnect.projeto.database.DbUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/*
 * 
 *  Controller da Interface Login
 * 
 */

public class ControllerInterfaceLogin implements Initializable {


	@FXML
	private AnchorPane telaLogin;
	
    @FXML
    private Button entrar;

    @FXML
    private TextField matricula;

    @FXML
    private Pane panel;

    @FXML
    private PasswordField password;

    @FXML
    private Button voltaInicio;

    @FXML
    void entrar(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resources) {
       entrar.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                DbUtils.conectarUser(event, matricula.getText(), password.getText());
            }
        });
    }
    
    @FXML
    public void voltaInicio(ActionEvent event) throws IOException{
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

}

