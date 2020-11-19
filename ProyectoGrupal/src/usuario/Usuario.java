/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import java.text.SimpleDateFormat;

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
    
    


    
}
