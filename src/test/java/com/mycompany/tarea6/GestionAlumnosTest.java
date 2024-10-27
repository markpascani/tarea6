package com.mycompany.tarea6;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.*;

public class GestionAlumnosTest {

    private static final String TEST_DIR = "testDir";
    private static final String TEST_FILE = "alumnos_test.txt";
    private Directorio dir;
    private GestionAlumnos gestionAlumnos;

    @BeforeEach
    public void setUp() {
        // Crear directorio de prueba
        dir = new Directorio(TEST_DIR);
        dir.getDir().mkdirs();
        // Crear instancia de GestionAlumnos con el directorio de prueba y un Scanner simulado
        gestionAlumnos = new GestionAlumnos(dir, new Scanner(System.in));
    }

    @AfterEach
    public void tearDown() {
        // Eliminar ficheros y directorios de prueba
        File testFile = new File(dir.getDir(), TEST_FILE);
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
        // Primera sesión del programa: Crear fichero y añadir dos alumnos
        primeraSesionCrearFicheroYAnadirDosAlumnos();

        // Segunda sesión del programa: Seleccionar el fichero y añadir un tercer alumno
        segundaSesionSeleccionarFicheroYAnadirAlumno();

        // Tercera sesión del programa: Seleccionar el fichero y mostrar todos los alumnos
        terceraSesionSeleccionarFicheroYMostrarAlumnos();

        // Validación de los alumnos añadidos
        verificarAlumnos();
    }

    private void primeraSesionCrearFicheroYAnadirDosAlumnos() {
        String inputSesion1
                = "1\n" + TEST_FILE + "\n" // Crear el fichero vacío
                + "2\n0\n" // Seleccionar el fichero creado
                + "1\n" // Cargar primer alumno
                + "1\nJuan\nPerez López\nH\n2000-05-15\nDAM\n2º\nA\n" // Datos del primer alumno
                + "1\n" // Cargar segundo alumno
                + "2\nMaría\nGarcía Sánchez\nF\n2001-08-22\nDAW\n1º\nB\n" // Datos del segundo alumno
                + "4\n" // Salir del menúu principal
                + "3\n";  // Salir del programa

        gestionAlumnos.setScanner(new Scanner(new ByteArrayInputStream(inputSesion1.getBytes())));
        gestionAlumnos.mostrarMenuInicial();
    }

    private void segundaSesionSeleccionarFicheroYAnadirAlumno() {
        String inputSesion2
                = "2\n0\n" // Reabrir y seleccionar el fichero
                + "1\n" // Cargar tercer alumno
                + "3\nCarlos\nRuiz Martínez\nH\n1999-12-30\nASIR\n2º\nC\n" // Datos del tercer alumno
                + "4\n" // Salir del menú principal
                + "3\n";                     // Cerrar el programa

        gestionAlumnos.setScanner(new Scanner(new ByteArrayInputStream(inputSesion2.getBytes())));
        gestionAlumnos.mostrarMenuInicial();
    }

    private void terceraSesionSeleccionarFicheroYMostrarAlumnos() {
        String inputSesion3
                = "2\n0\n" // Seleccionar el fichero
                + "2\n" // Mostrar todos los alumnos
                + "4\n"  // Salir del menu principal
                + "3\n"; //Cerrar el programa

        gestionAlumnos.setScanner(new Scanner(new ByteArrayInputStream(inputSesion3.getBytes())));
        gestionAlumnos.mostrarMenuInicial();
    }

    private List<Alumno> leerAlumnosDesdeFichero(File fichero) {
        List<Alumno> alumnos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fichero))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Alumno alumno = Alumno.fromText(line);
                if (alumno != null) {
                    alumnos.add(alumno);
                }
            }
        } catch (IOException e) {
            fail("Error al leer el fichero de alumnos: " + e.getMessage());
        }
        return alumnos;
    }

    private void verificarAlumnos() {
        File ficheroAlumnos = new File(dir.getDir(), TEST_FILE);
        List<Alumno> alumnos = leerAlumnosDesdeFichero(ficheroAlumnos);
        assertEquals(3, alumnos.size(), "El número de alumnos añadidos no es correcto.");

        Alumno alumno1 = alumnos.get(0);
        assertEquals(1, alumno1.getNia());
        assertEquals("Juan", alumno1.getNombre());

        Alumno alumno2 = alumnos.get(1);
        assertEquals(2, alumno2.getNia());
        assertEquals("María", alumno2.getNombre());

        Alumno alumno3 = alumnos.get(2);
        assertEquals(3, alumno3.getNia());
        assertEquals("Carlos", alumno3.getNombre());

    }
}
