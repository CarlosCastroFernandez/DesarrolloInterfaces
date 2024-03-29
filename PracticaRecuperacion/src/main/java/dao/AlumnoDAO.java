package dao;

import clase.Alumno;

import java.util.List;

public interface AlumnoDAO {

    public List<Alumno> getAll();
    public Alumno getAlumnoById(Long id);
    public Alumno getAlumnoBydni(String dni);
    public Alumno insertarAlumno(Alumno alumno);
    public void modificarAlumno(Long id);

}
