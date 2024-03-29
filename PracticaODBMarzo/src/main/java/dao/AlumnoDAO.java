package dao;

import clase.Alumno;
import utils.ObjectDBUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class AlumnoDAO {

    public static void insertarAlumno(Alumno alumno) {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(alumno);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    public static void listarTodo(){
        EntityManager em=ObjectDBUtil.getEntityManagerFactory().createEntityManager();

        TypedQuery<Alumno> q=em.createQuery("select a from Alumno a", Alumno.class);
        ArrayList<Alumno>listaAlumno= (ArrayList<Alumno>) q.getResultList();
        if(!listaAlumno.isEmpty()){
            for (Alumno alumno:listaAlumno){
                System.out.println(alumno);
            }
        }
    }
    public static void listarSuspenso(){
        EntityManager em=ObjectDBUtil.getEntityManagerFactory().createEntityManager();

        TypedQuery<Alumno> q=em.createQuery("select a from Alumno a where a.di<5 or a.ad<5", Alumno.class);
        ArrayList<Alumno>listaAlumno= (ArrayList<Alumno>) q.getResultList();
        if(!listaAlumno.isEmpty()){
            for (Alumno alumno:listaAlumno){
                System.out.println(alumno);
            }
        }else{
            System.out.println("No hay Suspensos");
        }
    }

    public static void estadistica(){
        EntityManager em=ObjectDBUtil.getEntityManagerFactory().createEntityManager();

        TypedQuery<Alumno> q=em.createQuery("select a from Alumno a", Alumno.class);
        ArrayList<Alumno>listaAlumno= (ArrayList<Alumno>) q.getResultList();
        if(!listaAlumno.isEmpty()){
            for (Alumno alumno:listaAlumno){
                System.out.println("El alumno "+alumno.getNombre()+" tiene una media de: "+(alumno.getDi()+alumno.getAd())/2);
            }
        }
        q=em.createQuery("select a from Alumno a where a.di >4.9 and a.ad>4.9", Alumno.class);
        ArrayList<Alumno>listaAlumnoSinSuspenso= (ArrayList<Alumno>) q.getResultList();
        Double ratioAprobados=(double)listaAlumnoSinSuspenso.size()/listaAlumno.size();
        System.out.println("El Ratio de aprobados es de: "+ratioAprobados);
    }
    public static void actualizarAlumno(Alumno alumno){
        EntityManager em=ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        Alumno alumnoMod=em.find(Alumno.class,alumno.getEmail());
        em.getTransaction().begin();
        alumnoMod.setNombre(alumno.getNombre());
        em.merge(alumnoMod);
        em.getTransaction().commit();
        em.close();
    }
}
