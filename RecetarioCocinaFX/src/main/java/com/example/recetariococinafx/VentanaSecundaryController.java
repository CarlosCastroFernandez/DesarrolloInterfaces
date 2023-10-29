package com.example.recetariococinafx;

import clases.Receta;
import clases.Sesion;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class VentanaSecundaryController implements Initializable {
    @javafx.fxml.FXML
    private Label label;
    @javafx.fxml.FXML
    private Button botonVolver;
    @javafx.fxml.FXML
    private Spinner<Double> spinner;
    @javafx.fxml.FXML
    private TextField textNombre;
    @javafx.fxml.FXML
    private ComboBox comboDificultad;
    @javafx.fxml.FXML
    private ComboBox comboTipo;
    @javafx.fxml.FXML
    private Button botonActualizar;
    private Receta r;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         r = Sesion.getRecetaActual();
        System.out.println(r.toString());
        textNombre.setText(r.getNombre());
        comboDificultad.getItems().addAll("Facil","Dificil","Moderado");
        comboDificultad.getSelectionModel().select(r.getDificultad());
        comboTipo.getItems().addAll("Desayuno","Merienda","Almuerzo","Cena");
        comboTipo.getSelectionModel().select(r.getTipo());

        spinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 10, r.getDuracion(), 0.25));



    }

    @javafx.fxml.FXML
    public void volver(ActionEvent actionEvent) {

    }

    @javafx.fxml.FXML
    public void actualizar(ActionEvent actionEvent) {
        r.setDuracion(spinner.getValue().intValue());
        r.setDificultad((String)comboDificultad.getSelectionModel().getSelectedItem());
        r.setTipo((String)comboTipo.getSelectionModel().getSelectedItem());
        r.setNombre(textNombre.getText());
        Sesion.setRecetaActual(r);
        HelloApplication.loadFXML("recetario-cocina.fxml");

    }
}
