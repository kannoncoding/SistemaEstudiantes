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
import utilidades.Utilidades;


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
    private JButton agregarBtn = new JButton("Agregar Estudiante");
    private JButton mostrarEstudiantesBtn = new JButton("Mostrar Estudiantes");

    // Lista de estudiantes
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
        String entrada = JOptionPane.showInputDialog("Ingrese la cantidad total de estudiantes a registrar:");
        try {
            totalEstudiantes = Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un número válido. La aplicación se cerrará.");
            System.exit(1);
        }
        initializeUI();
    }


// Método para el botón que muestra los estudiantes
private void mostrarEstudiantes() {
    JFrame estudiantesFrame = new JFrame("Lista de Estudiantes");
    estudiantesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    estudiantesFrame.setSize(400, 600);

    JTextArea estudiantesTextArea = new JTextArea();
    estudiantesTextArea.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(estudiantesTextArea);
    estudiantesFrame.add(scrollPane);

    // Append student details to the text area in the specified format
    for (Estudiante estudiante : estudiantes) {
        // Calculate the final score for the current student
        double finalScore = Utilidades.calcularFinalScore(estudiante);

        String studentInfo = estudiante.getNombre() + " " + estudiante.getApellido1() + " " + estudiante.getApellido2() + "\n" + // Student's Full Name
                             estudiante.getCarrera() + "\n" + // Carrera
                             "carne No. " + estudiante.getIdentificador() + "\n" + // carne No. (identificador)
                             "Nota Final: " + String.format("%.2f", finalScore) + "\n\n\n"; // Final Score with two decimal places
        estudiantesTextArea.append(studentInfo);
    }

    // Display the window
    estudiantesFrame.pack();
    estudiantesFrame.setLocationRelativeTo(null);
    estudiantesFrame.setVisible(true);
}



    // Método para inicializar la interfaz de usuario
    private void initializeUI() {
        frame = new JFrame("Registro de Estudiantes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 900);

        panel = new JPanel(new GridLayout(0, 2));

        // Añadir campos de texto y etiquetas al panel
        addComponentsToPanel();

        // Añadir botón para mostrar los estudiantes y su lógica
        panel.add(mostrarEstudiantesBtn);
        mostrarEstudiantesBtn.addActionListener(e -> mostrarEstudiantes());

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

    // Método para añadir componentes al panel
    private void addComponentsToPanel() {
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
        panel.add(agregarBtn);

        // Lógica para agregar estudiantes
        agregarBtn.addActionListener(e -> agregarEstudiante());
    }

    // Método para agregar estudiantes y validar los campos
private void agregarEstudiante() {
    // Recolectar los datos de los campos de texto
    String identificador = identificadorField.getText();
    String nombre = nombreField.getText();
    String apellido1 = apellido1Field.getText();
    String apellido2 = apellido2Field.getText();
    String carrera = carreraField.getText();
    String proyecto1Str = proyecto1Field.getText();
    String proyecto2Str = proyecto2Field.getText();
    String foroAcademicoStr = foroAcademicoField.getText();
    String encuestaStr = encuestaField.getText();
    String juegoStr = juegoField.getText();

    // Validación básica
    if (identificador.isEmpty() || nombre.isEmpty() || apellido1.isEmpty() ||
        apellido2.isEmpty() || carrera.isEmpty() ||
        proyecto1Str.isEmpty() || proyecto2Str.isEmpty() || foroAcademicoStr.isEmpty() ||
        encuestaStr.isEmpty() || juegoStr.isEmpty()) {
        JOptionPane.showMessageDialog(frame, "Todos los campos deben estar llenos", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Convertir calificaciones de String a double
    double proyecto1, proyecto2, foroAcademico, encuesta, juego;
    try {
        proyecto1 = Double.parseDouble(proyecto1Str);
        proyecto2 = Double.parseDouble(proyecto2Str);
        foroAcademico = Double.parseDouble(foroAcademicoStr);
        encuesta = Double.parseDouble(encuestaStr);
        juego = Double.parseDouble(juegoStr);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(frame, "Las calificaciones deben ser números válidos", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Crear instancia de Estudiante y añadirlo a la lista
    Estudiante estudiante = new Estudiante(identificador, nombre, apellido1, apellido2, carrera, proyecto1, proyecto2, foroAcademico, encuesta, juego);
    estudiantes.add(estudiante);
    estudiantesIngresados++;

    // Limpiar campos de texto después de añadir
    limpiarCampos();

    // Actualizar área de texto
    textArea.append("Estudiante agregado: " + nombre + "\n");

    // Verificar si se alcanzó el total de estudiantes y mostrar mensaje de completado
    if (estudiantesIngresados >= totalEstudiantes) {
        JOptionPane.showMessageDialog(frame, "Registro completado, " + totalEstudiantes + " estudiantes agregados", "Registro Completado", JOptionPane.INFORMATION_MESSAGE);
    }
}

private void limpiarCampos() {
    identificadorField.setText("");
    nombreField.setText("");
    apellido1Field.setText("");
    apellido2Field.setText("");
    carreraField.setText("");
    proyecto1Field.setText("");
    proyecto2Field.setText("");
    foroAcademicoField.setText("");
    encuestaField.setText("");
    juegoField.setText("");
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
