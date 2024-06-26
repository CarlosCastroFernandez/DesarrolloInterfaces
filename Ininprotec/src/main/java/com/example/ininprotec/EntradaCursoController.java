package com.example.ininprotec;

import Util.EnvioCorreoElectronico;
import Util.MYSQLUtil;
import Util.Utilidad;
import clase.AlumnoCurso;
import clase.PersonalBolsa;
import implement.AlumnoCursoDAOImplement;
import implement.PersonalBolsaDAOImplement;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.fonts.SimpleFontFamily;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class EntradaCursoController implements Initializable {
    @javafx.fxml.FXML
    private TableView<PersonalBolsa> tablaCurso;
    @javafx.fxml.FXML
    private TableColumn<PersonalBolsa, String> cModulo1;
    @javafx.fxml.FXML
    private TableColumn<PersonalBolsa, String> cModulo2;
    @javafx.fxml.FXML
    private TableColumn<PersonalBolsa, String> cModulo3;
    @javafx.fxml.FXML
    private TableColumn<PersonalBolsa, String> cModulo4;
    @javafx.fxml.FXML
    private TableColumn<PersonalBolsa, String> cModulo5;
    @javafx.fxml.FXML
    private Label labelTitutlo;
    @javafx.fxml.FXML
    private TextField textBuscador;
    @javafx.fxml.FXML
    private TableColumn<PersonalBolsa, String> cNombre;
    @javafx.fxml.FXML
    private TableColumn<PersonalBolsa, String> cModulo6;
    @javafx.fxml.FXML
    private TableColumn<PersonalBolsa, String> cModulo7;
    @javafx.fxml.FXML
    private Label labelM1;
    @javafx.fxml.FXML
    private Spinner spM1;
    @javafx.fxml.FXML
    private Label labelM2;
    @javafx.fxml.FXML
    private Spinner spM2;
    @javafx.fxml.FXML
    private Label labelM3;
    @javafx.fxml.FXML
    private Spinner spM3;
    @javafx.fxml.FXML
    private Label labelM4;
    @javafx.fxml.FXML
    private Spinner spM4;
    @javafx.fxml.FXML
    private Label labelM5;
    @javafx.fxml.FXML
    private Spinner spM5;
    @javafx.fxml.FXML
    private Label labelM6;
    @javafx.fxml.FXML
    private Spinner spM6;
    @javafx.fxml.FXML
    private Label labelM7;
    @javafx.fxml.FXML
    private Spinner spM7;
    @javafx.fxml.FXML
    private Button botonGuardad;
    private PersonalBolsa personalElegido;
    @javafx.fxml.FXML
    private ImageView imagenActualizar;
    @javafx.fxml.FXML
    private ImageView imagenAddAlumno;
    private ObservableList<PersonalBolsa> alumnos;
    private ObservableList<PersonalBolsa> filtroAlumnos = FXCollections.observableArrayList();
    @javafx.fxml.FXML
    private TableColumn<PersonalBolsa, String> cNotaFinal;
    @javafx.fxml.FXML
    private Button botonAlumnosNUevos;
    @javafx.fxml.FXML
    private Button botonListadoAlumnos;
    @javafx.fxml.FXML
    private Button botonCertificado;
    @javafx.fxml.FXML
    private ImageView imagenFlecha;
    @javafx.fxml.FXML
    private Spinner <Double>spAltura;
    @javafx.fxml.FXML
    private ComboBox comboIdioma;
    @javafx.fxml.FXML
    private ComboBox comboFormacion;
    @javafx.fxml.FXML
    private DatePicker dateDesde;
    @javafx.fxml.FXML
    private DatePicker dateHasta;
    @javafx.fxml.FXML
    private Button botonBuscar;
    @javafx.fxml.FXML
    private Button botonQuitar;
    private HashMap<String,String>copiado=new HashMap<>();
    private HashMap<String,String>sentencia=new HashMap<>();
    private  HashMap<String,Object>parametro=new HashMap<>();
    private ObservableList<PersonalBolsa>filtrado=FXCollections.observableArrayList();
    private ChangeListener<String> listenerFormacion;
    private ChangeListener<String> listenerIdioma;
    @javafx.fxml.FXML
    private TableColumn <PersonalBolsa,String>cFechaFinalizacion;
    private Boolean boleano=false;
    private Boolean otroBoleano=false;
    private ObservableList<PersonalBolsa>alumnosNuevos=FXCollections.observableArrayList();
    @javafx.fxml.FXML
    private Button botonDesapuntar;
    @javafx.fxml.FXML
    private TableColumn  <PersonalBolsa,String>cAltura;
    @javafx.fxml.FXML
    private TableColumn  <PersonalBolsa,String>cIdioma;
    @javafx.fxml.FXML
    private TableColumn  <PersonalBolsa,String>cFormacion;
    @javafx.fxml.FXML
    private RadioButton radioHombre;
    @javafx.fxml.FXML
    private ToggleGroup vale;
    @javafx.fxml.FXML
    private RadioButton radioMujer;
    @javafx.fxml.FXML
    private ChoiceBox <String>comboSigno;
    private String nombreCurso;
    private String certificadoAño;
    private String horas;
    private String dias;
    private String mes;
    private Boolean sinSello;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image imagenFlechia=new Image(EntradaCursoController.class.getClassLoader().getResource("imagenes/flechita.png").toExternalForm());
        imagenFlecha.setOnMouseClicked(mouseEvent -> {
            HelloApplication.cambioVentana("principal-view.fxml");
            Utilidad.setCurso(null);
        });
        labelTitutlo.setText(Utilidad.getCurso().getNombre());
        Image imagen = new Image(EntradaCursoController.class.getClassLoader().getResource("imagenes/recargar.png").toExternalForm());
        Image imagenAgregar = new Image(EntradaCursoController.class.getClassLoader().getResource("imagenes/agregar.png").toExternalForm());
        imagenFlecha.setImage(imagenFlechia);
        imagenAddAlumno.setImage(imagenAgregar);
        imagenActualizar.setImage(imagen);
        spAltura.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0,2.10,0.0,0.1));
        comboIdioma.getItems().add("Ingles");
        comboIdioma.getItems().add("Árabe");
        comboIdioma.getItems().add("Francés");
        comboFormacion.getItems().add("Militar");
        comboFormacion.getItems().add("TCCC");
        comboFormacion.getItems().add("CPO");
        comboSigno.getItems().add("<");
        comboSigno.getItems().add(">");
        comboSigno.getItems().add("=");
        comboSigno.getItems().add("<=");
        comboSigno.getItems().add(">=");
        radioHombre.setOnAction(actionEvent -> {
            copiado.put("sexo","alumnoId.sexo=:sexo");
            parametro.put("sexo",'h');
        });
        radioMujer.setOnAction(actionEvent -> {
            copiado.put("sexo","alumnoId.sexo=:sexo");
            parametro.put("sexo",'m');
        });
        imagenActualizar.setOnMouseClicked(evento -> {
            alumnos.clear();
            alumnos.addAll(new PersonalBolsaDAOImplement().getAllByCursoId(Utilidad.getCurso()));
            tablaCurso.setItems(alumnos);
        });
        listenerFormacion=(((observableValue, s, t1) -> {
            copiado.put("formacion","alumnoId.titulacion LIKE:titulacion");
            parametro.put("formacion",comboFormacion.getValue().toString().toLowerCase());
        }));
        comboFormacion.getSelectionModel().selectedItemProperty().addListener(listenerFormacion);

        listenerIdioma=(((observableValue, curso, t1) -> {
            copiado.put("idioma","alumnoId.idioma LIKE:idioma");
            parametro.put("idioma",comboIdioma.getValue().toString().toLowerCase());
        }));
        comboIdioma.getSelectionModel().selectedItemProperty().addListener(listenerIdioma);

        switch (Utilidad.getCurso().getModulos().size()) {
            case 1:
                labelM1.setText(Utilidad.getCurso().getModulos().get(0).getNombre());
                spM1.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                cModulo1.setText(Utilidad.getCurso().getModulos().get(0).getNombre());
                labelM2.setVisible(false);
                labelM3.setVisible(false);
                labelM4.setVisible(false);
                labelM5.setVisible(false);
                labelM6.setVisible(false);
                labelM7.setVisible(false);
                spM2.setVisible(false);
                spM3.setVisible(false);
                spM4.setVisible(false);
                spM5.setVisible(false);
                spM6.setVisible(false);
                spM7.setVisible(false);
                cModulo2.setVisible(false);
                cModulo3.setVisible(false);
                cModulo4.setVisible(false);
                cModulo5.setVisible(false);
                cModulo6.setVisible(false);
                cModulo7.setVisible(false);
                cIdioma.setCellValueFactory(alumno->{
                    String fecha=alumno.getValue().getIdioma();
                    return new SimpleStringProperty(fecha);
                });
                cFormacion.setCellValueFactory(alumno->{
                    String fecha=alumno.getValue().getTitulacion();
                    return new SimpleStringProperty(fecha);
                });
                cAltura.setCellValueFactory(alumno->{
                    String fecha=String.valueOf(alumno.getValue().getAltura());
                    return new SimpleStringProperty(fecha);
                });
                cFechaFinalizacion.setCellValueFactory(alumno->{
                    String fecha="";
                    for(int i=0;i<alumno.getValue().getCursosAlumnos().size();i++){
                        if(alumno.getValue().getCursosAlumnos().get(i).getCursoId().getId()==Utilidad.getCurso().getId()){
                            fecha=alumno.getValue().getCursosAlumnos().get(i).getFechaFin();
                        }
                    }
                    return new SimpleStringProperty((fecha==null?"":fecha));
                });
                cNombre.setCellValueFactory((alumno) -> {
                    String nombre = alumno.getValue().getNombre() + " " + alumno.getValue().getApellido1() + " " + alumno.getValue().getApellido2();
                    return new SimpleStringProperty(nombre);
                });
                cNotaFinal.setCellValueFactory((alumno) -> {
                    AlumnoCurso alumnoCurso=(new AlumnoCursoDAOImplement().getAlumnoCursoById(alumno.getValue(),Utilidad.getCurso()));
                   String nota=String.valueOf(alumnoCurso.getNotaCurso());
                    return new SimpleStringProperty((nota.equals("null") ? "" : nota));

                });
                cModulo1.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModuloAlumno().size(); i++) {
                        if (alumno.getValue().getModuloAlumno().get(i).getModuloId().getNombre().contains(cModulo1.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModuloAlumno().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null") ? "" : nota));
                });
                break;
            case 2:
                labelM1.setText(Utilidad.getCurso().getModulos().get(0).getNombre());
                labelM2.setText(Utilidad.getCurso().getModulos().get(1).getNombre());
                spM1.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM2.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                labelM3.setVisible(false);
                labelM4.setVisible(false);
                labelM5.setVisible(false);
                labelM6.setVisible(false);
                labelM7.setVisible(false);
                spM3.setVisible(false);
                spM4.setVisible(false);
                spM5.setVisible(false);
                spM6.setVisible(false);
                spM7.setVisible(false);
                cModulo3.setVisible(false);
                cModulo4.setVisible(false);
                cModulo5.setVisible(false);
                cModulo6.setVisible(false);
                cModulo7.setVisible(false);
                cModulo1.setText(Utilidad.getCurso().getModulos().get(0).getNombre());
                cModulo2.setText(Utilidad.getCurso().getModulos().get(1).getNombre());
                cIdioma.setCellValueFactory(alumno->{
                    String fecha=alumno.getValue().getIdioma();
                    return new SimpleStringProperty(fecha);
                });
                cFormacion.setCellValueFactory(alumno->{
                    String fecha=alumno.getValue().getTitulacion();
                    return new SimpleStringProperty(fecha);
                });
                cAltura.setCellValueFactory(alumno->{
                    String fecha=String.valueOf(alumno.getValue().getAltura());
                    return new SimpleStringProperty(fecha);
                });
                cFechaFinalizacion.setCellValueFactory(alumno->{
                    String fecha="";
                    for(int i=0;i<alumno.getValue().getCursosAlumnos().size();i++){
                        if(alumno.getValue().getCursosAlumnos().get(i).getCursoId().getId()==Utilidad.getCurso().getId()){
                            fecha=alumno.getValue().getCursosAlumnos().get(i).getFechaFin();
                        }
                    }
                    return new SimpleStringProperty((fecha==null?"":fecha));
                });
                cNombre.setCellValueFactory((alumno) -> {
                    String nombre = alumno.getValue().getNombre() + " " + alumno.getValue().getApellido1() + " " + alumno.getValue().getApellido2();
                    return new SimpleStringProperty(nombre);
                });
                cNotaFinal.setCellValueFactory((alumno) -> {
                    AlumnoCurso alumnoCurso=(new AlumnoCursoDAOImplement().getAlumnoCursoById(alumno.getValue(),Utilidad.getCurso()));
                    String nota=String.valueOf(alumnoCurso.getNotaCurso());
                    return new SimpleStringProperty((nota.equals("null") ? "" : nota));

                });
                cModulo1.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModuloAlumno().size(); i++) {
                        if (alumno.getValue().getModuloAlumno().get(i).getModuloId().getNombre().contains(cModulo1.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModuloAlumno().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null") ? "" : nota));
                });
                cModulo2.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModuloAlumno().size(); i++) {
                        if (alumno.getValue().getModuloAlumno().get(i).getModuloId().getNombre().contains(cModulo2.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModuloAlumno().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null") ? "" : nota));
                });
                break;
            case 3:
                labelM1.setText(Utilidad.getCurso().getModulos().get(0).getNombre());
                labelM2.setText(Utilidad.getCurso().getModulos().get(1).getNombre());
                labelM3.setText(Utilidad.getCurso().getModulos().get(2).getNombre());
                spM1.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM2.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM3.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                labelM4.setVisible(false);
                labelM5.setVisible(false);
                labelM6.setVisible(false);
                labelM7.setVisible(false);
                spM4.setVisible(false);
                spM5.setVisible(false);
                spM6.setVisible(false);
                spM7.setVisible(false);
                cModulo4.setVisible(false);
                cModulo5.setVisible(false);
                cModulo6.setVisible(false);
                cModulo7.setVisible(false);
                cModulo1.setText(Utilidad.getCurso().getModulos().get(0).getNombre());
                cModulo3.setText(Utilidad.getCurso().getModulos().get(2).getNombre());
                cModulo2.setText(Utilidad.getCurso().getModulos().get(1).getNombre());
                cIdioma.setCellValueFactory(alumno->{
                    String fecha=alumno.getValue().getIdioma();
                    return new SimpleStringProperty(fecha);
                });
                cFormacion.setCellValueFactory(alumno->{
                    String fecha=alumno.getValue().getTitulacion();
                    return new SimpleStringProperty(fecha);
                });
                cAltura.setCellValueFactory(alumno->{
                    String fecha=String.valueOf(alumno.getValue().getAltura());
                    return new SimpleStringProperty(fecha);
                });
                cFechaFinalizacion.setCellValueFactory(alumno->{
                    String fecha="";
                    for(int i=0;i<alumno.getValue().getCursosAlumnos().size();i++){
                        if(alumno.getValue().getCursosAlumnos().get(i).getCursoId().getId()==Utilidad.getCurso().getId()){
                            fecha=alumno.getValue().getCursosAlumnos().get(i).getFechaFin();
                        }
                    }
                    return new SimpleStringProperty((fecha==null?"":fecha));
                });
                cNombre.setCellValueFactory((alumno) -> {
                    String nombre = alumno.getValue().getNombre() + " " + alumno.getValue().getApellido1() + " " + alumno.getValue().getApellido2();
                    return new SimpleStringProperty(nombre);
                });
                cNotaFinal.setCellValueFactory((alumno) -> {
                    AlumnoCurso alumnoCurso=(new AlumnoCursoDAOImplement().getAlumnoCursoById(alumno.getValue(),Utilidad.getCurso()));
                    String nota=String.valueOf(alumnoCurso.getNotaCurso());
                    return new SimpleStringProperty((nota.equals("null") ? "" : nota));

                });
                cModulo1.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModuloAlumno().size(); i++) {
                        if (alumno.getValue().getModuloAlumno().get(i).getModuloId().getNombre().contains(cModulo1.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModuloAlumno().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null") ? "" : nota));
                });
                cModulo2.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModuloAlumno().size(); i++) {
                        if (alumno.getValue().getModuloAlumno().get(i).getModuloId().getNombre().contains(cModulo2.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModuloAlumno().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null") ? "" : nota));
                });
                cModulo3.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModuloAlumno().size(); i++) {
                        if (alumno.getValue().getModuloAlumno().get(i).getModuloId().getNombre().contains(cModulo3.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModuloAlumno().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null") ? "" : nota));
                });
                break;
            case 4:
                labelM1.setText(Utilidad.getCurso().getModulos().get(0).getNombre());
                labelM2.setText(Utilidad.getCurso().getModulos().get(1).getNombre());
                labelM3.setText(Utilidad.getCurso().getModulos().get(2).getNombre());
                labelM4.setText(Utilidad.getCurso().getModulos().get(3).getNombre());
                spM4.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM1.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM2.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM3.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                labelM5.setVisible(false);
                labelM6.setVisible(false);
                labelM7.setVisible(false);
                spM5.setVisible(false);
                spM6.setVisible(false);
                spM7.setVisible(false);
                cModulo5.setVisible(false);
                cModulo6.setVisible(false);
                cModulo7.setVisible(false);
                cModulo1.setText(Utilidad.getCurso().getModulos().get(0).getNombre());
                cModulo2.setText(Utilidad.getCurso().getModulos().get(1).getNombre());
                cModulo3.setText(Utilidad.getCurso().getModulos().get(2).getNombre());
                cModulo4.setText(Utilidad.getCurso().getModulos().get(3).getNombre());
                cIdioma.setCellValueFactory(alumno->{
                    String fecha=alumno.getValue().getIdioma();
                    return new SimpleStringProperty(fecha);
                });
                cFormacion.setCellValueFactory(alumno->{
                    String fecha=alumno.getValue().getTitulacion();
                    return new SimpleStringProperty(fecha);
                });
                cAltura.setCellValueFactory(alumno->{
                    String fecha=String.valueOf(alumno.getValue().getAltura());
                    return new SimpleStringProperty(fecha);
                });
                cFechaFinalizacion.setCellValueFactory(alumno->{
                    String fecha="";
                    for(int i=0;i<alumno.getValue().getCursosAlumnos().size();i++){
                        if(alumno.getValue().getCursosAlumnos().get(i).getCursoId().getId()==Utilidad.getCurso().getId()){
                            fecha=alumno.getValue().getCursosAlumnos().get(i).getFechaFin();
                        }
                    }
                    return new SimpleStringProperty((fecha==null?"":fecha));
                });
                cNombre.setCellValueFactory((alumno) -> {
                    String nombre = alumno.getValue().getNombre() + " " + alumno.getValue().getApellido1() + " " + alumno.getValue().getApellido2();
                    return new SimpleStringProperty(nombre);
                });
                cNotaFinal.setCellValueFactory((alumno) -> {
                    AlumnoCurso alumnoCurso=(new AlumnoCursoDAOImplement().getAlumnoCursoById(alumno.getValue(),Utilidad.getCurso()));
                    String nota=String.valueOf(alumnoCurso.getNotaCurso());
                    return new SimpleStringProperty((nota.equals("null") ? "" : nota));

                });
                cModulo1.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModuloAlumno().size(); i++) {
                        if (alumno.getValue().getModuloAlumno().get(i).getModuloId().getNombre().contains(cModulo1.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModuloAlumno().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null") ? "" : nota));
                });
                cModulo2.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModuloAlumno().size(); i++) {
                        if (alumno.getValue().getModuloAlumno().get(i).getModuloId().getNombre().contains(cModulo2.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModuloAlumno().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null") ? "" : nota));
                });
                cModulo3.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModuloAlumno().size(); i++) {
                        if (alumno.getValue().getModuloAlumno().get(i).getModuloId().getNombre().contains(cModulo3.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModuloAlumno().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null") ? "" : nota));
                });
                cModulo4.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModuloAlumno().size(); i++) {
                        if (alumno.getValue().getModuloAlumno().get(i).getModuloId().getNombre().contains(cModulo4.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModuloAlumno().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null") ? "" : nota));
                });
                break;
            case 5:
                labelM1.setText(Utilidad.getCurso().getModulos().get(0).getNombre());
                labelM2.setText(Utilidad.getCurso().getModulos().get(1).getNombre());
                labelM3.setText(Utilidad.getCurso().getModulos().get(2).getNombre());
                labelM4.setText(Utilidad.getCurso().getModulos().get(3).getNombre());
                labelM5.setText(Utilidad.getCurso().getModulos().get(4).getNombre());
                spM5.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM4.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM1.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM2.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM3.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                labelM6.setVisible(false);
                labelM7.setVisible(false);
                spM6.setVisible(false);
                spM7.setVisible(false);

                cModulo6.setVisible(false);
                cModulo7.setVisible(false);
                cModulo1.setText(Utilidad.getCurso().getModulos().get(0).getNombre());
                cModulo2.setText(Utilidad.getCurso().getModulos().get(1).getNombre());
                cModulo3.setText(Utilidad.getCurso().getModulos().get(2).getNombre());
                cModulo4.setText(Utilidad.getCurso().getModulos().get(3).getNombre());
                cModulo5.setText(Utilidad.getCurso().getModulos().get(4).getNombre());
                cIdioma.setCellValueFactory(alumno->{
                    String fecha=alumno.getValue().getIdioma();
                    return new SimpleStringProperty(fecha);
                });
                cFormacion.setCellValueFactory(alumno->{
                    String fecha=alumno.getValue().getTitulacion();
                    return new SimpleStringProperty(fecha);
                });
                cAltura.setCellValueFactory(alumno->{
                    String fecha=String.valueOf(alumno.getValue().getAltura());
                    return new SimpleStringProperty(fecha);
                });
                cFechaFinalizacion.setCellValueFactory(alumno->{
                    String fecha="";
                    for(int i=0;i<alumno.getValue().getCursosAlumnos().size();i++){
                        if(alumno.getValue().getCursosAlumnos().get(i).getCursoId().getId()==Utilidad.getCurso().getId()){
                            fecha=alumno.getValue().getCursosAlumnos().get(i).getFechaFin();
                        }
                    }
                    return new SimpleStringProperty((fecha==null?"":fecha));
                });
                cNombre.setCellValueFactory((alumno) -> {
                    String nombre = alumno.getValue().getNombre() + " " + alumno.getValue().getApellido1() + " " + alumno.getValue().getApellido2();
                    return new SimpleStringProperty(nombre);
                });
                cNotaFinal.setCellValueFactory((alumno) -> {
                    AlumnoCurso alumnoCurso=(new AlumnoCursoDAOImplement().getAlumnoCursoById(alumno.getValue(),Utilidad.getCurso()));
                    String nota=String.valueOf(alumnoCurso.getNotaCurso());
                    return new SimpleStringProperty((nota.equals("null") ? "" : nota));

                });
                cModulo1.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModuloAlumno().size(); i++) {
                        if (alumno.getValue().getModuloAlumno().get(i).getModuloId().getNombre().contains(cModulo1.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModuloAlumno().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null") ? "" : nota));
                });
                cModulo2.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModuloAlumno().size(); i++) {
                        if (alumno.getValue().getModuloAlumno().get(i).getModuloId().getNombre().contains(cModulo2.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModuloAlumno().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null") ? "" : nota));
                });
                cModulo3.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModuloAlumno().size(); i++) {
                        if (alumno.getValue().getModuloAlumno().get(i).getModuloId().getNombre().contains(cModulo3.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModuloAlumno().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null") ? "" : nota));
                });
                cModulo4.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModuloAlumno().size(); i++) {
                        if (alumno.getValue().getModuloAlumno().get(i).getModuloId().getNombre().contains(cModulo4.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModuloAlumno().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null") ? "" : nota));
                });
                cModulo5.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModuloAlumno().size(); i++) {
                        if (alumno.getValue().getModuloAlumno().get(i).getModuloId().getNombre().contains(cModulo5.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModuloAlumno().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null") ? "" : nota));
                });
                break;
            case 6:
                labelM1.setText(Utilidad.getCurso().getModulos().get(0).getNombre());
                labelM2.setText(Utilidad.getCurso().getModulos().get(1).getNombre());
                labelM3.setText(Utilidad.getCurso().getModulos().get(2).getNombre());
                labelM4.setText(Utilidad.getCurso().getModulos().get(3).getNombre());
                labelM5.setText(Utilidad.getCurso().getModulos().get(4).getNombre());
                labelM6.setText(Utilidad.getCurso().getModulos().get(5).getNombre());
                spM6.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM5.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM4.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM1.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM2.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM3.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                labelM7.setVisible(false);
                spM7.setVisible(false);
                cModulo7.setVisible(false);
                cModulo1.setText(Utilidad.getCurso().getModulos().get(0).getNombre());
                cModulo2.setText(Utilidad.getCurso().getModulos().get(1).getNombre());
                cModulo3.setText(Utilidad.getCurso().getModulos().get(2).getNombre());
                cModulo4.setText(Utilidad.getCurso().getModulos().get(3).getNombre());
                cModulo5.setText(Utilidad.getCurso().getModulos().get(4).getNombre());
                cModulo6.setText(Utilidad.getCurso().getModulos().get(5).getNombre());
                cIdioma.setCellValueFactory(alumno->{
                    String fecha=alumno.getValue().getIdioma();
                    return new SimpleStringProperty(fecha);
                });
                cFormacion.setCellValueFactory(alumno->{
                    String fecha=alumno.getValue().getTitulacion();
                    return new SimpleStringProperty(fecha);
                });
                cAltura.setCellValueFactory(alumno->{
                    String fecha=String.valueOf(alumno.getValue().getAltura());
                    return new SimpleStringProperty(fecha);
                });
                cFechaFinalizacion.setCellValueFactory(alumno->{
                    String fecha="";
                    for(int i=0;i<alumno.getValue().getCursosAlumnos().size();i++){
                        if(alumno.getValue().getCursosAlumnos().get(i).getCursoId().getId()==Utilidad.getCurso().getId()){
                            fecha=alumno.getValue().getCursosAlumnos().get(i).getFechaFin();
                        }
                    }
                    return new SimpleStringProperty((fecha==null?"":fecha));
                });
                cNombre.setCellValueFactory((alumno) -> {
                    String nombre = alumno.getValue().getNombre() + " " + alumno.getValue().getApellido1() + " " + alumno.getValue().getApellido2();
                    return new SimpleStringProperty(nombre);
                });
                cNotaFinal.setCellValueFactory((alumno) -> {
                    AlumnoCurso alumnoCurso=(new AlumnoCursoDAOImplement().getAlumnoCursoById(alumno.getValue(),Utilidad.getCurso()));
                    String nota=String.valueOf(alumnoCurso.getNotaCurso());
                    return new SimpleStringProperty((nota.equals("null") ? "" : nota));

                });
                cModulo1.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModuloAlumno().size(); i++) {
                        if (alumno.getValue().getModuloAlumno().get(i).getModuloId().getNombre().contains(cModulo1.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModuloAlumno().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null") ? "" : nota));
                });
                cModulo2.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModuloAlumno().size(); i++) {
                        if (alumno.getValue().getModuloAlumno().get(i).getModuloId().getNombre().contains(cModulo2.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModuloAlumno().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null") ? "" : nota));
                });
                cModulo3.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModuloAlumno().size(); i++) {
                        if (alumno.getValue().getModuloAlumno().get(i).getModuloId().getNombre().contains(cModulo3.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModuloAlumno().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null") ? "" : nota));
                });
                cModulo4.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModuloAlumno().size(); i++) {
                        if (alumno.getValue().getModuloAlumno().get(i).getModuloId().getNombre().contains(cModulo4.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModuloAlumno().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null") ? "" : nota));
                });
                cModulo5.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModuloAlumno().size(); i++) {
                        if (alumno.getValue().getModuloAlumno().get(i).getModuloId().getNombre().contains(cModulo5.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModuloAlumno().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null") ? "" : nota));
                });
                cModulo6.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModuloAlumno().size(); i++) {
                        if (alumno.getValue().getModuloAlumno().get(i).getModuloId().getNombre().contains(cModulo6.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModuloAlumno().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null") ? "" : nota));
                });
                break;
            case 7:
                labelM1.setText(Utilidad.getCurso().getModulos().get(0).getNombre());
                labelM2.setText(Utilidad.getCurso().getModulos().get(1).getNombre());
                labelM3.setText(Utilidad.getCurso().getModulos().get(2).getNombre());
                labelM4.setText(Utilidad.getCurso().getModulos().get(3).getNombre());
                labelM5.setText(Utilidad.getCurso().getModulos().get(4).getNombre());
                labelM6.setText(Utilidad.getCurso().getModulos().get(5).getNombre());
                labelM7.setText(Utilidad.getCurso().getModulos().get(6).getNombre());
                spM7.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM6.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM5.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM4.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM1.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM2.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM3.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                cModulo1.setText(Utilidad.getCurso().getModulos().get(0).getNombre());
                cModulo2.setText(Utilidad.getCurso().getModulos().get(1).getNombre());
                cModulo3.setText(Utilidad.getCurso().getModulos().get(2).getNombre());
                cModulo4.setText(Utilidad.getCurso().getModulos().get(3).getNombre());
                cModulo5.setText(Utilidad.getCurso().getModulos().get(4).getNombre());
                cModulo6.setText(Utilidad.getCurso().getModulos().get(5).getNombre());
                cModulo7.setText(Utilidad.getCurso().getModulos().get(6).getNombre());
                cIdioma.setCellValueFactory(alumno->{
                    String fecha=alumno.getValue().getIdioma();
                    return new SimpleStringProperty(fecha);
                });
                cFormacion.setCellValueFactory(alumno->{
                    String fecha=alumno.getValue().getTitulacion();
                    return new SimpleStringProperty(fecha);
                });
                cAltura.setCellValueFactory(alumno->{
                    String fecha=String.valueOf(alumno.getValue().getAltura());
                    return new SimpleStringProperty(fecha);
                });
                cFechaFinalizacion.setCellValueFactory(alumno->{
                    String fecha="";
                    for(int i=0;i<alumno.getValue().getCursosAlumnos().size();i++){
                        if(alumno.getValue().getCursosAlumnos().get(i).getCursoId().getId()==Utilidad.getCurso().getId()){
                            fecha=alumno.getValue().getCursosAlumnos().get(i).getFechaFin();
                        }
                    }
                    return new SimpleStringProperty((fecha==null?"":fecha));
                });
                cNombre.setCellValueFactory((alumno) -> {
                    String nombre = alumno.getValue().getNombre() + " " + alumno.getValue().getApellido1() + " " + alumno.getValue().getApellido2();
                    return new SimpleStringProperty(nombre);
                });
                cNotaFinal.setCellValueFactory((alumno) -> {
                    AlumnoCurso alumnoCurso=(new AlumnoCursoDAOImplement().getAlumnoCursoById(alumno.getValue(),Utilidad.getCurso()));
                    String nota=String.valueOf(alumnoCurso.getNotaCurso());
                    return new SimpleStringProperty((nota.equals("null") ? "" : nota));

                });
                cModulo1.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModuloAlumno().size(); i++) {
                        if (alumno.getValue().getModuloAlumno().get(i).getModuloId().getNombre().contains(cModulo1.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModuloAlumno().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null") ? "" : nota));
                });
                cModulo2.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModuloAlumno().size(); i++) {
                        if (alumno.getValue().getModuloAlumno().get(i).getModuloId().getNombre().contains(cModulo2.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModuloAlumno().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null") ? "" : nota));
                });
                cModulo3.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModuloAlumno().size(); i++) {
                        if (alumno.getValue().getModuloAlumno().get(i).getModuloId().getNombre().contains(cModulo3.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModuloAlumno().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null") ? "" : nota));
                });
                cModulo4.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModuloAlumno().size(); i++) {
                        if (alumno.getValue().getModuloAlumno().get(i).getModuloId().getNombre().contains(cModulo4.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModuloAlumno().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null") ? "" : nota));
                });
                cModulo5.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModuloAlumno().size(); i++) {
                        if (alumno.getValue().getModuloAlumno().get(i).getModuloId().getNombre().contains(cModulo5.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModuloAlumno().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null") ? "" : nota));
                });
                cModulo6.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModuloAlumno().size(); i++) {
                        if (alumno.getValue().getModuloAlumno().get(i).getModuloId().getNombre().contains(cModulo6.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModuloAlumno().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null") ? "" : nota));
                });
                cModulo7.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModuloAlumno().size(); i++) {
                        if (alumno.getValue().getModuloAlumno().get(i).getModuloId().getNombre().contains(cModulo7.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModuloAlumno().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null") ? "" : nota));
                });
                break;


        }
        alumnos = FXCollections.observableArrayList();
        alumnos.addAll((new PersonalBolsaDAOImplement().getAllByCursoId(Utilidad.getCurso())));
        tablaCurso.setItems(alumnos);

        tablaCurso.getSelectionModel().selectedItemProperty().addListener((observableValue, personalBolsa, t1) -> {
            if (!tablaCurso.getItems().isEmpty()) {
                personalElegido = t1;


            }

        });
        tablaCurso.setRowFactory(tv -> {
            TableRow<PersonalBolsa> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                    PersonalBolsa clickedRow = row.getItem();
                    Utilidad.setAlumno(clickedRow);
                    HelloApplication.cambioVentana("registroAlumno-view.fxml");
                }
            });
            return row;
        });


    }


    @javafx.fxml.FXML
    public void guardarNotas(ActionEvent actionEvent) {
        if (personalElegido != null) {
            Double notaCurso=0.0;
            Integer posicionCurso=0;
            switch (Utilidad.getCurso().getModulos().size()) {
                case 1:
                    for (int i = 0; i < personalElegido.getModuloAlumno().size(); i++) {
                        if (personalElegido.getModuloAlumno().get(i).getModuloId().getNombre().contains(labelM1.getText())) {
                            personalElegido.getModuloAlumno().get(i).setNotaModulo(Double.valueOf(spM1.getValue().toString()));
                            notaCurso+=personalElegido.getModuloAlumno().get(i).getNotaModulo();
                        }


                    }
                    notaCurso=notaCurso/Utilidad.getCurso().getModulos().size();

                    for(int i=0;i<personalElegido.getCursosAlumnos().size();i++){
                        if(personalElegido.getCursosAlumnos().get(i).getCursoId().getNombre().equals(Utilidad.getCurso().getNombre())){
                            if(personalElegido.getNotaFinal()==null){
                                personalElegido.setNotaFinal(notaCurso);
                            }else if(personalElegido.getCursosAlumnos().get(i).getNotaCurso()!=null){
                                Integer numeroCursos=numeroCursosConNonta(personalElegido);
                                Double notaDevuelta=numeroCursos*personalElegido.getNotaFinal();
                                Double resta=null;
                                if(notaDevuelta>personalElegido.getCursosAlumnos().get(i).getNotaCurso()){
                                    resta=notaDevuelta-personalElegido.getCursosAlumnos().get(i).getNotaCurso();
                                }else if(notaDevuelta<personalElegido.getCursosAlumnos().get(i).getNotaCurso()){
                                    resta=personalElegido.getCursosAlumnos().get(i).getNotaCurso()-notaDevuelta;
                                }else{
                                    resta=personalElegido.getCursosAlumnos().get(i).getNotaCurso()-notaDevuelta;
                                }
                                resta+=notaCurso;
                                Double notaCalculada=resta/numeroCursos;
                                Double notaFinal= (double) (Math.round(notaCalculada*10.0)/10.0);
                                personalElegido.setNotaFinal(notaFinal);

                            }else{
                                Integer numeroCursos=numeroCursosConNonta(personalElegido);
                                if(numeroCursos==0){
                                    numeroCursos=1;
                                }
                                Double notaDevuelta= personalElegido.getNotaFinal()*numeroCursos;
                                numeroCursos=numeroCursosConNonta(personalElegido);
                                notaDevuelta+=notaCurso;
                                Double notaMediaFinal=notaDevuelta/(numeroCursos+1);
                                Double redondeo= (double) (Math.round(notaMediaFinal*10.0)/10.0);
                                personalElegido.setNotaFinal(redondeo);

                            }
                            personalElegido.getCursosAlumnos().get(i).setNotaCurso(notaCurso);
                            LocalDate fechaFinalizacion=LocalDate.now();
                            DateTimeFormatter formato=DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            String fromal=fechaFinalizacion.format(formato);
                            if(personalElegido.getCursosAlumnos().get(i).getFechaFin()==null){
                                personalElegido.getCursosAlumnos().get(i).setFechaFin(fromal);
                            }
                        }

                    }

                    break;
                case 2:
                    for (int i = 0; i < personalElegido.getModuloAlumno().size(); i++) {
                        if (personalElegido.getModuloAlumno().get(i).getModuloId().getNombre().contains(labelM1.getText())) {
                            personalElegido.getModuloAlumno().get(i).setNotaModulo(Double.valueOf(spM1.getValue().toString()));
                            notaCurso+=personalElegido.getModuloAlumno().get(i).getNotaModulo();
                        } else if (personalElegido.getModuloAlumno().get(i).getModuloId().getNombre().contains(labelM2.getText())) {
                            personalElegido.getModuloAlumno().get(i).setNotaModulo(Double.valueOf(spM2.getValue().toString()));
                            notaCurso+=personalElegido.getModuloAlumno().get(i).getNotaModulo();
                        }


                    }
                    notaCurso=notaCurso/Utilidad.getCurso().getModulos().size();
                    notaCurso=Math.round(notaCurso*10)/10.0;
                    for(int i=0;i<personalElegido.getCursosAlumnos().size();i++){
                        if(personalElegido.getCursosAlumnos().get(i).getCursoId().getNombre().equals(Utilidad.getCurso().getNombre())){
                            if(personalElegido.getNotaFinal()==null){
                                personalElegido.setNotaFinal(notaCurso);
                            }else if(personalElegido.getCursosAlumnos().get(i).getNotaCurso()!=null){
                                Integer numeroCursos=numeroCursosConNonta(personalElegido);
                                Double notaDevuelta=numeroCursos*personalElegido.getNotaFinal();
                                Double resta=null;
                                if(notaDevuelta>personalElegido.getCursosAlumnos().get(i).getNotaCurso()){
                                    resta=notaDevuelta-personalElegido.getCursosAlumnos().get(i).getNotaCurso();
                                }else if(notaDevuelta<personalElegido.getCursosAlumnos().get(i).getNotaCurso()){
                                    resta=personalElegido.getCursosAlumnos().get(i).getNotaCurso()-notaDevuelta;
                                }else{
                                    resta=personalElegido.getCursosAlumnos().get(i).getNotaCurso()-notaDevuelta;
                                }
                                resta+=notaCurso;
                                Double notaCalculada=resta/numeroCursos;
                                Double notaFinal= (double) (Math.round(notaCalculada*10.0)/10.0);
                                personalElegido.setNotaFinal(notaFinal);

                            }else{
                                Integer numeroCursos=numeroCursosConNonta(personalElegido);
                                if(numeroCursos==0){
                                    numeroCursos=1;
                                }
                                Double notaDevuelta= personalElegido.getNotaFinal()*numeroCursos;
                                numeroCursos=numeroCursosConNonta(personalElegido);
                                notaDevuelta+=notaCurso;
                                Double notaMediaFinal=notaDevuelta/(numeroCursos+1);
                                Double redondeo= (double) (Math.round(notaMediaFinal*10.0)/10.0);
                                personalElegido.setNotaFinal(redondeo);

                            }
                            personalElegido.getCursosAlumnos().get(i).setNotaCurso(notaCurso);
                            LocalDate fechaFinalizacion=LocalDate.now();
                            DateTimeFormatter formato=DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            String fromal=fechaFinalizacion.format(formato);
                            if(personalElegido.getCursosAlumnos().get(i).getFechaFin()==null){
                                personalElegido.getCursosAlumnos().get(i).setFechaFin(fromal);
                            }
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < personalElegido.getModuloAlumno().size(); i++) {
                        if (personalElegido.getModuloAlumno().get(i).getModuloId().getNombre().contains(labelM1.getText())) {
                            personalElegido.getModuloAlumno().get(i).setNotaModulo(Double.valueOf(spM1.getValue().toString()));
                            notaCurso+=personalElegido.getModuloAlumno().get(i).getNotaModulo();
                        } else if (personalElegido.getModuloAlumno().get(i).getModuloId().getNombre().contains(labelM2.getText())) {
                            personalElegido.getModuloAlumno().get(i).setNotaModulo(Double.valueOf(spM2.getValue().toString()));
                            notaCurso+=personalElegido.getModuloAlumno().get(i).getNotaModulo();
                        } else if (personalElegido.getModuloAlumno().get(i).getModuloId().getNombre().contains(labelM3.getText())) {
                            personalElegido.getModuloAlumno().get(i).setNotaModulo(Double.valueOf(spM3.getValue().toString()));
                            notaCurso+=personalElegido.getModuloAlumno().get(i).getNotaModulo();
                        }


                    }
                    notaCurso=notaCurso/Utilidad.getCurso().getModulos().size();
                    notaCurso=Math.round(notaCurso*10)/10.0;
                    for(int i=0;i<personalElegido.getCursosAlumnos().size();i++){
                        if(personalElegido.getCursosAlumnos().get(i).getCursoId().getNombre().equals(Utilidad.getCurso().getNombre())){
                            if(personalElegido.getNotaFinal()==null){
                                personalElegido.setNotaFinal(notaCurso);
                            }else if(personalElegido.getCursosAlumnos().get(i).getNotaCurso()!=null){
                                Integer numeroCursos=numeroCursosConNonta(personalElegido);
                                Double notaDevuelta=numeroCursos*personalElegido.getNotaFinal();
                                Double resta=null;
                                if(notaDevuelta>personalElegido.getCursosAlumnos().get(i).getNotaCurso()){
                                    resta=notaDevuelta-personalElegido.getCursosAlumnos().get(i).getNotaCurso();
                                }else if(notaDevuelta<personalElegido.getCursosAlumnos().get(i).getNotaCurso()){
                                    resta=personalElegido.getCursosAlumnos().get(i).getNotaCurso()-notaDevuelta;
                                }else{
                                    resta=personalElegido.getCursosAlumnos().get(i).getNotaCurso()-notaDevuelta;
                                }
                                resta+=notaCurso;
                                Double notaCalculada=resta/numeroCursos;
                                Double notaFinal= (double) (Math.round(notaCalculada*10.0)/10.0);
                                personalElegido.setNotaFinal(notaFinal);
                            }else{
                                Integer numeroCursos=numeroCursosConNonta(personalElegido);
                                if(numeroCursos==0){
                                    numeroCursos=1;
                                }
                                Double notaDevuelta= personalElegido.getNotaFinal()*numeroCursos;
                                numeroCursos=numeroCursosConNonta(personalElegido);
                                notaDevuelta+=notaCurso;
                                Double notaMediaFinal=notaDevuelta/(numeroCursos+1);
                                Double redondeo= (double) (Math.round(notaMediaFinal*10.0)/10.0);
                                personalElegido.setNotaFinal(redondeo);

                            }
                            personalElegido.getCursosAlumnos().get(i).setNotaCurso(notaCurso);
                            LocalDate fechaFinalizacion=LocalDate.now();
                            DateTimeFormatter formato=DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            String fromal=fechaFinalizacion.format(formato);
                            if(personalElegido.getCursosAlumnos().get(i).getFechaFin()==null){
                                personalElegido.getCursosAlumnos().get(i).setFechaFin(fromal);
                            }
                        }

                    }
                    break;
                case 4:
                    for (int i = 0; i < personalElegido.getModuloAlumno().size(); i++) {
                        if (personalElegido.getModuloAlumno().get(i).getModuloId().getNombre().contains(labelM1.getText())) {
                            personalElegido.getModuloAlumno().get(i).setNotaModulo(Double.valueOf(spM1.getValue().toString()));
                            notaCurso+=personalElegido.getModuloAlumno().get(i).getNotaModulo();
                        } else if (personalElegido.getModuloAlumno().get(i).getModuloId().getNombre().contains(labelM2.getText())) {
                            personalElegido.getModuloAlumno().get(i).setNotaModulo(Double.valueOf(spM2.getValue().toString()));
                            notaCurso+=personalElegido.getModuloAlumno().get(i).getNotaModulo();
                        } else if (personalElegido.getModuloAlumno().get(i).getModuloId().getNombre().contains(labelM3.getText())) {
                            personalElegido.getModuloAlumno().get(i).setNotaModulo(Double.valueOf(spM3.getValue().toString()));
                            notaCurso+=personalElegido.getModuloAlumno().get(i).getNotaModulo();
                        } else if (personalElegido.getModuloAlumno().get(i).getModuloId().getNombre().contains(labelM4.getText())) {
                            personalElegido.getModuloAlumno().get(i).setNotaModulo(Double.valueOf(spM4.getValue().toString()));
                            notaCurso+=personalElegido.getModuloAlumno().get(i).getNotaModulo();
                        }

                    }
                    notaCurso=notaCurso/Utilidad.getCurso().getModulos().size();
                    notaCurso=Math.round(notaCurso*10)/10.0;
                    for(int i=0;i<personalElegido.getCursosAlumnos().size();i++){
                        if(personalElegido.getCursosAlumnos().get(i).getCursoId().getNombre().equals(Utilidad.getCurso().getNombre())){
                            if(personalElegido.getNotaFinal()==null){
                                personalElegido.setNotaFinal(notaCurso);
                            }else if(personalElegido.getCursosAlumnos().get(i).getNotaCurso()!=null){
                                Integer numeroCursos=numeroCursosConNonta(personalElegido);
                                Double notaDevuelta=numeroCursos*personalElegido.getNotaFinal();
                                Double resta=null;
                                if(notaDevuelta>personalElegido.getCursosAlumnos().get(i).getNotaCurso()){
                                    resta=notaDevuelta-personalElegido.getCursosAlumnos().get(i).getNotaCurso();
                                }else if(notaDevuelta<personalElegido.getCursosAlumnos().get(i).getNotaCurso()){
                                    resta=personalElegido.getCursosAlumnos().get(i).getNotaCurso()-notaDevuelta;
                                }else{
                                    resta=personalElegido.getCursosAlumnos().get(i).getNotaCurso()-notaDevuelta;
                                }
                                resta+=notaCurso;
                                Double notaCalculada=resta/numeroCursos;
                                Double notaFinal= (double) (Math.round(notaCalculada*10.0)/10.0);
                                personalElegido.setNotaFinal(notaFinal);

                            }else{
                                Integer numeroCursos=numeroCursosConNonta(personalElegido);
                                if(numeroCursos==0){
                                    numeroCursos=1;
                                }
                                Double notaDevuelta= personalElegido.getNotaFinal()*numeroCursos;
                                numeroCursos=numeroCursosConNonta(personalElegido);
                                notaDevuelta+=notaCurso;
                                Double notaMediaFinal=notaDevuelta/(numeroCursos+1);
                                Double redondeo= (double) (Math.round(notaMediaFinal*10.0)/10.0);
                                personalElegido.setNotaFinal(redondeo);

                            }
                            personalElegido.getCursosAlumnos().get(i).setNotaCurso(notaCurso);
                            LocalDate fechaFinalizacion=LocalDate.now();
                            DateTimeFormatter formato=DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            String fromal=fechaFinalizacion.format(formato);
                            if(personalElegido.getCursosAlumnos().get(i).getFechaFin()==null){
                                personalElegido.getCursosAlumnos().get(i).setFechaFin(fromal);
                            }
                        }

                    }
                    break;
                case 5:

                    for (int i = 0; i < personalElegido.getModuloAlumno().size(); i++) {
                        if (personalElegido.getModuloAlumno().get(i).getModuloId().getNombre().contains(labelM1.getText())) {
                            personalElegido.getModuloAlumno().get(i).setNotaModulo(Double.valueOf(spM1.getValue().toString()));
                            notaCurso+=personalElegido.getModuloAlumno().get(i).getNotaModulo();
                        } else if (personalElegido.getModuloAlumno().get(i).getModuloId().getNombre().contains(labelM2.getText())) {
                            personalElegido.getModuloAlumno().get(i).setNotaModulo(Double.valueOf(spM2.getValue().toString()));
                            notaCurso+=personalElegido.getModuloAlumno().get(i).getNotaModulo();
                        } else if (personalElegido.getModuloAlumno().get(i).getModuloId().getNombre().contains(labelM3.getText())) {
                            personalElegido.getModuloAlumno().get(i).setNotaModulo(Double.valueOf(spM3.getValue().toString()));
                            notaCurso+=personalElegido.getModuloAlumno().get(i).getNotaModulo();
                        } else if (personalElegido.getModuloAlumno().get(i).getModuloId().getNombre().contains(labelM4.getText())) {
                            personalElegido.getModuloAlumno().get(i).setNotaModulo(Double.valueOf(spM4.getValue().toString()));
                            notaCurso+=personalElegido.getModuloAlumno().get(i).getNotaModulo();
                        } else if (personalElegido.getModuloAlumno().get(i).getModuloId().getNombre().contains(labelM5.getText())) {
                            personalElegido.getModuloAlumno().get(i).setNotaModulo(Double.valueOf(spM5.getValue().toString()));
                            notaCurso+=personalElegido.getModuloAlumno().get(i).getNotaModulo();
                        }

                    }
                    notaCurso=notaCurso/Utilidad.getCurso().getModulos().size();
                    notaCurso=Math.round(notaCurso*10)/10.0;
                    for(int i=0;i<personalElegido.getCursosAlumnos().size();i++){
                        if(personalElegido.getCursosAlumnos().get(i).getCursoId().getNombre().equals(Utilidad.getCurso().getNombre())){
                            if(personalElegido.getNotaFinal()==null){
                                personalElegido.setNotaFinal(notaCurso);
                            }else if(personalElegido.getCursosAlumnos().get(i).getNotaCurso()!=null){
                                Integer numeroCursos=numeroCursosConNonta(personalElegido);
                                Double notaDevuelta=numeroCursos*personalElegido.getNotaFinal();
                                Double resta=null;
                                if(notaDevuelta>personalElegido.getCursosAlumnos().get(i).getNotaCurso()){
                                    resta=notaDevuelta-personalElegido.getCursosAlumnos().get(i).getNotaCurso();
                                }else if(notaDevuelta<personalElegido.getCursosAlumnos().get(i).getNotaCurso()){
                                    resta=personalElegido.getCursosAlumnos().get(i).getNotaCurso()-notaDevuelta;
                                }else{
                                    resta=personalElegido.getCursosAlumnos().get(i).getNotaCurso()-notaDevuelta;
                                }
                                resta+=notaCurso;
                                Double notaCalculada=resta/numeroCursos;
                                Double notaFinal= (double) (Math.round(notaCalculada*10.0)/10.0);
                                personalElegido.setNotaFinal(notaFinal);

                            }else{
                                Integer numeroCursos=numeroCursosConNonta(personalElegido);
                                if(numeroCursos==0){
                                    numeroCursos=1;
                                }
                                Double notaDevuelta= personalElegido.getNotaFinal()*numeroCursos;
                                numeroCursos=numeroCursosConNonta(personalElegido);
                                notaDevuelta+=notaCurso;
                                Double notaMediaFinal=notaDevuelta/(numeroCursos+1);
                                Double redondeo= (double) (Math.round(notaMediaFinal*10.0)/10.0);
                                personalElegido.setNotaFinal(redondeo);

                            }
                            personalElegido.getCursosAlumnos().get(i).setNotaCurso(notaCurso);
                            LocalDate fechaFinalizacion=LocalDate.now();
                            DateTimeFormatter formato=DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            String fromal=fechaFinalizacion.format(formato);
                            if(personalElegido.getCursosAlumnos().get(i).getFechaFin()==null){
                                personalElegido.getCursosAlumnos().get(i).setFechaFin(fromal);
                            }
                        }

                    }
                    break;
                case 6:
                    for (int i = 0; i < personalElegido.getModuloAlumno().size(); i++) {
                        if (personalElegido.getModuloAlumno().get(i).getModuloId().getNombre().contains(labelM1.getText())) {
                            personalElegido.getModuloAlumno().get(i).setNotaModulo(Double.valueOf(spM1.getValue().toString()));
                            notaCurso+=personalElegido.getModuloAlumno().get(i).getNotaModulo();
                        } else if (personalElegido.getModuloAlumno().get(i).getModuloId().getNombre().contains(labelM2.getText())) {
                            personalElegido.getModuloAlumno().get(i).setNotaModulo(Double.valueOf(spM2.getValue().toString()));
                            notaCurso+=personalElegido.getModuloAlumno().get(i).getNotaModulo();
                        } else if (personalElegido.getModuloAlumno().get(i).getModuloId().getNombre().contains(labelM3.getText())) {
                            personalElegido.getModuloAlumno().get(i).setNotaModulo(Double.valueOf(spM3.getValue().toString()));
                            notaCurso+=personalElegido.getModuloAlumno().get(i).getNotaModulo();
                        } else if (personalElegido.getModuloAlumno().get(i).getModuloId().getNombre().contains(labelM4.getText())) {
                            personalElegido.getModuloAlumno().get(i).setNotaModulo(Double.valueOf(spM4.getValue().toString()));
                            notaCurso+=personalElegido.getModuloAlumno().get(i).getNotaModulo();
                        } else if (personalElegido.getModuloAlumno().get(i).getModuloId().getNombre().contains(labelM5.getText())) {
                            personalElegido.getModuloAlumno().get(i).setNotaModulo(Double.valueOf(spM5.getValue().toString()));
                            notaCurso+=personalElegido.getModuloAlumno().get(i).getNotaModulo();
                        } else if (personalElegido.getModuloAlumno().get(i).getModuloId().getNombre().contains(labelM6.getText())) {
                            personalElegido.getModuloAlumno().get(i).setNotaModulo(Double.valueOf(spM6.getValue().toString()));
                            notaCurso+=personalElegido.getModuloAlumno().get(i).getNotaModulo();
                        }

                    }
                    notaCurso=notaCurso/Utilidad.getCurso().getModulos().size();
                    notaCurso=Math.round(notaCurso*10)/10.0;
                    for(int i=0;i<personalElegido.getCursosAlumnos().size();i++){
                        if(personalElegido.getCursosAlumnos().get(i).getCursoId().getNombre().equals(Utilidad.getCurso().getNombre())){
                            if(personalElegido.getNotaFinal()==null){
                                personalElegido.setNotaFinal(notaCurso);
                            }else if(personalElegido.getCursosAlumnos().get(i).getNotaCurso()!=null){
                                Integer numeroCursos=numeroCursosConNonta(personalElegido);
                                Double notaDevuelta=numeroCursos*personalElegido.getNotaFinal();
                                Double resta=null;
                                if(notaDevuelta>personalElegido.getCursosAlumnos().get(i).getNotaCurso()){
                                    resta=notaDevuelta-personalElegido.getCursosAlumnos().get(i).getNotaCurso();
                                }else if(notaDevuelta<personalElegido.getCursosAlumnos().get(i).getNotaCurso()){
                                    resta=personalElegido.getCursosAlumnos().get(i).getNotaCurso()-notaDevuelta;
                                }else{
                                    resta=personalElegido.getCursosAlumnos().get(i).getNotaCurso()-notaDevuelta;
                                }
                                resta+=notaCurso;
                                Double notaCalculada=resta/numeroCursos;
                                Double notaFinal= (double) (Math.round(notaCalculada*10.0)/10.0);
                                personalElegido.setNotaFinal(notaFinal);

                            }else{
                                Integer numeroCursos=numeroCursosConNonta(personalElegido);
                                if(numeroCursos==0){
                                    numeroCursos=1;
                                }
                                Double notaDevuelta= personalElegido.getNotaFinal()*numeroCursos;
                                numeroCursos=numeroCursosConNonta(personalElegido);
                                notaDevuelta+=notaCurso;
                                Double notaMediaFinal=notaDevuelta/(numeroCursos+1);
                                Double redondeo= (double) (Math.round(notaMediaFinal*10.0)/10.0);
                                personalElegido.setNotaFinal(redondeo);

                            }
                            personalElegido.getCursosAlumnos().get(i).setNotaCurso(notaCurso);
                            LocalDate fechaFinalizacion=LocalDate.now();
                            DateTimeFormatter formato=DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            String fromal=fechaFinalizacion.format(formato);
                            if(personalElegido.getCursosAlumnos().get(i).getFechaFin()==null){
                                personalElegido.getCursosAlumnos().get(i).setFechaFin(fromal);
                            }
                        }

                    }
                    break;
                case 7:
                    for (int i = 0; i < personalElegido.getModuloAlumno().size(); i++) {
                        if (personalElegido.getModuloAlumno().get(i).getModuloId().getNombre().contains(labelM1.getText())) {
                            personalElegido.getModuloAlumno().get(i).setNotaModulo(Double.valueOf(spM1.getValue().toString()));
                            notaCurso+=personalElegido.getModuloAlumno().get(i).getNotaModulo();
                        } else if (personalElegido.getModuloAlumno().get(i).getModuloId().getNombre().contains(labelM2.getText())) {
                            personalElegido.getModuloAlumno().get(i).setNotaModulo(Double.valueOf(spM2.getValue().toString()));
                            notaCurso+=personalElegido.getModuloAlumno().get(i).getNotaModulo();
                        } else if (personalElegido.getModuloAlumno().get(i).getModuloId().getNombre().contains(labelM3.getText())) {
                            personalElegido.getModuloAlumno().get(i).setNotaModulo(Double.valueOf(spM3.getValue().toString()));
                            notaCurso+=personalElegido.getModuloAlumno().get(i).getNotaModulo();
                        } else if (personalElegido.getModuloAlumno().get(i).getModuloId().getNombre().contains(labelM4.getText())) {
                            personalElegido.getModuloAlumno().get(i).setNotaModulo(Double.valueOf(spM4.getValue().toString()));
                            notaCurso+=personalElegido.getModuloAlumno().get(i).getNotaModulo();
                        } else if (personalElegido.getModuloAlumno().get(i).getModuloId().getNombre().contains(labelM5.getText())) {
                            personalElegido.getModuloAlumno().get(i).setNotaModulo(Double.valueOf(spM5.getValue().toString()));
                            notaCurso+=personalElegido.getModuloAlumno().get(i).getNotaModulo();
                        } else if (personalElegido.getModuloAlumno().get(i).getModuloId().getNombre().contains(labelM6.getText())) {
                            personalElegido.getModuloAlumno().get(i).setNotaModulo(Double.valueOf(spM6.getValue().toString()));
                            notaCurso+=personalElegido.getModuloAlumno().get(i).getNotaModulo();
                        } else if (personalElegido.getModuloAlumno().get(i).getModuloId().getNombre().contains(labelM7.getText())) {
                            personalElegido.getModuloAlumno().get(i).setNotaModulo(Double.valueOf(spM7.getValue().toString()));
                            notaCurso+=personalElegido.getModuloAlumno().get(i).getNotaModulo();
                        }

                    }
                    notaCurso=notaCurso/Utilidad.getCurso().getModulos().size();
                    notaCurso=Math.round(notaCurso*10)/10.0;
                    for(int i=0;i<personalElegido.getCursosAlumnos().size();i++){
                        if(personalElegido.getCursosAlumnos().get(i).getCursoId().getNombre().equals(Utilidad.getCurso().getNombre())){
                            if(personalElegido.getNotaFinal()==null){
                                personalElegido.setNotaFinal(notaCurso);
                            }else if(personalElegido.getCursosAlumnos().get(i).getNotaCurso()!=null){
                               Integer numeroCursos=numeroCursosConNonta(personalElegido);
                               Double notaDevuelta=numeroCursos*personalElegido.getNotaFinal();
                               Double resta=null;
                                if(notaDevuelta>personalElegido.getCursosAlumnos().get(i).getNotaCurso()){
                                    resta=notaDevuelta-personalElegido.getCursosAlumnos().get(i).getNotaCurso();
                                }else if(notaDevuelta<personalElegido.getCursosAlumnos().get(i).getNotaCurso()){
                                    resta=personalElegido.getCursosAlumnos().get(i).getNotaCurso()-notaDevuelta;
                                }else{
                                    resta=personalElegido.getCursosAlumnos().get(i).getNotaCurso()-notaDevuelta;
                                }
                                resta+=notaCurso;
                                Double notaCalculada=resta/numeroCursos;
                                Double notaFinal= (double) (Math.round(notaCalculada*10.0)/10.0);
                                personalElegido.setNotaFinal(notaFinal);

                            }else{
                                Integer numeroCursos=numeroCursosConNonta(personalElegido);
                                if(numeroCursos==0){
                                    numeroCursos=1;
                                }
                                Double notaDevuelta= personalElegido.getNotaFinal()*numeroCursos;
                                numeroCursos=numeroCursosConNonta(personalElegido);
                                notaDevuelta+=notaCurso;
                                Double notaMediaFinal=notaDevuelta/(numeroCursos+1);
                                Double redondeo= (double) (Math.round(notaMediaFinal*10.0)/10.0);
                                personalElegido.setNotaFinal(redondeo);

                            }
                            personalElegido.getCursosAlumnos().get(i).setNotaCurso(notaCurso);
                            LocalDate fechaFinalizacion=LocalDate.now();
                            DateTimeFormatter formato=DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            String fromal=fechaFinalizacion.format(formato);
                            if(personalElegido.getCursosAlumnos().get(i).getFechaFin()==null){
                                personalElegido.getCursosAlumnos().get(i).setFechaFin(fromal);
                            }

                        }

                    }
                    break;


            }
            System.out.println(personalElegido);
            (new PersonalBolsaDAOImplement()).modNotas(personalElegido);
            if(boleano){
                tablaCurso.getItems().remove(personalElegido);
            }else{
                alumnos.clear();
                alumnos.addAll(new PersonalBolsaDAOImplement().getAllByCursoId(Utilidad.getCurso()));
            }

        }
    }

    @javafx.fxml.FXML
    public void agregarAlumno(Event event) {
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("todos-alumnos-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1000, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("ININPROTEC");
        stage.getIcons().add(new Image(EntradaCursoController.class.getClassLoader().getResource("imagenes/escudo.png").toExternalForm()));
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void filtrar(Event event) {
        String filtro=textBuscador.getText().toLowerCase();
        filtro=filtro.replace("á","a");
        filtro= filtro.replace("é","e");
        filtro= filtro.replace("í","i");
        filtro= filtro.replace("ó","o");
        filtro= filtro.replace("ú","u");
        if(filtro.isEmpty()){
            if(otroBoleano){
                tablaCurso.setItems(filtrado);
            }else{
                tablaCurso.setItems(alumnos);
            }

        }else{
            filtroAlumnos.clear();
            if(otroBoleano){
                for(PersonalBolsa a:filtrado){
                    String porBusqueda= a.getNombre().toLowerCase()+" "+a.getApellido1().toLowerCase()+" "+a.getApellido2().toLowerCase();
                    porBusqueda=porBusqueda.replace("á","a");
                    porBusqueda=porBusqueda.replace("é","e");
                    porBusqueda=porBusqueda.replace("í","i");
                    porBusqueda=porBusqueda.replace("ó","o");
                    porBusqueda=porBusqueda.replace("ú","u");
                    if(porBusqueda.contains(filtro.toLowerCase())){
                        filtroAlumnos.add(a);
                    }
                }
                tablaCurso.setItems(filtroAlumnos);
            }else{
                for(PersonalBolsa a:alumnos){
                    String porBusqueda= a.getNombre().toLowerCase()+" "+a.getApellido1().toLowerCase()+" "+a.getApellido2().toLowerCase();
                    porBusqueda=porBusqueda.replace("á","a");
                    porBusqueda=porBusqueda.replace("é","e");
                    porBusqueda=porBusqueda.replace("í","i");
                    porBusqueda=porBusqueda.replace("ó","o");
                    porBusqueda=porBusqueda.replace("ú","u");
                    if(porBusqueda.contains(filtro.toLowerCase())){
                        filtroAlumnos.add(a);
                    }
                }
                tablaCurso.setItems(filtroAlumnos);
            }


        }
    }

    @javafx.fxml.FXML
    public void mostrarAlumnosNuevos(ActionEvent actionEvent) {
        boleano=true;
        alumnosNuevos.clear();
        alumnosNuevos.addAll((new PersonalBolsaDAOImplement()).getAllByCursoIdNuevos(Utilidad.getCurso()));
        tablaCurso.setItems(alumnosNuevos);
    }

    @javafx.fxml.FXML
    public void generarListadoAlumnos(ActionEvent actionEvent) {
        Connection c= MYSQLUtil.getConexion();
        HashMap<String,Object> parametro=new HashMap<>();
        parametro.put("cursoId",Utilidad.getCurso().getId());
        parametro.put("cursoNombre",Utilidad.getCurso().getNombre());
        try {
            JasperPrint jasper= JasperFillManager.fillReport("ListadoAlumnos.jasper",parametro,c);
            JRViewer visor=new JRViewer(jasper);
            JFrame frame = new JFrame("Listado de Alumnos Nuevos");
            frame.getContentPane().add(visor);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.pack();
            frame.setVisible(true);

            //GENERA PDF
            JRPdfExporter exp = new JRPdfExporter();
            exp.setExporterInput(new SimpleExporterInput(jasper));
            exp.setExporterOutput(new SimpleOutputStreamExporterOutput("listadoAlumnos.pdf"));
            exp.setConfiguration(new SimplePdfExporterConfiguration());
            exp.exportReport();
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void generarCertificado(ActionEvent actionEvent) {
        if(personalElegido!=null){
            showDialog();
            Connection c= MYSQLUtil.getConexion();
            HashMap<String,Object> parametro=new HashMap<>();
            parametro.put("alumnoId",personalElegido.getId());
            parametro.put("cursoNombre",Utilidad.getCurso().getNombre());
            parametro.put("dias",dias);
            parametro.put("año",certificadoAño);
            parametro.put("mes",mes);
            parametro.put("nombreCompleto",nombreCurso);
            parametro.put("horas",horas);
            try {
                if(dias!=null&&certificadoAño!=null&&mes!=null&&nombreCurso!=null&&horas!=null){

                    JasperPrint jasper= JasperFillManager.fillReport((sinSello?"certificadoAlumno.jasper":"certificadoSinSello.jasper"),parametro,c);
                    JRViewer visor=new JRViewer(jasper);
                    JFrame frame = new JFrame("Certificado Del Alumnado");
                    frame.getContentPane().add(visor);
                    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    frame.pack();
                    frame.setVisible(true);

                    //GENERA PDF


                    JRPdfExporter exp = new JRPdfExporter();
                    exp.setExporterInput(new SimpleExporterInput(jasper));
                    exp.setExporterOutput(new SimpleOutputStreamExporterOutput("Certificado.pdf"));

                    SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();

                    exp.setConfiguration(configuration);
                    exp.exportReport();
                }else{
                    Alert alerta=new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("ERROR");
                    alerta.setHeaderText("Campos vacíos");
                    alerta.setContentText("Por favor asegurese de que los campos introducidos no esten vacíos");
                    alerta.showAndWait();
                }

            } catch (JRException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @javafx.fxml.FXML
    public void buscar(ActionEvent actionEvent) {
        if(spAltura.getValue()!=0.0&&comboSigno.getValue()==null){
            Alert alerta=new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText("Selección Signo de la Altura");
            alerta.setContentText("Seleccione el simbolo de la altura ya que se necesita\nsaber como lo quieres buscar por ejemplo " +
                    "<,>,=,<=,>=");
            alerta.showAndWait();
        }else{
            boleano=false;
            otroBoleano=true;
            filtrado.clear();
            parametro.put("curso",Utilidad.getCurso().getId());
            ArrayList<PersonalBolsa> personalBBDD=new ArrayList<>();
            List<String> fechas=new ArrayList<>();
            if(spAltura.getValue()!=0.0){
                copiado.put("altura","alumnoId.altura"+comboSigno.getValue()+":altura");
                parametro.put("altura",spAltura.getValue());
            }
            if(dateDesde.getValue()!=null&&dateHasta.getValue()!=null){
                LocalDate desde=dateDesde.getValue();
                LocalDate hasta=dateHasta.getValue();
                fechas=obtenerFechas(desde,hasta);
                System.out.println(fechas);
                copiado.put("fecha","fechaFin=:fecha");

            }
            HashMap<String,String>copiadoViceversa=new HashMap<>();
            copiadoViceversa.putAll(copiado);
            switch (copiado.size()){
                case 1:
                    sentencia.put("sentencia","select ca.alumnoId from AlumnoCurso ca"+" where ca.cursoId.id=:idCurso and ca."+
                            ((copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):"")))))));

                    System.out.println(sentencia);
                    copiado.clear();
                    copiado.putAll(copiadoViceversa);
                    personalBBDD=(ArrayList<PersonalBolsa>) (new AlumnoCursoDAOImplement()). getFiltradoTodosConCurso(fechas,sentencia,parametro);
                    filtrado.addAll(personalBBDD);
                    tablaCurso.setItems(filtrado);
                    break;
                case 2:

                    sentencia.put("sentencia","select ca.alumnoId from AlumnoCurso ca"+" where ca.cursoId.id=:idCurso and ca."+
                            ((copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):"")))))));
                    String contiene= sentencia.get("sentencia");


                    if(contiene.contains("ca.alumnoId.altura")){
                        copiado.remove("altura");
                    }else if(contiene.contains("ca.alumnoId.idioma")) {
                        copiado.remove("idioma");
                    }else if(contiene.contains("ca.alumnoId.titulacion")) {
                        copiado.remove("formacion");
                    }else if(contiene.contains("ca.alumnoId.titulacion")) {
                        copiado.remove("calificacion");
                    }else if(contiene.contains("ca.fechaFin")) {
                        copiado.remove("fecha");
                    }else if(contiene.contains("ca.alumnoId.sexo")){
                        copiado.remove("sexo");
                    }
                    String hecho=" and ca."+ ((copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):""))))));
                    sentencia.put("sentencia",sentencia.get("sentencia")+hecho);
                    System.out.println(sentencia);
                    copiado.clear();
                    copiado.putAll(copiadoViceversa);
                    personalBBDD=(ArrayList<PersonalBolsa>) (new AlumnoCursoDAOImplement()).getFiltradoTodosConCurso(fechas,sentencia,parametro);
                    filtrado.addAll(personalBBDD);
                    tablaCurso.setItems(filtrado);
                    break;


                case 3:
                    sentencia.put("sentencia","select ca.alumnoId from AlumnoCurso ca"+" where ca.cursoId.id=:idCurso and ca."+
                            ((copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):"")))))));
                    String contiene1= sentencia.get("sentencia");


                    if(contiene1.contains("ca.alumnoId.altura")){
                        copiado.remove("altura");
                    }else if(contiene1.contains("ca.alumnoId.idioma")) {
                        copiado.remove("idioma");
                    }else if(contiene1.contains("ca.alumnoId.titulacion")) {
                        copiado.remove("formacion");
                    }else if(contiene1.contains("ca.alumnoId.titulacion")) {
                        copiado.remove("calificacion");
                    }else if(contiene1.contains("ca.fechaFin")) {
                        copiado.remove("fecha");
                    }else if(contiene1.contains("ca.alumnoId.sexo")){
                        copiado.remove("sexo");
                    }
                    String hecho1=" and ca."+ ((copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):""))))));
                    sentencia.put("sentencia",sentencia.get("sentencia")+hecho1);
                    String contiene2= sentencia.get("sentencia");

                    if(contiene2.contains("ca.alumnoId.altura")&&copiado.containsKey("altura")){
                        copiado.remove("altura");
                    }else if(contiene2.contains("ca.alumnoId.idioma")&&copiado.containsKey("idioma")) {
                        copiado.remove("idioma");
                    }else if(contiene2.contains("ca.alumnoId.titulacion")&&copiado.containsKey("formacion")) {
                        copiado.remove("formacion");
                    }else if(contiene2.contains("ca.fecha")&&copiado.containsKey("fecha")) {
                        copiado.remove("fecha");
                    }else if(contiene2.contains("ca.alumnoId.sexo")&&copiado.containsKey("sexo")){
                        copiado.remove("sexo");
                    }
                    String hecho2=" and ca."+ ((copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):""))))));
                    sentencia.put("sentencia",sentencia.get("sentencia")+hecho2);
                    System.out.println(sentencia);
                    copiado.clear();
                    copiado.putAll(copiadoViceversa);
                    personalBBDD=(ArrayList<PersonalBolsa>) (new AlumnoCursoDAOImplement()).getFiltradoTodosConCurso(fechas,sentencia,parametro);
                    filtrado.addAll(personalBBDD);
                    tablaCurso.setItems(filtrado);
                    break;
                case 4:
                    sentencia.put("sentencia","select ca.alumnoId from AlumnoCurso ca"+" where ca.cursoId.id=:idCurso and ca."+
                            ((copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):"")))))));
                    String contiene3= sentencia.get("sentencia");


                    if(contiene3.contains("ca.alumnoId.altura")){
                        copiado.remove("altura");
                    }else if(contiene3.contains("ca.alumnoId.idioma")) {
                        copiado.remove("idioma");
                    }else if(contiene3.contains("ca.alumnoId.titulacion")) {
                        copiado.remove("formacion");
                    }else if(contiene3.contains("ca.alumnoId.titulacion")) {
                        copiado.remove("calificacion");
                    }else if(contiene3.contains("ca.fechaFin")) {
                        copiado.remove("fecha");
                    }else if(contiene3.contains("ca.alumnoId.sexo")){
                        copiado.remove("sexo");
                    }
                    String hecho3=" and ca."+ ((copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):""))))));
                    sentencia.put("sentencia",sentencia.get("sentencia")+hecho3);
                    String contiene4= sentencia.get("sentencia");

                    if(contiene4.contains("ca.alumnoId.altura")&&copiado.containsKey("altura")){
                        copiado.remove("altura");
                    }else if(contiene4.contains("ca.alumnoId.idioma")&&copiado.containsKey("idioma")) {
                        copiado.remove("idioma");
                    }else if(contiene4.contains("ca.alumnoId.titulacion")&&copiado.containsKey("formacion")) {
                        copiado.remove("formacion");
                    }else if(contiene4.contains("ca.fecha")&&copiado.containsKey("fecha")) {
                        copiado.remove("fecha");
                    }else if(contiene4.contains("ca.alumnoId.sexo")&&copiado.containsKey("sexo")){
                        copiado.remove("sexo");
                    }
                    String hecho4=" and ca."+ ((copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):""))))));
                    sentencia.put("sentencia",sentencia.get("sentencia")+hecho4);
                    String contiene5= sentencia.get("sentencia");

                    if(contiene5.contains("ca.alumnoId.altura")&&copiado.containsKey("altura")){
                        copiado.remove("altura");
                    }else if(contiene5.contains("ca.alumnoId.idioma")&&copiado.containsKey("idioma")) {
                        copiado.remove("idioma");
                    }else if(contiene5.contains("ca.alumnoId.titulacion")&&copiado.containsKey("formacion")) {
                        copiado.remove("formacion");
                    }else if(contiene5.contains("ca.fecha")&&copiado.containsKey("fecha")) {
                        copiado.remove("fecha");
                    }else if(contiene5.contains("ca.alumnoId.sexo")&&copiado.containsKey("sexo")){
                        copiado.remove("sexo");
                    }
                    String hecho5=" and ca."+ ((copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):""))))));
                    sentencia.put("sentencia",sentencia.get("sentencia")+hecho5);
                    System.out.println(sentencia);
                    copiado.clear();
                    copiado.putAll(copiadoViceversa);
                    personalBBDD=(ArrayList<PersonalBolsa>) (new AlumnoCursoDAOImplement()).getFiltradoTodosConCurso(fechas,sentencia,parametro);
                    filtrado.addAll(personalBBDD);
                    tablaCurso.setItems(filtrado);

                    break;
                case 5:
                    sentencia.put("sentencia","select ca.alumnoId from AlumnoCurso ca where ca.cursoId.id=:idCurso and ca.alumnoId.altura=:altura and ca.alumnoId.idioma LIKE:idioma and ca.alumnoId.titulacion LIKE:titulacion and ca.fechaFin=:fecha");
                    System.out.println(sentencia);
                    copiado.clear();
                    copiado.putAll(copiadoViceversa);
                    System.out.println(parametro.size());
                    personalBBDD=(ArrayList<PersonalBolsa>) (new AlumnoCursoDAOImplement()).getFiltradoTodosConCurso(fechas,sentencia,parametro);
                    filtrado.addAll(personalBBDD);
                    tablaCurso.setItems(filtrado);
                    break;
            }
        }

    }

    @javafx.fxml.FXML
    public void quitar(ActionEvent actionEvent) {
        if(spAltura.getValue()!=0.0||radioHombre.isSelected()||radioMujer.isSelected()||comboIdioma.getValue()!=null||comboFormacion.getValue()!=null||(dateDesde.getValue()!=null&&dateHasta.getValue()!=null)){
            dateDesde.setValue(null);
            dateHasta.setValue(null);
            boleano=false;
            otroBoleano=false;
            copiado.clear();
            textBuscador.clear();
            radioMujer.setSelected(false);
            radioHombre.setSelected(false);
            comboSigno.getSelectionModel().select(null);
            comboIdioma.getSelectionModel().selectedItemProperty().removeListener(listenerIdioma);
            comboFormacion.getSelectionModel().selectedItemProperty().removeListener(listenerFormacion);
            comboIdioma.getSelectionModel().select(null);
            comboFormacion.getSelectionModel().select(null);
            spAltura.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0,2.10,0.0,0.1));
            alumnos.clear();
            alumnos.addAll((new PersonalBolsaDAOImplement().getAllByCursoId(Utilidad.getCurso())));
            if(textBuscador.getText().isEmpty()){
                tablaCurso.setItems(alumnos);
            }else {
                tablaCurso.setItems(filtroAlumnos);
            }
            copiado.clear();
            parametro.clear();
            comboFormacion.getSelectionModel().selectedItemProperty().addListener(listenerFormacion);
            comboIdioma.getSelectionModel().selectedItemProperty().addListener(listenerIdioma);
        }



    }
    public static List<String> obtenerFechas(LocalDate desde, LocalDate hasta) {
        List<String> listaFechas = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (!desde.isAfter(hasta)) {
            // Formatear y agregar a la lista
            listaFechas.add(desde.format(formatter));
            // Incrementar un día
            desde = desde.plusDays(1);
        }

        return listaFechas;
    }

    @javafx.fxml.FXML
    public void desapuntar(ActionEvent actionEvent) {
        Double notaCurso=null;
        AlumnoCurso guardado=null;
        if(personalElegido!=null){
            for(AlumnoCurso alumnoCurso:personalElegido.getCursosAlumnos()){
                if(alumnoCurso.getCursoId().getId()==Utilidad.getCurso().getId()){
                    notaCurso=alumnoCurso.getNotaCurso();
                    guardado=alumnoCurso;
                }
            }
            Alert alerta=new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("INFORMACIÓN");
            alerta.setHeaderText("Desapuntar el alumno del curso");
            alerta.setContentText("¿Seguro deseas quitar a este alumno del curso? "+(notaCurso!=null?"Ten en cuenta que se bajará la media de la nota del alumno":""));
            Optional<ButtonType> tipo=alerta.showAndWait();
            if(tipo.get()==ButtonType.OK){
                if(notaCurso!=null){
                    Integer numeroNotas=numeroCursosConNonta(personalElegido);
                    Double notaDevuelta=personalElegido.getNotaFinal()*numeroNotas;
                    Double notaCalculo=notaDevuelta-notaCurso;
                    Double notaCalculoFinal=null;
                    Double notaFInal=null;
                    if(numeroNotas!=1){
                         notaCalculoFinal=notaCalculo/(numeroNotas-1);
                        notaFInal= (double) (Math.round(notaCalculoFinal*10)/10);
                    }else{
                        notaFInal=null;
                    }
                    personalElegido.setNotaFinal(notaFInal);
                    Integer posicion=personalElegido.getCursosAlumnos().indexOf(guardado);
                    personalElegido.getCursosAlumnos().get(posicion).setNotaCurso(null);
                    if(personalElegido.getCursosAlumnos().size()==1){
                        personalElegido.setNotaFinal(null);
                    }
                    (new PersonalBolsaDAOImplement()).modNotas(personalElegido);
                    (new AlumnoCursoDAOImplement()).quitarAlumnoDelCurso(Utilidad.getCurso(),personalElegido);
                    tablaCurso.getItems().remove(personalElegido);
                }else{
                    Integer posicion=personalElegido.getCursosAlumnos().indexOf(guardado);
                    personalElegido.getCursosAlumnos().get(posicion).setNotaCurso(null);
                    (new PersonalBolsaDAOImplement()).modNotas(personalElegido);
                    (new AlumnoCursoDAOImplement()).quitarAlumnoDelCurso(Utilidad.getCurso(),personalElegido);
                    tablaCurso.getItems().remove(personalElegido);
                }


            }



        }

    }
    private Integer numeroCursosConNonta(PersonalBolsa alumno){
        Integer contador=0;

        for(int i=0;i<alumno.getCursosAlumnos().size();i++){
            if(alumno.getCursosAlumnos().get(i).getNotaCurso()!=null){
                contador++;
            }
        }
        return contador;
    }

    private void showDialog(){
        Dialog<HashMap<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Introducir Datos");
        dialog.setHeaderText("Introduce todos los datos:");

        // Configurar tipos de botones
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Crear etiquetas y campos de texto
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField textField1 = new TextField();
        textField1.setPromptText("Nombre Completo Del Curso");
        TextField textField2 = new TextField();
        textField2.setPromptText("Horas Totales");
        TextField textField3 = new TextField();
        textField3.setPromptText("Mes");
        TextField textField4 = new TextField(); // Nuevo campo de texto
        textField4.setPromptText("Año");
        TextField textField5 = new TextField(); // Nuevo campo de texto
        textField5.setPromptText("Días");
        CheckBox sello=new CheckBox();


        grid.add(new Label("¿Sello?"),0,0 );
        grid.add(sello,1,0);
        grid.add(new Label("Nombre  Del Curso:"), 0, 1);
        grid.add(textField1, 1, 1);
        grid.add(new Label("Horas Totales:"), 0, 2);
        grid.add(textField2, 1, 2);
        grid.add(new Label("Mes"),0,3);
        grid.add(textField3, 1, 3);
        grid.add(new Label("Año:"), 0, 4);
        grid.add(textField4, 1, 4);
        grid.add(new Label("Días:"), 0, 5);
        grid.add(textField5, 1, 5);

        // Incorporar el grid al diálogo
        dialog.getDialogPane().setContent(grid);

        // Request focus on the first field by default.
        Platform.runLater(textField1::requestFocus);

        // Convertir la entrada en un par cuando el botón OK es presionado.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK&&!textField1.getText().isEmpty()&&!textField2.getText().isEmpty()&&
                    !textField3.getText().isEmpty()&&!textField4.getText().isEmpty()&&!textField5.getText().isEmpty()){
                HashMap<String, String> data = new HashMap<>();
                data.put("nombre", textField1.getText());
                data.put("horas", textField2.getText());
                data.put("mes", textField3.getText());
                data.put("año", textField4.getText());
                data.put("dias", textField5.getText());


                return data;
            }else{
                return null;
            }

        });

        // Mostrar el diálogo y capturar el resultado
        Optional<HashMap<String, String>> result = dialog.showAndWait();

        result.ifPresent(data -> {
            nombreCurso= data.get("nombre");
            horas= data.get("horas");
            mes=data.get("mes");
            certificadoAño =data.get("año");
            dias=data.get("dias");
            sinSello=sello.isSelected();

    });
}
}

