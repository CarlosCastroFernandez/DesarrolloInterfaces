package com.example.teamsfxjasper;

import clase.Alumno;
import errores.NombreConNumerosException;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.swing.JRViewer;
import utils.MYSQLConecction;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TableColumn<Alumno,String> columnaNombre;
    @FXML
    private TableColumn<Alumno,String> columnaApellidos;
    @FXML
    private TableColumn<Alumno,String> columnaAD;
    @FXML
    private TableColumn<Alumno,String> columnaSGE;
    @FXML
    private TableColumn <Alumno,String>columnaDI;
    @FXML
    private TableColumn<Alumno,String> columnaPMDM;
    @FXML
    private TableColumn <Alumno,String>columnaPSP;
    @FXML
    private TableColumn <Alumno,String>columnaEIE;
    @FXML
    private TableColumn<Alumno,String> columnaHLC;
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
    private Button botonInsertar;
    @FXML
    private Button botonDWPDF;
    @FXML
    private Button botonSalir;
    @FXML
    private TableView <Alumno>tabla;
    @FXML
    private Label labelInfo;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnaNombre.setCellValueFactory((alumno)->{
            String nombreAlumno=alumno.getValue().getNombre();
            return new SimpleStringProperty(nombreAlumno);
        });
        columnaApellidos.setCellValueFactory((alumno)->{
            String apellidos=alumno.getValue().getApellido();
            return new SimpleStringProperty(apellidos);
        });
        columnaAD.setCellValueFactory((alumno)->{
            String apellidos=String.valueOf(alumno.getValue().getAD());
            return new SimpleStringProperty(apellidos);
        });
        columnaDI.setCellValueFactory((alumno)->{
            String apellidos=String.valueOf(alumno.getValue().getDI());
            return new SimpleStringProperty(apellidos);
        });
        columnaEIE.setCellValueFactory((alumno)->{
            String apellidos=String.valueOf(alumno.getValue().getEIE());
            return new SimpleStringProperty(apellidos);
        });
        columnaHLC.setCellValueFactory((alumno)->{
            String apellidos=String.valueOf(alumno.getValue().getHLC());
            return new SimpleStringProperty(apellidos);
        });
        columnaPMDM.setCellValueFactory((alumno)->{
            String apellidos=String.valueOf(alumno.getValue().getPMDM());
            return new SimpleStringProperty(apellidos);
        });
        columnaPSP.setCellValueFactory((alumno)->{
            String apellidos=String.valueOf(alumno.getValue().getPSP());
            return new SimpleStringProperty(apellidos);
        });
        columnaSGE.setCellValueFactory((alumno)->{
            String apellidos=String.valueOf(alumno.getValue().getSGE());
            return new SimpleStringProperty(apellidos);
        });


        tabla.getSelectionModel().selectedItemProperty().addListener((observableValue, alumno, t1) -> {
            Alumno alumnoSeleccionado=t1;
            Byte notaAD = alumnoSeleccionado.getAD();
            Byte notaSGE = alumnoSeleccionado.getSGE();
            Byte notaPMDM = alumnoSeleccionado.getPMDM();
            Byte notaDI =alumnoSeleccionado.getDI();
            Byte notaPSP = alumnoSeleccionado.getPSP();
            Byte notaHLC = alumnoSeleccionado.getHLC();
            Byte notaEIE = alumnoSeleccionado.getEIE();
            ArrayList<Byte>notas=new ArrayList<>();
            notas.add(notaAD);
            notas.add(notaSGE);
            notas.add(notaPMDM);
            notas.add(notaDI);
            notas.add(notaPSP);
            notas.add(notaHLC); notas.add(notaEIE);
            Integer contador=0;
            Integer sumatorio=0;
            for(Byte nota:notas){
                if(nota<5){
                    contador++;
                }
            }
            if(contador==0){
                for(Byte nota:notas){
                    sumatorio+=nota;
                }
                Double mediaTotal=(double)sumatorio/notas.size();
                labelInfo.setText("La nota media es: "+mediaTotal);
            }else{
                labelInfo.setText("Tiene suspensa"+(contador>1?"s":"")+" "+contador+" asignatura"+(contador>1?"s":""));
            }



        });

    }
    @FXML
    public void insertarAlumno(ActionEvent actionEvent) {
        if(!textAD.getText().isEmpty()&&!textApellido.getText().isEmpty()&&!textNombre.getText().isEmpty()&&
    !textDI.getText().isEmpty()&&!textEIE.getText().isEmpty()&&!textSGE.getText().isEmpty()&&!textHLC.getText().isEmpty()
    &&!textPMDM.getText().isEmpty()&&!textPSP.getText().isEmpty()){
            try {

                Byte notaAD = Byte.valueOf(textAD.getText());
                Byte notaSGE = Byte.valueOf(textSGE.getText());
                Byte notaPMDM = Byte.valueOf(textPMDM.getText());
                Byte notaDI = Byte.valueOf(textDI.getText());
                Byte notaPSP = Byte.valueOf(textPSP.getText());
                Byte notaHLC = Byte.valueOf(textHLC.getText());
                Byte notaEIE = Byte.valueOf(textEIE.getText());


                if (notaAD >= 0 && notaAD <= 10 && notaSGE >= 0 && notaSGE <= 10 && notaPMDM >= 0 && notaPMDM <= 10 && notaDI >= 0 && notaDI <= 10
                        && notaPSP >= 0 && notaPSP <= 10 && notaHLC >= 0 && notaHLC <= 10 && notaEIE >= 0 && notaEIE <= 10) {
                    Alumno alumno = new Alumno(textNombre.getText(), textApellido.getText(),
                            notaAD, notaSGE, notaPMDM, notaPSP, notaEIE, notaDI, notaHLC);

                    tabla.getItems().add(alumno);

                } else {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setHeaderText("Error");
                    alerta.setContentText("Las notas deben de estar en el\n\t\tintervalo [0-10]");
                    alerta.showAndWait();
                }

            }catch (NumberFormatException e){
               // e.printStackTrace();
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setHeaderText("Error");
                alerta.setContentText("Asegurate de meter las notas bien tio!!");
                alerta.showAndWait();
            } catch (NombreConNumerosException e) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setHeaderText("Error");
                alerta.setContentText("Asegurate de que el nombre y el apellido no tengan numeros!!");
                alerta.showAndWait();
            }
        }else{
            Alert alerta=new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText("Error");
            alerta.setContentText("Por favor Los campos deben de estar rellenos");
            alerta.showAndWait();
        }
    }

    @FXML
    public void descargarPDF(ActionEvent actionEvent) {
        Connection conexion= MYSQLConecction.getConexion();
        try {
            JasperPrint jasper= JasperFillManager.fillReport("examenAlumno.jasper", new HashMap<>(),conexion);
            JRViewer visor=new JRViewer(jasper);
            JFrame ventana=new JFrame("Listado Notas");
            ventana.getContentPane().add(visor);
            ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
            ventana.pack();
            ventana.setVisible(true);
            JRPdfExporter exp=new JRPdfExporter();
            exp.setExporterInput(new SimpleExporterInput(jasper));
            exp.setExporterOutput(new SimpleOutputStreamExporterOutput("Notas.pdf"));
            exp.setConfiguration(new SimplePdfExporterConfiguration());
            exp.exportReport();
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void salir(ActionEvent actionEvent) {
    }


}