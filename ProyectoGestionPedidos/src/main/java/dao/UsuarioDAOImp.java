package dao;

import clases.Usuario;
import utils.UsuarioDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAOImp implements UsuarioDAO {
private static Connection conexion;
private static String loadByPersonal="select * from usuario where email=?";
    private static String load="select * from usuario where id=?";
    public UsuarioDAOImp(Connection conn){
        conexion=conn;
    }
    @Override
    public Usuario consultaPersonal(String pass) {
     Usuario u=null;
        try {
            PreparedStatement pst=conexion.prepareStatement(loadByPersonal);
            pst.setString(1,pass);

            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                u=new Usuario(rs.getInt("id"),rs.getString("nombre")
                ,rs.getString("email"),rs.getString("contraseña"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return u;
    }

    @Override
    public Usuario load(Integer id) {
        Usuario u=null;
        try {
            PreparedStatement pst=conexion.prepareStatement(load);
            pst.setInt(1,id);

            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                u=new Usuario(rs.getInt("id"),rs.getString("nombre")
                        ,rs.getString("email"),rs.getString("contraseña"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return u;
    }
}
