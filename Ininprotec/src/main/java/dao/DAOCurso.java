package dao;

import clase.AlumnoCurso;
import clase.Curso;
import clase.Modulo;

import java.util.List;

public interface DAOCurso{
   // public void subir(T objeto);
    public List<Curso> getAll();
    public void editCurso(Curso curso,List<Modulo>modulosBorrados);
}
