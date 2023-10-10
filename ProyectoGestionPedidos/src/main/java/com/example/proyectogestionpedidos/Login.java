package com.example.proyectogestionpedidos;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {
    @javafx.fxml.FXML
    private Label labelTitulo;
    @javafx.fxml.FXML
    private Label labelUsuario;
    @javafx.fxml.FXML
    private TextField textUsuario;
    @javafx.fxml.FXML
    private Label labelPassword;
    @javafx.fxml.FXML
    private TextField textPassword;
    @javafx.fxml.FXML
    private Button botonIniciar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @javafx.fxml.FXML
    public void iniciarSesion(ActionEvent actionEvent) {

    }
}
