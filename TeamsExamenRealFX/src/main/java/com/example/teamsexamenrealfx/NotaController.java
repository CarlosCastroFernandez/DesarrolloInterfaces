package com.example.teamsexamenrealfx;

import clases.Alumno;
import clases.Modulo;
import dao.AlumnoDAO;
import dao.ModuloDAO;
import errores.NotaException;
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
import utils.MYSQLConnection;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class NotaController implements Initializable {


    @FXML
    private TableColumn<Alumno,String> columnaNombre;
    @FXML
    private TableColumn <Alumno,String>columnaApellido;
    @FXML
    private TableColumn <Alumno,String>columnaCorreo;
    @FXML
    private TableColumn <Alumno,String>columnaDNI;
    @FXML
    private TableColumn<Alumno,String> columnaFecha;
    @FXML
    private TableColumn <Alumno,String>columnaTLF;
    @FXML
    private TableColumn <Alumno,String>columnaLocalidad;
    @FXML
    private TableColumn <Alumno,String>columaAD;
    @FXML
    private TableColumn<Alumno,String> columnaDI;
    @FXML
    private TableColumn <Alumno,String>columnaSGE;
    @FXML
    private TableColumn<Alumno,String> columnaHLC;
    @FXML
    private TableColumn <Alumno,String>columnaPMDM;
    @FXML
    private TableColumn<Alumno,String> columnaPSP;
    @FXML
    private TableColumn<Alumno,String> columnaEIE;
    @FXML
    private TextField textNombre;
    @FXML
    private TextField textApellidos;
    @FXML
    private TextField textCorreo;
    @FXML
    private TextField textDNI;
    @FXML
    private DatePicker dateFecha;
    @FXML
    private TextField textTelefono;
    @FXML
    private TextField textAD;
    @FXML
    private TextField textDI;
    @FXML
    private TextField textSGE;
    @FXML
    private TextField textHLC;
    @FXML
    private TextField textPMDM;
    @FXML
    private TextField textPSP;
    @FXML
    private TextField textEIE;
    @FXML
    private Button botonDescargar;
    @FXML
    private Button botonInsertar;
    @FXML
    private Button botonSalir;
    @FXML
    private TableView <Alumno>tabla;
    @FXML
    private TextField textLocalidad;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columaAD.setCellValueFactory((alumno)->{
            String dato=String.valueOf(alumno.getValue().getModulo().getAd());
            return new SimpleStringProperty(dato);
        });
        columnaSGE.setCellValueFactory((alumno)->{
            String dato=String.valueOf(alumno.getValue().getModulo().getSge());
            return new SimpleStringProperty(dato);
        });
        columnaDI.setCellValueFactory((alumno)->{
            String dato=String.valueOf(alumno.getValue().getModulo().getDi());
            return new SimpleStringProperty(dato);
        });
        columnaPMDM.setCellValueFactory((alumno)->{
            String dato=String.valueOf(alumno.getValue().getModulo().getPmdm());
            return new SimpleStringProperty(dato);
        });
        columnaHLC.setCellValueFactory((alumno)->{
            String dato=String.valueOf(alumno.getValue().getModulo().getHlc());
            return new SimpleStringProperty(dato);
        });
        columnaEIE.setCellValueFactory((alumno)->{
            String dato=String.valueOf(alumno.getValue().getModulo().getEie());
            return new SimpleStringProperty(dato);
        });
        columnaPSP.setCellValueFactory((alumno)->{
            String dato=String.valueOf(alumno.getValue().getModulo().getPsp());
            return new SimpleStringProperty(dato);
        });
        columnaNombre.setCellValueFactory((alumno)->{
            String dato=alumno.getValue().getNombre();
            return new SimpleStringProperty(dato);
        });
        columnaApellido.setCellValueFactory((alumno)->{
            String dato=alumno.getValue().getApellido1()+ " "+alumno.getValue().getApellido2();
            return new SimpleStringProperty(dato);
        });
        columnaDNI.setCellValueFactory((alumno)->{
            String dato=alumno.getValue().getDni();
            return new SimpleStringProperty(dato);
        });
        columnaFecha.setCellValueFactory((alumno)->{
            String dato=String.valueOf(alumno.getValue().getFecha());
            return new SimpleStringProperty(dato);
        });
        columnaTLF.setCellValueFactory((alumno)->{
            String dato=alumno.getValue().getTelefono();
            return new SimpleStringProperty(dato);
        });
        columnaLocalidad.setCellValueFactory((alumno)->{
            String dato=alumno.getValue().getLocalidad();
            return new SimpleStringProperty(dato);
        });
        columnaCorreo.setCellValueFactory((alumno)->{
            String dato=alumno.getValue().getCorreo();
            return new SimpleStringProperty(dato);
        });
        ArrayList<Alumno>lista=AlumnoDAO.getAll();
        rellenoListaAlumnoModulo(lista);
        tabla.getItems().addAll(lista);
        tabla.getSelectionModel().selectedItemProperty().addListener((observableValue, alumno, t1) -> {
           Alumno alumnoTabla=t1;
           Long sumatoria=0L;
           Integer contador=0;
           Double resultadoFinal=0.0;
           ArrayList<Long>notas=new ArrayList<>();
           Long notaAD=alumnoTabla.getModulo().getAd();
            Long notaSGE=alumnoTabla.getModulo().getSge();
            Long notaDI=alumnoTabla.getModulo().getDi();
            Long notaPMDM=alumnoTabla.getModulo().getPmdm();
            Long notaPSP=alumnoTabla.getModulo().getPsp();
            Long notaEIE=alumnoTabla.getModulo().getEie();
            Long notaHLC=alumnoTabla.getModulo().getHlc();
            notas.add(notaAD);notas.add(notaSGE);notas.add(notaDI);notas.add(notaPMDM);
            notas.add(notaPSP);notas.add(notaEIE);notas.add(notaHLC);
            for(Long nota:notas){
                if(nota<5){
                    contador++;
                }
            }
            if(contador==0){
                for(Long nota:notas){
                    sumatoria+=nota;
                }
                Double notaMedia=(double)sumatoria/notas.size();
                resultadoFinal=Math.round(notaMedia*10.0)/10.0;
            }

            Alert alerta=new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("INFORMACION");
            alerta.setHeaderText("DATOS ALUMNO");
            alerta.setContentText(""+alumnoTabla+"\n----------\n"+(contador==0?"La Nota Media es "+resultadoFinal:"El numero de Asignaturas Suspensas es de "+contador));
            alerta.showAndWait();

        });

    }

    private static void rellenoListaAlumnoModulo(ArrayList<Alumno> lista) {
        for(Alumno alumno: lista){
            alumno.getModulo().getAlumnos().add(alumno);
        }
    }

    @FXML
    public void dowload(ActionEvent actionEvent) {
        Connection conexion= MYSQLConnection.getConexion();
        try {
            JasperPrint jasper= JasperFillManager.fillReport("team.jasper", new HashMap<>(),conexion);
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
    public void insertar(ActionEvent actionEvent) {
        if (!textAD.getText().isEmpty()
                &&!textApellidos.getText().isEmpty()
                &&!textDI.getText().isEmpty()
                &&!textEIE.getText().isEmpty()
                &&!textHLC.getText().isEmpty()
                &&!textNombre.getText().isEmpty()
                &&!textPMDM.getText().isEmpty()
                &&!textPSP.getText().isEmpty()
                &&!textSGE.getText().isEmpty()
                &&!textCorreo.getText().isEmpty()
                &&!dateFecha.getValue().toString().equals("")
                &&!textDNI.getText().isEmpty()
                &&!textLocalidad.getText().isEmpty()
                &&!textTelefono.getText().isEmpty()
        ){
            String[] apellidos=textApellidos.getText().split(" ");
            DateTimeFormatter formato=DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechaFormateada=dateFecha.getValue().format(formato);
            LocalDate local=LocalDate.parse(fechaFormateada,formato);
            Date fechaReal=Date.valueOf(local);
            try {
                Modulo modulo=new Modulo(Long.valueOf(textAD.getText()),Long.valueOf(textSGE.getText()),Long.valueOf(textDI.getText()),Long.valueOf(textPMDM.getText()),Long.valueOf(textPSP.getText()),
                        Long.valueOf(textEIE.getText()),Long.valueOf(textHLC.getText()));
                modulo=ModuloDAO.insertarModulo(modulo);
                Alumno alumno=new Alumno(textNombre.getText(),apellidos[0],apellidos[1],textCorreo.getText(),textDNI.getText(),
                        fechaReal,textLocalidad.getText(),textTelefono.getText(),modulo.getId(),modulo);
                alumno=AlumnoDAO.insertarModulo(alumno);

                tabla.getItems().add(alumno);

            } catch (NotaException e) {
                Alert alerta=new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("ERROR");
                alerta.setHeaderText("NOTA FUERA DE RANGO");
                alerta.setContentText("Asegurese de que todos los campos de NOTAS tengan un numero comprendido entre 0-10 ");
                alerta.showAndWait();
            }catch(NumberFormatException n){
                Alert alerta=new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("ERROR");
                alerta.setHeaderText("LETRAS EN NOTAS");
                alerta.setContentText("Asegurese de que todos los campos de NOTAS tengan un numero ");
                alerta.showAndWait();

            }
           /* */
        }else{
            Alert alerta=new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText("CAMPO VACIO");
            alerta.setContentText("Asegurese de que todos los campos esten rellenos");
            alerta.showAndWait();


        }

    }

    @FXML
    public void salir(ActionEvent actionEvent) {
        System.exit(0);
    }
}

