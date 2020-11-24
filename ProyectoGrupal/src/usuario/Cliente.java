/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import ManejoArchivo.ManejoArchivos;
import evento.Evento;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import tipos.TipoEstadoS;
import tipos.TipoEvento;

/**
 *
 * @author ErikaVilla
 */
/**
 * Crear la clase hija Cliente de la clase padre Usuario con sus respectivos atributos.
 * @author Adriel Robles
 */
public class Cliente extends Usuario {
    protected int telefono;
    protected String correoElectronico;
    /**
     * Se crea constructor para poder darle valores a los atributos de Cliente
     * @param nombre
     * @param apellido
     * @param usuario
     * @param contrasena
     * @param tipo
     * @param telefono
     * @param correoElectronico 
     */
    /**
     * Constructor que reciba toda la informacion de cliente mas dos elementos adicionales que serian telefon y correoElectronico.
     * @param nombre
     * @param apellido
     * @param usuario
     * @param contrasena
     * @param tipo
     * @param telefono
     * @param correoElectronico 
     */
    public Cliente(String nombre, String apellido, String usuario, String contrasena, char tipo,int telefono, String correoElectronico) {
        super(nombre, apellido, usuario, contrasena, tipo);
        this.telefono=telefono;
        this.correoElectronico=correoElectronico;
    }
    //GETTERS 

    public int getTelefono() {
        return telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }
    
    // SETTERS

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
    /**
     * Metodo generarSolicitud el cual consiste donde el cliente va a ingresar la fecha del evento con su correspondiente fecha de
     * solicitud generada en el instante y el respectivo cliente, ademas de guardarla en el archivo solicitudes.txt las informaciones
     * respectivas y mostrara un mensaje de la solicitud registrada con un toString de la clase Solicitudes.
     * @param fechaE
     * @param fechaS
     * @param evento
     * @param cliente 
     */
    public void generarSolicitud(Date fechaE,Date fechaS,String evento,Cliente cliente){
        Solicitud solicitud = new Solicitud(cliente,fechaE,fechaS,evento);
        String linea=solicitud.getId_solicitud()+","+nombre+","+solicitud.getPlanificador().getNombre()+","+formato.format(fechaS)+","+formato.format(fechaE)+","+solicitud.getEstado()+","+solicitud.getTipoEvento();
        ManejoArchivos.EscribirArchivo("solicitudes.txt",linea);
        System.out.println("/***********SOLICITUD REGISTRADA***********/");
        System.out.println("/*                                      */");
        System.out.println("/******************************************/");
        System.out.print(solicitud.toString());
        System.out.println("**Se ha registrado su solicitud, pronto el planificador se conectara con usted por \n telefono o video conferencia para completar el proceso de recoleccion de datos.\n");
        }                        
    public OrdenPago generarPago(String codOrden){
        return null;
    }
    }
   
