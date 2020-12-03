/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evento;

import ManejoArchivo.ManejoArchivos;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import tipos.TipoAdicional;
import tipos.TipoEstadoE;
import tipos.TipoEstadoS;
import tipos.TipoEvento;
import usuario.Cliente;
import usuario.Planificador;
import usuario.Solicitud;
import usuario.Usuario;

/**
 *
 * @author ErikaVilla
 */
public class Evento {
    protected TipoEvento tipoEvento;
    protected Date fecha;
    protected Double precioTotal;
    protected String codigoEvento;
    protected Cliente cliente;
    protected Planificador planificador;
    protected String lugar;
    protected Date hora_Ini;
    protected Date hora_Fin;
    protected int capacidad;
    protected TipoEstadoE estado;
    protected ArrayList<Adicional> elementos_ad = new ArrayList<>();
    protected Adicional preadicional;
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat formatoh = new SimpleDateFormat("HH:mm");
    /**
     * Constructor que se crea cuando el planificador consulta la solicitud.
     * @param fecha
     * @param cliente
     * @param planificador 
     * @param tipoEvento 
     */
    public Evento(Date fecha,Cliente cliente,Planificador planificador,TipoEvento tipoEvento){
        codigoEvento= generarCodigo();
        this.tipoEvento = tipoEvento;
        this.fecha=fecha;
        this.cliente=cliente;
        this.planificador = planificador;
        estado = TipoEstadoE.PENDIENTE;
    }

    public Evento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Evento(String codigoEvento, String cliente, String tipoEvento,String fecha,String hora_Ini, String hora_Fin, String capacidad, String planificador, String estado, String lugar){
        this.codigoEvento =codigoEvento;
        this.cliente = Usuario.buscarCliente(cliente);
        this.tipoEvento= TipoEvento.valueOf(tipoEvento);
        try {
            this.hora_Ini= formatoh.parse(hora_Ini);
            this.hora_Fin=formatoh.parse(hora_Fin);
            this.fecha= formato.parse(fecha);
        } catch (ParseException ex) {
            System.out.println("");
        }
        this.capacidad=Integer.parseInt(capacidad);
        this.planificador= Usuario.buscarPlanificador(planificador);
        this.estado= TipoEstadoE.valueOf(estado);
        this.lugar=lugar; 
    }
    // GETTERS Y SETTERS 

    public Adicional getPreadicional() {
        return preadicional;
    }

    public void setPreadicional(Adicional preadicional) {
        this.preadicional = preadicional;
    }
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getCodigoEvento() {
        return codigoEvento;
    }

