package org.example;

import clase.Actividad;
import clase.Alumno;
import implementosdao.ActividadDAOImplement;
import implementosdao.AlumnoDAOImplement;
import org.hibernate.SessionFactory;
import utils.HibernateConection;

import java.util.List;

public class Main {
    public static void main(String[] args) {



        AlumnoDAOImplement dao=new AlumnoDAOImplement();
        ActividadDAOImplement daoActividad=new ActividadDAOImplement();
        /*List<Alumno>alumnos=dao.getAll();
        for(Alumno alumno:alumnos){
            System.out.println(alumno);
        }



        ActividadDAOImplement daoActividad=new ActividadDAOImplement();
        Alumno alumno=dao.getAlumnoById(2L);
        List<Actividad>actividades=daoActividad.getActividadByAlumno(alumno.getId());
        if(!actividades.isEmpty()){
            alumno.setActividades(actividades);
        }
        System.out.println(alumno);
       Alumno alumnoNuevo=new Alumno("CAYETANO","FERNANDA","8765432Q","952316790","lorena@gmail.com");
       alumnoNuevo=dao.insertarAlumno(alumnoNuevo);
        System.out.println(alumnoNuevo);

        alumnoNuevo.setNombre("SANTIAGOOOO");

            dao.modificarAlumno(alumnoNuevo);
            dao.delete(12L);*/

        //SessionFactory funciona= HibernateConection.getSession();
       /* List<Alumno>alumnos=dao.getAll();
        for(Alumno alumno:alumnos){
            System.out.println(alumno);
        }*/
        System.out.println(dao.getAlumnoById(2L));
        System.out.println(dao.getAlumnoBydni("98732165400"));
        //Alumno alumnoNuevo=new Alumno("Carlos","Castro","8765432Q","952316790","lorena@gmail.com");
        //System.out.println(dao.insertarAlumno(alumnoNuevo));
        //dao.modificarAlumno(8L);
        //dao.delete(11L);
        daoActividad.delete(8L);


    }
}