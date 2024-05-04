package com.example.ininprotecspring.clase;

import jakarta.persistence.*;

@Entity
@Table(name = "instructor_curso")
public class InstructorCurso {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
@ManyToOne()
@JoinColumn(name = "instructor_id")
  private PersonalIIP instructor;
@ManyToOne()
@JoinColumn(name = "curso_id")
  private Curso curso;

    public InstructorCurso(Long id, PersonalIIP instructor, Curso curso) {
        this.id = id;
        this.instructor = instructor;
        this.curso = curso;
    }

    public InstructorCurso() {
    }

    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public PersonalIIP getInstructorId() {
    return instructor;
  }

  public void setInstructorId(PersonalIIP instructorId) {
    this.instructor = instructorId;
  }


  public Curso getCursoId() {
    return curso;
  }

  public void setCursoId(Curso cursoId) {
    this.curso = cursoId;
  }

  @Override
  public String toString() {
    return "InstructorCurso{" +
            "id=" + id +
            ", instructor=" + instructor.getNombre() +
            ", curso=" + curso.getNombre() +
            '}';
  }
}
