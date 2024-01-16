package controllers;

import domain.item.Item;
import domain.pedido.Pedido;
import domain.pedido.PedidoDAO;
import domain.producto.ProductoDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.BinaryOperator;

public class MainController implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    private TableView<Pedido> table;
    @FXML
    private TableColumn<Pedido,String> cCodigo;
    @FXML
    private TableColumn<Pedido,String> cFecha;
    @FXML
    private TableColumn<Pedido,String> cCantidad;
    @FXML
    private TableColumn<Pedido,String> cTotal;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PedidoDAO pedidoDAO = new PedidoDAO();

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
            var items = fila.getValue().getItems();
            var salida = 0.0;
            for(Item i : items) salida+=i.getProducto().getPrecio()*i.getCantidad();
            return new SimpleStringProperty(salida+"€");
        });

        welcomeText.setText( pedidoDAO.getAll().toString() );
        table.getItems().addAll( pedidoDAO.getAll());

    }
}