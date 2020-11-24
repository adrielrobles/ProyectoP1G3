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
public class Empresarial extends Evento{
    private final double precioBase= 200;
    private int cantidadPersonas;
/**
 * Constructor creado al momento de ingresar como evento Empresarial con su respectivo precio Base.
 * @param fecha
 * @param cliente
 * @param planificador
 * @param tipoEvento 
 */
    public Empresarial(Date fecha, Cliente cliente, Planificador planificador,TipoEvento tipoEvento) {
        super(fecha, cliente, planificador,tipoEvento);
        precioTotal= precioBase;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }
    
    
}
