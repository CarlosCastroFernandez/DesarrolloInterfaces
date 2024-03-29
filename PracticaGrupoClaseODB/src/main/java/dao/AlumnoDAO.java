package dao;

import clase.Alumno;
import utils.ObjectDBUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class AlumnoDAO {

    public static void insertarAlumno(Alumno alumno){
        EntityManager em= ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(alumno);
            em.getTransaction().commit();
        }finally {
            em.close();
        }



    }
    public static void listarTodo(){
        EntityManager em= ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Alumno>q=em.createQuery("select a from Alumno a", Alumno.class);
        ArrayList<Alumno>alumnos= (ArrayList<Alumno>) q.getResultList();
        Long contador=0L;

        for(Alumno alumnito:alumnos){
            contador++;
            System.out.println(contador+": "+alumnito);
        }
    }
    public static void listarSuspensos(){
        EntityManager em= ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Alumno>q=em.createQuery("select a from Alumno a where a.ad<5 or a.di<5", Alumno.class);
        ArrayList<Alumno>alumnosSupensos= (ArrayList<Alumno>) q.getResultList();
        for(Alumno alumnito:alumnosSupensos){
            System.out.println(alumnito);
        }

    }
    public static void estadistica(){
        EntityManager em= ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Alumno>q=em.createQuery("select a from Alumno a", Alumno.class);
        ArrayList<Alumno>alumnos= (ArrayList<Alumno>) q.getResultList();
        for(Alumno alumnito:alumnos){
            Double notaMedia=(alumnito.getAd()+alumnito.getDi())/2;
            System.out.println("La Nota Media de "+alumnito.getNombre()+" Es: "+notaMedia);

        }
        q=em.createQuery("select a from Alumno a where a.ad>4.9 and a.di>4.9", Alumno.class);
        ArrayList<Alumno>alumnosAprobados= (ArrayList<Alumno>) q.getResultList();
        Double ratioAprobados=(double)alumnosAprobados.size()/alumnos.size();
        System.out.println("Ratio de Aprobados "+ratioAprobados);
    }
    public static void AlumnoMod(Alumno alumno){
        EntityManager em= ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        Alumno alumnoBDD=em.find(Alumno.class,alumno.getId());
        em.getTransaction().begin();
        alumnoBDD.setNombre(alumno.getNombre());
        em.merge(alumnoBDD);
        em.getTransaction().commit();
        em.close();
    }
    public  void alumnoDelete(Alumno alumno){
        EntityManager em= ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        Alumno alumnoBDD=em.find(Alumno.class,alumno.getId());
        em.getTransaction().begin();
        em.remove(alumnoBDD);
        em.getTransaction().commit();
        em.close();
    }

}
