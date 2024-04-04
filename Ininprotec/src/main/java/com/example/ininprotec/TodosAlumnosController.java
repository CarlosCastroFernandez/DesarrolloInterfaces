package com.example.ininprotec;

import Util.Utilidad;
import clase.*;
import implement.PersonalBolsaDAOImplement;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    @javafx.fxml.FXML
    private TableColumn <PersonalBolsa,String>cDNI;
    @javafx.fxml.FXML
    private TableColumn  <PersonalBolsa,String>cCorreo;
    @javafx.fxml.FXML
    private TableColumn  <PersonalBolsa,String>cTelefono;
    @javafx.fxml.FXML
    private TableColumn  <PersonalBolsa,String>cRol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cNombre.setCellValueFactory((alumno)->{
            String nombre=alumno.getValue().getNombre()+" "+alumno.getValue().getApellido1()+" "+alumno.getValue().getApellido2();
            return new SimpleStringProperty(nombre);
        });
        cCurso.setCellValueFactory((alumno)->{
            String curso="";
            if(!alumno.getValue().getCursosAlumnos().isEmpty()){
                for(AlumnoCurso c:alumno.getValue().getCursosAlumnos()){
                    curso+=c.getCursoId().getNombre()+", ";
                }
                curso=curso.substring(0,curso.lastIndexOf(","));
                return new SimpleStringProperty(curso);
            }else{
                curso="Sin Curso";
                return new SimpleStringProperty(curso);
            }

        });
        cDNI.setCellValueFactory((alumno)->{
            String dni=alumno.getValue().getDni();
            return new SimpleStringProperty(dni);
        });
        cCorreo.setCellValueFactory((alumno)->{
            String correo=alumno.getValue().getCorreo();
            return new SimpleStringProperty(correo);
        });
        cTelefono.setCellValueFactory((alumno)->{
            String telefono=alumno.getValue().getTelefono();
            return new SimpleStringProperty(telefono);
        });
        cRol.setCellValueFactory((alumno)->{
            String rol=(alumno.getValue().getEsAlumno()==1?"Alumno":alumno.getValue().getEsAlumno()==2?"Trabajador":"Alumno y Trabajador");
            return new SimpleStringProperty(rol);
        });
        alumnos.addAll((new PersonalBolsaDAOImplement().getAll()));
        tabla.setItems(alumnos);

        tabla.getSelectionModel().selectedItemProperty().addListener((observableValue, personalBolsa, t1) -> {
            alumnoElegido=t1;

        });
    }

    @javafx.fxml.FXML
    public void agregar(ActionEvent actionEvent) {
        AlumnoCurso alumnoCurso=new AlumnoCurso();

        if(alumnoElegido!=null){
            if(!alumnoElegido.getCursosAlumnos().isEmpty()){
                alumnoCurso.setAlumnoId(alumnoElegido);
                alumnoCurso.setCursoId(Utilidad.getCurso());
                alumnoElegido.getCursosAlumnos().add(alumnoCurso);

                for(Modulo m:Utilidad.getCurso().getModulos()){
                    AlumnoModulo alumnoModulo=new AlumnoModulo();
                    alumnoModulo.setAlumnoId(alumnoElegido);
                    alumnoModulo.setModuloId(m);
                    alumnoElegido.getModuloAlumno().add(alumnoModulo);
                }
            }else{
                alumnoElegido.setCursosAlumnos(new ArrayList<>());
                alumnoElegido.setModuloAlumno(new ArrayList<>());
                alumnoCurso.setAlumnoId(alumnoElegido);
                alumnoCurso.setCursoId(Utilidad.getCurso());
                alumnoElegido.getCursosAlumnos().add(alumnoCurso);
                for(Modulo m:Utilidad.getCurso().getModulos()){
                    AlumnoModulo alumnoModulo=new AlumnoModulo();
                    alumnoModulo.setAlumnoId(alumnoElegido);
                    alumnoModulo.setModuloId(m);
                    alumnoElegido.getModuloAlumno().add(alumnoModulo);
                }
            }
            if(alumnoElegido.getEsAlumno()==2){
                alumnoElegido.setEsAlumno(3L);
            }
            (new PersonalBolsaDAOImplement()).agregarAlumnoCurso(alumnoElegido);
            alumnos.remove(alumnoElegido);

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
                String porBusqueda= a.getNombre().toLowerCase()+" "+a.getApellido1().toLowerCase()+" "+a.getApellido2().toLowerCase()+" "+a.getDni().toLowerCase()
                        +" "+a.getTelefono().toLowerCase()+" "+a.getCorreo().toLowerCase();
                if(porBusqueda.contains(filtro.toLowerCase())){
                    filtroALumnos.add(a);
                }
            }
            tabla.setItems(filtroALumnos);
        }
    }
}
