package domain.pedido;

import domain.DAO;
import domain.ObjectDBUtil;
import lombok.extern.java.Log;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Log
public class PedidoDAO implements DAO<Pedido> {
    @Override
    public Pedido save(Pedido pedido) {

        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(pedido);
            em.flush();
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
        try {
            em.getTransaction().begin();
            em.merge(pedido);
            em.getTransaction().commit();
            log.info(pedido.toString());
        }catch (Exception ex){
            log.severe("Error");
            System.out.println(ex.getMessage());
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
        return p;
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

    public Pedido getByCodigo(String codigo) {
        Pedido salida  = null;

        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try{
            TypedQuery<Pedido> query = em.createQuery("select p from Pedido p where p.codigo = :codigo", Pedido.class);
            query.setParameter("codigo",codigo);
            var resultado = query.getResultList();
            log.info(resultado.toString());
            if(resultado.size()>0){
                salida=resultado.get(0);
            }
        } finally {
            em.close();
        }
        System.out.println(salida);
        return salida;
    }
}
