/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import ManejoArchivo.ManejoArchivos;
import Sistema.Sistema;
import evento.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import tipos.TipoEstadoE;
import tipos.TipoEstadoO;
import tipos.TipoEstadoS;
import tipos.TipoEvento;

/**
 *
 * @author ErikaVilla
 */
public class Planificador extends Usuario {
    private Evento evento;
    private static ArrayList<Solicitud> solicitudes = new ArrayList<>();
    private static ArrayList<Evento> eventos = new ArrayList<>();
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat formatoh = new SimpleDateFormat("HH:mm");
    /**
     * Constructor creado para poder crear un planificador cuando se inicie el programa.
     * @param nombre
     * @param apellido
     * @param usuario
     * @param contrasena
     * @param tipo 
     */
    public Planificador(String nombre, String apellido, String usuario, String contrasena, char tipo) {
        super(nombre, apellido, usuario, contrasena, tipo);
    }  

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
    
    /**
     * este metodo nos permitira agregar todas las solicitudes de correspondiente planificador que se encuentre dentro del sistema
     * donde leeremos el archivo solicitudes.txt y recorremos linea por linea comparando si tiene el mismo nombre y es de tipo cliente.
     * @param planificador 
     */
    public void obtenerSolicitudes(Planificador planificador){
        ArrayList<String> presolicitudes = ManejoArchivos.LeeFichero("solicitudes.txt");
         for (String pre : presolicitudes) {
            String listsoli[] = pre.split(",");
            if(listsoli[2].equals(nombre)&&listsoli[5].equals("PENDIENTE")){
                try {
                    solicitudes.add(new Solicitud(listsoli[0],buscarCliente(listsoli[1]),planificador,formato.parse(listsoli[3]),formato.parse(listsoli[4]),listsoli[5],listsoli[6]));
                } catch (ParseException ex) {
                    System.out.println(ex);
                }
            }       
        }
        
    }    
    public void obtenerEventos(){
        for(OrdenPago orden : ordenPago){
            
        }
    }
    /**
     * Este metodo permitira consultar las solicitudes pendiente que tiene el planificador leyendo el arrayList de solicitudes que 
     * posee el planificador.
     */
    public void consularSolicitudP(){
       int i=1;
       for(Solicitud solici:solicitudes){
           if(String.valueOf(solici.getEstado()).equals("PENDIENTE")){
               System.out.println(i+".- "+solici.getId_solicitud()+" - "+formato.format(solici.getFechaSolicitud()));
               i++;
           }
       }           
    }
    /**
     * Metodo para retornar el Evento generado segun el Id de la solicitud dependiendo del tipo de Evento
     * @param id_solicitud
     * @return 
     */
    public boolean consularSolicitud(String id_solicitud){
        for(Solicitud solici:solicitudes){
            if(solici.getId_solicitud().equals(id_solicitud)){
                System.out.print(solici.toString());
                switch (solici.getTipoEvento()) {
                    case BODA:
                        evento = new Boda(solici.getFechaEvento(),solici.getCliente(),solici.getPlanificador(),solici.getTipoEvento());
                        return false;
                    case EMPRESARIAL:
                        evento = new Empresarial(solici.getFechaEvento(),solici.getCliente(),solici.getPlanificador(),solici.getTipoEvento());
                        return false;
                    case INFANTIL:
                        evento = new Infantil(solici.getFechaEvento(),solici.getCliente(),solici.getPlanificador(),solici.getTipoEvento());
                        return false;
                    default:
                        break;
                }
                System.out.println("Precio Base: "+evento.getPrecioTotal());
            }
        }
        return true;     
    }
      public boolean consultarOrdenPago(String id_ordenPago){
        for(OrdenPago orden:ordenPago){
            if(orden.getCodOrden().equals(ordenPago)){
                if(orden.getEstadoOrden().equals(TipoEstadoO.APROBADO)){
                    System.out.println("El pago de este evento se ha realizado: "+formato.format(orden.getFechaRegistro()));
                    return true;
                }
                else        
                    System.out.println("El pago de este evento no a sido generado");
            }
        }
        System.out.println("Ingrese un id de orden correcto");
        return false;     
    }
    /**.
     * Metodo para registrar los demas datos de un evento Boda generados por el usuario.
     * @param fechaI
     * @param fechaF
     * @param capacidad
     * @param vehiculo
     * @param lugar 
     */
    public void registrarEvento(Date fechaI,Date fechaF,int capacidad,String vehiculo,String lugar){
        evento.setLugar(lugar);
        evento.setHora_Ini(fechaI);
        evento.setHora_Fin(fechaF);
        evento.setCapacidad(capacidad);
        Boda boda = (Boda) evento;
        if(vehiculo.equalsIgnoreCase("S"))
            boda.setVehiculo(true);
        else
            boda.setVehiculo(false);
        evento= boda;
    }
    /**
     * Metodo de sobrecarga para registrar los demas datos de un evento Infantil generado por el planificador.
     * @param fechaI
     * @param fechaF
     * @param capacidad
     * @param personajD
     * @param cantSor
     * @param juegoF
     * @param lugar 
     */
    public void registrarEvento(Date fechaI,Date fechaF,int capacidad,int personajD,int cantSor,String juegoF,String lugar){
        evento.setLugar(lugar);
        evento.setHora_Ini(fechaI);
        evento.setHora_Fin(fechaF);
        evento.setCapacidad(capacidad);
        Infantil infantil = (Infantil) evento;
        infantil.setCantidadDisfrazados(personajD);
        infantil.setCantidadSorpresas(cantSor);
        if(juegoF.equalsIgnoreCase("S"))
            infantil.setJuegos(true);
        else
            infantil.setJuegos(false);
        evento = infantil;
    }
    /**
     * Metodo de sobrecarga para registrar los demas datos de un evento Empresarial generado por el planificador.
     * @param fechaI
     * @param fechaF
     * @param capacidad
     * @param cantVehiE
     * @param lugar 
     */
    public void registrarEvento(Date fechaI,Date fechaF,int capacidad,int cantVehiE,String lugar){
        evento.setLugar(lugar);
        evento.setHora_Ini(fechaI);
        evento.setHora_Fin(fechaF);
        evento.setCapacidad(capacidad);
        Empresarial empresarial = (Empresarial) evento;
        empresarial.setCantidadPersonas(cantVehiE);
        evento=empresarial;
    }
    
