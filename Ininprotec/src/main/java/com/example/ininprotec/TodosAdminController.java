package com.example.ininprotec;

import Util.Utilidad;
import clase.*;
import implement.PersonalIIPDAOImplement;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class TodosAdminController implements Initializable {
    @javafx.fxml.FXML
    private TableView<PersonalIIP> tabla;
    @javafx.fxml.FXML
    private TableColumn<PersonalIIP,String> cNombre;
    @javafx.fxml.FXML
    private TableColumn<PersonalIIP,String>  cDNI;
    @javafx.fxml.FXML
    private TableColumn<PersonalIIP,String>  cCorreo;
    @javafx.fxml.FXML
    private TableColumn <PersonalIIP,String> cTelefono;
    @javafx.fxml.FXML
    private Button botonBorrar;
    @javafx.fxml.FXML
    private TextField textBuscar;
    private ObservableList<PersonalIIP> filtroCurso= FXCollections.observableArrayList();

    private PersonalIIP administradorSeleccionado;
    private ObservableList<PersonalIIP> administradores = FXCollections.observableArrayList();
    private ObservableList<PersonalIIP> filtroAdmin = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Curso sinCurso=new Curso();
        sinCurso.setNombre("Sin Curso");


        tabla.setRowFactory(tv -> {
            TableRow<PersonalIIP> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                    PersonalIIP clickedRow = row.getItem();
                    Utilidad.setAdmin(clickedRow);
                    HelloApplication.cambioVentana("registroAdmin-view.fxml");
                }
            });
            return row;
        });
        cNombre.setCellValueFactory((instructor)->{
            String nombre=instructor.getValue().getNombre()+" "+instructor.getValue().getApellido1()+" "+instructor.getValue().getApellido2();
            return new SimpleStringProperty(nombre);
        });

        cDNI.setCellValueFactory((instructor)->{
            String dni=instructor.getValue().getDni();
            return new SimpleStringProperty(dni);
        });
        cCorreo.setCellValueFactory((instructor)->{
            String correo=instructor.getValue().getCorreo();
            return new SimpleStringProperty(correo);
        });
        cTelefono.setCellValueFactory((instructor)->{
            String telefono=instructor.getValue().getTelefono();
            return new SimpleStringProperty(telefono);
        });
        tabla.getSelectionModel().selectedItemProperty().addListener((observableValue, personalIIP, t1) -> {
            administradorSeleccionado =t1;
        });
        administradores.addAll((new PersonalIIPDAOImplement().getAllAdministradores()));

        tabla.setItems(administradores);


    }

    @javafx.fxml.FXML
    public void borrar(ActionEvent actionEvent) {
        if(administradorSeleccionado !=null){
            try{
            Alert alerta=new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("INFORMACIÓN!");
            alerta.setHeaderText("Borrado de Administrativo/a");
            alerta.setContentText("¿Seguro deseas borrar al Administrativo/a?");
            Optional<ButtonType> tipo=alerta.showAndWait();
            if(tipo.get()==ButtonType.OK){
                (new PersonalIIPDAOImplement()).borrar(administradorSeleccionado);
                Alert alerta2=new Alert(Alert.AlertType.CONFIRMATION);
                alerta2.setTitle("OK!");
                alerta2.setHeaderText("Borrado Con Éxito");
                alerta2.setContentText("El Administrativo/a con nombre "+ administradorSeleccionado.getNombre()+" y\n" +
                        "DNI: "+ administradorSeleccionado.getDni()+" Ha sido BORRADO");
                alerta2.showAndWait();
                tabla.getItems().remove(administradorSeleccionado);
            }
            }catch(Exception e){

            }
        }else{
            Alert alerta=new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText("Instructor No Seleccionado");
            alerta.setContentText("Por favor seleccione un instructor de la tabla y luego pulsa borrar");
            alerta.showAndWait();
        }
    }


    @javafx.fxml.FXML
    public void filtro(Event event) {
        String filtro=textBuscar.getText().toLowerCase();
        filtro=filtro.replace("á","a");
        filtro=filtro.replace("é","ee");
        filtro=filtro.replace("í","i");
        filtro=filtro.replace("ó","o");
        filtro=filtro.replace("ú","u");

        if(filtro.isEmpty()){
            tabla.setItems(administradores);

        } else{
            filtroAdmin.clear();
            for(PersonalIIP a: administradores){
                String porBusqueda= a.getNombre().toLowerCase()+" "+a.getApellido1().toLowerCase()+" "+a.getApellido2().toLowerCase()+" "+a.getDni().toLowerCase()
                        +" "+a.getTelefono().toLowerCase()+" "+a.getCorreo().toLowerCase();
                porBusqueda=porBusqueda.replace("á","a");
                porBusqueda=porBusqueda.replace("é","ee");
                porBusqueda=porBusqueda.replace("í","i");
                porBusqueda=porBusqueda.replace("ó","o");
                porBusqueda=porBusqueda.replace("ú","u");
                if(porBusqueda.contains(filtro.toLowerCase())){
                    filtroAdmin.add(a);
                }
            }
            tabla.setItems(filtroAdmin);
        }
    }


    }


