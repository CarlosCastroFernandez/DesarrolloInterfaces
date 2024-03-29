package clase;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="alumno")
public class Alumno {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nombre;
  private String apellido;
  private String dni;
  private String telefono;
  private String correo;
  @OneToMany(mappedBy = "alumno",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
  private List<Actividad>actividades;

public Alumno(){

}

    public Alumno(Long id, String nombre, String apellido, String dni, String telefono, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.correo = correo;

    }
    public Alumno(String nombre, String apellido, String dni, String telefono, String correo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.correo = correo;
    }

    public Long getId() {
    return id;
  }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
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


  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }


  public String getDni() {
    return dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }


  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }


  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", actividades=" + actividades +
                '}';
    }
}
