package domain;

import clase.ActividadDiaria;
import clase.Profesor;
import exception.ApellidoConNumero;
import exception.DNIInvalido;
import exception.NombreConNumero;
import exception.UsuarioInexistente;
//import lombok.extern.java.Log;
import clase.Alumno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//@Log
//public class AlumnoDAOImp implements AlumnoDAO{
    //private static Connection connection;
   // private final static String queryLoad = "select * from where id = ?";
   // private final static String queryRegister = "insert into alumno(dni, email, nombre, apellido1, apellido2, telefono, " +
            //"contrasenha, profesor, empresa, fechaNacimiento, horasDual, horasFCT, curso, observaciones)" + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    //public AlumnoDAOImp(Connection c){ connection = c;}

   // @Override
    //public Alumno loadActivity(String nombre){
      //  Alumno salida = null;

       /* try {
            PreparedStatement pst=connection.prepareStatement(queryLoad);
           // pst.setString(1,id);
            ResultSet rs=pst.executeQuery();*/

           /* if(rs.next()){
               salida=new Alumno(rs.getString("dni"), rs.getString("email"), rs.getNString("nombre"),
                        rs.getString("apellido1"),rs.getString("apellido2"), rs.getInt("telefono"),
                        rs.getString("contrasenha"),rs.getString("profesor"),rs.getString("empresa"),
                        rs.getString("fechaNacimiento"),rs.getInt("horasDual"), rs.getInt("horasFCT"),
                        rs.getString("curso"),rs.getString("observaciones"));
            }else{
                log.warning("Usuario no existe");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NombreConNumero e) {
            throw new RuntimeException(e);
        } catch (DNIInvalido e) {
            throw new RuntimeException(e);
        } catch (ApellidoConNumero e) {
            throw new RuntimeException(e);
        }*/

   // }

   /* @Override
    public ArrayList<ActividadDiaria> loadAll() {
        return null;
    }

    @Override
    public Alumno saveActivity(Alumno a) {
        return null;
    }

    @Override
    public Alumno updateActivity(Alumno a) {
        return null;
    }

    @Override
    public void removeActivity(Alumno a) {

    }*/
//}
