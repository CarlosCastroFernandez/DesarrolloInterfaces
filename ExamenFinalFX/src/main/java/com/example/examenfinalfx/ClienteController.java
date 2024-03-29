package com.example.examenfinalfx;

import clase.Cliente;
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
import java.util.HashMap;
import java.util.ResourceBundle;

public class ClienteController implements Initializable {

    @FXML
    private RadioButton radioHombre;
    @FXML
    private RadioButton radioMujer;
    @FXML
    private TextField textPeso;
    @FXML
    private TextField textTalla;
    @FXML
    private TextField textEdad;
    @FXML
    private ComboBox comboActividad;
    @FXML
    private TextArea textAreaObs;
    @FXML
    private Button botonGuardar;
    @FXML
    private Label labelInfo;
    @FXML
    private ToggleGroup uno;
    @FXML
    private TextField textNombre;
    @FXML
    private Button botonDescargar;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        comboActividad.getItems().add("Sedentario");
        comboActividad.getItems().add("Moderado");
        comboActividad.getItems().add("Activo");
        comboActividad.getItems().add("Muy Activo");
        comboActividad.getSelectionModel().selectFirst();

    }
    @FXML
    public void guardar(ActionEvent actionEvent) {
        if(!textPeso.getText().isEmpty()&&!textNombre.getText().isEmpty()&&!textEdad.getText().isEmpty()&&!textTalla.getText().isEmpty()
        &&!textAreaObs.getText().isEmpty()&&comboActividad.getValue()!=null){
            Float peso=Float.valueOf(textPeso.getText());
            Integer talla=Integer.valueOf(textTalla.getText());
            Integer edad=Integer.valueOf(textEdad.getText());
            String nivelActividad=comboActividad.getValue().toString();
            String sexo="";
            Double calculoGER=null;
            Float dato=0.0f;
            if(radioHombre.isSelected()){
                sexo="hombre";
            }else if (radioMujer.isSelected()){
                sexo="mujer";
            }
            Cliente cliente=new Cliente(textNombre.getText(),sexo,peso,edad,talla,nivelActividad,textAreaObs.getText());
            if(radioHombre.isSelected()){
                calculoGER=66.473+13.751*cliente.getPeso()+5.0033*cliente.getTalla()-6.755*cliente.getEdad();
            }else if (radioMujer.isSelected()){
                calculoGER=655.0955+9.463*cliente.getPeso()+1.8496*cliente.getTalla()-4.6756*cliente.getEdad();
            }else{
                labelInfo.setStyle("-fx-background-color: #FF2B2B");
                labelInfo.setText("Seleccion el sexo Por Favor");
            }
            if(calculoGER!=null){
                calculoGER=Math.round(calculoGER*10.0)/10.0;
                dato=cliente.datoPorActividad(cliente.getTipoActividad(), cliente.getSexo());
                Double resultado=calculoGER*dato;
                resultado=Math.round(resultado*10.0)/10.0;
                labelInfo.setStyle("-fx-background-color: #84EF2C");
                labelInfo.setText("El CLiente "+cliente.getNombre()+" tiene un GER de "+calculoGER +" y un GET de "+resultado);
            }


        }else{
            labelInfo.setStyle("-fx-background-color: #FF2B2B");
            labelInfo.setText("Por favor no deje ningun Campo Vacio");
        }

    }


    @FXML
    public void descargar(ActionEvent actionEvent) {
        Connection conexion= MYSQLConnection.getConexion();
        try {
            JasperPrint jasper= JasperFillManager.fillReport("examenFinalFX.jasper", new HashMap<>(),conexion);
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
}