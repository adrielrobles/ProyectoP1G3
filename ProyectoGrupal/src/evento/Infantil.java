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
public class Infantil extends Evento {
    private double precioBase = 300;
    private int cantidadDisfrazados;
    private int cantidadSorpresas;
    private boolean juegos;
/**
 * Constructor para crear un Evento Infantil con su respectivo precio Base
 * @param fecha
 * @param cliente
 * @param planificador
 * @param tipoEvento 
 */
    public Infantil(Date fecha, Cliente cliente, Planificador planificador,TipoEvento tipoEvento) {
        super(fecha, cliente, planificador,tipoEvento);
        precioTotal= precioBase;
    }
    /**
     *  Constructor que sirve para poder instanciar un evento de tipo Infantil al leer el archivo eventos.txt.    
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
     * @param cantidadDisfrazados
     * @param cantidadSorpresas
     * @param juegos 
     */
    public Infantil(String codigoEvento, String cliente, String tipoEvento,String fecha,String hora_Ini, String hora_Fin, String capacidad, String planificador, String estado, String lugar, String cantidadDisfrazados, String cantidadSorpresas, String juegos){
        super(codigoEvento,cliente,tipoEvento,fecha,hora_Ini,hora_Fin,capacidad,planificador,estado,lugar);    
        this.cantidadDisfrazados=Integer.parseInt(cantidadDisfrazados);
        this.cantidadSorpresas=Integer.parseInt(cantidadSorpresas);
        if (juegos.equalsIgnoreCase("SI APLICA")){
            this.juegos=true;
        }else{
            this.juegos=false;
        }
    }
    
    // Getters y Setters
    public int getCantidadDisfrazados() {
        return cantidadDisfrazados;
    }

    public void setCantidadDisfrazados(int cantidadDisfrazados) {
        this.cantidadDisfrazados = cantidadDisfrazados;
    }

    public int getCantidadSorpresas() {
        return cantidadSorpresas;
    }

    public void setCantidadSorpresas(int cantidadSorpresas) {
        this.cantidadSorpresas = cantidadSorpresas;
    }

    public boolean isJuegos() {
        return juegos;
    }

    public void setJuegos(boolean juegos) {
        this.juegos = juegos;
    }
    /**
     *  Metodo de sobrescritura de mostrarMensaje de parte de Infantil.
     */
    @Override
     public void mostrarMensaje(){
         System.out.println("Recuerde que se si realizan un próximo evento en las mismas instalaciones en un plazo de 6 meses se les otorgará en servicio de música gratuito.");
     }
    
}
