package com.example.recetariococinafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        var ventana = new FileChooser();
       File ruta= ventana.showOpenDialog(null);
       System.out.println(ruta.getParent()+File.separator+ruta.getName());
    }
}