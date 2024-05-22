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
    private Integer horasLV;
    private Integer horasSDF;
    private Integer horasTotales=0;
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
private ArrayList<String>listadoFestivos=new ArrayList<>();
    @javafx.fxml.FXML
    private Button añadir;
    @javafx.fxml.FXML
    private ImageView imagenFlecha;

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
            String beneficio=String.valueOf(servicio.getValue().getHorasSDF())+" h";
            return new SimpleStringProperty(beneficio);
        });
        cLaboral.setCellValueFactory(servicio->{
            String beneficio=String.valueOf(servicio.getValue().getHorasLV())+" h";
            return new SimpleStringProperty(beneficio);
        });
        cHorasTotales.setCellValueFactory(servicio->{
            String beneficio=String.valueOf(servicio.getValue().getHoraTotal())+" h";
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
                        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
                            dateList.add(date.format(formatter));
                            String fecha=date.format(formatter);
                            if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY||festivos.contains(fecha)) {
                                // Suma 24 para sábados y domingos
                                horasTotales += servicio.getHorasSDF();
                            } else {
                                // Suma 12 para los días de lunes a viernes
                                horasTotales += servicio.getHorasLV();
                            }
                        }
                        Double beneficio=Math.round((servicio.getComision() *horasTotales)*10.0)/10.0;
                        servicio.setBeneficio(beneficio);
                        listaInicialTabla.add(servicio);
                        obs.add(new XYChart.Data<>(servicio.getNombre(), beneficio));
                        (new ServicioDAOImplement()).actualizacionBeneficio(servicio);
                        horasTotales=0;
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
                        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
                            dateList.add(date.format(formatter));
                            String fecha=date.format(formatter);
                            if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY||festivos.contains(fecha)) {
                                // Suma 24 para sábados y domingos
                                horasTotales += servicio.getHorasSDF();
                            } else {
                                // Suma 12 para los días de lunes a viernes
                                horasTotales += servicio.getHorasLV();
                            }
                        }
                        Double beneficio=Math.round((servicio.getComision() *horasTotales)*10.0)/10.0;
                        servicio.setBeneficio(beneficio);
                        listaInicialTabla.add(servicio);
                        obs.add(new XYChart.Data<>(servicio.getNombre(), beneficio));
                        (new ServicioDAOImplement()).actualizacionBeneficio(servicio);
                        horasTotales=0;
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
        TextField textField2 = new TextField();
        textField2.setPromptText("Horas L-V");
        TextField textField3 = new TextField();
        textField3.setPromptText("Horas S-D-Festv");
        TextField textField4 = new TextField(); // Nuevo campo de texto
        textField4.setPromptText("Costo del Servicio");
        ComboBox<String>comboMunicipio=new ComboBox<>();
        comboMunicipio.setPromptText("Municipio...");
        comboMunicipio.getItems().add("Málaga");
        comboMunicipio.getItems().add("Marbella");
        comboMunicipio.getItems().add("Estepona");


        grid.add(new Label("Nombre  Del Servicio:"), 0, 0);
        grid.add(textField1, 1, 0);
        grid.add(new Label("Horas L-V:"), 0, 1);
        grid.add(textField2, 1, 1);
        grid.add(new Label("Horas S-D-Festv"),0,2);
        grid.add(textField3, 1, 2);
        grid.add(new Label("Comisión del Servicio:"), 0, 3);
        grid.add(textField4, 1, 3);
        grid.add(new Label("Municipio:"), 0, 4);
        grid.add(comboMunicipio, 1, 4);

        // Incorporar el grid al diálogo
        dialog.getDialogPane().setContent(grid);

        // Request focus on the first field by default.
        Platform.runLater(textField1::requestFocus);

        // Convertir la entrada en un par cuando el botón OK es presionado.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK&&!textField1.getText().isEmpty()&&!textField2.getText().isEmpty()&&
            !textField3.getText().isEmpty()&&!textField4.getText().isEmpty()&&comboMunicipio.getValue()!=null) {
                String municipio=comboMunicipio.getValue();
                municipio=municipio.toLowerCase();
                municipio=municipio.replace("á","a");
                municipio=municipio.replace("é","e");
                municipio=municipio.replace("í","i");
                municipio=municipio.replace("ó","o");
                municipio=municipio.replace("ú","u");
                HashMap<String, String> data = new HashMap<>();
                data.put("nombreServicio", textField1.getText());
                data.put("horasLV", textField2.getText());
                data.put("horasSDF", textField3.getText());
                data.put("comision", textField4.getText());
                data.put("municipio",municipio);
                return data;
            }
            return null;
        });

        // Mostrar el diálogo y capturar el resultado
        Optional<HashMap<String, String>> result = dialog.showAndWait();

        result.ifPresent(data -> {
            LocalDate año=LocalDate.now();
            String añoActual=String.valueOf(año.getYear());
            nombreServicio= data.get("nombreServicio");
            horasLV=Integer.valueOf( data.get("horasLV"));
            horasSDF=Integer.valueOf(data.get("horasSDF"));
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
            Double beneficios=calculoComision();
            Servicio servicioNuevo=new Servicio(nombreServicio,horasLV,horasSDF,comision,beneficios,horasTotales,municipio);
            horasTotales=0;
            servicioNuevo=(new ServicioDAOImplement().subida(servicioNuevo));
            listaInicialTabla.add(servicioNuevo);
            obs.add(new XYChart.Data<>(nombreServicio, beneficios));
        });
    }
    private Double calculoComision(){
        Integer contador=0;
        LocalDate start = LocalDate.now().withDayOfMonth(1);
        LocalDate end = start.plusMonths(1).minusDays(1);
        ArrayList<String>festivos=listaFechaFestivos(listadoFestivos);
        System.out.println(festivos);
        List<String> dateList = new ArrayList<>();

        // Formateador de fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Itera desde el primer día hasta el último día del mes
        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
            // Agregar la fecha formateada a la lista
            dateList.add(date.format(formatter));
            String fecha=date.format(formatter);
            if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY||festivos.contains(fecha)) {
                // Suma 24 para sábados y domingos
                contador++;
                horasTotales += horasSDF;
            } else {
                // Suma 12 para los días de lunes a viernes
                horasTotales += horasLV;
            }
        }
        System.out.println(contador +" SABADOSDOMINGOSFESTIVOS");
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
