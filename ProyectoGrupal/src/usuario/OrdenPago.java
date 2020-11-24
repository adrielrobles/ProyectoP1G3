/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import ManejoArchivo.ManejoArchivos;
import evento.Evento;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import tipos.TipoEstadoO;
import tipos.TipoEstadoS;

/**
 *
 * @author ErikaVilla
 */
public class OrdenPago {
    private String codTransa;
    private String codOrden;
    private Evento evento;
    private Date FechaRegistro;
    private TipoEstadoO estadoOrden;
    private double totalPagar;
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    
    public OrdenPago(Evento evento){
        codOrden = generarCodigo();
        this.evento = evento;
        totalPagar = evento.getPrecioTotal();
        estadoOrden = TipoEstadoO.PENDIENTEPAGO;
    }
    //Getters y Setters

    public double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }
    public String getCodTransa() {
        return codTransa;
    }

    public void setCodTransa(String codTransa) {
        this.codTransa = codTransa;
    }

    public String getCodOrden() {
        return codOrden;
    }

    public void setCodOrden(String codOrden) {
        this.codOrden = codOrden;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Date getFechaRegistro() {
        return FechaRegistro;
    }

    public void setFechaRegistro(Date FechaRegistro) {
        this.FechaRegistro = FechaRegistro;
    }

    public TipoEstadoO getEstadoOrden() {
        return estadoOrden;
    }

    public void setEstadoOrden(TipoEstadoO estadoOrden) {
        this.estadoOrden = estadoOrden;
    }
    /**
     * Genera el codigo unico a la Orden de pago al momento que se cree una.
     * @return String
     */
    private String generarCodigo() {
        boolean validacion=false;
        String codigos="";
        ArrayList <String> listaC=new ArrayList<>();
         for(String linea: ManejoArchivos.LeeFichero("ordenPago.txt")){
            listaC.add(linea.split(",")[0]);
        }
        while(validacion==false){
        double codigo = Math.random() * (100);  
        if(listaC.contains(String.valueOf(codigo))){
            validacion=false;
        }
        else{
            codigos = Integer.toString((int)codigo);
            validacion=true;
        }
        }
        return codigos;
    }
    /**
     * Metodo para presenta la orden de pago generada.
     * @return 
     */
    public String toString() {
        return "CODIGO PAGO: "+codOrden+"\nCLIENTE: " + evento.getCliente().getNombre().toUpperCase()+"\nEVENTO: "+evento.getTipoEvento()+"\nFECHA EVENTO: "+
                formato.format(evento.getFecha())+"\nADICIONALES: "+evento.presentarAdici()+"\nTOTAL A PAGAR: "+evento.getPrecioTotal();      
    }
    
    
    
}
