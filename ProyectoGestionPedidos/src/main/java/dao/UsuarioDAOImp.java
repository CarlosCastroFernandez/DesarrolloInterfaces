package dao;

import clases.Usuario;
import utils.UsuarioDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementación de la interfaz UsuarioDAO que gestiona la obtención de información de usuarios desde una base de datos.
 */
public class UsuarioDAOImp implements UsuarioDAO {
    private static Connection conexion; // Objeto de conexión a la base de datos
    private static String loadByPersonal = "SELECT * FROM usuario WHERE email=?"; // Consulta SQL para cargar un usuario por su email
    private static String load = "SELECT * FROM usuario WHERE id=?"; // Consulta SQL para cargar un usuario por su ID

    /**
     * Constructor de la clase UsuarioDAOImp.
     *
     * @param conn Objeto de conexión a la base de datos.
     */
    public UsuarioDAOImp(Connection conn) {
        conexion = conn;
    }

    /**
     * Obtiene la información de un usuario específico por su dirección de correo electrónico.
     *
     * @param email La dirección de correo electrónico del usuario que se desea cargar.
     * @return El objeto Usuario con la información del usuario especificado.
     */
    @Override
    public Usuario consultaPersonal(String email) {
        Usuario u = null;
        try {
            PreparedStatement pst = conexion.prepareStatement(loadByPersonal);
            pst.setString(1, email);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                u = new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("email"), rs.getString("contraseña"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return u;
    }

    /**
     * Obtiene la información de un usuario específico por su ID.
     *
     * @param id El identificador del usuario que se desea cargar.
     * @return El objeto Usuario con la información del usuario especificado.
     */
    @Override
    public Usuario load(Integer id) {
        Usuario u = null;
        try {
            PreparedStatement pst = conexion.prepareStatement(load);
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                u = new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("email"), rs.getString("contraseña"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return u;
    }
}

