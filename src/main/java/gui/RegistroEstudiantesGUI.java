/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import modelo.Estudiante;


public class RegistroEstudiantesGUI {
    private JFrame frame;
    private JPanel panel;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    

    // Declaraciones de campos de texto
    private JTextField identificadorField = new JTextField(10);
    private JTextField nombreField = new JTextField(10);
    private JTextField apellido1Field = new JTextField(10);
    private JTextField apellido2Field = new JTextField(10);
    private JTextField carreraField = new JTextField(10);
    private JButton agregarBtn = new JButton("Agregar Estudiante"); // Botón de ejemplo
    
    //lista de estudiantes
    private ArrayList<Estudiante> estudiantes = new ArrayList<>();
    
    // Nuevos campos para las calificaciones
    private JTextField proyecto1Field = new JTextField(5);
    private JTextField proyecto2Field = new JTextField(5);
    private JTextField foroAcademicoField = new JTextField(5);
    private JTextField encuestaField = new JTextField(5);
    private JTextField juegoField = new JTextField(5);
    
    
    

    
    // Variable para controlar la cantidad de estudiantes
    private int totalEstudiantes;
    private int estudiantesIngresados = 0;
    
    
    
    // Constructor
  public RegistroEstudiantesGUI() {
        // Solicitar la cantidad total de estudiantes al iniciar la aplicación
        String entrada = JOptionPane.showInputDialog("Ingrese la cantidad total de estudiantes a registrar:");
        try {
            totalEstudiantes = Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un número válido. La aplicación se cerrará.");
            System.exit(1); // Sale del programa si no se proporciona un número válido
        }
        initializeUI();
    }
  
  private void mostrarPromedios() {
    if (estudiantes.isEmpty()) {
        textArea.append("No hay estudiantes para calcular promedios.\n");
        return;
    }

    double sumaPromedios = 0;
    for (Estudiante estudiante : estudiantes) {
        double promedioEstudiante = estudiante.calcularPromedioFinal();
        textArea.append("Promedio de " + estudiante.getNombre() + " " + estudiante.getApellido1() + " " + estudiante.getApellido2() + ": " + String.format("%.2f", promedioEstudiante) + "\n");
        sumaPromedios += promedioEstudiante;
    }

    double promedioCurso = sumaPromedios / estudiantes.size();
    textArea.append("Promedio de los estudiantes agregados: " + String.format("%.2f", promedioCurso) + "\n");
}
  
  
    
    // Método para inicializar la interfaz de usuario
    private void initializeUI() {
        // Crear la ventana
        frame = new JFrame("Registro de Estudiantes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 900);

        // Crear panel con un layout
        panel = new JPanel(new GridLayout(0, 2));

        // Añadir campos de texto y etiquetas al panel
        panel.add(new JLabel("Identificador:"));
        panel.add(identificadorField);
        panel.add(new JLabel("Nombre:"));
        panel.add(nombreField);
        panel.add(new JLabel("Primer apellido:"));
        panel.add(apellido1Field);
        panel.add(new JLabel("Segundo apellido:"));
        panel.add(apellido2Field);
        panel.add(new JLabel("Carrera:"));
        panel.add(carreraField);
        panel.add(new JLabel("Proyecto 1:"));
        panel.add(proyecto1Field);
        panel.add(new JLabel("Proyecto 2:"));
        panel.add(proyecto2Field);
        panel.add(new JLabel("Foro Académico:"));
        panel.add(foroAcademicoField);
        panel.add(new JLabel("Encuesta:"));
        panel.add(encuestaField);
        panel.add(new JLabel("Juego:"));
        panel.add(juegoField);
        
        // Añadir botón y su lógica
        panel.add(agregarBtn);
        agregarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Recolectar los datos de los campos de texto
                String identificador = identificadorField.getText();
                String nombre = nombreField.getText();
                String apellido1 = apellido1Field.getText();
                String apellido2 = apellido2Field.getText();
                String carrera = carreraField.getText();
                double proyecto1 = Double.parseDouble(proyecto1Field.getText());
                double proyecto2 = Double.parseDouble(proyecto2Field.getText());
                double foroAcademico = Double.parseDouble(foroAcademicoField.getText());
                double encuesta = Double.parseDouble(encuestaField.getText());
                double juego = Double.parseDouble(juegoField.getText());

                
                
        // Validación del identificador
        if (!identificador.matches("\\d{9}")) {
            JOptionPane.showMessageDialog(frame, "El identificador debe ser de 9 dígitos numéricos.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return; // Detiene la ejecución si no pasa la validación
        }

        // Validación de longitud mínima de nombre y apellidos
        if (nombre.length() < 3 || apellido1.length() < 3 || apellido2.length() < 3) {
            JOptionPane.showMessageDialog(frame, "Nombre y apellidos deben tener al menos 3 caracteres.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return; // Detiene la ejecución si no pasa la validación
        }

        // Validación de la carrera
        if (carrera.trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Debe ingresar una carrera.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return; // Detiene la ejecución si no pasa la validación
        }

        // Si pasa todas las validaciones, procede a construir la cadena de texto y guardar
        String infoEstudiante = identificador + ", " + nombre + ", " + apellido1 + ", " + apellido2 + ", " + carrera + ", " +
                                proyecto1Field.getText() + ", " + proyecto2Field.getText() + ", " + foroAcademicoField.getText() + ", " +
                                encuestaField.getText() + ", " + juegoField.getText() + "\n";
        
         // Crear una nueva instancia de Estudiante con los datos recogidos
                Estudiante estudiante = new Estudiante(identificador, nombre, apellido1, apellido2, carrera,
                                                        proyecto1, proyecto2, foroAcademico, encuesta, juego);

                // Añadir la instancia a la lista de estudiantes
                estudiantes.add(estudiante);
                estudiantesIngresados++;

                

                // Aquí se verifica si ya se alcanzó el total de estudiantes y, de ser así, calcular y mostrar los promedios.
                if (estudiantesIngresados >= totalEstudiantes) {
                    // Puedes llamar aquí a un método que calcule y muestre los promedios, por ejemplo.
                    mostrarPromedios();
                }

                // se indica que se agrego el estudiante
        guardarEnArchivo(infoEstudiante);
        textArea.append("Estudiante agregado: " + nombre + "\n");
        
        
    }
}
        
        );


        // Área de texto para mostrar estudiantes o resultados
        textArea = new JTextArea(10, 40);
        textArea.setEditable(false);
        scrollPane = new JScrollPane(textArea);
        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Mostrar la ventana
        frame.pack();
        frame.setVisible(true);
    }
    
    // Método para guardar información de estudiantes en un archivo
    private void guardarEnArchivo(String texto) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Estudiantes.txt", true))) {
            bw.write(texto);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error al guardar en archivo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}



