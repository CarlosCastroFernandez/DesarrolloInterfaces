package controllers;

import domain.item.Item;
import domain.item.ItemDAO;
import domain.pedido.Pedido;
import domain.pedido.PedidoDAO;
import domain.producto.Producto;
import domain.producto.ProductoDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Label info;
    @FXML
    private TableView<Pedido> tablaPedido;
    @FXML
    private TableColumn<Pedido,String> cCodigo;
    @FXML
    private TableColumn<Pedido,String> cFecha;
    @FXML
    private TableColumn<Pedido,String> cCantidad;
    @FXML
    private TableColumn<Pedido,String> cTotal;
    @FXML
    private Button btnNuevoPedido;
    @FXML
    private TableView<Item> tablaItems;
    @FXML
    private TableColumn<Item,String> cProducto;
    @FXML
    private Label lblCodigo;
    @FXML
    private Label lblTotal;
    @FXML
    private ChoiceBox<Producto> comboProducto;
    @FXML
    private Spinner<Integer> spinnerCantidad;
    @FXML
    private Button btnAñadir;
    @FXML
    private Button btnBorrar;
    @FXML
    private TableColumn<Item,String> cItemCantidad;
    @FXML
    private TextField txtCodigo;
    @FXML
    private Button btnBorrarPedido;

    private ArrayList<Pedido> pedidos = new ArrayList<>(0);
    private ArrayList<Producto> productos = new ArrayList<>(0);
    private Pedido pedidoActual = null;
    private Item itemActual = null;

    private final PedidoDAO pedidoDAO = new PedidoDAO();
    private final ProductoDAO productoDAO = new ProductoDAO();
    private final ItemDAO itemDAO = new ItemDAO();
    @FXML
    private Button btnRefrescar;

    ObservableList<Pedido> observableTablaPedidos = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        cCodigo.setCellValueFactory( (fila)->{
            var salida = fila.getValue().getCodigo();
            return new SimpleStringProperty(salida);
        });
        cFecha.setCellValueFactory( (fila)->{
            var salida = fila.getValue().getFecha().toString();
            return new SimpleStringProperty(salida);
        });
        cCantidad.setCellValueFactory( (fila)->{
            var salida = fila.getValue().getItems().size()+"";
            return new SimpleStringProperty(salida);
        });
        cTotal.setCellValueFactory( (fila)->{
            return new SimpleStringProperty(fila.getValue().getCalculoTotal()+"€");
        });
        cProducto.setCellValueFactory((fila)->{
            var salida = fila.getValue().getProducto().getNombre();
            return new SimpleStringProperty(salida);
        });
        cItemCantidad.setCellValueFactory((fila)->{
            var salida = fila.getValue().getCantidad()+"";
            return new SimpleStringProperty(salida);
        });

        comboProducto.setConverter(new StringConverter<Producto>() {
            @Override
            public String toString(Producto producto) {
                return (producto!=null)?producto.getNombre():"";
            }

            @Override
            public Producto fromString(String s) {
                return null;
            }
        });

        spinnerCantidad.setValueFactory( new SpinnerValueFactory.IntegerSpinnerValueFactory(1,99,1,1));

        tablaPedido.getSelectionModel().selectedItemProperty().addListener((observableValue, pedido, t1) -> {
                if(t1!=null) {
                    pedidoActual = t1;
                    actualizarTablaItems();
                }
        });

        tablaItems.getSelectionModel().selectedItemProperty().addListener((observableValue, item, t1) -> {
                if(t1!=null) {
                    itemActual = t1;
                    actualizarFormularioItem();
                }
        });


        actualizarDatosTablaPedidos();

        productos = (ArrayList<Producto>) productoDAO.getAll();
        comboProducto.getItems().addAll(productos);
    }

    @FXML
    private void actualizarDatosTablaPedidos() {
        observableTablaPedidos.clear();
        tablaPedido.getItems().clear();
        observableTablaPedidos.addAll((ArrayList<Pedido>) pedidoDAO.getAll());
        tablaPedido.setItems(observableTablaPedidos);
    }

    private void actualizarFormularioItem() {
        info.setText( itemActual.toString() );
        comboProducto.setValue(itemActual.getProducto());
        spinnerCantidad.getValueFactory().setValue(itemActual.getCantidad());
    }

    private void actualizarTablaItems() {
        info.setText( pedidoActual.toString() );
        lblCodigo.setText(pedidoActual.getCodigo());
        txtCodigo.setText(pedidoActual.getCodigo());
        lblTotal.setText(pedidoActual.getCalculoTotal()+"€");

        ObservableList<Item> observableTablaItems = FXCollections.observableArrayList();
        observableTablaItems.addAll(pedidoActual.getItems());
        tablaItems.setItems(observableTablaItems);

    }

    @FXML
    public void nuevoPedidoVacio(ActionEvent actionEvent) {

        if( pedidoDAO.getByCodigo( txtCodigo.getText() ) != null ){
            info.setText("No se permiten códigos duplicados");
        }else {
            Pedido p = new Pedido(txtCodigo.getText(),new Date());
            pedidoActual = pedidoDAO.save(p);
            info.setText("created!");
            txtCodigo.setText("");
        }
        actualizarDatosTablaPedidos();
    }

    @FXML
    public void borrarPedido(ActionEvent actionEvent) {
        observableTablaPedidos.clear();
    }


    @FXML
    public void nuevoItem(ActionEvent actionEvent) {
        Item item = new Item();
        item.setCantidad(spinnerCantidad.getValue());
        item.setProducto( comboProducto.getValue() );
        pedidoActual.addItem(item);
        pedidoDAO.update(pedidoActual);

        actualizarDatosTablaPedidos();
        actualizarTablaItems();

        txtCodigo.setText("");

    }

    @FXML
    public void borrarItem(ActionEvent actionEvent) {
        if(itemActual!=null){
            itemDAO.remove(itemActual);
            itemActual=null;

            pedidoActual=pedidoDAO.get(pedidoActual.getId());

            actualizarDatosTablaPedidos();
            actualizarTablaItems();
        }
    }
}