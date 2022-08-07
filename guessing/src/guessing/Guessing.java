/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package guessing;

import java.util.LinkedList;

/**
 *
 * @author Gilson Ponce Briones
 */
public class Guessing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //crear el arbol con las preguntas(/)
        //cargar las respuestas(/)
        //hacer el menu iterativo(/)
        //cargar las respuestas del usuario(/)
        //y presentar la respuesta(/)
        arbolBinario raiz=new arbolBinario();
        LinkedList<String>preguntas=new LinkedList<>();
        preguntas.add("Es este animal un mamifero");
        preguntas.add("Es este animal un carnivoro");
        preguntas.add("Este animal se para en 4 patas");
        raiz.buildTreeQuestion(preguntas);
        LinkedList<String> respuestas = new LinkedList();
        respuestas.add("si");
        respuestas.add("si");
        respuestas.add("si");
        raiz.buildTreeResponce("Oso", respuestas);
        LinkedList<String> respuestas2 = new LinkedList();
        respuestas2.add("si");
        respuestas2.add("no");
        respuestas2.add("si");
        raiz.buildTreeResponce("Venado", respuestas2);
        LinkedList<String> respuestas3 = new LinkedList();
        respuestas3.add("no");
        respuestas3.add("si");
        respuestas3.add("no");
        raiz.buildTreeResponce("Lechuza", respuestas3);
        LinkedList<String> respuestas4 = new LinkedList();
        respuestas4.add("no");
        respuestas4.add("no");
        respuestas4.add("no");
        raiz.buildTreeResponce("Paloma", respuestas4);
        LinkedList<String>respuestasUser=MenuPrueba.InvocarMenu(preguntas);
        String animal=raiz.searchAnswer(respuestasUser);
        System.out.println("El animal es: "+animal);
    }
    
}
