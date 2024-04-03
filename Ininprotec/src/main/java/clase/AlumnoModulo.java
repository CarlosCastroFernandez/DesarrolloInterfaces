package clase;

import jakarta.persistence.*;

@Entity
@Table(name = "alumno_modulo")
public class AlumnoModulo {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
@ManyToOne()
@JoinColumn(name = "alumno_id")
  private PersonalBolsa alumnoId;
@ManyToOne()
@JoinColumn(name = "modulo_id")
  private Modulo moduloId;
@Column(name = "nota_modulo")
  private Double notaModulo;

    public AlumnoModulo(Long id, PersonalBolsa alumnoId, Modulo moduloId, Double notaModulo) {
        this.id = id;
        this.alumnoId = alumnoId;
        this.moduloId = moduloId;
        this.notaModulo = notaModulo;
    }

    public AlumnoModulo() {
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


  public Modulo getModuloId() {
    return moduloId;
  }

  public void setModuloId(Modulo moduloId) {
    this.moduloId = moduloId;
  }


  public Double getNotaModulo() {
    return notaModulo;
  }

  public void setNotaModulo(Double notaModulo) {
    this.notaModulo = notaModulo;
  }

  @Override
  public String toString() {
    return "AlumnoModulo{" +
            "id=" + id +
            ", alumnoId=" + alumnoId.getNombre() +
            ", moduloId=" + moduloId.getNombre() +
            ", notaModulo=" + notaModulo +
            '}';
  }
}
