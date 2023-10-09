package dominio;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class DataBase {

    private static Connection conexion;
    private static Logger logger;

    static {
        logger = Logger.getLogger(DataBase.class.getName());
        String url = "jdbc:mysql://localhost:3306/ad";
        String usuario = "root";
        String password = "admin";
        try {
            conexion = DriverManager.getConnection(url, usuario, password);
            logger.info("Succesfull conection dataBase");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConexion() {
        return conexion;
    }

    public static void closeConexion() {
        try {
            DataBase.getConexion().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<String[]> getAll() {
        var salida = new ArrayList<String[]>();
        try {
            Statement st = DataBase.getConexion().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tareas");
            while (rs.next()) {
                salida.add(converToRow(rs));
            }
            System.out.println(salida.size());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return salida;
    }

    private static String[] converToRow(ResultSet rs) throws SQLException {
        String[] fila = new String[]{
                String.valueOf(rs.getInt("id")),
                rs.getString("titulo"),
                rs.getString("prioridad"),
                rs.getString("categoria"),
                rs.getString("descripcion"),
                String.valueOf(rs.getInt("usuario_id"))

        };
        return fila;
    }
    public static ArrayList<Tarea>getAllTarea(){
        var salida = new ArrayList<Tarea>();
        try {
            Statement st = DataBase.getConexion().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tareas");
            while (rs.next()) {
                salida.add(new Tarea(rs));
            }
            System.out.println(salida.size());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return salida;
    }
}
