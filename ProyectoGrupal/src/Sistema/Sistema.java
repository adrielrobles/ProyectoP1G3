/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import java.util.ArrayList;
import usuario.*;
import java.util.Scanner;
import ManejoArchivo.ManejoArchivos;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import evento.Evento;


/**
 *
 * @author ErikaVilla
 */
public class Sistema {
   private static ArrayList<Usuario> usuarios = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    boolean fechaValidada = false;
   public static ArrayList<Usuario> getUsuarios(){
    return usuarios;   
   }
   
    /**
     * Metodo menuprincipal va a retornar el ususario con el cual vamos a trabajr en el sistema.
     * @return Usuario
     */
    private Usuario menuprincipal(){
        Usuario usuario1= null;
        agginfoUsuario();
        do{
       System.out.println("++++++++++++++++++++++++++++++++++++++++\n"+"|");
       System.out.println("|\t BIENVENIDO AL SISTEMA\n"+"|");
       System.out.println("++++++++++++++++++++++++++++++++++++++++");
       System.out.print("USUARIO: ");
       String usuario = sc.nextLine();
       System.out.print("CONTRASEÑA: ");
       String contrasenia =sc.nextLine();
       usuario1 = verifiUsuario(usuario,contrasenia);
       if (usuario1==null){
           System.out.println("Ingrese usuario y contraseña correctos");
       }
        }while(usuario1==null);
        return usuario1;
    }
    /**
     * Metodo donde va a presentar el menu despues de ingresar con usuario y contraseña
     * ademas de acceder a una condicion si es Cliente o planificador.
     * @return void
     */
    public void iniciar(){
        Usuario usuario = menuprincipal();
        String opcion="";
        String opcion2="";
        if(usuario.getTipo()=='C'){
            Cliente usuarioC = (Cliente)usuario;
            while(!(opcion.equals("3"))){
              System.out.println("1. Solicitar Planificacion De Evento");
              System.out.println("2. Registar Pago Evento ");
              System.out.println("3. Salir");
              System.out.print("Seleccione opcion: ");
              opcion = sc.nextLine();
              switch (opcion){
                case "1":
                    System.out.println("/*********Nueva Solicitud***********/");
                    System.out.println("/*                                 */");
                    System.out.println("/***********************************/");
                        System.out.println("Bienvenid@ " + usuarioC.getNombre());//agreue
                        System.out.println("");
                        System.out.println("TIPO DE EVENTO (Elija)");
                        System.out.println("1. Boda ");
                        System.out.println("2. Fiesta Infantil ");
                        System.out.println("3. Fiesta Empresarial ");
                        System.out.print("Seleccione opcion: ");
                        opcion2 = sc.nextLine();
                        Date fechaE = new Date();
                        Date fechaS = new Date();
                        String registraSol;
                        switch (opcion2) {    
                            case "1":
                                System.out.println("/*********EVENTO BODA**********/");
                                System.out.println("                                ");
                                fechaE = ingresarFecha(304);
                                System.out.println("¿Desea registrar su solicitud?: ");
                                registraSol = sc.nextLine();
                                switch(registraSol.toUpperCase()){
                                    case "S":
                                        usuarioC.generarSolicitud(fechaE,fechaS,"BODA",usuarioC);
                                        break;
                                    default:
                                        System.out.println("No se genero Solicitud.");
                                }
                                break;
                            case "2":
                                System.out.println("/*********EVENTO FIESTA INFANTIL**********/");
                                System.out.println("                                           ");
                                fechaE = ingresarFecha(21);
                                System.out.println("¿Desea registrar su solicitud?: ");
                                registraSol = sc.nextLine();
                                switch(registraSol.toUpperCase()){
                                    case "S":
                                        usuarioC.generarSolicitud(fechaE,fechaS,"INFANTIL",usuarioC);
                                        break;
                                    default:
                                        System.out.println("No se genero Solicitud.");
                                }
                                break;
                            case "3":
                                System.out.println("/*********EVENTO FIESTA EMPRESARIAL**********/");
                                System.out.println("                                              ");
                                fechaE = ingresarFecha(60);
                                System.out.println("¿Desea registrar su solicitud?: ");
                                registraSol = sc.nextLine();
                                switch(registraSol.toUpperCase()){
                                    case "S":
                                        usuarioC.generarSolicitud(fechaE,fechaS,"EMPRESARIAL",usuarioC);
                                        break;
                                    default:
                                        System.out.println("No se genero Solicitud.");
                                }
                                break;
                                
                        }

                    case "2":
                        System.out.println("/*********Registrar Pago Evento**********/");
                        System.out.println("/                                        /");
                        System.out.println("/****************************************/");
                        break;
                    case "3":
                        System.out.println("Acaba de salir ");
                        System.out.println("                                   ");
                        break;
                    default:
                        System.out.println("Opcion No valida!!, vuelva a ingresar");
                }
            
            }
        } else if (usuario.getTipo() == 'P') {
            while (!opcion.equals("5")) {
                System.out.println("Bienvenid@ " + usuario.getNombre());
                System.out.println(" ");
                System.out.println("1.Consultar Solicitudes Pendientes ");
                System.out.println("2. Registrar Evento");
                System.out.println("3. Confirmar Evento ");
                System.out.println("4. Consultar Evento ");
                System.out.println("5. Salir");
                System.out.print("Seleccione opcion: ");
                opcion = sc.nextLine();
                switch (opcion) {
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
        }
    
        sc.close();
    }

    /**
     * Metodo para la verificar si el ususario existe en el sistema donde
     * recorreremos el ArrayList Usuario y ponemos una condicion si el usuaario
     * ingresado es igual al usuario en el Array asi mismo con la contraseña si
     * entra a la condicion retornaremos la informacion del usuario de no ser
     * asi devolvera null.
     *
     * @param usuario
     * @param contrasenia
     * @return Usuario
     */
    private Usuario verifiUsuario(String usuario, String contrasenia) {
        for (Usuario usuarios : usuarios) {
            if (usuario.equals(usuarios.getUsuario())) {
                if (contrasenia.equals(usuarios.getContrasena())) {
                    return usuarios;
                }
            }
        }
        return null;
    }

    /**
     * Metodo para agregar a todos los usuarios al ArrayList usuarios de la
     * clase Usuario que se encuentran en el archivo usuarios.txt leyendo por el
     * metodo ManejoArchivos.LeeFichero el cual retornara una lista string por
     * cada linea en el archivo, la cual se va recorrer y obtener un String la
     * cual la vamos a separar en una lista con "," donde vamos a poner una
     * condicion para cliente y planificado donde vamos a instanciar los dos
     * tipos de usuarios debido a que algunos tienen mas indices que otro.
     *
     * @return void
     */
    private void agginfoUsuario() {
        ArrayList<String> preusuarios = ManejoArchivos.LeeFichero("usuarios.txt");
        for (String preusuario : preusuarios) {
            String listusuario[] = preusuario.split(",");
            if (listusuario[4].equals("C")) {
                usuarios.add(new Cliente(listusuario[0], listusuario[1], listusuario[2], listusuario[3], listusuario[4].charAt(0), Integer.parseInt(listusuario[5]), listusuario[6]));
            }
            else{
                usuarios.add(new Planificador(listusuario[0], listusuario[1], listusuario[2], listusuario[3], listusuario[4].charAt(0)));
            }           
        }        
    }
    /**
     * Metodo para ingresar la fecha y poder validar la sintaxis, ademas de validar de segun el evento que se escoja en el menu principal
     * todo esto se lograr por medio del try catch para validar la sintaxis y asi pasar a poder verificar si la fecha esta de acuerdo con los 
     * parametros de los eventos.
     * @param numero
     * @return fecha
     */
    private Date ingresarFecha(int numero){
        boolean validacion=false;
        while(validacion==false){
        System.out.println("Fecha del evento: ");
        String fechax = sc.nextLine();
        try{
            Date fecha = formato.parse(fechax);
            boolean validafecha = new Evento().validarTiempo(fecha, numero);
            if (validafecha) {
                System.out.println("¡Fecha valida!");
                System.out.println("Ha registrado todos los datos necesarios para la solicitud ");
                return fecha;
            }
            else {
                if(numero == 304)
                System.out.println("**La fecha es muy proxima. Para este tipo de evento debemos tener\tpor lo menos 10 meses para planificar. Ingrese nuevamente");
                else if (numero==21)
                System.out.println("**La fecha es muy proxima. Para este tipo de evento debemos tener\tpor lo menos 10 meses para planificar. Ingrese nuevamente");
                else if (numero==60)
                System.out.println("**La fecha es muy proxima. Para este tipo de evento debemos tener\tpor lo menos 10 meses para planificar. Ingrese nuevamente");
            }            
        }catch(Exception e){
            System.out.println("Ingrese la sintaxis de fecha correcta");
        }
        }
        return null;
    }
}
