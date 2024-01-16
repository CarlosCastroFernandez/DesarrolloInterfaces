package clases;




import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
@Entity


public class Producto implements Serializable {
  @Id
  @GeneratedValue
  private Long id;        // Identificador único del producto
  private String nombre;  // Nombre del producto
  private String precio;  // Precio del producto
  private Long cantidad;  // Cantidad disponible del producto
public Producto(){

}

  public Producto(Long id, String nombre, String precio, Long cantidad) {
    this.id = id;
    this.nombre = nombre;
    this.precio = precio;
    this.cantidad = cantidad;
  }

  /**
   * Obtiene el identificador único del producto.
   *
   * @return El identificador del producto.
   */
  public Long getId() {
    return id;
  }

  /**
   * Establece el identificador único del producto.
   *
   * @param id El nuevo identificador del producto.
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Obtiene el nombre del producto.
   *
   * @return El nombre del producto.
   */
  public String getNombre() {
    return nombre;
  }

  /**
   * Establece el nombre del producto.
   *
   * @param nombre El nuevo nombre del producto.
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  /**
   * Obtiene el precio del producto.
   *
   * @return El precio del producto en forma de cadena de caracteres.
   */
  public String getPrecio() {
    return precio;
  }

  /**
   * Establece el precio del producto.
   *
   * @param precio El nuevo precio del producto en forma de cadena de caracteres.
   */
  public void setPrecio(String precio) {
    this.precio = precio;
  }

  /**
   * Obtiene la cantidad disponible del producto en stock.
   *
   * @return La cantidad disponible del producto.
   */
  public Long getCantidad() {
    return cantidad;
  }

  /**
   * Establece la cantidad disponible del producto en stock.
   *
   * @param cantidad La nueva cantidad disponible del producto.
   */
  public void setCantidad(Long cantidad) {
    this.cantidad = cantidad;
  }
}