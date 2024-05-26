package implement;

import Util.HibernateUtil;
import Util.Utilidad;
import clase.AlumnoCurso;
import clase.Curso;
import clase.Modulo;
import clase.PersonalBolsa;
import dao.DAOPersonalBolsa;
import error.DNIIncorrecto;
import javafx.scene.control.Alert;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class PersonalBolsaDAOImplement implements DAOPersonalBolsa {
    @Override
    public List<PersonalBolsa> getAll() {
        List<PersonalBolsa>alumnos=new ArrayList<>();
        try(Session s=HibernateUtil.getSession().openSession()){
            Query<PersonalBolsa>q=s.createQuery("FROM PersonalBolsa", PersonalBolsa.class);
            alumnos=q.getResultList();

        }
        return alumnos;
    }

    @Override
    public void modNotas(PersonalBolsa alumno) {
        try(Session s=HibernateUtil.getSession().openSession()){
            Transaction t=s.beginTransaction();
            PersonalBolsa alumnoElegido=s.get(PersonalBolsa.class,alumno.getId());
            alumnoElegido=alumno;
            s.merge(alumnoElegido);
            t.commit();
        }
    }

    @Override
    public List<PersonalBolsa> getAllByCursoId(Curso curso) {
       List<PersonalBolsa>listaAlumnos=new ArrayList<>();

        try(Session s= HibernateUtil.getSession().openSession()){
            Query<PersonalBolsa>q=s.createQuery("select distinct a.alumnoId from AlumnoCurso a where a.cursoId.id=:id",PersonalBolsa.class);
            q.setParameter("id",curso.getId());
            listaAlumnos=q.getResultList();

        }
        return listaAlumnos;
    }

    @Override
    public List<PersonalBolsa> getAllByCursoIdNuevos(Curso curso) {
        List<PersonalBolsa>listaAlumnos=new ArrayList<>();

        try(Session s= HibernateUtil.getSession().openSession()){
            Query<PersonalBolsa>q=s.createQuery("select distinct a.alumnoId from AlumnoCurso a where a.cursoId.id=:id and a.notaCurso is null",PersonalBolsa.class);
            q.setParameter("id",curso.getId());
            listaAlumnos=q.getResultList();
            System.out.println(listaAlumnos);

        }
        return listaAlumnos;
    }

    @Override
    public void agregarAlumnoCurso(PersonalBolsa alumno) {
        try(Session s= HibernateUtil.getSession().openSession()){
            Transaction t=s.beginTransaction();
            PersonalBolsa alumnoBBDD=s.get(PersonalBolsa.class,alumno.getId());
            alumnoBBDD=alumno;
            s.merge(alumnoBBDD);
            t.commit();

        }
    }

    @Override
    public PersonalBolsa modPersonalBolsa(PersonalBolsa personal) {
        PersonalBolsa personalBBDD=null;
        try(Session s= HibernateUtil.getSession().openSession()){
            Transaction t=s.beginTransaction();
            personalBBDD=s.get(PersonalBolsa.class,personal.getId());
            personalBBDD.setNombre(personal.getNombre());
            personalBBDD.setApellido1(personal.getApellido1());
            personalBBDD.setApellido2(personal.getApellido2());
            personalBBDD.setCorreo(personal.getCorreo());
            personalBBDD.setTelefono(personal.getTelefono());
            personalBBDD.setLicenciaArma(personal.getLicenciaArma());
            personalBBDD.setFechaNacimiento(personal.getFechaNacimiento());
            personalBBDD.setTallaCamiseta(personal.getTallaCamiseta());
            personalBBDD.setNumeroCuenta(personal.getNumeroCuenta());
            personalBBDD.setNumeroSocial(personal.getNumeroSocial());
            personalBBDD.setTitulacion(personal.getTitulacion());
            personalBBDD.setLugarResidencia(personal.getLugarResidencia());
            personalBBDD.setImagenPerfil(personal.getImagenPerfil());
            personalBBDD.setCurriculumUrl(personal.getCurriculumUrl());
            personalBBDD.setDni(personal.getDni());
            personalBBDD.setNumeroTip(personal.getNumeroTip());
            personalBBDD.setAltura(personal.getAltura());
            personalBBDD.setSexo(personal.getSexo());
            personalBBDD.setIdioma(personal.getIdioma());
            personalBBDD.setEsAlumno(personal.getEsAlumno());
            t.commit();
        } catch (DNIIncorrecto e) {
            throw new RuntimeException(e);
        }catch(ConstraintViolationException a){
            Utilidad.setComprDNI(true);
            Alert alerta=new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText("DNI Duplicado");
            alerta.setContentText("Por favor asegurese de que el dni no sea duplicado con " +
                    "alguno de las personas que se enceuntran en la app");
            alerta.showAndWait();
        }
        return personalBBDD;
    }

    @Override
    public void borradoAlumno(PersonalBolsa cliente) {
        try(Session s= HibernateUtil.getSession().openSession()){
            Transaction t=s.beginTransaction();
            PersonalBolsa clienteBBDD=s.get(PersonalBolsa.class,cliente.getId());
            s.remove(clienteBBDD);
            t.commit();
        }
    }

    @Override
    public List<PersonalBolsa> getAllByCursoIdTerminados(Curso curso) {
        List<PersonalBolsa>listaAlumnos=new ArrayList<>();

        try(Session s= HibernateUtil.getSession().openSession()){
            Query<PersonalBolsa>q=s.createQuery("select distinct a.alumnoId from AlumnoCurso a where a.cursoId.id=:id and a.notaCurso is not null",PersonalBolsa.class);
            q.setParameter("id",curso.getId());
            listaAlumnos=q.getResultList();
            System.out.println(listaAlumnos);

        }
        return listaAlumnos;

    }

    @Override
    public List<PersonalBolsa> getAllNuevos() {
        List<PersonalBolsa>listaAlumnos=new ArrayList<>();

        try(Session s= HibernateUtil.getSession().openSession()){
            Query<PersonalBolsa>q=s.createQuery("select distinct a.alumnoId from AlumnoCurso a where a.notaCurso is null",PersonalBolsa.class);
            listaAlumnos=q.getResultList();

        }
        return listaAlumnos;
    }

    @Override
    public List<PersonalBolsa> getAllTerminados() {
        List<PersonalBolsa>listaAlumnos=new ArrayList<>();

        try(Session s= HibernateUtil.getSession().openSession()){
            Query<PersonalBolsa>q=s.createQuery("select distinct a.alumnoId from AlumnoCurso a where a.notaCurso is not null",PersonalBolsa.class);
            listaAlumnos=q.getResultList();

        }
        return listaAlumnos;
    }

    @Override
    public List<PersonalBolsa> getAllBolsa() {
        List<PersonalBolsa>listaAlumnos=new ArrayList<>();

        try(Session s= HibernateUtil.getSession().openSession()){
            Query<PersonalBolsa>q=s.createQuery("select a from PersonalBolsa a  where a.notaFinal is not null",PersonalBolsa.class);
            listaAlumnos=q.getResultList();
        }
        return listaAlumnos;
    }

    @Override
    public void modPosicion(PersonalBolsa alumno) {
        try(Session s= HibernateUtil.getSession().openSession()){
            Transaction t=s.beginTransaction();
            PersonalBolsa alumnoBBDD=s.get(PersonalBolsa.class,alumno.getId());
            alumnoBBDD.setPosicion(alumno.getPosicion());
            t.commit();
        }

    }


    public void subir(PersonalBolsa objeto) {
        try(Session s= HibernateUtil.getSession().openSession()){
            Transaction t=s.beginTransaction();
            s.persist(objeto);

            t.commit();
        }catch (ConstraintViolationException e){
            Utilidad.setComprDNI(true);
           Alert alerta=new Alert(Alert.AlertType.ERROR);
           alerta.setTitle("ERROR");
           alerta.setHeaderText("DNI Duplicado");
           alerta.setContentText("Por favor asegurese de que el dni no sea duplicado con " +
                   "alguno de las personas que se enceuntran en la app");
           alerta.showAndWait();
        }
    }

}
