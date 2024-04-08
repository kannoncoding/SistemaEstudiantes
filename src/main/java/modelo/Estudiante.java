/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author kannon
 */
public class Estudiante extends Persona {
    private String carrera;
    private double proyecto1;
    private double proyecto2;
    private double foroAcademico;
    private double encuesta;
    private double juego;
 
    // Constructor
    public Estudiante(String identificador, String nombre, String apellido1, String apellido2,
                      String carrera, double proyecto1, double proyecto2, double foroAcademico,
                      double encuesta, double juego) {
        super(identificador, nombre, apellido1, apellido2);
        this.carrera = carrera;
        this.proyecto1 = proyecto1;
        this.proyecto2 = proyecto2;
        this.foroAcademico = foroAcademico;
        this.encuesta = encuesta;
        this.juego = juego;
    }
 
    // Métodos para calcular el promedio
 public double calcularPromedioFinal() {
        return (proyecto1 * 0.4) + (proyecto2 * 0.4) + (foroAcademico * 0.1) +
               (encuesta * 0.05) + (juego * 0.05);
 }
        

// Getters y Setters
    public String getCarrera() {
        return carrera;
    }
 
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
 
  
}
