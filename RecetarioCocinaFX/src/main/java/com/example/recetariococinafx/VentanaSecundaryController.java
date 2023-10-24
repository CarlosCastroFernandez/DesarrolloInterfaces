package com.example.recetariococinafx;

import clases.Receta;
import clases.Sesion;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class VentanaSecundaryController implements Initializable {
    @javafx.fxml.FXML
    private Label label;
    @javafx.fxml.FXML
    private Button botonVolver;
    @javafx.fxml.FXML
    private Spinner<Double> spinner;

    public void volver(ActionEvent actionEvent) {
        HelloApplication.loadFXML("recetario-cocina.fxml");


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Receta r = Sesion.getRecetaActual();
        label.setText(r.toString());
        spinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 10, 5, 0.25));


    }
}
