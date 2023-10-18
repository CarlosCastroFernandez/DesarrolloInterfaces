package usuario;

import java.util.ArrayList;

public interface UsuarioDAO {
    public Usuario load(Long id);
    public ArrayList<Usuario>loadAll();
    public Usuario save(Usuario u);
    public Usuario update (Usuario u);
    public void remove(Usuario u);
}
