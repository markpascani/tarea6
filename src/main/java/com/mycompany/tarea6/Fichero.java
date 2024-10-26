/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tarea6;

import java.io.File;
import java.io.IOException;


/**
 *
 * @author Mihai
 */
public class Fichero {

    private File fichero;

    public File getFichero() {
        return fichero;
    }

    public void setFichero(File fichero) {
        this.fichero = fichero;
    }

    /**
     * Constructor que recibe un String para el nombre del fichero que instancia
     * un objeto Fichero que contiene un File archivo.
     *
     * @param nombreFichero
     */
    public Fichero(String nombreFichero, File dir) {
        if (dir.exists()) {
            this.fichero = crearFichero(nombreFichero, dir);
        } else {
            System.out.println("No existe el directorio así que no se puede crear el fichero.");
        }
    }
    
    public Fichero(){};
    /**
     * Método que crea un fichero de tipo File en un directorio especificado si
     * no existe, y en caso de existir solo el objeto java para tratarlo
     *
     * @param nombreFichero
     * @param dir
     * @return File
     */
    public File crearFichero(String nombreFichero, File dir) {
        if(!dir.exists()){
            System.out.println("El directorio no existe, abortamos la creación del fichero.");
            return null;
        }
        
        
        this.fichero = new File(dir, nombreFichero);
        try {
            if (!fichero.exists()) {
                fichero.createNewFile();
                System.out.println("Fichero creado con éxito.");

            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("El fichero ya existe, queda cargado con éxito.");
        }
        return fichero;
    }




}