    public void setCodigoEvento(String codigoEvento) {
        this.codigoEvento = codigoEvento;
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

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Date getHora_Ini() {
        return hora_Ini;
    }

    public void setHora_Ini(Date hora_Ini) {
        this.hora_Ini = hora_Ini;
    }

    public Date getHora_Fin() {
        return hora_Fin;
    }

    public void setHora_Fin(Date hora_Fin) {
        this.hora_Fin = hora_Fin;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public TipoEstadoE getEstado() {
        return estado;
    }

    public void setEstado(TipoEstadoE estado) {
        this.estado = estado;
    }

    public ArrayList<Adicional> getElementos_ad() {
        return elementos_ad;
    }

    public void setElementos_ad(ArrayList<Adicional> elementos_ad) {
        this.elementos_ad = elementos_ad;
    }
    /**
     * Metodo para crear un preAdicinal con el cual trabajaremos y decidiremos si agregar o no al Evento.
     * @param cantidad
     * @param adicional
     * @param evento 
     */
    public void creapreAdicional(int cantidad, String adicional, Evento evento){
        preadicional = new Adicional(evento,adicional,cantidad);
        precioTotal = precioTotal+preadicional.getValorTotal();
    }
    /**
     * Metodo de sobrecarga para los adicionales que poseen descripcion tales como MUSICA y BEBIDA.
     * @param cantidad
     * @param adicional
     * @param evento
     * @param descripcion 
     */
     public void creapreAdicional(int cantidad, String adicional, Evento evento,String descripcion){
        preadicional = new Adicional(evento,adicional,cantidad,descripcion);
        precioTotal = precioTotal+preadicional.getValorTotal();
    }
     /**
      *Agrega el preadicional a la lista de elementos_ad del evento cuando el usuario haya acepto agregarlo.
      */
    public void aggAdicional(){
        elementos_ad.add(preadicional);    
    }
    /**
     * Metodo para verificar si el Adicional que se va a crear ya ha sido ingresado o no.
     * @param tipoAdiconal
     * @return boolean 
     */
    public boolean verificacionAd(String tipoAdiconal){
        for(Adicional adicional: elementos_ad){
            if(TipoAdicional.valueOf(tipoAdiconal).equals(adicional.getTipo())){
                return false;
            }
        }
        return true;
    }
    /**
     * Metodo de sobrecarga utilizados para MUSICA y BEBIDAS.
     * @param tipoAdiconal
     * @param descripcion
     * @return boolean
     */
    public boolean verificacionAd(String tipoAdiconal,String descripcion){
        for(Adicional adicional: elementos_ad){
            if(TipoAdicional.valueOf(tipoAdiconal).equals(adicional.getTipo())&& adicional.getDescripcion().equals(descripcion)){
                return false;
            }
        }
        return true;
    }
    /**
     * Valida el tiempo ingresado por el cliente si esta en el rango de acuerdo al evento que se eliga.
     * @param fecha
     * @param intervaloTiempo
     * @return 
     */
     public static boolean validarTiempo(Date fecha,int intervaloTiempo) {
        boolean tiempoValido = false;
        Date fechaInicial = new Date();
        int dias = (int) ((fecha.getTime() - fechaInicial.getTime()) / (86400000));//1dia tiene 86400000
        if (dias>intervaloTiempo){
            tiempoValido= true;
        }
        return tiempoValido;
    }
     /**
      * Genera el codigo unico del Evento.
      * @return 
      */
     private String generarCodigo() {
        boolean validacion=false;
        String codigos="";
        ArrayList <String> listaC=new ArrayList<>();
         for(String linea: ManejoArchivos.LeeFichero("eventos.txt")){
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
      * Agrega los adicionales agregados en el evento en el adicionales.txt cuando el planificador haya generado la orden de pago.
      */
     public void crearAdicionales(){
        for(Adicional adicionales:elementos_ad){
            if(adicionales.getDescripcion()== null){
                String linea= adicionales.getEvento().getCodigoEvento()+","+adicionales.getTipo()+","+adicionales.getCantidad()+","+adicionales.getPrecio()+","+adicionales.getValorTotal();
                ManejoArchivos.EscribirArchivo("adicionales.txt",linea);
            }
            else{
                String linea= adicionales.getEvento().getCodigoEvento()+","+adicionales.getTipo()+","+adicionales.getCantidad()+","+adicionales.getPrecio()+","+adicionales.getValorTotal()+","+adicionales.getDescripcion();
                ManejoArchivos.EscribirArchivo("adicionales.txt",linea);
            }
        }
        }
     /**
      * Presenta los adicionales que posee el evento con su respectiva descripcion y tipo.
      * @return 
      */
     public String presentarAdici(){
         String linea="\n";
         if(elementos_ad.size()>0){
         for(Adicional adicionales:elementos_ad){
             if (String.valueOf(adicionales.getTipo()).equals("BEBIDA")||String.valueOf(adicionales.getTipo()).equals("MUSICA")){
                 linea = linea+adicionales.getDescripcion()+"\n";
             }
             else
                 linea = linea+String.valueOf(adicionales.getTipo())+"\n";
             }}
         else
             linea = "NO POSEE ADICIONALES";
         return linea;
     }
     public ArrayList<Adicional> recuperarAdicional(Evento evento){
         ArrayList<Adicional> adicionales = new ArrayList<>();
         
         return null;
     }
}