    public void registrarAdicional(int cantidad,String adicional){
        evento.creapreAdicional(cantidad,adicional,evento);
        System.out.println("Total:"+evento.getPreadicional().getValorTotal());
    }
    public void registrarAdicional(int cantidad,String adicional,String descripcion){
        evento.creapreAdicional(cantidad,adicional,evento,descripcion);
        System.out.println("Total:"+evento.getPreadicional().getValorTotal());
    }
    public void agregaAdicional(){
        evento.aggAdicional();
        System.out.println("Se ha agregado su eleccion.");
        System.out.println("Total a pagar: "+evento.getPrecioTotal());
    }
    public void cambiaEstadoS(String id_solicitud){
        int indice =0;
        for(Solicitud solicitud:solicitudes){
            if(solicitud.getId_solicitud().equals(id_solicitud)){
                indice = solicitudes.indexOf(solicitud);
                solicitud.setEstado(TipoEstadoS.valueOf("APROBADO"));
                solicitudes.set(indice, solicitud);
            }
            }
        ManejoArchivos.sobrescrituraA("solicitudes.txt", solicitudes);
        }
    public void cambiaEstadoO(String id_solicitud){
        int indice =0;
        for(OrdenPago orden:ordenPago){
            if(orden.getCodOrden().equals(id_solicitud)){
                for(Evento event:eventos){
                    if(orden.getEvento().getCodigoEvento().equals(event.getCodigoEvento())){
                        event.setEstado(TipoEstadoE.CONFIRMADO);
                    }
                }
            }
            }
        ManejoArchivos.sobrescrituraA("eventos.txt", solicitudes);
        }
    

