package dao;

import clase.Curso;
import clase.PersonalIIP;

import java.util.List;

public interface DAOPersonalIIP {
    public List<PersonalIIP> getAll();

    public void subir(PersonalIIP instructor);
    public void actualizar(PersonalIIP instructor);
    public List<PersonalIIP>instructorByCursoId(Curso curso);
    public List<PersonalIIP>instructorSinCursoId();
    public void borrar(PersonalIIP instructor);
}
