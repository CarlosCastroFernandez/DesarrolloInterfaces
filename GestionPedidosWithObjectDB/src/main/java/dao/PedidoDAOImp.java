package dao;

import clases.Pedido;
import utils.PedidoDAO;

import javax.persistence.EntityManager;

public class PedidoDAOImp implements PedidoDAO {
    /**
     * Guarda un pedido en base de datos
     * @param pedido se le pasa un pedido en memoria
     * @return y devuelve ese pedido insertado
     */
    @Override
    public Pedido save(Pedido pedido) {
        EntityManager em=ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        Pedido ped=pedido;
        try{
            em.getTransaction().begin();
            em.persist(ped);
            em.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            em.close();
        }
        return ped;

    }
    /**
     * Borra un pedido en base de datos
     * @param p se le pasa un pedido en memoria
     *
     */
    public void delete(Pedido p){
        EntityManager em=ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        Pedido pedido=null;
        try{
            em.getTransaction().begin();
           pedido= em.find(Pedido.class,p.getId());
           em.remove(pedido);
           em.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            em.close();
        }
    }
}
