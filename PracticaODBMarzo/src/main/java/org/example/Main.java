package org.example;

import clase.Alumno;
import dao.AlumnoDAO;

public class Main {
    public static void main(String[] args) {
       // Alumno alumno=new Alumno("Pepe","666921216","carlos@gmail.com",7.6,5.5);
        //Alumno alumno1=new Alumno("Carlos","666921216","pepe@gmail.com",7.6,5.5);
       // AlumnoDAO.insertarAlumno(alumno1);
        Alumno alumno1=new Alumno("Naomi","666921216","naomi@gmail.com",4.9,6.0);
        //AlumnoDAO.insertarAlumno(alumno1);
        AlumnoDAO.listarTodo();
        System.out.println("------------------------------  SUSPENSOS  ----------------------------");
        AlumnoDAO.listarSuspenso();
        System.out.println("--------------------------- MEDIA y RATIO APROBADOS ------------------");
        AlumnoDAO.estadistica();
        alumno1.setNombre("CARLITITITITOS");
        AlumnoDAO.actualizarAlumno(alumno1);
        AlumnoDAO.listarTodo();
    }
}