/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import ManejoArchivo.ManejoArchivos;
import evento.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import tipos.TipoEstadoO;
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
    public OrdenPago(String codOrden,String evento, String totalPagar, String estadoOrden){
        this.codOrden=codOrden;
        this.evento =conseguirEvento(evento);
        this.totalPagar=Double.parseDouble(totalPagar);
        this.estadoOrden=TipoEstadoO.valueOf(estadoOrden);
    }
    public OrdenPago(String codOrden,String evento, String totalPagar, String estadoOrden,String codTransa,String FechaRegistro){
        this(codOrden,evento,totalPagar,estadoOrden);  
        this.codTransa=codTransa;
        try {
            this.FechaRegistro=formato.parse(FechaRegistro);
        } catch (ParseException ex) {
            Logger.getLogger(OrdenPago.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                formato.format(evento.getFecha())+"\nADICIONALES: "+evento.presentarAdici()+"\nTOTAL A PAGAR: "+evento.getPrecioTotal()+"\n";      
    }
    //83,Adriel,BODA,12/12/2024,15:00,03:00,200,Jose,PENDIENTE,DURAN,Si Aplica
    public Evento conseguirEvento(String codigoEvento){
       ArrayList<String> eventos = ManejoArchivos.LeeFichero("eventos.txt");
       for(String linea: eventos){
           String listeventos[]= linea.split(",");
           if (listeventos[0].equals(codigoEvento)){
           switch (listeventos[2]){
               case "BODA":
                   return (Evento)new Boda(listeventos[0],listeventos[1],listeventos[2],listeventos[3],listeventos[4],listeventos[5],listeventos[6],listeventos[7],listeventos[8],listeventos[9],listeventos[10]);                              
               case "INFANTIL":
                   return (Evento)new Infantil(listeventos[0],listeventos[1],listeventos[2],listeventos[3],listeventos[4],listeventos[5],listeventos[6],listeventos[7],listeventos[8],listeventos[9],listeventos[10],listeventos[11],listeventos[12]);
               case "EMPRESARIAL":
                   return (Evento)new Empresarial(listeventos[0],listeventos[1],listeventos[2],listeventos[3],listeventos[4],listeventos[5],listeventos[6],listeventos[7],listeventos[8],listeventos[9],listeventos[10]);       
           }     
       }
       }
      return null;  
    }
    
}
