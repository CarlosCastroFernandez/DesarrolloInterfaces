package clases;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
@Table(name="itempedido")
public class Itempedido implements Serializable {

  // Atributos de la clase

  /**
   * Identificador único del elemento de pedido.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /**
   * Código del pedido al que pertenece este elemento.
   * Nota: La anotación @Transient indica que este campo no debe ser persistido en la base de datos.
   */
  @Transient
  private String codigoPedido;

  /**
   * Cantidad de productos en este elemento de pedido.
   */
  private Integer cantidad;

  /**
   * Pedido al que pertenece este elemento.
   * La relación @ManyToOne indica una asociación many-to-one con la clase Pedido.
   * El joinColumn especifica la columna en la tabla 'itempedido' que se utiliza para mapear la relación.
   */
  @ManyToOne
  @JoinColumn(name = "codigoPedido",referencedColumnName = "codigo")
  private Pedido pedido;

  /**
   * Producto asociado a este elemento.
   * La relación @ManyToOne indica una asociación many-to-one con la clase Producto.
   * El joinColumn especifica la columna en la tabla 'itempedido' que se utiliza para mapear la relación.
   */
  @ManyToOne()
  @JoinColumn(name="productoID")
  private Producto producto;

  /**
   * Constructor de la clase Itempedido.
   *
   * @param id           Identificador del elemento de pedido.
   * @param codigo       Código del pedido al que pertenece este elemento.
   * @param cantidad     Cantidad de productos en este elemento de pedido.
   * @param productoId   Identificador del producto asociado a este elemento.
   */
  public Itempedido(Integer id, String codigo, Integer cantidad, Integer productoId) {
    this.producto = new Producto(); // Crea una instancia de la clase Producto
    this.id = id;
    this.codigoPedido = codigo;
    this.cantidad = cantidad;

  }
  public Itempedido(){

  }



  // Métodos Getter y Setter para acceder y modificar los atributos

  /**
   * Obtiene el identificador del elemento de pedido.
   *
   * @return El identificador del elemento de pedido.
   */
  public Integer getId() {
    return id;
  }

  public Pedido getPedido() {
    return pedido;
  }

  public void setPedido(Pedido pedido) {
    this.pedido = pedido;
  }

  /**
   * Establece el identificador del elemento de pedido.
   *
   * @param id El nuevo identificador del elemento de pedido.
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Obtiene el código del pedido al que pertenece este elemento.
   *
   * @return El código del pedido.
   */
  public String getCodigoPedido() {
    return codigoPedido;
  }

  /**
   * Establece el código del pedido al que pertenece este elemento.
   *
   * @param codigoPedido El nuevo código de pedido.
   */
  public void setCodigoPedido(String codigoPedido) {
    this.codigoPedido = codigoPedido;
  }

  /**
   * Obtiene la cantidad de productos en este elemento de pedido.
   *
   * @return La cantidad de productos.
   */
  public Integer getCantidad() {
    return cantidad;
  }

  /**
   * Establece la cantidad de productos en este elemento de pedido.
   *
   * @param cantidad La nueva cantidad de productos.
   */
  public void setCantidad(Integer cantidad) {
    this.cantidad = cantidad;
  }



  /**
   * Obtiene el objeto Producto asociado a este elemento de pedido.
   *
   * @return El objeto Producto asociado.
   */
  public Producto getProducto() {
    return producto;
  }

  /**
   * Establece el objeto Producto asociado a este elemento de pedido.
   *
   * @param producto El nuevo objeto Producto asociado.
   */
  public void setProducto(Producto producto) {
    this.producto = producto;
  }

  // Método toString para obtener una representación en cadena del objeto

  /**
   * Obtiene una representación en cadena del objeto Itempedido.
   *
   * @return Una cadena que contiene el código del pedido, la cantidad y el nombre del producto.
   */
  @Override
  public String toString() {
    String frase = "";
    frase += codigoPedido + "-----" + cantidad + "-----" + producto.getNombre() + "\n";
    return frase;
  }
}
