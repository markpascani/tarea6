/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tarea6;

import java.util.Scanner;

/**
 *
 * @author Mihai
 */
public class Main {
    public static void main (String[] args){

        //Instanciamos el directorio y gestion de alumnos;
        Directorio directorio = new Directorio("directorio");
        GestionAlumnos gestionAlumnos = new GestionAlumnos(directorio);

        
    }
}
