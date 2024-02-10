package com.example.apiturismo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

/**
 * La clase Propietario representa la entidad de un propietario en el sistema.
 * Cada propietario tiene un identificador único, nombre, apellido, correo, contraseña, token,
 * y listas de hoteles y restaurantes asociados.
 *
 * La clase está anotada con las anotaciones JPA para mapearla a una tabla en la base de datos.
 * Utiliza la estrategia de generación de identificador automático y establece las relaciones
 * con otras entidades, como Hotel y Restaurante.
 */
@Entity
@Table(name = "propietario")
public class Propietario {

  /**
   * Identificador único del propietario.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Nombre del propietario.
   */
  private String nombre;

  /**
   * Apellido del propietario.
   */
  private String apellido;

  /**
   * Correo electrónico del propietario.
   * Esta propiedad está anotada con @JsonIgnore para evitar su serialización en respuestas JSON.
   */
  @JsonIgnore
  private String correo;

  /**
   * Contraseña del propietario.
   * Esta propiedad está anotada con @JsonIgnore para evitar su serialización en respuestas JSON.
   */
  @JsonIgnore
  private String contraseña;

  /**
   * Token asociado al propietario.
   * Esta propiedad está anotada con @JsonIgnore para evitar su serialización en respuestas JSON.
   */
  @JsonIgnore
  private String token;

  /**
   * Lista de hoteles asociados al propietario.
   * La relación está mapeada a través del campo "propietarioId" en la base de datos.
   */
  @JsonIgnore
  @OneToMany(mappedBy = "propietarioId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private List<Hotel> hoteles;

  /**
   * Lista de restaurantes asociados al propietario.
   * La relación está mapeada a través del campo "propietarioRId" en la base de datos.
   */
  @JsonIgnore
  @OneToMany(mappedBy = "propietarioRId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private List<Restaurante> restaurantes;

  /**
   * Constructor predeterminado para la clase Propietario.
   */
  public Propietario() {
    // Constructor vacío requerido por JPA.
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


  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }


  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }


  public String getContraseña() {
    return contraseña;
  }

  public void setContraseña(String contraseña) {
    this.contraseña = contraseña;
  }

  public List<Hotel> getHoteles() {
    return hoteles;
  }

  public void setHoteles(List<Hotel> hoteles) {
    this.hoteles = hoteles;
  }

  public List<Restaurante> getRestaurantes() {
    return restaurantes;
  }

  public void setRestaurantes(List<Restaurante> restaurantes) {
    this.restaurantes = restaurantes;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

}
