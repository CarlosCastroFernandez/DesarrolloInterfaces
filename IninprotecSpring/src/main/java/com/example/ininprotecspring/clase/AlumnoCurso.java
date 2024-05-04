package com.example.ininprotecspring.clase;

import jakarta.persistence.*;

@Entity
@Table(name = "alumno_curso")
public class AlumnoCurso {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
@ManyToOne()
@JoinColumn(name = "alumno_id")
  private PersonalBolsa alumnoId;
@ManyToOne()
@JoinColumn(name = "curso_id")
  private Curso cursoId;
@Column(name = "nota_curso")
  private Double notaCurso;
@Column(name = "fecha_finalizacion")
private String fechaFin;

    public AlumnoCurso(Long id, PersonalBolsa alumnoId, Curso cursoId, Double notaCurso,String fechaFin) {
        this.id = id;
        this.alumnoId = alumnoId;
        this.cursoId = cursoId;
        this.notaCurso = notaCurso;
        this.fechaFin=fechaFin;
    }

    public AlumnoCurso() {
    }

    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public PersonalBolsa getAlumnoId() {
    return alumnoId;
  }

  public void setAlumnoId(PersonalBolsa alumnoId) {
    this.alumnoId = alumnoId;
  }


  public Curso getCursoId() {
    return cursoId;
  }

  public void setCursoId(Curso cursoId) {
    this.cursoId = cursoId;
  }

  public String getFechaFin() {
    return fechaFin;
  }

  public void setFechaFin(String fechaFin) {
    this.fechaFin = fechaFin;
  }

  public Double getNotaCurso() {
    return notaCurso;
  }

  public void setNotaCurso(Double notaCurso) {
    this.notaCurso = notaCurso;
  }

  @Override
  public String toString() {
    return "AlumnoCurso{" +
            "id=" + id +
            ", alumnoId=" + alumnoId.getNombre() +
            ", cursoId=" + cursoId.getNombre() +
            ", notaCurso=" + notaCurso +
            '}';
  }
}
