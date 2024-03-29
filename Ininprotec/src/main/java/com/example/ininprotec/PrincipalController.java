package com.example.ininprotec;

import Util.Utilidad;
import clase.Curso;
import implement.CursoDAOImplement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {

    @FXML
    private FlowPane principalFlowPane;
    @FXML
    private ImageView imagenPerfil;
    @FXML
    private ImageView imagenNombre;
    @FXML
    private Label labelNombre;
    @FXML
    private Button botonEntrarBolsa;
    @FXML
    private ImageView imagenEmail;
    @FXML
    private ImageView imagenDni;
    @FXML
    private ComboBox comboServicios;
    @FXML
    private Button botonEntrarServicios;
    @FXML
    private Button botonEditarServicios;
    @FXML
    private Button botonCrearServicios;
    @FXML
    private ComboBox <Curso>comboCurso;
    @FXML
    private Button botonEntrarCurso;
    @FXML
    private Button botonEditarCurso;
    @FXML
    private Button botonCrearCurso;
    @FXML
    private Button botonEntrarRegistro;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelDNI;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String ruta = PrincipalController.class.getClassLoader().getResource("imagenes/iconoNombre.png").toExternalForm();
        String rutaEmail = PrincipalController.class.getClassLoader().getResource("imagenes/iconoEmail.png").toExternalForm();
        String rutaDNI = PrincipalController.class.getClassLoader().getResource("imagenes/iconoDNI.png").toExternalForm();
        String rutaPerfil = PrincipalController.class.getClassLoader().getResource("imagenes/imagenDefectoPerfil.png").toExternalForm();
        Image image = new Image(ruta);
        Image image1 = new Image(rutaEmail);
        Image image2 = new Image(rutaDNI);
        Image imagePerfil = new Image(rutaPerfil);
        imagenNombre.setImage(image);
        imagenEmail.setImage(image1);
        imagenDni.setImage(image2);
        imagenPerfil.setImage(imagePerfil);
        comboCurso.setConverter(new StringConverter<Curso>() {

            @Override
            public String toString(Curso curso) {
                if(curso!=null){
                    return curso.getNombre();
                }else{
                    return "";
                }

            }

            @Override
            public Curso fromString(String s) {
                return null;
            }
        });
        CursoDAOImplement daoCurso=new CursoDAOImplement();
        comboCurso.getItems().addAll(daoCurso.getAll());

    }

    @FXML
    public void crearCursoNuevo(ActionEvent actionEvent) {
    HelloApplication.cambioVentana("creacionCurso-view.fxml");
    }

    @FXML
    public void entrarCurso(ActionEvent actionEvent) {
        Utilidad.setCurso(comboCurso.getValue());
        HelloApplication.cambioVentana("entradaCurso-view.fxml");
    }

    @FXML
    public void entrarRegistroAlumno(ActionEvent actionEvent) {
        HelloApplication.cambioVentana("registroAlumno-view.fxml");
    }
}