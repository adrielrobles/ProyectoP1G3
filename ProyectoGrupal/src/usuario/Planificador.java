/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import evento.Evento;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author ErikaVilla
 */
public class Planificador extends Usuario {
    ArrayList<Evento> listaEventos;
    
    public Planificador(String nombre, String apellido, String usuario, String contrasena, char tipo) {
        super(nombre, apellido, usuario, contrasena, tipo);
    }     
    public ArrayList<Solicitud> consultarSoliPen(){
        return null;
        
    }
    public Solicitud consularSolicitud(String id_solicitud){
        return null;
        
    }
    public Evento registrarEvento(String id_solicitud){
        return null;
        
    }
    public boolean confirmarEvento(String codOrden){
        return false;
        
    }
    public ArrayList<Evento> consultarEvento(Evento tipo){
        return null;
        
        
    }
     public Planificador buscarPlanificador(ArrayList<Usuario> planificador) {
        ArrayList<Planificador> unPlanificador = new ArrayList<>();
        for (Usuario p: planificador){
            if (p.getTipo()=='P'){
                planificador.add(p);
            }
        }
        Random rand = new Random();
        int posicion = rand.nextInt(unPlanificador.size());
        Planificador elegidoPlanificador = unPlanificador.get(posicion);
        return elegidoPlanificador;

    }
    
}
