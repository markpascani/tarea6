/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tarea6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author Mihai
 */
public class GestionAlumnos {

    private Fichero fichero = new Fichero();
    private Directorio dir;
    private Scanner sc;

    // -----------------
    // Getters y setters
    // -----------------
    public Fichero getFichero() {
        return fichero;
    }

    public void setFichero(Fichero fichero) {
        this.fichero = fichero;
    }

    public Directorio getDir() {
        return dir;
    }

    public void setDir(Directorio dir) {
        this.dir = dir;
    }

    public void setScanner(Scanner sc) {
        this.sc = sc;
    }

    // -------------
    // Constructores
    // -------------
    /**
     * Constructor con fichero y directorio
     *
     * @param dir
     */
    public GestionAlumnos(Directorio dir, Scanner sc) {
        this.dir = dir;
        this.sc = sc;

    }

    /**
     * Método que muestra los ficheros de un directorio y sleeciona uno de ellos
     * para trabajarlo
     */
    public void mostrarMenuInicial() {
        while (true) {
            System.out.println("""
                           1. Crear fichero vacío.
                           2. Elegir fichero con el que trabajar.
                           3. Salir.""");

            if (sc.hasNextInt()) {
                int respuesta = sc.nextInt();
                sc.nextLine(); // Limpiar la línea después de leer un int
                System.out.println("Respuesta seleccionada en mostrarMenuInicial(): " + respuesta);

                switch (respuesta) {
                    case 1 -> {
                        crearFicheroVacio();
                    }
                    case 2 -> {
                        elegirFicheroConElQueSeTrabaja();
                        System.out.println("Abriendo el menu principal después de elegir un fichero");
                    }
                    case 3 -> {
                        System.out.println("Cierre del programa");
                        return; // Salir del método y finalizar el programa
                    }
                    default -> {
                        System.out.println("Opción fuera de rango. Selecciona 1, 2 o 3.");
                    }
                }
            } else {
                System.out.println("Tienes que seleccionar una opción que sea 1, 2 o 3.");
                sc.nextLine(); // Limpiar la entrada no válida
            }
        }
    }

    public void crearFicheroVacio() {
        String ficheroNuevo;
        ficheroNuevo = solicitarString("Introduce nombre del fichero a crear: ");
        this.fichero.crearFichero(ficheroNuevo, this.dir.getDir());

    }

