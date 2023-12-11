package com.example.hibernatewithfx.domain;

import lombok.extern.java.Log;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
@Log
public class HibernateUtil {
    private static  SessionFactory sf;
    static{
        try{
            Configuration cf=new Configuration();
            cf.configure("hibernate.cfg.xml");
            sf=cf.buildSessionFactory();
            log.info("SesionFactory created");
        }catch(Exception e){
            log.severe("Error al crear SesionFactory");
        }

    }
    public static SessionFactory getSesionFactory(){
        return sf;
    }
}
