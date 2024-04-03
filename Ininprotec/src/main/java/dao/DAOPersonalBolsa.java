package dao;

import clase.Curso;
import clase.PersonalBolsa;

import java.util.List;

public interface DAOPersonalBolsa {
    public List<PersonalBolsa> getAll();
    public void modNotas(PersonalBolsa alumno);
    public List<PersonalBolsa> getAllByCursoId(Curso curso);
    public List<PersonalBolsa>getAllByCursoIdNuevos(Curso curso);
    public void agregarAlumnoCurso(PersonalBolsa alumno);
    public PersonalBolsa modPersonalBolsa(PersonalBolsa personal);
}