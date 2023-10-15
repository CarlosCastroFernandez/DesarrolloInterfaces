package dominio;

import java.sql.*;
import java.util.ArrayList;

public class TareaDAOImp implements TareaDAO{
    private static Connection connection;
    private final static String queryLoadAll="SELECT * FROM tareas";
    private final static String queryLoad="select * from tareas where id=?";
    private final static String querySave="insert into tareas(titulo,prioridad,usuario_id,categoria,descripcion)\n"+
            "VALUES (?,?,?,?,?)";

    private final static String queryLoadAllByResponsable="SELECT * FROM tareas WHERE usuario_id=?";
    public TareaDAOImp(Connection c){
        connection=c;

    }
    @Override
    public Tarea load(Long id) {

        var salida=new Tarea();
        try{
            var pst=connection.prepareStatement(queryLoad);
            pst.setLong(1,id);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                salida=new TareaAdapter().loadFromResulSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return salida;
    }

    @Override
    public ArrayList<Tarea> loadAll() {
        var salida = new ArrayList<Tarea>();
        try {
            Statement st =connection.createStatement();
            ResultSet rs = st.executeQuery(queryLoadAll);
            while (rs.next()) {
                salida.add(new TareaAdapter().loadFromResulSet(rs));
            }
            System.out.println(salida.size());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return salida;

    }

    @Override
    public ArrayList<Tarea> loadAllByResponsable(Long responsable)  {
        var salida=new ArrayList<Tarea>();
        try(PreparedStatement pst=connection.prepareStatement(queryLoadAllByResponsable)){
            pst.setLong(1,responsable);
            ResultSet rs=pst.executeQuery();
            while (rs.next()) {
                salida.add(new TareaAdapter().loadFromResulSet(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return salida;
    }

    @Override
    public Tarea save(Tarea t) {
        try {
            PreparedStatement pst=connection.prepareStatement(querySave);
            pst.setString(1,t.getTitulo());
            pst.setString(2,t.getPrioridad());
            pst.setLong(3,t.getUsuario_id());
            pst.setString(4,t.getCategoria());
            pst.setString(5,t.getDescripcion());
            int filas=pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Tarea update(Tarea t) {
        return null;
    }

    @Override
    public Tarea update(Long id, Tarea t) {
        return null;
    }

    @Override
    public void remove(Tarea t) {

    }
}
