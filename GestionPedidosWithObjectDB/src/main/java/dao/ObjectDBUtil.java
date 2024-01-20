package dao;

import lombok.Data;
import lombok.Getter;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
 @Data
 /**
  * Clase que se utiliza para crear una base ded datos embebida en este caso ObjectDB
  */

public class ObjectDBUtil {
     @Getter

    private final static EntityManagerFactory entityManagerFactory;


    static{
        entityManagerFactory = Persistence.createEntityManagerFactory("data.odb");

    }

}
