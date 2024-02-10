package com.example.apiturismo;

import jakarta.persistence.*;

import java.util.List;

/**
 * La clase Hotel representa la entidad de un hotel en el sistema.
 * Cada hotel tiene un identificador único, nombre, dirección, número de estrellas,
 * descripción, precio por noche, metros cuadrados, número de habitaciones, año de fundación,
 * propietario asociado y una lista de clientes que han reservado en el hotel.
 *
 * La clase está anotada con las anotaciones JPA para mapearla a una tabla en la base de datos.
 * Utiliza la estrategia de generación de identificador automático y establece las relaciones
 * con otras entidades, como Propietario y Cliente.
 */
@Entity
@Table(name = "hotel")
public class Hotel {

  /**
   * Identificador único del hotel.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Nombre del hotel.
   */
  private String nombre;

  /**
   * Dirección del hotel.
   */
  private String direccion;

  /**
   * Número de estrellas que tiene el hotel.
   */
  private Long estrellas;

  /**
   * Descripción del hotel.
   */
  private String descripcion;

  /**
   * Precio por noche en el hotel.
   */
  private Double precioNoche;

  /**
   * Metros cuadrados totales del hotel.
   */
  private Double metrosCuadrados;

  /**
   * Número total de habitaciones en el hotel.
   */
  private Long numHabitaciones;

  /**
   * Año en que fue fundado el hotel.
   */
  private Long anioFundado;

  /**
   * Propietario asociado al hotel.
   * La relación está mapeada a través del campo "propietario_id" en la base de datos.
   */
  @ManyToOne
  @JoinColumn(name = "propietario_id")
  private Propietario propietarioId;

  /**
   * Lista de clientes que han reservado en el hotel.
   * La relación está mapeada a través del campo "hotelId" en la base de datos.
   */
  @OneToMany(mappedBy = "hotelId", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
  private List<Cliente> clientes;

  /**
   * Constructor predeterminado para la clase Hotel.
   */
  public Hotel() {
    // Constructor vacío requerido por JPA.
  }

  // Métodos getter y setter para todos los campos de la clase...





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

  public List<Cliente> getClientes() {
    return clientes;
  }

  public void setClientes(List<Cliente> clientes) {
    this.clientes = clientes;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }


  public Long getEstrellas() {
    return estrellas;
  }

  public void setEstrellas(Long estrellas) {
    this.estrellas = estrellas;
  }


  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }


  public Double getPrecioNoche() {
    return precioNoche;
  }

  public void setPrecioNoche(Double precioNoche) {
    this.precioNoche = precioNoche;
  }


  public Double getMetrosCuadrados() {
    return metrosCuadrados;
  }

  public void setMetrosCuadrados(Double metrosCuadrados) {
    this.metrosCuadrados = metrosCuadrados;
  }


  public Long getNumHabitaciones() {
    return numHabitaciones;
  }

  public void setNumHabitaciones(Long numHabitaciones) {
    this.numHabitaciones = numHabitaciones;
  }


  public Long getAnioFundado() {
    return anioFundado;
  }

  public void setAnioFundado(Long anioFundado) {
    this.anioFundado = anioFundado;
  }


  public Propietario getPropietarioId() {
    return propietarioId;
  }

  public void setPropietarioId(Propietario propietarioId) {
    this.propietarioId = propietarioId;
  }


}
