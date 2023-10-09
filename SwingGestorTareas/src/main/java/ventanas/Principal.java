package ventanas;

import org.example.Sesion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;

public class Principal extends JFrame{
    private JPanel panel;
    private JTable tabla;
    private JPanel panelFormulario;
    private JPanel panelOrden;
    private JLabel labelTarea;
    private JTextField textTarea;
    private JComboBox comboBox;
    private JTextField txtEstado;
    private JLabel labelEstado;
    private JLabel labelPrioridad;
    private JLabel labelDescripcion;
    private JTextArea txtArea;
    private JButton añadirButton;
    private JPanel botonAñadir;
    private JLabel labelInfo;
    private DefaultTableModel datos;
    private JMenuBar menuBar;
    public Principal(){
        this.setContentPane(panel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(800,400);
        this.setTitle("Listado de tareas");
        this.setResizable(false);
        this.setVisible(true);
        datos=(DefaultTableModel) tabla.getModel();
        datos.addColumn("Tarea");
        datos.addColumn("Prioridad");
        datos.addColumn("Estado");
        datos.addColumn("Descripcion");
        loadUser();
        tabla.setModel(datos);
        labelInfo.setText(Sesion.getUser());
        menuBar=new JMenuBar();
        JMenu menu=new JMenu("Archivo");
        menuBar.add(menu);

        JMenuItem menuItem=new JMenuItem("Logout");
        menu.add(menuItem);
        menu.addSeparator();
        JMenuItem menuItem2=new JMenuItem("Salir");
        menu.add(menuItem2);
        menu.addSeparator();
        JMenuItem menuItemGuardar=new JMenuItem("Guardar");
        menu.add(menuItemGuardar);
        this.setJMenuBar(menuBar);
        añadirButton.addActionListener(e ->addToTable());
        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                readTable();
            }
        });
        menuItem.addActionListener(e -> actionMenu1());
        menuItemGuardar.addActionListener(e->accionSave());

    }

    private void accionSave() {
        String sesion=Sesion.getUser()+".obj";
        try (ObjectOutputStream escritura=new ObjectOutputStream(new FileOutputStream(sesion))){

            escritura.writeObject(datos);
            System.out.println("Guardado con exito");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadUser(){
        String sesion=Sesion.getUser()+".obj";
        try {
            ObjectInputStream lectura=new ObjectInputStream(new FileInputStream(sesion));
            System.out.println("Cargando datos...");
            datos=(DefaultTableModel) lectura.readObject();
            lectura.close();
        } catch (IOException e) {
            //throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void actionMenu1() {
        Sesion.setUser(null);
        this.dispose();
        (new Login()).setVisible(true);
    }


    private void readTable() {
        var selected=tabla.getSelectedRow();
        System.out.println(selected);
        System.out.println(datos.getValueAt(selected,0));
        System.out.println(datos.getValueAt(selected,1));
        System.out.println(datos.getValueAt(selected,2));
        System.out.println(datos.getValueAt(selected,3));
    }

    private void addToTable() {
        String tarea= textTarea.getText();
        String prioridad=(String)comboBox.getSelectedItem();
        String estado=txtEstado.getText();
        String descripcion=txtArea.getText();
        String[]dato={tarea,prioridad,estado,descripcion};
        datos.addRow(dato);
        System.out.println(tarea);
        System.out.println(prioridad);
        System.out.println(estado);
        System.out.println(descripcion);
    }
}
