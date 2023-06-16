package br.com.schoolconnect.projeto.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ControllerInterfaceProfessor {

    @FXML
    private Label atividadesTela;

    @FXML
    private Label dadosTela;

    @FXML
    private Label estagioTela;

    @FXML
    private Label historicoTela;

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
    private Pane panelEstagio;

    @FXML
    private Pane panelHistorico;

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
    void atividadesTela(MouseEvent event) {
        // Ação quando o rótulo de atividadesTela for clicado
    }

    @FXML
    void dadosTela(MouseEvent event) {
        // Ação quando o rótulo de dadosTela for clicado
    }

    @FXML
    void estagioTela(MouseEvent event) {
        // Ação quando o rótulo de estagioTela for clicado
    }

    @FXML
    void historicoTela(MouseEvent event) {
        // Ação quando o rótulo de historicoTela for clicado
    }

    @FXML
    void horarioTela(MouseEvent event) {
        // Ação quando o rótulo de horarioTela for clicado
    }

    @FXML
    void materiasTela(MouseEvent event) {
        // Ação quando o rótulo de materiasTela for clicado
    }

}