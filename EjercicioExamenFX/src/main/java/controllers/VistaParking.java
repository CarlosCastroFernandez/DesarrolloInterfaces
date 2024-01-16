package controllers;

import clases.Cliente;
import clases.Parking;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.StringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

public class VistaParking implements Initializable {
    @javafx.fxml.FXML
    private TextField textMatricula;
    @javafx.fxml.FXML
    private ComboBox comboBoxModelo;
    @javafx.fxml.FXML
    private ChoiceBox <Cliente>choiceBoxCliente;
    @javafx.fxml.FXML
    private RadioButton radioStandard;
    @javafx.fxml.FXML
    private ToggleGroup solo;
    @javafx.fxml.FXML
    private RadioButton radioOferta;
    @javafx.fxml.FXML
    private RadioButton radioLarga;
    @javafx.fxml.FXML
    private DatePicker dateEntrada;
    @javafx.fxml.FXML
    private DatePicker dateSalida;
    @javafx.fxml.FXML
    private Button botonAñadir;
    @javafx.fxml.FXML
    private Button botonSalir;
    @javafx.fxml.FXML
    private TableView <Parking>tabla;
    @javafx.fxml.FXML
    private TableColumn <Parking,String> cMatricula;
    @javafx.fxml.FXML
    private TableColumn <Parking,String> cModelo;
    @javafx.fxml.FXML
    private TableColumn <Parking,String> cFechaEntrada;
    @javafx.fxml.FXML
    private TableColumn <Parking,String> cFechaSalida;
    @javafx.fxml.FXML
    private TableColumn <Parking,String> cCliente;
    @javafx.fxml.FXML
    private TableColumn <Parking,String> cTarifa;
    @javafx.fxml.FXML
    private TableColumn <Parking,String> cCoste;
    @javafx.fxml.FXML
    private ImageView imageTitulo;
    @javafx.fxml.FXML
    private Label labelCoste;
    private String tarifa;
    @javafx.fxml.FXML
    private Label labelAlumno;
    private Integer coste;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cMatricula.setCellValueFactory((fila)->{
            String nombre=fila.getValue().getMatricula();
            return new SimpleStringProperty(nombre);
        });
        cCliente.setCellValueFactory((fila)->{
            String nombre=fila.getValue().getCliente().getNombre();
            return new SimpleStringProperty(nombre);
        });
        cCoste.setCellValueFactory((fila)->{
            String nombre=String.valueOf(fila.getValue().getCoste());
            return new SimpleStringProperty(nombre);
        });
        cFechaEntrada.setCellValueFactory((fila)->{
            String nombre=String.valueOf(fila.getValue().getEntrada());
            return new SimpleStringProperty(nombre);
        });
        cFechaSalida.setCellValueFactory((fila)->{
            String nombre=String.valueOf(fila.getValue().getSalida());
            return new SimpleStringProperty(nombre);
        });
        cModelo.setCellValueFactory((fila)->{
            String nombre=fila.getValue().getModeloCoche();
            return new SimpleStringProperty(nombre);
        });
        cTarifa.setCellValueFactory((fila)->{
            String nombre=fila.getValue().getTarifa();
            return new SimpleStringProperty(nombre);
        });
        comboBoxModelo.getItems().add("Mercedes");
        comboBoxModelo.getItems().add("BMW");
        comboBoxModelo.getItems().add("Citroen");
        comboBoxModelo.getItems().add("Renault");
        comboBoxModelo.getSelectionModel().selectFirst();
        Cliente cliente1=new Cliente(1,"Carlos","bubachico@gmail.com");
        Cliente cliente2=new Cliente(2,"Pepe","pepe@gmail.com");
        Cliente cliente3=new Cliente(3,"Alejandro","alejandro@gmail.com");
        Cliente cliente4=new Cliente(4,"Carmen","carmen@gmail.com");
        choiceBoxCliente.setConverter(new StringConverter<Cliente>() {
            @Override
            public String toString(Cliente cliente) {
                if(cliente!=null){
                    return cliente.getNombre();
                }else {
                    return null;
                }

            }

            @Override
            public Cliente fromString(String s) {
                return null;
            }
        });

