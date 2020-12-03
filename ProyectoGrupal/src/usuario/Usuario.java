/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import ManejoArchivo.ManejoArchivos;
import Sistema.Sistema;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author ErikaVilla
 */
public abstract class Usuario {
    protected String nombre;
    protected String apellido;
    protected String usuario;
    protected String contrasena;
    protected char tipo;
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    protected ArrayList<OrdenPago> ordenPago = new ArrayList<>();

    //GETTERS

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public char getTipo() {
        return tipo;
    }
    //SETTERS

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }
     public ArrayList<OrdenPago> getOrdenPago() {
        return ordenPago;
    }

    public void setOrdenPago(ArrayList<OrdenPago> ordenPago) {
        this.ordenPago = ordenPago;
    }
     /*
    Creacion de constructor el cual va recibir como parametros todas las varibles de instancia
    */
    public Usuario(String nombre, String apellido, String usuario, String contrasena, char tipo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.tipo = tipo;
    }
    
     public static Cliente buscarCliente(String nombre){
         for(Usuario u:Sistema.getUsuarios()){
             if(u.getNombre().equals(nombre)&&(u.getTipo()=='C')){
                 return (Cliente)u;
             }
         }return null;
    }  
    public static Planificador buscarPlanificador(String nombre){
         for(Usuario u:Sistema.getUsuarios()){
             if(u.getNombre().equals(nombre)&&(u.getTipo()=='P')){
                 return (Planificador)u;
             }
         }return null;
    }  
        
}
