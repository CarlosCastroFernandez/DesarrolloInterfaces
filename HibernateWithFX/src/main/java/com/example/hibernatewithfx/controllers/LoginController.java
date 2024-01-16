package com.example.hibernatewithfx.controllers;

import com.example.hibernatewithfx.HelloApplication;
import com.example.hibernatewithfx.Session;
import com.example.hibernatewithfx.domain.HibernateUtil;
import com.example.hibernatewithfx.domain.juego.Game;
import com.example.hibernatewithfx.domain.usuario.User;
import com.example.hibernatewithfx.domain.usuario.UserDAO;
import jakarta.persistence.NoResultException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button botonAcceder;
    @FXML
    private Button botonCancelar;
    @FXML
    private Label labelInfo;
    @FXML
    private TextField textUsuario;
    @FXML
    private PasswordField textPassword;


    @FXML
    public void login(ActionEvent actionEvent) {
       String user=textUsuario.getText();
        String password=textPassword.getText();
      ;
        if(user.length()<4||password.length()<4){
            labelInfo.setText("Usuario "+user+" Incorrecto");
            labelInfo.setStyle("-fx-background-color: red");
        }else{
            try{
                UserDAO dao=new UserDAO();
                User usuarioBueno=dao.validateUser(textUsuario.getText(),textPassword.getText());
                if(usuarioBueno==null){
                    labelInfo.setText("Usuario "+user+" Incorrecto");
                    labelInfo.setStyle("-fx-background-color: red");
                }else{
                    labelInfo.setText("Usuario "+user+" Correcto");
                    labelInfo.setStyle("-fx-background-color: green");
                    Session.setUsuario(usuarioBueno);
                    HelloApplication.cambio("main-view.fxml");
                }
            }catch(NoResultException e){
                e.printStackTrace();
                labelInfo.setText("Usuario "+user+" Incorrecto");
                labelInfo.setStyle("-fx-background-color: red");
            }

        }

    }

    @FXML
    public void cancel(ActionEvent actionEvent) {
        textPassword.clear();
        textUsuario.clear();
        labelInfo.setText("");
        labelInfo.setStyle("-fx-background-color: transparent");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(Field f: Game.class.getDeclaredFields()){
            System.out.println(f.getName()+" : "+f.getType().getName()+"@"+f.getAnnotatedType().getType().getTypeName());
        }
    }
}