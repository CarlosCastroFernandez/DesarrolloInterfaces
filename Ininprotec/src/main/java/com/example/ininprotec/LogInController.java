package com.example.ininprotec;

import Util.HashPassword;
import Util.Utilidad;
import clase.PersonalIIP;
import implement.PersonalIIPDAOImplement;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class LogInController implements Initializable {
    @javafx.fxml.FXML
    private TextField textCorreo;
    @javafx.fxml.FXML
    private PasswordField textContraseña;
    @javafx.fxml.FXML
    private ImageView imageEscudo;
    @javafx.fxml.FXML
    private Button botonIncio;
    @javafx.fxml.FXML
    private ProgressIndicator indicator;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image=new Image(LogInController.class.getClassLoader().getResource("imagenes/escudo.png").toExternalForm());
        imageEscudo.setImage(image);
        indicator.setVisible(false);

    }

    @javafx.fxml.FXML
    public void inicioSesion(ActionEvent actionEvent) {
        if(!textContraseña.getText().isEmpty()&&!textCorreo.getText().isEmpty()){
            String correo=textCorreo.getText();
            try {
                String contraseña= HashPassword.hashPassword(textContraseña.getText());

                PersonalIIP personal=(new PersonalIIPDAOImplement().login(correo));


                if(personal!=null){
                    if(personal.getContraseña().equals(contraseña)){
                        Utilidad.setLogPersonal(personal);
                        Alert alerta=new Alert(Alert.AlertType.CONFIRMATION);
                        alerta.setTitle("OK!");
                        alerta.setHeaderText("Inicio De Sesión Correcto");
                        alerta.showAndWait();
                        HelloApplication.cambioVentana("principal-view.fxml");
                    }else{
                        Alert alerta=new Alert(Alert.AlertType.ERROR);
                        alerta.setTitle("ERROR");
                        alerta.setHeaderText("Inicio De Sesión Incorrecto");
                        alerta.setContentText("Contraseña Incorrecta");
                        alerta.showAndWait();

                    }
                }else{
                    Alert alerta=new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("ERROR");
                    alerta.setHeaderText("Inicio De Sesión Incorrecto");
                    alerta.setContentText("Correo Electrónico Incorrecto");
                    alerta.showAndWait();
                }

            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
