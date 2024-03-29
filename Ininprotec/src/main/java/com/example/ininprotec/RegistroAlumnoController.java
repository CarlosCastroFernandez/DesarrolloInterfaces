package com.example.ininprotec;

import Util.Utilidad;
import clase.Curso;
import clase.PersonalBolsa;
import implement.CursoDAOImplement;
import implement.PersonalBolsaDAOImplement;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.ClientInfoStatus;
import java.sql.Date;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.ResourceBundle;





public class RegistroAlumnoController implements Initializable {
    @javafx.fxml.FXML
    private ImageView imagenPerfil;
    @javafx.fxml.FXML
    private Button botonArchivos;
    @javafx.fxml.FXML
    private Label labelURL;
    @javafx.fxml.FXML
    private TextField textDni;
    @javafx.fxml.FXML
    private TextArea textAreaTIP;
    @javafx.fxml.FXML
    private TextField textNombre;
    @javafx.fxml.FXML
    private TextField textApellido1;
    @javafx.fxml.FXML
    private TextField textApellido2;
    @javafx.fxml.FXML
    private TextField textEmail;
    @javafx.fxml.FXML
    private TextField textTelefono;
    @javafx.fxml.FXML
    private TextField textLicenciaArmas;
    @javafx.fxml.FXML
    private DatePicker dateFecha;
    @javafx.fxml.FXML
    private TextField textCamiseta;
    @javafx.fxml.FXML
    private TextField textIBAN;
    @javafx.fxml.FXML
    private TextField textSegSocial;
    @javafx.fxml.FXML
    private TextField textTitulacion;
    @javafx.fxml.FXML
    private TextField textResidencia;
    @javafx.fxml.FXML
    private RadioButton radioAlumno;
    @javafx.fxml.FXML
    private ToggleGroup uno;
    @javafx.fxml.FXML
    private RadioButton radioTrabajador;
    @javafx.fxml.FXML
    private FlowPane flowPaneCurso;
    @javafx.fxml.FXML
    private ComboBox <Curso>comboCurso;
    private String archivoImagen;
    @javafx.fxml.FXML
    private Button botonGuardar;
    private Path nuevoPath;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelURL.setStyle("-fx-text-fill: #000000");
        labelURL.setStyle("-fx-underline: true");
        flowPaneCurso.setVisible(false);
        CursoDAOImplement daoCurso=new CursoDAOImplement();
        radioAlumno.setOnAction(actionEvent -> {
            flowPaneCurso.setVisible(true);
        });
        radioTrabajador.setOnAction(actionEvent -> {
            comboCurso.getSelectionModel().select(null);
            flowPaneCurso.setVisible(false);
        });
        String rutaImagen=RegistroAlumnoController.class.getClassLoader().getResource("imagenes/imagenDefectoPerfil.png").toExternalForm();
        Image imagen=new Image(rutaImagen);
        imagenPerfil.setImage(imagen);
        comboCurso.setConverter(new StringConverter<Curso>() {


            @Override
            public String toString(Curso curso) {
                return (curso == null ? null : curso.getNombre());
            }

            @Override
            public Curso fromString(String s) {
                return null;
            }


        });
        comboCurso.getItems().addAll(daoCurso.getAll());
        labelURL.setOnMouseClicked(mouseEvent -> {
            if(!labelURL.getText().equals("")){
                String rutaArchivo=nuevoPath.toString();
                File archivo=new File(rutaArchivo);
                try {
                    Desktop.getDesktop().open(archivo);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        labelURL.setOnMouseEntered(evento->{

            labelURL.setStyle("-fx-text-fill: #0124FB");
        });
        labelURL.setOnMouseExited(evento->{
            labelURL.setStyle("-fx-text-fill: #000000");
        });



    }
    @javafx.fxml.FXML
    public void clickImagen(Event event) {
        FileChooser archivo=new FileChooser();
        File ruta=archivo.showOpenDialog(null);
        if(ruta!=null){
            archivoImagen=ruta.getPath();
            System.out.println(ruta.getPath());
            Image imagen=new Image(ruta.getPath());
            imagenPerfil.setImage(imagen);
        }


    }

    @javafx.fxml.FXML
    public void openArchivos(ActionEvent actionEvent) {
        FileChooser openArchivos=new FileChooser();
        File archivo=openArchivos.showOpenDialog(null);
        System.out.println(archivo.getName());
        if(archivo!=null&&!textNombre.getText().isEmpty()&&!textApellido1.getText().isEmpty()&&!textApellido2.getText().isEmpty()){
            Path origenPath= Paths.get(archivo.getPath());
            Path destino=Paths.get("./Curriculums/"+archivo.getName());
            try {
                Files.copy(origenPath,destino);
                    String nombreElegido="CV "+textNombre.getText()+" "+textApellido1.getText()+" "+textApellido2.getText();
                    String extension=archivo.getName().substring(archivo.getName().lastIndexOf("."),archivo.getName().length());
                    nombreElegido+=extension;
                    nuevoPath=destino.resolveSibling(nombreElegido);
                    Files.move(destino,nuevoPath, StandardCopyOption.REPLACE_EXISTING);
                    String nombreRuta=String.valueOf(nuevoPath);
                    String nombreFinalRuta=nombreRuta.substring(nombreRuta.lastIndexOf(File.separator)+1,nombreRuta.length());
                    labelURL.setText(nombreFinalRuta);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }else{
            Alert alerta=new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("Campos Necesarios");
            alerta.setContentText("Se necesita rellena rl campo nombre apellido1 y apellido2");
            alerta.showAndWait();
        }

    }
    public static byte[] imageToByteArray(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;

        while ((bytesRead = fis.read(buffer)) != -1) {
            bos.write(buffer, 0, bytesRead);
        }

        fis.close();
        bos.close();

        return bos.toByteArray();
    }

    @javafx.fxml.FXML
    public void guardar(ActionEvent actionEvent) {

        if(!textNombre.getText().isEmpty()&&!textApellido1.getText().isEmpty()&&!textApellido2.getText().isEmpty()){
            byte[]imagenCargada=null;
            if(archivoImagen!=null){
                File archivo=new File(archivoImagen);
                try {
                    imagenCargada=imageToByteArray(archivo);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            PersonalBolsa clienteA=new PersonalBolsa(textNombre.getText(),textApellido1.getText(),textApellido2.getText(),textEmail.getText(),
                    textDni.getText(),textTelefono.getText(), Date.valueOf(dateFecha.getValue()),textLicenciaArmas.getText(),
                    textCamiseta.getText(),labelURL.getText(),textIBAN.getText(),textSegSocial.getText(),(radioAlumno.isSelected()?1L:0L),
                    textAreaTIP.getText(),imagenCargada,textTitulacion.getText(),textResidencia.getText());
            if(clienteA.getEsAlumno()==1){
                clienteA.setCursosAlumnos(new ArrayList<>());
                clienteA.setModulos(new ArrayList<>());
                clienteA.getCursosAlumnos().add(comboCurso.getValue());
                if(comboCurso.getValue()!=null){
                    clienteA.getModulos().addAll(comboCurso.getValue().getModulos());
                }else{
                    clienteA.setModulos(null);
                }

                PersonalBolsaDAOImplement daoAlumno=new PersonalBolsaDAOImplement();
                daoAlumno.subir(clienteA);
            }else{
                PersonalBolsaDAOImplement daoTrabajador=new PersonalBolsaDAOImplement();
                daoTrabajador.subir(clienteA);
            }
        }
        if(textNombre.getText().isEmpty()){
            textNombre.setStyle("-fx-border-color: #B30909");
        }
        if(textApellido2.getText().isEmpty()){
            textApellido2.setStyle("-fx-border-color: #B30909");
        }
        if(textApellido1.getText().isEmpty()){
            textApellido1.setStyle("-fx-border-color: #B30909");
        }


        }

}