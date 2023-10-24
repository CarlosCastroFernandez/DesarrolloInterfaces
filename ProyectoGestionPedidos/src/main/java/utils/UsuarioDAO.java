package utils;

import clases.Usuario;

public interface UsuarioDAO {

public Usuario consultaPersonal (String restriccion);
public Usuario load (Integer id);
}
