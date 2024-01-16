package domain.producto;

import domain.DAO;
import domain.ObjectDBUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO implements DAO<Producto>{

    @Override
    public Producto save(Producto producto) {
        return null;
    }

    public void saveAll(List<Producto> productos){

        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try{
            em.getTransaction().begin();
            for(Producto p : productos){
                em.persist(p);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Producto update(Producto producto) {
        return null;
    }

    @Override
    public boolean remove(Producto producto) {
        return false;
    }

    @Override
    public Producto get(Long id) {

        Producto salida = null;
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try {
            salida = em.find(Producto.class, id);
        } finally {
            em.close();
        }
        return salida;
    }

    @Override
    public List<Producto> getAll() {

        List<Producto> salida  = new ArrayList<>(0);

        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try{
            TypedQuery<Producto> query = em.createQuery("select p from Producto p", Producto.class);
            salida = query.getResultList();
        } finally {
            em.close();
        }
        return salida;
    }
}
