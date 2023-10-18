package usuario;

import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
@Log
public class UsuarioDAOImp implements UsuarioDAO {
    private static Connection connection;
    private static final String QUERY_LOAD="select *from usuario where id=?";
    public UsuarioDAOImp(Connection conexion){
        connection=conexion;
    }
    @Override
    public Usuario load(Long id) {
        Usuario t=null;
        try {
            PreparedStatement pst=UsuarioDAOImp.connection.prepareStatement(QUERY_LOAD);
            log.info(QUERY_LOAD);
            log.info(id.toString());
            pst.setLong(1,id);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                t=new Usuario();
                t.setId(rs.getLong("id"));
                t.setNombre(rs.getString("nombre"));
                t.setEmail(rs.getString("email"));

            }else{
                log.warning("Usuario not Found");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return t;
    }

    @Override
    public ArrayList<Usuario> loadAll() {
        return null;
    }

    @Override
    public Usuario save(Usuario u) {
        return null;
    }

    @Override
    public Usuario update(Usuario u) {
        return null;
    }

    @Override
    public void remove(Usuario u) {

    }
}
