package Util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class MYSQLUtil {
    static private Connection conexion;
    private static Logger log=Logger.getLogger(MYSQLUtil.class.getName());
    static{
        InputStream is = MYSQLUtil.class.getClassLoader().getResourceAsStream("mysql.properties");
        Properties p = new Properties();
        try {
            p.load(is);
            conexion = DriverManager.getConnection("jdbc:mysql://" + p.getProperty("url") + ":" + p.getProperty("port") + "/" + p.getProperty("nombreBD"), p.getProperty("user"), p.getProperty("pass"));
            log.info("Conexion MYSQL Exitosa");
        } catch (IOException e) {
            log.warning("Fallo en la conexion MYSQL");
        } catch (SQLException e) {
            log.warning("Fallo en la conexion MYSQL");
        }


    }

    public static Connection getConexion(){
        return conexion;
    }
}
