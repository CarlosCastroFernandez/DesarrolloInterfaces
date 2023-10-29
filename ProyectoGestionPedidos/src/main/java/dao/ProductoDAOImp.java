package dao;

import clases.Producto;
import utils.ProductoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoDAOImp implements ProductoDAO {
    private static Connection conexion;
    private static final String loadProduct="SELECT * FROM producto where id=?";
    public ProductoDAOImp(Connection conn){
        conexion=conn;
    }
    @Override
    public Producto loadProducto(Integer id) {
        Producto producto=new Producto();
        try {
            PreparedStatement pst=conexion.prepareStatement(loadProduct);
            pst.setInt(1,id);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
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
