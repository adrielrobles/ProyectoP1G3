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
    
}
