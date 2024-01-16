package dao;

import clases.Itempedido;
import clases.Pedido;
import utils.ItemPedidoDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Implementación de la interfaz ItemPedidoDAO que gestiona la obtención de elementos de pedido desde una base de datos.
 */
public class ItemPedidoDAOImp implements ItemPedidoDAO {
    private static EntityManager conexion; // Objeto de conexión a la base de datos
    private static final String queryLoadItem = "SELECT * FROM itempedido where codigoPedido=?"; // Consulta SQL para cargar elementos de pedido

    /**
     * Guarda en base de datos un itemPedido y modifica el total del pedido aactual
     * @param item parametro en el que se le pasa un itemPedido de memoria
     * @return devuelve ese ItemPedido insertado
     */


    public Itempedido save(Itempedido item){
        conexion=ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        Itempedido myItem=item;
        try{
            conexion.getTransaction().begin();
            conexion.persist(myItem);
            Pedido p=conexion.find(Pedido.class,item.getPedido().getId());
            p.setTotal(item.getPedido().getTotal());
            conexion.merge(p);
            conexion.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            conexion.close();
        }
        return myItem;
    }
    /**
     * Guarda en base de datos un itemPedido y modifica el total del pedido aactual
     * @param item parametro en el que se le pasa un itemPedido de memoria
     * @return devuelve ese ItemPedido modificado
     */

    public void update(Itempedido  item){
        conexion=ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        Itempedido itemPedido=null;
        try{
            conexion.getTransaction().begin();
            itemPedido=conexion.find(Itempedido.class,item.getId());
            itemPedido.getPedido().setTotal(item.getPedido().getTotal());
            itemPedido.setCantidad(item.getCantidad());
            itemPedido.setProducto(item.getProducto());
            conexion.merge(itemPedido);
            conexion.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            conexion.close();
        }


    }
    /**
     * Borra en base de datos un itemPedido y modifica el total del pedido aactual
     * @param item parametro en el que se le pasa un itemPedido de memoria
     *
     */
    public void delete(Itempedido  item){
        conexion=ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        Itempedido itemPedido=null;
        try{
            conexion.getTransaction().begin();
            itemPedido=conexion.find(Itempedido.class,item.getId());
            String[]precio=itemPedido.getProducto().getPrecio().split(" ");
            itemPedido.getPedido().setTotal(itemPedido.getPedido().getTotal()-(itemPedido.getCantidad()* Integer.valueOf(precio[0])));
            itemPedido.setPedido(null);
            conexion.remove(itemPedido);
            conexion.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            conexion.close();
        }


    }
}
