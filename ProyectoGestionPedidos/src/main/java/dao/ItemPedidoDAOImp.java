package dao;

import clases.Itempedido;
import clases.Pedido;
import utils.ItemPedidoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemPedidoDAOImp implements ItemPedidoDAO {
    private static Connection conexion;
    private static final String queryLoadItem="SELECT * FROM itempedido where codigoPedido=?";
    public ItemPedidoDAOImp(Connection conn){
        conexion=conn;
    }
    @Override
    public ArrayList<Itempedido> loadItem(Pedido codigo) {
        ArrayList<Itempedido>  itemSalida=new ArrayList<>();
        try {
            PreparedStatement pst=conexion.prepareStatement(queryLoadItem);
            pst.setString(1,codigo.getCodigo());
            ResultSet rs=pst.executeQuery();
            while(rs.next()) {
                Itempedido item = new Itempedido(rs.getInt("id"),rs.getString("codigoPedido"),rs.getInt("cantidad"),rs.getInt("productoID"));
                item.getProducto().add(new ProductoDAOImp(DBConecction.conexion()).loadProducto(item.getProductoId()));
                itemSalida.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemSalida;
    }
}
