package br.com.schoolconnect.projeto.controller;

import java.io.IOException;

import br.com.schoolconnect.projeto.model.Global;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.util.Duration;

public class ControllerInterfaceAluno {

    @FXML
    private Label boletimTela;

    @FXML
    private Button consultaProfessor;

    @FXML
    private Label dadosTela;

    @FXML
    private Label data_inicio;

    @FXML
    private Label disciplinaTela;

    @FXML
    private Label email;

    @FXML
    private Label horarioTela;

    @FXML
    private ListView<?> listDisciplina;

    @FXML
    private ListView<?> listProfessor;

    @FXML
    private Label matricula;

    @FXML
    private Button matricularDisciplina;

    @FXML
    private Label nome;

    @FXML
    private Pane panel;

    @FXML
    private Pane panelBoletim;

    @FXML
    private Pane panelDados;

    @FXML
    private Pane panelDisciplina;

    @FXML
    private Pane panelHorario;

    @FXML
    private Pane panelProfessores;

    @FXML
    private Label professoresTela;

    @FXML
    private Button sair;

    @FXML
    private Label situacao;

    @FXML
    private TableView<?> tableHorario;

    @FXML
    private ImageView welcome;
    
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
    void consultaProfessor(ActionEvent event) {

    }

    @FXML
    void matricularDisciplina(ActionEvent event) {

    }
    
    @FXML
    void tableHorario(ActionEvent event) {

    }
    
    @FXML
    void telaClicada(MouseEvent event) {
        Label labelClicado = (Label) event.getSource();
        
        panelDados.setVisible(labelClicado == dadosTela);
        
        matricula.setText(Global.aluno.getMatricula());
        nome.setText(Global.aluno.getNome());
        email.setText(Global.aluno.getEmail());
        
        panelProfessores.setVisible(labelClicado == professoresTela);
        panelBoletim.setVisible(labelClicado == boletimTela);
        panelHorario.setVisible(labelClicado == horarioTela);
        panelDisciplina.setVisible(labelClicado == disciplinaTela);
    }
   
    
    // animações bacanas
    
    public void initialize() {
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.ZERO, new KeyValue(welcome.translateYProperty(), 0)),
            new KeyFrame(Duration.seconds(3), new KeyValue(welcome.translateYProperty(), -50))
        );
        timeline.setAutoReverse(true);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    
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
