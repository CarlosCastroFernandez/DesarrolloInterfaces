package dao;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class MYSQLConnection {
    static private Connection conexion;
    static private Logger log;
    static{
        log=Logger.getLogger(MYSQLConnection.class.getName());
        InputStream is = MYSQLConnection.class.getClassLoader().getResourceAsStream("mysql.properties");
        Properties p = new Properties();
        try {
            p.load(is);
            conexion = DriverManager.getConnection("jdbc:mysql://" + p.getProperty("url") + ":" + p.getProperty("port") + "/" + p.getProperty("nombreBD"), p.getProperty("user"), p.getProperty("pass"));
            log.info("jdbc:mysql://"+p.getProperty("url")+":"+p.getProperty("port")+"/"+p.getProperty("nombreBD")+","+p.getProperty("user")+","+p.getProperty("pass"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

     public static Connection getConexion(){
        return conexion;
     }


}