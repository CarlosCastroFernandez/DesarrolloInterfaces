package dao;

import clases.Itempedido;
import clases.Pedido;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.ItemPedidoDAO;

import java.util.ArrayList;

/**
 * Implementación de la interfaz ItemPedidoDAO que gestiona la obtención de elementos de pedido desde una base de datos.
 */
public class ItemPedidoDAOImp implements ItemPedidoDAO {
    private static SessionFactory conexion; // Objeto de conexión a la base de datos
    private static final String queryLoadItem = "SELECT * FROM itempedido where codigoPedido=?"; // Consulta SQL para cargar elementos de pedido

    /**
     * Constructor de la clase ItemPedidoDAOImp.
     *
     * @param conn Objeto de conexión a la base de datos.
     */


    /**
     * Obtiene una lista de elementos de pedido asociados a un pedido específico.
     *
   //  * @param codigo El pedido del que se deben cargar los elementos.
     * @return Una lista de elementos de pedido relacionados con el pedido especificado.
     */


    public Itempedido save(Itempedido item){
        Itempedido myItem=item;
        try(Session s=HibernateUtils.getSession().openSession()){
            Transaction t=s.beginTransaction();
            s.persist(myItem);
            Pedido p=s.get(Pedido.class,item.getPedido().getId());
            p.setTotal(item.getPedido().getTotal());
            t.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        return myItem;
    }
    public void update(Itempedido  item){
        Itempedido itemPedido=null;
        try(Session s=HibernateUtils.getSession().openSession()){
            Transaction t=s.beginTransaction();
            itemPedido=s.get(Itempedido.class,item.getId());
            itemPedido.getPedido().setTotal(item.getPedido().getTotal());
            itemPedido.setCantidad(item.getCantidad());
            itemPedido.setProducto(item.getProducto());
            t.commit();
        }catch(Exception e){
            e.printStackTrace();
        }


    }
    public void delete(Itempedido  item){
        Itempedido itemPedido=null;
        try(Session s=HibernateUtils.getSession().openSession()){
            Transaction t=s.beginTransaction();
            itemPedido=s.get(Itempedido.class,item.getId());
            String[]precio=itemPedido.getProducto().getPrecio().split(" ");
            itemPedido.getPedido().setTotal(itemPedido.getPedido().getTotal()-(itemPedido.getCantidad()* Integer.valueOf(precio[0])));
            itemPedido.setPedido(null);
            s.remove(itemPedido);
            t.commit();
        }catch(Exception e){
            e.printStackTrace();
        }


    }
}
