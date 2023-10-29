package com.example.proyectogestionpedidos;

import clases.Pedido;
import clases.Session;
import dao.DBConecction;
import dao.PedidoDAOImp;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VentanaUsuario implements Initializable {
    @javafx.fxml.FXML
    private Label labelBienvenido;
    @javafx.fxml.FXML
    private ListView <Pedido> listaPedidos;
    @javafx.fxml.FXML
    private ComboBox comboBox;
    private ObservableList<Pedido>obs;
    @javafx.fxml.FXML
    private Button botonListar;
    @javafx.fxml.FXML
    private Button botonVer;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelBienvenido.setText("Bienvenido/a "+ Session.getUsuario().getNombre());

        PedidoDAOImp pedidosAll=new PedidoDAOImp(DBConecction.conexion());
        ArrayList<Pedido>salida=pedidosAll.loadAll(Session.getUsuario().getId());
        Session.getUsuario().getPedidos().addAll(salida);
        System.out.println(Session.getUsuario().getPedidos());
        comboBox.setConverter(new StringConverter<Pedido>() {
            @Override
            public String toString(Pedido pedido) {
                if(pedido!=null){
                    return pedido.getCodigo();
                }else {
                    return null;
                }
            }

            @Override
            public Pedido fromString(String s) {
                return null;
            }
        });

        for(int i=0;i<salida.size();i++){
            listaPedidos.getItems().add(salida.get(i));
        }
        obs= FXCollections.observableArrayList();
        obs.addAll(listaPedidos.getItems());
        comboBox.setItems(obs);
        comboBox.valueProperty().addListener((observableValue, o, t1) -> {
                listaPedidos.getItems().clear();
                listaPedidos.getItems().add((Pedido)t1);
        });
        listaPedidos.getSelectionModel().getSelectedItems().addListener((ListChangeListener<? super Pedido>) (observable) ->{
            try{
                Pedido pedido=observable.getList().get(0);
                Session.setPedido(pedido);

            }catch (IndexOutOfBoundsException e){

            }
        });

    }

    @javafx.fxml.FXML
    public void listarAllPedidos(ActionEvent actionEvent) {
            listaPedidos.getItems().clear();
            listaPedidos.getItems().addAll(comboBox.getItems());
    }

    @javafx.fxml.FXML
    public void verPedido(ActionEvent actionEvent) {
        if(Session.getPedido()!=null){
            HelloApplication.cambioVentana("ventana-pedido.fxml");
        }else{
            Alert alerta= new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Error De Seleccion");
            alerta.setContentText("Por favor seleccione un pedido");
            alerta.showAndWait();
        }

    }
}