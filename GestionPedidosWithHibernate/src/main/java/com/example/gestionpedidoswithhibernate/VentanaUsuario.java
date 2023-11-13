package com.example.gestionpedidoswithhibernate;

import clases.Itempedido;
import clases.Pedido;
import clases.Session;
import dao.HibernateUtils;
import dao.PedidoDAOImp;
import dao.UsuarioDAOImp;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
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
    @javafx.fxml.FXML
    private MenuItem salir;
    @javafx.fxml.FXML
    private ImageView imagenAñadir;
    @javafx.fxml.FXML
    private Button botonAsignar;
    @javafx.fxml.FXML
    private Button botonEliminar;
    @javafx.fxml.FXML
    private Label labelInfo;


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

        // Consulta todos los pedidos relacionados con el usuario y los carga en el ComboBox.



        // Configura el ComboBox para mostrar códigos de pedido en la lista desplegable.
        comboBox.setConverter(new StringConverter<Pedido>() {
            @Override
            public String toString(Pedido pedido) {
                if (pedido != null) {
                    return pedido.getCodigo();
                } else {
                    return null;
                }
            }

            @Override
            public Pedido fromString(String s) {
                return null;
            }
        });

        // Llena la lista de pedidos con los pedidos consultados.
            UsuarioDAOImp carga=new UsuarioDAOImp(HibernateUtils.getSession());
            Session.setUsuario(carga.reLoad());
            listaPedidos.getItems().addAll(Session.getUsuario().getPedidos());


        // Configura el ComboBox y la lista de pedidos para interactuar y mantener el pedido seleccionado.
        obs = FXCollections.observableArrayList();
        obs.addAll(listaPedidos.getItems());
        comboBox.setItems(obs);
        comboBox.valueProperty().addListener((observableValue, o, t1) -> {
            listaPedidos.getItems().clear();
            listaPedidos.getItems().add((Pedido) t1);
        });
        listaPedidos.getSelectionModel().getSelectedItems().addListener((ListChangeListener<? super Pedido>) (observable) -> {
            try {
                Pedido pedido = observable.getList().get(0);
                Session.setPedido(pedido);
            } catch (IndexOutOfBoundsException e) {
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
     * Maneja el evento de listar todos los pedidos, rellenando la lista de pedidos con todos los pedidos disponibles.
     *
     * @param actionEvent El evento que desencadena la acción (por ejemplo, al hacer clic en un botón).
     */
    @javafx.fxml.FXML
    public void listarAllPedidos(ActionEvent actionEvent) {
        listaPedidos.getItems().clear();
        listaPedidos.getItems().addAll(comboBox.getItems());
    }

    /**
     * Maneja el evento de ver un pedido seleccionado, redirigiendo a la ventana de detalles del pedido.
     * Muestra una alerta si no se ha seleccionado ningún pedido.
     *
     * @param actionEvent El evento que desencadena la acción (por ejemplo, al hacer clic en un botón).
     */
    @javafx.fxml.FXML
    public void verPedido(ActionEvent actionEvent) {
        if (Session.getPedido() != null) {
            HelloApplication.cambioVentana("ventana-pedido.fxml");
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Error De Selección");
            alerta.setContentText("Por favor seleccione un pedido");
            alerta.showAndWait();
        }
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

    @javafx.fxml.FXML
    public void añadir(Event event) {
        Session.setPedido(null);
        HelloApplication.cambioVentana("añadir-editar-view.fxml");
    }

    @javafx.fxml.FXML
    public void asignaProductos(ActionEvent actionEvent) {
        if (Session.getPedido() != null) {
            HelloApplication.cambioVentana("añadir-editar-view.fxml");
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Error De Selección");
            alerta.setContentText("Por favor seleccione un pedido");
            alerta.showAndWait();
        }
    }

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
                (new PedidoDAOImp()).delete(Session.getPedido());
                Session.setUsuario(new UsuarioDAOImp(HibernateUtils.getSession()).reLoad());
                listaPedidos.getItems().clear();
                listaPedidos.getItems().addAll(Session.getUsuario().getPedidos());

            }
        }
    }
}