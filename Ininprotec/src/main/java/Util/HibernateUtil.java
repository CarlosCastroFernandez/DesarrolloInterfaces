package Util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Logger;

public class HibernateUtil {
    private static SessionFactory conexion; // Objeto de conexión a la base de datos
    private static Logger log; // Objeto de registro de eventos

    static {
        log = Logger.getLogger(HibernateUtil.class.getName());

        try {
            Configuration configure=new Configuration();
            configure.configure();
            conexion=configure.buildSessionFactory();
            log.info("Conexión Succesfully.");
        } catch (Exception e) {
            e.printStackTrace();
            log.severe("Conexion Failed");
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
