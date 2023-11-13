package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Clase que gestiona la conexión a la base de datos utilizando JDBC.
 */
public class HibernateUtils {
    private static SessionFactory conexion; // Objeto de conexión a la base de datos
    private static Logger log; // Objeto de registro de eventos

    static {
        log = Logger.getLogger(HibernateUtils.class.getName());

        try {
            // Carga la configuración de la base de datos desde un archivo de propiedades.
            InputStream is = HibernateUtils.class.getClassLoader().getResourceAsStream("config.properties");
            Properties p = new Properties();
            p.load(is);
            Configuration configure=new Configuration();
            configure.configure();
            conexion=configure.buildSessionFactory();
            log.info("Conexión exitosa a la base de datos.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Obtiene la conexión a la base de datos.
     *
     * @return El objeto de conexión a la base de datos.
     */
    public static SessionFactory getSession() {
        return conexion;
    }
}