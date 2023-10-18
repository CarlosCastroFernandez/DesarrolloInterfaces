package domain;

import clase.Profesor;
import exception.*;
import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Log
public class ProfesorDAOImp implements ProfesorDAO{

    private static Connection conexion;
    private static String saveTeacher="select * from profesor where dni=?";
    private static String registerTeacher="insert into profesor(dni,nombre,email,apellido1,apellido2,contraseña,telefono)\n" +
            "VALUE (?,?,?,?,?,?,?)";
    public ProfesorDAOImp(Connection conn){
        conexion=conn;
    }

    @Override
    public Profesor loadTeacher(String dni,String contrasenha) throws UsuarioInexistente {

        Profesor profesor=null;
        try {
            PreparedStatement pst=conexion.prepareStatement(saveTeacher);
            pst.setString(1,dni);
            ResultSet rs=pst.executeQuery();

            if(rs.next()){
                profesor=new Profesor(dni, contrasenha);
                profesor.setDni(dni);
                profesor.setNombre(rs.getString("nombre"));
                profesor.setPassword(rs.getString("contraseña"));
                if(!contrasenha.equals(profesor.getPassword())){
                    throw new ContrasenhaIncorrecta("Contraseña Incorrecta");
                }
                profesor.setApellido1(rs.getString("apellido1"));
                profesor.setApellido2(rs.getString("apellido2"));
                profesor.setCorreo(rs.getString("email"));
                profesor.setTelefono(rs.getInt("telefono"));
            }else{
                log.warning("Usuario no existe");
                throw new UsuarioInexistente("Usuario no existe");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ContrasenhaIncorrecta e) {
            throw new RuntimeException(e);
        } catch (DNIInvalido e) {
            throw new RuntimeException(e);
        } catch (NombreConNumero e) {
            throw new RuntimeException(e);
        } catch (ApellidoConNumero e) {
            throw new RuntimeException(e);
        }
        return profesor;
    }

    @Override
    public void injection(Profesor profesor) {
        try {
            PreparedStatement pst=conexion.prepareStatement(registerTeacher);
            pst.setString(1,profesor.getDni());
            pst.setString(2,profesor.getCorreo());
            pst.setString(3,profesor.getNombre());
            pst.setString(4,profesor.getApellido1());
            pst.setString(5,profesor.getApellido2());
            pst.setString(6, profesor.getPassword());
            pst.setInt(7,profesor.getTelefono());
            Integer fila=pst.executeUpdate();
            pst.close();
            conexion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