    public boolean verificacionAd(String tipoAdicional){
        return evento.verificacionAd(tipoAdicional);
    }
    public boolean verificacionAd(String tipoAdicional,String descripcion){
        return evento.verificacionAd(tipoAdicional,descripcion);
    }
    /**
     * Metodo para guardar los eventos en el archivo eventos cuando el planificador haya generado la orden de Pago ademas que se van a 
     * guardar los adicionales en el archivo adicionales.txt con su respectivo codigo.
     */
     public void crearEvento(){
        switch(evento.getTipoEvento()){
            case BODA:
                Boda boda = (Boda) evento;
                String vehiculo ="";
                if(boda.isVehiculo()){
                    vehiculo="SI APLICA";
                }
                else 
                    vehiculo="NO APLICA";
                String lineaB=boda.getCodigoEvento()+","+boda.getCliente().getNombre()+","+boda.getTipoEvento()+","+formato.format(boda.getFecha())+","+formatoh.format(boda.getHora_Ini())+","+formatoh.format(boda.getHora_Fin())+","+boda.getCapacidad()+","+boda.getPlanificador().getNombre()+","+boda.getEstado()+","+boda.getLugar()+","+vehiculo;
                ManejoArchivos.EscribirArchivo("eventos.txt",lineaB);
                evento.crearAdicionales();
                break;
            case EMPRESARIAL:
                Empresarial empresarial = (Empresarial) evento;
                String lineaE=empresarial.getCodigoEvento()+","+empresarial.getCliente().getNombre()+","+empresarial.getTipoEvento()+","+formato.format(empresarial.getFecha())+","+formatoh.format(empresarial.getHora_Ini())+","+formatoh.format(empresarial.getHora_Fin())+","+empresarial.getCapacidad()+","+empresarial.getPlanificador().getNombre()+","+empresarial.getEstado()+","+empresarial.getLugar()+","+empresarial.getCantidadPersonas();
                ManejoArchivos.EscribirArchivo("eventos.txt",lineaE);
                evento.crearAdicionales();
                break;
            case INFANTIL:
                Infantil infantil = (Infantil) evento;
                String juegos ="";
                if(infantil.isJuegos()){
                    juegos="SI APLICA";
                }
                else 
                    juegos="NO APLICA";
                String lineaI=infantil.getCodigoEvento()+","+infantil.getCliente().getNombre()+","+infantil.getTipoEvento()+","+formato.format(infantil.getFecha())+","+formatoh.format(infantil.getHora_Ini())+","+formatoh.format(infantil.getHora_Fin())+","+infantil.getCapacidad()+","+infantil.getPlanificador().getNombre()+","+infantil.getEstado()+","+infantil.getLugar()+","+infantil.getCantidadDisfrazados()+","+infantil.getCantidadSorpresas()+","+juegos;
                ManejoArchivos.EscribirArchivo("eventos.txt",lineaI);
                evento.crearAdicionales();
                break;
        }
        }
     /**
      * Metodo para poder guardar la orden de pago con sus respectivos datos en el archivo ordenPago.txt.
      */
     public void generarOrden(){
         OrdenPago ordenPago = new OrdenPago(evento);
         String linea=ordenPago.getCodOrden()+","+ordenPago.getEvento().getCodigoEvento()+","+ordenPago.getEvento().getPrecioTotal()+","+ordenPago.getEstadoOrden();
         ManejoArchivos.EscribirArchivo("ordenPago.txt",linea);
         System.out.println("/***********ORDEN DE PAGO*****************/");
         System.out.println("/*                                        */");
         System.out.println("/******************************************/");
         System.out.print(ordenPago.toString());
     }
     public void  OrdenesDePago(){
        ArrayList<String> codigo= ManejoArchivos.LeeFichero("ordenPago.txt");
        for(String cod: codigo){
           String listcod[]=cod.split(",");
               OrdenPago ordenPago= new OrdenPago(listcod[0],listcod[1],listcod[2],listcod[3],listcod[4],listcod[5]);
               if (ordenPago.getEvento().getPlanificador().getNombre().equals(nombre)&&ordenPago.getEstadoOrden().equals(TipoEstadoO.APROBADO)){
                        this.ordenPago.add(ordenPago); 
           }
        }       
    }
     
//     public Planificador buscarPlanificador(ArrayList<Usuario> planificador) {
//        ArrayList<Planificador> unPlanificador = new ArrayList<>();
//        for (Usuario p: planificador){
//            if (p.getTipo()=='P'){
//                planificador.add(p);
//            }
//        }
//        Random rand = new Random();
//        int posicion = rand.nextInt(unPlanificador.size());
//        Planificador elegidoPlanificador = unPlanificador.get(posicion);
//        return elegidoPlanificador;
//    }

    
}
