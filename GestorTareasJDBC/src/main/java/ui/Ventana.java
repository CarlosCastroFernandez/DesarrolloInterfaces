package ui;

import dominio.DataBase;
import dominio.Tarea;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.util.ArrayList;

public class Ventana extends JFrame {
    private JPanel panel1;
    private JTable table1;
    private DefaultTableModel data;
    public Ventana(){
        this.setContentPane(panel1);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600,400);
        this.setTitle("Acceso con JDBC");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        table1.setRowHeight(40);
        data= (DefaultTableModel) table1.getModel();
        data.addColumn("id");
        data.addColumn("tarea");
        data.addColumn("prioridad");
        data.addColumn("usuario");
        data.addColumn("categoria");
        data.addColumn("descripcion");
        DataBase.getConexion();



       /* var datos=DataBase.getAll();
        for(int i=0;i<datos.size();i++){
            data.addRow(datos.get(i));
        }*/
        table1.doLayout();

        ArrayList<Tarea>tareas= DataBase.getAllTarea();
        fillTable(tareas);

        DataBase.closeConexion();

    }

    private void fillTable(ArrayList<Tarea>tareas) {
        DataBase.getAllTarea().forEach((t)->System.out.println(t));

        tareas.forEach((t)->{
            data.addRow(t.toArrayString());
        });
    }

}
