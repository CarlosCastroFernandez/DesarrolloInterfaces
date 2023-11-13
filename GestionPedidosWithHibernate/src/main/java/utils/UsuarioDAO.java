package utils;

import clases.Usuario;
import errores.ContraseñaInvalida;
import errores.UsuarioNoExiste;

public interface UsuarioDAO {

public Usuario consultaPersonal (String restriccion,String password) throws UsuarioNoExiste, ContraseñaInvalida;
public Usuario reLoad ();
}
