package domain;

import domain.pedido.Pedido;
import domain.producto.Producto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Data {

    public static List<Producto> generateProducts(){
        List<Producto> listaProductos = new ArrayList<>();
        listaProductos.add(new Producto(1L, "Laptop", 899.99));
        listaProductos.add(new Producto(2L, "Teléfono móvil", 499.99));
        listaProductos.add(new Producto(3L, "Televisor 4K", 799.99));
        listaProductos.add(new Producto(4L, "Auriculares inalámbricos", 129.99));
        listaProductos.add(new Producto(5L, "Teclado mecánico", 79.99));
        listaProductos.add(new Producto(6L, "Mouse gaming", 49.99));
        listaProductos.add(new Producto(7L, "Cámara digital", 349.99));
        listaProductos.add(new Producto(8L, "Impresora multifunción", 199.99));
        listaProductos.add(new Producto(9L, "Altavoces Bluetooth", 89.99));
        listaProductos.add(new Producto(10L, "Smartwatch", 149.99));
        return listaProductos;
    }

    public static List<Pedido> generatePedidos(){
        ArrayList<Pedido> listaPedidos = new ArrayList<>();
        listaPedidos.add(new Pedido(1L,"P001", generateDate(), new ArrayList<>()));
        listaPedidos.add(new Pedido(2L,"P002", generateDate(), new ArrayList<>()));
        listaPedidos.add(new Pedido(3L,"P003", generateDate(), new ArrayList<>()));
        listaPedidos.add(new Pedido(4L,"P004", generateDate(), new ArrayList<>()));
        return listaPedidos;
    }


    private static Date generateDate() {
        long now = System.currentTimeMillis();
        long randomDate = ThreadLocalRandom.current().nextLong(now - 2592000000L, now + 2592000000L);
        return new Date(randomDate);
    }
}
