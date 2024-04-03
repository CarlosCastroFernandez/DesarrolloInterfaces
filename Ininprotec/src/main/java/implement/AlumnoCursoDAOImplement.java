package implement;

import Util.HibernateUtil;
import clase.AlumnoCurso;
import clase.Curso;
import clase.PersonalBolsa;
import dao.DAOAlumnoCurso;
import org.hibernate.Session;
import org.hibernate.query.Query;

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
}
