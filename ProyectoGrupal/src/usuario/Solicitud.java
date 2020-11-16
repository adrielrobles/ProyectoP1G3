/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

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
    
    public String generarCodigo(){
        return null;
        
    }
    public Planificador buscarPlanificador(ArrayList<Planificador>planificadores){
        return null;
        
    }

    
}
