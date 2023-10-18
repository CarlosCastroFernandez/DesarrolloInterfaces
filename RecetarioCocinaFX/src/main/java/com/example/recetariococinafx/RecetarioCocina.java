package com.example.recetariococinafx;

import clases.Receta;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class RecetarioCocina implements Initializable {
    @javafx.fxml.FXML
    private ImageView imagen;
    @javafx.fxml.FXML
    private Label labelTitulo;
    @javafx.fxml.FXML
    private Label labelReceta;
    @javafx.fxml.FXML
    private Label labelNombreReceta;
    @javafx.fxml.FXML
    private TextField txtNombre;
    @javafx.fxml.FXML
    private Label labelDuracion;
    @javafx.fxml.FXML
    private Label labelNumeroDuracion;
    @javafx.fxml.FXML
    private Slider barraSlider;
    @javafx.fxml.FXML
    private ComboBox comboBox;
    @javafx.fxml.FXML
    private ListView<String> lista;
    @javafx.fxml.FXML
    private Button botonAñadir;
    @javafx.fxml.FXML
    private TableView<Receta> tabla;
    @javafx.fxml.FXML
    private TableColumn<Receta, String> cNombre;
    @javafx.fxml.FXML
    private TableColumn<Receta, String> cDuracion;
    @javafx.fxml.FXML
    private TableColumn<Receta, String> cDificultad;
    @javafx.fxml.FXML
    private TableColumn<Receta, String> cTipo;
    @javafx.fxml.FXML
    private Label labelInfo;

    @javafx.fxml.FXML
    public void initialize() {

    }

    @javafx.fxml.FXML
    public void añadir(ActionEvent actionEvent) {
        Receta receta=new Receta();
        String nombre=txtNombre.getText();
        Integer min= (int) barraSlider.getValue();
        String dificultad=(String)comboBox.getSelectionModel().getSelectedItem();
        String listas=lista.getSelectionModel().getSelectedItem();
        receta.setNombre(nombre);
        receta.setDificultad(dificultad);
        receta.setTipo(listas);
        receta.setDuracion(min);
        tabla.getItems().add(receta);
        labelInfo.setText(receta.toString());


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       /* comboBox.getItems().add("Facil");
        comboBox.getItems().add("Medio");
        comboBox.getItems().add("Dificil");
        comboBox.getItems().addAll("Basica","Hard","Pro");
        comboBox.getSelectionModel().selectFirst();*/
        ObservableList<String> datos = FXCollections.observableArrayList();
        datos.addAll("Fácil", "Medio", "Difícil","Moderada");
        comboBox.setItems(datos);
        barraSlider.setValue(60);
        barraSlider.valueProperty().addListener((observableValue, number, t1) -> labelNumeroDuracion.setText(t1.intValue() + " min"));
        txtNombre.textProperty().addListener((ob,vold,vnew)->labelInfo.setText("Antiguo: "+vold+" nuevo: "+vnew));
        tabla.getSelectionModel().selectedItemProperty().addListener(
                (ob,ant,nuevo)->{
                    labelInfo.setText(nuevo.toString());
                    txtNombre.setText(nuevo.getNombre());
                    barraSlider.setValue(nuevo.getDuracion());
                    lista.getSelectionModel().select(nuevo.getTipo());
                    comboBox.getSelectionModel().select(nuevo.getDificultad());
                }
        );

       /* lista.getItems().addAll("Desayuno", "Segundo Desayuno", "Almuerzo", "Sobre Almuerzo", "Merienda",
                "Cena", "Sobre Cena", "Post Cena");*/
        ObservableList<String> datosLista = FXCollections.observableArrayList();
        datosLista.addAll("Desayuno", "Segundo Desayuno", "Almuerzo", "Sobre Almuerzo", "Merienda",
                "Cena", "Sobre Cena", "Post Cena");
        lista.setItems(datosLista);
        lista.getSelectionModel().select(0);

        cNombre.setCellValueFactory((fila) -> new SimpleStringProperty(fila.getValue().getNombre()));
        cDificultad.setCellValueFactory((fila ->{
            String nombre =fila.getValue().getDificultad();
            return new SimpleStringProperty(nombre);
        }));
        cDuracion.setCellValueFactory((fila->{
            String dificultad=String.valueOf(fila.getValue().getDuracion()) ;
            return new SimpleStringProperty(dificultad);
        }));
        cTipo.setCellValueFactory((fila->{
            String tipo=fila.getValue().getTipo();
            return new SimpleStringProperty(tipo);
        }));
        tabla.getItems().add(new Receta("Tacos de carne asada", "Almuerzo", 45, "Fácil"));
        tabla.getItems().add(new Receta("Huevos revueltos con tocino", "Desayuno", 15, "Moderada"));
        tabla.getItems().add(new Receta("Sándwich de jamón y queso", "Merienda", 10, "Fácil"));
        tabla.getItems().add(new Receta("Pollo a la parrilla con verduras", "Almuerzo", 60, "Moderada"));
        tabla.getItems().add(new Receta("Avena con frutas", "Desayuno", 20, "Fácil"));
        tabla.getItems().add(new Receta("Ensalada de atún", "Almuerzo", 30, "Moderada"));
        tabla.getItems().add(new Receta("Pizza casera", "Cena", 35, "Moderada"));
        tabla.getItems().add(new Receta("Batido de frutas", "Merienda", 5, "Fácil"));
        tabla.getItems().add(new Receta("Sopa de pollo casera", "Cena", 40, "Difícil"));
        tabla.getItems().add(new Receta("Pancakes con sirope de arce", "Desayuno", 25, "Moderada"));

    }

    @javafx.fxml.FXML
    public void ActualizarDuracion(Event event) {

    }

    @javafx.fxml.FXML
    public void click(Event event) {
        System.out.println(tabla.getSelectionModel().getSelectedItem());
    }
}