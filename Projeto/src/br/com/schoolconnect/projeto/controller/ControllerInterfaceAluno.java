package br.com.schoolconnect.projeto.controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ControllerInterfaceAluno {
	
	private static Stage stage;
	public static Scene scene;

	public void start(Stage t) throws Exception {
	stage = new Stage();

	stage.setResizable(false);

	Parent painel = FXMLLoader.load(getClass().getResource("InterfaceAluno.fxml"));
	scene = new Scene(painel);

	stage.setTitle("Aluno");
	stage.setScene(scene);
	stage.show();
	}
	public static void main(String[] args) {
	launch(args);
	}
	private static void launch(String[] args) {
		// TODO Auto-generated method stub
		
	}
}