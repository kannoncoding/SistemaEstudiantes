package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
//    private JTextArea textArea;  >por eliminarse

// por implementar tablas en vez de texto
    private JTable table;
    private DefaultTableModel model;    
    
    private JScrollPane scrollPane;

    // Declaraciones de campos de texto
    private JTextField identificadorField = new JTextField(10);
    private JTextField nombreField = new JTextField(10);
    private JTextField apellido1Field = new JTextField(10);
    private JTextField apellido2Field = new JTextField(10);
    private JTextField carreraField = new JTextField(10);
    private JButton agregarBtn = new JButton("Agregar Estudiante");
    private JButton mostrarEstudiantesBtn = new JButton("Mostrar Estudiantes");
    private JButton eliminarBtn = new JButton("Eliminar Estudiante");
    private JTextField eliminarIdField = new JTextField(10);

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

    // limpiar el modelo antes de añadir nuevas filas?
    model.setRowCount(0);

    // Agrega los datos de los estudiantes al modelo de la tabla
    for (Estudiante estudiante : estudiantes) {
        double finalScore = Utilidades.calcularFinalScore(estudiante);
        String nombreCompleto = estudiante.getNombre() + " " + estudiante.getApellido1() + " " + estudiante.getApellido2();
        Object[] row = {estudiante.getIdentificador(), nombreCompleto, estudiante.getCarrera(), String.format("%.2f", finalScore)};
        model.addRow(row);
    }

    JScrollPane scrollPane = new JScrollPane(table);
    estudiantesFrame.add(scrollPane);

    // Display the window
    estudiantesFrame.pack();
    estudiantesFrame.setLocationRelativeTo(null);
    estudiantesFrame.setVisible(true);
}


