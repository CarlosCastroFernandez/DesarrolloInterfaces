package app;

import domain.Data;
import domain.item.Item;
import domain.item.ItemDAO;
import domain.pedido.Pedido;
import domain.pedido.PedidoDAO;
import domain.producto.ProductoDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        try {
            ProductoDAO productoDAO = new ProductoDAO();
            productoDAO.saveAll(Data.generateProducts());
            ItemDAO itemDAO = new ItemDAO();

            PedidoDAO pedidoDAO = new PedidoDAO();
            pedidoDAO.saveAll(Data.generatePedidos());
            Item i = new Item(null,3,productoDAO.get(1L),null);
            Item i2 = new Item(null,1,productoDAO.get(2L),null);
            Item i3 = new Item(null,4,productoDAO.get(3L),null);
            Pedido p = pedidoDAO.get(1L);
            p.addItem(i);
            p.addItem(i2);
            p.addItem(i3);
            pedidoDAO.update(p);



        } catch (Exception e) {
            // Do nothing
        }

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/views/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Gestor de pedidos");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}