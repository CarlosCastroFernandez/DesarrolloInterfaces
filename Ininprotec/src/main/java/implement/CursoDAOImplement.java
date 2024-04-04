package implement;

import Util.HibernateUtil;
import clase.AlumnoCurso;
import clase.Curso;
import clase.Modulo;
import clase.PersonalIIP;
import dao.DAOCurso;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CursoDAOImplement implements DAOCurso {
    public CursoDAOImplement(){

    }

    public void subir(Curso objeto) {
        try(Session s= HibernateUtil.getSession().openSession()){
            Transaction t=s.beginTransaction();
            List<Modulo>modulos=new ArrayList<>();
            s.persist(objeto);
            for(int i=0;i<objeto.getModulos().size();i++){
                PersonalIIP instructor=s.get(PersonalIIP.class,objeto.getModulos().get(i).getInstructor().getId());
                modulos.addAll(objeto.getModulos().get(i).getInstructor().getModulos());
                instructor.setModulos(modulos);
                s.merge(instructor);
            }
            t.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Curso> getAll() {
        List<Curso>cursos=new ArrayList<>();
        try(Session s=HibernateUtil.getSession().openSession()){
            Query q=s.createQuery("FROM Curso ",Curso.class);
            cursos=q.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return cursos;
    }
}
