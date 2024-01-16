package com.example.gestionpedidoswithobjectdb;

import clases.Itempedido;
import clases.Session;
import dao.ItemPedidoDAOImp;
import dao.PedidoDAOImp;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
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
    private Label labelCliente;
    @javafx.fxml.FXML
    private MenuItem salir;
    @javafx.fxml.FXML
    private Button botonEditar;
    private Itempedido items;
    @javafx.fxml.FXML
    private Button botonBorrar;
    private List<Itempedido>itemsAll;
    @javafx.fxml.FXML
    private Button botonAñadir;


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
      // ItemPedidoDAOImp dao = new ItemPedidoDAOImp();


             itemsAll= Session.getPedido().getItems();

        // Configura las celdas de la tabla para mostrar información de los elementos del pedido.
        cPedido.setCellValueFactory((fila) -> {
            String codigo = fila.getValue().getPedido().getCodigo();
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
        tabla.getSelectionModel().selectedItemProperty().addListener((observableValue, itempedido, t1) -> {
            items=t1;
        });

        // Agrega los elementos a la tabla.
        tabla.getItems().addAll(itemsAll);
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
    /**
     * Maneja el evento de editae un pedido.
     *
     * @param actionEvent El evento que desencadena la acción (por ejemplo, al hacer clic en un botón).
     */

    @javafx.fxml.FXML
    public void editar(ActionEvent actionEvent) {
        if(items!=null){
            Session.setItem(items);
            HelloApplication.cambioVentana("añadir-editar-view.fxml");
        }else{
            Alert alerta=new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText("Seleccion");
            alerta.setContentText("Selecciona un pedido de la tabla para editarlo");
            alerta.showAndWait();
        }
    }
    /**
     * Maneja el evento de borrar un pedido.
     *
     * @param actionEvent El evento que desencadena la acción (por ejemplo, al hacer clic en un botón).
     */

    @javafx.fxml.FXML
    public void borrarItem(ActionEvent actionEvent) {
        try{
        if(items!=null&&tabla.getItems().size()>=1){
            Alert alerta=new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("ATENCION!");
            alerta.setHeaderText("¿Deseas Eliminar el producto?");
            ButtonType tipo=alerta.showAndWait().get();
            if(tipo.getButtonData()== ButtonBar.ButtonData.OK_DONE){
                Itempedido i=null;
                System.out.println(itemsAll);
                i=  Session.getPedido().getItems().remove(Session.getPedido().getItems().indexOf(items));
                System.out.println(i);
                (new ItemPedidoDAOImp()).delete(i);
                tabla.getItems().clear();
                tabla.getItems().addAll(itemsAll);
            }
            if(tabla.getItems().isEmpty()){
                Alert alerta2=new Alert(Alert.AlertType.INFORMATION);
                alerta2.setTitle("ATENCION!");
                alerta2.setHeaderText("¿Deseas Eliminar tambien el pedido "+Session.getPedido().getCodigo());
                    ButtonType tipo2=alerta2.showAndWait().get();
                    if(tipo2.getButtonData()== ButtonBar.ButtonData.OK_DONE){
                        (new PedidoDAOImp()).delete(Session.getPedido());
                        tabla.getItems().clear();
                        tabla.getItems().addAll(itemsAll);
                    }


                }

            }
        }catch(Exception e){

        }

    }
    /**
     * Maneja el evento de añadir un pedido.
     *
     * @param actionEvent El evento que desencadena la acción (por ejemplo, al hacer clic en un botón).
     */

    @javafx.fxml.FXML
    public void añadirProducto(ActionEvent actionEvent) {
        Session.setItem(null);
        HelloApplication.cambioVentana("añadir-editar-view.fxml");
    }
    /**
     * Vuelve a la ventana usuario.
     *
     * @param actionEvent El evento que desencadena la acción (por ejemplo, al hacer clic en un botón).
     */
@javafx.fxml.FXML
    public void volver(ActionEvent actionEvent) {
        Session.setPedido(null);
        HelloApplication.cambioVentana("ventana-usuario.fxml");
    }
}