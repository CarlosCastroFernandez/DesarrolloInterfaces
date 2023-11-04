package dao;

import clases.Pedido;
import utils.PedidoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Implementación de la interfaz PedidoDAO que gestiona la obtención de pedidos desde una base de datos.
 */
public class PedidoDAOImp implements PedidoDAO {
    private static Connection conexion; // Objeto de conexión a la base de datos
    private static String downAll = "SELECT * FROM pedido WHERE usuarioId=?"; // Consulta SQL para cargar todos los pedidos de un usuario

    /**
     * Constructor de la clase PedidoDAOImp.
     *
     * @param conn Objeto de conexión a la base de datos.
     */
    public PedidoDAOImp(Connection conn) {
        conexion = conn;
    }

    /**
     * Obtiene una lista de todos los pedidos asociados a un usuario específico.
     *
     * @param id El identificador del usuario del que se deben cargar los pedidos.
     * @return Una lista de pedidos relacionados con el usuario especificado.
     */
    @Override
    public ArrayList<Pedido> loadAll(Integer id) {
        ArrayList<Pedido> salida = new ArrayList<Pedido>();
        try {
            PreparedStatement pst = conexion.prepareStatement(downAll);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setCodigo(rs.getString("codigo"));
                pedido.setFecha(rs.getString("fecha"));
                pedido.setUsuarioId(rs.getInt("usuarioId"));
                pedido.setTotal(rs.getInt("total"));
                pedido.setUsuario(new UsuarioDAOImp(DBConecction.conexion()).load(pedido.getUsuarioId()));
                pedido.getItems().addAll(new ItemPedidoDAOImp(DBConecction.conexion()).loadItem(pedido));
                salida.add(pedido);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return salida;
    }
}