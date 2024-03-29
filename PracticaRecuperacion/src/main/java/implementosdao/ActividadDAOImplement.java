package implementosdao;

import clase.Actividad;
import clase.Alumno;
import dao.ActividadDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateConection;
import utils.MYSQLConection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActividadDAOImplement implements ActividadDAO {
    private final String GETALLACTIVIDADBYALUMNO="select * from actividad where alumno_id=?";
    public ActividadDAOImplement(){

    }
    @Override
    public List<Actividad> getAll() {
        return null;
    }

    @Override
    public List<Actividad> getActividadByAlumno(Long id) {
        List<Actividad>actividades=new ArrayList<>();
        try {
            PreparedStatement pst= MYSQLConection.getConexion().prepareStatement(GETALLACTIVIDADBYALUMNO);
            pst.setLong(1,id);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                Actividad actividad=new Actividad(rs.getLong("id"),rs.getString("nombre"),rs.getDate("fecha"),
                        rs.getString("observaciones"),rs.getLong("alumno_id"));
                actividad.setAlumno(new AlumnoDAOImplement().getAlumnoById(actividad.getAlumnoId()));
                actividades.add(actividad);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return actividades;
    }

    @Override
    public Actividad getActividadById(Long id) {
        return null;
    }
    public void delete(Long id) {
        try(Session sesion= HibernateConection.getSession().openSession()) {
            Actividad actividadPersistente=sesion.get(Actividad.class,id);
            Transaction t=sesion.beginTransaction();
            sesion.remove(actividadPersistente);
            t.commit();
        }
    }
}

