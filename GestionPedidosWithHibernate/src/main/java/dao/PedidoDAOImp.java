package dao;

import clases.Pedido;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.PedidoDAO;

public class PedidoDAOImp implements PedidoDAO {
    /**
     * Guarda un pedido en base de datos
     * @param pedido se le pasa un pedido en memoria
     * @return y devuelve ese pedido insertado
     */
    @Override
    public Pedido save(Pedido pedido) {
        Pedido ped=pedido;
        try(Session s=HibernateUtils.getSession().openSession()){
            Transaction t=s.beginTransaction();
            s.persist(ped);
            t.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        return ped;

    }
    /**
     * Borra un pedido en base de datos
     * @param p se le pasa un pedido en memoria
     *
     */
    public void delete(Pedido p){
        Pedido pedido=null;
        try(Session s=HibernateUtils.getSession().openSession()){
            Transaction t=s.beginTransaction();
           pedido= s.get(Pedido.class,p.getId());
           s.remove(pedido);
           t.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
