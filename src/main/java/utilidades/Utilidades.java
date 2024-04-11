/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package utilidades;

import modelo.Estudiante;

public class Utilidades {
    
    // This method calculates the final score for a student based on the grades of each evaluation instrument
    public static double calcularFinalScore(Estudiante estudiante) {
        double proyecto1Score = estudiante.getProyecto1() * 0.40; // 40% of final score
        double proyecto2Score = estudiante.getProyecto2() * 0.40; // 40% of final score
        double foroAcademicoScore = estudiante.getForoAcademico() * 0.10; // 10% of final score
        double encuestaScore = estudiante.getEncuesta() * 0.05; // 5% of final score
        double juegoScore = estudiante.getJuego() * 0.05; // 5% of final score
        
        // Calculating the current final score by adding up the % obtained of each evaluation instrument
        double currentFinalScore = proyecto1Score + proyecto2Score + foroAcademicoScore + encuestaScore + juegoScore;
        
        // Returning the calculated current final score
        return currentFinalScore;
    }
}
