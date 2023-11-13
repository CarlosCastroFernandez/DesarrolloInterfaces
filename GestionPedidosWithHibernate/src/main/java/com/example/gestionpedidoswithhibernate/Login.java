package com.example.gestionpedidoswithhibernate;

import clases.Session;
import clases.Usuario;
import dao.HibernateUtils;
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
    @javafx.fxml.FXML
    private Label labelPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }

    /**
     * Este método se invoca cuando se presiona un botón para iniciar sesión en la interfaz de usuario.
     * Realiza la autenticación del usuario y redirige a la siguiente ventana si la autenticación es exitosa.
     *
     * @param actionEvent El evento que desencadena la acción (por ejemplo, al hacer clic en un botón).
     */
    @javafx.fxml.FXML
    public void iniciarSesion(ActionEvent actionEvent) {
        String email=textUsuario.getText();
        String contraseña=password.getText();
        UsuarioDAOImp consulta=new UsuarioDAOImp(HibernateUtils.getSession());
        try {
            Session.setUsuario(consulta.consultaPersonal(email,contraseña));
            HelloApplication.cambioVentana("ventana-usuario.fxml");
        } catch (UsuarioNoExiste e) {
           e.printStackTrace();
        } catch (ContraseñaInvalida e) {
           e.printStackTrace();
        }



    }
}
