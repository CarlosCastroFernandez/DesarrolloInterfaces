package com.example.ininprotec;

import Util.Utilidad;
import clase.AlumnoCurso;
import clase.AlumnoModulo;
import clase.Curso;
import clase.PersonalBolsa;
import implement.CursoDAOImplement;
import implement.PersonalBolsaDAOImplement;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
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
    @javafx.fxml.FXML
    private Label labelCurso;
    @javafx.fxml.FXML
    private ImageView imagenFlecha;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image imagenFlechia=new Image(RegistroAlumnoController.class.getClassLoader().getResource("imagenes/flechita.png").toExternalForm());
        imagenFlecha.setImage(imagenFlechia);
        imagenFlecha.setOnMouseClicked(mouseEvent -> {
            HelloApplication.cambioVentana("principal-view.fxml");
        });
        if(Utilidad.getAlumno()==null){
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
                                        public String toString(Curso alumnoCurso) {
                                            if(alumnoCurso!=null){
                                                return alumnoCurso.getNombre();
                                            }
                                            return null;
                                        }

                                        @Override
                                        public Curso fromString(String s) {
                                            return null;
                                        }

                                        ;
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
        }else{
            if(Utilidad.getAlumno().getEsAlumno()==1){
                radioAlumno.setSelected(true);
            }else{
                radioTrabajador.setSelected(true);
            }
            labelCurso.setVisible(false);
            radioTrabajador.setVisible(false);
            radioAlumno.setVisible(false);
            comboCurso.setVisible(false);
            textNombre.setText(Utilidad.getAlumno().getNombre());
            textApellido1.setText(Utilidad.getAlumno().getApellido1());
            textApellido2.setText(Utilidad.getAlumno().getApellido2());
            textEmail.setText(Utilidad.getAlumno().getCorreo());
            textTelefono.setText(Utilidad.getAlumno().getTelefono());
            textLicenciaArmas.setText(Utilidad.getAlumno().getLicenciaArma());
            dateFecha.setValue(LocalDate.parse(Utilidad.getAlumno().getFechaNacimiento().toString()));
            textCamiseta.setText(Utilidad.getAlumno().getTallaCamiseta());
            textIBAN.setText(Utilidad.getAlumno().getNumeroCuenta());
            textSegSocial.setText(Utilidad.getAlumno().getNumeroSocial());
            textTitulacion.setText(Utilidad.getAlumno().getTitulacion());
            textResidencia.setText(Utilidad.getAlumno().getLugarResidencia());
            if(Utilidad.getAlumno().getImagenPerfil()!=null){
                ByteArrayInputStream lectura=new ByteArrayInputStream(Utilidad.getAlumno().getImagenPerfil());
                Image imagenLeida=new Image(lectura);
                try {
                    lectura.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                imagenPerfil.setImage(imagenLeida);
            }else{
                String rutaImagen=RegistroAlumnoController.class.getClassLoader().getResource("imagenes/imagenDefectoPerfil.png").toExternalForm();
                Image imagen=new Image(rutaImagen);
                imagenPerfil.setImage(imagen);
            }


            labelURL.setText(Utilidad.getAlumno().getCurriculumUrl());
            textDni.setText(Utilidad.getAlumno().getDni());
            textAreaTIP.setText(Utilidad.getAlumno().getNumeroTip());
        }




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
        AlumnoCurso alumnoCurso=new AlumnoCurso();
        AlumnoModulo alumnoModulo=new AlumnoModulo();
        if(Utilidad.getAlumno()==null){
            if(!textNombre.getText().isEmpty()&&!textApellido1.getText().isEmpty()&&!textApellido2.getText().isEmpty()&&dateFecha.getValue()!=null&&!textDni.getText().isEmpty()&&
            !textEmail.getText().isEmpty()&&(radioAlumno.isSelected()||radioTrabajador.isSelected())){
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
                        textCamiseta.getText(),labelURL.getText(),textIBAN.getText(),textSegSocial.getText(),(radioAlumno.isSelected()?1L:2L),
                        textAreaTIP.getText(),imagenCargada,textTitulacion.getText(),textResidencia.getText());
                if(clienteA.getEsAlumno()==1){
                    clienteA.setCursosAlumnos(new ArrayList<>());
                    clienteA.setModuloAlumno(new ArrayList<>());
                    alumnoCurso.setAlumnoId(clienteA);
                    alumnoCurso.setCursoId(comboCurso.getValue());
                    clienteA.getCursosAlumnos().add(alumnoCurso);
                    if(comboCurso.getValue()!=null){
                        alumnoModulo.setAlumnoId(clienteA);
                        for(int i=0;i<comboCurso.getValue().getModulos().size();i++){
                            alumnoModulo.setModuloId(comboCurso.getValue().getModulos().get(i));
                            clienteA.getModuloAlumno().add(alumnoModulo);
                        }


                    }else{
                        clienteA.setModuloAlumno(null);
                        clienteA.setCursosAlumnos(null);
                    }

                    PersonalBolsaDAOImplement daoAlumno=new PersonalBolsaDAOImplement();
                    daoAlumno.subir(clienteA);
                }else{
                    PersonalBolsaDAOImplement daoTrabajador=new PersonalBolsaDAOImplement();
                    daoTrabajador.subir(clienteA);
                }
            }else{
                if(textNombre.getText().isEmpty()){
                    textNombre.setStyle("-fx-border-color: #B30909");
                }
                if(textApellido2.getText().isEmpty()){
                    textApellido2.setStyle("-fx-border-color: #B30909");
                }
                if(textApellido1.getText().isEmpty()){
                    textApellido1.setStyle("-fx-border-color: #B30909");
                }
                if(textEmail.getText().isEmpty()){
                    textEmail.setStyle("-fx-border-color: #B30909");
                }
                if(dateFecha.getValue()==null){
                    dateFecha.setStyle("-fx-border-color: #B30909");
                }
                if(textDni.getText().isEmpty()){
                    textDni.setStyle("-fx-border-color: #B30909");
                }
                if(radioAlumno.isSelected()==false&&radioTrabajador.isSelected()==false){
                    radioAlumno.setStyle("-fx-border-color: #B30909");
                    radioTrabajador.setStyle("-fx-border-color: #B30909");
                }
            }
        }else{
            if(!textNombre.getText().isEmpty()&&!textApellido1.getText().isEmpty()&&!textApellido2.getText().isEmpty()&&dateFecha.getValue()!=null&&!textDni.getText().isEmpty()&&
                    !textEmail.getText().isEmpty()){
                byte[]imagenCargada=null;
                if(archivoImagen!=null){
                    File archivo=new File(archivoImagen);
                    try {
                        imagenCargada=imageToByteArray(archivo);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }else{
                    Image imagenCojida=imagenPerfil.getImage();
                    BufferedImage image= SwingFXUtils.fromFXImage(imagenCojida,null);
                    ByteArrayOutputStream biImagen=new ByteArrayOutputStream();
                    try {
                        ImageIO.write(image,"png",biImagen);
                        imagenCargada=biImagen.toByteArray();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }finally {
                        try {
                            biImagen.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                Utilidad.getAlumno().setNombre(textNombre.getText());
                Utilidad.getAlumno().setApellido1(textApellido1.getText());
                Utilidad.getAlumno().setApellido2(textApellido2.getText());
                Utilidad.getAlumno().setCorreo(textEmail.getText());
                Utilidad.getAlumno().setTelefono(textTelefono.getText());
                Utilidad.getAlumno().setLicenciaArma(textLicenciaArmas.getText());
                Utilidad.getAlumno().setFechaNacimiento(Date.valueOf(dateFecha.getValue()));
                Utilidad.getAlumno().setTallaCamiseta(textCamiseta.getText());
                Utilidad.getAlumno().setNumeroCuenta(textIBAN.getText());
                Utilidad.getAlumno().setNumeroSocial(textSegSocial.getText());
                Utilidad.getAlumno().setTitulacion(textTitulacion.getText());
                Utilidad.getAlumno().setLugarResidencia(textResidencia.getText());
                Utilidad.getAlumno().setImagenPerfil(imagenCargada);
                Utilidad.getAlumno().setCurriculumUrl(labelURL.getText());
                Utilidad.getAlumno().setDni(textDni.getText());
                Utilidad.getAlumno().setNumeroTip(textAreaTIP.getText());
                Utilidad.setAlumno((new PersonalBolsaDAOImplement()).modPersonalBolsa(Utilidad.getAlumno()));
                Alert alerta=new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("OK!");
                alerta.setHeaderText("Alumno Modificado Con Éxito");
                Optional<ButtonType> tipo=alerta.showAndWait();
                if(tipo.get()==ButtonType.OK){
                    Utilidad.setAlumno(null);
                    Utilidad.setCurso(null);
                  HelloApplication.cambioVentana("principal-view.fxml");
                }
            }else{
                if(textNombre.getText().isEmpty()){
                    textNombre.setStyle("-fx-border-color: #B30909");
                }
                if(textApellido2.getText().isEmpty()){
                    textApellido2.setStyle("-fx-border-color: #B30909");
                }
                if(textApellido1.getText().isEmpty()){
                    textApellido1.setStyle("-fx-border-color: #B30909");
                }
                if(textEmail.getText().isEmpty()){
                    textEmail.setStyle("-fx-border-color: #B30909");
                }
                if(dateFecha.getValue()==null){
                    dateFecha.setStyle("-fx-border-color: #B30909");
                }
                if(textDni.getText().isEmpty()){
                    textDni.setStyle("-fx-border-color: #B30909");
                }
            }

        }




        }

}