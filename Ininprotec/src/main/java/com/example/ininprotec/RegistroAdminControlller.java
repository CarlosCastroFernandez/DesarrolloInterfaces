package com.example.ininprotec;

import Util.HashPassword;
import Util.Utilidad;
import clase.PersonalIIP;
import implement.PersonalIIPDAOImplement;
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
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class RegistroAdminControlller implements Initializable {
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
    private String archivoImagen;
    @javafx.fxml.FXML
    private Button botonGuardar;
    private Path nuevoPath;
    @javafx.fxml.FXML
    private ImageView imagenFlecha;
    private byte[]parseo;
    @javafx.fxml.FXML
    private Button botonAbrir;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image imagenFlechia=new Image(RegistroAlumnoController.class.getClassLoader().getResource("imagenes/flechita.png").toExternalForm());
        imagenFlecha.setImage(imagenFlechia);
        imagenFlecha.setOnMouseClicked(mouseEvent -> {
            HelloApplication.cambioVentana("principal-view.fxml");
            Utilidad.setAlumno(null);
            Utilidad.setCurso(null);
            Utilidad.setInstructor(null);
        });



        if(Utilidad.getAdmin()==null){
            botonAbrir.setVisible(false);
            String rutaImagen=RegistroAlumnoController.class.getClassLoader().getResource("imagenes/imagenDefectoPerfil.png").toExternalForm();
            Image imagen=new Image(rutaImagen);
            imagenPerfil.setImage(imagen);

        }else{
            if(Utilidad.getAdmin().getCurriculum()==null){
                botonArchivos.setVisible(true);
                botonAbrir.setVisible(false);
            }else{
                botonArchivos.setVisible(false);
                botonAbrir.setVisible(true);
            }

            textNombre.setText(Utilidad.getAdmin().getNombre());
            textApellido1.setText(Utilidad.getAdmin().getApellido1());
            textApellido2.setText(Utilidad.getAdmin().getApellido2());
            textEmail.setText(Utilidad.getAdmin().getCorreo());
            textTelefono.setText(Utilidad.getAdmin().getTelefono());
            textLicenciaArmas.setText(Utilidad.getAdmin().getLicenciaArma());
            if (Utilidad.getAdmin().getFechaNacimiento() != null) {
                dateFecha.setValue(LocalDate.parse(Utilidad.getAdmin().getFechaNacimiento().toString()));
            } else {
                dateFecha.setValue(null);
            }
            textCamiseta.setText(Utilidad.getAdmin().getTallaCamiseta());
            textIBAN.setText(Utilidad.getAdmin().getNumeroCuenta());
            textSegSocial.setText(Utilidad.getAdmin().getNumeroSocial());
            textTitulacion.setText(Utilidad.getAdmin().getTitulacion());
            textResidencia.setText(Utilidad.getAdmin().getLugarResidencia());
            if(Utilidad.getAdmin().getImagenPerfil()!=null){
                ByteArrayInputStream lectura=new ByteArrayInputStream(Utilidad.getAdmin().getImagenPerfil());
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


            textDni.setText(Utilidad.getAdmin().getDni());
            textAreaTIP.setText(Utilidad.getAdmin().getNumeroTip());
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
            parseo=documentoToByteArray(archivo);
        }else{
            Alert alerta=new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("Campos Necesarios");
            alerta.setContentText("Se necesita rellena rl campo nombre apellido1 y apellido2");
            alerta.showAndWait();
        }

    }
    private byte[] documentoToByteArray(File file){
        byte[] fileContent = null;
        try {
            fileContent = Files.readAllBytes(file.toPath());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileContent;
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
        if(Utilidad.getAdmin()==null){
            if(!textNombre.getText().isEmpty()&&!textApellido1.getText().isEmpty()&&!textApellido2.getText().isEmpty()&&!textDni.getText().isEmpty()){
                byte[]imagenCargada=null;
                if(archivoImagen!=null){
                    File archivo=new File(archivoImagen);
                    try {
                        imagenCargada=imageToByteArray(archivo);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                String contraseña="IIP"+textDni.getText().substring(5,8);

                PersonalIIP clienteA=new PersonalIIP(textNombre.getText(),textApellido1.getText(),textApellido2.getText(),textEmail.getText(),
                        textDni.getText(),textTelefono.getText(),  (dateFecha.getValue()==null?null: Date.valueOf(dateFecha.getValue())),textCamiseta.getText(),
                        (parseo==null?null:parseo),textIBAN.getText(),textSegSocial.getText(),textAreaTIP.getText(), 2L,imagenCargada,textResidencia.getText(),textTitulacion.getText(),textLicenciaArmas.getText());
                try {
                    clienteA.setContraseña(HashPassword.hashPassword(contraseña));
                    (new PersonalIIPDAOImplement()).subir(clienteA);

                } catch (NoSuchAlgorithmException e) {
                    Logger log=Logger.getLogger(RegistroAdminControlller.class.getClassLoader().getName());
                    log.severe("Algo a ocurrido mal en el hasheo de la contraseña");
                    throw new RuntimeException(e);
                }



                textNombre.clear();  textApellido1.clear(); textApellido2.clear();  textEmail.clear();
                textTelefono.clear(); textLicenciaArmas.clear();dateFecha.setValue(null);  textCamiseta.clear();
                textIBAN.clear();   textSegSocial.clear();  textTitulacion.clear();  textResidencia.clear();
                String rutaImagen=RegistroAlumnoController.class.getClassLoader().getResource("imagenes/imagenDefectoPerfil.png").toExternalForm();
                imagenPerfil.setImage(new Image(rutaImagen));    labelURL.setText("");
                textDni.clear();textAreaTIP.clear();
                Alert alerta=new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("OK!");
                alerta.setHeaderText("Administrativo/a Insertado/a Con Éxito");
                alerta.showAndWait();

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
                if(textDni.getText().isEmpty()){
                    textDni.setStyle("-fx-border-color: #B30909");
                }



            }
        }else{
            if(!textNombre.getText().isEmpty()&&!textApellido1.getText().isEmpty()&&!textApellido2.getText().isEmpty()&&!textDni.getText().isEmpty()){
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
                Utilidad.getAdmin().setNombre(textNombre.getText());
                Utilidad.getAdmin().setApellido1(textApellido1.getText());
                Utilidad.getAdmin().setApellido2(textApellido2.getText());
                Utilidad.getAdmin().setCorreo(textEmail.getText());
                Utilidad.getAdmin().setTelefono(textTelefono.getText());
                Utilidad.getAdmin().setLicenciaArma(textLicenciaArmas.getText());
                Utilidad.getAdmin().setFechaNacimiento(Date.valueOf(dateFecha.getValue()));
                Utilidad.getAdmin().setTallaCamiseta(textCamiseta.getText());
                Utilidad.getAdmin().setNumeroCuenta(textIBAN.getText());
                Utilidad.getAdmin().setNumeroSocial(textSegSocial.getText());
                Utilidad.getAdmin().setTitulacion(textTitulacion.getText());
                Utilidad.getAdmin().setLugarResidencia(textResidencia.getText());
                Utilidad.getAdmin().setImagenPerfil(imagenCargada);
                Utilidad.getAdmin().setCurriculum((parseo==null?null:parseo));
                Utilidad.getAdmin().setDni(textDni.getText());
                Utilidad.getAdmin().setNumeroTip(textAreaTIP.getText());
                //MODIFICACION INSTRUCOT
                (new PersonalIIPDAOImplement()).actualizar(Utilidad.getAdmin());
                //-------------------------
                Alert alerta=new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("OK!");
                alerta.setHeaderText("Administrativo/a Modificado/a Con Éxito");
                Optional<ButtonType> tipo=alerta.showAndWait();
                if(tipo.get()==ButtonType.OK){
                    Utilidad.setAlumno(null);
                    Utilidad.setCurso(null);
                    Utilidad.setInstructor(null);
                    HelloApplication.cambioVentana("registroAdmin-view.fxml");
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
                if(textDni.getText().isEmpty()){
                    textDni.setStyle("-fx-border-color: #B30909");
                }


            }

        }




    }



    @javafx.fxml.FXML
    public void abiriCurriculum(ActionEvent actionEvent) {
        try(FileOutputStream fos=new FileOutputStream("."+File.separator+"Curriculums"+File.separator+"curriculum.pdf",false)) {
            fos.write(Utilidad.getAdmin().getCurriculum());
            File filePath=new File("."+File.separator+"Curriculums"+File.separator+"curriculum.pdf");
            Desktop.getDesktop().open(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

