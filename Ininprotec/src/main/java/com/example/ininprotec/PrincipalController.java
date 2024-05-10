package com.example.ininprotec;

import Util.HashPassword;
import Util.MYSQLUtil;
import Util.Utilidad;
import clase.AlumnoCurso;
import clase.Curso;
import clase.PersonalIIP;
import implement.CursoDAOImplement;
import implement.PersonalIIPDAOImplement;
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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
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
    private ComboBox<Curso> comboCurso;
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
    @FXML
    private Button botonGestionAlumno;
    @FXML
    private Button botonGestionInst;
    @FXML
    private Button botonGestionAdmin;
    @FXML
    private ImageView imagenAdmin;
    @FXML
    private Button botonContraseña;
    @FXML
    private ImageView imagenTitulo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imagenCursos.setImage(new Image(PrincipalController.class.getClassLoader().getResource("imagenes/iconoFormacion.png").toExternalForm()));
        imagenRegistro.setImage(new Image(PrincipalController.class.getClassLoader().getResource("imagenes/iconoRegistrarse.png").toExternalForm()));
        imagenServicios.setImage(new Image(PrincipalController.class.getClassLoader().getResource("imagenes/iconoServicio.png").toExternalForm()));
        imagenBolsa.setImage(new Image(PrincipalController.class.getClassLoader().getResource("imagenes/iconoClasificacion.png").toExternalForm()));
        imagenProfe.setImage(new Image(PrincipalController.class.getClassLoader().getResource("imagenes/iconProfe.png").toExternalForm()));
        imagenAdmin.setImage(new Image(PrincipalController.class.getClassLoader().getResource("imagenes/iconAdministrativa.png").toExternalForm()));
        imagenTitulo.setImage(new Image(PrincipalController.class.getClassLoader().getResource("imagenes/escudo.png").toExternalForm()));
        String ruta = PrincipalController.class.getClassLoader().getResource("imagenes/iconoNombre.png").toExternalForm();
        String rutaEmail = PrincipalController.class.getClassLoader().getResource("imagenes/iconoEmail.png").toExternalForm();
        String rutaDNI = PrincipalController.class.getClassLoader().getResource("imagenes/iconoDNI.png").toExternalForm();
        String rutaPerfil = PrincipalController.class.getClassLoader().getResource("imagenes/imagenDefectoPerfil.png").toExternalForm();
        Image image = new Image(ruta);
        Image image1 = new Image(rutaEmail);
        Image image2 = new Image(rutaDNI);
        imagenNombre.setImage(image);
        imagenEmail.setImage(image1);
        imagenDni.setImage(image2);

        if (Utilidad.getLogPersonal().getImagenPerfil() != null) {
            ByteArrayInputStream lectura = new ByteArrayInputStream(Utilidad.getLogPersonal().getImagenPerfil());
            Image imagePerfil = new Image(lectura);
            imagenPerfil.setImage(imagePerfil);
        } else {
            Image imagePerfil = new Image(rutaPerfil);
            imagenPerfil.setImage(imagePerfil);
        }
        labelDNI.setText(Utilidad.getLogPersonal().getDni());
        labelEmail.setText(Utilidad.getLogPersonal().getCorreo());
        labelNombre.setText(Utilidad.getLogPersonal().getNombre());

        comboCurso.setConverter(new StringConverter<Curso>() {


            @Override
            public String toString(Curso alumnoCurso) {
                if (alumnoCurso != null) {
                    return alumnoCurso.getNombre();
                } else {
                    return null;
                }

            }

            @Override
            public Curso fromString(String s) {
                return null;
            }
        });

        CursoDAOImplement daoCurso = new CursoDAOImplement();
        comboCurso.getItems().addAll(daoCurso.getAll());
        botonGestionAlumno.setOnAction(actionEvent -> {
            HelloApplication.cambioVentana("todos-alumnos-view.fxml");
        });
        botonGestionInst.setOnAction(actionEvent -> {
            HelloApplication.cambioVentana("todos-instructor-view.fxml");
        });
        botonGestionAdmin.setOnAction(actionEvent -> {
            HelloApplication.cambioVentana("todosAdmin-view.fxml");
        });

    }

    @FXML
    public void miPerfil(ActionEvent actionEvent) {
        if (Utilidad.getLogPersonal().getInstructor() == 0 || Utilidad.getLogPersonal().getInstructor() == 1) {
            PersonalIIP paso = Utilidad.getLogPersonal();
            Utilidad.setInstructor(paso);
            Utilidad.setMiPerfil(true);
            HelloApplication.cambioVentana("registroInstructor-view.fxml");
        }
    }

    @FXML
    public void entrar(Event event) {
        if (comboCurso.getValue() != null) {
            Utilidad.setCurso(comboCurso.getValue());
            HelloApplication.cambioVentana("entradaCurso-view.fxml");
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
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
        HelloApplication.cambioVentana("bolsaEmpleo-view.fxml");
    }

    @FXML
    public void entrarInstructor(Event event) {
        HelloApplication.cambioVentana("registroInstructor-view.fxml");
    }

    @FXML
    public void edicionCurso(ActionEvent actionEvent) {
        if (comboCurso.getValue() != null) {
            Utilidad.setCurso(comboCurso.getValue());
            HelloApplication.cambioVentana("creacionCurso-view.fxml");
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText("Curso No Seleccionado");
            alerta.setContentText("Por favor seleccione un curso para poder editarlo.");
            alerta.showAndWait();
        }
    }

    @FXML
    public void borrarCurso(ActionEvent actionEvent) {
        if (comboCurso.getValue() != null) {
            try {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("INFORMACIÓN!");
                alerta.setHeaderText("Borrado de Curso");
                alerta.setContentText("¿Seguro deseas borrar el curso " + comboCurso.getValue().getNombre() + "?");
                Optional<ButtonType> tipo = alerta.showAndWait();
                if (tipo.get() == ButtonType.OK) {
                    (new CursoDAOImplement()).borrarCurso(comboCurso.getValue());
                    comboCurso.getItems().remove(comboCurso.getValue());
                }

            } catch (Exception e) {

            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("ERROR");
            alerta.setHeaderText("Selección de Curso");
            alerta.setContentText("Por favor asegurese de seleccionar el curso antes de borrarlo.");
            alerta.showAndWait();
        }


    }

    /*@Deprecated
    public void generarPlantilla(ActionEvent actionEvent) {
        HashMap<String, Object> parametro = new HashMap<>();
        try {
            JasperPrint jasper = JasperFillManager.fillReport("plantillaCV.jasper", parametro, new JREmptyDataSource());
            JRPdfExporter exp = new JRPdfExporter();
            exp.setExporterInput(new SimpleExporterInput(jasper));
            exp.setExporterOutput(new SimpleOutputStreamExporterOutput("PlantillaJasper.pdf"));
            exp.setConfiguration(new SimplePdfExporterConfiguration());
            exp.exportReport();
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("OK!");
            alerta.setHeaderText("Plantilla Generada");
            alerta.setContentText("Plantilla generada con éxito");
            alerta.showAndWait();
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }*/

    @FXML
    public void entrarAdmin(Event event) {
        HelloApplication.cambioVentana("registroAdmin-view.fxml");
    }

    @FXML
    public void cambiarContraseña(ActionEvent actionEvent) {
        Dialog<HashMap<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Introducir Datos");
        dialog.setHeaderText("Introduce todos los datos:");

        // Configurar tipos de botones
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        PasswordField textField1 = new PasswordField();
        textField1.setPromptText("Contraseña Nueva");
        grid.add(new Label("Contraseña Nueva:"), 0, 0);
        grid.add(textField1, 1, 0);
        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(dialogButton -> {
            if (!textField1.getText().isEmpty()) {
                try {
                    String contraseña = HashPassword.hashPassword(textField1.getText());
                    Utilidad.getLogPersonal().setContraseña(contraseña);
                    (new PersonalIIPDAOImplement()).cambioPassword(Utilidad.getLogPersonal());
                    return null;
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
            }else{

                return null;

            }
        });
        dialog.showAndWait();


    }
}