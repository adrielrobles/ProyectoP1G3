/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evento;

import tipos.TipoAdicional;

/**
 *
 * @author ErikaVilla
 */
public class Adicional {
    private Evento evento;
    private TipoAdicional tipo;
    private double valorTotal;
    private int cantidad;
    private double precio;
    private String descripcion;
    public Adicional(Evento evento,String tipo,int cantidad){
        this.evento = evento;
        this.tipo = TipoAdicional.valueOf(tipo);
        this.cantidad = cantidad;
        precio = generarPrecio(TipoAdicional.valueOf(tipo),cantidad);
        valorTotal = precio*cantidad;
    }
    public Adicional(Evento evento,String tipo,int cantidad,String descripcion){
        this.evento = evento;
        this.tipo = TipoAdicional.valueOf(tipo);
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        precio = generarPrecio(TipoAdicional.valueOf(tipo),cantidad,descripcion);
        valorTotal = precio*cantidad;
    }
    //Getters y Setters

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public Evento getEvento(){
        return evento;
    }
    
    public void setEvento(Evento evento) {
        this.evento = evento;
    }
    
    public TipoAdicional getTipo() {
        return tipo;
    }

    public void setTipo(TipoAdicional tipo) {
        this.tipo = tipo;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int calcularValor(int cantidad) {
        return 0;
    }
    public double calcularValorBocaditos(int cantidad){
        return 0;
        
    }
    /**
     * Metodo para generar el precio a las adicionales comida, fotografia y bocaditos debido a que estos no poseen descripcion.
     * @param tipo
     * @param cantidad
     * @return 
     */
    private double generarPrecio(TipoAdicional tipo, int cantidad){
        switch(tipo){
            case COMIDA:
                return 15.00;
            case BOCADITOS:
                if(cantidad >150)
                    return 0.10;
                else
                    return 0.25;
            case FOTOGRAFIA:
                return 500;
        }
        return 0;
    }
    /**
     * Metodo de sobrecarga para generar precios a los adicionales BEBIDA y MUSICA debido a que estos poseen descripcion.
     * @param tipo
     * @param cantidad
     * @param descripcion
     * @return 
     */
    private double generarPrecio(TipoAdicional tipo, int cantidad,String descripcion){
        switch(tipo){
            case BEBIDA:
            switch(descripcion){
                case "Whisky":
                    return 50.00;
                case "Vodka":
                    return 25.00;
                case "Cerveza":
                    return 3.00;
                case "Refresco":
                    return 1.00;
        }
            case MUSICA:
                switch(descripcion){
                    case "DJ":
                        return 300;
                    case "Banda":
                        return 2000;
                }
    }
        return 0;
    }
    
}
