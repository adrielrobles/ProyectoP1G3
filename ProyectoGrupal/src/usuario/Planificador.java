/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import ManejoArchivo.ManejoArchivos;
import Sistema.Sistema;
import evento.Evento;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import tipos.TipoEstadoS;

/**
 *
 * @author ErikaVilla
 */
public class Planificador extends Usuario {
    ArrayList<Evento> listaEventos;
    private static ArrayList<Solicitud> solicitudes = new ArrayList<>();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    
    public Planificador(String nombre, String apellido, String usuario, String contrasena, char tipo) {
        super(nombre, apellido, usuario, contrasena, tipo);
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
            if(listsoli[2].equals(nombre)&&listsoli[4].equals("P")){
                try {
                    solicitudes.add(new Solicitud(listsoli[0],buscarCliente(listsoli[1]),planificador,formato.parse(listsoli[3]),formato.parse(listsoli[4]),listsoli[5],listsoli[6]));
                } catch (ParseException ex) {
                    System.out.println(ex);
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
           if(solici.getEstado().equals(TipoEstadoS.PENDIENTE)){
               System.out.println(i+".- "+solici.getId_solicitud()+" - "+formato.format(solici.getFechaSolicitud()));
           }
       }           
    }
    public Solicitud consularSolicitud(String id_solicitud){
        return null;
        
    }
    public Evento registrarEvento(String id_solicitud){
        return null;
        
    }
    public boolean confirmarEvento(String codOrden){
        return false;
        
    }
    public ArrayList<Evento> consultarEvento(Evento tipo){
        return null;
        
        
    }
     public Planificador buscarPlanificador(ArrayList<Usuario> planificador) {
        ArrayList<Planificador> unPlanificador = new ArrayList<>();
        for (Usuario p: planificador){
            if (p.getTipo()=='P'){
                planificador.add(p);
            }
        }
        Random rand = new Random();
        int posicion = rand.nextInt(unPlanificador.size());
        Planificador elegidoPlanificador = unPlanificador.get(posicion);
        return elegidoPlanificador;

    }
     public Cliente buscarCliente(String nombre){
         
         for(Usuario u:Sistema.getUsuarios()){
             if(u.getNombre().equals(nombre)&&(u.getTipo()=='C')){
                 return (Cliente)u;
             }
         }return null;
    }
    
}
