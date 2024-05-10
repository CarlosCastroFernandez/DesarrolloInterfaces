package dao;

import clase.Curso;
import clase.PersonalIIP;

import java.util.List;

public interface DAOPersonalIIP {
    public List<PersonalIIP> getAllInstructores();
    public List<PersonalIIP>getAllAdministradores();

    public void subir(PersonalIIP instructor);
    public PersonalIIP actualizar(PersonalIIP instructor);
    public List<PersonalIIP>instructorByCursoId(Curso curso);
    public List<PersonalIIP>instructorSinCursoId();
    public void borrar(PersonalIIP instructor);
    public PersonalIIP login(String correo);
    public void cambioPassword(PersonalIIP personal);
}
