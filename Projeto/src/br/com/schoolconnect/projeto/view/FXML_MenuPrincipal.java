package br.com.schoolconnect.projeto.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class FXML_MenuPrincipal  extends Application{
	private static Stage stage;
	public static Scene scene;
	
	public void start(Stage t) throws Exception  {
		stage = new Stage();
		
		stage.setResizable(false);

		Parent painel = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
		scene = new Scene(painel);

		stage.setTitle("Menu");
	    stage.getIcons().add(new Image(FXML_MenuPrincipal.class.getResourceAsStream( "img/logofullhd.png" ))); 

		stage.show();

		stage.setScene(scene);

		stage.setOnCloseRequest((WindowEvent t1) -> {
			t1.consume();
			stage.close();
		});
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

	public static Stage getStage() {
		return stage;
	}

	public static Scene getScene() {
		return scene;
	}

	public static void setScene(Scene scene) {
		FXML_MenuPrincipal.scene = scene;
	}

}