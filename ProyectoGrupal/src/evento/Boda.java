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
    /**
     * Constructor que sirve para poder instanciar un evento de tipo Boda al leer el archivo eventos.txt.
     * @param codigoEvento
     * @param cliente
     * @param tipoEvento
     * @param fecha
     * @param hora_Ini
     * @param hora_Fin
     * @param capacidad
     * @param planificador
     * @param estado
     * @param lugar
     * @param vehiculo 
     */
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
    
    /**
     * Metodo de sobrescritura de mostrarMensaje de parte de Boda.
     */
    @Override
     public void mostrarMensaje(){
         System.out.println(" Recuerde que los novios tendrán un 25% de descuento si compran sus tickets de luna de miel en la aerolínea LATAM.");
     }
    
    
}
