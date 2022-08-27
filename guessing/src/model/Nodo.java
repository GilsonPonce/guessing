/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Gilson Ponce Briones, Jose Zambrano, Derek Aviles
 */
public class Nodo {
    private String dato;
    private Nodo izquierda, derecha;
    private boolean Question;

    public Nodo(String dato,boolean Question) {
        this.dato = dato;
        this.Question = Question;
        this.izquierda = this.derecha = null;
    }


    public String getDato() {
        return dato;
    }
    
    public void setDato(String dato){
        this.dato = dato;
    }
    
    public boolean isQuestion(){
        return Question;
    }

    public Nodo getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Nodo izquierda) {
        this.izquierda = izquierda;
    }

    public Nodo getDerecha() {
        return derecha;
    }

    public void setDerecha(Nodo derecha) {
        this.derecha = derecha;
    }

    public void imprimirDato() {
        System.out.println(this.getDato());
    }
}
