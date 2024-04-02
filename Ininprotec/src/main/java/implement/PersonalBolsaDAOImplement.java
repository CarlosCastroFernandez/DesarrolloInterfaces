package implement;

import Util.HibernateUtil;
import clase.Curso;
import clase.Modulo;
import clase.PersonalBolsa;
import dao.DAOPersonalBolsa;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
            for (Modulo moduloElegido : alumnoElegido.getModulos()) {
                for (Modulo modulo : alumno.getModulos()) {
                    if (moduloElegido.getId().equals(modulo.getId())) {
                        moduloElegido.setNotaModulo(modulo.getNotaModulo());
                        break; // Salir del bucle una vez que se ha encontrado el módulo
                    }
                }
            }
            s.merge(alumnoElegido);
            t.commit();
        }
    }

    @Override
    public List<PersonalBolsa> getAllByCursoId(Curso curso) {
        List<PersonalBolsa>listaAlumnos=new ArrayList<>();
        try(Session s= HibernateUtil.getSession().openSession()){
            Curso cursoElegido=s.get(Curso.class,curso.getId());
            listaAlumnos.addAll(cursoElegido.getAlumnos());
        }
        return listaAlumnos;
    }

    @Override
    public void agregarAlumnoCurso(PersonalBolsa alumno) {
        try(Session s= HibernateUtil.getSession().openSession()){
            Transaction t=s.beginTransaction();
            PersonalBolsa alumnoBDD=s.get(PersonalBolsa.class,alumno.getId());
            alumnoBDD.setCursosAlumnos(alumno.getCursosAlumnos());
            alumnoBDD.setModulos(alumno.getModulos());
            for(Curso curso:alumnoBDD.getCursosAlumnos()){
                curso.getAlumnos().add(alumno);
                s.merge(curso);
            }
            for(Modulo modulo:alumnoBDD.getModulos()){
                modulo.getAlumnos().add(alumno);
                s.merge(modulo);
            }
            alumnoBDD.setEsAlumno(alumno.getEsAlumno());
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
            t.commit();
        }
        return personalBBDD;
    }


    public void subir(PersonalBolsa objeto) {
        try(Session s= HibernateUtil.getSession().openSession()){
            Transaction t=s.beginTransaction();
            s.persist(objeto);
            if(objeto.getCursosAlumnos()!=null){
                for(Curso curso:objeto.getCursosAlumnos()){
                    curso.getAlumnos().add(objeto);
                    s.merge(curso);
                }
            }
            if(objeto.getModulos()!=null){
                for(Modulo modulo:objeto.getModulos()){
                    modulo.getAlumnos().add(objeto);
                    s.merge(modulo);
                }
            }

            t.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
