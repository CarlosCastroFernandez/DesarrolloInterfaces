package dao;

import clase.AlumnoCurso;
import clase.Curso;
import clase.PersonalBolsa;

public interface DAOAlumnoCurso {
    public AlumnoCurso getAlumnoCursoById(PersonalBolsa alumno, Curso curso);
}
