/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.util.ArrayList;
import usuario.Usuario;
import java.util.Scanner;


/**
 *
 * @author ErikaVilla
 */
public class Sistema {
    ArrayList<Usuario> usuarios;
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args){
      // menu();
      
      //comentario
      Sistema main = new Sistema();
        main.menu();
    }
     public void menu(){
        String opcion="";
        while(!opcion.equals("6")){
              System.out.println("/*********Nueva Solicitud**********/");
              System.out.println("/*                                    */");
              System.out.println("/***********************************/");
              System.out.println("1. Boda ");
              System.out.println("2. Fiesta Infantil ");
              System.out.println("3. Fiesta Empresarial ");
              System.out.print("Seleccione: ");
              opcion = sc.nextLine();
            switch (opcion){
                case "1":
                    System.out.println("/*********EVENTO BODA**********/");
                    System.out.println("                                   ");
                    break;
                case "2":
                    System.out.println("/*********EVENTO FIESTA INFANTIL**********/");
                    System.out.println("                                   ");
                    break;
                case "3": 
                   System.out.println("/*********EVENTO FIESTA EMPRESARIAL**********/");
                    System.out.println("                                   ");
                    break;
                
                default:
                    System.out.println("Opcion No valida!!, vuelva a ingresar");
            }
        }
        sc.close();
    }
    
}
