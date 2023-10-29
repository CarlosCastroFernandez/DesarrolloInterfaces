package com.example.proyectogestionpedidos;import clases.Itempedido;
import clases.Session;
import dao.DBConecction;
import dao.ItemPedidoDAOImp;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VentanaPedido implements Initializable {
    @javafx.fxml.FXML
    private Label labelTitulo;
    @javafx.fxml.FXML
    private TableView <Itempedido>tabla;
    @javafx.fxml.FXML
    private TableColumn <Itempedido,String>cPedido;
    @javafx.fxml.FXML
    private TableColumn <Itempedido,String>cCantidad;
    @javafx.fxml.FXML
    private TableColumn <Itempedido,String>cProducto;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelTitulo.setText("Informacion del Pedido "+Session.getPedido().getCodigo() +" del Cliente "+Session.getUsuario().getEmail());
        ItemPedidoDAOImp dao=new ItemPedidoDAOImp(DBConecction.conexion());
        ArrayList<Itempedido>items=dao.loadItem(Session.getPedido());
        cPedido.setCellValueFactory((fila)->{
            String codigo=fila.getValue().getCodigoPedido();
            return new SimpleStringProperty(codigo);
        });
        cCantidad.setCellValueFactory((fila)->{
            String codigo=String.valueOf(fila.getValue().getCantidad());
            return new SimpleStringProperty(codigo);
        });
        cProducto.setCellValueFactory((fila)->{
            String codigo=fila.getValue().getProducto().get(0).getNombre();
            return new SimpleStringProperty(codigo);
        });
        tabla.getItems().addAll(items);



    }
}
