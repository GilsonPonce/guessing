/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Database;

/**
 * FXML Controller class
 *
 * @author Gilson Ponce
 */
public class controllerViewUploadFile implements Initializable {

    @FXML
    private Button btnUploadQuestion;
    @FXML
    private TextField txtUrlQuestion;
    @FXML
    private Button btnUploadAnswers;
    @FXML
    private TextField txtUrlAnswers;
    @FXML
    private Button btnNext;
    @FXML
    private TextField txtNumQuestion;
    
    private Database data = Database.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void hangleUploadQuestion(ActionEvent event) {
       Stage stage = new Stage();
       FileChooser fc = new FileChooser();
       fc.setTitle("Seleccione el archivo de preguntas");
       fc.setInitialDirectory(
            new File(System.getProperty("user.home"))
        ); 
       fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT", "*.txt"));
       File file = fc.showOpenDialog(stage);
       if(file!=null)txtUrlQuestion.setText(file.getPath()); 
    }

    @FXML
    private void hangleUploadAnswer(ActionEvent event) {
       Stage stage = new Stage();
       FileChooser fc = new FileChooser();
       fc.setTitle("Seleccione el archivo de respuestas");
       fc.setInitialDirectory(
            new File(System.getProperty("user.home"))
        ); 
       fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT", "*.txt"));
       File file = fc.showOpenDialog(stage);
       if(file!=null){
           txtUrlAnswers.setText(file.getPath());
       }
    }

    @FXML
    private void hangleNext(ActionEvent event) throws FileNotFoundException {
        if(txtUrlQuestion.getText().isEmpty()){
            warning("Elija un archivo de preguntas, por favor");
            return;
        }
        if(txtUrlAnswers.getText().isEmpty()){
            warning("Elija un archivo de respuestas, por favor");
            return;
        }
        if(txtNumQuestion.getText().isEmpty()){
            warning("Ingrese el numero de preguntas deseados");
            return;
        }
        boolean isNumeric =  txtNumQuestion.getText().trim().matches("[+-]?\\d*(\\.\\d+)?");
        if(!isNumeric){
            warning("Ingrese un numero, por favor");
            return;
        }
        LinkedList<String> preguntas = preguntas();
        Map<String,LinkedList<String>> respuestas = respuestas();
        data.buildTree(preguntas, respuestas);
        data.setNumQuestion(Integer.parseInt(txtNumQuestion.getText().trim()));
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        startGame();
    }
    
    private LinkedList<String> preguntas() throws FileNotFoundException{
        LinkedList<String> pre = new LinkedList();
        String url = txtUrlQuestion.getText().trim();
        File doc = new File(url);
        Scanner obj = new Scanner(doc);
        while(obj.hasNextLine()){
            pre.add(obj.nextLine().trim());
        }
        return pre;
    }
    
    private Map<String,LinkedList<String>> respuestas() throws FileNotFoundException{
        Map<String,LinkedList<String>> map = new HashMap<>();
        String url = txtUrlAnswers.getText().trim();
        File doc = new File(url);
        Scanner obj = new Scanner(doc);
        while(obj.hasNextLine()){
            String[] listLinea = obj.nextLine().trim().split(" ");
            LinkedList<String> resp = new LinkedList();
            String nomAnimal = listLinea[0];
            for(int i = 1;i<listLinea.length;i++){
                resp.add(listLinea[i]);
            }
            map.put(nomAnimal,resp);
        }
        return map;
    }
    
    private void warning(String text){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Campos vacios");
            alert.setContentText(text);
            alert.showAndWait();
    }
    
    private void startGame(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/viewThinkAnimal.fxml"));
            Parent root = loader.load();
            ViewThinkAnimalController controllerThink = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Adivinando tu animal");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ViewThinkAnimalController .class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
