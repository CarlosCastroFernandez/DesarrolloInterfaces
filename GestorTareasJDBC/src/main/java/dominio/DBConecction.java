package dominio;

import org.example.Main;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class DBConecction {
    private static final Connection conexion;
    private static Logger logger;

    static {
        logger = Logger.getLogger(DataBase.class.getName());
        String url ;
        String usuario ;
        String password ;
        var cfg=new Properties();
        try {
            InputStream ip=Main.class.getClassLoader().getResourceAsStream("ddb.cfg");
            cfg.load(ip);
            logger.info("Configuracion cargada");
            url="jdbc:mysql://"+cfg.getProperty("host")+":"+cfg.getProperty("port")+"/"+cfg.getProperty("dbname");
            usuario=cfg.getProperty("user");
            password=cfg.getProperty("pass");
        } catch (IOException e) {
            logger.severe("Error procesando configuracion");
            throw new RuntimeException(e);
        }
        try {
            conexion = DriverManager.getConnection(url, usuario, password);
            logger.info("Succesfull conection dataBase");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConexion() {
        return conexion;
    }
}
