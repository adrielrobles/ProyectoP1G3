/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import java.util.ArrayList;
import usuario.*;
import java.util.Scanner;
import ManejoArchivo.ManejoArchivos;
import java.text.SimpleDateFormat;
import java.util.Date;
import evento.Evento;

/**
 *
 * @author ErikaVilla
 */
public class Sistema {

    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);
    private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat formatoh = new SimpleDateFormat("HH:mm");
    private boolean fechaValidada = false;
    public static ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Metodo menuprincipal va a retornar el ususario con el cual vamos a
     * trabajr en el sistema.
     *
     * @return Usuario
     */
    private Usuario menuprincipal() {
        Usuario usuario1 = null;
        agginfoUsuario();
        do {
            System.out.println("++++++++++++++++++++++++++++++++++++++++\n" + "|");
            System.out.println("|\t BIENVENIDO AL SISTEMA\n" +  "|");
            System.out.println("++++++++++++++++++++++++++++++++++++++++");
            System.out.print("USUARIO: ");
            String usuario = sc.nextLine();
            System.out.print("CONTRASEÑA: ");
            String contrasenia = sc.nextLine();
            usuario1 = verifiUsuario(usuario, contrasenia);
            if (usuario1 == null) {
                System.out.println("Ingrese usuario y contraseña correctos");
            }
        } while (usuario1 == null);
        return usuario1;
    }

    /**
     * Metodo donde va a presentar el menu despues de ingresar con usuario y
     * contraseña ademas de acceder a una condicion si es Cliente o
     * planificador.
     *
     * @return void
     */
    public void iniciar() {
        Usuario usuario = menuprincipal();
        String opcion = "";
        if (usuario.getTipo() == 'C') {
            Cliente usuarioC = (Cliente) usuario;
            usuarioC.OrdenesDePago();
            while (!(opcion.equals("3"))) {
                System.out.println("              BIENVENIDO@ " + usuario.getNombre().toUpperCase());
                System.out.println("1. Solicitar Planificacion De Evento");
                System.out.println("2. Registar Pago Evento ");
                System.out.println("3. Salir");
                System.out.print("Seleccione opcion: ");
                opcion = sc.nextLine();
                switch (opcion) {
                    case "1":
                        String opcion2 = "";
                        System.out.println("/*********NUEVA SOLICITUD***********/");
                        System.out.println("/*                                  */");
                        System.out.println("/************************************/");
                        System.out.println("TIPO DE EVENTO (Elija)");
                        System.out.println("1. Boda ");
                        System.out.println("2. Fiesta Infantil ");
                        System.out.println("3. Fiesta Empresarial ");
                        System.out.print("Seleccione opcion: ");
                        opcion2 = sc.nextLine();
                        Date fechaE = new Date();
                        Date fechaS = new Date();
                        String registraSol;
                        switch (opcion2) {
                            case "1":
                                System.out.println("/*********EVENTO BODA**********/");
                                System.out.println("                                ");
                                fechaE = ingresarFecha(304);
                                System.out.println("¿Desea registrar su solicitud?: (S/N) ");
                                registraSol = sc.nextLine();
                                switch (registraSol.toUpperCase()) {
                                    case "S":
                                        usuarioC.generarSolicitud(fechaE, fechaS, "BODA", usuarioC);
                                        break;
                                    case "N":
                                        System.out.println("No se genero Solicitud.");
                                        break;
                                    default:
                                        System.out.println("INGRESO INCORRECTO!!---Utilize S o N");
                                }
                                break;
                            case "2":
                                System.out.println("/*********EVENTO FIESTA INFANTIL**********/");
                                System.out.println("                                           ");
                                fechaE = ingresarFecha(21);
                                System.out.print("¿Desea registrar su solicitud?: (S/N) ");
                                registraSol = sc.nextLine();
                                switch (registraSol.toUpperCase()) {
                                    case "S":
                                        usuarioC.generarSolicitud(fechaE, fechaS, "INFANTIL", usuarioC);
                                        break;
                                    case "N":
                                        System.out.println("No se genero Solicitud.");
                                        break;
                                    default:
                                        System.out.println("INGRESO INCORRECTO!!---Utilize S o N");
                                }
                                break;
                            case "3":
                                System.out.println("/*********EVENTO FIESTA EMPRESARIAL**********/");
                                System.out.println("                                              ");
                                fechaE = ingresarFecha(60);
                                System.out.print("¿Desea registrar su solicitud?: (S/N)");
                                registraSol = sc.nextLine();
                                switch (registraSol.toUpperCase()) {
                                    case "S":
                                        usuarioC.generarSolicitud(fechaE, fechaS, "EMPRESARIAL", usuarioC);
                                        break;
                                    case "N":
                                        System.out.println("No se genero Solicitud.");
                                        break;
                                    default:
                                        System.out.println("INGRESO INCORRECTO!!---Utilize S o N");
                                }
                                break;

                        }
                        break;
                    case "2":
                        System.out.println("/*********Registrar Pago Evento**********/");
                        System.out.println("/                                        /");
                        System.out.println("/****************************************/");
                        ArrayList<String> codigosO = usuarioC.recuperarCodO();
                        if (codigosO.size() != 0) {
                            for (String codirecu : codigosO) {
                                System.out.println("Su orden con codigo " + codirecu + " esta pendiente de pago.");
                                String caso = "";
                                while (!(caso.equalsIgnoreCase("N") || caso.equalsIgnoreCase("S"))) {
                                    System.out.print("¿Desea registrar pago ahora? (S/N): ");
                                    caso = sc.nextLine();
                                    switch (caso.toUpperCase()) {
                                        case "S":
                                            Date fecha = new Date();
                                            System.out.print("Ingrese codigo de transaccion: ");
                                            String codTran = sc.nextLine();
                                            usuarioC.generarPago(codTran, codirecu, fecha);
                                            break;
                                        case "N":
                                            System.out.println("No se registro pago.");
                                            break;
                                        default:
                                            System.out.println("INGRESO INCORRECTO!!---Utilize S o N");
                                    }
                                }
                            }
                        } else {
                            System.out.println("Usted no posee eventos a pagar.");
                        }
                        ManejoArchivos.sobrescrituraAOrden(usuarioC.getOrdenPago(), "ordenPago.txt");
                        break;
                    case "3":
                        System.out.println("Acaba de salir del programa.");
                        System.out.println("Gracias por su visita.");
                        System.out.println("                                   ");
                        break;
                    default:
                        System.out.println("Opcion No valida!!, vuelva a ingresar.");
                }

            }
        } else if (usuario.getTipo() == 'P') {
            Planificador usuarioP = (Planificador) usuario;
            while (!opcion.equals("5")) {
                usuarioP.obtenerSolicitudes(usuarioP);
                usuarioP.OrdenesDePago();
                usuarioP.recuperarEventos();
                System.out.println("              BIENVENIDO@ " + usuario.getNombre().toUpperCase());
                System.out.println("1. Consultar Solicitudes Pendientes ");
                System.out.println("2. Registrar Evento");
                System.out.println("3. Confirmar Evento ");
                System.out.println("4. Consultar Evento ");
                System.out.println("5. Salir");
                System.out.print("Seleccione opcion: ");
                opcion = sc.nextLine();
                switch (opcion) {
                    case "1":
                        System.out.println("/*********SOLICITUDES PENDIENTES**********/");
                        System.out.println("/*                                       */");
                        System.out.println("/*****************************************/");
                        usuarioP.consularSolicitudP();
                        break;
                    case "2":
                        String id_solicitud = "";
                        System.out.println("/*********REGISTRO DE EVENTOS**********/");
                        System.out.println("/*                                    */");
                        System.out.println("/**************************************/");
                        boolean validacion = true;
                        while (validacion) {
                            System.out.print("Ingrese el id de solicitud: ");
                            id_solicitud = sc.nextLine();
                            validacion = usuarioP.consularSolicitud(id_solicitud);
                            if (validacion == true) {
                                System.out.println("Ingrese un id de solicitud correcto");
                            }
                        }
                        System.out.println("/*********REGISTRO DE DATOS DEL EVENTO**********/");
                        System.out.println("/*                                             */");
                        System.out.println("/***********************************************/");
                        Date horaI = ingresarhora("inicio");
                        Date horaF = ingresarhora("fin");
                        int capacidad = 0;
                        String vehiculo = "";
                        int cantVehiE = 0;
                        int cantPerD = 0;
                        int cantSor = 0;
                        String juegoF = "";
                        System.out.print("Ingrese lugar a realizar Evento: ");
                        String lugar = sc.nextLine();
                        System.out.print("Capacidad: ");
                        capacidad = sc.nextInt();
                        sc.nextLine();
                        switch (usuarioP.getEvento().getTipoEvento()) {
                            case BODA:
                                while (!(vehiculo.equalsIgnoreCase("N") || vehiculo.equalsIgnoreCase("S"))) {
                                    System.out.print("¿Desea registar un vehiculo para los novios?(S/N): ");
                                    vehiculo = sc.nextLine();
                                    switch (vehiculo.toUpperCase()) {
                                        case "S":
                                            break;
                                        case "N":
                                            break;
                                        default:
                                            System.out.println("INGRESO INCORRECTO!!---Utilize S o N");
                                    }
                                }
                                usuarioP.registrarEvento(horaI, horaF, capacidad, vehiculo, lugar);
                                break;
                            case EMPRESARIAL:
                                String vehiculoE = "";
                                while (!(vehiculoE.equalsIgnoreCase("S") || vehiculoE.equalsIgnoreCase("N"))) {
                                    System.out.print("¿Desea registar un transporte? (S/N): ");
                                    vehiculoE = sc.nextLine();
                                    switch (vehiculoE.toUpperCase()) {
                                        case "S":
                                            System.out.print("Ingrese la cantidad de personas que se transportan: ");
                                            cantVehiE = sc.nextInt();
                                            sc.nextLine();
                                            break;
                                        case "N":
                                            break;
                                        default:
                                            System.out.println("INGRESO INCORRECTO!!---Utilize S o N");
                                    }
                                }
                                usuarioP.registrarEvento(horaI, horaF, capacidad, cantVehiE, lugar);
                                break;
                            case INFANTIL:
                                String personajD = "";
                                while (!(personajD.equalsIgnoreCase("S") || personajD.equalsIgnoreCase("N"))) {
                                    System.out.print("¿Desea registrar personajes disfrazados? (S/N): ");
                                    personajD = sc.nextLine();
                                    if (personajD.equalsIgnoreCase("S") || personajD.equalsIgnoreCase("N")) {
                                        if (personajD.equalsIgnoreCase("S")) {
                                            System.out.print("Ingrese la cantidad de personajes disfrazados: ");
                                            cantPerD = sc.nextInt();
                                            sc.nextLine();
                                        } else {
                                            System.out.println("No requiere del servicio de personajes disfrazados");
                                        }
                                    } else {
                                        System.out.println("INGRESO INCORRECTO!!---Utilize S o N");
                                    }
                                }
                                String valcants = "";
                                while (!(valcants.equalsIgnoreCase("S") || valcants.equalsIgnoreCase("N"))) {
                                    System.out.print("¿Desea registrar sorpresas? (S/N): ");
                                    valcants = sc.nextLine();
                                    if (valcants.equalsIgnoreCase("S") || valcants.equalsIgnoreCase("N")) {
                                        if (valcants.equalsIgnoreCase("S")) {
                                            System.out.print("Ingrese la cantidad de sopresas a repartirse: ");
                                            cantSor = sc.nextInt();
                                            sc.nextLine();
                                        } else {
                                            System.out.println("No requiere del servicio de sorpresas");
                                        }
                                    } else {
                                        System.out.println("INGRESO INCORRECTO!!---Utilize S o N");
                                    }
                                }
                                while (!(juegoF.equalsIgnoreCase("S") || juegoF.equalsIgnoreCase("N"))) {
                                    System.out.print("¿Desea registrar juegos durante la fiesta? (S/N): ");
                                    juegoF = sc.nextLine();
                                    if (juegoF.equalsIgnoreCase("S") || juegoF.equalsIgnoreCase("N")) {
                                        if (juegoF.equalsIgnoreCase("S")) {
                                            System.out.println("Se agrego juegos");
                                        } else {
                                            System.out.println("No se agrego juegos");
                                        }
                                    } else {
                                        System.out.println("INGRESO INCORRECTO!!---Utilize S o N");
                                    }
                                }
                                usuarioP.registrarEvento(horaI, horaF, capacidad, cantPerD, cantSor, juegoF, lugar);
                                break;
                        }
                        opcion = "";
                        while (!(opcion.equalsIgnoreCase("N"))) {
                            System.out.print("¿Desea registrar elementos adicionales?(S/N): ");
                            opcion = sc.nextLine();
                            switch (opcion.toUpperCase()) {
                                case "S":
                                    usuarioP = menuEventoA(usuarioP);
                                    break;
                                case "N":
                                    System.out.println("Ha concluido el ingreso de los datos del evento");
                                    System.out.println("El costo total de su evento sera: " + usuarioP.getEvento().getPrecioTotal());
                                    break;
                                default:
                                    System.out.println("INGRESO INCORRECTO!!---Utilize S o N");
                            }
                        }
                        opcion = "";
                        while (!(opcion.equals("N"))) {
                            System.out.print("¿Desea generar Orden de Pago?(S/N): ");
                            opcion = sc.nextLine();
                            switch (opcion.toUpperCase()) {
                                case "S":
                                    usuarioP.cambiaEstadoS(id_solicitud);
                                    usuarioP.crearEvento();
                                    usuarioP.generarOrden();
                                    opcion = "N";
                                    break;
                                case "N":
                                    break;
                                default:
                                    System.out.println("INGRESO INCORRECTO!!---Utilize S o N");
                            }
                        }
                        break;

                    case "3":
                        System.out.println("/*********Confirmar Evento**********/");
                        System.out.println("/*                                    */");
                        System.out.println("/***********************************/");
                        validacion = true;
                        String orden = "";
                        if (usuarioP.numeroOrdenC() != 0) {
                            while (validacion) {
                                System.out.print("Ingrese el Id de la oden de pago: ");
                                orden = sc.nextLine();
                                validacion = usuarioP.consultarOrdenPago(orden);
                            }
                            System.out.print("¿Desea aprobar este pago? (S/N): ");
                            String op = sc.nextLine();
                            switch (op.toUpperCase()) {
                                case "S":
                                    usuarioP.cambiaEstadoE(orden);
                                    System.out.println("El pago se ha aprobado.");
                                    System.out.println("El evento se ha confirmado para la fecha establecida. ");
                                    break;
                                case "N":
                                    System.out.println("No se aprobo pago de Evento");
                                    break;
                                default:
                                    System.out.println("INGRESO INCORRECTO!!---Utilize S o N");
                            }
                        } else {
                            System.out.println("No posee ningun evento a confirmar");
                        }
                        break;
                    case "4":
                        String opcion4p = "";
                        while (!opcion4p.equals("4")) {
                            System.out.println("/*********Consultar Evento**********/");
                            System.out.println("/*                                 */");
                            System.out.println("/***********************************/");
                            System.out.println("1. Boda");
                            System.out.println("2. Fiesta Infantil");
                            System.out.println("3. Fiesta Empresarial ");
                            System.out.println("4. Volver al menu anterior ");
                            System.out.print("Elija el tipo de Evento que desa consultar: ");
                            opcion4p = sc.nextLine();
                            if (opcion4p.equals("1") || opcion4p.equals("2") || opcion4p.equals("3") || opcion4p.equals("4")) {
                                usuarioP.conteoEventos(opcion4p);
                            } else {
                                System.out.println("Ingrese opcion correcta");
                            }
                        }
                        break;
                    default:
                        System.out.println("Opcion No valida!!, vuelva a ingresar");
                }
            }
        }

        sc.close();
    }

    /**
     * Metodo para la verificar si el ususario existe en el sistema donde
     * recorreremos el ArrayList Usuario y ponemos una condicion si el usuaario
     * ingresado es igual al usuario en el Array asi mismo con la contraseña si
     * entra a la condicion retornaremos la informacion del usuario de no ser
     * asi devolvera null.
     *
     * @param usuario
     * @param contrasenia
     * @return Usuario
     */
    private Usuario verifiUsuario(String usuario, String contrasenia) {
        for (Usuario usuarios : usuarios) {
            if (usuario.equals(usuarios.getUsuario())) {
                if (contrasenia.equals(usuarios.getContrasena())) {
                    return usuarios;
                }
            }
        }
        return null;
    }

    /**
     * Metodo para agregar a todos los usuarios al ArrayList usuarios de la
     * clase Usuario que se encuentran en el archivo usuarios.txt leyendo por el
     * metodo ManejoArchivos.LeeFichero el cual retornara una lista string por
     * cada linea en el archivo, la cual se va recorrer y obtener un String la
     * cual la vamos a separar en una lista con "," donde vamos a poner una
     * condicion para cliente y planificado donde vamos a instanciar los dos
     * tipos de usuarios debido a que algunos tienen mas indices que otro.
     *
     * @return void
     */
    private void agginfoUsuario() {
        ArrayList<String> preusuarios = ManejoArchivos.LeeFichero("usuarios.txt");
        for (String preusuario : preusuarios) {
            String listusuario[] = preusuario.split(",");
            if (listusuario[4].equals("C")) {
                usuarios.add(new Cliente(listusuario[0], listusuario[1], listusuario[2], listusuario[3], listusuario[4].charAt(0), Integer.parseInt(listusuario[5]), listusuario[6]));
            } else {
                usuarios.add(new Planificador(listusuario[0], listusuario[1], listusuario[2], listusuario[3], listusuario[4].charAt(0)));
            }
        }
    }

    /**
     * Metodo para ingresar la fecha y poder validar la sintaxis, ademas de
     * validar de segun el evento que se escoja en el menu principal todo esto
     * se lograr por medio del try catch para validar la sintaxis y asi pasar a
     * poder verificar si la fecha esta de acuerdo con los parametros de los
     * eventos.
     *
     * @param numero
     * @return fecha
     */
    private Date ingresarFecha(int numero) {
        boolean validacion = false;
        while (validacion == false) {
            System.out.println("Fecha del evento: (dd/mm/yy)");
            String fechax = sc.nextLine();
            try {
                Date fecha = formato.parse(fechax);
                boolean validafecha = Evento.validarTiempo(fecha, numero);
                if (validafecha) {
                    System.out.println("¡Fecha valida!");
                    System.out.println("Ha registrado todos los datos necesarios para la solicitud ");
                    return fecha;
                } else {
                    if (numero == 304) {
                        System.out.println("**La fecha es muy proxima. Para este tipo de evento debemos tener\tpor lo menos 10 meses para planificar. Ingrese nuevamente");
                    } else if (numero == 21) {
                        System.out.println("**La fecha es muy proxima. Para este tipo de evento debemos tener\tpor lo menos 3 semanas para planificar. Ingrese nuevamente");
                    } else if (numero == 60) {
                        System.out.println("**La fecha es muy proxima. Para este tipo de evento debemos tener\tpor lo menos 3 meses para planificar. Ingrese nuevamente");
                    }
                }
            } catch (Exception e) {
                System.out.println("Ingrese la sintaxis de fecha correcta (dd/mm/yy).");
            }
        }
        return null;
    }

    /**
     * Metodo que verificara el correcto ingreso de la hora de ingreso por el
     * ususario con la sintaxis correcta y devolvera la correspondiente hora con
     * su respectivo formato.
     *
     * @param linea
     * @return
     */
    private Date ingresarhora(String linea) {
        boolean validacion = false;
        while (validacion == false) {
            System.out.print("Ingrese Hora " + linea + ": (HH:mm) ");
            String hora = sc.nextLine();
            try {
                Date fecha = formatoh.parse(hora);
                return fecha;
            } catch (Exception e) {
                System.out.println("Ingrese la sintaxis de hora correcta (HH:mm)");
            }
        }
        return null;
    }

    /**
     * Metodo para visualizar el menu de Adicionales y poder agregarlos, ademas
     * que sean guardados dentro del evento que posee el planificador y retornar
     * el mismo y poder guardarlos en el UsuarioP.
     *
     * @param planificador
     * @return planificador
     */
    private Planificador menuEventoA(Planificador planificador) {
        String opcion = "";
        String opcion2 = "";
        while (!(opcion.equals("6"))) {
            System.out.println("/*****REGISTRO DE ELEMENTOS ADICIONALES PARA EL EVENTO*****/");
            System.out.println("Las opciones son:");
            System.out.println("1. Comida");
            System.out.println("2. Bocaditos");
            System.out.println("3. Bebida");
            System.out.println("4. Musica");
            System.out.println("5. Fotografia");
            System.out.println("6. Regresar al menu anterior");
            System.out.print("Eliga elemento a adicionar: ");
            opcion = sc.nextLine();
            String agregar = "";
            switch (opcion) {
                case "1":
                    if (planificador.verificacionAd("COMIDA")) {
                        System.out.println("Ingrese cantidad de platos: ");
                        int cantPla = sc.nextInt();
                        sc.nextLine();
                        planificador.registrarAdicional(cantPla, "COMIDA");
                        System.out.print("¿Desea agregar? (S/N): ");
                        agregar = sc.nextLine();
                        if (agregar.equalsIgnoreCase("S")) {
                            planificador.agregaAdicional();
                        } else if (agregar.equalsIgnoreCase("N")) {
                            System.out.println("No se agrego adicional");
                        } else {
                            System.out.println("INGRESO INCORRECTO!!---Utilize S o N");
                        }
                    } else {
                        System.out.println("YA HA AGREGADO ESTE ELEMENTO A SU LISTA ELIGA OTRO");
                    }
                    break;
                case "2":
                    if (planificador.verificacionAd("BOCADITOS")) {
                        System.out.println("Ingrese cantidad de bocaditos: ");
                        int cantBoc = sc.nextInt();
                        sc.nextLine();
                        planificador.registrarAdicional(cantBoc, "BOCADITOS");
                        System.out.print("¿Desea agregar? (S/N): ");
                        agregar = sc.nextLine();
                        if (agregar.equalsIgnoreCase("S")) {
                            planificador.agregaAdicional();
                        } else if (agregar.equalsIgnoreCase("N")) {
                            System.out.println("No se agrego adicional");
                        } else {
                            System.out.println("INGRESO INCORRECTO!!---Utilize S o N");
                        }
                    } else {
                        System.out.println("YA HA AGREGADO ESTE ELEMENTO A SU LISTA ELIGA OTRO");
                    }
                    break;
                case "3":
                    while (!(opcion2.equals("5"))) {
                        System.out.println("Tipos de botellas:");
                        System.out.println("1. Whisky: $50 por botella");
                        System.out.println("2. Vodka: $25 por botella");
                        System.out.println("3. Cerveza: $3 por botella");
                        System.out.println("4. Refresco: $1 por botella");
                        System.out.println("5. Regresar al menu anterior");
                        System.out.print("Eliga elemento a adicionar: ");
                        opcion2 = sc.nextLine();
                        int cantBot = 0;
                        switch (opcion2) {
                            case "1":
                                if (planificador.verificacionAd("BEBIDA", "Whisky")) {
                                    System.out.println("Ingrese cantidad de bebida: ");
                                    cantBot = sc.nextInt();
                                    sc.nextLine();
                                    planificador.registrarAdicional(cantBot, "BEBIDA", "Whisky");
                                    System.out.print("¿Desea agregar? (S/N): ");
                                    agregar = sc.nextLine();
                                    if (agregar.equalsIgnoreCase("S")) {
                                        planificador.agregaAdicional();
                                    } else if (agregar.equalsIgnoreCase("N")) {
                                        System.out.println("No se agrego adicional");
                                    } else {
                                        System.out.println("INGRESO INCORRECTO!!---Utilize S o N");
                                    }
                                } else {
                                    System.out.println("YA HA AGREGADO ESTE ELEMENTO A SU LISTA ELIGA OTRO");
                                }
                                break;
                            case "2":
                                if (planificador.verificacionAd("BEBIDA", "Vodka")) {
                                    System.out.println("Ingrese cantidad de bebida: ");
                                    cantBot = sc.nextInt();
                                    sc.nextLine();
                                    planificador.registrarAdicional(cantBot, "BEBIDA", "Vodka");
                                    System.out.print("¿Desea agregar? (S/N): ");
                                    agregar = sc.nextLine();
                                    if (agregar.equalsIgnoreCase("S")) {
                                        planificador.agregaAdicional();
                                    } else if (agregar.equalsIgnoreCase("N")) {
                                        System.out.println("No se agrego adicional");
                                    } else {
                                        System.out.println("INGRESO INCORRECTO!!---Utilize S o N");
                                    }
                                } else {
                                    System.out.println("YA HA AGREGADO ESTE ELEMENTO A SU LISTA ELIGA OTRO");
                                }
                                break;
                            case "3":
                                if (planificador.verificacionAd("BEBIDA", "Cerveza")) {
                                    System.out.println("Ingrese cantidad de bebida: ");
                                    cantBot = sc.nextInt();
                                    sc.nextLine();
                                    planificador.registrarAdicional(cantBot, "BEBIDA", "Cerveza");
                                    System.out.print("¿Desea agregar? (S/N): ");
                                    agregar = sc.nextLine();
                                    if (agregar.equalsIgnoreCase("S")) {
                                        planificador.agregaAdicional();
                                    } else if (agregar.equalsIgnoreCase("N")) {
                                        System.out.println("No se agrego adicional");
                                    } else {
                                        System.out.println("INGRESO INCORRECTO!!---Utilize S o N");
                                    }
                                } else {
                                    System.out.println("YA HA AGREGADO ESTE ELEMENTO A SU LISTA ELIGA OTRO");
                                }
                                break;
                            case "4":
                                if (planificador.verificacionAd("BEBIDA", "Refresco")) {
                                    System.out.println("Ingrese cantidad de bebida ");
                                    cantBot = sc.nextInt();
                                    sc.nextLine();
                                    planificador.registrarAdicional(cantBot, "BEBIDA", "Refresco");
                                    System.out.print("¿Desea agregar? (S/N): ");
                                    agregar = sc.nextLine();
                                    if (agregar.equalsIgnoreCase("S")) {
                                        planificador.agregaAdicional();
                                    } else if (agregar.equalsIgnoreCase("N")) {
                                        System.out.println("No se agrego adicional");
                                    } else {
                                        System.out.println("INGRESO INCORRECTO!!---Utilize S o N");
                                    }
                                } else {
                                    System.out.println("YA HA AGREGADO ESTE ELEMENTO A SU LISTA ELIGA OTRO");
                                }
                                break;
                            case "5":
                                break;
                            default:
                                System.out.println("Ingrese opcion correcta");
                        }
                    }
                    break;
                case "4":
                    opcion2 = "";
                    while (!(opcion2.equals("3"))) {
                        System.out.println("Tipos de Musica:");
                        System.out.println("1. DJ: $300");
                        System.out.println("2. Banda: $2000");
                        System.out.println("3. Regresar al menu anterior");
                        System.out.print("Eliga elemento a adicionar: ");
                        opcion2 = sc.nextLine();
                        switch (opcion2) {
                            case "1":
                                if (planificador.verificacionAd("MUSICA", "DJ")) {
                                    planificador.registrarAdicional(1, "MUSICA", "DJ");
                                    System.out.print("¿Desea agregar? (S/N): ");
                                    agregar = sc.nextLine();
                                    if (agregar.equalsIgnoreCase("S")) {
                                        planificador.agregaAdicional();
                                    } else if (agregar.equalsIgnoreCase("N")) {
                                        System.out.println("No se agrego adicional");
                                    } else {
                                        System.out.println("INGRESO INCORRECTO!!---Utilize S o N");
                                    }
                                } else {
                                    System.out.println("YA HA AGREGADO ESTE ELEMENTO A SU LISTA ELIGA OTRO");
                                }
                                break;
                            case "2":
                                if (planificador.verificacionAd("MUSICA", "Banda")) {
                                    planificador.registrarAdicional(1, "MUSICA", "Banda");
                                    System.out.print("¿Desea agregar? (S/N): ");
                                    agregar = sc.nextLine();
                                    if (agregar.equalsIgnoreCase("S")) {
                                        planificador.agregaAdicional();
                                    } else if (agregar.equalsIgnoreCase("N")) {
                                        System.out.println("No se agrego adicional");
                                    } else {
                                        System.out.println("INGRESO INCORRECTO!!---Utilize S o N");
                                    }
                                } else {
                                    System.out.println("YA HA AGREGADO ESTE ELEMENTO A SU LISTA ELIGA OTRO");
                                }
                                break;
                            case "3":
                                break;
                            default:
                                System.out.println("Ingrese opcion correcta");
                        }
                    }
                    break;
                case "5":
                    if (planificador.verificacionAd("FOTOGRAFIA")) {
                        planificador.registrarAdicional(1, "FOTOGRAFIA");
                        System.out.print("¿Desea agregar? (S/N): ");
                        agregar = sc.nextLine();
                        if (agregar.equalsIgnoreCase("S")) {
                            planificador.agregaAdicional();
                        } else if (agregar.equalsIgnoreCase("N")) {
                            System.out.println("No se agrego adicional");
                        } else {
                            System.out.println("INGRESO INCORRECTO!!---Utilize S o N");
                        }
                    } else {
                        System.out.println("YA HA AGREGADO ESTE ELEMENTO A SU LISTA ELIGA OTRO");
                    }
                    break;
                case "6":
                    break;
                default:
                    System.out.println("Ingrese una opcion correcta");
            }
        }
        return planificador;
    }
}
