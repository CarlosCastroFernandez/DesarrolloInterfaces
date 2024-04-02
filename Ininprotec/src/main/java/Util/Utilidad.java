package Util;

import clase.Curso;
import clase.PersonalBolsa;

public class Utilidad {

    private static Curso curso;
    private static PersonalBolsa alumno;

    public static Curso getCurso() {
        return curso;
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
