package implement;

import Util.HibernateUtil;
import clase.Servicio;
import dao.DAOServicio;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ServicioDAOImplement implements DAOServicio {
    @Override
    public Servicio subida(Servicio servicio) {
        Servicio servicioBBD=servicio;
        try(Session s= HibernateUtil.getSession().openSession()){
            Transaction t=s.beginTransaction();
            s.persist(servicioBBD);
            t.commit();
        }
        return servicioBBD;
    }

    @Override
    public List<Servicio> getAll() {
        List<Servicio>servicioBBBD=new ArrayList<>();
        try(Session s= HibernateUtil.getSession().openSession()){
            Query<Servicio>q=s.createQuery("FROM Servicio");
            servicioBBBD=q.getResultList();
        }
        return servicioBBBD;
    }
}
