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
import tipos.*;

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

    public static ArrayList<Evento> getEventos() {
        return eventos;
    }

    public static void setEventos(ArrayList<Evento> eventos) {
        Planificador.eventos = eventos;
    }
    
    /**
     * este metodo nos permitira agregar todas las solicitudes de correspondiente planificador que se encuentre dentro del sistema
     * donde leeremos el archivo solicitudes.txt y recorremos linea por linea comparando si tiene el mismo nombre y es de tipo cliente.
     * @param planificador 
     */
    public void obtenerSolicitudes(Planificador planificador){
        solicitudes.clear();
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
/**
 * Recupera todos los Eventos que le pertenecen al planificador y los agregas al arraylist de eventos del planificdor.
 */    
    public void recuperarEventos(){
      eventos.clear();
       ArrayList<String> leventos = ManejoArchivos.LeeFichero("eventos.txt");
       for(String linea: leventos){
           String listeventos[]= linea.split(",");
           if (listeventos[7].equals(nombre)){
           switch (listeventos[2]){
               case "BODA":
                   eventos.add((Evento)new Boda(listeventos[0],listeventos[1],listeventos[2],listeventos[3],listeventos[4],listeventos[5],listeventos[6],listeventos[7],listeventos[8],listeventos[9],listeventos[10]));
                   break;
               case "INFANTIL":
                   eventos.add((Evento)new Infantil(listeventos[0],listeventos[1],listeventos[2],listeventos[3],listeventos[4],listeventos[5],listeventos[6],listeventos[7],listeventos[8],listeventos[9],listeventos[10],listeventos[11],listeventos[12]));
                   break;
               case "EMPRESARIAL":
                   eventos.add((Evento)new Empresarial(listeventos[0],listeventos[1],listeventos[2],listeventos[3],listeventos[4],listeventos[5],listeventos[6],listeventos[7],listeventos[8],listeventos[9],listeventos[10])); 
                   break;
           }     
       }
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
    /**
     * Permite consultar las ordenes de pago aprobadas que le pertenecen al planificador y poder observar
     * cuando se realizo dicho pago.
     * @param id_ordenPago
     * @return 
     */
      public boolean consultarOrdenPago(String id_ordenPago){
        for(OrdenPago orden:ordenPago){
            if(orden.getCodOrden().equals(id_ordenPago)){
                if(orden.getEstadoOrden().equals(TipoEstadoO.APROBADO)){
                    System.out.println("El pago de este evento se ha realizado: "+formato.format(orden.getFechaRegistro()));
                    return false;
                }
                else        
                    System.out.println("El pago de este evento no a sido generado");
            }
        }
        System.out.println("Ingrese un id de orden correcto");
        return true;     
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
    /**
     * Permite registrar el adicional que no posea descripcion para poder trabajar con el sin que haya sido guardado todavia.
     * @param cantidad
     * @param adicional 
     */
    public void registrarAdicional(int cantidad,String adicional){
        evento.creapreAdicional(cantidad,adicional,evento);
        System.out.println("Total:"+evento.getPreadicional().getValorTotal());
    }
    /**
     * Permite registrar el adicional que  posea descripcion para poder trabajar con el sin que haya sido guardado todavia.
     * @param cantidad
     * @param adicional
     * @param descripcion 
     */
    public void registrarAdicional(int cantidad,String adicional,String descripcion){
        evento.creapreAdicional(cantidad,adicional,evento,descripcion);
        System.out.println("Total:"+evento.getPreadicional().getValorTotal());
    }
    /**
     * Permite generar agregar adiccionales al evento y presentando el total a pagar del evento
     */
    public void agregaAdicional(){
        evento.aggAdicional();
        System.out.println("Se ha agregado su eleccion.");
        System.out.println("Total a pagar: "+evento.getPrecioTotal());
    }
    /**
     * Permite cambiar de Estado de solicitud cuando el evento haya sigo generado por el planificador.
     * @param id_solicitud 
     */
    public void cambiaEstadoS(String id_solicitud){
        int indice =0;
        for(Solicitud solicitud:solicitudes){
            if(solicitud.getId_solicitud().equals(id_solicitud)){
                indice = solicitudes.indexOf(solicitud);
                solicitud.setEstado(TipoEstadoS.valueOf("APROBADO"));
                solicitudes.set(indice, solicitud);
            }
            }
        ManejoArchivos.sobrescrituraASolicitudes("solicitudes.txt", solicitudes);
        }
    /**
     * Metodo que permite cambiar el estado de Evento cuando el planificador haya ingresado el id de la orden de pago
     * y preguntarle si desea aprobarla.
     * @param id_solicitud 
     */
    public void cambiaEstadoE(String id_solicitud){
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
        ManejoArchivos.sobrescrituraAEvento(eventos,"eventos.txt");
        }
    
/**
 * Metodo que permite verificar si el adiccional ya le pertenece al evento o no solo para tipos de adicionales
 * que no tengas descricion.
 * @param tipoAdicional
 * @return 
 */
    public boolean verificacionAd(String tipoAdicional){
        return evento.verificacionAd(tipoAdicional);
    }
    /**
     * Metodo que permite verificar si el adiccional ya le pertenece al evento o no solo para tipos de adicionales
     * que tengan descripcion.
     * @param tipoAdicional
     * @param descripcion
     * @return 
     */
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
         System.out.println(ordenPago.getEstadoOrden());
         String linea=ordenPago.getCodOrden()+","+ordenPago.getEvento().getCodigoEvento()+","+ordenPago.getEvento().getPrecioTotal()+","+ordenPago.getEstadoOrden();
         ManejoArchivos.EscribirArchivo("ordenPago.txt",linea);
         System.out.println("/***********ORDEN DE PAGO*****************/");
         System.out.println("/*                                        */");
         System.out.println("/******************************************/");
         System.out.print(ordenPago.toString());
     }
     /**
      * Metodo que permite recuperar las ordenes de pagos del planificador ya sean que esten aprobadas o pendientes.
      */
     public void  OrdenesDePago(){
         ordenPago.clear();
        ArrayList<String> codigo= ManejoArchivos.LeeFichero("ordenPago.txt");
        for(String cod: codigo){
           String listcod[]=cod.split(",");
           if(listcod.length==6){ 
               OrdenPago ordenPago= new OrdenPago(listcod[0],listcod[1],listcod[2],listcod[3],listcod[4],listcod[5]);
               if (ordenPago.getEvento().getPlanificador().getNombre().equals(nombre)&&String.valueOf(ordenPago.getEstadoOrden()).equals("APROBADO")&&String.valueOf(ordenPago.getEvento().getEstado()).equals("PENDIENTE")){
                        this.ordenPago.add(ordenPago); 
           }
           }
        }       
    }
     /**
      * Permite recuperar el numero total de ordenes de pago aprobadas para el planificador.
      * @return 
      */
     public int numeroOrdenC(){       
         int i =0;
         for(OrdenPago orden1: ordenPago){
             if(String.valueOf(orden1.getEstadoOrden()).equals("APROBADO"))
                 i++;
         }
         return i;
     }
     /**
      * Metodo para poder presentar por pantalla los eventos que le pertencen al planificador de acuerdo a la opcion 
      * que desee, ademas de presentar un mensaje de acuerdo al tipo de evento.
      * @param opcion 
      */
     public void conteoEventos(String opcion){
         int num=0;
         Evento evento= null ;
         for (Evento evento1: eventos){
             if (String.valueOf(evento1.getTipoEvento()).equals("BODA")&& opcion.equals("1")){
                 num++;
                 evento = evento1;
             }
             else if (String.valueOf(evento1.getTipoEvento()).equals("EMPRESARIAL")&& opcion.equals("3")){
                 num++;
                 evento = evento1;
             }
             else if (String.valueOf(evento1.getTipoEvento()).equals("INFANTIL")&& opcion.equals("2")){
                 num++;
                 evento = evento1;
             }
         }
         if (opcion.equals("1")){
         System.out.println("Tiene "+num+" Bodas asignadas ");
         if(evento != null){
         Boda boda =(Boda) evento;
         boda.mostrarMensaje();
         }
         }
         else if (opcion.equals("2")){
         System.out.println("Tiene "+num+" fiestas Infantiles asignadas ");
                  if(evento != null){ 
         Infantil infantil =(Infantil) evento;
         infantil.mostrarMensaje();
         }
         }
         else if (opcion.equals("3")){
         System.out.println("Tiene "+num+" fiestas Empresariales asignadas");
         if(evento != null){
         Empresarial empresarial =(Empresarial) evento;
         empresarial.mostrarMensaje();
         }
         }
     }    
}
