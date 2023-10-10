package com.example.bienvenidofx;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SecondaryView implements Initializable
{

    @javafx.fxml.FXML
    private Button saludar;
    @javafx.fxml.FXML
    private Button despedir;
    @javafx.fxml.FXML
    private Label label;
    @javafx.fxml.FXML
    private TextField txtNombre;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @javafx.fxml.FXML
    public void saludar(ActionEvent actionEvent) {
        label.setText("Hola "+txtNombre.getText());
        var alerta=new Alert(Alert.AlertType.INFORMATION);
        alerta.setContentText("Hola "+txtNombre.getText());
        alerta.showAndWait();
    }

    @javafx.fxml.FXML
    public void despedir(ActionEvent actionEvent) {
        label.setText("Adios "+txtNombre.getText());
    }
}