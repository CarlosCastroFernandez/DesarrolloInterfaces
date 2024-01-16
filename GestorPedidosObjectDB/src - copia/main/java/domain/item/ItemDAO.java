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
        try{
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
        return false;
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
