/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author kannon
 */
public class Persona {
    protected String identificador;
    protected String nombre;
    protected String apellido1;
    protected String apellido2;
 
    // Constructor
    public Persona(String identificador, String nombre, String apellido1, String apellido2) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
    }
 
    // Getters y Setters
    public String getIdentificador() {
        return identificador;
    }
 
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
 
    public String getNombre() {
        return nombre;
    }
 
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
 
    public String getApellido1() {
        return apellido1;
    }
 
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }
 
    public String getApellido2() {
        return apellido2;
    }
 
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }
}
