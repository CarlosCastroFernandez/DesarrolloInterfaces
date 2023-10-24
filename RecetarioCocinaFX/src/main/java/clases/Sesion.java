package clases;

import lombok.Data;


public class Sesion {
    private static Receta recetaActual=null;

    public static Receta getRecetaActual() {
        return recetaActual;
    }

    public static void setRecetaActual(Receta recetaActual) {
        Sesion.recetaActual = recetaActual;
    }
}
