package clases;


public class Producto {

  private long id;        // Identificador único del producto
  private String nombre;  // Nombre del producto
  private String precio;  // Precio del producto
  private long cantidad;  // Cantidad disponible del producto

  /**
   * Obtiene el identificador único del producto.
   *
   * @return El identificador del producto.
   */
  public long getId() {
    return id;
  }

  /**
   * Establece el identificador único del producto.
   *
   * @param id El nuevo identificador del producto.
   */
  public void setId(long id) {
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
  public long getCantidad() {
    return cantidad;
  }

  /**
   * Establece la cantidad disponible del producto en stock.
   *
   * @param cantidad La nueva cantidad disponible del producto.
   */
  public void setCantidad(long cantidad) {
    this.cantidad = cantidad;
  }
}