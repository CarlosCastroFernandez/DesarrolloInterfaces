package ui;

import dominio.*;
import tarea.Tarea;
import tarea.TareaAdapter;
import tarea.TareaDAOImp;
import usuario.Usuario;
import usuario.UsuarioDAOImp;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Ventana extends JFrame {
    private final TareaDAOImp dao = new TareaDAOImp(DBConecction.getConexion());
    private JPanel panel1;
    private JTable table1;
    private JLabel info;
    private JButton pruebaButton;
    private JTextField txtDescripcion;
    private JTextField txtTarea;
    private JTextField txtPrioridad;
    private JLabel txtCategoria;
    private JComboBox comboPrioridad;
    private JComboBox comboCategoria;
    private DefaultTableModel data;

    private ArrayList<Tarea> tareas;
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

        tareas= dao.loadAll();
        fillTable(tareas);

        Tarea tarita=new Tarea("Peter Pan","Fast",21L,"Trabajo","Peter pan y sus amigos");
        dao.save(tarita);


        /*table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila=table1.getSelectedRow();
                String id= (String) table1.getValueAt(fila,0);
                Tarea t=dao.load(Long.valueOf( id));
                if(t!=null){
                    JOptionPane.showMessageDialog(null,t);
                }
            }
        });*/
        table1.getSelectionModel().addListSelectionListener( (ev) -> showDetails(ev) );
        pruebaButton.addActionListener(e -> guardarTarea());
    }

    private void guardarTarea() {
        Tarea t=new Tarea();
        Usuario u=(new UsuarioDAOImp(DBConecction.getConexion()).load(1L));
        t.setUsuario(u);
        t.setUsuario_id(u.getId());
        t.setCategoria((String) comboCategoria.getSelectedItem());
        t.setDescripcion(txtDescripcion.getText());
        t.setPrioridad((String) comboPrioridad.getSelectedItem());
        t.setTitulo(txtTarea.getText());
        t=dao.save(t);
        tareas= dao.loadAll();
        fillTable(tareas);

    }

    private void showDetails(ListSelectionEvent ev) {
        if(!ev.getValueIsAdjusting()){
            Tarea selected=tareas.get(table1.getSelectedRow());
            info.setText(selected.toString());

        }
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
