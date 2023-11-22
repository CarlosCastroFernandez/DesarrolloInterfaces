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
     * Guarda en base de datos un itemPedido y modifica el total del pedido aactual
     * @param item parametro en el que se le pasa un itemPedido de memoria
     * @return devuelve ese ItemPedido insertado
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
    /**
     * Guarda en base de datos un itemPedido y modifica el total del pedido aactual
     * @param item parametro en el que se le pasa un itemPedido de memoria
     * @return devuelve ese ItemPedido modificado
     */

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
    /**
     * Borra en base de datos un itemPedido y modifica el total del pedido aactual
     * @param item parametro en el que se le pasa un itemPedido de memoria
     *
     */
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
