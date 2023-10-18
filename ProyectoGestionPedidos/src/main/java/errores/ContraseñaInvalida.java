package errores;

public class ContraseñaInvalida extends Exception{
    public ContraseñaInvalida(String mensaje){
        super(mensaje);
    }
}
