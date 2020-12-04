package ManejoArchivo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import evento.Evento;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import usuario.OrdenPago;
import usuario.Solicitud;

/**
 *
 * @author Adriel Robles
 */
public class ManejoArchivos {


    public static ArrayList<String> LeeFichero(String nombrearchivo) {
        ArrayList<String> lineas = new ArrayList<>();
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(nombrearchivo);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return lineas;

    }

    public static void EscribirArchivo(String nombreArchivo, String linea) {

        FileWriter fichero = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(nombreArchivo,true);
            bw = new BufferedWriter(fichero);
            bw.write(linea+"\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    //fichero.close();
                    bw.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    /**
     * Creacion del metodo sobreescrituraA para sobrescibrir en el archivo solicitudes, es decir cambiar del tipo de estado PENDIENTE a
     * APROBADP.
     * @param nombreArchivo
     * @param solicitudes 
     */
    public static void sobrescrituraA(String nombreArchivo,ArrayList<Solicitud> solicitudes){
        ArrayList<String> lista = LeeFichero(nombreArchivo);
        ArrayList<String> nuevaLista = new ArrayList<>();
        for (String linea:lista){
            String listsoli[] = linea.split(",");
            if(listsoli[5].equals("APROBADO")){
                nuevaLista.add(linea);
            }
            else{
            for(Solicitud lineaS:solicitudes){
                String lineaN="";
                if(listsoli[0].equals(lineaS.getId_solicitud())){
                    if (String.valueOf(lineaS.getEstado()).equals("APROBADO")){
                        listsoli[5] = String.valueOf(lineaS.getEstado());
                    for(String lineaN2:listsoli){
                        if(lineaN.length()==0)
                            lineaN = lineaN2;
                        else
                            lineaN=lineaN+","+lineaN2;
                    }
                    nuevaLista.add(lineaN);
                    }
                }
                }
            if(listsoli[5].equals("PENDIENTE"))
                nuevaLista.add(linea);
            }
        }
        File fichero = new File(nombreArchivo);
        fichero.delete();
        for(String lineaA:nuevaLista){
        EscribirArchivo(nombreArchivo,lineaA);
        }        
    }
    public static void sobrescrituraAOrden(ArrayList<OrdenPago> orden, String nombreArchivo){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); //Bro porque esta linea sino la utilizaste
        ArrayList<String> lista = LeeFichero(nombreArchivo);
        ArrayList<String> nuevaLista = new ArrayList<>();
        for (String linea:lista){
            String listsoli[] = linea.split(",");
            if(listsoli[3].equals("APROBADO")){
                nuevaLista.add(linea);
            }
            else{
            for(OrdenPago lineaO:orden){
                String lineaN="";
                if (lineaO.getCodOrden().equals(listsoli[0])){
                    if (String.valueOf(lineaO.getEstadoOrden()).equals("APROBADO")){
                    listsoli[3] = String.valueOf(lineaO.getEstadoOrden());
                    ArrayList<String> sublista = new ArrayList<String>(Arrays.asList(listsoli));
                    sublista.add(lineaO.getCodTransa());
                    sublista.add(formato.format(lineaO.getFechaRegistro()));
                    for(String lineaN2:sublista){
                        if(lineaN.length()==0)
                            lineaN = lineaN2;
                        else
                            lineaN=lineaN+","+lineaN2;
                    }
                    nuevaLista.add(lineaN);
                    }
                }
            }
            if(listsoli[3].equals("PENDIENTEPAGO")){
                nuevaLista.add(linea);
            }
        }
        }
        File fichero = new File(nombreArchivo);
        fichero.delete();
        for(String lineaA:nuevaLista){
        EscribirArchivo(nombreArchivo,lineaA);
        }        
    }
    public static void sobrescrituraAEvento(ArrayList<Evento> evento, String nombreArchivo){
        ArrayList<String>lista=LeeFichero(nombreArchivo);
        ArrayList<String>nuevaLista=new ArrayList<>();
        for (String linea:lista){
            String listsoli[] = linea.split(",");
            if(listsoli[8].equals("CONFIRMADO")){
                nuevaLista.add(linea);
            }
            else{
            for(Evento lineaE:evento){
                String lineaN="";
                if (lineaE.getCodigoEvento().equals(listsoli[0])){
                    if (String.valueOf(lineaE.getEstado()).equals("CONFIRMADO")){
                    listsoli[8] = String.valueOf(lineaE.getEstado());
                    for(String lineaN2:listsoli){
                        if(lineaN.length()==0)
                            lineaN = lineaN2;
                        else
                            lineaN=lineaN+","+lineaN2;
                    }
                    nuevaLista.add(lineaN);
                    }
                }
            }
            if(listsoli[8].equals("PENDIENTE"))
                nuevaLista.add(linea);
            }
        }
        File fichero = new File(nombreArchivo);
        fichero.delete();
        for(String lineaA:nuevaLista){
        EscribirArchivo(nombreArchivo,lineaA);
        }  
    }
}
