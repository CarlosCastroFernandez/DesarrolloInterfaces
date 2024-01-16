package dao;

import lombok.Data;
import lombok.Getter;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
 @Data

public class ObjectDBUtil {
     @Getter

    private final static EntityManagerFactory entityManagerFactory;

    static{
        entityManagerFactory = Persistence.createEntityManagerFactory("data.odb");

    }

}
