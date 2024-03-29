package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class MYSQLConection {

    private static Logger log = Logger.getLogger(MYSQLConection.class.getName());
    private static final Connection conexion;

    static {
        InputStream is=MYSQLConection.class.getClassLoader().getResourceAsStream("mysql.properties");
        Properties p=new Properties();
        try {
            p.load(is);
            conexion= DriverManager.getConnection("jdbc:mysql://localhost"+":"+p.getProperty("port")+"/"+
                            p.getProperty("nombreBD"),p.getProperty("user"),p.getProperty("password"));
            log.info("Conexion Succesfully");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            log.info("Conexion Failed");
            throw new RuntimeException(e);
        }

    }
    public static Connection getConexion(){
        return conexion;
    }

}
