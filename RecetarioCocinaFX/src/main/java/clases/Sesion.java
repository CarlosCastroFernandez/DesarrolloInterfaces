package clases;

import lombok.Data;


public class Sesion {
    private static Receta recetaActual=null;
    private static Integer posicion=0;

    public static Receta getRecetaActual() {
        return recetaActual;
    }
    private static Receta recetaNueva=null;

    public static void setRecetaActual(Receta recetaActual) {
        Sesion.recetaActual = recetaActual;
    }

    public static Integer getPosicion() {
        return posicion;
    }

    public static Receta getRecetaNueva() {
        return recetaNueva;
    }

    public static void setRecetaNueva(Receta recetaNueva) {
        Sesion.recetaNueva = recetaNueva;
    }

    public static void setPosicion(Integer posicion) {
        Sesion.posicion = posicion;
    }
}
