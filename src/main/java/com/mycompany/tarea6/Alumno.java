/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tarea6;

import java.time.LocalDate;

/**
 *
 * @author Mihai
 */
public class Alumno {
    
    private int nia;
    private String nombre;
    private String apellidos;
    private char genero;
    private LocalDate fechaNacimiento;
    private String ciclo;
    private String curso;
    private String grupo;

    // -------------
    // Constructores
    // -------------
    /**
     * Constructor de alumno con todos los params.
     *
     * @param nia
     * @param nombre
     * @param apellidos
     * @param genero
     * @param fechaNacimiento
     * @param ciclo
     * @param curso
     * @param grupo
     */
    public Alumno(int nia, String nombre, String apellidos, char genero, LocalDate fechaNacimiento, String ciclo, String curso, String grupo) {
        this.nia = nia;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.ciclo = ciclo;
        this.curso = curso;
        this.grupo = grupo;
    }
    
    /**
     * Constructor de alumno sin inicializar atributos
     */
    public Alumno(){};
    
    
    
    // -------
    // Métodos
    // -------
    
    /**
     * Convierte de un string a objeto Alumno
     * @param text (String con formato correcto con separadores).
     * @return Alumno
     */
    public static Alumno fromText(String text) {
        String[] alumnoTexto = text.split(":");
        int nia = Integer.parseInt(alumnoTexto[0]);
        String nombre = alumnoTexto[1];
        String apellidos = alumnoTexto[2];
        char genero = alumnoTexto[3].charAt(0);
        LocalDate fechaNac = LocalDate.parse(alumnoTexto[4]);
        String ciclo = alumnoTexto[5];
        String curso = alumnoTexto[6];
        String grupo = alumnoTexto[7];
        
        return new Alumno(nia, nombre, apellidos, genero, fechaNac, ciclo, curso, grupo);
    }
    
    /**
     * Convierte un objeto alumno a un string
     * para guardar en el fichero con el formato correcto
     * @param alumno
     * @return stringbuilder de alumno en formato texto
     */
    public static StringBuilder toText(Alumno alumno){
        StringBuilder alumnoFormatoTexto = new StringBuilder();
        
        alumnoFormatoTexto.append(alumno.getNia()).append(":");
        alumnoFormatoTexto.append(alumno.getNombre()).append(":");
        alumnoFormatoTexto.append(alumno.getApellidos()).append(":");
        alumnoFormatoTexto.append(alumno.getGenero()).append(":");
        alumnoFormatoTexto.append(alumno.getFechaNacimiento()).append(":");
        alumnoFormatoTexto.append(alumno.getCiclo()).append(":");
        alumnoFormatoTexto.append(alumno.getCurso()).append(":");
        alumnoFormatoTexto.append(alumno.getGrupo());
        
        return alumnoFormatoTexto;        
        
    }
    // -----------------
    // Getters y setters
    // -----------------
    /**
     * Getter del nia
     *
     * @return numero de nia del alumno
     */
    public int getNia() {
        return nia;
    }

    /**
     * Setter del nia
     *
     * @param nia
     */
    public void setNia(int nia) {
        this.nia = nia;
    }

    /**
     * Getter del nombre
     *
     * @return nombre alumno
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter del nombre del alumno
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Getter del apellido
     *
     * @return apellido del alumno
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Setter del apellido del alumno
     *
     * @param apellidos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Getter del genero
     *
     * @return genero del alumno
     */
    public char getGenero() {
        return genero;
    }

    /**
     * Setter del genero del alumno
     *
     * @param genero
     */
    public void setGenero(char genero) {
        this.genero = genero;
    }

    /**
     * Getter de la fecha de nacimiento
     *
     * @return fecha nacimiento dle alumno
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Setter de la fecha de nacimiento del alumno
     *
     * @param fechaNacimiento
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Getter del ciclo
     *
     * @return ciclo del alumno
     */
    public String getCiclo() {
        return ciclo;
    }

    /**
     * Setter del ciclo del alumno
     *
     * @param ciclo
     */
    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    /**
     * Getter del curso
     *
     * @return curso del alumno
     */
    public String getCurso() {
        return curso;
    }

    /**
     * Setter del curso del alumno
     *
     * @param curso
     */
    public void setCurso(String curso) {
        this.curso = curso;
    }

    /**
     * Getter del grupo
     *
     * @return grupo del alumno
     */
    public String getGrupo() {
        return grupo;
    }

    /**
     * Setter del grupo del alumno
     *
     * @param grupo
     */
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
    
    /**
     * Método to String con todos los atributos de alumno
     * @return 
     */
    @Override
    public String toString() {
        return "Alumno{" + "nia=" + nia + ", nombre=" + nombre + ", apellidos=" + apellidos + ", genero=" + genero + ", fechaNacimiento=" + fechaNacimiento + ", ciclo=" + ciclo + ", curso=" + curso + ", grupo=" + grupo + '}';
    }
    
    
}
