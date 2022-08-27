/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */

import model.arbolBinario;
import java.util.ArrayList;
import java.util.LinkedList;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author GJPONCE
 */
public class testArbolBinario {
    
    public testArbolBinario() {
    }
        
   @Test
   public void testArbolBinario(){
       arbolBinario arbolDecision = new arbolBinario();
       LinkedList<String> preguntas = new LinkedList();
       preguntas.add("Es este animal vive en la sabana Africana?");
       preguntas.add("Es mamifero?");
       preguntas.add("Es carnivoro?");
       preguntas.add("Se para en cuatro patas?");
       preguntas.add("Puede llegar a pesar toneladas?");
       arbolDecision.buildTreeQuestion(preguntas);
       System.out.println("Impresion de arbol");
       arbolDecision.preorden();
       /*LinkedList<String> respuestas = new LinkedList();
       respuestas.add("si");
       respuestas.add("si");
       respuestas.add("si");
       arbolDecision.buildTreeResponce("Oso", respuestas);
       LinkedList<String> respuestas2 = new LinkedList();
       respuestas2.add("si");
       respuestas2.add("no");
       respuestas2.add("si");
       arbolDecision.buildTreeResponce("Venado", respuestas2);
       LinkedList<String> respuestas3 = new LinkedList();
       respuestas3.add("no");
       respuestas3.add("si");
       respuestas3.add("no");
       arbolDecision.buildTreeResponce("Lechuza", respuestas3);
       LinkedList<String> respuestas4 = new LinkedList();
       respuestas4.add("no");
       respuestas4.add("no");
       respuestas4.add("no");
       arbolDecision.buildTreeResponce("Paloma", respuestas4);
       System.out.println("Impresion de arbol");
       arbolDecision.preorden();
       LinkedList<String> respUsuario = new LinkedList();
       respUsuario.add("no");
       respUsuario.add("si");
       respUsuario.add("no");
       String animal = arbolDecision.searchAnswer(respUsuario);
       assertEquals("Lechuza",animal);*/
   }
}
