package implementosdao;

import clase.Actividad;
import clase.Alumno;
import dao.ActividadDAO;
import dao.AlumnoDAO;
import dao.DAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateConection;
import utils.MYSQLConection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class AlumnoDAOImplement implements AlumnoDAO {
    private final String GETALLALUMNOS="select * from alumno";
    private final String GETALUMNOBYID="select * from alumno where id=?";
    private final String INSERTARALUMNO="insert into alumno (nombre,apellido,dni,telefono,correo) VALUES (?,?,?,?,?)";
    private final String MODIFICACION="update alumno set nombre=? where id=?";
    private final String DELETE="delete from alumno where id=?";
    public AlumnoDAOImplement(){

    }
    @Override
    public List<Alumno> getAll() {
        List<Alumno>alumnos= new ArrayList<Alumno>();
        /*try {
            PreparedStatement pst= MYSQLConection.getConexion().prepareStatement(GETALLALUMNOS);
            ResultSet rs=pst.executeQuery();
            while (rs.next()){
                Alumno alumno=new Alumno(rs.getLong("id"),rs.getString("nombre"),rs.getString("apellido"),
                        rs.getString("dni"),rs.getString("telefono"),rs.getString("correo"));
                alumno.setActividades(new ActividadDAOImplement().getActividadByAlumno(alumno.getId()));
                alumnos.add(alumno);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
        try(Session sesion= HibernateConection.getSession().openSession()){
            Query<Alumno>q=sesion.createQuery("from Alumno", Alumno.class);
            alumnos=q.getResultList();
        }
        return alumnos;
    }

    @Override
    public Alumno getAlumnoById(Long id) {
        Alumno alumno=null;
        try {
            PreparedStatement pst=MYSQLConection.getConexion().prepareStatement(GETALUMNOBYID);
            pst.setLong(1,id);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                 alumno=new Alumno(rs.getLong("id"),rs.getString("nombre"),rs.getString("apellido"),
                         rs.getString("dni"),rs.getString("telefono"),rs.getString("correo"));
            }
            return alumno;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public Alumno getAlumnoBydni(String dni) {
        Alumno alumno=null;
        try(Session sesion= HibernateConection.getSession().openSession()){
            Query<Alumno>q=sesion.createQuery("select a from Alumno a where a.dni=:dni", Alumno.class);
            q.setParameter("dni",dni);
            alumno=q.getSingleResult();
        }
        return alumno;

    }

    @Override
    public Alumno insertarAlumno(Alumno alumno) {
        Alumno alumnoNuevo=alumno;

        /*try {
            PreparedStatement pst=MYSQLConection.getConexion().prepareStatement(INSERTARALUMNO, RETURN_GENERATED_KEYS);
            pst.setString(1, alumno.getNombre());
            pst.setString(2,alumno.getApellido());
            pst.setString(3,alumno.getDni());
            pst.setString(4,alumno.getTelefono());
            pst.setString(5,alumno.getCorreo());
            Integer numeroFilas=pst.executeUpdate();
            ResultSet rs=pst.getGeneratedKeys();
            if(rs.next()){
                Long id=rs.getLong(1);
                alumnoNuevo.setId(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
        try(Session sesion= HibernateConection.getSession().openSession()) {
            Transaction t=sesion.beginTransaction();
            sesion.persist(alumnoNuevo);
            t.commit();
        }catch (Exception e){
            e.printStackTrace();
        }

        return alumnoNuevo;
    }

    @Override
    public void modificarAlumno(Long id) {
        /*Boolean error=false;
        try {
            PreparedStatement pst=MYSQLConection.getConexion().prepareStatement(MODIFICACION);
            pst.setString(1,alumno.getNombre());
            pst.setLong(2,alumno.getId());
            Integer numeroFilas=pst.executeUpdate();
            if(numeroFilas==0){
                error=true;
            }
            if(error){
                System.out.println("NO SE PUDO MODIFICAR LA BASE DE DATOS PORQUE NO EXISTE");
            }else{
                System.out.println("MODIFICACION HECHA");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
        try(Session sesion= HibernateConection.getSession().openSession()) {
            Alumno alumnoPersistente=sesion.get(Alumno.class,id);
            Transaction t=sesion.beginTransaction();
            alumnoPersistente.setNombre("PEPE");
            alumnoPersistente.setApellido("HOLA");
            t.commit();
        }

    }

    public void delete(Long id) {
        /*Boolean error=false;
        try {
            PreparedStatement pst=MYSQLConection.getConexion().prepareStatement(DELETE);
            pst.setLong(1,id);
            Integer numeroFilas=pst.executeUpdate();
            if(numeroFilas==0){
                error=true;
            }
            if(error){
                System.out.println("NO SE PUDO BORRAR LA BASE DE DATOS PORQUE NO EXISTE");
            }else{
                System.out.println("Boorado al alumno con id "+id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
        try(Session sesion= HibernateConection.getSession().openSession()) {
            Alumno alumnoPersistente=sesion.get(Alumno.class,id);
            Transaction t=sesion.beginTransaction();
            sesion.remove(alumnoPersistente);
            t.commit();
        }
    }


}


