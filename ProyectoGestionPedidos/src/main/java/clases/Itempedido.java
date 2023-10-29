package clases;


import java.util.ArrayList;

public class Itempedido {

  private Integer id;
  private String codigoPedido;
  private Integer cantidad;
  private Integer productoId;
  private ArrayList<Producto> producto;

public Itempedido(Integer id,String codigo,Integer cantidad,Integer productoId){
  this.producto=new ArrayList<>();
  this.id=id;
  this.codigoPedido=codigo;
  this.cantidad=cantidad;
  this.productoId=productoId;
}
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getCodigoPedido() {
    return codigoPedido;
  }

  public ArrayList<Producto> getProducto() {
    return producto;
  }

  public void setProducto(ArrayList<Producto> producto) {
    this.producto = producto;
  }

  public void setCodigoPedido(String codigoPedido) {
    this.codigoPedido = codigoPedido;
  }


  public Integer getCantidad() {
    return cantidad;
  }

  public void setCantidad(Integer cantidad) {
    this.cantidad = cantidad;
  }


  public Integer getProductoId() {
    return productoId;
  }

  public void setProductoId(Integer productoId) {
    this.productoId = productoId;
  }

  @Override
  public String toString() {
  String frase="";
  for(int i=0;i<producto.size();i++){
    frase+=codigoPedido+"-----"+cantidad+"-----"+producto.get(i).getNombre()+"\n";
  }
    return frase;
  }
}
