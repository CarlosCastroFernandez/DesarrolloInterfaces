package dao;

import clase.AlumnoCurso;
import clase.Curso;
import clase.PersonalBolsa;

import java.util.HashMap;
import java.util.List;

public interface DAOAlumnoCurso {
    public AlumnoCurso getAlumnoCursoById(PersonalBolsa alumno, Curso curso);
    public List<PersonalBolsa>getFiltradoTodos(List<String>fechas, HashMap<String,String>sentencia,HashMap<String,Object>parametros);
}
