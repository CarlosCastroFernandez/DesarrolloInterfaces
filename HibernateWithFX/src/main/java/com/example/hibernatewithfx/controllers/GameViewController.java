package com.example.hibernatewithfx.controllers;

import com.example.hibernatewithfx.HelloApplication;
import com.example.hibernatewithfx.Session;
import com.example.hibernatewithfx.domain.HibernateUtil;
import com.example.hibernatewithfx.domain.juego.Game;
import com.example.hibernatewithfx.domain.juego.GameDao;
import com.example.hibernatewithfx.domain.usuario.User;
import com.example.hibernatewithfx.domain.usuario.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import org.hibernate.Transaction;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class GameViewController implements Initializable {
    @javafx.fxml.FXML
    private Label labelInfo;
    @javafx.fxml.FXML
    private MenuItem meuCerrar;
    @javafx.fxml.FXML
    private MenuItem menuVolver;
    @javafx.fxml.FXML
    private Label labelTitulo;
    @javafx.fxml.FXML
    private TextField txtName;
    @javafx.fxml.FXML
    private Spinner<Integer> spinnerAño;
    @javafx.fxml.FXML
    private Spinner<Integer> spinnerJugadores;
    @javafx.fxml.FXML
    private ComboBox comboBoxUsuario;
    @javafx.fxml.FXML
    private Button botonGuardar;
    @javafx.fxml.FXML
    private Button botonBorrar;
    @javafx.fxml.FXML
    private ComboBox<String> comboBoxCategory;
    @javafx.fxml.FXML
    private ComboBox<String> comboBoxPlataforma;
    @javafx.fxml.FXML
    private ComboBox <String>comboBoxEstado;
    @javafx.fxml.FXML
    private ComboBox <String>comboBoxEmpresa;
    @javafx.fxml.FXML
    private ComboBox<String> comboBoxEstadoJuego;
    @javafx.fxml.FXML
    private ComboBox<String> comboBoxFormato;
    @javafx.fxml.FXML
    private ComboBox<String> comboBoxEstadoCaja;
    private final GameDao gameDAO=new GameDao();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelInfo.setText(""+Session.getCurrentGame().getName());
        spinnerAño.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1970,2023, Math.toIntExact(Session.getCurrentGame().getYear()),1));

        spinnerJugadores.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,4, Math.toIntExact(Session.getCurrentGame().getPlayers()),1));
        comboBoxUsuario.setValue(Session.getUsuario());
        comboBoxUsuario.setConverter(new StringConverter<User>() {
            @Override
            public String toString(User user) {
                if(user!=null){
                    return user.getUsername();
                }else{
                    return "";
                }

            }

            @Override
            public User fromString(String s) {
                return null;
            }
        });
        comboBoxUsuario.getItems().addAll(new UserDAO().getAll());

        txtName.setText(Session.getCurrentGame().getName());

        comboBoxPlataforma.setEditable(true);
        ArrayList<String> plataformas= (ArrayList<String>) new GameDao().getPlataform();
        comboBoxPlataforma.getItems().addAll(plataformas);
        comboBoxPlataforma.setValue(Session.getCurrentGame().getPlataform());

        comboBoxFormato.setEditable(true);
        ArrayList<String> formato= (ArrayList<String>) new GameDao().getFormato();
        comboBoxFormato.getItems().addAll(formato);
        comboBoxFormato.setValue(Session.getCurrentGame().getFormat());


        comboBoxEstadoJuego.setEditable(true);
        ArrayList<String> statusJuego= (ArrayList<String>) new GameDao().getEstadoJuego();
        comboBoxEstadoJuego.getItems().addAll(statusJuego);
        comboBoxEstadoJuego.setValue(Session.getCurrentGame().getGameStatus());


        comboBoxEstadoCaja.setEditable(true);
        ArrayList<String> cajas= (ArrayList<String>) new GameDao().getEstadoCaja();
        comboBoxEstadoCaja.getItems().addAll(cajas);
        comboBoxEstadoCaja.setValue(Session.getCurrentGame().getBoxStatus());

        comboBoxEstado.setEditable(true);
        ArrayList<String> estudios= (ArrayList<String>) new GameDao().getEstudio();
        comboBoxEstado.getItems().addAll(estudios);
        comboBoxEstado.setValue(Session.getCurrentGame().getStudy());


        comboBoxEmpresa.setEditable(true);
        ArrayList<String> empresas= (ArrayList<String>) new GameDao().getEmpresa();
        comboBoxEmpresa.getItems().addAll(empresas);
        comboBoxEmpresa.setValue(Session.getCurrentGame().getEnterprise());

        comboBoxCategory.setEditable(true);
        ArrayList<String> categorias= (ArrayList<String>) new GameDao().getCategories();
        comboBoxCategory.getItems().addAll(categorias);
        comboBoxCategory.setValue(Session.getCurrentGame().getCategory());


        labelTitulo.setText(Session.getCurrentGame().getName());
        menuVolver.setOnAction(actionEvent -> {
            Session.setCurrentGame(null);
            HelloApplication.cambio("main-view.fxml");
        });
        meuCerrar.setOnAction(actionEvent -> {
            Session.setCurrentGame(null);
            Session.setUsuario(null);
            HelloApplication.cambio("login-view.fxml");

        });


    }

    @javafx.fxml.FXML
    public void save(ActionEvent actionEvent) {
        Game g=Session.getCurrentGame();
        if(txtName.getText().length()>1) g.setName(txtName.getText());
        if(comboBoxCategory.getValue().length()>1) g.setCategory(comboBoxCategory.getValue());
        if(comboBoxEmpresa.getValue().length()>1) g.setEnterprise(comboBoxEmpresa.getValue());
        if(comboBoxEstado.getValue().length()>1) g.setStudy(comboBoxEstado.getValue());
        if(comboBoxEstadoJuego.getValue().length()>1) g.setGameStatus(comboBoxEstadoJuego.getValue());
        if(comboBoxEstadoCaja.getValue().length()>1) g.setBoxStatus(comboBoxEstadoCaja.getValue());
        if(comboBoxFormato.getValue().length()>1) g.setFormat(comboBoxFormato.getValue());
        if(comboBoxPlataforma.getValue().length()>1) g.setPlataform(comboBoxPlataforma.getValue());
        g.setYear(Long.valueOf( spinnerAño.getValue()));

        g.setPlayers(Long.valueOf(spinnerJugadores.getValue()));
        if(g.getId()!=null){
            gameDAO.update(g);
        }else{
            gameDAO.save(g);
        }

        Session.setCurrentGame(null);
        HelloApplication.cambio("main-view.fxml");
    }

    @javafx.fxml.FXML
    public void delete(ActionEvent actionEvent) {
        Alert allerta=new Alert(Alert.AlertType.CONFIRMATION);
        allerta.setContentText("¿Deseas borrar "+Session.getCurrentGame().getName()+" del listado?");
        ButtonType result= allerta.showAndWait().get();
        if(result.getButtonData()== ButtonBar.ButtonData.OK_DONE){
            gameDAO.delete(Session.getCurrentGame());
            HelloApplication.cambio("main-view.fxml");
        }

    }
}
