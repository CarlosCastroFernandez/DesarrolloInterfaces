package com.example.gestionpedidoswithobjectdb;


import clases.Pedido;
import clases.Session;

import dao.ObjectDBUtil;
import dao.PedidoDAOImp;
import dao.UsuarioDAOImp;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.ResourceBundle;

public class VentanaUsuario implements Initializable {
    @javafx.fxml.FXML
    private Label labelBienvenido;
    @javafx.fxml.FXML
    private ListView <Pedido> listaPedidos;
    @javafx.fxml.FXML
    private ComboBox <String>comboBox;
    private ObservableList<Pedido>obs;
    @javafx.fxml.FXML
    private MenuItem salir;
    @javafx.fxml.FXML
    private ImageView imagenAñadir;
    @javafx.fxml.FXML
    private Button botonEliminar;
    @javafx.fxml.FXML
    private Label labelInfo;
    @javafx.fxml.FXML
    private Button botonAñadir;
    @javafx.fxml.FXML
    private Button botodPDF;



    /**
     * Inicializa la ventana y carga datos relacionados con el usuario y sus pedidos en la interfaz.
     *
     * @param url           La ubicación relativa del archivo FXML.
     * @param resourceBundle Recursos específicos de la localización.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Establece un mensaje de bienvenida que incluye el nombre del usuario actual.
        labelBienvenido.setText("Bienvenido/a " + Session.getUsuario().getNombre());
        comboBox.getItems().add("Codigo");
        comboBox.getItems().add("Fecha");
        comboBox.getItems().add("Precio");
        comboBox.getItems().add("Sin Orden");
        comboBox.getSelectionModel().select("Sin Orden");


        // Llena la lista de pedidos con los pedidos consultados.
        UsuarioDAOImp carga=new UsuarioDAOImp(ObjectDBUtil.getEntityManagerFactory().createEntityManager());
        Session.setUsuario(carga.reLoad());
        listaPedidos.getItems().addAll(Session.getUsuario().getPedidos());
            comboBox.valueProperty().addListener((observableValue, s, t1) -> {

                    if(t1.equals("Codigo")){
                        listaPedidos.getItems().clear();
                        Session.getUsuario().getPedidos().sort(Pedido::compareTo);
                        listaPedidos.getItems().addAll(Session.getUsuario().getPedidos());
                    }else if(t1.equals("Fecha")){
                        listaPedidos.getItems().clear();
                        Session.getUsuario().getPedidos().sort(Pedido::compareFecha);
                        listaPedidos.getItems().addAll(Session.getUsuario().getPedidos());
                    }else if(t1.equals("Precio")){
                        listaPedidos.getItems().clear();
                        Session.getUsuario().getPedidos().sort(Pedido::compareTotal);
                        listaPedidos.getItems().addAll(Session.getUsuario().getPedidos());
                    } else{
                        listaPedidos.getItems().clear();
                        listaPedidos.getItems().addAll(Session.getUsuario().getPedidos());
                    }


            });





        listaPedidos.getSelectionModel().getSelectedItems().addListener((ListChangeListener<? super Pedido>) (observable) -> {
            try {
                Pedido pedido = observable.getList().get(0);
                Session.setPedido(pedido);
            } catch (IndexOutOfBoundsException e) {

            }
        });
        listaPedidos.setOnMouseClicked(mouseEvent -> {
            if(Session.getPedido()!=null&&mouseEvent.getClickCount()==2){
                HelloApplication.cambioVentana("ventana-pedido.fxml");
            }
        });

        Long contador=0L;
        for(int i=0;i<Session.getUsuario().getPedidos().size();i++){
            contador+=Session.getUsuario().getPedidos().get(i).getTotal();
        }
        labelInfo.setText("Total a pagar: "+contador+"€");
        labelInfo.setStyle("-fx-font-family: 'Snap ITC'");
    }



    /**
     * Maneja el evento de salida al inicio de sesión, estableciendo tanto el usuario como el pedido como nulos y regresando a la pantalla de inicio de sesión.
     *
     * @param actionEvent El evento que desencadena la acción (por ejemplo, al hacer clic en un botón).
     */
    @javafx.fxml.FXML
    public void salidaInicio(ActionEvent actionEvent) {
        // Establece tanto el usuario como el pedido como nulos y regresa a la pantalla de inicio de sesión.
        Session.setUsuario(null);
        Session.setPedido(null);
        HelloApplication.cambioVentana("login.fxml");
    }


    /**
     * Elimina un pedido tanto en memoria como en la base de datos
     * @param actionEvent
     */
    @javafx.fxml.FXML
    public void eliminarPedido(ActionEvent actionEvent) {
        if(Session.getPedido()==null){
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Error De Selección");
            alerta.setContentText("Por favor seleccione un pedido");
            alerta.showAndWait();
        }else{
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Eliminar");
            alerta.setHeaderText("Seguro que deseas ELIMINAR el pedido "+Session.getPedido().getCodigo());
            alerta.setContentText("Si borras este pedido se borraran todos los productos asignados a él");
            ButtonType tipo=alerta.showAndWait().get();
            if(tipo.getButtonData()== ButtonBar.ButtonData.OK_DONE){
                //BORRAR PEDIDO
                (new PedidoDAOImp()).delete(Session.getPedido());
                Session.setUsuario(new UsuarioDAOImp(ObjectDBUtil.getEntityManagerFactory().createEntityManager()).reLoad());
                listaPedidos.getItems().clear();
                listaPedidos.getItems().addAll(Session.getUsuario().getPedidos());

            }
        }
    }
    /**
     * Añade un pedido tanto en memoria como en la base de datos
     * @param actionEvent
     */

    @javafx.fxml.FXML
    public void añadir(ActionEvent actionEvent) {
        Session.setPedido(null);
        HelloApplication.cambioVentana("añadir-editar-view.fxml");
    }




}