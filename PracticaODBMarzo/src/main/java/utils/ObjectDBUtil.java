package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ObjectDBUtil {
    private static final EntityManagerFactory entittyManager;

    static {
        entittyManager= Persistence.createEntityManagerFactory("data.odb");
    }

    public static EntityManagerFactory getEntityManagerFactory(){
        return entittyManager;
    }

}
