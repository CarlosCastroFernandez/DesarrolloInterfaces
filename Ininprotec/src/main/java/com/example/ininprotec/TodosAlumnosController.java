package com.example.ininprotec;

import Util.Utilidad;
import clase.*;
import implement.CursoDAOImplement;
import implement.PersonalBolsaDAOImplement;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.util.StringConverter;

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
    @javafx.fxml.FXML
    private Button botonBorrar;
    @javafx.fxml.FXML
    private ComboBox <Curso> comboCurso;
    @javafx.fxml.FXML
    private ComboBox comboCalificacion;
    @javafx.fxml.FXML
    private Button botonQuitar;
    private Curso cursoSeleccionado;
    private ObservableList<PersonalBolsa>filtroCurso= FXCollections.observableArrayList();
    private ObservableList<PersonalBolsa>filtroCalificacion=FXCollections.observableArrayList();
    private ObservableList<PersonalBolsa>filtroCalificacionTodos=FXCollections.observableArrayList();
private ChangeListener<String> comboCalificacionListener;
    @javafx.fxml.FXML
    private ComboBox comboFiltroTodosCalificacion;
    private ChangeListener<String> listenerCalificacionTodos;
    private ChangeListener<Curso> listenerCurso;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboCurso.setConverter(new StringConverter <Curso>() {
            @Override
            public String toString(Curso o) {
                return (o!=null?o.getNombre():"");
            }

            @Override
            public Curso fromString(String s) {
                return null;
            }
        });
        comboCurso.getItems().addAll((new CursoDAOImplement().getAll()));
        comboCalificacion.getItems().add("Calificado");
        comboCalificacion.getItems().add("No Calificado");
        comboCalificacion.setDisable(true);
        comboFiltroTodosCalificacion.getItems().add("Calificado");
        comboFiltroTodosCalificacion.getItems().add("No Calificado");
        listenerCurso=((observableValue, s, t1) -> {
            filtroCurso.clear();
            filtroCalificacionTodos.clear();
            comboFiltroTodosCalificacion.getSelectionModel().selectedItemProperty().removeListener(listenerCalificacionTodos);
            comboFiltroTodosCalificacion.getSelectionModel().select(null);
            comboFiltroTodosCalificacion.getSelectionModel().selectedItemProperty().addListener(listenerCalificacionTodos);
            cursoSeleccionado=t1;
            if(cursoSeleccionado!=null){
                comboCalificacion.setDisable(false);
                filtroCurso.addAll((new PersonalBolsaDAOImplement().getAllByCursoId(cursoSeleccionado)));
                tabla.setItems(filtroCurso);

            }
        });
        comboCurso.getSelectionModel().selectedItemProperty().addListener(listenerCurso);


       comboCalificacionListener=((observableValue, o, t1) -> {
           comboFiltroTodosCalificacion.getSelectionModel().selectedItemProperty().removeListener(listenerCalificacionTodos);
           filtroCalificacionTodos.clear();
           comboFiltroTodosCalificacion.getSelectionModel().select(null);
           comboFiltroTodosCalificacion.getSelectionModel().selectedItemProperty().addListener(listenerCalificacionTodos);
            String seleccion=t1.toString();
            if(seleccion.equals("No Calificado")){
                filtroCalificacion.clear();
                filtroCalificacion.addAll((new PersonalBolsaDAOImplement().getAllByCursoIdNuevos(cursoSeleccionado)));
                tabla.setItems(filtroCalificacion);
            } else if (seleccion.equals("Calificado")) {
                filtroCalificacion.clear();
                filtroCalificacion.addAll((new PersonalBolsaDAOImplement().getAllByCursoIdTerminados(cursoSeleccionado)));
                tabla.setItems(filtroCalificacion);
            }
        });
       comboCalificacion.getSelectionModel().selectedItemProperty().addListener(comboCalificacionListener);

       listenerCalificacionTodos=((observableValue, o, t1)->{
           filtroCurso.clear();
           filtroCalificacion.clear();
           comboCurso.getSelectionModel().selectedItemProperty().removeListener(listenerCurso);
           comboCalificacion.getSelectionModel().selectedItemProperty().removeListener(comboCalificacionListener);
           comboCurso.getSelectionModel().select(null);
           comboCalificacion.getSelectionModel().select(null);
           comboCalificacion.setDisable(true);
           comboCalificacion.getSelectionModel().selectedItemProperty().addListener(comboCalificacionListener);
           comboCurso.getSelectionModel().selectedItemProperty().addListener(listenerCurso);
           String seleccion="";
           if(t1!=null){
               seleccion=t1.toString();
           }

           if(seleccion.equals("No Calificado")){
               filtroCalificacionTodos.clear();
               filtroCalificacionTodos.addAll((new PersonalBolsaDAOImplement().getAllNuevos()));
               tabla.setItems(filtroCalificacionTodos);
           } else if (seleccion.equals("Calificado")) {
               filtroCalificacionTodos.clear();
               filtroCalificacionTodos.addAll((new PersonalBolsaDAOImplement().getAllTerminados()));
               tabla.setItems(filtroCalificacionTodos);
           }

       });
       comboFiltroTodosCalificacion.getSelectionModel().selectedItemProperty().addListener(listenerCalificacionTodos);



       if(Utilidad.getCurso()!=null){
            botonAgregar.setVisible(true);
            botonBorrar.setVisible(false);
        }else{
            botonAgregar.setVisible(false);
            botonBorrar.setVisible(true);
            tabla.setRowFactory(tv -> {
                TableRow<PersonalBolsa> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                        PersonalBolsa clickedRow = row.getItem();
                        Utilidad.setAlumno(clickedRow);
                        HelloApplication.cambioVentana("registroAlumno-view.fxml");
                    }
                });
                return row;
            });
        }

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

        if(alumnoElegido!=null&&Utilidad.getCurso()!=null){
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

        }else {
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
        if(filtro.isEmpty()&&comboCalificacion.getValue()==null&&comboCurso.getValue()==null){
            tabla.setItems(alumnos);
            filtroALumnos.clear();
        }else if(filtro.isEmpty()&&comboCurso.getValue()!=null&&comboCalificacion.getValue()==null){
            tabla.setItems(filtroCurso);
            filtroALumnos.clear();
        }else if(filtro.isEmpty()&&comboCurso.getValue()!=null&&comboCalificacion.getValue()!=null){
            tabla.setItems(filtroCalificacion);
            filtroALumnos.clear();
        } else if (filtro.isEmpty()&&comboFiltroTodosCalificacion!=null) {
            tabla.setItems(filtroCalificacionTodos);
            filtroALumnos.clear();
        } else{
            comboCalificacion.getSelectionModel().selectedItemProperty().removeListener(comboCalificacionListener);
            comboCalificacion.getSelectionModel().select(null);
            comboFiltroTodosCalificacion.getSelectionModel().select(null);
            comboCurso.getSelectionModel().select(null);
            comboCalificacion.setDisable(true);
            comboCalificacion.getSelectionModel().selectedItemProperty().addListener(comboCalificacionListener);
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

    @javafx.fxml.FXML
    public void borrar(ActionEvent actionEvent) {
        if(Utilidad.getCurso()==null&&alumnoElegido!=null){
            (new PersonalBolsaDAOImplement()).borradoAlumno(alumnoElegido);
            alumnos.remove(alumnoElegido);
            if(!filtroALumnos.isEmpty()){
                filtroALumnos.remove(alumnoElegido);
            }

        }else{
            Alert alerta=new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText("Alumno No Seleccionado");
            alerta.setContentText("Selecciona un alumno en la tabla para agregarlo");
            alerta.showAndWait();
        }
    }

    @javafx.fxml.FXML
    public void quitar(ActionEvent actionEvent) {
        comboCalificacion.getSelectionModel().selectedItemProperty().removeListener(comboCalificacionListener);
        comboCalificacion.getSelectionModel().select(null);
        comboCurso.getSelectionModel().select(null);
        comboFiltroTodosCalificacion.getSelectionModel().select(null);
        comboCalificacion.setDisable(true);
        alumnos.clear();
        alumnos.addAll((new PersonalBolsaDAOImplement().getAll()));
        if(textBuscar.getText().isEmpty()){
            tabla.setItems(alumnos);
        }else {
            tabla.setItems(filtroALumnos);
        }
        comboCalificacion.getSelectionModel().selectedItemProperty().addListener(comboCalificacionListener);

    }
}
