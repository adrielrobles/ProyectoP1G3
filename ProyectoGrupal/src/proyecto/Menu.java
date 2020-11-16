/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import static proyecto.Sistema.sc;

/**
 *
 * @author ErikaVilla
 */
public class Menu {
    public static void presentarMenuUsuario(){
        String opcion="";
        String opcion2="";
        while(!opcion.equals("3")){
              System.out.println("1. Solicitar Planificacion De Evento");
              System.out.println("2. Registar Pago Evento ");
              System.out.println("3. Salir");
              System.out.print("Seleccione: ");
              opcion = sc.nextLine();
              switch (opcion){
                case "1":
                   
                    System.out.println("/*********Nueva Solicitud**********/");
                   System.out.println("/*                                    */");
                   System.out.println("/***********************************/");
                   System.out.println("1. Boda ");
                    System.out.println("2. Fiesta Infantil ");
                    System.out.println("3. Fiesta Empresarial ");
                    System.out.print("Seleccione: ");
                    opcion2 = sc.nextLine();
                    switch(opcion2){
                        case "1":
                        System.out.println("/*********EVENTO Boda**********/");
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
                   
                    break;
                case "2":
                    System.out.println("/*********Registrar Pago Evento**********/");
                    System.out.println("                                   ");
                    break;
                case "3": 
                    System.out.println("Acaba de salir ");
                    System.out.println("                                   ");
                     break;
                default:
                    System.out.println("Opcion No valida!!, vuelva a ingresar");
            }
              
        }
        
    }
    public static void presentarMenuPlanificador(){
        String opcion="";
        while(!opcion.equals("5")){
              System.out.println("Bienvenido Adriel ");
              System.out.println(" ");
              System.out.println("1.Consultar Solicitudes Pendientes ");
              System.out.println("2. Registrar Evento");
              System.out.println("3. Confirmar Evento ");
              System.out.println("4. Consultar Evento ");
              System.out.println("5. Salir");
              System.out.print("Seleccione: ");
              opcion = sc.nextLine();
            switch (opcion){
                case "1":
                    System.out.println("/*********Solicitudes Pendientes**********/");
                    System.out.println("/*                                    */");
                    System.out.println("/***********************************/");
                    break;
                case "2":
                    System.out.println("/*********Registro De Eventos**********/");
                    System.out.println("/*                                    */");
                    System.out.println("/***********************************/");
                    break;
                case "3": 
                   System.out.println("/*********Confirmar Evento**********/");
                   System.out.println("/*                                    */");
                   System.out.println("/***********************************/");
                    break;
                case "4": 
                   System.out.println("/*********Consultar Evento**********/");
                   System.out.println("/*                                    */");
                   System.out.println("/***********************************/");
                    break;
                
                default:
                    System.out.println("Opcion No valida!!, vuelva a ingresar");
            }
        }
        sc.close();
    }
    }
    

