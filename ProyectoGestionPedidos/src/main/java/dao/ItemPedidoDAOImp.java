package dao;

import clases.Itempedido;
import clases.Pedido;
import utils.ItemPedidoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Implementación de la interfaz ItemPedidoDAO que gestiona la obtención de elementos de pedido desde una base de datos.
 */
public class ItemPedidoDAOImp implements ItemPedidoDAO {
    private static Connection conexion; // Objeto de conexión a la base de datos
    private static final String queryLoadItem = "SELECT * FROM itempedido where codigoPedido=?"; // Consulta SQL para cargar elementos de pedido

    /**
     * Constructor de la clase ItemPedidoDAOImp.
     *
     * @param conn Objeto de conexión a la base de datos.
     */
    public ItemPedidoDAOImp(Connection conn) {
        conexion = conn;
    }

    /**
     * Obtiene una lista de elementos de pedido asociados a un pedido específico.
     *
     * @param codigo El pedido del que se deben cargar los elementos.
     * @return Una lista de elementos de pedido relacionados con el pedido especificado.
     */
    @Override
    public ArrayList<Itempedido> loadItem(Pedido codigo) {
        ArrayList<Itempedido> itemSalida = new ArrayList<>();
        try {
            PreparedStatement pst = conexion.prepareStatement(queryLoadItem);
            pst.setString(1, codigo.getCodigo());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Itempedido item = new Itempedido(rs.getInt("id"), rs.getString("codigoPedido"), rs.getInt("cantidad"), rs.getInt("productoID"));
                item.setProducto(new ProductoDAOImp(DBConecction.conexion()).loadProducto(item.getProductoId()));
                itemSalida.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemSalida;
    }
}
