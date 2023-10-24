package com.example.recetariococinafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Stage myStage;
    @Override
    public void start(Stage stage) throws IOException {
        myStage=stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("recetario-cocina.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Recetas!");
        stage.setScene(scene);
        stage.show();
    }

    public static void loadFXML(String fxml){
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        myStage.setScene(scene);

        } catch (IOException e) {
            System.out.println("Error al cargar el FXMl");
            throw new RuntimeException(e);
        }

    }
    public static void ventanaEmergente(String fxml){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.out.println("Error al cargar el FXMl");
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        launch();
    }
}