/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import ManejoArchivo.ManejoArchivos;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import tipos.TipoEstadoS;

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
    private void agregarSolicitud() {
        ArrayList<String> solicitudes = ManejoArchivos.LeeFichero("solicitudes.txt");
        Solicitud solNueva = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String codigo = solNueva.generarCodigo();
        String nombreC = solNueva.cliente.getNombre();
        String nomPlanificador = solNueva.planificador.getNombre();
        Date fechasSol = solNueva.getFechaEvento();
        String fechasSoli = sdf.format(fechasSol);
        Date fechaEven = solNueva.getFechaSolicitud();
        String fechaEven1 = sdf.format(fechaEven);
        String estado = TipoEstadoS.PENDIENTE.toString();
        solicitudes.add(codigo);
        solicitudes.add(nombreC);
        solicitudes.add(nomPlanificador);
        solicitudes.add(fechasSoli);
        solicitudes.add(fechaEven1);
    }
    public Solicitud generarSolicitud(){
        
        
        return null;
        
    }
    public OrdenPago generarPago(String codOrden){
        return null;
        
    }
    
    

}
