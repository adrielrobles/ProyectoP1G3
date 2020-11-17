/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

/**
 *
 * @author ErikaVilla
 */
/**
 * Crear la clase hija Cliente de la clase padre Usuario con sus respectivos atributos.
 * @author Adriel Robles
 */
public class Cliente extends Usuario {
    private int telefono;
    private String correoElectronico;
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
    public Cliente(String nombre, String apellido, String usuario, String contrasena, char tipo,int telefono, String correoElectronico) {
        super(nombre, apellido, usuario, contrasena, tipo);
        this.telefono=telefono;
        this.correoElectronico=correoElectronico;
    }
    
    public Solicitud generarSolicitud(){
        return null;
        
    }
    public OrdenPago generarPago(String codOrden){
        return null;
        
    }
    
    

}
