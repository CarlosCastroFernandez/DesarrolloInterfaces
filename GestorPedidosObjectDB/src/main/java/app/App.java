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
            // Si no hay nada en la base de datos, introduzco datos de prueba
            ProductoDAO productoDAO = new ProductoDAO();
            productoDAO.saveAll(Data.generateProducts());
          /*  ItemDAO itemDAO = new ItemDAO();

            PedidoDAO pedidoDAO = new PedidoDAO();
            pedidoDAO.saveAll(Data.generatePedidos());*/
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