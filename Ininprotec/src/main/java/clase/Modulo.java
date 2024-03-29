package clase;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="modulo")
public class Modulo implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private Long id;
  private String nombre;

  @Transient
  private Long cursoId;
  @JoinColumn(name = "curso_id")
  @ManyToOne()
  private Curso curso;
  @JoinColumn(name = "instructor_id")
  @ManyToOne
  private PersonalIIP instructor;
  @ManyToMany(mappedBy = "modulos",fetch = FetchType.EAGER)
  private List<PersonalBolsa> alumnos;
  @Column(name = "nota_modulo")
  private Double notaModulo;

  public Modulo(Long id, String nombre, Long cursoId, Curso curso) {
    this.id = id;
    this.nombre = nombre;

    this.cursoId = cursoId;
    this.curso = curso;
  }
  public Modulo(String nombre, Long cursoId, Curso curso) {
    this.nombre = nombre;

    this.cursoId = cursoId;
    this.curso = curso;
  }

  public Modulo() {
  }

  public Double getNotaModulo() {
    return notaModulo;
  }

  public void setNotaModulo(Double notaModulo) {
    this.notaModulo = notaModulo;
  }

  public Curso getCurso() {
    return curso;
  }

  public void setCurso(Curso curso) {
    this.curso = curso;
  }

  public PersonalIIP getInstructor() {
    return instructor;
  }

  public List<PersonalBolsa> getAlumnos() {
    return alumnos;
  }

  public void setAlumnos(List<PersonalBolsa> alumnos) {
    this.alumnos = alumnos;
  }

  public void setInstructor(PersonalIIP instructor) {
    this.instructor = instructor;
  }

  public Long getId() {
    return id;
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




  public Long getCursoId() {
    return cursoId;
  }

  public void setCursoId(Long cursoId) {
    this.cursoId = cursoId;
  }

  @Override
  public String toString() {
    return "Modulo{" +
            "id=" + id +
            ", nombre='" + nombre + '\'' +
            "notaModulo "+notaModulo+" "+
            ", cursoId=" + curso.getId()+ ", instructorid = "+instructor.getId();

  }
}
