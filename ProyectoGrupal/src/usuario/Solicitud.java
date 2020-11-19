/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import tipos.TipoEstadoS;
import Sistema.*;

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

    public Solicitud( Cliente cliente, Date fechaSolicitud, Date fechaEvento, TipoEstadoS estado) {
    
        this.cliente = cliente;
        id_solicitud = generarCodigo();
        planificador = buscarPlanificador();   
        this.fechaSolicitud = fechaSolicitud;
        this.fechaEvento = fechaEvento;
        this.estado = estado;
    }

    private String generarCodigo() {
        double codigo = Math.random() * (100);  
        String codigos = Double.toString(codigo);
        return codigos;

    }

    public Planificador buscarPlanificador() {
        ArrayList<Planificador> unPlanificador = new ArrayList<>();
        for (Usuario p: Sistema.getUsuarios()){
            if (p.getTipo()=='P'){
                unPlanificador.add((Planificador)p);
            }
        }
        Random rand = new Random();
        int posicion = rand.nextInt(unPlanificador.size());
        Planificador elegidoPlanificador = unPlanificador.get(posicion);
        return elegidoPlanificador;
    }
}
