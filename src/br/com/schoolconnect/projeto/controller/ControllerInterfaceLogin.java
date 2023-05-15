package br.com.schoolconnect.projeto.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.schoolconnect.projeto.database.DbUtils;
import br.com.schoolconnect.projeto.view.FXML_Cadastro;
import br.com.schoolconnect.projeto.view.FXML_Login;
import br.com.schoolconnect.projeto.view.FXML_MenuPrincipal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
    	FXML_MenuPrincipal tela = new FXML_MenuPrincipal();
        try {
            tela.start(new Stage());
            FXML_MenuPrincipal.getStage().show();
        } catch (Exception e) {
            System.out.println("Erro ao executar a tela de menu");
        }

        // Ao abrir a nova tela, fecha a tela de Cadastro
        Stage stage = (Stage) FXML_Login.getScene().getWindow();
        stage.close();
    }

}

