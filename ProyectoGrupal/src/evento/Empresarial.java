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
    //String lineaE=empresarial.getCodigoEvento()+","+empresarial.getCliente().getNombre()+","+empresarial.getTipoEvento()+","+empresarial.getFecha()+","+formatoh.format(empresarial.getHora_Ini())+","+formatoh.format(empresarial.getHora_Fin())+","+empresarial.getCapacidad()+","+empresarial.getPlanificador().getNombre()+","+empresarial.getEstado()+","+empresarial.getLugar()+","+empresarial.getCantidadPersonas();
                
    public Empresarial(String codigoEvento, String cliente, String tipoEvento,String fecha,String hora_Ini, String hora_Fin, String capacidad, String planificador, String estado, String lugar, String cantidadPersonas){
        super(codigoEvento,cliente,tipoEvento,fecha,hora_Ini,hora_Fin,capacidad,planificador,estado,lugar);
        this.cantidadPersonas=Integer.valueOf(cantidadPersonas);
    }
    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }
    @Override
     public void mostrarMensaje(){
         System.out.println("Recuerde que el transporte al lugar del evento es gratuito en un próximo evento, si lo organizan con el mismo planificador.");
     }
    
}
