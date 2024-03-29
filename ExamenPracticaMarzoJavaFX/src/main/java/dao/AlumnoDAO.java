package dao;

import clase.Alumno;
import conexion.MYSQLConecction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AlumnoDAO {
    private static final String INSERTAR_ALUMNO="insert into alumno (nombre,apellido,ad,sge,pmdm,psp,eie,di,hlc) VALUES (?,?,?,?,?,?,?,?,?)";

    public static Alumno insertarAlumno(Alumno alumno){
        Alumno alumnoConId=alumno;
        try {
            PreparedStatement pst= MYSQLConecction.getConexion().prepareStatement(INSERTAR_ALUMNO, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1,alumno.getNombre());
            pst.setString(2,alumno.getApellido());
            pst.setByte(3, alumno.getAD());
            pst.setByte(4, alumno.getSGE());
            pst.setByte(5, alumno.getPMDM());
            pst.setByte(6, alumno.getPSP());
            pst.setByte(7, alumno.getEIE());
            pst.setByte(8, alumno.getDI());
            pst.setByte(9, alumno.getHLC());
            Integer numeroFilasAfectadas=pst.executeUpdate();
            if(numeroFilasAfectadas==1){
                ResultSet rs=pst.getGeneratedKeys();
                if(rs.next()){
                    int idGenerado=rs.getInt(1);
                    alumnoConId.setId(idGenerado);
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return alumnoConId;
    }
    public static ArrayList<Alumno> getAllAlumnos(){
        ArrayList<Alumno>alumnos=new ArrayList<>();
        try {
            PreparedStatement pst=MYSQLConecction.getConexion().prepareStatement("select * from alumno");
            ResultSet rs=pst.executeQuery();
            while (rs.next()){
                Alumno alumnoRecogido=new Alumno(rs.getInt("id"),rs.getString("nombre"),rs.getString("apellido"),
                        rs.getByte("ad"),rs.getByte("sge"),rs.getByte("pmdm"),rs.getByte("psp"),
                        rs.getByte("eie"),rs.getByte("di"),rs.getByte("hlc"));
                alumnos.add(alumnoRecogido);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return alumnos;
    }
}
