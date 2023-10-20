package tarea;

import tarea.Tarea;
import usuario.UsuarioDAOImp;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TareaAdapter {
    private Tarea t=new Tarea();
    public TareaAdapter(Tarea tarea){
        this.t=tarea;
    }
    public TareaAdapter(){}
    public Tarea loadFromResulSet(ResultSet rs) throws SQLException {
        t.setId(rs.getLong("id"));
        t.setTitulo(rs.getString("titulo"));
        t.setPrioridad(rs.getString("prioridad"));
        t.setUsuario_id(rs.getLong("usuario_id"));
        t.setCategoria(rs.getString("categoria"));
        t.setDescripcion(rs.getString("descripcion"));

        return this.t;
    }
    public String[]toArrayString(){
        return new String[]{
                String.valueOf(t.getId()),t.getTitulo(),t.getPrioridad(),
                (t.getUsuario()==null)?"-":t.getUsuario().getNombre(),
                t.getCategoria(),
                t.getDescripcion()
        };
    }
}
