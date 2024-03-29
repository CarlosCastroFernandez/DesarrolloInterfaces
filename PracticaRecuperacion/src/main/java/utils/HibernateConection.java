package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Logger;

public class HibernateConection {

    private static  SessionFactory session;
    private static Logger log=Logger.getLogger(HibernateConection.class.getName());
    static{
        try{
            Configuration c=new Configuration();
            c.configure("hibernate.cfg.xml");
            session=c.buildSessionFactory();
            log.info("Conexion establecida");
        }catch (Exception e){
            log.severe("FALLO CONEXION");
        }

    }
    public static SessionFactory getSession(){
        return session;
    }
}
