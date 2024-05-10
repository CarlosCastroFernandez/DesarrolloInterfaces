package Util;

import clase.Curso;
import clase.PersonalBolsa;
import clase.PersonalIIP;

public class Utilidad {

    private static Curso curso;
    private static PersonalBolsa alumno;
    private static PersonalIIP instructor;
    private static Boolean comprDNI=false;
    private static PersonalIIP admin;
    private static PersonalIIP logPersonal;
    private static Boolean miPerfil=false;

    public static Curso getCurso() {
        return curso;
    }

    public static Boolean getComprDNI() {
        return comprDNI;
    }

    public static Boolean getMiPerfil() {
        return miPerfil;
    }

    public static void setMiPerfil(Boolean miPerfil) {
        Utilidad.miPerfil = miPerfil;
    }

    public static PersonalIIP getAdmin() {
        return admin;
    }

    public static PersonalIIP getLogPersonal() {
        return logPersonal;
    }

    public static void setLogPersonal(PersonalIIP logPersonal) {
        Utilidad.logPersonal = logPersonal;
    }

    public static void setAdmin(PersonalIIP admin) {
        Utilidad.admin = admin;
    }

    public static void setComprDNI(Boolean comprDNI) {
        Utilidad.comprDNI = comprDNI;
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
