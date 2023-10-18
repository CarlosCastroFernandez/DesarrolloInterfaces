package tarea;

import tarea.Tarea;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TareaDAO {

    public Tarea load(Long id);
    public ArrayList<Tarea> loadAll();
    public ArrayList<Tarea>loadAllByResponsable(Long responsable) throws SQLException;
    public Tarea save(Tarea t);
    public Tarea update(Tarea t);
    public Tarea update(Long id,Tarea t);
    public void remove(Tarea t);
}
