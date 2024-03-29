package br.com.schoolconnect.projeto.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class FXML_Cadastro extends Application{
	
	private static Stage stage;
	public static Scene scene;
	
	public void start(Stage t) throws Exception  {
		stage = new Stage();
		
		stage.setResizable(false);

		Parent painel = FXMLLoader.load(getClass().getResource("Cadastro.fxml"));
		scene = new Scene(painel);

		stage.setTitle("Cadastre-se");
		stage.getIcons().add(new Image(FXML_Cadastro.class.getResourceAsStream( "img/symbolimage.png" ))); 

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
		FXML_Cadastro.scene = scene;
	}

}
