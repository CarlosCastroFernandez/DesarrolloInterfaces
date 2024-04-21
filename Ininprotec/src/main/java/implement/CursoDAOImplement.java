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
           // Boolean borrado=false;
            Transaction t=s.beginTransaction();
            Curso cursoBBDD=s.get(Curso.class,curso.getId());

            List<Modulo> modulosToRemove = new ArrayList<>();
            for(Modulo modulo:modulosBorrados){
                Modulo moduloBBDD=s.get(Modulo.class,modulo.getId());
                if(moduloBBDD!=null){
                    Iterator<Modulo>it=cursoBBDD.getModulos().iterator();
                    //borrado=true;
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

           /* if(borrado){
                for (AlumnoCurso alumnoCurso : cursoBBDD.getAlumnoCurso()) {
                    if (alumnoCurso.getNotaCurso() != null) { // Sólo procesar si ya hay una nota establecida
                        double sumaNotas = 0;
                        int contadorModulos = 0;
                        for (AlumnoModulo alumnoModulo : alumnoCurso.getAlumnoId().getModuloAlumno()) {
                            if (!modulosBorrados.stream().anyMatch(m -> m.getId().equals(alumnoModulo.getModuloId().getId()))) {
                                if (alumnoModulo.getModuloId() != null && alumnoModulo.getModuloId().getCurso().getId().equals(cursoBBDD.getId())) {
                                    sumaNotas += (alumnoModulo.getNotaModulo() != null) ? alumnoModulo.getNotaModulo() : 0;
                                    contadorModulos++;
                                }
                            }
                        }
                        if (contadorModulos > 0) {
                            double nuevaNotaCurso = sumaNotas / contadorModulos;
                            alumnoCurso.setNotaCurso(nuevaNotaCurso);
                        } // Si no hay módulos válidos, no se actualiza nada
                    }



                }
            }*/


            if(curso.getModulos().size()==cursoBBDD.getModulos().size()){
                for (int i=0;i<curso.getModulos().size();i++){
                    InstructorCurso instructorCurso=new InstructorCurso();
                    System.out.println(curso.getModulos().get(i));
                    cursoBBDD.getModulos().get(i).setNombre(curso.getModulos().get(i).getNombre());
                    if(   cursoBBDD.getModulos().get(i).getInstructor().getId()!=curso.getModulos().get(i).getInstructor().getId()){
                        InstructorCurso instructorCursoObtenido=new InstructorCurso();
                       instructorCursoObtenido.setInstructorId(cursoBBDD.getModulos().get(i).getInstructor());
                       instructorCursoObtenido.setCursoId(cursoBBDD);
                       cursoBBDD.getCursoInstructor().remove(instructorCursoObtenido);
                        cursoBBDD.getModulos().get(i).setInstructor(curso.getModulos().get(i).getInstructor());
                        instructorCurso.setInstructorId(curso.getModulos().get(i).getInstructor());
                        instructorCurso.setCursoId(cursoBBDD);
                        cursoBBDD.getCursoInstructor().add(instructorCurso);
                    }



                }
            }else if(curso.getModulos().size()>cursoBBDD.getModulos().size()){

                    for (int i=0;i<curso.getModulos().size();i++){
                        InstructorCurso instructorCurso=new InstructorCurso();
                        try{
                        System.out.println(curso.getModulos().get(i));
                        cursoBBDD.getModulos().get(i).setNombre(curso.getModulos().get(i).getNombre());
                        if(   cursoBBDD.getModulos().get(i).getInstructor().getId()!=curso.getModulos().get(i).getInstructor().getId()){
                            InstructorCurso instructorCursoObtenido=new InstructorCurso();
                            instructorCursoObtenido.setInstructorId(cursoBBDD.getModulos().get(i).getInstructor());
                            instructorCursoObtenido.setCursoId(cursoBBDD);
                            cursoBBDD.getCursoInstructor().remove(instructorCursoObtenido);
                            cursoBBDD.getModulos().get(i).setInstructor(curso.getModulos().get(i).getInstructor());
                            instructorCurso.setInstructorId(curso.getModulos().get(i).getInstructor());
                            instructorCurso.setCursoId(cursoBBDD);
                            cursoBBDD.getCursoInstructor().add(instructorCurso);
                        }
                        }catch (IndexOutOfBoundsException e){
                            s.persist(curso.getModulos().get(i));
                            cursoBBDD.getModulos().add(curso.getModulos().get(i));
                            instructorCurso.setInstructorId(curso.getModulos().get(i).getInstructor());
                            instructorCurso.setCursoId(cursoBBDD);
                            Query<InstructorCurso>query=s.createQuery("select ic from InstructorCurso ic where ic.instructor.id=:idInstructor and ic.curso.id=:cursoId");
                            query.setParameter("idInstructor",curso.getModulos().get(i).getInstructor().getId());
                            query.setParameter("cursoId",cursoBBDD.getId());
                            InstructorCurso instructoExistente=query.getSingleResult();
                            if(instructoExistente==null){
                                cursoBBDD.getCursoInstructor().add(instructorCurso);
                            }
                            Query<PersonalBolsa>q=s.createQuery("select ca.alumnoId from AlumnoCurso ca where ca.cursoId.id=:idCursito");
                            q.setParameter("idCursito",cursoBBDD.getId());
                            List<PersonalBolsa> alumnosEncurso=q.getResultList();
                            System.out.println(alumnosEncurso);
                            for(int a=0;a<alumnosEncurso.size();a++){
                                AlumnoModulo alumnoModulo=new AlumnoModulo();
                                alumnoModulo.setAlumnoId(alumnosEncurso.get(a));
                                alumnoModulo.setModuloId(curso.getModulos().get(i));
                                s.persist(alumnoModulo);
                                alumnosEncurso.get(a).getModuloAlumno().add(alumnoModulo);
                            }


                        }
                    }



            }
            s.merge(cursoBBDD);
            t.commit();
        }


       /* try(Session s=HibernateUtil.getSession().openSession()){
            Transaction t=s.beginTransaction();
            Curso cursoBBDD=s.get(Curso.class,curso.getId());
            cursoBBDD.setCursoInstructor(new ArrayList<>());

            for (InstructorCurso instructorCurso : curso.getCursoInstructor()) {
               cursoBBDD.getCursoInstructor().add(instructorCurso); // Guarda el cambio
            }
            if(!modulosBorrados.isEmpty()){
                for (Modulo modulo : modulosBorrados) {
                    Modulo managedModulo = s.get(Modulo.class, modulo.getId());
                    s.remove(managedModulo); // Elimina el módulo
                }

                for (Modulo modulo : curso.getModulos()) {
                    s.persist(modulo);
                    Modulo managedModulo = s.get(Modulo.class, modulo.getId());
                    if (managedModulo != null) {
                        managedModulo.setInstructor(modulo.getInstructor());
                        managedModulo.setNombre(modulo.getNombre());
                        s.saveOrUpdate(managedModulo);
                    } else {
                        // Si el módulo es nuevo, deberías persistirlo
                        s.persist(modulo);
                    }
                   cursoBBDD.getModulos().add(modulo);
                }
            }

            s.merge(cursoBBDD);
            t.commit();*/
        }


    }

