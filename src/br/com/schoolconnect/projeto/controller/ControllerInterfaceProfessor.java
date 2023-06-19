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

public class ControllerInterfaceProfessor {

    @FXML
    private Label alunosTela;

    @FXML
    private Button cadastroDados;

    @FXML
    private Button cadastroDisciplina;

    @FXML
    private Button consultaAluno;

    @FXML
    private Label curriculo;

    @FXML
    private Label dadosTela;

    @FXML
    private Label disciplinaTela;

    @FXML
    private Label email;

    @FXML
    private Label graus_academicos;

    @FXML
    private Label horarioTela;

    @FXML
    private ListView<?> listAluno;

    @FXML
    private ListView<?> listDisciplina;

    @FXML
    private Label matricula;

    @FXML
    private Label nome;

    @FXML
    private Label notasTela;

    @FXML
    private Pane panel;

    @FXML
    private Pane panelAlunos;

    @FXML
    private Pane panelAlunos1;

    @FXML
    private Pane panelDados;

    @FXML
    private Pane panelDisciplina;

    @FXML
    private Pane panelHorario;

    @FXML
    private Pane panelNotas;

    @FXML
    private Button removerDisciplina;

    @FXML
    private Button sair;

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
    void cadastroDados(ActionEvent event) {

    }

    @FXML
    void cadastroDisciplina(ActionEvent event) {

    }
    
    @FXML
    void removerDisciplina(ActionEvent event) {

    }

    @FXML
    void consultaAluno(ActionEvent event) {

    }
    
    @FXML
    void telaClicada(MouseEvent event) {
        Label labelClicado = (Label) event.getSource();

        panelDados.setVisible(labelClicado == dadosTela);
       
        matricula.setText(Global.professor.getMatricula());
        nome.setText(Global.professor.getNome());
        email.setText(Global.professor.getEmail());
        graus_academicos.setText(Global.professor.getGraus());
        curriculo.setText(Global.professor.getCurriculo());
        
        panelAlunos.setVisible(labelClicado == alunosTela);
        panelNotas.setVisible(labelClicado == notasTela);
        panelHorario.setVisible(labelClicado == horarioTela);
        panelDisciplina.setVisible(labelClicado == disciplinaTela);
    }
    
    @FXML
    void tableHorario(ActionEvent event) {

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
