package dominio;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class Tarea {
    private Long id;
    private String titulo;
    private String prioridad;
    private Long usuario_id;
    private String categoria;
    private String descripcion;
    public Tarea(ResultSet rs) throws SQLException {
        id=rs.getLong("id");
        titulo=rs.getString("titulo");
        prioridad=rs.getString("prioridad");
        usuario_id=rs.getLong("usuario_id");
        categoria=rs.getString("categoria");
        descripcion=rs.getString("descripcion");
    }
    public String[]toArrayString(){
        return new String[]{
                String.valueOf(id),prioridad, String.valueOf(usuario_id),categoria,descripcion
        };
    }

}
