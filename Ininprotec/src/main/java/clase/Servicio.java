package clase;

import jakarta.persistence.*;

@Entity
@Table(name = "servicio")
public class Servicio {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nombre;
  private Double beneficio;

    public Servicio(Long id, String nombre, Double beneficio) {
        this.id = id;
        this.nombre = nombre;
        this.beneficio = beneficio;
    }

    public Servicio(String nombre, Double beneficio) {
      this.nombre = nombre;
      this.beneficio = beneficio;
    }
  public Servicio() {
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


  public Double getBeneficio() {
    return beneficio;
  }

  public void setBeneficio(Double beneficio) {
    this.beneficio = beneficio;
  }

    @Override
    public String toString() {
        return "Servicio{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", beneficio=" + beneficio +
                '}';
    }
}
