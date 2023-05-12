package br.com.schoolconnect.projeto.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//import br.com.schoolconnect.projeto.database.DbUtils; Assim q adiciona o drive do bd parau de funcionar
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

public class ControllerInterfaceCadastro {

    @FXML
    private Button cadastrar;

    @FXML
    private TextField cpf;

    @FXML
    private TextField email;

    @FXML
    private TextField endereco;

    @FXML
    private RadioButton escolhaAluno;

    @FXML
    private RadioButton escolhaProfessor;

    @FXML
    private TextField fone;

    @FXML
    private ToggleGroup função;

    @FXML
    private TextField matricula;

    @FXML
    private Pane panel;

    @FXML
    private PasswordField password;

    @FXML
    void cadastrar(ActionEvent event) {

    }

    void escolhaAluno(ActionEvent event) {/*
    	cadastrar.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (!matricula.getText().trim().isEmpty() && !senha.getText().trim().isEmpty()) {
                    DbUtils.fazerCadastroAluno(event, matricula.getText(), senha.getText(), email.getText(), nome.getText());
                } else {
                    System.out.println("Por favor preencha os campos acima.");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Preencha todos os campos acima!");
                    alert.show();
                }
            }
        });*/
    }

    @FXML
    void escolhaProfessor(ActionEvent event) {/*
    	cadastrar.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (!matricula.getText().trim().isEmpty() && !senha.getText().trim().isEmpty()) {
                    DbUtils.fazerCadastroProfessor(event, matricula.getText(), senha.getText(), email.getText(), nome.getText());
                } else {
                    System.out.println("Por favor preencha os campos acima.");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Preencha todos os campos acima!");
                    alert.show();
                }
            }
        });*/

    }

}
