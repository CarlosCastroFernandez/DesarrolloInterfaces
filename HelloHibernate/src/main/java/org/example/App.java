package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {


        System.out.println( "Hello World!" );

        Configuration configuration=new Configuration();
        configuration.configure();
        SessionFactory sf= configuration.buildSessionFactory();
        Usuario u=new Usuario();
        u.setNombre("Santiago");
        u.setEmail("Santi@Santi.santi");
        sf.inSession(session -> { //SOLO PARA LEER PERO NO CAMBIA BBDDD
            Query<Usuario> q=session.createQuery("from Usuario where id=id",Usuario.class);
            q.setParameter("id",1);
            q.getResultList().forEach(System.out::println);
        });
        /*sf.inTransaction(session -> {
            Usuario usuario=session.get(Usuario.class,1);
            System.out.println(usuario);
            usuario.setEmail("sdffdsfbgg");

        });*/
        /*try(Session s=sf.openSession()){
            Transaction t= s.beginTransaction();
            s.persist(u);
            t.commit();
            Query q=s.createQuery("FROM Usuario ");
            ArrayList<Usuario>listaUsuario= (ArrayList<Usuario>) q.getResultList();

            System.out.println(listaUsuario);
        }*/
    }
}
