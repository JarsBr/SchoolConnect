package br.com.schoolconnect.projeto.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class ControllerMenuPrincipal {

	@FXML
	private ImageView config;

	@FXML
	private Button create;

	@FXML
	private Button login;

	@FXML
	void iniciar(ActionEvent event) {
		System.out.println("Teste tela principal");
	}

}
