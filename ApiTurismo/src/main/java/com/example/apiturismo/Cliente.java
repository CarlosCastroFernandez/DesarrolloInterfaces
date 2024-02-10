package com.example.apiturismo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

/**
 * La clase Cliente representa la entidad de un cliente en el sistema.
 * Cada cliente tiene un identificador único, nombre, apellido, correo, contraseña, token,
 * hotel asociado (en caso de reservas en hoteles) y restaurante asociado (en caso de reservas en restaurantes).
 *
 * La clase está anotada con las anotaciones JPA para mapearla a una tabla en la base de datos.
 * Utiliza la estrategia de generación de identificador automático y establece las relaciones
 * con otras entidades, como Hotel y Restaurante.
 */
@Entity
@Table(name = "cliente")
public class Cliente {

  /**
   * Identificador único del cliente.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Nombre del cliente.
   */
  private String nombre;

  /**
   * Apellido del cliente.
   */
  private String apellido;

  /**
   * Correo electrónico del cliente.
   * Esta propiedad está anotada con @JsonIgnore para evitar su serialización en respuestas JSON.
   */
  @JsonIgnore
  private String correo;

  /**
   * Contraseña del cliente.
   * Esta propiedad está anotada con @JsonIgnore para evitar su serialización en respuestas JSON.
   */
  @JsonIgnore
  private String contraseña;

  /**
   * Token asociado al cliente.
   * Esta propiedad está anotada con @JsonIgnore para evitar su serialización en respuestas JSON.
   */
  @JsonIgnore
  private String token;

  /**
   * Hotel asociado al cliente en caso de reservas en hoteles.
   * La relación está mapeada a través del campo "hotel_id" en la base de datos.
   */
  @ManyToOne()
  @JoinColumn(name = "hotel_id")
  @JsonIgnore
  private Hotel hotelId;

  /**
   * Restaurante asociado al cliente en caso de reservas en restaurantes.
   * La relación está mapeada a través del campo "restaurante_id" en la base de datos.
   */
  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "restaurante_id")
  private Restaurante restauranteId;

  /**
   * Constructor predeterminado para la clase Cliente.
   */
  public Cliente() {
    // Constructor vacío requerido por JPA.
  }

  // Métodos getter y setter para todos los campos de la clase...


  public long getId() {
    return id;
  }

  public void setId(long id) {
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


  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }


  public Hotel getHotelId() {
    return hotelId;
  }

  public void setHotelId(Hotel hotelId) {
    this.hotelId = hotelId;
  }


  public Restaurante getRestauranteId() {
    return restauranteId;
  }

  public void setRestauranteId(Restaurante restauranteId) {
    this.restauranteId = restauranteId;
  }


}
