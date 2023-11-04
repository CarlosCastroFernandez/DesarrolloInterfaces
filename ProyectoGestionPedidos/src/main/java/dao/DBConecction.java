package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Clase que gestiona la conexión a la base de datos utilizando JDBC.
 */
public class DBConecction {
    private static Connection conexion; // Objeto de conexión a la base de datos
    private static Logger log; // Objeto de registro de eventos

    static {
        log = Logger.getLogger(DBConecction.class.getName());

        try {
            // Carga la configuración de la base de datos desde un archivo de propiedades.
            InputStream is = DBConecction.class.getClassLoader().getResourceAsStream("config.properties");
            Properties p = new Properties();
            p.load(is);

            // Establece la conexión a la base de datos utilizando los valores de configuración.
            conexion = DriverManager.getConnection("jdbc:mysql://" + p.get("host") + ":" + p.get("port") + "/"
                    + p.get("name"), (String) p.get("user"), (String) p.get("pass"));

            log.info("Conexión exitosa a la base de datos.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Obtiene la conexión a la base de datos.
     *
     * @return El objeto de conexión a la base de datos.
     */
    public static Connection conexion() {
        return conexion;
    }
}