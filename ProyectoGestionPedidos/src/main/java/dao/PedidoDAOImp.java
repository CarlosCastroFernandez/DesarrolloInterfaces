package dao;

import clases.Pedido;
import utils.PedidoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PedidoDAOImp implements PedidoDAO {
    private static Connection conexion;
    private static String downAll="select * from pedido where usuario=?";
   public PedidoDAOImp(Connection conn){
        conexion=conn;
   }
    @Override

    public ArrayList<Pedido> loadAll(Integer id) {
        ArrayList<Pedido>salida=new ArrayList<Pedido>();
        try {
            PreparedStatement pst=conexion.prepareStatement(downAll);
            pst.setInt(1,id);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                Pedido pedido=new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setCodigo(rs.getString("codigo"));
                pedido.setFecha(rs.getString("fecha"));
                pedido.setUsuarioId(rs.getInt("usuario"));
                pedido.setTotal(rs.getInt("total"));
                salida.add(pedido);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return salida;

    }
}
