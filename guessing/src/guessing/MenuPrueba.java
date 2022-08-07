/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guessing;

import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author FxFF
 */
public class MenuPrueba {

    public static LinkedList<String> InvocarMenu(LinkedList<String>preguntas){
        LinkedList<String> respUsuario = new LinkedList();
        Scanner sc=new Scanner(System.in);
        System.out.println("Ingrese su nombre: ");
        String nombre=sc.next();
        System.out.println("Bienvenido "+nombre);
        System.out.println("Ingrese el numero de preguntas a realizarse: ");
        int cant=sc.nextInt();
        while(cant>preguntas.size()){
            System.out.println("El numero de preguntas proporcionado es demasiado grande intenta con otro numero");
            cant=sc.nextInt();
        }
        for(int i=0;i<cant;i++){
            System.out.println(preguntas.get(i));
            String resp=sc.next();
            while(!(resp.toLowerCase().equals("si") || resp.toLowerCase().equals("no"))){
                System.out.println("Ingrese una respuesta valida (si/no)");
                resp=sc.next();
            }
            respUsuario.add(resp);
        }
        return respUsuario;
    }
}
