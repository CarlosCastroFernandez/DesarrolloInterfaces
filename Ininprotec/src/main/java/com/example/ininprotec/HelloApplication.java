package com.example.ininprotec;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Stage miStage; //
    @Override
    public void start(Stage stage) throws IOException {
        miStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("logIn-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 750);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    public static void cambioVentana(String ruta) {
        if(ruta.equals("creacionCurso-view.fxml")){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(ruta));
                Scene scene = new Scene(fxmlLoader.load(),1200,750);
                miStage.setScene(scene);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(ruta));
                Scene scene = new Scene(fxmlLoader.load(),1200,750);
                miStage.setScene(scene);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static void main(String[] args) {
        launch();
    }
}