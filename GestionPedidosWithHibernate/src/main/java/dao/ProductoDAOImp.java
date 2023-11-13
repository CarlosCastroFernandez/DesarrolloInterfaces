package dao;

import clases.Producto;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.ProductoDAO;

import java.util.ArrayList;

public class ProductoDAOImp implements ProductoDAO {
    @Override
    public ArrayList<Producto> getAllProducts() {
        ArrayList<Producto>productos=new ArrayList<>();
        try(Session s=HibernateUtils.getSession().openSession()){
            Query<Producto> q=s.createQuery("from Producto");
            productos= (ArrayList<Producto>) q.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }
        return productos;
    }
}
