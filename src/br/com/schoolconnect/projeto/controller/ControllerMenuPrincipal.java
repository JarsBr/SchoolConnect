package br.com.schoolconnect.projeto.controller;

import br.com.schoolconnect.projeto.view.FXML_Cadastro;
import br.com.schoolconnect.projeto.view.FXML_Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControllerMenuPrincipal {

	@FXML
	private Button cadastro;

	@FXML
	private Button login;

	@FXML
	void button_cadastro(ActionEvent event) {
		// Abre um nova tela
		FXML_Cadastro tela = new FXML_Cadastro();
		try {
			tela.start(new Stage());
			FXML_Cadastro.getStage().show();
		} catch (Exception e) {
			System.out.println("Erro ao executar a tela de cadastro");
		}

	}

	@FXML
	void button_login(ActionEvent event) {

		// Abre um nova tela
		FXML_Login tela = new FXML_Login();
		try {
			tela.start(new Stage());
			FXML_Login.getStage().show();
		} catch (Exception e) {
			System.out.println("Erro ao executar a tela de login");
		}

	}

}
