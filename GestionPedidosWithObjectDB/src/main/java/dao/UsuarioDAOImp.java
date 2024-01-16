package dao;

import clases.Producto;
import clases.Usuario;
import errores.ContraseñaInvalida;
import errores.UsuarioNoExiste;
import utils.UsuarioDAO;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz UsuarioDAO que gestiona la obtención de información de usuarios desde una base de datos.
 */
public class UsuarioDAOImp implements UsuarioDAO {
    private static EntityManager conexion; // Objeto de conexión a la base de datos
    private static String loadByPersonal = "SELECT * FROM usuario WHERE email=?"; // Consulta SQL para cargar un usuario por su email
    private static String load = "SELECT * FROM usuario WHERE id=?"; // Consulta SQL para cargar un usuario por su ID

    /**
     * Constructor de la clase UsuarioDAOImp.
     *
     * @param conn Objeto de conexión a la base de datos.
     */
    public UsuarioDAOImp(EntityManager conn) {
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
        ArrayList<Usuario>lista=new ArrayList<>();
        try  {
            TypedQuery<Usuario> q = conexion.createQuery("select u from Usuario u where email=:correo", Usuario.class);
            q.setParameter("correo", email);
            lista =(ArrayList<Usuario>) q.getResultList();
            usuario=lista.get(0);
            System.out.println(usuario);
            if (!password.equals(usuario.getContraseña())) {
                throw new ContraseñaInvalida("Contraseña Inbalida");
            }
        } catch (Exception e) {
            throw new UsuarioNoExiste("No existe el usuario");
        }finally {
            conexion.close();
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
        try  {
            usuario = conexion.find(Usuario.class, clases.Session.getUsuario().getId());

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            conexion.close();
        }
        return usuario;
    }
    public void saveAll(List<Usuario> usuarios){
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try{
            em.getTransaction().begin();
            for(Usuario u : usuarios){
                em.persist(u);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    }


