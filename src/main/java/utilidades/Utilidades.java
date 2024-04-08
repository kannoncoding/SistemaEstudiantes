/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

/**
 *
 * @author kannon
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Estudiante;
 
public class Utilidades {
 
    public static void guardarEstudiante(Estudiante estudiante) {
        try (FileWriter fw = new FileWriter("Estudiantes.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(estudiante.getIdentificador() + "," + estudiante.getNombre() + "," +
                        estudiante.getApellido1() + "," + estudiante.getApellido2() + "," +
                        estudiante.getCarrera() + "," + estudiante.calcularPromedioFinal());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    public static List<Estudiante> cargarEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Estudiantes.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                // Aquí asumimos que ya tienes una manera de instanciar un estudiante a partir de los datos.
                // Necesitarás ajustar esto según cómo estés manejando las calificaciones en el archivo.
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return estudiantes;
    }
 
    // Métodos adicionales para operaciones de filtrado y cálculo de promedios
}

