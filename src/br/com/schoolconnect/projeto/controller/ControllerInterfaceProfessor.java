package br.com.schoolconnect.projeto.controller;

import br.com.schoolconnect.projeto.view.FXML_InterfaceProfessor;
import br.com.schoolconnect.projeto.view.FXML_Login;
import br.com.schoolconnect.projeto.view.FXML_MenuPrincipal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ControllerInterfaceProfessor {

    @FXML
    private Pane panel;

    @FXML
    private Button sair;

    @FXML
    void button_sair(ActionEvent event) {
    	FXML_MenuPrincipal tela = new FXML_MenuPrincipal();
        try {
            tela.start(new Stage());
            FXML_MenuPrincipal.getStage().show();
        } catch (Exception e) {
            System.out.println("Erro ao executar a tela de menu");
        }

        // Ao abrir a nova tela, fecha a tela de Cadastro
        Stage stage = (Stage) FXML_InterfaceProfessor.getScene().getWindow();
        stage.close();
    }

}
