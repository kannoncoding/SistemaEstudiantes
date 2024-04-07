/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author kannon
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class Main {
 
    public static void main(String[] args) {
        // Crear la ventana
        JFrame frame = new JFrame("Registro de Estudiantes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 900);
 
        // Crear panel con un layout
        JPanel panel = new JPanel(new GridLayout(0, 2));
 
        // Crear y añadir componentes al panel
        JLabel identificadorLabel = new JLabel("Identificador:");
        JTextField identificadorField = new JTextField(10);
        panel.add(identificadorLabel);
        panel.add(identificadorField);
 
        JLabel nombreLabel = new JLabel("Nombre:");
        JTextField nombreField = new JTextField(10);
        panel.add(nombreLabel);
        panel.add(nombreField);
 
        JLabel apellido1Label = new JLabel("Apellido 1:");
        JTextField apellido1Field = new JTextField(10);
        panel.add(apellido1Label);
        panel.add(apellido1Field);
 
        JLabel apellido2Label = new JLabel("Apellido 2:");
        JTextField apellido2Field = new JTextField(10);
        panel.add(apellido2Label);
        panel.add(apellido2Field);
 
        JLabel carreraLabel = new JLabel("Carrera:");
        JTextField carreraField = new JTextField(10);
        panel.add(carreraLabel);
        panel.add(carreraField);
 
        // Aquí puedes seguir añadiendo campos para las calificaciones
 
        JButton agregarButton = new JButton("Agregar Estudiante");
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí deberías tomar los valores de los campos y crear un objeto Estudiante
                // Luego, usar la clase Utilidades para guardar el estudiante en el archivo
                // Finalmente, mostrar un mensaje de confirmación o de error en caso de que los datos no sean válidos
            }
        });
 
        panel.add(agregarButton);
 
        // Área de texto para mostrar estudiantes o resultados
        JTextArea textArea = new JTextArea(10, 40);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
 
        // Mostrar la ventana
        frame.pack();
        frame.setVisible(true);
    }
}

