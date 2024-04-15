package implement;

import Util.HibernateUtil;
import clase.Curso;
import clase.InstructorCurso;
import clase.PersonalIIP;
import dao.DAOInstructorCurso;
import org.hibernate.Session;
import org.hibernate.query.Query;


import java.util.List;

public class InstructorCursoDAOImplement implements DAOInstructorCurso {

    @Override
    public Boolean comprobacion(PersonalIIP instructor, Curso curso) {
        InstructorCurso instructorCurso=null;
        try(Session s=HibernateUtil.getSession().openSession()){
            Query<InstructorCurso>q=s.createQuery("from InstructorCurso ic where ic.instructor.id=:id and ic.curso.id=:idC", InstructorCurso.class);
            q.setParameter("id",instructor.getId());
            q.setParameter("idC",curso.getId());
             instructorCurso=q.getSingleResult();

        }catch (Exception e){
             instructorCurso=null;
        }
        return (instructorCurso!=null?true:false);
    }
}
