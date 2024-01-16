package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * Hello world!
 *
 */
public class App{

    private static final EntityManagerFactory emf;

    static{
        emf = Persistence.createEntityManagerFactory("data.odb");
    }


    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Teacher t = new Teacher();
            t.setName(  RandomStringUtils.randomAlphabetic(10) );
            for(int i=0; i<1+Math.round(9*Math.random());i++) {
                Alumn a = new Alumn();
                a.setName(RandomStringUtils.randomAlphabetic(5, 10));
                t.addAlumn(a);
            }
            em.persist( t );
            em.getTransaction().commit();

            TypedQuery<Teacher> q = em.createQuery("select t from Teacher t", Teacher.class);
            q.getResultList().forEach( (tt)->{
                System.out.println( tt.getName() );
                System.out.println( tt.getAlumns().size());
                tt.getAlumns().forEach( System.out::println);
            });

            System.out.println("----");

            q = em.createQuery( " select t from Teacher t join t.alumns a group by t order by count(a) desc", Teacher.class);
            System.out.println( q.getResultList().get(0));

        }
        finally {
            em.close();
        }
    }
}
