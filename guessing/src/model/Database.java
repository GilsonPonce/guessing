/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author Gilson Ponce
 */
public final class Database {
    private arbolBinario arbol;
    private int numQuestion; 
    private LinkedList<String> respuestasUsuario;
    private static final Database INSTANCE = new Database();
    
    public static Database getInstance(){
      return INSTANCE;
    }

    public arbolBinario getArbol() {
        return arbol;
    }

    public void setArbol(arbolBinario arbol) {
        this.arbol = arbol;
    }

    public int getNumQuestion() {
        return numQuestion;
    }

    public void setNumQuestion(int numQuestion) {
        this.numQuestion = numQuestion;
    }
    
    public void buildTree(LinkedList<String> preguntas, Map<String,LinkedList<String>> respuestas){
        arbol = new arbolBinario();
        arbol.buildTreeQuestion(preguntas);
        arbol.preorden();
        respuestas.forEach((key,value)-> arbol.buildTreeResponce(key,value));
        //arbol.preorden();
    }
}
