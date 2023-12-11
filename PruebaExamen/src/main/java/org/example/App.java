package org.example;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 * Hello world!
 *
 */
public class App

{
   private static Connection conexion;
    public static void main( String[] args )
    {
        try {
            conexion= DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebaFrancisco","root","admin");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        LocalDate fechaHoy= LocalDate.now();
        System.out.println(fechaHoy);
        java.sql.Date fecha= Date.valueOf(fechaHoy);
        System.out.println(fecha);
        String nombre="Carlos";

        String sentencia="insert into fecha (nombre,fecha) VALUES ('"+nombre+"'"+","+"'"+fecha+"')";
        try {
            Statement st=conexion.createStatement();
            int filas=st.executeUpdate(sentencia,Statement.RETURN_GENERATED_KEYS);
            System.out.println(filas);
            if(filas==1){
                ResultSet rs=st.getGeneratedKeys();
                rs.next();
                    int idGenerada=rs.getInt(1);
                    System.out.println(idGenerada);


            }

            st.close();
            conexion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            conexion= DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebaFrancisco","root","admin");
            String leer="select fecha from fecha where id=1";
            Statement st=conexion.createStatement();
            ResultSet rs=st.executeQuery(leer);
            if(rs.next()){
                Date fechaRecibida=rs.getDate("fecha");
                SimpleDateFormat simple=new SimpleDateFormat("dd-MM-yyyy");
                String formato=simple.format(fechaRecibida);
                System.out.println(formato);
                System.out.println(fechaRecibida);
            }
            st.close();
            conexion.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
