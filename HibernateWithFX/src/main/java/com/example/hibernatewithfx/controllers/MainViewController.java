package com.example.hibernatewithfx.controllers;

import com.example.hibernatewithfx.HelloApplication;
import com.example.hibernatewithfx.Session;
import com.example.hibernatewithfx.domain.juego.GameDao;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    @javafx.fxml.FXML
    private Button botonSalir;
    @javafx.fxml.FXML
    private Label labelInfo;

    @javafx.fxml.FXML
    public void salida(ActionEvent actionEvent) {
        Session.setUsuario(null);
        HelloApplication.cambio("login-view.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelInfo.setText(Session.getUsuario().toString());
        (new GameDao()).getAllFromUser(Session.getUsuario()).forEach(System.out::println);
    }
}
