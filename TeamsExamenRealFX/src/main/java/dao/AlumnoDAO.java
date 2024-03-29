package dao;

import clases.Alumno;
import clases.Modulo;
import utils.MYSQLConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class AlumnoDAO {
    private static final String INSERTAR="insert into alumno (nombre,apellido1,apellido2,correo,dni,fecha,localidad,telefono,modulo_id) values (?,?,?,?,?,?,?,?,?)";

  private static final String GETALL="select * from alumno";
    public static Alumno insertarModulo(Alumno alumno){
        Alumno alumnito=alumno;

        try {
            PreparedStatement ps= MYSQLConnection.getConexion().prepareStatement(INSERTAR, RETURN_GENERATED_KEYS);
            ps.setString(1,alumno.getNombre());
            ps.setString(2,alumno.getApellido1());
            ps.setString(3,alumno.getApellido2());
            ps.setString(4,alumno.getCorreo());
            ps.setString(5,alumno.getDni());
            ps.setDate(6,alumno.getFecha());
            ps.setString(7,alumno.getLocalidad());
            ps.setString(8,alumno.getTelefono());
            ps.setInt(9,Integer.valueOf((int) alumno.getModuloId()));
            Integer numeroFilaInsertada=ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if (rs.next()){
                Long id=(long)rs.getInt(1);
                alumnito.setId(id);
            }
            return alumnito;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static ArrayList<Alumno> getAll(){
     ArrayList<Alumno>listaAlumno=new ArrayList<>();
        try {
            PreparedStatement pst=MYSQLConnection.getConexion().prepareStatement(GETALL);
            ResultSet rs=pst.executeQuery();
            while (rs.next()){
               Alumno alumno=new Alumno();
                alumno.setId(rs.getLong("id"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellido1(rs.getString("apellido1"));
                alumno.setApellido2(rs.getString("apellido2"));
                alumno.setCorreo(rs.getString("correo"));
                alumno.setDni(rs.getString("dni"));
                alumno.setFecha(rs.getDate("fecha"));
                alumno.setLocalidad(rs.getString("localidad"));
                alumno.setTelefono(rs.getString("telefono"));
                alumno.setModuloId(rs.getLong("modulo_id"));
                alumno.setModulo(ModuloDAO.getModuloBYId(alumno.getModuloId()));
                System.out.println(alumno.getModulo());
                listaAlumno.add(alumno);

            }


            return listaAlumno;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
