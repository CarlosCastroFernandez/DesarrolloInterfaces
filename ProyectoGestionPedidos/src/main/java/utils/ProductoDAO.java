package utils;

import clases.Pedido;
import clases.Producto;

public interface ProductoDAO {
    public Producto loadProducto(Integer id);
}
