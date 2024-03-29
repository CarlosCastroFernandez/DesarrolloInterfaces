package Util;

import clase.Curso;

public class Utilidad {

    private static Curso curso;

    public static Curso getCurso() {
        return curso;
    }

    public static void setCurso(Curso curso) {
        Utilidad.curso = curso;
    }
}
