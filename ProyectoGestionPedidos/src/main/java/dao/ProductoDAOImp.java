package dao;

import clases.Producto;
import utils.ProductoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementación de la interfaz ProductoDAO que gestiona la obtención de información de productos desde una base de datos.
 */
public class ProductoDAOImp implements ProductoDAO {
    private static Connection conexion; // Objeto de conexión a la base de datos
    private static final String loadProduct = "SELECT * FROM producto where id=?"; // Consulta SQL para cargar un producto por su ID

    /**
     * Constructor de la clase ProductoDAOImp.
     *
     * @param conn Objeto de conexión a la base de datos.
     */
    public ProductoDAOImp(Connection conn) {
        conexion = conn;
    }

    /**
     * Obtiene la información de un producto específico por su ID.
     *
     * @param id El identificador del producto que se desea cargar.
     * @return El objeto Producto con la información del producto especificado.
     */
    @Override
    public Producto loadProducto(Integer id) {
        Producto producto = new Producto();
        try {
            PreparedStatement pst = conexion.prepareStatement(loadProduct);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setCantidad(rs.getInt("cantidad"));
                producto.setPrecio(rs.getString("precio"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return producto;
    }
}
