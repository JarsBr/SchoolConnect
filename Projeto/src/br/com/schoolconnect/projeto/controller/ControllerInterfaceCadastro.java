package br.com.schoolconnect.projeto.controller;

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

    @FXML
    void escolhaAluno(ActionEvent event) {

    }

    @FXML
    void escolhaProfessor(ActionEvent event) {

    }

}
