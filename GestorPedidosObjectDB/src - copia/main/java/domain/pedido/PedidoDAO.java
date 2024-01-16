package domain.pedido;

import domain.DAO;
import domain.ObjectDBUtil;
import domain.producto.Producto;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO implements DAO<Pedido> {
    @Override
    public Pedido save(Pedido pedido) {

        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(pedido);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return pedido;
    }

    public void saveAll(List<Pedido> pedidos){

        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try{
            em.getTransaction().begin();
            for(Pedido p : pedidos){
                em.persist(p);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Pedido update(Pedido pedido) {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try{
            em.getTransaction().begin();
            Pedido p = em.find(Pedido.class,pedido.getId());
            em.merge(pedido);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return pedido;
    }

    @Override
    public boolean remove(Pedido pedido) {
        return false;
    }

    @Override
    public Pedido get(Long id) {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        Pedido p=null;
        try{
            p = em.find(Pedido.class,id);
        } finally {
            em.close();
        }
        return pedido;
    }

    @Override
    public List<Pedido> getAll() {

        List<Pedido> salida  = new ArrayList<>(0);

        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try{
            TypedQuery<Pedido> query = em.createQuery("select p from Pedido p", Pedido.class);
            salida = query.getResultList();
        } finally {
            em.close();
        }
        return salida;
    }
}
