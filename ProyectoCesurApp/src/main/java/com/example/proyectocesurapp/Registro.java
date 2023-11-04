package com.example.proyectocesurapp;

import clase.Profesor;
import domain.DBConnection;
import domain.ProfesorDAOImp;
import exception.ApellidoConNumero;
import exception.DNIInvalido;
import exception.NombreConNumero;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

public class Registro implements Initializable {
    @javafx.fxml.FXML
    private Label labelTitulo;
    @javafx.fxml.FXML
    private TextField txtEmail;
    @javafx.fxml.FXML
    private PasswordField passField;
    @javafx.fxml.FXML
    private TextField txtNombre;
    @javafx.fxml.FXML
    private TextField txtApellido1;
    @javafx.fxml.FXML
    private TextField txtApellido2;
    @javafx.fxml.FXML
    private TextField txtTelefono;
    @javafx.fxml.FXML
    private Button botonRegistro;
    @javafx.fxml.FXML
    private Button botonCancelar;
    @javafx.fxml.FXML
    private TextField txtDNI;
    @javafx.fxml.FXML
    private ImageView imagen;

    @javafx.fxml.FXML
    public void registrarse(ActionEvent actionEvent) {
        String dni=txtDNI.getText();
        String nombre=txtNombre.getText();
        String apellido1=txtApellido1.getText();
        String apellido2=txtApellido2.getText();
        String email=txtEmail.getText();
        Integer telefono=Integer.valueOf(txtTelefono.getText());
        String contraseña=passField.getText();
        try {
            Profesor profe=new Profesor(nombre,apellido1,apellido2,contraseña,email,dni,telefono);
            ProfesorDAOImp dao=new ProfesorDAOImp(DBConnection.getConnection());
             profe=dao.injection(profe);
        } catch (NombreConNumero e) {
            throw new RuntimeException(e);
        } catch (ApellidoConNumero e) {
            throw new RuntimeException(e);
        } catch (DNIInvalido e) {
            throw new RuntimeException(e);
        }
        Stage nuevaVentana = new Stage();
        nuevaVentana.setTitle("Login");

        // Cargar el archivo FXML de la nueva ventana (reemplaza 'NuevaVentana.fxml' con el nombre de tu archivo FXML)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
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
        Stage ventanaActual=(Stage)botonRegistro.getScene().getWindow();
        ventanaActual.close();

    }

    @javafx.fxml.FXML
    public void cancelar(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    @javafx.fxml.FXML
    public void changeImage(Event event) {
        FileChooser file=new FileChooser();
        File ruta= file.showOpenDialog(null);
        if(ruta!=null){
            File carpeta=new File("imagenes de Carlos");
            if(!carpeta.exists()){
                try {
                    carpeta.mkdir();
                    Path origen= Path.of(ruta.getAbsolutePath());
                    Path destino= Path.of("./imagenes de Carlos");
                    Path destinoArchivo=destino.resolve("carlos.jpg");
                    Files.copy(origen,destinoArchivo, StandardCopyOption.REPLACE_EXISTING);
                    imagen.setImage(new Image("file:"+destinoArchivo));

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }
}
