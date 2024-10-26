/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.tarea6;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.PrintStream;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Mihai
 */
public class GestionAlumnosTest {

    private static final String TEST_DIR = "testDir";
    private static final String TEST_FILE = "alumnos_test.txt";
    private Directorio dir;
    private Fichero fich;
    private GestionAlumnos gestionAlumnos;
    private Scanner sc;

    @BeforeEach
    public void setUp() {
        // Crear directorio de prueba
        dir = new Directorio(TEST_DIR);
        fich = new Fichero(TEST_FILE, dir.getDir());
        sc = new Scanner(System.in);
        // Crear instancia de GestionAlumnos con el directorio de prueba
        gestionAlumnos = new GestionAlumnos(dir, sc);
    }

    @AfterEach
    public void tearDown() {
        // Eliminar ficheros y directorios de prueba
        File testFile = new File(TEST_DIR, TEST_FILE);
        if (testFile.exists()) {
            testFile.delete();
        }
        File testDir = new File(TEST_DIR);
        if (testDir.exists()) {
            testDir.delete();
        }
    }

    @Test
    public void testProgramaCompleto() {
        // Paso 1: Crear un fichero vacío
        crearFicheroVacio();

        // Paso 2: Añadir 2 alumnos
        anadirDosAlumnos();

        // Paso 3: Cerrar el programa (Simulado al terminar el método)
        // Paso 4: Reabrir el programa (Crear una nueva instancia)
        gestionAlumnos = new GestionAlumnos(dir, sc);

        // Paso 5: Seleccionar el fichero usado previamente
        seleccionarFicheroExistente();

        // Paso 6: Añadir 1 alumno
        anadirUnAlumno();

        // Paso 7: Cerrar el programa (Simulado al terminar el método)
        // Paso 8: Reabrir el programa (Crear una nueva instancia)
        gestionAlumnos = new GestionAlumnos(dir, sc);

        // Paso 9: Seleccionar el fichero usado previamente
        seleccionarFicheroExistente();

        // Paso 10: Mostrar todos los alumnos
        mostrarTodosLosAlumnos();

        // Paso 11: Cerrar el programa (Simulado al terminar el método)
    }

    private void crearFicheroVacio() {
        // Simular la opción de crear un fichero vacío
        gestionAlumnos.crearFicheroVacio();

        // Verificar que el fichero se ha creado
        File testFile = new File(TEST_DIR, TEST_FILE);
        assertTrue(testFile.exists(), "El fichero vacío no se ha creado correctamente.");
    }

    private void anadirDosAlumnos() {
        // Simular la entrada del usuario para añadir 2 alumnos
        String input = "12345\nJuan\nPérez López\nH\n2000-05-15\nDAM\n2º\nA\n"
                + "67890\nMaría\nGarcía Sánchez\nF\n2001-08-22\nDAW\n1º\nB\n";
        Scanner sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
        gestionAlumnos.setScanner(sc);

        // Llamar al método para cargar el primer alumno
        gestionAlumnos.cargarAlumno();

        // Llamar al método para cargar el segundo alumno
        gestionAlumnos.cargarAlumno();

        // Verificar que los alumnos se han añadido al fichero
        List<Alumno> alumnos = gestionAlumnos.leerAlumnosDesdeFichero();
        assertEquals(2, alumnos.size(), "No se han añadido correctamente los 2 alumnos.");
    }

    private void seleccionarFicheroExistente() {
        // Simular la entrada del usuario para seleccionar el fichero existente
        String input = "0\n";
        Scanner sc = new Scanner(new ByteArrayInputStream(input.getBytes()));

        // Simular la selección del fichero
        gestionAlumnos.mostrarMenuSeleccionFichero(sc);

        // Verificar que el fichero seleccionado es el correcto
        File ficheroSeleccionado = gestionAlumnos.getFichero().getFichero();
        assertEquals(TEST_FILE, ficheroSeleccionado.getName(), "No se ha seleccionado el fichero correcto.");
    }

    private void anadirUnAlumno() {
        // Simular la entrada del usuario para añadir 1 alumno
        String input = "1\nCarlos\nLopez\nH\n1997-09-19\n1\nC\nASIR\n"
                + "3\n";
        Scanner sc = new Scanner(new ByteArrayInputStream(input.getBytes()));

        // Simular el menú principal
        gestionAlumnos.menuPrincipal(sc);

        // Verificar que el alumno se ha añadido al fichero
        List<Alumno> alumnos = gestionAlumnos.obtenerAlumnos();
        assertEquals(3, alumnos.size(), "No se ha añadido correctamente el alumno.");
    }

    private void mostrarTodosLosAlumnos() {
        // Capturar la salida del sistema
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Mostrar los alumnos
        gestionAlumnos.mostrarAlumnos();

        // Restaurar System.out
        System.setOut(originalOut);

        // Verificar que se muestran los 3 alumnos
        String output = outContent.toString();
        assertTrue(output.contains("Mihai"));
        assertTrue(output.contains("Maria"));
        assertTrue(output.contains("Carlos"));
    }
}
