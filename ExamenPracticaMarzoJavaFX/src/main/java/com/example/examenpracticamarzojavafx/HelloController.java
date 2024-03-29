package com.example.examenpracticamarzojavafx;

import clase.Alumno;
import conexion.MYSQLConecction;
import dao.AlumnoDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TextField textNombre;
    @FXML
    private TextField textApellido;
    @FXML
    private TextField textAD;
    @FXML
    private TextField textSGE;
    @FXML
    private TextField textDI;
    @FXML
    private TextField textPMDM;
    @FXML
    private TextField textPSP;
    @FXML
    private TextField textEIE;
    @FXML
    private TextField textHLC;
    @FXML
    private Button botonNuevoAlumno;
    @FXML
    private Button botonDescargarNotas;
    @FXML
    private Button botonSalir;
    @FXML
    private TableView <Alumno>tabla ;
    @FXML
    private TableColumn <Alumno,String>columnaNombre;
    @FXML
    private TableColumn <Alumno, String> columnaApellido;
    @FXML
    private TableColumn <Alumno, String>columnaAD;
    @FXML
    private TableColumn <Alumno, String>columnaSGE;
    @FXML
    private TableColumn <Alumno, String> columnaDI;
    @FXML
    private TableColumn <Alumno, String> columnaPMDM;
    @FXML
    private TableColumn <Alumno, String> columnaPSP;
    @FXML
    private TableColumn <Alumno, String> columnaEIE;
    @FXML
    private TableColumn <Alumno, String> columnaHLC;
    @FXML
    private Label textLabel;
    private ObservableList observable;
    private ContextMenu contextMenu;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contextMenu=new ContextMenu();
        contextMenu.getItems().add(new MenuItem("HOLA"));
        contextMenu.getItems().add(new SeparatorMenuItem());
        contextMenu.getItems().add(new MenuItem("ADIOS"));
        textNombre.setContextMenu(contextMenu);
        textNombre.setOnMousePressed(mouseEvent -> {
            if(mouseEvent.isSecondaryButtonDown()){
                contextMenu.show(textNombre,mouseEvent.getScreenX(),mouseEvent.getScreenY());
            }
        });
        contextMenu.getItems().get(2).setOnAction(actionEvent -> {
            textLabel.setText("Ole tus HUevos");
        });
         observable= FXCollections.observableArrayList();
        columnaNombre.setCellValueFactory((nombre )->{
            String nombreAlumno=nombre.getValue().getNombre();
            return new SimpleStringProperty(nombreAlumno);
        });
        columnaApellido.setCellValueFactory((apellido)->{
            String apellidoAlumno=apellido.getValue().getApellido();
            return new SimpleStringProperty(apellidoAlumno);
        });
        columnaAD.setCellValueFactory((nota)->{
            String notaAd=nota.getValue().getAD().toString();
            return new SimpleStringProperty(notaAd);
        });
        columnaSGE.setCellValueFactory((nota)->{
            String notaAd=nota.getValue().getSGE().toString();
            return new SimpleStringProperty(notaAd);
        });
        columnaDI.setCellValueFactory((nota)->{
            String notaAd=nota.getValue().getDI().toString();
            return new SimpleStringProperty(notaAd);
        });
        columnaPMDM.setCellValueFactory((nota)->{
            String notaAd=nota.getValue().getPMDM().toString();
            return new SimpleStringProperty(notaAd);
        });
        columnaPSP.setCellValueFactory((nota)->{
            String notaAd=nota.getValue().getPSP().toString();
            return new SimpleStringProperty(notaAd);
        });
        columnaEIE.setCellValueFactory((nota)->{
            String notaAd=nota.getValue().getEIE().toString();
            return new SimpleStringProperty(notaAd);
        });
        columnaHLC.setCellValueFactory((nota)->{
            String notaAd=nota.getValue().getHLC().toString();
            return new SimpleStringProperty(notaAd);
        });
    ArrayList<Alumno>alumnos=AlumnoDAO.getAllAlumnos();
    observable.addAll(alumnos);
    tabla.setItems(observable);
        tabla.getSelectionModel().selectedItemProperty().addListener((observableValue, alumno, t1) -> {
            Alumno alumnoActual=t1;
            Byte contador=0;
            Integer sumador=0;
            ArrayList <Byte>notas=new ArrayList();
            notas.add(alumnoActual.getEIE());
            notas.add(alumnoActual.getPSP());
            notas.add(alumnoActual.getAD());
            notas.add(alumnoActual.getPMDM());
            notas.add(alumnoActual.getDI());
            notas.add(alumnoActual.getHLC());
            notas.add(alumnoActual.getSGE());

            for(int i=0;i<notas.size();i++){
                if(notas.get(i)<5){
                    contador++;
                }
            }
            if(contador==0){
                for(int i=0;i<notas.size();i++){
                    sumador+=notas.get(i);
                }

                textLabel.setText("La Nota Media es de "+(sumador/notas.size()));
            }else{
                textLabel.setText("Tienes suspensa"+(contador>1?"s":"")+" "+contador +" Asignatura"+(contador>1?"s":""));
            }

        });







    }

    @FXML
    public void newAlumn(ActionEvent actionEvent) {

        Alumno alumnoNuevo=new Alumno(textNombre.getText(),textApellido.getText(),Byte.valueOf(textAD.getText()),Byte.valueOf(textSGE.getText()),Byte.valueOf(textPMDM.getText()),
                Byte.valueOf(textPSP.getText()),Byte.valueOf(textEIE.getText()),Byte.valueOf(textDI.getText()),Byte.valueOf(textHLC.getText()));

        alumnoNuevo=AlumnoDAO.insertarAlumno(alumnoNuevo);
        observable.add(alumnoNuevo);



    }


    @FXML
    public void noteDowload(ActionEvent actionEvent) {
        Connection c= MYSQLConecction.getConexion();
        try {
            //Sirve oara compilar JRXML si ya tengo el archivo .jasper no hace falta compilarlo de nuevo
          //  JasperReport report= JasperCompileManager.compileReport("examenAlumno.jasper");
            JasperPrint jasper= JasperFillManager.fillReport("examenAlumno.jasper",new HashMap<>(),c);
            JRViewer visor= new JRViewer(jasper);
            JFrame ventana=new JFrame("Listado Alumnos");
            ventana.getContentPane().add(visor);
            ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
            ventana.pack();
            ventana.setVisible(true);

            JRPdfExporter exp = new JRPdfExporter();
            exp.setExporterInput(new SimpleExporterInput(jasper));
            exp.setExporterOutput(new SimpleOutputStreamExporterOutput("Alumnos.pdf"));
            exp.setConfiguration(new SimplePdfExporterConfiguration());
            exp.exportReport();
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void exit(ActionEvent actionEvent) {
    }


}