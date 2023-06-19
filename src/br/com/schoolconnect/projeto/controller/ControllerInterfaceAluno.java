package br.com.schoolconnect.projeto.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.util.Duration;

public class ControllerInterfaceAluno {

    @FXML
    private Label atividadesTela;

    @FXML
    private Label dadosTela;

    @FXML
    private Label professoresTela;

    @FXML
    private Label boletimTela;

    @FXML
    private Label horarioTela;

    @FXML
    private Label materiasTela;

    @FXML
    private Pane panel;

    @FXML
    private Pane panelAtividades;

    @FXML
    private Pane panelDados;

    @FXML
    private Pane panelProfessores;

    @FXML
    private Pane panelBoletim;

    @FXML
    private Pane panelHorario;

    @FXML
    private Pane panelMaterias;

    @FXML
    private Button sair;

    @FXML
    void button_sair(ActionEvent event) {
        Stage currentStage = (Stage) sair.getScene().getWindow();
        currentStage.close();
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MenuPrincipal.fxml"));
            Parent root = loader.load();
            
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (IOException e) {
            System.out.println("Erro ao carregar a tela de menu");
        }
    }

    @FXML
    void telaClicada(MouseEvent event) {
        Label labelClicado = (Label) event.getSource();
        
        panelAtividades.setVisible(labelClicado == atividadesTela);
        panelDados.setVisible(labelClicado == dadosTela);
        panelProfessores.setVisible(labelClicado == professoresTela);
        panelBoletim.setVisible(labelClicado == boletimTela);
        panelHorario.setVisible(labelClicado == horarioTela);
        panelMaterias.setVisible(labelClicado == materiasTela);
    }
    
    // animações bacanas
    @FXML
    private void labelMousePressed(MouseEvent event) {
        Label label = (Label) event.getSource();
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.2), label);
        transition.setToY(5); // Define o deslocamento vertical
        transition.play();
    }

    @FXML
    private void labelMouseReleased(MouseEvent event) {
        Label label = (Label) event.getSource();
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.2), label);
        transition.setToY(0); // Retorna à posição original
        transition.play();
    }
}
