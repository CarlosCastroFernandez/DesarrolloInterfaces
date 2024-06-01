package com.example.ininprotec;

import Util.Utilidad;
import clase.Servicio;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import implement.ServicioDAOImplement;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GraficaController implements Initializable {
    @javafx.fxml.FXML
    private BarChart <String, Number>grafica;
    @javafx.fxml.FXML
    private CategoryAxis xNombre;
    @javafx.fxml.FXML
    private NumberAxis yNumeros;
    @javafx.fxml.FXML
    private TableView <Servicio>tabla;
    @javafx.fxml.FXML
    private TableColumn <Servicio,String>cNombre;
    @javafx.fxml.FXML
    private TableColumn <Servicio,String>cBeneficio;
    private ObservableList obs;
    private String nombreServicio;
    private Double comision;
    private Integer horasLunes;
    private Integer horasMartes;
    private Integer horasMiercoles;
    private Integer horasJueves;
    private Integer horasViernes;
    private Integer horasSabado;
    private Integer horasDomingo;
    private Integer horasFestivo;
    private Integer horasLV=0;
    private Integer horasSDF=0;
    private Integer horasTotales=0;
    private DatePicker datePickerInicio;
    private DatePicker datePickerFin;

private  XYChart.Series<String,Number>series;
    @javafx.fxml.FXML
    private TableColumn <Servicio,String>cLaboral;
    @javafx.fxml.FXML
    private TableColumn <Servicio,String>cFestivos;
    @javafx.fxml.FXML
    private TableColumn <Servicio,String>cHorasTotales;
    @javafx.fxml.FXML
    private TableColumn <Servicio,String>cComision;
    @javafx.fxml.FXML
    private TextField textBuscador;
    private ObservableList <Servicio>listaInicialTabla=FXCollections.observableArrayList();
    private ObservableList <Servicio>filtroBuscador=FXCollections.observableArrayList();
    @javafx.fxml.FXML
    private Button botonBorrar;
    private Servicio servicioElegido;
    private CheckBox checkBox;
private ArrayList<String>listadoFestivos=new ArrayList<>();
    @javafx.fxml.FXML
    private Button añadir;
    @javafx.fxml.FXML
    private ImageView imagenFlecha;
    @javafx.fxml.FXML
    private TableColumn <Servicio,String>cDireccion;
    @javafx.fxml.FXML
    private TableColumn<Servicio,String> cEscoltas;
    @javafx.fxml.FXML
    private TableColumn<Servicio,String> cPrecio;
    @javafx.fxml.FXML
    private TableColumn<Servicio,String> cIva;
    @javafx.fxml.FXML
    private TableColumn <Servicio,String>cSalario;
    @javafx.fxml.FXML
    private TableColumn <Servicio,String>cFechaInicio;
    @javafx.fxml.FXML
    private TableColumn<Servicio,String> cFechaFin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Image imagenFlechia=new Image(CreacionCursoController.class.getClassLoader().getResource("imagenes/flechita.png").toExternalForm());
        imagenFlecha.setImage(imagenFlechia);
        imagenFlecha.setOnMouseClicked(mouseEvent -> {
            HelloApplication.cambioVentana("principal-view.fxml");
        });

        cNombre.setCellValueFactory(servicio->{
            String nombre=servicio.getValue().getNombre();
            return new SimpleStringProperty(nombre);
        });
        cBeneficio.setCellValueFactory(servicio->{
            String beneficio=String.valueOf(servicio.getValue().getBeneficio())+" €";
            return new SimpleStringProperty(beneficio);
        });
        cComision.setCellValueFactory(servicio->{
            String beneficio=String.valueOf(servicio.getValue().getComision())+" €";
            return new SimpleStringProperty(beneficio);
        });
        cFestivos.setCellValueFactory(servicio->{
            String beneficio=String.valueOf(servicio.getValue().getHorasSDF())+" h/Mes";
            return new SimpleStringProperty(beneficio);
        });
        cLaboral.setCellValueFactory(servicio->{
            String beneficio=String.valueOf(servicio.getValue().getHorasLV())+" h/Mes";
            return new SimpleStringProperty(beneficio);
        });
        cHorasTotales.setCellValueFactory(servicio->{
            String beneficio=String.valueOf(servicio.getValue().getHoraTotal())+" h";
            return new SimpleStringProperty(beneficio);
        });
        cDireccion.setCellValueFactory(servicio->{
            String beneficio=servicio.getValue().getDireccion();
            return new SimpleStringProperty(beneficio);
        });
        cEscoltas.setCellValueFactory(servicio->{
            String beneficio=String.valueOf(servicio.getValue().getnEscoltas());
            return new SimpleStringProperty(beneficio);
        });
        cPrecio.setCellValueFactory(servicio->{
            String beneficio=String.valueOf(servicio.getValue().getPrecio())+ " €";
            return new SimpleStringProperty(beneficio);
        });
        cIva.setCellValueFactory(servicio->{
            String beneficio=servicio.getValue().getIva();
            return new SimpleStringProperty(beneficio);
        });
        cSalario.setCellValueFactory(servicio->{
            String beneficio=String.valueOf(servicio.getValue().getSalario())+ " €";
            return new SimpleStringProperty(beneficio);
        });
        cFechaInicio.setCellValueFactory(servicio->{
            String beneficio=servicio.getValue().getFechaIn();
            return new SimpleStringProperty(beneficio);
        });
        cFechaFin.setCellValueFactory(servicio->{
            String beneficio=servicio.getValue().getFechaFin();
            return new SimpleStringProperty(beneficio);
        });


        obs= FXCollections.observableArrayList();
        List<Servicio>servicios=(new ServicioDAOImplement()).getAll();
        listaInicialTabla.addAll(servicios);
        tabla.setItems(listaInicialTabla);
        for(int i=0;i<servicios.size();i++){
            obs.add(new XYChart.Data<>(servicios.get(i).getNombre(), servicios.get(i).getBeneficio()));
        }
        series=new XYChart.Series<>(obs);
        grafica.getData().addAll(series);


        añadir.setOnMouseClicked(mouseEvent -> {
            showDialog();

        });
        tabla.getSelectionModel().selectedItemProperty().addListener((observableValue, servicio, t1) -> {
            servicioElegido=t1;
        });
        LocalDate fechaActual=LocalDate.now();
        String añoActual=String.valueOf(fechaActual.getYear());
        LocalDate start = LocalDate.now().withDayOfMonth(1);
        LocalDate end = start.plusMonths(1).minusDays(1);
        DateTimeFormatter formato=DateTimeFormatter.ofPattern("yyyy-MM");
        String fechaParse=fechaActual.format(formato);
        File archivoFecha=new File("./fecha-actual.txt");
        try {
            BufferedReader lectura=new BufferedReader(new FileReader(archivoFecha));
            String leer= lectura.readLine();
            lectura.close();
            if(leer!=null&&!leer.equals(fechaParse)){
                obs.clear();
                listaInicialTabla.clear();
                List<String> dateList = new ArrayList<>();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                List<Servicio>serviciosActuales=(new ServicioDAOImplement().getAll());
                if(!serviciosActuales.isEmpty()){
                    for(Servicio servicio:serviciosActuales){
                        if(servicio.getMunicipio().equals("malaga")){
                            listadoFestivos.add(añoActual+"-08-19");
                            listadoFestivos.add(añoActual+"-09-09");
                        } else if (servicio.getMunicipio().equals("marbella")) {
                            listadoFestivos.add(añoActual+"-06-11");
                            listadoFestivos.add(añoActual+"-10-19");

                        } else if (servicio.getMunicipio().equals("estepona")) {
                            listadoFestivos.add(añoActual+"-05-15");
                            listadoFestivos.add(añoActual+"-07-16");
                            listadoFestivos.add(añoActual+"-07-17");

                        }
                        ArrayList<String>festivos=listaFechaFestivos(listadoFestivos);
                        if(servicio.getFechaIn().equals("Indefinido")||servicio.getFechaFin().equals("Indefinido")){
                            for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
                                dateList.add(date.format(formatter));
                                String fecha=date.format(formatter);
                                if (date.getDayOfWeek() == DayOfWeek.MONDAY &&!festivos.contains(fecha)) {

                                    horasTotales += servicio.getHorasLunes();
                                    horasLV+=servicio.getHorasLunes();
                                } else if(date.getDayOfWeek()==DayOfWeek.MONDAY&&festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasFestivos();
                                    horasSDF+=servicio.getHorasFestivos();
                                }else if(date.getDayOfWeek()==DayOfWeek.TUESDAY&&!festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasMartes();
                                    horasLV+=servicio.getHorasMartes();
                                }else if(date.getDayOfWeek()==DayOfWeek.TUESDAY&&festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasFestivos();
                                    horasSDF+=servicio.getHorasFestivos();
                                }else if(date.getDayOfWeek()==DayOfWeek.FRIDAY&&!festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasViernes();
                                    horasLV+=servicio.getHorasViernes();
                                }else if(date.getDayOfWeek()==DayOfWeek.FRIDAY&&festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales +=servicio.getHorasFestivos();
                                    horasSDF+=servicio.getHorasFestivos();
                                }else if(date.getDayOfWeek()==DayOfWeek.THURSDAY&&!festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasJueves();
                                    horasLV+=servicio.getHorasJueves();
                                }else if(date.getDayOfWeek()==DayOfWeek.THURSDAY&&festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasFestivos();
                                    horasSDF+=servicio.getHorasFestivos();
                                }else if(date.getDayOfWeek()==DayOfWeek.WEDNESDAY&&!festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasMiercoles();
                                    horasLV+=servicio.getHorasMiercoles();
                                }else if(date.getDayOfWeek()==DayOfWeek.WEDNESDAY&&festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasFestivos();
                                    horasSDF+=servicio.getHorasFestivos();
                                }else if(date.getDayOfWeek()==DayOfWeek.SATURDAY&&!festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasSabado();
                                    horasSDF+=servicio.getHorasSabado();
                                }else if(date.getDayOfWeek()==DayOfWeek.SATURDAY&&festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasFestivos();
                                    horasSDF+=servicio.getHorasFestivos();
                                }else if(date.getDayOfWeek()==DayOfWeek.SUNDAY&&!festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasDomingo();
                                    horasSDF+=servicio.getHorasDomingo();
                                }else if(date.getDayOfWeek()==DayOfWeek.SUNDAY&&festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasFestivos();
                                    horasSDF+=servicio.getHorasFestivos();
                                }
                            }
                        }else{
                            LocalDate fechaIn=LocalDate.parse(servicio.getFechaIn(),formatter);
                            LocalDate fechaFin=LocalDate.parse(servicio.getFechaFin(),formatter);
                            for (LocalDate date = fechaIn; !date.isAfter(fechaFin); date = date.plusDays(1)){
                                dateList.add(date.format(formatter));
                                String fecha=date.format(formatter);
                                if (date.getDayOfWeek() == DayOfWeek.MONDAY &&!festivos.contains(fecha)) {

                                    horasTotales += servicio.getHorasLunes();
                                    horasLV+=servicio.getHorasLunes();
                                } else if(date.getDayOfWeek()==DayOfWeek.MONDAY&&festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasFestivos();
                                    horasSDF+=servicio.getHorasFestivos();
                                }else if(date.getDayOfWeek()==DayOfWeek.TUESDAY&&!festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasMartes();
                                    horasLV+=servicio.getHorasMartes();
                                }else if(date.getDayOfWeek()==DayOfWeek.TUESDAY&&festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasFestivos();
                                    horasSDF+=servicio.getHorasFestivos();
                                }else if(date.getDayOfWeek()==DayOfWeek.FRIDAY&&!festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasViernes();
                                    horasLV+=servicio.getHorasViernes();
                                }else if(date.getDayOfWeek()==DayOfWeek.FRIDAY&&festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales +=servicio.getHorasFestivos();
                                    horasSDF+=servicio.getHorasFestivos();
                                }else if(date.getDayOfWeek()==DayOfWeek.THURSDAY&&!festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasJueves();
                                    horasLV+=servicio.getHorasJueves();
                                }else if(date.getDayOfWeek()==DayOfWeek.THURSDAY&&festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasFestivos();
                                    horasSDF+=servicio.getHorasFestivos();
                                }else if(date.getDayOfWeek()==DayOfWeek.WEDNESDAY&&!festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasMiercoles();
                                    horasLV+=servicio.getHorasMiercoles();
                                }else if(date.getDayOfWeek()==DayOfWeek.WEDNESDAY&&festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasFestivos();
                                    horasSDF+=servicio.getHorasFestivos();
                                }else if(date.getDayOfWeek()==DayOfWeek.SATURDAY&&!festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasSabado();
                                    horasSDF+=servicio.getHorasSabado();
                                }else if(date.getDayOfWeek()==DayOfWeek.SATURDAY&&festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasFestivos();
                                    horasSDF+=servicio.getHorasFestivos();
                                }else if(date.getDayOfWeek()==DayOfWeek.SUNDAY&&!festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasDomingo();
                                    horasSDF+=servicio.getHorasDomingo();
                                }else if(date.getDayOfWeek()==DayOfWeek.SUNDAY&&festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasFestivos();
                                    horasSDF+=servicio.getHorasFestivos();
                                }
                            }

                        }

                        Double beneficio=Math.round((servicio.getComision() *horasTotales)*10.0)/10.0;
                        servicio.setBeneficio(beneficio);
                        servicio.setHoraTotal(horasTotales);
                        servicio.setHorasLV(horasLV);
                        servicio.setHorasSDF(horasSDF);
                        listaInicialTabla.add(servicio);
                        obs.add(new XYChart.Data<>(servicio.getNombre(), beneficio));
                        (new ServicioDAOImplement()).actualizacionBeneficio(servicio);
                        horasTotales=0;
                        horasSDF=0;
                        horasLV=0;
                    }
                }
                BufferedWriter escritura=new BufferedWriter(new FileWriter(archivoFecha,false));
                escritura.write(fechaParse);
                escritura.close();

            }else if(leer==null){
                obs.clear();
                listaInicialTabla.clear();
                List<String> dateList = new ArrayList<>();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                List<Servicio>serviciosActuales=(new ServicioDAOImplement().getAll());
                if(!serviciosActuales.isEmpty()){
                    for(Servicio servicio:serviciosActuales){
                        if(servicio.getMunicipio().equals("malaga")){
                            listadoFestivos.add(añoActual+"-08-19");
                            listadoFestivos.add(añoActual+"-09-09");
                        } else if (servicio.getMunicipio().equals("marbella")) {
                            listadoFestivos.add(añoActual+"-06-11");
                            listadoFestivos.add(añoActual+"-10-19");

                        } else if (servicio.getMunicipio().equals("estepona")) {
                            listadoFestivos.add(añoActual+"-05-15");
                            listadoFestivos.add(añoActual+"-07-16");
                            listadoFestivos.add(añoActual+"-07-17");

                        }
                        ArrayList<String>festivos=listaFechaFestivos(listadoFestivos);
                        if(servicio.getFechaIn().equals("Indefinido")||servicio.getFechaFin().equals("Indefinido")){
                            for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
                                dateList.add(date.format(formatter));
                                String fecha=date.format(formatter);
                                if (date.getDayOfWeek() == DayOfWeek.MONDAY &&!festivos.contains(fecha)) {

                                    horasTotales += servicio.getHorasLunes();
                                    horasLV+=servicio.getHorasLunes();
                                } else if(date.getDayOfWeek()==DayOfWeek.MONDAY&&festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasFestivos();
                                    horasSDF+=servicio.getHorasFestivos();
                                }else if(date.getDayOfWeek()==DayOfWeek.TUESDAY&&!festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasMartes();
                                    horasLV+=servicio.getHorasMartes();
                                }else if(date.getDayOfWeek()==DayOfWeek.TUESDAY&&festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasFestivos();
                                    horasSDF+=servicio.getHorasFestivos();
                                }else if(date.getDayOfWeek()==DayOfWeek.FRIDAY&&!festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasViernes();
                                    horasLV+=servicio.getHorasViernes();
                                }else if(date.getDayOfWeek()==DayOfWeek.FRIDAY&&festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales +=servicio.getHorasFestivos();
                                    horasSDF+=servicio.getHorasFestivos();
                                }else if(date.getDayOfWeek()==DayOfWeek.THURSDAY&&!festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasJueves();
                                    horasLV+=servicio.getHorasJueves();
                                }else if(date.getDayOfWeek()==DayOfWeek.THURSDAY&&festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasFestivos();
                                    horasSDF+=servicio.getHorasFestivos();
                                }else if(date.getDayOfWeek()==DayOfWeek.WEDNESDAY&&!festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasMiercoles();
                                    horasLV+=servicio.getHorasMiercoles();
                                }else if(date.getDayOfWeek()==DayOfWeek.WEDNESDAY&&festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasFestivos();
                                    horasSDF+=servicio.getHorasFestivos();
                                }else if(date.getDayOfWeek()==DayOfWeek.SATURDAY&&!festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasSabado();
                                    horasSDF+=servicio.getHorasSabado();
                                }else if(date.getDayOfWeek()==DayOfWeek.SATURDAY&&festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasFestivos();
                                    horasSDF+=servicio.getHorasFestivos();
                                }else if(date.getDayOfWeek()==DayOfWeek.SUNDAY&&!festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasDomingo();
                                    horasSDF+=servicio.getHorasDomingo();
                                }else if(date.getDayOfWeek()==DayOfWeek.SUNDAY&&festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasFestivos();
                                    horasSDF+=servicio.getHorasFestivos();
                                }
                            }
                        }else{
                            LocalDate fechaIn=LocalDate.parse(servicio.getFechaIn(),formatter);
                            LocalDate fechaFin=LocalDate.parse(servicio.getFechaFin(),formatter);
                            for (LocalDate date = fechaIn; !date.isAfter(fechaFin); date = date.plusDays(1)){
                                dateList.add(date.format(formatter));
                                String fecha=date.format(formatter);
                                if (date.getDayOfWeek() == DayOfWeek.MONDAY &&!festivos.contains(fecha)) {

                                    horasTotales += servicio.getHorasLunes();
                                    horasLV+=servicio.getHorasLunes();
                                } else if(date.getDayOfWeek()==DayOfWeek.MONDAY&&festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasFestivos();
                                    horasSDF+=servicio.getHorasFestivos();
                                }else if(date.getDayOfWeek()==DayOfWeek.TUESDAY&&!festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasMartes();
                                    horasLV+=servicio.getHorasMartes();
                                }else if(date.getDayOfWeek()==DayOfWeek.TUESDAY&&festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasFestivos();
                                    horasSDF+=servicio.getHorasFestivos();
                                }else if(date.getDayOfWeek()==DayOfWeek.FRIDAY&&!festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasViernes();
                                    horasLV+=servicio.getHorasViernes();
                                }else if(date.getDayOfWeek()==DayOfWeek.FRIDAY&&festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales +=servicio.getHorasFestivos();
                                    horasSDF+=servicio.getHorasFestivos();
                                }else if(date.getDayOfWeek()==DayOfWeek.THURSDAY&&!festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasJueves();
                                    horasLV+=servicio.getHorasJueves();
                                }else if(date.getDayOfWeek()==DayOfWeek.THURSDAY&&festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasFestivos();
                                    horasSDF+=servicio.getHorasFestivos();
                                }else if(date.getDayOfWeek()==DayOfWeek.WEDNESDAY&&!festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasMiercoles();
                                    horasLV+=servicio.getHorasMiercoles();
                                }else if(date.getDayOfWeek()==DayOfWeek.WEDNESDAY&&festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasFestivos();
                                    horasSDF+=servicio.getHorasFestivos();
                                }else if(date.getDayOfWeek()==DayOfWeek.SATURDAY&&!festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasSabado();
                                    horasSDF+=servicio.getHorasSabado();
                                }else if(date.getDayOfWeek()==DayOfWeek.SATURDAY&&festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasFestivos();
                                    horasSDF+=servicio.getHorasFestivos();
                                }else if(date.getDayOfWeek()==DayOfWeek.SUNDAY&&!festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasDomingo();
                                    horasSDF+=servicio.getHorasDomingo();
                                }else if(date.getDayOfWeek()==DayOfWeek.SUNDAY&&festivos.contains(fecha)) {
                                    // Suma 12 para los días de lunes a viernes
                                    horasTotales += servicio.getHorasFestivos();
                                    horasSDF+=servicio.getHorasFestivos();
                                }
                            }
                        }

                        Double beneficio=Math.round((servicio.getComision() *horasTotales)*10.0)/10.0;
                        servicio.setBeneficio(beneficio);
                        servicio.setHoraTotal(horasTotales);
                        servicio.setHorasLV(horasLV);
                        servicio.setHorasSDF(horasSDF);
                        listaInicialTabla.add(servicio);
                        obs.add(new XYChart.Data<>(servicio.getNombre(), beneficio));
                        (new ServicioDAOImplement()).actualizacionBeneficio(servicio);
                        horasTotales=0;
                        horasSDF=0;
                        horasLV=0;
                    }
                }
                BufferedWriter escritura=new BufferedWriter(new FileWriter(archivoFecha,false));
                escritura.write(fechaParse);
                escritura.close();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    private void showDialog() {

        // Crear el diálogo
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
        textField1.setPromptText("Nombre del Servicio");

         checkBox=new CheckBox();

         datePickerInicio=new DatePicker();
        datePickerInicio.setPromptText("Fecha de Inicio");

         datePickerFin=new DatePicker();
        datePickerFin.setPromptText("Fecha de Fin");

        TextField textField12=new TextField();
        textField12.setPromptText("Número de escoltas");

        TextField textField13=new TextField();
        textField13.setPromptText("Dirección");


        TextField textField14=new TextField();
        textField14.setPromptText("Precio");


        TextField textField15=new TextField();
        textField15.setPromptText("IVA");


        TextField textField16=new TextField();
        textField16.setPromptText("Salario");

        TextField textField2 = new TextField();
        textField2.setPromptText("Horas Lunes");

        TextField textField4 = new TextField(); // Nuevo campo de texto
        textField4.setPromptText("Costo del Servicio");

        TextField textField5 = new TextField();
        textField5.setPromptText("Horas Martes");

        TextField textField6 = new TextField();
        textField6.setPromptText("Horas Miercoles");

        TextField textField7 = new TextField();
        textField7.setPromptText("Horas Jueves");

        TextField textField8 = new TextField();
        textField8.setPromptText("Horas Viernes");

        TextField textField9 = new TextField();
        textField9.setPromptText("Horas Sábado");

        TextField textField10 = new TextField();
        textField10.setPromptText("Horas Domingo");

        TextField textField11 = new TextField();
        textField11.setPromptText("Horas Festivo");

        ComboBox<String>comboMunicipio=new ComboBox<>();
        comboMunicipio.setPromptText("Municipio...");
        comboMunicipio.getItems().add("Málaga");
        comboMunicipio.getItems().add("Marbella");
        comboMunicipio.getItems().add("Estepona");
        try{
            checkBox.setOnAction(actionEvent -> {
                if(checkBox.isSelected()){
                    datePickerInicio.setDisable(true);
                    datePickerFin.setDisable(true);
                }else{
                    datePickerInicio.setDisable(false);
                    datePickerFin.setDisable(false);
                }
            });

        }catch(Exception e){
            e.printStackTrace();
        }


        grid.add(new Label("Nombre  Del Servicio:"), 0, 0);
        grid.add(textField1, 1, 0);

        grid.add(new Label("¿Fecha Indefinida?"),0,1);
        grid.add(checkBox,1,1);

        grid.add(new Label("Fecha de Inicio"),0,2);
        grid.add(datePickerInicio,1,2);

        grid.add(new Label("Fecha de Fin"),0,3);
        grid.add(datePickerFin,1,3);

        grid.add(new Label("Número de Escolta"),0,4);
        grid.add(textField12,1,4);

        grid.add(new Label("Dirección"),0,5);
        grid.add(textField13,1,5);

        grid.add(new Label("IVA"),0,6);
        grid.add(textField15,1,6);

        grid.add(new Label("Salario"),0,7);
        grid.add(textField16,1,7);

        grid.add(new Label("Precio"),0,8);
        grid.add(textField14,1,8);

        grid.add(new Label("Horas Lunes:"), 0, 9);
        grid.add(textField2, 1, 9);

        grid.add(new Label("Horas Martes:"), 0, 10);
        grid.add(textField5, 1, 10);

        grid.add(new Label("Horas Miercoles:"), 0, 11);
        grid.add(textField6, 1, 11);

        grid.add(new Label("Horas Jueves:"), 0, 12);
        grid.add(textField7, 1, 12);

        grid.add(new Label("Horas Viernes:"), 0, 13);
        grid.add(textField8, 1, 13);

        grid.add(new Label("Horas Sábado:"), 0, 14);
        grid.add(textField9, 1, 14);

        grid.add(new Label("Horas Domingo:"), 0, 15);
        grid.add(textField10, 1, 15);

        grid.add(new Label("Horas Feestivos:"), 0, 16);
        grid.add(textField11, 1, 16);


        grid.add(new Label("Comisión del Servicio:"), 0, 17);
        grid.add(textField4, 1, 17);

        grid.add(new Label("Municipio:"), 0, 18);
        grid.add(comboMunicipio, 1, 18);

        // Incorporar el grid al diálogo
        dialog.getDialogPane().setContent(grid);

        // Request focus on the first field by default.
        Platform.runLater(textField1::requestFocus);

        // Convertir la entrada en un par cuando el botón OK es presionado.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK&&!textField1.getText().isEmpty()&&!textField2.getText().isEmpty()&&
            !textField5.getText().isEmpty()&&!textField4.getText().isEmpty()&&comboMunicipio.getValue()!=null
            &&!textField6.getText().isEmpty()&&!textField7.getText().isEmpty()&&!textField8.getText().isEmpty()&&
            !textField9.getText().isEmpty()&&!textField10.getText().isEmpty()&&!textField11.getText().isEmpty()
                    &&!textField12.getText().isEmpty()&&!textField13.getText().isEmpty()&&!textField14.getText().isEmpty()
                    &&!textField15.getText().isEmpty()&&!textField16.getText().isEmpty()&&checkBox.isSelected()) {

                String municipio=comboMunicipio.getValue();
                municipio=municipio.toLowerCase();
                municipio=municipio.replace("á","a");
                municipio=municipio.replace("é","e");
                municipio=municipio.replace("í","i");
                municipio=municipio.replace("ó","o");
                municipio=municipio.replace("ú","u");
                HashMap<String, String> data = new HashMap<>();
                data.put("nombreServicio", textField1.getText());
                data.put("horasLunes", textField2.getText());
                data.put("horasMartes", textField5.getText());
                data.put("horasMiercoles", textField6.getText());
                data.put("horasJueves", textField7.getText());
                data.put("horasViernes", textField8.getText());
                data.put("horasSabado", textField9.getText());
                data.put("horasDomingo", textField10.getText());
                data.put("horasFestivos", textField11.getText());
                data.put("comision", textField4.getText());
                data.put("municipio",municipio);
                data.put("direccion",textField13.getText());
                data.put("escoltas",textField12.getText());
                data.put("precio",textField14.getText());
                data.put("iva",textField15.getText());
                data.put("salario",textField16.getText());
                data.put("fechaInicio",(datePickerInicio.getValue()==null?"Indefinido":String.valueOf(datePickerInicio.getValue())));
                data.put("fechaFin",(datePickerFin.getValue()==null?"Indefinido":String.valueOf(datePickerFin.getValue())));
                data.put("aceptada",(checkBox.isSelected()?"si":"no"));
                return data;
            }else if(dialogButton == ButtonType.OK&&!textField1.getText().isEmpty()&&!textField2.getText().isEmpty()&&
                    !textField5.getText().isEmpty()&&!textField4.getText().isEmpty()&&comboMunicipio.getValue()!=null
                    &&!textField6.getText().isEmpty()&&!textField7.getText().isEmpty()&&!textField8.getText().isEmpty()&&
                    !textField9.getText().isEmpty()&&!textField10.getText().isEmpty()&&!textField11.getText().isEmpty()&&
                     !textField12.getText().isEmpty()&&!textField13.getText().isEmpty()&&!textField14.getText().isEmpty()
                    &&!textField15.getText().isEmpty()&&!textField16.getText().isEmpty()&&!checkBox.isSelected()
            &&datePickerInicio.getValue()!=null&&datePickerFin.getValue()!=null){
                String municipio=comboMunicipio.getValue();
                municipio=municipio.toLowerCase();
                municipio=municipio.replace("á","a");
                municipio=municipio.replace("é","e");
                municipio=municipio.replace("í","i");
                municipio=municipio.replace("ó","o");
                municipio=municipio.replace("ú","u");
                HashMap<String, String> data = new HashMap<>();
                data.put("nombreServicio", textField1.getText());
                data.put("horasLunes", textField2.getText());
                data.put("horasMartes", textField5.getText());
                data.put("horasMiercoles", textField6.getText());
                data.put("horasJueves", textField7.getText());
                data.put("horasViernes", textField8.getText());
                data.put("horasSabado", textField9.getText());
                data.put("horasDomingo", textField10.getText());
                data.put("horasFestivos", textField11.getText());
                data.put("comision", textField4.getText());
                data.put("municipio",municipio);
                data.put("direccion",textField13.getText());
                data.put("escoltas",textField12.getText());
                data.put("precio",textField14.getText());
                data.put("iva",textField15.getText());
                data.put("salario",textField16.getText());
                data.put("fechaInicio",(datePickerInicio.getValue()==null?"Indefinido":String.valueOf(datePickerInicio.getValue())));
                data.put("fechaFin",(datePickerFin.getValue()==null?"Indefinido":String.valueOf(datePickerFin.getValue())));
                data.put("aceptada",(checkBox.isSelected()?"si":"no"));
                return data;
            }
            return null;
        });

        // Mostrar el diálogo y capturar el resultado
        Optional<HashMap<String, String>> result = dialog.showAndWait();

        result.ifPresent(data -> {
            try{
                LocalDate año=LocalDate.now();
                String añoActual=String.valueOf(año.getYear());
                nombreServicio= data.get("nombreServicio");
                horasLunes=Integer.valueOf( data.get("horasLunes"));
                horasMartes=Integer.valueOf(data.get("horasMartes"));
                horasMiercoles=Integer.valueOf(data.get("horasMiercoles"));
                horasJueves=Integer.valueOf(data.get("horasJueves"));
                horasViernes=Integer.valueOf(data.get("horasViernes"));
                horasSabado=Integer.valueOf(data.get("horasSabado"));
                horasDomingo=Integer.valueOf(data.get("horasDomingo"));
                horasFestivo=Integer.valueOf(data.get("horasFestivos"));
                String direccion=data.get("direccion");
                Integer nEscoltas=Integer.valueOf(data.get("escoltas"));
                String iva=data.get("iva");
                Double precio=Double.valueOf(data.get("precio"));
                Double salario=Double.valueOf(data.get("salario"));
                String fechaInicio=data.get("fechaInicio");
                String fechaFin=data.get("fechaFin");
                Boolean selected=(data.get("aceptada").equals("si")?true:false);

                comision =Double.valueOf(data.get("comision"));
                String municipio=data.get("municipio");
                if(municipio.equals("malaga")){
                    listadoFestivos.add(añoActual+"-08-19");
                    listadoFestivos.add(añoActual+"-09-09");

                } else if (municipio.equals("marbella")) {
                    listadoFestivos.add(añoActual+"-06-11");
                    listadoFestivos.add(añoActual+"-10-19");

                } else if (municipio.equals("estepona")) {
                    listadoFestivos.add(añoActual+"-05-15");
                    listadoFestivos.add(añoActual+"-07-16");
                    listadoFestivos.add(añoActual+"-07-17");

                }
                Double beneficios=calculoComision(selected,fechaInicio,fechaFin);
                Servicio servicioNuevo=new Servicio(nombreServicio,horasLunes,horasMartes,horasMiercoles,horasJueves,
                        horasViernes,horasSabado,horasDomingo,horasFestivo,horasLV,horasSDF,comision
                        ,beneficios,horasTotales,municipio,direccion,nEscoltas,precio,iva,salario,fechaInicio,fechaFin);
                horasTotales=0;
                horasLV=0;
                horasSDF=0;
                servicioNuevo=(new ServicioDAOImplement().subida(servicioNuevo));
                listaInicialTabla.add(servicioNuevo);
                obs.add(new XYChart.Data<>(nombreServicio, beneficios));
            }catch (Exception e){
                e.printStackTrace();
                Alert alerta=new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("ERROR");
                alerta.setHeaderText("Datos Erroneos");
                alerta.setContentText("Asegurese de que los datos no sean erroneos");
                alerta.showAndWait();
            }

        });
    }
    private Double calculoComision(Boolean selected,String fechaInicio,String fechaFinal){

        LocalDate start = LocalDate.now().withDayOfMonth(1);
        LocalDate end = start.plusMonths(1).minusDays(1);
        ArrayList<String>festivos=listaFechaFestivos(listadoFestivos);
        System.out.println(festivos);
        List<String> dateList = new ArrayList<>();

        // Formateador de fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Itera desde el primer día hasta el último día del mes
        if(selected){
            for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
                // Agregar la fecha formateada a la lista
                dateList.add(date.format(formatter));
                String fecha=date.format(formatter);
                if (date.getDayOfWeek() == DayOfWeek.MONDAY &&!festivos.contains(fecha)) {

                    horasTotales += horasLunes;
                    horasLV+=horasLunes;
                } else if(date.getDayOfWeek()==DayOfWeek.MONDAY&&festivos.contains(fecha)) {
                    // Suma 12 para los días de lunes a viernes
                    horasTotales += horasFestivo;
                    horasSDF+=horasFestivo;
                }else if(date.getDayOfWeek()==DayOfWeek.TUESDAY&&!festivos.contains(fecha)) {
                    // Suma 12 para los días de lunes a viernes
                    horasTotales += horasMartes;
                    horasLV+=horasMartes;
                }else if(date.getDayOfWeek()==DayOfWeek.TUESDAY&&festivos.contains(fecha)) {
                    // Suma 12 para los días de lunes a viernes
                    horasTotales += horasFestivo;
                    horasSDF+=horasFestivo;
                }else if(date.getDayOfWeek()==DayOfWeek.FRIDAY&&!festivos.contains(fecha)) {
                    // Suma 12 para los días de lunes a viernes
                    horasTotales += horasViernes;
                    horasLV+=horasViernes;
                }else if(date.getDayOfWeek()==DayOfWeek.FRIDAY&&festivos.contains(fecha)) {
                    // Suma 12 para los días de lunes a viernes
                    horasTotales += horasFestivo;
                    horasSDF+=horasFestivo;
                }else if(date.getDayOfWeek()==DayOfWeek.THURSDAY&&!festivos.contains(fecha)) {
                    // Suma 12 para los días de lunes a viernes
                    horasTotales += horasJueves;
                    horasLV+=horasJueves;
                }else if(date.getDayOfWeek()==DayOfWeek.THURSDAY&&festivos.contains(fecha)) {
                    // Suma 12 para los días de lunes a viernes
                    horasTotales += horasFestivo;
                    horasSDF+=horasFestivo;
                }else if(date.getDayOfWeek()==DayOfWeek.WEDNESDAY&&!festivos.contains(fecha)) {
                    // Suma 12 para los días de lunes a viernes
                    horasTotales += horasMiercoles;
                    horasLV+=horasMiercoles;
                }else if(date.getDayOfWeek()==DayOfWeek.WEDNESDAY&&festivos.contains(fecha)) {
                    // Suma 12 para los días de lunes a viernes
                    horasTotales += horasFestivo;
                    horasSDF+=horasFestivo;
                }else if(date.getDayOfWeek()==DayOfWeek.SATURDAY&&!festivos.contains(fecha)) {
                    // Suma 12 para los días de lunes a viernes
                    horasTotales += horasSabado;
                    horasSDF+=horasSabado;
                }else if(date.getDayOfWeek()==DayOfWeek.SATURDAY&&festivos.contains(fecha)) {
                    // Suma 12 para los días de lunes a viernes
                    horasTotales += horasFestivo;
                    horasSDF+=horasFestivo;
                }else if(date.getDayOfWeek()==DayOfWeek.SUNDAY&&!festivos.contains(fecha)) {
                    // Suma 12 para los días de lunes a viernes
                    horasTotales += horasDomingo;
                    horasSDF+=horasDomingo;
                }else if(date.getDayOfWeek()==DayOfWeek.SUNDAY&&festivos.contains(fecha)) {
                    // Suma 12 para los días de lunes a viernes
                    horasTotales += horasFestivo;
                    horasSDF+=horasFestivo;
                }
            }
        }else{
           DateTimeFormatter formato=DateTimeFormatter.ofPattern("yyyy-MM-dd");
           LocalDate fechaIn=LocalDate.parse(fechaInicio,formato);
           LocalDate fechaF=LocalDate.parse(fechaFinal,formato);
            for (LocalDate date = fechaIn; !date.isAfter(fechaF); date = date.plusDays(1)) {
                dateList.add(date.format(formato));
                String fecha=date.format(formato);
                if (date.getDayOfWeek() == DayOfWeek.MONDAY &&!festivos.contains(fecha)) {
                    horasTotales += horasLunes;
                    horasLV+=horasLunes;
                } else if(date.getDayOfWeek()==DayOfWeek.MONDAY&&festivos.contains(fecha)) {
                    // Suma 12 para los días de lunes a viernes
                    horasTotales += horasFestivo;
                    horasSDF+=horasFestivo;
                }else if(date.getDayOfWeek()==DayOfWeek.TUESDAY&&!festivos.contains(fecha)) {
                    // Suma 12 para los días de lunes a viernes
                    horasTotales += horasMartes;
                    horasLV+=horasMartes;
                }else if(date.getDayOfWeek()==DayOfWeek.TUESDAY&&festivos.contains(fecha)) {
                    // Suma 12 para los días de lunes a viernes
                    horasTotales += horasFestivo;
                    horasSDF+=horasFestivo;
                }else if(date.getDayOfWeek()==DayOfWeek.FRIDAY&&!festivos.contains(fecha)) {
                    // Suma 12 para los días de lunes a viernes
                    horasTotales += horasViernes;
                    horasLV+=horasViernes;
                }else if(date.getDayOfWeek()==DayOfWeek.FRIDAY&&festivos.contains(fecha)) {
                    // Suma 12 para los días de lunes a viernes
                    horasTotales += horasFestivo;
                    horasSDF+=horasFestivo;
                }else if(date.getDayOfWeek()==DayOfWeek.THURSDAY&&!festivos.contains(fecha)) {
                    // Suma 12 para los días de lunes a viernes
                    horasTotales += horasJueves;
                    horasLV+=horasJueves;
                }else if(date.getDayOfWeek()==DayOfWeek.THURSDAY&&festivos.contains(fecha)) {
                    // Suma 12 para los días de lunes a viernes
                    horasTotales += horasFestivo;
                    horasSDF+=horasFestivo;
                }else if(date.getDayOfWeek()==DayOfWeek.WEDNESDAY&&!festivos.contains(fecha)) {
                    // Suma 12 para los días de lunes a viernes
                    horasTotales += horasMiercoles;
                    horasLV+=horasMiercoles;
                }else if(date.getDayOfWeek()==DayOfWeek.WEDNESDAY&&festivos.contains(fecha)) {
                    // Suma 12 para los días de lunes a viernes
                    horasTotales += horasFestivo;
                    horasSDF+=horasFestivo;
                }else if(date.getDayOfWeek()==DayOfWeek.SATURDAY&&!festivos.contains(fecha)) {
                    // Suma 12 para los días de lunes a viernes
                    horasTotales += horasSabado;
                    horasSDF+=horasSabado;
                }else if(date.getDayOfWeek()==DayOfWeek.SATURDAY&&festivos.contains(fecha)) {
                    // Suma 12 para los días de lunes a viernes
                    horasTotales += horasFestivo;
                    horasSDF+=horasFestivo;
                }else if(date.getDayOfWeek()==DayOfWeek.SUNDAY&&!festivos.contains(fecha)) {
                    // Suma 12 para los días de lunes a viernes
                    horasTotales += horasDomingo;
                    horasSDF+=horasDomingo;
                }else if(date.getDayOfWeek()==DayOfWeek.SUNDAY&&festivos.contains(fecha)) {
                    // Suma 12 para los días de lunes a viernes
                    horasTotales += horasFestivo;
                    horasSDF+=horasFestivo;
                }
            }
        }

        Double comision=Math.round((this.comision *horasTotales)*10.0)/10.0;
        listadoFestivos.clear();
        return comision;
    }