    /**
     * Método para elegir un fichero con el que se va a trabajar y pasar al menu
     * principal
     *
     * @param sc
     */
    private void elegirFicheroConElQueSeTrabaja() {
        File directorio = this.dir.getDir();
        File[] ficheros = directorio.listFiles();

        // Verificar si hay archivos en el directorio
        if (ficheros == null || ficheros.length == 0) {
            System.out.println("No hay archivos disponibles en el directorio para seleccionar.");
            return; // Salir del método si no hay archivos
        }

        int respuesta = -1;

        System.out.println("Elige un fichero para trabajar: \n" + mostrarFicherosASeleccionar(directorio));
        while (true) {
            if (sc.hasNextInt()) {
                respuesta = sc.nextInt();
                sc.nextLine(); // Limpiar la línea después de leer el índice

                if (respuesta >= 0 && respuesta < ficheros.length) {
                    break;
                } else {
                    System.out.println("Número fuera de rango");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, introduce un número");
                sc.nextLine(); // Limpiar la entrada no válida

            }
        }
        this.fichero.crearFichero(ficheros[respuesta].getName(), directorio);
        System.out.println("Has seleccionado el fichero " + ficheros[respuesta].getName());
        menuGestionAlumnos(); // Asumiendo que este es el flujo deseado

        
    }

    /**
     * Método que muestra el menú de la aplicación
     *
     * @param sc
     */
    public void menuGestionAlumnos() {
        while (true) {
            System.out.println("""
                           1. Cargar alumno.
                           2. Mostrar todos los alumnos del fichero.
                           3. Cambiar fichero con el que se trabaja.
                           4. Salir.
                           """);

            if (sc.hasNextInt()) {
                int respuesta = sc.nextInt();
                System.out.println("Respuesta seleccionada en menuPrincipal(): " + respuesta);
                sc.nextLine(); // Limpiar la línea después de leer un int

                switch (respuesta) {
                    case 1 -> {
                        System.out.println("Cargar alumno seleccionado");
                        cargarAlumno();
                    }
                    case 2 -> {
                        System.out.println("Mostrar todos los alumnos seleccionado");
                        mostrarAlumnos();
                    }
                    case 3 -> {
                        System.out.println("Cambiar fichero seleccionado");
                        elegirFicheroConElQueSeTrabaja();
                        return;
                    }
                    case 4 -> {
                        System.out.println("Salir del menú principal seleccionado");
                        return;
                    }
                    default -> {
                        System.out.println("Opción fuera de rango. Selecciona 1, 2, 3 o 4.");
                    }
                }
            } else {
                System.out.println("Tienes que seleccionar una opción que sea 1, 2, 3 o 4.");
                sc.nextLine(); // Limpiar la entrada no válida
            }
        }
    }

    /**
     * Mñetodo que construye el string para mostrar los ficheros disponibles
     * (nombre y numero).
     *
     * @param dir
     * @return
     */
    private StringBuilder mostrarFicherosASeleccionar(File dir) {
        StringBuilder sb = new StringBuilder();
        File[] ficheros = dir.listFiles();

        //Construir el string con los nombres de los ficheros
        if (ficheros == null || ficheros.length == 0) {
            sb.append("No hay archivos disponibles en el directorio.\n");
        } else {
            for (int i = 0; i < ficheros.length; i++) {
                sb.append(i).append(".").append(ficheros[i].getName()).append("\n");
            }
        }
        return sb;
    }

    /**
     * Mñetodo que cargar los alumnos de un fichero y devuelve una lista.
     *
     * @return
     */
    private void mostrarAlumnos() {
        File ficheroALeer = this.fichero.getFichero();

        if (comprobarSiElFicheroTieneAlumnos(ficheroALeer)) {
            try (BufferedReader lector = new BufferedReader(new FileReader(ficheroALeer));) {

                String line;
                while ((line = lector.readLine()) != null) {
                    Alumno alumno = Alumno.fromText(line);
                    if (alumno != null) {
                        System.out.println(alumno.toString());
                    }
                }
            } catch (IOException e) {
                System.out.println("No se ha podido leer el fichero");
            }
        } else {
            System.out.println("El fichero no contiene alumnos o no existe.");
        }

    }

    /**
     * Método que comprueba si un fichero tiene alumnos
     *
     * @param ficheroAComprobar
     * @return
     */
    private boolean comprobarSiElFicheroTieneAlumnos(File ficheroAComprobar) {
        if (ficheroAComprobar.exists()) {
            try (BufferedReader lector = new BufferedReader(new FileReader(ficheroAComprobar))) {

                String line = lector.readLine();
                if (line != null && !line.trim().isEmpty()) {
                    return Alumno.fromText(line) != null;
                } else {
                    return false;
                }
            } catch (IOException e) {
                return false;
            }
        } else {
            System.out.println("El fichero no existe");
            return false;
        }
    }

    /**
     * Método que carga el alumno introducido por consola en el fichero
     *
     * @param sc
     */
    public void cargarAlumno() {
        Alumno alumno = nuevoAlumno();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichero.getFichero(), true))) {

            System.out.println(fichero.getFichero().getName());
            StringBuilder alumnoWriter = Alumno.toText(alumno);
            System.out.println("Alumno a escribir: " + alumnoWriter.toString());
            writer.write(alumnoWriter.toString());
            writer.newLine(); // Añadir nueva línea después de cada alumno
            System.out.println("Alumno guardado exitosamente en el fichero."); // Confirmación en consola
        } catch (IOException e) {
            System.out.println("No se ha podido cargar el fichero: " + e.getMessage());
        }

    }

    /**
     * Método que solicita por consola los datos para crear un alumno
     *
     * @return Alumno
     */
    private Alumno nuevoAlumno() {
        Alumno alumno = new Alumno();
        try {
            //solicitar y crear un objeto alumno
            alumno.setNia(solicitarEntero("Introduce el NIA del alumno: "));
            alumno.setNombre(solicitarString("INtroduce el nombre del alumno: "));
            alumno.setApellidos(solicitarString("Introduce los apellidos del alumno: "));
            alumno.setGenero(solicitarGenero("Introduce el genero del alumno(H/F): "));
            alumno.setFechaNacimiento(solicitarFecha("Introduce la fecha de nacimiento del alumno: "));
            alumno.setCurso(solicitarString("Introduce curso del alumno: "));
            alumno.setGrupo(solicitarString("Introduce el grupo del alumno: "));
            alumno.setCiclo(solicitarString("Introduce el ciclo del alumno: "));
        } catch (Exception e) {
            System.out.println("No se ha podido gestionar el nuevo alumno. " + e.getMessage());
        }
        return alumno;

    }

    /**
     * Método que solicita por consola un entero y comprueba su validez
     *
     * @param sc
     * @param mensaje
     * @return int
     */
    private int solicitarEntero(String mensaje) {
        System.out.println(mensaje);
        while (true) {
            if (sc.hasNextInt()) {
                int valor = sc.nextInt();
                sc.nextLine(); // Limpiar la línea
                return valor;
            } else {
                System.out.println("Entrada inválida. Por favor, introduce un número entero.");
                sc.next(); // Limpiar la entrada no válida
            }

        }
    }

    /**
     * Método que solicita por consola un string y comprueba su validez
     *
     * @param sc
     * @param mensaje
     * @return String
     */
    private String solicitarString(String mensaje) {
        System.out.println(mensaje);
        while (true) {
            String entrada = sc.nextLine().trim();
            if (!entrada.isEmpty()) {
                return entrada;
            } else {
                System.out.println("Entrada inválida. Por favor, introduce un texto válido.");
            }
        }
    }

    /**
     * Método que solicita por consola una fecha y comprueba su validez
     *
     * @param sc
     * @param mensaje
     * @return LocalDate
     */
    private LocalDate solicitarFecha(String mensaje) {
        System.out.println(mensaje);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            String entrada = sc.nextLine().trim();
            try {
                return LocalDate.parse(entrada, formato);
            } catch (DateTimeParseException e) {
                System.out.println("Entrada inválida. Por favor, introduce una fecha válida en formato yyyy-MM-dd");
            }
        }
    }

    /**
     * Método que solicita un caracter por consola y comprueba su validez
     *
     * @param sc
     * @param mensaje
     * @return char
     */
    private char solicitarGenero(String mensaje) {
        System.out.println(mensaje);
        while (true) {
            String entrada = sc.next();
            sc.nextLine();
            if (entrada.equalsIgnoreCase("H") || entrada.equalsIgnoreCase("F")) {
                return entrada.charAt(0);
            } else {
                System.out.println("Entrada inválida. Por favor, introduce un género válido (H/F).");
            }
        }
    }

}
