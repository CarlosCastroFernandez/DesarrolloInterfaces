package implement;

import Util.HibernateUtil;
import clase.Curso;
import clase.InstructorCurso;
import clase.Modulo;
import clase.PersonalIIP;
import dao.DAOPersonalIIP;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class PersonalIIPDAOImplement implements DAOPersonalIIP {
    public PersonalIIPDAOImplement() {

    }


    @Override
    public List<PersonalIIP> getAllInstructores() {
        List<PersonalIIP> listaInstructores = new ArrayList<>();
        try (Session s = HibernateUtil.getSession().openSession()) {
            Query<PersonalIIP> q = s.createQuery("FROM PersonalIIP p where p.instructor=1L or p.instructor=0L", PersonalIIP.class);
            listaInstructores = q.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaInstructores;
    }

    @Override
    public List<PersonalIIP> getAllAdministradores() {
        List<PersonalIIP> listaInstructores = new ArrayList<>();
        try (Session s = HibernateUtil.getSession().openSession()) {
            Query<PersonalIIP> q = s.createQuery("FROM PersonalIIP p where p.instructor=2L", PersonalIIP.class);
            listaInstructores = q.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaInstructores;
    }

    @Override
    public void subir(PersonalIIP instructor) {
        try (Session s = HibernateUtil.getSession().openSession()) {
            Transaction t = s.beginTransaction();
            s.persist(instructor);
            t.commit();

        }
    }

    @Override
    public void actualizar(PersonalIIP instructor) {
        try (Session s = HibernateUtil.getSession().openSession()) {
            Transaction t = s.beginTransaction();
            PersonalIIP instructorBBDD = s.get(PersonalIIP.class, instructor.getId());
            instructorBBDD = instructor;
            s.merge(instructorBBDD);
            t.commit();

        }
    }

    @Override
    public List<PersonalIIP> instructorByCursoId(Curso curso) {
        try (Session s = HibernateUtil.getSession().openSession()) {
            Query<PersonalIIP> q = s.createQuery("select distinct i.instructor from InstructorCurso i where i.curso.id=:id", PersonalIIP.class);
            q.setParameter("id", curso.getId());
            List<PersonalIIP> instructores = q.getResultList();
            return instructores;
        }
    }

    @Override
    public List<PersonalIIP> instructorSinCursoId() {
        try (Session s = HibernateUtil.getSession().openSession()) {
            Query<PersonalIIP> q = s.createQuery("SELECT DISTINCT instructor FROM PersonalIIP instructor LEFT JOIN InstructorCurso ic ON instructor.id = ic.instructor.id WHERE ic.curso IS NULL", PersonalIIP.class);
            List<PersonalIIP> instructores = q.getResultList();
            return instructores;
        }
    }

    @Override
    public void borrar(PersonalIIP instructor) {
        try (Session s = HibernateUtil.getSession().openSession()) {
            Transaction t=s.beginTransaction();
            PersonalIIP instructorBBDD=s.get(PersonalIIP.class,instructor.getId());
            Query q=s.createQuery("delete  from InstructorCurso i where i.instructor.id=:id");
            q.setParameter("id",instructor.getId());
            int deletedCount = q.executeUpdate();
            for (Modulo modulo : instructorBBDD.getModulos()) {
                modulo.setInstructor(null);  // Desvincula el instructor
                s.saveOrUpdate(modulo); // Actualiza el módulo para guardar el cambio
            }
            s.remove(instructorBBDD);
            t.commit();
        }
    }

    @Override
    public PersonalIIP login(String correo) {
        PersonalIIP personalBBDD=null;
        try (Session s = HibernateUtil.getSession().openSession()) {
            Query<PersonalIIP>q=s.createQuery("select p from PersonalIIP p where p.correo=:correo");
            q.setParameter("correo",correo);
            personalBBDD=q.getSingleResult();
            if(personalBBDD!=null){
                return personalBBDD;
            }else{
                return null;
            }
        }catch(NoResultException e){
            return null;
        }
    }

    @Override
    public void cambioPassword(PersonalIIP personal) {
        try (Session s = HibernateUtil.getSession().openSession()) {
            Transaction t=s.beginTransaction();
            PersonalIIP personalBBDD=s.get(PersonalIIP.class,personal.getId());
            personalBBDD.setContraseña(personal.getContraseña());
            t.commit();

        }

    }
}