        choiceBoxCliente.getItems().add(cliente1);
        choiceBoxCliente.getItems().add(cliente2);
        choiceBoxCliente.getItems().add(cliente3);
        choiceBoxCliente.getItems().add(cliente4);
        choiceBoxCliente.getSelectionModel().selectFirst();
        coste = 0;
        radioStandard.setOnAction(actionEvent -> {
            if(dateEntrada.getValue()!=null&&dateSalida.getValue()!=null){
                LocalDate entrada=dateEntrada.getValue();
                LocalDate salida=dateSalida.getValue();
                Period periodo=Period.between(entrada,salida);
                Long dias= (long) periodo.getDays();
                coste= (int) (dias*8);
                labelCoste.setText(""+coste);
                tarifa=radioStandard.getText();
            }else{
                labelCoste.setText("xxxx");
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informacion");
                alert.setHeaderText("Por favor seleccione una fecha de entrada\n y de salida");
                alert.showAndWait();
            }


        });
        radioLarga.setOnAction(actionEvent -> {
            if(dateEntrada.getValue()!=null&&dateSalida.getValue()!=null){
                LocalDate entrada=dateEntrada.getValue();
                LocalDate salida=dateSalida.getValue();
                Period periodo=Period.between(entrada,salida);
                Long dias= (long) periodo.getDays();
                coste= (int) (dias*2);

                labelCoste.setText(""+coste);
                tarifa=radioLarga.getText();
            }else{
                labelCoste.setText("xxxx");
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informacion");
                alert.setHeaderText("Por favor seleccione una fecha de entrada\n y de salida");
                alert.showAndWait();
            }

        });
        radioOferta.setOnAction(actionEvent -> {
            if(dateEntrada.getValue()!=null&&dateSalida.getValue()!=null){
                LocalDate entrada=dateEntrada.getValue();
                LocalDate salida=dateSalida.getValue();
                Period periodo=Period.between(entrada,salida);
                Long dias= (long) periodo.getDays();
                coste= (int) (dias*6);

                labelCoste.setText(""+coste);
                tarifa=radioOferta.getText();
            }else{
                labelCoste.setText("xxxx");
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informacion");
                alert.setHeaderText("Por favor seleccione una fecha de entrada\n y de salida");
                alert.showAndWait();
            }

        });
    labelAlumno.setOnMouseClicked(mouseEvent -> {
        Alert alerta=new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Informacion");
        alerta.setHeaderText("Carlos Castro Fernandez 2ºDAM");
        alerta.showAndWait();
    });

    }
    @javafx.fxml.FXML
    public void añadir(ActionEvent actionEvent) {
        if(textMatricula.getText().isEmpty()||comboBoxModelo.getValue()==null||choiceBoxCliente.getValue()==null
        ||tarifa==null|| dateEntrada.getValue()==null||dateSalida.getValue()==null){
            Alert alerta=new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("Comprueba de que no quede ningun campo vacio");
            alerta.showAndWait();
        }else {
            Parking parking =new Parking();
            parking.setCliente(choiceBoxCliente.getValue());
            parking.setEntrada(dateEntrada.getValue());
            parking.setSalida(dateSalida.getValue());
            parking.setMatricula(textMatricula.getText());
            String []separacion=labelCoste.getText().split(" ");
            parking.setCoste(Integer.valueOf(separacion[0]));
            parking.setTarifa(tarifa);
            parking.setModeloCoche((String)comboBoxModelo.getValue());
            tabla.getItems().add(parking);
            textMatricula.clear();
            comboBoxModelo.getSelectionModel().select(null);
            choiceBoxCliente.getSelectionModel().select(null);
            labelCoste.setText("xxxx");
            tarifa=null;
            dateEntrada.setValue(null);
            dateSalida.setValue(null);
            radioOferta.setSelected(false);
            radioLarga.setSelected(false);
            radioStandard.setSelected(false);

        }
    }

    @javafx.fxml.FXML
    public void salir(ActionEvent actionEvent) {
        System.exit(0);
    }

}
