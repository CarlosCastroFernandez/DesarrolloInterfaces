package clase;


import jakarta.persistence.*;

import java.sql.Date;
@Entity
@Table(name = "actividad")
public class Actividad {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nombre;
  private Date fecha;
  private String observaciones;
  @Transient
  private Long alumnoId;
@JoinColumn(name="alumno_id")
@ManyToOne()
  private Alumno alumno;

    public Actividad(Long id, String nombre, Date fecha, String observaciones, Long alumnoId) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.observaciones = observaciones;
        this.alumnoId = alumnoId;

    }
    public Actividad( String nombre, Date fecha, String observaciones, Long alumnoId, Alumno alumno) {

        this.nombre = nombre;
        this.fecha = fecha;
        this.observaciones = observaciones;
        this.alumnoId = alumnoId;
        this.alumno = alumno;
    }
    public Actividad(){

    }

    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }


  public Date getFecha() {
    return fecha;
  }

  public void setFecha(java.sql.Date fecha) {
    this.fecha = fecha;
  }


  public String getObservaciones() {
    return observaciones;
  }

  public void setObservaciones(String observaciones) {
    this.observaciones = observaciones;
  }


  public Long getAlumnoId() {
    return alumnoId;
  }

  public void setAlumnoId(Long alumnoId) {
    this.alumnoId = alumnoId;
  }

    @Override
    public String toString() {
        return "Actividad{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fecha=" + fecha +
                ", observaciones='" + observaciones + '\'' +
                ", alumnoId=" + alumnoId +
                '}';
    }
}
