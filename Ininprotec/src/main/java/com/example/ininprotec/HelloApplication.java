package com.example.ininprotec;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class HelloApplication extends Application {
    private static Stage miStage; //

    @Override
    public void start(Stage stage) throws IOException {
        InputStream fontStream = HelloApplication.class.getClassLoader().getResourceAsStream("fonts/airstrike.ttf");
        if (fontStream == null) {
            System.out.println("El archivo de fuente no se encontró.");
        } else {
            Font font = Font.loadFont(fontStream, 36);
            if (font != null) {
                System.out.println("Fuente cargada: " + font.getName());
            } else {
                System.out.println("No se pudo cargar la fuente.");
            }
        }

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("logIn-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 750);
        stage.setTitle("ININPROTEC");
        stage.setScene(scene);
        stage.getIcons().add(new Image(HelloApplication.class.getClassLoader().getResource("imagenes/escudo.png").toExternalForm()));
        miStage = stage;
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


}