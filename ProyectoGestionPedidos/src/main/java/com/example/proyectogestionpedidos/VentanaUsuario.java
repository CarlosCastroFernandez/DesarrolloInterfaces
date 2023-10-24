package com.example.proyectogestionpedidos;

import clases.Pedido;
import dao.DBConecction;
import dao.PedidoDAOImp;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VentanaUsuario implements Initializable {
    @javafx.fxml.FXML
    private Label labelBienvenido;
    @javafx.fxml.FXML
    private TableColumn <Pedido,String>cCodigo;
    @javafx.fxml.FXML
    private TableColumn<Pedido,String> cFecha;
    @javafx.fxml.FXML
    private TableView<Pedido> tabla;
    @javafx.fxml.FXML
    private TableColumn <Pedido,String>cUsuario;
    @javafx.fxml.FXML
    private TableColumn<Pedido,String> cTotal;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelBienvenido.setText("Bienvenido/a "+Login.usuario.getNombre());
        cCodigo.setCellValueFactory((fila->{
            String id=String.valueOf(fila.getValue().getCodigo());
            return new SimpleStringProperty(id);
        }));
        cUsuario.setCellValueFactory((fila->{
            String usuarioId=String.valueOf( fila.getValue().getUsuario().getNombre());
            return new SimpleStringProperty(usuarioId);
        }));
        cFecha.setCellValueFactory((fila->{
            String fecha=fila.getValue().getFecha();
            return new SimpleStringProperty(fecha);
        }));
        cTotal.setCellValueFactory((fila->{
            String total=String.valueOf(fila.getValue().getTotal());
            return new SimpleStringProperty(total);
        }));
        PedidoDAOImp pedidosAll=new PedidoDAOImp(DBConecction.conexion());
        ArrayList<Pedido>salida=pedidosAll.loadAll(Login.usuario.getId());
        System.out.println(salida);

        for(int i=0;i<salida.size();i++){
            tabla.getItems().add(salida.get(i));
        }



    }
}