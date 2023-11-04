package com.example.proyectogestionpedidos;

import clases.Itempedido;
import clases.Session;
import dao.DBConecction;
import dao.ItemPedidoDAOImp;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VentanaPedido implements Initializable {
    @javafx.fxml.FXML
    private Label labelTitulo;
    @javafx.fxml.FXML
    private TableView<Itempedido> tabla;
    @javafx.fxml.FXML
    private TableColumn<Itempedido, String> cPedido;
    @javafx.fxml.FXML
    private TableColumn<Itempedido, String> cCantidad;
    @javafx.fxml.FXML
    private TableColumn<Itempedido, String> cProducto;
    @javafx.fxml.FXML
    private Button botonVolver;
    @javafx.fxml.FXML
    private Label labelCliente;
    @javafx.fxml.FXML
    private MenuItem salir;

    @Override
    /**
     * Inicializa la ventana y carga datos relacionados con el pedido actual y su contenido en la interfaz.
     *
     * @param url           La ubicación relativa del archivo FXML.
     * @param resourceBundle Recursos específicos de la localización.
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Establece el título de la ventana con información del pedido actual.
        labelTitulo.setText("Información del Pedido: " + Session.getPedido().getCodigo());

        // Muestra el cliente asociado al pedido.
        labelCliente.setText("Cliente: " + Session.getUsuario().getEmail());

        // Carga los elementos del pedido y los muestra en una tabla.
        ItemPedidoDAOImp dao = new ItemPedidoDAOImp(DBConecction.conexion());
        ArrayList<Itempedido> items = dao.loadItem(Session.getPedido());

        // Configura las celdas de la tabla para mostrar información de los elementos del pedido.
        cPedido.setCellValueFactory((fila) -> {
            String codigo = fila.getValue().getCodigoPedido();
            return new SimpleStringProperty(codigo);
        });

        cCantidad.setCellValueFactory((fila) -> {
            String codigo = String.valueOf(fila.getValue().getCantidad());
            return new SimpleStringProperty(codigo);
        });

        cProducto.setCellValueFactory((fila) -> {
            String codigo = fila.getValue().getProducto().getNombre();
            return new SimpleStringProperty(codigo);
        });

        // Agrega los elementos a la tabla.
        tabla.getItems().addAll(items);
    }

    /**
     * Maneja el evento de volver atrás, estableciendo el pedido actual como nulo y regresando a la ventana de usuario.
     *
     * @param actionEvent El evento que desencadena la acción (por ejemplo, al hacer clic en un botón).
     */
    @javafx.fxml.FXML
    public void volver(ActionEvent actionEvent) {
        // Establece el pedido actual como nulo y regresa a la ventana de usuario.
        Session.setPedido(null);
        HelloApplication.cambioVentana("ventana-usuario.fxml");
    }

    /**
     * Maneja el evento de salida al inicio de sesión, estableciendo tanto el pedido como el usuario como nulos y volviendo a la pantalla de inicio de sesión.
     *
     * @param actionEvent El evento que desencadena la acción (por ejemplo, al hacer clic en un botón).
     */
    @javafx.fxml.FXML
    public void salidaInicio(ActionEvent actionEvent) {
        // Establece tanto el pedido como el usuario como nulos y regresa a la pantalla de inicio de sesión.
        Session.setPedido(null);
        Session.setUsuario(null);
        HelloApplication.cambioVentana("login.fxml");
    }
}