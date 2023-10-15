package ui;

import dominio.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
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


        TareaDAOImp dao=  new TareaDAOImp(DBConecction.getConexion());

        ArrayList<Tarea> tareas=dao.loadAllByResponsable(1L);
        fillTable(tareas);
        Tarea tarita=new Tarea("Peter Pan","Fast",21L,"Trabajo","Peter pan y sus amigos");
        dao.save(tarita);

        DataBase.closeConexion();

        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila=table1.getSelectedRow();
                String id= (String) table1.getValueAt(fila,0);
                Tarea t=dao.load(Long.valueOf( id));
                if(t!=null){
                    JOptionPane.showMessageDialog(null,t);
                }
            }
        });
    }

    private void fillTable(ArrayList<Tarea>tareas) {
        DataBase.getAllTarea().forEach((t)->System.out.println(t));
        for(int i=0;i<tareas.size();i++){
            TareaAdapter tareaAdapter=new TareaAdapter(tareas.get(i));
            data.addRow(tareaAdapter.toArrayString());
        }
        /*tareas.forEach((t)->{
            data.addRow(new TareaAdapter(t).toArrayString());
        });*/
    }

}
