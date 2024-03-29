package dao;

import clase.Actividad;
import clase.Alumno;

public interface DAO <T>{
    public T getBYAlumnoByCorreo(String correo);
    public T getActividadById(Long id);
}