private ArrayList<String>listaFechaFestivos(ArrayList<String>listadoFestivos){

        LocalDate fechaActual=LocalDate.now();
        Integer año=fechaActual.getYear();
        String parseoAño=String.valueOf(año);
    ObjectMapper mapper = new ObjectMapper();
    HttpClient cliente=HttpClient.newHttpClient();
    HttpRequest request1 = HttpRequest.newBuilder()
            .uri(URI.create("https://date.nager.at/api/v3/publicholidays/"+parseoAño+"/ES"))
            .build();
    try {
        HttpResponse<String> response = cliente.newHttpClient().send(request1, HttpResponse.BodyHandlers.ofString());
        JsonNode jsonNode = mapper.readTree(response.body());
        System.out.println(response.body());
        for (int i=0;i<jsonNode.size();i++){
            String comprueba=jsonNode.get(i).get("date").asText();
            Boolean boleano=jsonNode.get(i).get("global").asBoolean();

            JsonNode countiesNode = jsonNode.get(i).get("counties");
            if(boleano){
                listadoFestivos.add(comprueba);
            }


            if (countiesNode != null && countiesNode.isArray()) {
                for (JsonNode county : countiesNode) {
                    if ("ES-AN".equals(county.asText())&&boleano==false) {
                        listadoFestivos.add(comprueba);
                        break;
                    }
                }
            }


        }

       // System.out.println(jsonNode.get(0).get("date"));
    } catch (IOException e) {
        throw new RuntimeException(e);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
    return listadoFestivos;

}

    @javafx.fxml.FXML
    public void filtro(Event event) {
        String filtro=textBuscador.getText().toLowerCase();
        filtro=filtro.replace("á","a");
        filtro=filtro.replace("é","e");
        filtro=filtro.replace("í","i");
        filtro=filtro.replace("ó","o");
        filtro=filtro.replace("ú","u");
        if(filtro.isEmpty()){
            tabla.setItems(listaInicialTabla);
        }else{
            filtroBuscador.clear();
            for(Servicio servicio:listaInicialTabla){
                String datos=servicio.getNombre().toLowerCase()+" "+servicio.getBeneficio();
                datos=datos.toLowerCase();
                datos=datos.replace("á","a");
                datos=datos.replace("é","e");
                datos=datos.replace("í","i");
                datos=datos.replace("ó","o");
                datos=datos.replace("ú","u");
                if(datos.contains(filtro)){
                    filtroBuscador.add(servicio);
                }
            }
            tabla.setItems(filtroBuscador);
        }
    }

    @javafx.fxml.FXML
    public void borrarServicio(ActionEvent actionEvent) {
        if(servicioElegido!=null){
            Alert alerta=new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("INFORMACIÓN");
            alerta.setHeaderText("Borrado de Servicio");
            alerta.setContentText("¿Seguro que deseas borrar el servicio "+servicioElegido.getNombre()+"?");
            Optional<ButtonType> tipo=alerta.showAndWait();
            if(tipo.get()==ButtonType.OK){
                for (int i=0;i<obs.size();i++){
                    if(obs.get(i).toString().contains(servicioElegido.getNombre())){
                        (new ServicioDAOImplement()).borrar(servicioElegido);
                        obs.remove(obs.get(i));
                        listaInicialTabla.remove(servicioElegido);
                        break;
                    }
                }
            }

        }else{
            Alert alerta=new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText("Selección de Servicio");
            alerta.setContentText("Por favor assegurese de seleccionar primero un servicio en la tabla");
            alerta.showAndWait();
        }

    }
}
