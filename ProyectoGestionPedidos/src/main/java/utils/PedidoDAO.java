package utils;

import clases.Pedido;

import java.util.ArrayList;

public interface PedidoDAO {
    public ArrayList<Pedido>loadAll(Integer id);
}
