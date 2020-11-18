/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import tipos.TipoEstadoS;

/**
 *
 * @author ErikaVilla
 */
public class Solicitud {
    private String id_solicitud;
    public Cliente cliente;
    public Planificador planificador;
    private Date fechaSolicitud;
    private Date fechaEvento;
    private TipoEstadoS estado;
    
    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public void setFechaEvento(Date fechaEvento) {
       
        this.fechaEvento = fechaEvento;
    }

    public Date getFechaSolicitud() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaSolicitud = new Date();
        return fechaSolicitud;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public Solicitud(String id_solicitud, Cliente cliente, Planificador planificador, Date fechaSolicitud, Date fechaEvento, TipoEstadoS estado) {
        this.id_solicitud = id_solicitud;
        this.cliente = cliente;
        this.planificador = planificador;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaEvento = fechaEvento;
        this.estado = estado;
    }
    
    public String generarCodigo(){
        double codigo = Math.random()*(100) ;  // Esto da valores entre 0.0 y 7.0 excluido el 7.0
        String codigos =Double.toString(codigo);
        return codigos;
        
    }
    public Planificador buscarPlanificador(ArrayList<Planificador>planificadores){
        return null;
        
    }

    
}
