package dao;

import clases.Producto;

import utils.ProductoDAO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImp implements ProductoDAO {
    /**
     * Se trae tdda una lista de todos los productos
     * @return devuelve lista de productos
     */
    @Override
    public ArrayList<Producto> getAllProducts() {
        EntityManager em=ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        ArrayList<Producto>productos=new ArrayList<>();
        try{
            TypedQuery<Producto> q=  em.createQuery("select pr from Producto pr",Producto.class);
            productos= (ArrayList<Producto>) q.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            em.close();
        }
        return productos;
    }
    public ArrayList<Producto> getAllProductsJDBC(){
        Connection conexion=MYSQLConnection.getConexion();
        ArrayList<Producto>productos=new ArrayList<>();
        try {
            Statement ps=conexion.createStatement();
            ResultSet rs=ps.executeQuery("SELECT * FROM producto");
            while(rs.next()){
                Producto p =new Producto(rs.getLong("id"),rs.getString("nombre"),rs.getString("precio"),rs.getLong("cantidad"));
                productos.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productos;
    }
    public void saveAll(List<Producto> productos){

        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try{
            em.getTransaction().begin();
            for(Producto p : productos){
                em.persist(p);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
