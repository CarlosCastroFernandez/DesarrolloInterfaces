package dao;

import clases.Usuario;
import errores.ContraseñaInvalida;
import errores.UsuarioNoExiste;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import utils.UsuarioDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementación de la interfaz UsuarioDAO que gestiona la obtención de información de usuarios desde una base de datos.
 */
public class UsuarioDAOImp implements UsuarioDAO {
    private static SessionFactory conexion; // Objeto de conexión a la base de datos
    private static String loadByPersonal = "SELECT * FROM usuario WHERE email=?"; // Consulta SQL para cargar un usuario por su email
    private static String load = "SELECT * FROM usuario WHERE id=?"; // Consulta SQL para cargar un usuario por su ID

    /**
     * Constructor de la clase UsuarioDAOImp.
     *
     * @param conn Objeto de conexión a la base de datos.
     */
    public UsuarioDAOImp(SessionFactory conn) {
        conexion = conn;
    }

    /**
     * Obtiene la información de un usuario específico por su dirección de correo electrónico.
     *
     * @param email La dirección de correo electrónico del usuario que se desea cargar.
     * @return El objeto Usuario con la información del usuario especificado.
     */
    @Override
    public Usuario consultaPersonal(String email, String password) throws UsuarioNoExiste, ContraseñaInvalida {
        Usuario usuario = null;
        try (Session s = conexion.openSession()) {
            Query<Usuario> q = s.createQuery("from Usuario where email=:correo");
            q.setParameter("correo", email);
            usuario = q.getSingleResult();
            System.out.println(usuario);
            if (!password.equals(usuario.getContraseña())) {
                throw new ContraseñaInvalida("Contraseña Inbalida");
            }
        } catch (NoResultException e) {
            throw new UsuarioNoExiste("No existe el usuario");
        }
        return usuario;
    }

    /**
     * Obtiene la información de un usuario específico por su ID.
     *
     * @return El objeto Usuario con la información del usuario especificado.
     */
    @Override
    public Usuario reLoad() {
        Usuario usuario = null;
        try (Session s = conexion.openSession()) {
            usuario = s.get(Usuario.class, clases.Session.getUsuario().getId());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }
}

