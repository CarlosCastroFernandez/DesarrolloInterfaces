package com.example.proyectogestionpedidos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


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
        MiStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
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
            Scene scene = new Scene(fxmlLoader.load());
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