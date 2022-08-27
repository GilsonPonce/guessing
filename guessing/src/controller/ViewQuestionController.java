/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import com.sun.prism.paint.Color;
import java.awt.Paint;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import model.Database;
import model.Nodo;
import model.arbolBinario;

/**
 * FXML Controller class
 *
 * @author Gilson Ponce Briones, Jose Zambrano, Derek Aviles
 */
public class ViewQuestionController implements Initializable {

    @FXML
    private Button btnAnswerSi;
    @FXML
    private Label lblQuestion;
    @FXML
    private Button btnAnswerNo;
    
    private Nodo n;
    private int conteo;
    Database data = Database.getInstance();
    private LinkedList<String> respUser;
    @FXML
    private Button btnClose;
    @FXML
    private ImageView imgvAvatar;
    @FXML
    private Button btnVolverJugar;
    @FXML
    private Button btnVolverInicio;
    private Clip sonido;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conteo = 1;
        respUser =  new LinkedList();
        arbolBinario arbol = data.getArbol();
        n = arbol.getRaiz();
        lblQuestion.setText(n.getDato());
        try {
            sonido = AudioSystem.getClip();
            sonido.open(AudioSystem.getAudioInputStream(new File("./src/resources/music.wav")));
            sonido.start();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(ViewQuestionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(ViewQuestionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ViewQuestionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void searchPossibleAnswers(Nodo n) {
        if (n != null) {
            if(!n.isQuestion()){
                String msm = lblQuestion.getText();
                msm += n.getDato() + ", ";
                lblQuestion.setText(msm);
            }
            searchPossibleAnswers(n.getIzquierda());
            searchPossibleAnswers(n.getDerecha());
        }
    }
    
    private void changeQuestion(boolean afir){
        if(conteo == data.getNumQuestion()){
           if(afir){
            n = n.getIzquierda();
           }else{
            n = n.getDerecha();
           }
           if(n != null){
               habilitarBotones();
               if(n.isQuestion()){
                   lblQuestion.setText("Puede que sea algunos de estos animales: ");
                   searchPossibleAnswers(n);
               }else{
                   lblQuestion.setText("El animal que estabas pensando es "+n.getDato());
               }
           }else{
               habilitarBotones();
               lblQuestion.setText("Tal vez no haiga un animal con esas caracteristicas");
           }
           return; 
        }
        Nodo n1;
        if(afir){
            n1 = n.getIzquierda();
        }else{
            n1 = n.getDerecha();
        }
        lblQuestion.setText(n1.getDato());
        n = n1;
        conteo++;
    }

    @FXML
    private void hangleAnswerSi(ActionEvent event) {
        respUser.add("si");
        changeQuestion(true);
    }

    @FXML
    private void hangleAnswerNo(ActionEvent event) {
        respUser.add("no");
        changeQuestion(false);
    }

    @FXML
    private void hangleClose(ActionEvent event) {
       System.exit(0);
    }

    @FXML
    private void hangleVolverJugar(ActionEvent event) {
        Image image = new Image("./resources/pensando.gif");
        imgvAvatar.setImage(image);
        btnAnswerSi.setVisible(true);
        btnAnswerNo.setVisible(true);
        btnVolverInicio.setVisible(false);
        btnVolverJugar.setVisible(false);
        btnClose.setVisible(false);
        conteo = 1;
        respUser =  new LinkedList();
        arbolBinario arbol = data.getArbol();
        n = arbol.getRaiz();
        lblQuestion.setText(n.getDato());
    }

    @FXML
    private void hangleVolverInicio(ActionEvent event) {
        try {
            sonido.close();
            Node source = (Node) event.getSource();
            Stage stage1 = (Stage) source.getScene().getWindow();
            stage1.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/viewUploadFile.fxml"));
            Parent root = loader.load();
            controllerViewUploadFile controllerUploadFile = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Adivinando Animales");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(controllerViewUploadFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void habilitarBotones(){
        Image image = new Image("./resources/bien.gif");
        imgvAvatar.setImage(image);
        btnAnswerSi.setVisible(false);
        btnAnswerNo.setVisible(false);
        btnVolverInicio.setVisible(true);
        btnVolverJugar.setVisible(true);
        btnClose.setVisible(true);
    }
}
