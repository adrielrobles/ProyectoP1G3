/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import tipos.TipoEstadoE;
import tipos.TipoEvento;
import usuario.Cliente;
import usuario.Planificador;
import usuario.Usuario;

/**
 *
 * @author ErikaVilla
 */
public class Boda extends Evento{
    private final double precioBase=3500;
    protected boolean vehiculo;

    /**
     * Constructor creado para poder crear un Objeto Boda con sus respectivos atributos y su precio base
     * @param fecha
     * @param cliente
     * @param planificador
     * @param tipoEvento 
     */
    
    public Boda(Date fecha, Cliente cliente, Planificador planificador,TipoEvento tipoEvento) {
        super(fecha, cliente, planificador, tipoEvento);
        precioTotal= precioBase;
    }
    //83,Adriel,BODA,12/12/2024,15:00,03:00,200,Jose,PENDIENTE,DURAN,Si Aplica
    public Boda(String codigoEvento, String cliente, String tipoEvento,String fecha,String hora_Ini, String hora_Fin, String capacidad, String planificador, String estado, String lugar, String vehiculo){
        super(codigoEvento,cliente,tipoEvento,fecha,hora_Ini,hora_Fin,capacidad,planificador,estado,lugar);
        if(vehiculo.equalsIgnoreCase("SI APLICA")){
            this.vehiculo = true;
        }else{
            this.vehiculo = false;
        }
    }
    
    //Getters y Setters
    public boolean isVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(boolean vehiculo) {
        this.vehiculo = vehiculo;
    }
    
    
}
