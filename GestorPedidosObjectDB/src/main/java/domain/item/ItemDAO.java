package domain.item;

import domain.DAO;
import domain.ObjectDBUtil;
import domain.pedido.Pedido;

import javax.persistence.EntityManager;
import java.util.List;

public class ItemDAO implements DAO<Item> {
    @Override
    public Item save(Item item) {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(item);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return item;
    }

    @Override
    public Item update(Item item) {
        return null;
    }

    @Override
    public boolean remove(Item item) {
        Boolean salida= false;
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            Item i = em.find(Item.class,item.getId());
            salida = (i!=null);
            if(salida) {
                em.remove(i);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return salida;
    }


    @Override
    public Item get(Long id) {
        return null;
    }

    @Override
    public List<Item> getAll() {
        return null;
    }
}
