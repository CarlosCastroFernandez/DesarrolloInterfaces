package com.example.proyectogestionpedidos;

import clases.Usuario;
import dao.DBConecction;
import dao.UsuarioDAOImp;
import errores.ContraseñaInvalida;
import errores.UsuarioNoExiste;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
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
    protected static Usuario usuario;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @javafx.fxml.FXML
    public void iniciarSesion(ActionEvent actionEvent) {
        String email=textUsuario.getText();
        String contraseña=password.getText();
        UsuarioDAOImp consulta=new UsuarioDAOImp(DBConecction.conexion());
         usuario=consulta.consultaPersonal(email);
        Alert alerta=new Alert(Alert.AlertType.ERROR);
        if(usuario==null){
            try {
                alerta.setTitle("Error");
                alerta.setHeaderText("Usuario Invalido");
                alerta.setContentText("El usuario no existe");
                alerta.showAndWait();
                throw new UsuarioNoExiste("Usuario Inexistente");


            } catch (UsuarioNoExiste e) {
                throw new RuntimeException(e);
            }
        }else if(!usuario.getContraseña().equals(contraseña)){
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
            Stage nuevaVentana = new Stage();
            nuevaVentana.setTitle("Pedidos");

            // Cargar el archivo FXML de la nueva ventana (reemplaza 'NuevaVentana.fxml' con el nombre de tu archivo FXML)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ventana-usuario.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Crear una nueva escena con el contenido del archivo FXML
            Scene nuevaEscena = new Scene(root);

            // Establecer la escena en la nueva ventana
            nuevaVentana.setScene(nuevaEscena);

            // Mostrar la nueva ventana
            nuevaVentana.show();
            Stage ventanaActual=(Stage)botonIniciar.getScene().getWindow();
            ventanaActual.close();
        }





    }
}
