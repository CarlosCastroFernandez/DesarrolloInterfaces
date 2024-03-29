package com.example.gestionpedidoswithobjectdb;

import clases.Producto;
import clases.Usuario;
import dao.ObjectDBUtil;
import dao.PedidoDAOImp;
import dao.ProductoDAOImp;
import dao.UsuarioDAOImp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Clase principal de la aplicación que extiende la clase Application de JavaFX.
 */
public class HelloApplication extends Application {


    private static Stage MiStage; // Objeto de la ventana de la aplicación

    /**
     * Método principal para iniciar la aplicación.
     *
     * @param stage El objeto Stage principal de la aplicación.
     * @throws IOException Si se produce un error durante la carga de la ventana.
     */
    @Override
    public void start(Stage stage) throws IOException {
        try{
            List<Producto> p=(new ProductoDAOImp()).getAllProductsJDBC();
            (new ProductoDAOImp()).saveAll(p);
            List<Usuario>usuarios=new ArrayList<>();
            usuarios.add(new Usuario(1,"Carlos","bubachico@gmail.com","1234",new ArrayList<>()));
            usuarios.add(new Usuario(2,"Naomi","naomi@gmail.com","1234",new ArrayList<>()));
            usuarios.add(new Usuario(3,"Pepe","pepe@gmail.com","1234",new ArrayList<>()));
            usuarios.add(new Usuario(4,"Alex","alex@gmail.com","1234",new ArrayList<>()));
            (new UsuarioDAOImp(ObjectDBUtil.getEntityManagerFactory().createEntityManager())).saveAll(usuarios);


        }catch (Exception e){
            System.out.println(new UsuarioDAOImp(ObjectDBUtil.getEntityManagerFactory().createEntityManager()).countUser());
        }
        MiStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),700,600);
        stage.setTitle("Gestión de Pedidos");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Cambia la ventana actual de la aplicación a otra especificada por su ruta.
     *
     * @param ruta La ruta del archivo FXML de la ventana a la que se desea cambiar.
     */
    public static void cambioVentana(String ruta) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(ruta));
            Scene scene = new Scene(fxmlLoader.load(),700,600);
            MiStage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método principal que inicia la aplicación.
     *
     * @param args Argumentos de línea de comandos (no se utilizan en esta aplicación JavaFX).
     */
    public static void main(String[] args) {
        launch();
    }
}