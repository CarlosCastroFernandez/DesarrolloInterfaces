package implement;

import Util.HibernateUtil;
import clase.Curso;
import clase.Modulo;
import clase.PersonalBolsa;
import dao.DAOPersonalBolsa;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PersonalBolsaDAOImplement implements DAOPersonalBolsa {
    @Override
    public List<PersonalBolsa> getAll() {
        return null;
    }

    @Override
    public void modNotas(PersonalBolsa alumno) {
        try(Session s=HibernateUtil.getSession().openSession()){
            Transaction t=s.beginTransaction();
            PersonalBolsa alumnoElegido=s.get(PersonalBolsa.class,alumno.getId());
            for (Modulo moduloElegido : alumnoElegido.getModulos()) {
                for (Modulo modulo : alumno.getModulos()) {
                    if (moduloElegido.getId().equals(modulo.getId())) {
                        moduloElegido.setNotaModulo(modulo.getNotaModulo());
                        break; // Salir del bucle una vez que se ha encontrado el módulo
                    }
                }
            }
            s.merge(alumnoElegido);
            t.commit();
        }
    }

    @Override
    public List<PersonalBolsa> getAllByCursoId(Curso curso) {
        List<PersonalBolsa>listaAlumnos=new ArrayList<>();
        try(Session s= HibernateUtil.getSession().openSession()){
            Curso cursoElegido=s.get(Curso.class,curso.getId());
            listaAlumnos.addAll(cursoElegido.getAlumnos());
        }
        return listaAlumnos;
    }


    public void subir(PersonalBolsa objeto) {
        try(Session s= HibernateUtil.getSession().openSession()){
            Transaction t=s.beginTransaction();
            s.persist(objeto);
            if(objeto.getCursosAlumnos()!=null){
                for(Curso curso:objeto.getCursosAlumnos()){
                    curso.getAlumnos().add(objeto);
                    s.merge(curso);
                }
            }
            if(objeto.getModulos()!=null){
                for(Modulo modulo:objeto.getModulos()){
                    modulo.getAlumnos().add(objeto);
                    s.merge(modulo);
                }
            }

            t.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
