package org.example;

import clase.Alumno;
import dao.AlumnoDAO;

public class Main {
    public static void main(String[] args) {
        AlumnoDAO alumnoDAO=new AlumnoDAO();
        Alumno alumno1=new Alumno((long)1L,"Cayetano","655617432","pepe@gmail.com",6.8,9.0);
        Alumno alumno2=new Alumno((long)2L,"Dani","655617432","dani@gmail.com",4.5,9.0);
    try{

    AlumnoDAO.insertarAlumno(alumno1);
    AlumnoDAO.insertarAlumno(alumno2);

    }catch(Exception e){

}
        System.out.println("---------------TODOS ALUMNOS-----------------");
        AlumnoDAO.listarTodo();
        System.out.println("--------------- ALUMNOS  SUSPENSOS-----------------");
        AlumnoDAO.listarSuspensos();
        System.out.println("-------------------- NOTA MEDIA Y RATIO-------------------");
        AlumnoDAO.estadistica();
        alumno2.setNombre("Victor");
        AlumnoDAO.AlumnoMod(alumno2);
        AlumnoDAO.listarTodo();
        alumnoDAO.alumnoDelete(alumno1);
        AlumnoDAO.listarTodo();

    }
}