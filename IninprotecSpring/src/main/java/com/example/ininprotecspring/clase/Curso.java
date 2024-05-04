package com.example.ininprotecspring.clase;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "curso")
public class Curso implements Serializable {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nombre;
  @OneToMany(mappedBy = "cursoId",fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
  private List<AlumnoCurso> alumnoCurso;

  @OneToMany(mappedBy = "curso",fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
  private List<Modulo>modulos=new ArrayList<>();
  @OneToMany(mappedBy = "curso",fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
  private List<InstructorCurso> cursoInstructor;


  public Curso(Long id, String nombre, List<AlumnoCurso> alumnos,List<Modulo>modulos) {
    this.id = id;
    this.nombre = nombre;
    this.alumnoCurso = alumnos;
    this.modulos=modulos;
  }
  public Curso(Long id, String nombre) {
    this.id = id;
    this.nombre = nombre;

  }
  public Curso(String nombre,List<Modulo>modulos) {
    this.nombre = nombre;

    this.modulos=modulos;
  }

  public Curso() {
  }
  public Long getId() {
    return id;
  }



  public List<Modulo> getModulos() {
    return modulos;
  }

  public void setModulos(List<Modulo> modulos) {
    this.modulos = modulos;
  }

  public List<AlumnoCurso> getAlumnoCurso() {
    return alumnoCurso;
  }

  public void setAlumnoCurso(List<AlumnoCurso> alumnos) {
    this.alumnoCurso = alumnos;
  }

  public List<InstructorCurso> getCursoInstructor() {
    return cursoInstructor;
  }

  public void setCursoInstructor(List<InstructorCurso> instructor) {
    this.cursoInstructor = instructor;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }


  @Override
  public String toString() {
    return "Curso{" +
            "id=" + id +
            ", nombre='" + nombre + '\'' +
            '}'+" modulos"+modulos.toString();
  }
}
