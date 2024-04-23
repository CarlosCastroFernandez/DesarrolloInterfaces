package com.example.ininprotec;

import clase.Servicio;
import implement.ServicioDAOImplement;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class GraficaController implements Initializable {
    @javafx.fxml.FXML
    private BarChart <String, Number>grafica;
    @javafx.fxml.FXML
    private CategoryAxis xNombre;
    @javafx.fxml.FXML
    private NumberAxis yNumeros;
    @javafx.fxml.FXML
    private FlowPane textBuscador;
    @javafx.fxml.FXML
    private TableView <Servicio>tabla;
    @javafx.fxml.FXML
    private TableColumn <Servicio,String>cNombre;
    @javafx.fxml.FXML
    private TableColumn <Servicio,String>cBeneficio;
    private ObservableList obs;
    private String nombreServicio;
    private Double beneficiosServicio;
    @javafx.fxml.FXML
    private Label añadir;
private  XYChart.Series<String,Number>series;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cNombre.setCellValueFactory(servicio->{
            String nombre=servicio.getValue().getNombre();
            return new SimpleStringProperty(nombre);
        });
        cBeneficio.setCellValueFactory(servicio->{
            String beneficio=String.valueOf(servicio.getValue().getBeneficio());
            return new SimpleStringProperty(beneficio);
        });

        obs= FXCollections.observableArrayList();
        grafica.setTitle("Grafica de los Servicios");
        List<Servicio>servicios=(new ServicioDAOImplement()).getAll();
        tabla.getItems().addAll(servicios);
        for(int i=0;i<servicios.size();i++){
            obs.add(new XYChart.Data<>(servicios.get(i).getNombre(), servicios.get(i).getBeneficio()));
        }
        series=new XYChart.Series<>(obs);
        grafica.getData().addAll(series);


        añadir.setOnMouseClicked(mouseEvent -> {
            showDialog();

        });


    }

    @javafx.fxml.FXML
    public void filtro(Event event) {
    }
    private void showDialog() {
        // Crear el diálogo
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Introducir Datos");
        dialog.setHeaderText("Introduce dos tipos de datos:");

        // Configurar tipos de botones
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Crear etiquetas y campos de texto
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField textField1 = new TextField();
        textField1.setPromptText("Nombre del Servicio");
        TextField textField2 = new TextField();
        textField2.setPromptText("Beneficios");

        grid.add(new Label("Primer Dato:"), 0, 0);
        grid.add(textField1, 1, 0);
        grid.add(new Label("Segundo Dato:"), 0, 1);
        grid.add(textField2, 1, 1);

        // Incorporar el grid al diálogo
        dialog.getDialogPane().setContent(grid);

        // Request focus on the first field by default.
        Platform.runLater(textField1::requestFocus);

        // Convertir la entrada en un par cuando el botón OK es presionado.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return new Pair<>(textField1.getText(), textField2.getText());
            }
            return null;
        });

        // Mostrar el diálogo y capturar el resultado
        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(data -> {
            nombreServicio= data.getKey();
            beneficiosServicio=Double.valueOf(data.getValue());
            Servicio servicioNuevo=new Servicio(nombreServicio,beneficiosServicio);
            servicioNuevo=(new ServicioDAOImplement().subida(servicioNuevo));
            tabla.getItems().add(servicioNuevo);
            obs.add(new XYChart.Data<>(nombreServicio, beneficiosServicio));
        });
    }


}
