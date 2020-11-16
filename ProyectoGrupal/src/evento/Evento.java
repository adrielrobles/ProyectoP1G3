/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evento;

import java.util.ArrayList;
import java.util.Date;
import tipos.TipoEstadoS;
import usuario.Cliente;
import usuario.Planificador;
import usuario.Solicitud;

/**
 *
 * @author ErikaVilla
 */
public class Evento {
    private TipoEvento tipoEvento;
    private Date fecha;
    private Double precioTotal;
    private int codigoEvento;
    private Cliente cliente;
    private Planificador planificador;
    private String lugar;
    private String hora_Ini;
    private String hora_Fin;
    private int capacidad;
    private TipoEstadoS estado;
    private ArrayList<String> elementos_ad;
    
    public static void mostrarMensaje(){
    
}
    private boolean validarTiempo(Date fecha){
        return false;
        
    }
    
  //  private int  generarCodigoE(ArrayList<int>eventos, ArrayList<int>){
    //    return 0;
        
    //}
    
    public ArrayList<String> addAddicionales(String tipo){
        return null;
        
    }
    
    
    
    
    
}
