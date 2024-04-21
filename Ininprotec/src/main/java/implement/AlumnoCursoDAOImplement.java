package implement;

import Util.HibernateUtil;
import clase.AlumnoCurso;
import clase.Curso;
import clase.PersonalBolsa;
import dao.DAOAlumnoCurso;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class AlumnoCursoDAOImplement implements DAOAlumnoCurso {
    @Override
    public AlumnoCurso getAlumnoCursoById(PersonalBolsa alumno, Curso curso) {
        try(Session s= HibernateUtil.getSession().openSession()){
            Query<AlumnoCurso>q=s.createQuery("select a from AlumnoCurso a where a.alumnoId.id=:id and a.cursoId.id=:idCurso", AlumnoCurso.class);
            q.setParameter("id",alumno.getId());
            q.setParameter("idCurso",curso.getId());
            AlumnoCurso alumnoCurso=q.getSingleResult();
            return alumnoCurso;
        }
    }

    @Override
    public List<PersonalBolsa> getFiltradoTodos(List<String> fechas, HashMap<String, String> sentencia,HashMap<String,Object>parametros) {
        List<PersonalBolsa>alumnos=new ArrayList<>();
        List<PersonalBolsa>vacia=null;
        List<Date>fechaParse=new ArrayList<>();
        DateTimeFormatter formato=DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for(int i=0;i<fechas.size();i++){
            LocalDate local=LocalDate.parse(fechas.get(i),formato);
            fechaParse.add(Date.valueOf(local));
        }
        try(Session s=HibernateUtil.getSession().openSession()){
            if(fechas.isEmpty()||fechas.size()==1){
                Query<PersonalBolsa>q=s.createQuery(sentencia.get("sentencia"), PersonalBolsa.class);
                if(parametros.containsKey("curso")){
                    q.setParameter("idCurso",parametros.get("curso"));
                }
                if(parametros.containsKey("altura")){
                    q.setParameter("altura",parametros.get("altura"));
                }
                if(parametros.containsKey("idioma")){
                    q.setParameter("idioma","%"+parametros.get("idioma").toString().toLowerCase()+"%");
                }
                if(parametros.containsKey("formacion")){
                    q.setParameter("titulacion","%"+parametros.get("formacion").toString().toLowerCase()+"%");
                }
                if(parametros.containsKey("sexo")){
                    q.setParameter("sexo",parametros.get("sexo"));
                }
                if(fechas.size()==1){
                    q.setParameter("fecha",fechaParse.get(0));
                }
               alumnos=q.getResultList();

            }else{
                vacia=new ArrayList<>();
                for(int i=0;i<fechas.size();i++){
                    Query<PersonalBolsa>q=s.createQuery(sentencia.get("sentencia"), PersonalBolsa.class);
                    if(parametros.containsKey("curso")) {
                        q.setParameter("idCurso", parametros.get("curso"));
                    }
                    if(parametros.containsKey("altura")){
                        q.setParameter("altura",parametros.get("altura"));
                    }
                    if(parametros.containsKey("idioma")){
                        q.setParameter("idioma","%"+parametros.get("idioma")+"%");
                    }
                    if(parametros.containsKey("formacion")){
                        q.setParameter("titulacion","%"+parametros.get("formacion")+"%");
                    }
                    if(parametros.containsKey("sexo")){
                        q.setParameter("sexo",parametros.get("sexo"));
                    }

                    q.setParameter("fecha",fechaParse.get(i));
                    alumnos=q.getResultList();
                    vacia.addAll(alumnos);
                    alumnos.clear();

                }

            }
        }
        return (vacia==null?alumnos:vacia);
    }
    public List<PersonalBolsa> getFiltradoTodosConCurso(List<String> fechas, HashMap<String, String> sentencia,HashMap<String,Object>parametros) {
        List<PersonalBolsa>alumnos=new ArrayList<>();
        List<PersonalBolsa>vacia=null;
        try(Session s=HibernateUtil.getSession().openSession()){
            if(fechas.isEmpty()||fechas.size()==1){
                Query<PersonalBolsa>q=s.createQuery(sentencia.get("sentencia"), PersonalBolsa.class);
                    q.setParameter("idCurso",parametros.get("curso"));

                if(parametros.containsKey("altura")){
                    q.setParameter("altura",parametros.get("altura"));
                }
                if(parametros.containsKey("idioma")){
                    q.setParameter("idioma","%"+parametros.get("idioma")+"%");
                }
                if(parametros.containsKey("formacion")){
                    q.setParameter("titulacion","%"+parametros.get("formacion")+"%");
                }
                if(fechas.size()==1){
                    q.setParameter("fecha",fechas.get(0));
                }
                if(parametros.containsKey("sexo")){
                    q.setParameter("sexo",parametros.get("sexo"));
                }

                alumnos=q.getResultList();


            }else{
                vacia=new ArrayList<>();
                for(int i=0;i<fechas.size();i++){
                    Query<PersonalBolsa>q=s.createQuery(sentencia.get("sentencia"), PersonalBolsa.class);
                        q.setParameter("idCurso", parametros.get("curso"));
                    if(parametros.containsKey("altura")){
                        q.setParameter("altura",parametros.get("altura"));
                    }
                    if(parametros.containsKey("idioma")){
                        q.setParameter("idioma","%"+parametros.get("idioma")+"%");
                    }
                    if(parametros.containsKey("formacion")){
                        q.setParameter("titulacion","%"+parametros.get("formacion")+"%");
                    }
                    if(parametros.containsKey("sexo")){
                        q.setParameter("sexo",parametros.get("sexo"));
                    }

                    q.setParameter("fecha",fechas.get(i));
                    alumnos=q.getResultList();
                    vacia.addAll(alumnos);
                    alumnos.clear();

                }

            }
        }
        return ((vacia==null?alumnos:vacia));
    }
    public void quitarAlumnoDelCurso(Curso curso,PersonalBolsa alumno) {
        try(Session s=HibernateUtil.getSession().openSession()){
            Transaction t=s.beginTransaction();
            Curso cursoBBD=s.get(Curso.class,curso.getId());
            Iterator<AlumnoCurso> iterator = cursoBBD.getAlumnoCurso().iterator();
            while (iterator.hasNext()) {
                AlumnoCurso alumnoCurso = iterator.next();
                if (alumnoCurso.getAlumnoId().getId()==alumno.getId()) {
                    iterator.remove(); // Remueve el elemento usando el Iterator
                    s.remove(alumnoCurso);
                }
            }
            s.merge(cursoBBD);
            t.commit();
        }

    }

}
