package com.example.ininprotec;

import Util.Utilidad;
import clase.Curso;
import clase.Modulo;
import clase.PersonalBolsa;
import implement.PersonalBolsaDAOImplement;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TodosAlumnosController implements Initializable {
    @javafx.fxml.FXML
    private TableColumn <PersonalBolsa,String> cNombre;
    @javafx.fxml.FXML
    private TableColumn<PersonalBolsa,String> cCurso;
    @javafx.fxml.FXML
    private Button botonAgregar;
    @javafx.fxml.FXML
    private TextField textBuscar;
    @javafx.fxml.FXML
    private TableView <PersonalBolsa>tabla;
    private ObservableList<PersonalBolsa>alumnos= FXCollections.observableArrayList();
    private ObservableList<PersonalBolsa>filtroALumnos=FXCollections.observableArrayList();
    private PersonalBolsa alumnoElegido;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cNombre.setCellValueFactory((alumno)->{
            String nombre=alumno.getValue().getNombre()+" "+alumno.getValue().getApellido1()+" "+alumno.getValue().getApellido2();
            return new SimpleStringProperty(nombre);
        });
        cCurso.setCellValueFactory((alumno)->{
            String curso="";
            if(!alumno.getValue().getCursosAlumnos().isEmpty()){
                for(Curso c:alumno.getValue().getCursosAlumnos()){
                    curso+=c.getNombre()+", ";
                }
                curso=curso.substring(0,curso.lastIndexOf(","));
                return new SimpleStringProperty(curso);
            }else{
                curso="Sin Curso";
                return new SimpleStringProperty(curso);
            }

        });
        alumnos.addAll((new PersonalBolsaDAOImplement().getAll()));
        tabla.getItems().addAll(alumnos);

        tabla.getSelectionModel().selectedItemProperty().addListener((observableValue, personalBolsa, t1) -> {
            alumnoElegido=t1;

        });
    }

    @javafx.fxml.FXML
    public void agregar(ActionEvent actionEvent) {
        if(alumnoElegido!=null){
            if(!alumnoElegido.getCursosAlumnos().isEmpty()){
                alumnoElegido.getCursosAlumnos().add(Utilidad.getCurso());
                for(Modulo m:Utilidad.getCurso().getModulos()){
                    alumnoElegido.getModulos().add(m);
                }
            }else{
                alumnoElegido.setCursosAlumnos(new ArrayList<>());
                alumnoElegido.setModulos(new ArrayList<>());
                alumnoElegido.getCursosAlumnos().add(Utilidad.getCurso());
                for(Modulo m:Utilidad.getCurso().getModulos()){
                    alumnoElegido.getModulos().add(m);
                }
            }
            if(alumnoElegido.getEsAlumno()==2){
                alumnoElegido.setEsAlumno(3L);
            }
            (new PersonalBolsaDAOImplement()).agregarAlumnoCurso(alumnoElegido);
            Stage ventana= (Stage)botonAgregar.getScene().getWindow();
            ventana.close();

        }else{
            Alert alerta=new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText("Alumno No Seleccionado");
            alerta.setContentText("Selecciona un alumno en la tabla para agregarlo");
            alerta.showAndWait();
        }
    }

    @javafx.fxml.FXML
    public void filtro(Event event) {
        String filtro=textBuscar.getText();
        if(filtro.isEmpty()){
            tabla.setItems(alumnos);
        }else{
            filtroALumnos.clear();

            for(PersonalBolsa a:alumnos){
                String porBusqueda= a.getNombre().toLowerCase()+" "+a.getApellido1().toLowerCase()+" "+a.getApellido2().toLowerCase();
                if(porBusqueda.contains(filtro.toLowerCase())){
                    filtroALumnos.add(a);
                }
            }
            tabla.setItems(filtroALumnos);
        }
    }
}
