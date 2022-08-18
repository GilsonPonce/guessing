/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Database;
import model.Nodo;
import model.arbolBinario;

/**
 * FXML Controller class
 *
 * @author GJPONCE
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
    }    
    
    private void changeQuestion(boolean afir){
        if(conteo == data.getNumQuestion()){
           if(afir){
            n = n.getIzquierda();
           }else{
            n = n.getDerecha();
           }
           if(n != null){
               btnAnswerSi.setVisible(false);
               btnAnswerNo.setVisible(false);
               btnClose.setVisible(true);
               if(n.isQuestion()){
                   lblQuestion.setText("Puede que sea algunos de estos animales");
               }else{
                   lblQuestion.setText("El animal que estabas pensando es "+n.getDato());
               }
           }else{
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
    
}
