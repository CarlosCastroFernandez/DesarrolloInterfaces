package implement;

import Util.HibernateUtil;
import clase.PersonalIIP;
import dao.DAOPersonalIIP;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class PersonalIIPDAOImplement implements DAOPersonalIIP {
    public PersonalIIPDAOImplement(){

    }


    @Override
    public List<PersonalIIP> getAll() {
        List<PersonalIIP>listaInstructores=new ArrayList<>();
        try(Session s= HibernateUtil.getSession().openSession()){
            Query<PersonalIIP>q=s.createQuery("FROM PersonalIIP", PersonalIIP.class);
            listaInstructores=q.getResultList();

        }catch (Exception e){
            e.printStackTrace();
        }
        return listaInstructores;
    }
}
