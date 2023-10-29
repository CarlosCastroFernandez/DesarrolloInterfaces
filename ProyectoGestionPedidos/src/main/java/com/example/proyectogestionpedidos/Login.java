package com.example.proyectogestionpedidos;

import clases.Session;
import dao.DBConecction;
import dao.UsuarioDAOImp;
import errores.ContraseñaInvalida;
import errores.UsuarioNoExiste;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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
    private Button botonIniciar;
    @javafx.fxml.FXML
    private PasswordField password;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @javafx.fxml.FXML
    public void iniciarSesion(ActionEvent actionEvent) {
        String email=textUsuario.getText();
        String contraseña=password.getText();
        UsuarioDAOImp consulta=new UsuarioDAOImp(DBConecction.conexion());
         Session.setUsuario(consulta.consultaPersonal(email));
        Alert alerta=new Alert(Alert.AlertType.ERROR);
        if(Session.getUsuario()==null){
            try {
                alerta.setTitle("Error");
                alerta.setHeaderText("Usuario Invalido");
                alerta.setContentText("El usuario no existe");
                alerta.showAndWait();
                throw new UsuarioNoExiste("Usuario Inexistente");


            } catch (UsuarioNoExiste e) {
                throw new RuntimeException(e);
            }
        }else if(!Session.getUsuario().getContraseña().equals(contraseña)){
            try {
                alerta.setTitle("Error");
                alerta.setHeaderText("Contraseña Invalido");
                alerta.setContentText("Contraseña no existe para este usuario");
                alerta.showAndWait();
                throw new ContraseñaInvalida("La contraseña es invalida");
            } catch (ContraseñaInvalida e) {
                throw new RuntimeException(e);
            }
        }else{
            HelloApplication.cambioVentana("ventana-usuario.fxml");
        }





    }
}
