package br.com.schoolconnect.projeto.controller;

import java.io.Closeable;

import br.com.schoolconnect.projeto.view.FXML_Cadastro;
import br.com.schoolconnect.projeto.view.FXML_Login;
import br.com.schoolconnect.projeto.view.FXML_MenuPrincipal;
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
		
		// Ao Abrir a nova tela, fecha o Menu
				FXML_MenuPrincipal closeButton;
				Stage stage = (Stage) FXML_MenuPrincipal.getScene().getWindow();
				stage.close();		
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

		// Ao Abrir a nova tela, fecha o Menu
		FXML_MenuPrincipal closeButton;
		Stage stage = (Stage) FXML_MenuPrincipal.getScene().getWindow();
		stage.close();
	}

	
}