private void eliminarEstudiante() {
    String idParaEliminar = eliminarIdField.getText();

    if (idParaEliminar.isEmpty()) {
        JOptionPane.showMessageDialog(frame, "Ingrese un ID para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int estudianteIndex = -1; // Índice del estudiante en la lista
    Estudiante estudianteParaEliminar = null;
    for (int i = 0; i < estudiantes.size(); i++) {
        if (estudiantes.get(i).getIdentificador().equals(idParaEliminar)) {
            estudianteParaEliminar = estudiantes.get(i);
            estudianteIndex = i;
            break;
        }
    }

    if (estudianteParaEliminar == null) {
        JOptionPane.showMessageDialog(frame, "No se encontró el estudiante con ID: " + idParaEliminar, "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Confirmación para eliminar al estudiante
    int confirmacion = JOptionPane.showConfirmDialog(frame,
        "¿Eliminar al Estudiante " + estudianteParaEliminar.getNombreCompleto() + 
        " con carné " + estudianteParaEliminar.getIdentificador() + "?",
        "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

    if (confirmacion == JOptionPane.YES_OPTION) {
        // Elimina del modelo de la tabla y de la lista
        if (estudianteIndex != -1) {
            estudiantes.remove(estudianteIndex);
            model.removeRow(estudianteIndex);
        }
        actualizarArchivos();  // Actualiza los archivos tras la eliminación
        JOptionPane.showMessageDialog(frame, "Estudiante eliminado correctamente", "Eliminado", JOptionPane.INFORMATION_MESSAGE);
    }
}


private void actualizarArchivos() {
    // Sobreescribe el archivo Estudiantes.txt
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("Estudiantes.txt"))) {
        for (Estudiante estudiante : estudiantes) {
            bw.write(estudiante.toArchivo());
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(frame, "Error al guardar en archivo", "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Sobreescribe el archivo Promedios.txt
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("Promedios.txt"))) {
        for (Estudiante estudiante : estudiantes) {
            bw.write(estudiante.toPromediosArchivo());
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(frame, "Error al guardar en archivo", "Error", JOptionPane.ERROR_MESSAGE);
    }
}


    // Método para inicializar la interfaz de usuario

private void initializeUI() {
    frame = new JFrame("Registro de Estudiantes");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(700, 900);

    JPanel mainPanel = new JPanel(new BorderLayout());
    JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 10)); // Usamos un GridLayout para los inputs y botones

    // Añade campos y botones al panel de inputs
    inputPanel.add(new JLabel("Identificador:"));
    inputPanel.add(identificadorField);
    inputPanel.add(new JLabel("Nombre:"));
    inputPanel.add(nombreField);
    inputPanel.add(new JLabel("Primer apellido:"));
    inputPanel.add(apellido1Field);
    inputPanel.add(new JLabel("Segundo apellido:"));
    inputPanel.add(apellido2Field);
    inputPanel.add(new JLabel("Carrera:"));
    inputPanel.add(carreraField);
    inputPanel.add(new JLabel("Proyecto 1:"));
    inputPanel.add(proyecto1Field);
    inputPanel.add(new JLabel("Proyecto 2:"));
    inputPanel.add(proyecto2Field);
    inputPanel.add(new JLabel("Foro Académico:"));
    inputPanel.add(foroAcademicoField);
    inputPanel.add(new JLabel("Encuesta:"));
    inputPanel.add(encuestaField);
    inputPanel.add(new JLabel("Juego:"));
    inputPanel.add(juegoField);
    inputPanel.add(agregarBtn);
    inputPanel.add(new JLabel("ID para eliminar:"));
    inputPanel.add(eliminarIdField);
    inputPanel.add(eliminarBtn);

    // Configuración del botón agregar
    agregarBtn.addActionListener(e -> agregarEstudiante());
    // Configuración del botón eliminar
    eliminarBtn.addActionListener(e -> eliminarEstudiante());

    // Definir columnas y modelo de la tabla
    String[] columnNames = {"Identificador", "Nombre Completo", "Carrera", "Nota Final"};
    model = new DefaultTableModel(columnNames, 0);
    table = new JTable(model);
    table.setFillsViewportHeight(true);
    JScrollPane tableScrollPane = new JScrollPane(table);

    // Añadir los subpaneles al panel principal
    mainPanel.add(inputPanel, BorderLayout.NORTH);
    mainPanel.add(tableScrollPane, BorderLayout.CENTER);

    // Añadir el panel principal al frame
    frame.add(mainPanel);
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
        panel.add(new JLabel("ID para eliminar:"));
        panel.add(eliminarIdField);
        panel.add(eliminarBtn);

        // Lógica para agregar estudiantes
        agregarBtn.addActionListener(e -> agregarEstudiante());
        
        // Configuración del botón eliminar
        eliminarBtn.addActionListener(e -> eliminarEstudiante());
    }

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

    // Añadir fila al modelo de la tabla
    String nombreCompleto = nombre + " " + apellido1 + " " + apellido2;
    model.addRow(new Object[]{identificador, nombreCompleto, carrera, String.format("%.2f", estudiante.calcularPromedioFinal())});

    // Limpiar campos de texto después de añadir
    limpiarCampos();

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
    
    private void guardarDetallesEnArchivo(Estudiante estudiante) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("Promedios.txt", true))) {
        double finalScore = Utilidades.calcularFinalScore(estudiante);
        String studentDetails = estudiante.getIdentificador() + ", " +
                                estudiante.getNombre() + " " +
                                estudiante.getApellido1() + " " +
                                estudiante.getApellido2() + ", " +
                                estudiante.getCarrera() + ", " +
                                "Proyecto 1: " + estudiante.getProyecto1() + ", " +
                                "Proyecto 2: " + estudiante.getProyecto2() + ", " +
                                "Foro Académico: " + estudiante.getForoAcademico() + ", " +
                                "Encuesta: " + estudiante.getEncuesta() + ", " +
                                "Juego: " + estudiante.getJuego() + ", " +
                                "Nota Final: " + String.format("%.2f", finalScore) + "\n";
        bw.write(studentDetails);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(frame, "Error al guardar en archivo", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    
}
    