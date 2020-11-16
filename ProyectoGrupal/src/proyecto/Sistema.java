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
      Menu main = new Menu();
      Menu.presentarMenuUsuario();
      Menu.presentarMenuPlanificador();
    }
}
    
