/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * @author Gilson Ponce
 */
public class arbolBinario {
    private Nodo raiz;

    public arbolBinario() {
       
    }

    public Nodo getRaiz() {
        return raiz;
    }
    
    public int altura(){
        return altura(this.raiz); 
    }

    private int altura(Nodo n){
        if(n == null) return 0;
        return Math.max(n.getIzquierda() == null ? 0 : altura(n.getIzquierda()), n.getDerecha() == null ? 0 :altura(n.getDerecha()))+1;
    }
    
    private int factorEquilibrio(){
        return factorEquilibrio(this.raiz);
    }
    
    private int factorEquilibrio(Nodo n){
        if(altura(n) == 1) return 0;
        if(n.getIzquierda() == null) return 0 - altura(n.getDerecha());
        if(n.getDerecha() == null) return altura(n.getIzquierda());
        return altura(n.getIzquierda())-altura(n.getDerecha());
    }
    
    public boolean isEmpty(Nodo n){
        if(n.getDato().isEmpty()) return true;
        if(n.getIzquierda()==null && n.getDerecha()==null) return true;
        return false;
    }
    
    public void preorden(){
        preorden(this.raiz);
    }
    
    private void preorden(Nodo n) {
        if (n != null) {
            n.imprimirDato();
            preorden(n.getIzquierda());
            preorden(n.getDerecha());
        }
    }
    
    
    public void buildTreeQuestion(LinkedList<String> preguntas){
        LinkedList<Nodo> nodos = new LinkedList();
        for(int i=0;i<preguntas.size();i++){
           double numNodos = Math.pow(2, i);
           String pregunta = preguntas.get(i);
           for(int p=0;p<numNodos;p++){
               nodos.add(new Nodo(pregunta,true));
           }
        }
        int numNodoTotal = nodos.size();
        for(int e=0;e<numNodoTotal;e++){
            int nodoImpar = (2*e)+1;
            int nodoPar = (2*e)+2;
            Nodo n = nodos.get(e);
            if(e == 0){
                this.raiz = n;
            }
            n.setDerecha(nodos.get(nodoPar));
            n.setIzquierda(nodos.get(nodoImpar));
            if(nodoPar == (numNodoTotal - 1)){
               break;
            }
        }
       
    }
    
    public void buildTreeResponce(String respuesta,LinkedList<String> cola){
        if(cola.isEmpty())return;
        if(respuesta.isBlank())return;
        buildTreeResponce(this.raiz,respuesta,cola);
    }
    
    private void buildTreeResponce(Nodo n,String respuesta, LinkedList<String> colaRespuesta){
        String resp = colaRespuesta.poll();
        if(resp != null){
            if(colaRespuesta.isEmpty()){//ya estoy en el ultimo elemento
                Nodo nodoRes = new Nodo(respuesta,false);
                if(resp.equals("si")){
                    n.setIzquierda(nodoRes);
                }else if(resp.equals("no")){
                    n.setDerecha(nodoRes);
                }
                return;
            }
            if(resp.equals("si")){
                buildTreeResponce(n.getIzquierda(),respuesta,colaRespuesta);
            }else if(resp.equals("no")){
                buildTreeResponce(n.getDerecha(),respuesta,colaRespuesta);
            }
        }
    }
    
    public String searchAnswer(LinkedList<String> respuestasUsuario){
        if(respuestasUsuario.isEmpty()) return null;
        return searchAnswer(this.raiz,respuestasUsuario);
    }
    
    private String searchAnswer(Nodo n,LinkedList<String> respuestasUsuario){
        String resp = respuestasUsuario.poll();
        if(resp != null){
          if(resp.toLowerCase().equals("si")){
              return searchAnswer(n.getIzquierda(),respuestasUsuario);
          }else if(resp.toLowerCase().equals("no")){
              return searchAnswer(n.getDerecha(),respuestasUsuario);
          }
        }else{
            return n.getDato();
        }
        return null;
    }
    
}
