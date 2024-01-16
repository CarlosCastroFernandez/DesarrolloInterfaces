package errores;

public class UsuarioNoExiste extends Exception{
    public UsuarioNoExiste(String mensaje){
        super(mensaje);
    }
}
