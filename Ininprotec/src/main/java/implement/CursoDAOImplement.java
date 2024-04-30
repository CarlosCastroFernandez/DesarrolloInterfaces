package implement;

import Util.HibernateUtil;
import clase.*;
import dao.DAOCurso;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CursoDAOImplement implements DAOCurso {
    public CursoDAOImplement(){

    }

    public void subir(Curso objeto) {
        try(Session s= HibernateUtil.getSession().openSession()){
            Transaction t=s.beginTransaction();
          s.persist(objeto);
            t.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void borrarCurso(Curso objeto) {
        try(Session s= HibernateUtil.getSession().openSession()){
            Transaction t=s.beginTransaction();
            Curso cursoBBD=s.get(Curso.class,objeto.getId());
            s.remove(cursoBBD);
            t.commit();
        }
    }

    @Override
    public List<Curso> getAll() {
        List<Curso>cursos=new ArrayList<>();
        try(Session s=HibernateUtil.getSession().openSession()){
            Query q=s.createQuery("FROM Curso ",Curso.class);
            cursos=q.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return cursos;
    }

    @Override
    public void editCurso(Curso curso,List<Modulo>modulosBorrados) {
        System.out.println(modulosBorrados);
        System.out.println(curso);

        try(Session s=HibernateUtil.getSession().openSession()){
            Boolean borrado=false;
            Transaction t=s.beginTransaction();
            Curso cursoBBDD=s.get(Curso.class,curso.getId());

            List<Modulo> modulosToRemove = new ArrayList<>();
            for(Modulo modulo:modulosBorrados){
                System.out.println(modulo);
                Query<Modulo>queryM=s.createQuery("select m from Modulo m where m.nombre=:nombre", Modulo.class);
                queryM.setParameter("nombre",modulo.getNombre());
                Modulo moduloBBDD=queryM.getSingleResult();

                if(moduloBBDD!=null){
                    Iterator<Modulo>it=cursoBBDD.getModulos().iterator();
                    borrado=true;
                    while (it.hasNext()){
                        Modulo moduloCurso=it.next();

                        if(moduloCurso==moduloBBDD){
                            modulosToRemove.add(moduloCurso);
                            s.remove(moduloBBDD);

                        }
                    }
                }
                }

            for(Modulo modulo:modulosToRemove){
                cursoBBDD.getModulos().remove(modulo);
            }
            if(borrado) {
                Query<PersonalIIP> queryIns = s.createQuery("select ins.instructor from InstructorCurso ins where ins.curso.id=:idCurso", PersonalIIP.class);
                queryIns.setParameter("idCurso", cursoBBDD.getId());
                List<PersonalIIP> instructores = queryIns.getResultList();

                Boolean entrada = false;
                PersonalIIP instructorBorrado = null;
                for (int i = 0; i < instructores.size(); i++) {
                    instructorBorrado = instructores.get(i);
                    entrada = false;
                    for (Modulo modulos : cursoBBDD.getModulos()) {
                        PersonalIIP instructor = modulos.getInstructor();
                        if (instructores.get(i).equals(instructor)) {
                            entrada = true;
                            System.out.println("ENTRORORORROR");
                        } else if (!instructores.get(i).equals(instructor) && cursoBBDD.getModulos().size() == 1) {
                            Query<InstructorCurso> ins = s.createQuery("select ins from InstructorCurso ins where ins.instructor.id=:id and ins.curso.id=:idCurso", InstructorCurso.class);
                            ins.setParameter("id", instructorBorrado.getId());
                            ins.setParameter("idCurso",cursoBBDD.getId());
                            InstructorCurso instr = ins.getSingleResult();
                            cursoBBDD.getCursoInstructor().remove(instr);
                            s.remove(instr);
                        }

                    }
                    if (entrada == false && cursoBBDD.getModulos().size() > 1) {
                        Query<InstructorCurso> ins = s.createQuery("select ins from InstructorCurso ins where ins.instructor.id=:id and ins.curso.id=:idCurso", InstructorCurso.class);
                        ins.setParameter("id", instructorBorrado.getId());
                        ins.setParameter("idCurso",cursoBBDD.getId());
                        InstructorCurso instr = ins.getSingleResult();
                        cursoBBDD.getCursoInstructor().remove(instr);
                        s.remove(instr);
                    }
                }

            }

            if(curso.getModulos().size()==cursoBBDD.getModulos().size()){
                for (int i=0;i<curso.getModulos().size();i++) {
                    InstructorCurso instructorCurso = new InstructorCurso();
                    System.out.println(curso.getModulos().get(i));
                    cursoBBDD.getModulos().get(i).setNombre(curso.getModulos().get(i).getNombre());
                    if (cursoBBDD.getModulos().get(i).getInstructor().getId() != curso.getModulos().get(i).getInstructor().getId()) {
                        InstructorCurso instructorCursoObtenido = new InstructorCurso();
                        instructorCursoObtenido.setInstructorId(cursoBBDD.getModulos().get(i).getInstructor());
                        instructorCursoObtenido.setCursoId(cursoBBDD);
                        cursoBBDD.getCursoInstructor().remove(instructorCursoObtenido);
                        cursoBBDD.getModulos().get(i).setInstructor(curso.getModulos().get(i).getInstructor());
                        instructorCurso.setInstructorId(curso.getModulos().get(i).getInstructor());
                        instructorCurso.setCursoId(cursoBBDD);
                        Query<InstructorCurso> ins = s.createQuery("select ins from InstructorCurso ins where ins.instructor.id=:id and ins.curso.id=:idCurso", InstructorCurso.class);
                        ins.setParameter("id", instructorCurso.getInstructorId().getId());
                        ins.setParameter("idCurso",cursoBBDD.getId());
                        List<InstructorCurso>comp=ins.getResultList();
                        if(comp.isEmpty()){
                            cursoBBDD.getCursoInstructor().add(instructorCurso);
                        }
                    }
                }



            }else if(curso.getModulos().size()>cursoBBDD.getModulos().size()){

                    for (int i=0;i<curso.getModulos().size();i++) {
                        InstructorCurso instructorCurso = new InstructorCurso();
                        try {
                            System.out.println(curso.getModulos().get(i));
                            cursoBBDD.getModulos().get(i).setNombre(curso.getModulos().get(i).getNombre());
                            if (cursoBBDD.getModulos().get(i).getInstructor().getId() != curso.getModulos().get(i).getInstructor().getId()) {
                                InstructorCurso instructorCursoObtenido = new InstructorCurso();
                                instructorCursoObtenido.setInstructorId(cursoBBDD.getModulos().get(i).getInstructor());
                                instructorCursoObtenido.setCursoId(cursoBBDD);
                                cursoBBDD.getCursoInstructor().remove(instructorCursoObtenido);
                                cursoBBDD.getModulos().get(i).setInstructor(curso.getModulos().get(i).getInstructor());
                                instructorCurso.setInstructorId(curso.getModulos().get(i).getInstructor());
                                instructorCurso.setCursoId(cursoBBDD);
                                Query<InstructorCurso> ins = s.createQuery("select ins from InstructorCurso ins where ins.instructor.id=:id and ins.curso.id=:idCurso", InstructorCurso.class);
                                ins.setParameter("id", instructorCurso.getInstructorId().getId());
                                ins.setParameter("idCurso",cursoBBDD.getId());
                                List<InstructorCurso>comp=ins.getResultList();
                                if(comp.isEmpty()){
                                    cursoBBDD.getCursoInstructor().add(instructorCurso);
                                }

                            }
                        } catch (IndexOutOfBoundsException e) {
                            s.persist(curso.getModulos().get(i));
                            cursoBBDD.getModulos().add(curso.getModulos().get(i));
                            instructorCurso.setInstructorId(curso.getModulos().get(i).getInstructor());
                            instructorCurso.setCursoId(cursoBBDD);
                            Query<InstructorCurso> query = s.createQuery("select ic from InstructorCurso ic where ic.instructor.id=:idInstructor and ic.curso.id=:cursoId");
                            query.setParameter("idInstructor", curso.getModulos().get(i).getInstructor().getId());
                            query.setParameter("cursoId", cursoBBDD.getId());
                            List<InstructorCurso> instructoExistente = query.getResultList();
                            if (instructoExistente.isEmpty()) {
                                cursoBBDD.getCursoInstructor().add(instructorCurso);
                            }
                            Query<PersonalBolsa> q = s.createQuery("select ca.alumnoId from AlumnoCurso ca where ca.cursoId.id=:idCursito");
                            q.setParameter("idCursito", cursoBBDD.getId());
                            List<PersonalBolsa> alumnosEncurso = q.getResultList();
                            System.out.println(alumnosEncurso);
                            for (int a = 0; a < alumnosEncurso.size(); a++) {
                                AlumnoModulo alumnoModulo = new AlumnoModulo();
                                alumnoModulo.setAlumnoId(alumnosEncurso.get(a));
                                alumnoModulo.setModuloId(curso.getModulos().get(i));
                                s.persist(alumnoModulo);
                                alumnosEncurso.get(a).getModuloAlumno().add(alumnoModulo);
                            }


                        }
                    }




            }
            Query<PersonalIIP> queryIns = s.createQuery("select ins.instructor from InstructorCurso ins where ins.curso.id=:idCurso", PersonalIIP.class);
            queryIns.setParameter("idCurso", cursoBBDD.getId());
            List<PersonalIIP> instructores = queryIns.getResultList();

            Boolean entrada = false;
            PersonalIIP instructorBorrado = null;
            for (int i = 0; i < instructores.size(); i++) {
                instructorBorrado = instructores.get(i);
                entrada = false;
                for (Modulo modulos : cursoBBDD.getModulos()) {
                    PersonalIIP instructor = modulos.getInstructor();
                    if (instructores.get(i).equals(instructor)) {
                        entrada = true;
                        System.out.println("ENTRORORORROR");
                    } else if (!instructores.get(i).equals(instructor) && cursoBBDD.getModulos().size() == 1) {
                        Query<InstructorCurso> ins = s.createQuery("select ins from InstructorCurso ins where ins.instructor.id=:id", InstructorCurso.class);
                        ins.setParameter("id", instructorBorrado.getId());
                        InstructorCurso instr = ins.getSingleResult();
                        cursoBBDD.getCursoInstructor().remove(instr);
                        s.remove(instr);
                    }

                }
                if (entrada == false && cursoBBDD.getModulos().size() > 1) {
                    Query<InstructorCurso> ins = s.createQuery("select ins from InstructorCurso ins where ins.instructor.id=:id", InstructorCurso.class);
                    ins.setParameter("id", instructorBorrado.getId());
                    InstructorCurso instr = ins.getSingleResult();
                    cursoBBDD.getCursoInstructor().remove(instr);
                    s.remove(instr);
                }
            }

            s.merge(cursoBBDD);
            t.commit();
        }

        }


    }

