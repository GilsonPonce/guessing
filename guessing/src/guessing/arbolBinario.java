/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guessing;

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
        if(altura()==0){
          String pregunta = preguntas.getFirst();
          this.raiz = new Nodo(pregunta);
        }
        buildTreeQuestion(this.raiz,preguntas);
    }
    
    
    public void buildTreeQuestion(Nodo n,LinkedList<String> preguntas){
           String pregunta;
           if (preguntas.size() == altura()) {
               pregunta = preguntas.get(altura()-1);
           }else{
               pregunta = preguntas.get(altura());
           }
           n.setDerecha(new Nodo(pregunta));
           n.setIzquierda(new Nodo(pregunta));
       
       if(preguntas.size()!= altura()){
                buildTreeQuestion(n.getDerecha(),preguntas);
                buildTreeQuestion(n.getIzquierda(),preguntas);
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
                Nodo nodoRes = new Nodo(respuesta);
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
          if(resp.equals("si")){
              return searchAnswer(n.getIzquierda(),respuestasUsuario);
          }else if(resp.equals("no")){
              return searchAnswer(n.getDerecha(),respuestasUsuario);
          }
        }else{
            return n.getDato();
        }
        return null;
    }
    
}