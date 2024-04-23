package com.example.ininprotec;

import Util.Utilidad;
import clase.AlumnoCurso;
import clase.Curso;
import implement.CursoDAOImplement;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private ImageView imagenPerfil;
    @FXML
    private ImageView imagenNombre;
    @FXML
    private Label labelNombre;
    @FXML
    private ImageView imagenEmail;
    @FXML
    private ImageView imagenDni;
    @FXML
    private ComboBox <Curso>comboCurso;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelDNI;
    @FXML
    private Button botonMiPerfil;
    @FXML
    private ImageView imagenCursos;
    @FXML
    private MenuItem crearCurso;
    @FXML
    private ImageView imagenRegistro;
    @FXML
    private ImageView imagenServicios;
    @FXML
    private ImageView imagenBolsa;
    @FXML
    private ImageView imagenProfe;
    @FXML
    private MenuItem botonEditarCurso;
    @FXML
    private MenuItem itemBorrar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imagenCursos.setImage(new Image(PrincipalController.class.getClassLoader().getResource("imagenes/iconoFormacion.png").toExternalForm()));
        imagenRegistro.setImage(new Image(PrincipalController.class.getClassLoader().getResource("imagenes/iconoRegistrarse.png").toExternalForm()));
        imagenServicios.setImage(new Image(PrincipalController.class.getClassLoader().getResource("imagenes/iconoServicio.png").toExternalForm()));
        imagenBolsa.setImage(new Image(PrincipalController.class.getClassLoader().getResource("imagenes/iconoClasificacion.png").toExternalForm()));
        imagenProfe.setImage(new Image(PrincipalController.class.getClassLoader().getResource("imagenes/iconProfe.png").toExternalForm()));
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
                                    public String toString(Curso alumnoCurso) {
                                        if(alumnoCurso!=null){
                                            return alumnoCurso.getNombre();
                                        }else{
                                            return null;
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
    public void miPerfil(ActionEvent actionEvent) {
    }

    @FXML
    public void entrar(Event event) {
        if(comboCurso.getValue()!=null){
            Utilidad.setCurso(comboCurso.getValue());
            HelloApplication.cambioVentana("entradaCurso-view.fxml");
        }else{
            Alert alerta=new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText("Curso No Seleccionado");
            alerta.setContentText("Por favor seleccione un curso para poder entrar en él.");
            alerta.showAndWait();
        }

    }

    @FXML
    public void creacionCurso(ActionEvent actionEvent) {
        HelloApplication.cambioVentana("creacionCurso-view.fxml");
    }


    @FXML
    public void registro(Event event) {
        HelloApplication.cambioVentana("registroAlumno-view.fxml");
    }

    @FXML
    public void entrarServicios(Event event) {
        HelloApplication.cambioVentana("grafica-view.fxml");
    }

    @FXML
    public void entrarBolsa(Event event) {
    }

    @FXML
    public void entrarInstructor(Event event) {
        HelloApplication.cambioVentana("registroInstructor-view.fxml");
    }

    @FXML
    public void edicionCurso(ActionEvent actionEvent) {
        if(comboCurso.getValue()!=null){
            Utilidad.setCurso(comboCurso.getValue());
            HelloApplication.cambioVentana("creacionCurso-view.fxml");
        }else{
            Alert alerta=new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText("Curso No Seleccionado");
            alerta.setContentText("Por favor seleccione un curso para poder editarlo.");
            alerta.showAndWait();
        }
    }

    @FXML
    public void borrarCurso(ActionEvent actionEvent) {
        if(comboCurso.getValue()!=null){
            (new CursoDAOImplement()).borrarCurso(comboCurso.getValue());
        }
    }
}