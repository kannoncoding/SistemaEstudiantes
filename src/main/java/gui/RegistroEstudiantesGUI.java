/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    
    // Constructor
    public RegistroEstudiantesGUI() {
        initializeUI();
    }
    
    // Método para inicializar la interfaz de usuario
    private void initializeUI() {
        // Crear la ventana
        frame = new JFrame("Registro de Estudiantes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 900);

        // Crear panel con un layout
        panel = new JPanel(new GridLayout(0, 2));

        // Aquí iría el código para añadir los componentes al panel (omitido para brevedad)

        // Área de texto para mostrar estudiantes o resultados
        textArea = new JTextArea(10, 40);
        textArea.setEditable(false);
        scrollPane = new JScrollPane(textArea);
        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Botón y lógica para agregar estudiante (omitido para brevedad)
        
        // Mostrar la ventana
        frame.pack();
        frame.setVisible(true);
    }
    
    // Aquí puedes añadir métodos para manejar eventos, como agregar un estudiante, actualizar la lista, etc.
}
