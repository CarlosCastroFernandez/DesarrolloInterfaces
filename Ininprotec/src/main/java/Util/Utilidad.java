package Util;

import clase.Curso;
import clase.PersonalBolsa;
import clase.PersonalIIP;

public class Utilidad {

    private static Curso curso;
    private static PersonalBolsa alumno;
    private static PersonalIIP instructor;

    public static Curso getCurso() {
        return curso;
    }

    public static PersonalIIP getInstructor() {
        return instructor;
    }

    public static void setInstructor(PersonalIIP instructor) {
        Utilidad.instructor = instructor;
    }

    public static PersonalBolsa getAlumno() {
        return alumno;
    }

    public static void setAlumno(PersonalBolsa alumno) {
        Utilidad.alumno = alumno;
    }

    public static void setCurso(Curso curso) {
        Utilidad.curso = curso;
    }
}
