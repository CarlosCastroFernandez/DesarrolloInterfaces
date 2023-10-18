package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class DBConecction {
    private static Connection conexion;
    private static Logger log;
    static{
        log=Logger.getLogger(DBConecction.class.getName());
        try {
            InputStream is=DBConecction.class.getClassLoader().getResourceAsStream("config.properties");
            Properties p=new Properties();
            p.load(is);
            conexion= DriverManager.getConnection("jdbc:mysql://"+p.get("host")+":"+p.get("port")+"/"+p.get("name")
                    ,(String)p.get("user"),(String)p.get("pass"));
            log.info("Succesfull Conexion");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection conexion(){
        return conexion;
    }
}
