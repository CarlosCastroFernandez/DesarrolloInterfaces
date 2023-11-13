package com.example.hibernatewithfx.controllers;

import com.example.hibernatewithfx.HelloApplication;
import com.example.hibernatewithfx.Session;
import com.example.hibernatewithfx.domain.juego.Game;
import com.example.hibernatewithfx.domain.juego.GameDao;
import com.example.hibernatewithfx.domain.usuario.UserDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    @javafx.fxml.FXML
    private Label labelInfo;
    @javafx.fxml.FXML
    private TableView <Game>tabla;
    @javafx.fxml.FXML
    private TableColumn <Game,String>cNombre;
    @javafx.fxml.FXML
    private TableColumn <Game,String>cPlataforma;
    @javafx.fxml.FXML
    private TableColumn <Game,String>cCategoria;
    @javafx.fxml.FXML
    private TableColumn <Game,String>cAño;
    @javafx.fxml.FXML
    private TableColumn <Game,String>cEstudio;
    @javafx.fxml.FXML
    private TableColumn<Game,String> cFormato;
    @javafx.fxml.FXML
    private MenuItem salir;
    @javafx.fxml.FXML
    private Label labelTotal;
    @javafx.fxml.FXML
    private Button botonAñadir;

    @Deprecated
    public void salida(ActionEvent actionEvent) {
        Session.setUsuario(null);
        HelloApplication.cambio("login-view.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelInfo.setText("Bienvenido "+Session.getUsuario().getUsername());
        (Session.getUsuario()).getGames().forEach(System.out::println);
        cNombre.setCellValueFactory((fila)->{
            String nombre=fila.getValue().getName();
            return new SimpleStringProperty(nombre);
        });
        cAño.setCellValueFactory((fila)->{
            String nombre=String.valueOf(fila.getValue().getYear());
            return new SimpleStringProperty(nombre);
        });
        cCategoria.setCellValueFactory((fila)->{
            String nombre=fila.getValue().getCategory();
            return new SimpleStringProperty(nombre);
        });
        cFormato.setCellValueFactory((fila)->{
            String nombre=fila.getValue().getFormat();
            return new SimpleStringProperty(nombre);
        });
        cEstudio.setCellValueFactory((fila)->{
            String nombre=fila.getValue().getStudy();
            return new SimpleStringProperty(nombre);
        });
            cPlataforma.setCellValueFactory((fila)->{
            String nombre=fila.getValue().getPlataform();
            return new SimpleStringProperty(nombre);
        });

        Session.setUsuario(new UserDAO().get(Session.getUsuario().getId()));
            tabla.getItems().addAll(Session.getUsuario().getGames());
            salir.setOnAction(actionEvent -> {
                Session.setUsuario(null);
                HelloApplication.cambio("login-view.fxml");
            });
            labelTotal.setText(""+Session.getUsuario().getGamesQuantity()+" Juegos");
            tabla.getSelectionModel().selectedItemProperty().addListener((observableValue, game, t1) -> {
                Session.setCurrentGame(t1);
                HelloApplication.cambio("game-view.fxml");
            });
    }

    @javafx.fxml.FXML
    public void añadir(ActionEvent actionEvent) {
        Game g=new Game();
        g.setUser(Session.getUsuario());
        Session.setCurrentGame(g);
        HelloApplication.cambio("game-view.fxml");
    }
}
