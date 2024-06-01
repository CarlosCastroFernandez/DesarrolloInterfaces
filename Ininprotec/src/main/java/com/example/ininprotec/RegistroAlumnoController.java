package com.example.ininprotec;

import Util.EnvioCorreoElectronico;
import Util.HashPassword;
import Util.Utilidad;
import clase.AlumnoCurso;
import clase.AlumnoModulo;
import clase.Curso;
import clase.PersonalBolsa;
import error.DNIIncorrecto;
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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    @javafx.fxml.FXML
    private Label labelRol;
    @javafx.fxml.FXML
    private ComboBox comboRol;
    @javafx.fxml.FXML
    private TextArea textTitulacion;
    @javafx.fxml.FXML
    private TextArea textIdioma;
    @javafx.fxml.FXML
    private Spinner <Double> spAltura;
    @javafx.fxml.FXML
    private ToggleGroup vale;
    @javafx.fxml.FXML
    private RadioButton radioHombre;
    @javafx.fxml.FXML
    private RadioButton radioMujer;
    private byte[]parseo;
    @javafx.fxml.FXML
    private Button botonAbrir;
    @javafx.fxml.FXML
    private Button botonCorreo;
    private String recojidaNombre;
    private String recojidaCorreo;
    private Boolean botonEnvio=false;
    @javafx.fxml.FXML
    private FlowPane flowTrabajo;
    @javafx.fxml.FXML
    private ComboBox <String>comboCursoTr;
    @javafx.fxml.FXML
    private Spinner <Double>spNotaCurso;
    @javafx.fxml.FXML
    private Label labelNotaMedia;

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
        botonCorreo.setOnAction(actionEvent -> {
            botonEnvio=true;
            guardar(actionEvent);
            EnvioCorreoElectronico.enviar(recojidaCorreo,recojidaNombre);
            botonEnvio=false;
            HelloApplication.cambioVentana("todos-alumnos-view.fxml");
        });

        spAltura.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0,2.10,0.0,0.1));
       spNotaCurso.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0,10.0,0.0,0.1));
       comboCursoTr.getItems().add("Sin Cursos");
        comboCursoTr.getItems().add("Con Cursos");
        if(Utilidad.getAlumno()==null){
            flowTrabajo.setVisible(false);
            botonCorreo.setVisible(false);
            botonAbrir.setVisible(false);
            labelRol.setVisible(false);
            comboRol.setVisible(false);
            labelURL.setStyle("-fx-text-fill: #000000");
            labelURL.setStyle("-fx-underline: true");
            flowPaneCurso.setVisible(false);
            CursoDAOImplement daoCurso=new CursoDAOImplement();
            radioAlumno.setOnAction(actionEvent -> {
                flowPaneCurso.setVisible(true);
                flowTrabajo.setVisible(false);
            });
            radioTrabajador.setOnAction(actionEvent -> {
                comboCurso.getSelectionModel().select(null);
                flowPaneCurso.setVisible(false);
                spNotaCurso.setVisible(false);
                labelNotaMedia.setVisible(false);
                flowTrabajo.setVisible(true);
            });
            comboCursoTr.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> {
                if(t1.equals("Con Cursos")){
                    labelNotaMedia.setVisible(true);
                    spNotaCurso.setVisible(true);
                }else{
                    labelNotaMedia.setVisible(false);
                    spNotaCurso.setVisible(false);
                }
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

        }else{
            flowTrabajo.setVisible(false);
            labelRol.setVisible(true);
            comboRol.setVisible(true);
            comboRol.getItems().add("Alumno");
            comboRol.getItems().add("Trabajador");
            comboRol.getItems().add("Alumno y Trabajador");

            if(Utilidad.getAlumno().getEsAlumno()==1){
                radioAlumno.setSelected(true);
                comboCurso.getSelectionModel().select(0);
            }else if(Utilidad.getAlumno().getEsAlumno()==2){
                radioTrabajador.setSelected(true);
                comboCurso.getSelectionModel().select(1);
            }else{
                comboCurso.getSelectionModel().select(2);
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
            if (Utilidad.getAlumno().getFechaNacimiento() != null) {
                dateFecha.setValue(LocalDate.parse(Utilidad.getAlumno().getFechaNacimiento().toString()));
            } else {
                dateFecha.setValue(null);
            }
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

            parseo=Utilidad.getAlumno().getCurriculumUrl();
           if(parseo==null){
               labelURL.setText("Sin Archivo");
           }else {
               labelURL.setText("Con Archivo");
           }
            textDni.setText(Utilidad.getAlumno().getDni());
            textAreaTIP.setText(Utilidad.getAlumno().getNumeroTip());
            spAltura.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0,2.10,Utilidad.getAlumno().getAltura(),0.1));
            textIdioma.setText(Utilidad.getAlumno().getIdioma());
            if(Utilidad.getAlumno().getSexo()!='x'&&Utilidad.getAlumno().getSexo()=='h'){
                radioHombre.setSelected(true);
            }else if(Utilidad.getAlumno().getSexo()!='x'&&Utilidad.getAlumno().getSexo()=='m'){
                radioMujer.setSelected(true);
            }
            if(Utilidad.getAlumno().getEsAlumno()==1){
                comboRol.getSelectionModel().select(0);
            }else if(Utilidad.getAlumno().getEsAlumno()==2){
                comboRol.getSelectionModel().select(1);
            }else if(Utilidad.getAlumno().getEsAlumno()==3){
                comboRol.getSelectionModel().select(2);
            }




        }




    }
    @javafx.fxml.FXML
    public void clickImagen(Event event) {
        try{
            FileChooser archivo=new FileChooser();
            File ruta=archivo.showOpenDialog(null);
            if(ruta!=null){
                archivoImagen=ruta.getPath();
                System.out.println(ruta.getPath());
                Image imagen=new Image(ruta.getPath());
                imagenPerfil.setImage(imagen);
            }
        }catch (Exception e){

        }



    }

    @javafx.fxml.FXML
    public void openArchivos(ActionEvent actionEvent) {
        try{
            FileChooser openArchivos=new FileChooser();
            File archivo=openArchivos.showOpenDialog(null);
            System.out.println(archivo.getName());
            if(archivo!=null&&!textNombre.getText().isEmpty()&&!textApellido1.getText().isEmpty()&&!textApellido2.getText().isEmpty()){
                parseo=documentoToByteArray(archivo);
                labelURL.setText(archivo.getName());
            }else{
                Alert alerta=new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error");
                alerta.setHeaderText("Campos Necesarios");
                alerta.setContentText("Se necesita rellenar el campo nombre, apellido 1,  apellido 2");
                alerta.showAndWait();
            }
        }catch (Exception e){

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
    private byte[] documentoToByteArray(File file){
        byte[] fileContent = null;
        try {
            fileContent = Files.readAllBytes(file.toPath());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileContent;
    }

    @javafx.fxml.FXML
    public void guardar(ActionEvent actionEvent) {
        AlumnoCurso alumnoCurso=new AlumnoCurso();
        try {
        if(Utilidad.getAlumno()==null){
            if(!textNombre.getText().isEmpty()&&!textApellido1.getText().isEmpty()&&!textApellido2.getText().isEmpty()&&
            (radioAlumno.isSelected()||radioTrabajador.isSelected())&&!textDni.getText().isEmpty()&&!textEmail.getText().isEmpty()&&(radioHombre.isSelected()||radioMujer.isSelected())){
                byte[]imagenCargada=null;
                if(archivoImagen!=null){
                    File archivo=new File(archivoImagen);
                    try {
                        imagenCargada=imageToByteArray(archivo);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                LocalDate fechaLocal=LocalDate.now();
                DateTimeFormatter formato=DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String fechaTexto=formato.format(fechaLocal);
                LocalDate fechaFinalLocal=LocalDate.parse(fechaTexto,formato);
                Date fechaFinal=Date.valueOf(fechaFinalLocal);
                PersonalBolsa clienteA= null;

                String titulacion=textTitulacion.getText().toLowerCase();
                titulacion=titulacion.replace("á","a");
                titulacion=titulacion.replace("é","e");
                titulacion=titulacion.replace("í","i");
                titulacion=titulacion.replace("ó","o");
                titulacion=titulacion.replace("ú","u");
                String idioma= textIdioma.getText().toLowerCase();
                idioma=idioma.replace("á","a");
                idioma=idioma.replace("é","e");
                idioma=idioma.replace("í","i");
                idioma=idioma.replace("ó","o");
                idioma=idioma.replace("ú","u");



                    clienteA = new PersonalBolsa(textNombre.getText().strip(),textApellido1.getText().strip(),textApellido2.getText().strip(),textEmail.getText().strip(),
                            textDni.getText().strip(),textTelefono.getText().strip(), (dateFecha.getValue()==null?null: Date.valueOf(dateFecha.getValue())),textLicenciaArmas.getText(),
                            textCamiseta.getText().strip(),(parseo==null?null:parseo),textIBAN.getText().strip(),textSegSocial.getText().strip(),(radioAlumno.isSelected()?1L:2L),
                            textAreaTIP.getText(),imagenCargada,titulacion,textResidencia.getText().strip(),fechaFinal,idioma,spAltura.getValue(),(radioHombre.isSelected()?'h':(radioMujer.isSelected()?'m':'x')));
                try {
                    String contraseña="IIP"+textDni.getText().substring(5,8);
                   clienteA.setContraseña(HashPassword.hashPassword(contraseña));
                    System.out.println(clienteA.getContraseña());

                if(clienteA.getEsAlumno()==1){
                    clienteA.setCursosAlumnos(new ArrayList<>());
                    clienteA.setModuloAlumno(new ArrayList<>());
                    alumnoCurso.setAlumnoId(clienteA);
                    alumnoCurso.setCursoId(comboCurso.getValue());
                    clienteA.getCursosAlumnos().add(alumnoCurso);
                    if(comboCurso.getValue()!=null){
                        for(int i=0;i<comboCurso.getValue().getModulos().size();i++){
                            AlumnoModulo alumnoModulito = new AlumnoModulo(); // Crear una nueva instancia dentro del bucle
                            alumnoModulito.setAlumnoId(clienteA);
                            alumnoModulito.setModuloId(comboCurso.getValue().getModulos().get(i));
                            clienteA.getModuloAlumno().add(alumnoModulito);
                        }


                    }else{
                        clienteA.setModuloAlumno(null);
                        clienteA.setCursosAlumnos(null);
                    }

                    PersonalBolsaDAOImplement daoAlumno=new PersonalBolsaDAOImplement();
                    daoAlumno.subir(clienteA);
                }else{
                    if(comboCursoTr.getValue().toString().equals("Con Cursos")){
                        clienteA.setNotaFinal(spNotaCurso.getValue());
                        PersonalBolsaDAOImplement daoTrabajador=new PersonalBolsaDAOImplement();
                        daoTrabajador.subir(clienteA);
                    }else{
                        PersonalBolsaDAOImplement daoTrabajador=new PersonalBolsaDAOImplement();
                        daoTrabajador.subir(clienteA);
                    }

                }
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
                textNombre.clear();  textApellido1.clear(); textApellido2.clear();  textEmail.clear();
                textTelefono.clear(); textLicenciaArmas.clear();dateFecha.setValue(null);  textCamiseta.clear();
                textIBAN.clear();   textSegSocial.clear();  textTitulacion.clear();  textResidencia.clear();
                String rutaImagen=RegistroAlumnoController.class.getClassLoader().getResource("imagenes/imagenDefectoPerfil.png").toExternalForm();
                imagenPerfil.setImage(new Image(rutaImagen));    labelURL.setText(""); radioMujer.setSelected(false); radioHombre.setSelected(false);
                spAltura.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0,2.10,0.0,0.1));
                textDni.clear();textAreaTIP.clear(); radioAlumno.setSelected(false); radioTrabajador.setSelected(false); flowTrabajo.setVisible(false);
                flowPaneCurso.setVisible(false);
                if(Utilidad.getComprDNI()==false){
                    Alert alerta=new Alert(Alert.AlertType.CONFIRMATION);
                    alerta.setTitle("OK!");
                    alerta.setHeaderText("Alumno Insertado Con Éxito");
                    Optional<ButtonType> tipo=alerta.showAndWait();
                    if(tipo.get()==ButtonType.OK){
                       EnvioCorreoElectronico.enviar(clienteA.getCorreo(), clienteA.getNombre());

                    }

                }
                Utilidad.setComprDNI(false);

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

                if(radioAlumno.isSelected()==false&&radioTrabajador.isSelected()==false){
                    radioAlumno.setStyle("-fx-border-color: #B30909");
                    radioTrabajador.setStyle("-fx-border-color: #B30909");
                }
                if(textDni.getText().isEmpty()){
                    textDni.setStyle("-fx-border-color: #B30909");
                }
                if(textEmail.getText().isEmpty()){
                    textEmail.setStyle("-fx-border-color: #B30909");
                }
                if(radioMujer.isSelected()==false&&radioHombre.isSelected()==false){
                    radioMujer.setStyle("-fx-border-color: #B30909");
                    radioHombre.setStyle("-fx-border-color: #B30909");
                }
            }
        }else{
            if(!textNombre.getText().isEmpty()&&!textApellido1.getText().isEmpty()&&!textApellido2.getText().isEmpty()
            &&!textDni.getText().isEmpty()&&!textEmail.getText().isEmpty()){
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

                String titulacion=textTitulacion.getText().toLowerCase();
                titulacion=titulacion.replace("á","a");
                titulacion=titulacion.replace("é","e");
                titulacion=titulacion.replace("í","i");
                titulacion=titulacion.replace("ó","o");
                titulacion=titulacion.replace("ú","u");
                String idioma= textIdioma.getText().toLowerCase();
                idioma=idioma.replace("á","a");
                idioma=idioma.replace("é","e");
                idioma=idioma.replace("í","i");
                idioma=idioma.replace("ó","o");
                idioma=idioma.replace("ú","u");
                Utilidad.getAlumno().setNombre(textNombre.getText().strip());
                Utilidad.getAlumno().setApellido1(textApellido1.getText().strip());
                Utilidad.getAlumno().setApellido2(textApellido2.getText().strip());
                Utilidad.getAlumno().setCorreo(textEmail.getText().strip());
                Utilidad.getAlumno().setTelefono(textTelefono.getText().strip());
                Utilidad.getAlumno().setLicenciaArma(textLicenciaArmas.getText());
                Utilidad.getAlumno().setFechaNacimiento(dateFecha.getValue()!=null?Date.valueOf(dateFecha.getValue()):null);
                Utilidad.getAlumno().setTallaCamiseta(textCamiseta.getText().strip());
                Utilidad.getAlumno().setNumeroCuenta(textIBAN.getText().strip());
                Utilidad.getAlumno().setNumeroSocial(textSegSocial.getText().strip());
                Utilidad.getAlumno().setTitulacion(titulacion);
                Utilidad.getAlumno().setLugarResidencia(textResidencia.getText().strip());
                Utilidad.getAlumno().setImagenPerfil(imagenCargada);
                Utilidad.getAlumno().setCurriculumUrl((parseo));
                Utilidad.getAlumno().setDni(textDni.getText().strip());
                Utilidad.getAlumno().setNumeroTip(textAreaTIP.getText());
                Utilidad.getAlumno().setAltura(spAltura.getValue());
                Utilidad.getAlumno().setIdioma(idioma);
                Utilidad.getAlumno().setSexo((radioMujer.isSelected()?'m':radioHombre.isSelected()?'h':'x'));
                Long seleccionado=0L;
                if(comboRol.getValue().equals("Alumno")){
                    seleccionado=1L;
                }else if(comboRol.getValue().equals("Trabajador")){
                    seleccionado=2L;
                }else{
                    seleccionado=3L;
                }
                Utilidad.getAlumno().setEsAlumno(seleccionado);
                Utilidad.setAlumno((new PersonalBolsaDAOImplement()).modPersonalBolsa(Utilidad.getAlumno()));
               if(Utilidad.getComprDNI()==false){
                   Alert alerta=new Alert(Alert.AlertType.CONFIRMATION);
                   alerta.setTitle("OK!");
                   alerta.setHeaderText("Alumno Modificado Con Éxito "+(botonEnvio?"y Correo Enviado":""));
                   Optional<ButtonType> tipo=alerta.showAndWait();
                   if(tipo.get()==ButtonType.OK){
                       recojidaNombre=Utilidad.getAlumno().getNombre();
                       recojidaCorreo=Utilidad.getAlumno().getCorreo();
                       Utilidad.setAlumno(null);
                       Utilidad.setCurso(null);
                       if(botonEnvio==false){
                           HelloApplication.cambioVentana("todos-alumnos-view.fxml");
                       }

                   }
               }
               Utilidad.setComprDNI(false);

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
                if(textEmail.getText().isEmpty()){
                    textEmail.setStyle("-fx-border-color: #B30909");
                }

            }

        }
        } catch (DNIIncorrecto e) {
          Alert alerta=new Alert(Alert.AlertType.ERROR);
          alerta.setTitle("ERROR");
          alerta.setHeaderText("DNI Incorrecto");
          alerta.setContentText("Por favor asegurese de que el dni es correcto");
          alerta.showAndWait();
        }




        }

    @javafx.fxml.FXML
    public void abrirCurriculum(ActionEvent actionEvent) {
        try(FileOutputStream fos=new FileOutputStream("."+File.separator+"Curriculums"+File.separator+"curriculum.pdf",false)) {
            fos.write(Utilidad.getAlumno().getCurriculumUrl());
            File filePath=new File("."+File.separator+"Curriculums"+File.separator+"curriculum.pdf");
            Desktop.getDesktop().open(filePath);
        } catch (FileNotFoundException e) {
            Alert alerta=new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText("Sin Archivo");
            alerta.setContentText("Asegurese de que el alumno tenga un archivo pdf de curriculum subido.");
            alerta.showAndWait();
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @javafx.fxml.FXML
    public void enviarCorreoNuevo(ActionEvent actionEvent) {
    }

}