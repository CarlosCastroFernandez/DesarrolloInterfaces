package dao;

import clase.Actividad;

import java.util.List;

public interface ActividadDAO {

    public List<Actividad>getAll();
    public List<Actividad>getActividadByAlumno(Long id);
    public Actividad getActividadById(Long id);
}
