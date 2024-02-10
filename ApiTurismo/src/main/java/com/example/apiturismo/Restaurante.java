package com.example.apiturismo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "restaurante")
public class Restaurante {

  /**
   * Identificador único del restaurante.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Nombre del restaurante.
   */
  private String nombre;

  /**
   * Tipo de restaurante.
   */
  private String tipo;

  /**
   * Dirección del restaurante.
   */
  private String direccion;

  /**
   * Descripción del restaurante.
   */
  private String descripcion;

  /**
   * Valoración del restaurante.
   */
  private Double valoracion;

  /**
   * Año de fundación del restaurante.
   */
  private Long anioFundado;

  /**
   * Propietario del restaurante.
   * La relación está mapeada a través del campo "propietarioRId" en la base de datos.
   */
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "propietario_id")
  private Propietario propietarioRId;

  /**
   * Lista de clientes asociados al restaurante.
   * La relación está mapeada a través del campo "restauranteId" en la base de datos.
   */
  @OneToMany(mappedBy = "restauranteId", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
  private List<Cliente> clientes;

  /**
   * Constructor predeterminado para la clase Restaurante.
   */
  public Restaurante() {
    // Constructor vacío requerido por JPA.
  }

  public Long getId() {
    return id;
  }

  public Propietario getPropietarioRId() {
    return propietarioRId;
  }

  public void setPropietarioRId(Propietario propietarioRId) {
    this.propietarioRId = propietarioRId;
  }

  public List<Cliente> getClientes() {
    return clientes;
  }

  public void setClientes(List<Cliente> clientes) {
    this.clientes = clientes;
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


  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }


  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }


  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }


  public Double getValoracion() {
    return valoracion;
  }

  public void setValoracion(Double valoracion) {
    this.valoracion = valoracion;
  }


  public Long getAnioFundado() {
    return anioFundado;
  }

  public void setAnioFundado(Long anioFundado) {
    this.anioFundado = anioFundado;
  }

}
