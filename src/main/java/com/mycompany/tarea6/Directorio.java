/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tarea6;
/**
 *
 * @author Mihai
 */
import java.io.File;

public class Directorio {
    private final File dir;
    
    /**
     * Constructor de Directorio que pide un nombre y crea un directorio si no existe
     * o lo carga si existe
     * @param nombre 
     */
    public Directorio(String nombre) {
        this.dir = new File(nombre); // Crear el objeto File con el nombre

        if (!dir.exists()) { // Comprobar si no existe
            if (dir.mkdir()) { // Intentar crear el directorio
                System.out.println("Directorio con nombre " + nombre + " creado con éxito.");
            } else {
                System.out.println("No se pudo crear el directorio.");
            }
        } else {
            System.out.println("Directorio con nombre " + nombre + " cargado exitosamente.");
        }
    }
    
    /**
     * Método adicional para obtener el directorio
     * @return 
     */
    public File getDir() {
        return dir;
    }
}

