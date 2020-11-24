/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evento;

import java.util.Date;
import tipos.TipoEvento;
import usuario.Cliente;
import usuario.Planificador;

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
    
    //Getters y Setters
    public boolean isVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(boolean vehiculo) {
        this.vehiculo = vehiculo;
    }
    
    
}
