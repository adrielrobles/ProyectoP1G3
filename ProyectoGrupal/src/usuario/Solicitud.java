/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import ManejoArchivo.ManejoArchivos;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import tipos.TipoEstadoS;
import Sistema.*;
import tipos.TipoEvento;

/**
 *
 * @author ErikaVilla
 */
public class Solicitud {

    private String id_solicitud;
    private Cliente cliente;
    private Planificador planificador;
    private Date fechaSolicitud;
    private Date fechaEvento;
    private TipoEvento tipoEvento;
    private TipoEstadoS estado;
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    
    
/**
 * Contructor para crear una solicitud con respectivos parametros como cliente, fecha solicitud, fecha evento, tipo de evento
 * donde se puede observar que el id de solicitud se crea con el metodo generarCodigo y el planificador se le asignada aleatoriamente
 * con el metodo buscarPlanificador y el Estado de la solicitud va a estar pendiente
 * @param cliente
 * @param fechaSolicitud
 * @param fechaEvento
 * @param tipoevento 
 */
    public Solicitud(Cliente cliente,Date fechaSolicitud, Date fechaEvento,String tipoevento) {
        this.cliente = cliente;
        id_solicitud = generarCodigo();
        planificador = buscarPlanificador();   
        this.fechaSolicitud = fechaSolicitud;
        this.fechaEvento = fechaEvento;
        estado = TipoEstadoS.PENDIENTE;
        this.tipoEvento = TipoEvento.valueOf(tipoevento);

    }
     /**
     * Se agrego un constructor para generar las solicitudes al momento de leer el archivo solicitud
     * @param id_solicitud
     * @param cliente
     * @param planificador
     * @param fechaSolicitud
     * @param fechaEvento
     * @param estado
     * @param tipoEvento 
     */
    public Solicitud(String id_solicitud,Cliente cliente,Planificador planificador, Date fechaSolicitud,Date fechaEvento, String estado,String tipoEvento) {
        this.id_solicitud = id_solicitud;
        this.planificador=planificador;
        this.cliente=cliente;
        this.fechaSolicitud= fechaSolicitud;
        this.fechaEvento=fechaEvento;
        this.estado=TipoEstadoS.valueOf(estado);
        this.tipoEvento=TipoEvento.valueOf(tipoEvento);
        
        
    }
    //Getters y Setters
    
    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }
    
    public String getId_solicitud() {
        return id_solicitud;
    }

    public void setId_solicitud(String id_solicitud) {
        this.id_solicitud = id_solicitud;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Planificador getPlanificador() {
        return planificador;
    }

    public void setPlanificador(Planificador planificador) {
        this.planificador = planificador;
    }

    public TipoEstadoS getEstado() {
        return estado;
    }

    public void setEstado(TipoEstadoS estado) {
        this.estado = estado;
    }
    
/**
 * Metodo que va a retorna un Codigo de solicitud que sea diferentes a las codigos de las solicitudes que ya se han generado
 * donde leeremos el archivo solicitudes.txt y obtendremos todos los codigo de solicitudes y los guardaremos en una lista las
 * cuales pondremos una condicion si el codigo aleatorio contiene se seguira recorriendo el while hasta que se genere un codigo diferente.
 * @return codigos
 */
    private String generarCodigo() {
        boolean validacion=false;
        String codigos="";
        ArrayList <String> listaC=new ArrayList<>();
         for(String linea: ManejoArchivos.LeeFichero("solicitudes.txt")){
            listaC.add(linea.split(",")[0]);
        }
        while(validacion=false){
        double codigo = Math.random() * (100);  
        if(listaC.contains(codigo)){
            validacion=false;
        }
        else{
            codigos = Integer.toString((int)codigo);
            validacion=true;
        }
        }
        return codigos;
    }

    public Planificador buscarPlanificador() {
        ArrayList<Planificador> unPlanificador = new ArrayList<>();
        for (Usuario p: Sistema.getUsuarios()){
            if (p.getTipo()=='P'){
                unPlanificador.add((Planificador)p);
            }
        }
        Random rand = new Random();
        int posicion = rand.nextInt(unPlanificador.size());
        Planificador elegidoPlanificador = unPlanificador.get(posicion);
        return elegidoPlanificador;
    }
   

    public String toString() {
        return "CLIENTE: " + cliente.getNombre().toUpperCase()+" "+cliente.getApellido().toUpperCase()+"\nPLANIFICADOR ASIGNADO:"+
                planificador.getNombre().toUpperCase()+" "+planificador.getApellido().toUpperCase()+"\nFECHA REGISTRO: "+
                formato.format(fechaSolicitud)+"\nTIPO EVENTO: "+tipoEvento+"\nFECHA EVENTO: "+formato.format(fechaEvento)+"\n";
             
    }
   
    
}
