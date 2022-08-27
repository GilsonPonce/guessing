/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controller;

import java.io.IOException;
import java.util.LinkedList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Gilson Ponce Briones, Jose Zambrano, Derek Aviles
 */
public class Guessing extends Application{

    /**
     * @param primaryStage
     * */
    @Override
    public void start(Stage primaryStage){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Guessing.class.getResource("/view/viewUploadFile.fxml"));
            Pane ventana = (Pane) loader.load();
            Scene scene = new Scene(ventana);
            primaryStage.setScene(scene);
            //primaryStage.getIcons().add(new Image("/path/to/stackoverflow.jpg"));
            primaryStage.setTitle("Adivinando tu animal");
            primaryStage.setResizable(false);
            primaryStage.show();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
       launch(args); 
    }
    
}
