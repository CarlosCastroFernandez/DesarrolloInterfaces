package com.example.ininprotec;

import Util.HibernateUtil;
import Util.Utilidad;
import clase.*;
import implement.AlumnoCursoDAOImplement;
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
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    private ObservableList<PersonalBolsa>filtrado=FXCollections.observableArrayList();
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
private Boolean boleano=false;

private ChangeListener<String> comboCalificacionListener;
    private ChangeListener<String> listenerFormacion;
    private ChangeListener<Curso> listenerCurso;
    private ChangeListener<String> listenerIdioma;
    private ChangeListener<String> listenerSigno;
    @javafx.fxml.FXML
    private Spinner <Double>spAltura;
    @javafx.fxml.FXML
    private ComboBox comboIdioma;
    @javafx.fxml.FXML
    private DatePicker dateDesde;
    @javafx.fxml.FXML
    private DatePicker dateHasta;
    @javafx.fxml.FXML
    private Button botonBuscar;
    @javafx.fxml.FXML
    private ComboBox comboFormacion;
    private HashMap<String, String>copiado=new HashMap<>();
    private HashMap<String,String>sentencia=new HashMap<>();
    private HashMap<String,Object>parametro=new HashMap<>();
    private HashMap<String,Object>signo=new HashMap<>();

    @javafx.fxml.FXML
    private TableColumn <PersonalBolsa,String>cRegistro;
    @javafx.fxml.FXML
    private Button botonQuitar;
    @javafx.fxml.FXML
    private TableColumn <PersonalBolsa,String>cFormacion;
    @javafx.fxml.FXML
    private TableColumn <PersonalBolsa,String>cAltura;
    @javafx.fxml.FXML
    private TableColumn <PersonalBolsa,String>cIdioma;
    @javafx.fxml.FXML
    private RadioButton radioMujer;
    @javafx.fxml.FXML
    private ToggleGroup vale;
    @javafx.fxml.FXML
    private RadioButton radioHombre;
    @javafx.fxml.FXML
    private ChoiceBox<String> comboSigno;


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
        Curso  sinCurso=new Curso();
        sinCurso.setNombre("Sin Curso");
        comboCurso.getItems().add(sinCurso);
        comboCalificacion.getItems().add("Calificado");
        comboCalificacion.getItems().add("No Calificado");
        comboCalificacion.setDisable(true);
        comboIdioma.getItems().add("Ingles");
        comboIdioma.getItems().add("Árabe");
        comboIdioma.getItems().add("Francés");
        comboFormacion.getItems().add("Militar");
        comboFormacion.getItems().add("TCCC");
        comboFormacion.getItems().add("CPO");
        comboSigno.getItems().add("<");
        comboSigno.getItems().add(">");
        comboSigno.getItems().add("=");
        comboSigno.getItems().add("<=");
        comboSigno.getItems().add(">=");
        spAltura.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0,2.10,0.0,0.1));

        listenerCurso=((observableValue, s, t1) -> {

            if(comboCurso.getValue().getNombre().equals("Sin Curso")){
                copiado.put("curso","cursoId is null");
                parametro.remove("curso");
                comboCalificacion.setDisable(true);
            }else{
                System.out.println("Entro");
                copiado.put("curso","cursoId.id=:idCurso");
                parametro.put("curso",comboCurso.getValue().getId());
                comboCalificacion.setDisable(false);
            }




        });
        comboCurso.getSelectionModel().selectedItemProperty().addListener(listenerCurso);


       comboCalificacionListener=((observableValue, o, t1) -> {
           if(t1.equals("Calificado")){
               copiado.put("calificacion","notaCurso is not null");
           }else{
               copiado.put("calificacion","notaCurso is null");
           }


        });
       comboCalificacion.getSelectionModel().selectedItemProperty().addListener(comboCalificacionListener);

       listenerFormacion=(((observableValue, s, t1) -> {
           copiado.put("formacion","titulacion LIKE:titulacion");
           parametro.put("formacion",comboFormacion.getValue().toString().toLowerCase());
       }));
       comboFormacion.getSelectionModel().selectedItemProperty().addListener(listenerFormacion);

      listenerIdioma=(((observableValue, curso, t1) -> {
          copiado.put("idioma","idioma LIKE:idioma");
          parametro.put("idioma",(comboIdioma.getValue().toString().toLowerCase().equals("árabe")?"arabe":comboIdioma.getValue().toString().toLowerCase().equals("francés")?"frances":"ingles"));
      }));
       comboIdioma.getSelectionModel().selectedItemProperty().addListener(listenerIdioma);

radioHombre.setOnAction(actionEvent -> {
    if(radioHombre.isSelected()){
        copiado.put("sexo","sexo=:sexo");
        parametro.put("sexo",'h');
    }

});
radioMujer.setOnAction(actionEvent -> {
    if(radioMujer.isSelected()){
        copiado.put("sexo","sexo=:sexo");
        parametro.put("sexo",'m');
    }

        });


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
       cIdioma.setCellValueFactory(alumno->{
           String idioma=alumno.getValue().getIdioma();
           return new SimpleStringProperty(idioma);
       });
       cFormacion.setCellValueFactory(alumno->{
           String formacion=alumno.getValue().getTitulacion();
           return new SimpleStringProperty(formacion);
       });
       cAltura.setCellValueFactory(alumno->{
           String altura=String.valueOf(alumno.getValue().getAltura());
           return new SimpleStringProperty(altura);
       });
        cRegistro.setCellValueFactory(alumno->{
            String fecha=alumno.getValue().getFechaRegistro().toString();
            return new SimpleStringProperty(fecha);
        });
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
        String filtro=textBuscar.getText().toLowerCase();
        filtro=filtro.replace("á","a");
        filtro= filtro.replace("é","e");
        filtro= filtro.replace("í","i");
        filtro= filtro.replace("ó","o");
        filtro= filtro.replace("ú","u");
        if(filtro.isEmpty()){
            if(boleano){
                tabla.setItems(filtrado);
            }else{
                tabla.setItems(alumnos);
            }

        } else{
            filtroALumnos.clear();
            if(boleano){
                for(PersonalBolsa a:filtrado){
                    String porBusqueda= a.getNombre().toLowerCase()+" "+a.getApellido1().toLowerCase()+" "+a.getApellido2().toLowerCase()+" "+a.getDni().toLowerCase()
                            +" "+a.getTelefono().toLowerCase()+" "+a.getCorreo().toLowerCase();
                  porBusqueda=porBusqueda.replace("á","a");
                  porBusqueda=porBusqueda.replace("é","e");
                  porBusqueda=porBusqueda.replace("í","i");
                  porBusqueda=porBusqueda.replace("ó","o");
                  porBusqueda=porBusqueda.replace("ú","u");
                    if(porBusqueda.contains(filtro.toLowerCase())){
                        filtroALumnos.add(a);
                    }
                }

            }else{
                for(PersonalBolsa a:alumnos){
                    String porBusqueda= a.getNombre().toLowerCase()+" "+a.getApellido1().toLowerCase()+" "+a.getApellido2().toLowerCase()+" "+a.getDni().toLowerCase()
                            +" "+a.getTelefono().toLowerCase()+" "+a.getCorreo().toLowerCase();
                    porBusqueda=porBusqueda.replace("á","a");
                    porBusqueda=porBusqueda.replace("é","e");
                    porBusqueda=porBusqueda.replace("í","i");
                    porBusqueda=porBusqueda.replace("ó","o");
                    porBusqueda=porBusqueda.replace("ú","u");
                    if(porBusqueda.contains(filtro.toLowerCase())){
                        filtroALumnos.add(a);
                    }
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
        if(spAltura.getValue()!=0.0||radioHombre.isSelected()||radioMujer.isSelected()||comboCurso.getValue()!=null||comboIdioma.getValue()!=null||comboFormacion.getValue()!=null||(dateDesde.getValue()!=null&&dateHasta.getValue()!=null)){
            boleano=false;
            copiado.clear();
            comboCurso.getSelectionModel().selectedItemProperty().removeListener(listenerCurso);
            comboCalificacion.getSelectionModel().selectedItemProperty().removeListener(comboCalificacionListener);
            comboIdioma.getSelectionModel().selectedItemProperty().removeListener(listenerIdioma);
            comboFormacion.getSelectionModel().selectedItemProperty().removeListener(listenerFormacion);
            comboCalificacion.getSelectionModel().select(null);
            comboCurso.getSelectionModel().select(null);
            comboCalificacion.setDisable(true);
            comboSigno.getSelectionModel().select(null);
            radioHombre.setSelected(false);
            radioMujer.setSelected(false);
            comboIdioma.getSelectionModel().select(null);
            comboFormacion.getSelectionModel().select(null);
            spAltura.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0,2.10,0.0,0.1));
            alumnos.clear();
            alumnos.addAll((new PersonalBolsaDAOImplement().getAll()));
            if(textBuscar.getText().isEmpty()){
                tabla.setItems(alumnos);
            }else {
                tabla.setItems(filtroALumnos);
            }
            copiado.clear();
            parametro.clear();
            comboCalificacion.getSelectionModel().selectedItemProperty().addListener(comboCalificacionListener);
            comboFormacion.getSelectionModel().selectedItemProperty().addListener(listenerFormacion);
            comboIdioma.getSelectionModel().selectedItemProperty().addListener(listenerIdioma);
            comboCurso.getSelectionModel().selectedItemProperty().addListener(listenerCurso);

        }



    }

    @javafx.fxml.FXML
    public void buscar(ActionEvent actionEvent) {
        if(spAltura.getValue()!=0.0&&comboSigno.getValue()==null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText("Selección Signo de la Altura");
            alerta.setContentText("Seleccione el simbolo de la altura ya que se necesita\nsaber como lo quieres buscar por ejemplo " +
                    "<,>,=,<=,>=");
            alerta.showAndWait();
        }else{
            filtrado.clear();
            boleano=true;
            ArrayList<PersonalBolsa>personalBBDD=new ArrayList<>();
            List<String>fechas=new ArrayList<>();
            if(spAltura.getValue()!=0.0){
                copiado.put("altura","altura"+comboSigno.getValue()+":altura");
                parametro.put("altura",spAltura.getValue());
            }
            if(dateDesde.getValue()!=null&&dateHasta.getValue()!=null){
                LocalDate desde=dateDesde.getValue();
                LocalDate hasta=dateHasta.getValue();
                fechas=obtenerFechas(desde,hasta);
                copiado.put("fecha","fechaRegistro=:fecha");

            }
            HashMap<String,String>copiadoViceversa=new HashMap<>();
            copiadoViceversa.putAll(copiado);
            Boolean compruebaCase=false;

            switch (copiado.size()){
                case 1:
                    System.out.println(copiado);
                    sentencia.put("sentencia","select ca from "+(copiado.containsKey("curso")?"AlumnoCurso ca":"PersonalBolsa ca")+" where ca.");
                    String guardado=sentencia.get("sentencia");
                    if(comboCurso.getValue()!=null&&comboCurso.getValue().getNombre().equals("Sin Curso")){
                        sentencia.put("sentencia","select distinct ca from PersonalBolsa ca left join AlumnoCurso ac on ca.id = ac.alumnoId.id where ac."+copiado.get("curso"));
                    }
                    if(guardado.contains("PersonalBolsa")&&comboCurso.getValue()!=null&&!comboCurso.getValue().getNombre().equals("Sin Curso")){
                        sentencia.put("sentencia","select ca from "+(copiado.containsKey("curso")?"AlumnoCurso ca":"PersonalBolsa ca")+" where ca."+
                                ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):"")))))));
                    }else if(comboCurso.getValue()!=null&&!comboCurso.getValue().getNombre().equals("Sin Curso")){
                        sentencia.put("sentencia","select ca.alumnoId from "+(copiado.containsKey("curso")?"AlumnoCurso ca":(copiado.containsKey("fecha")?"AlumnoCurso ca":"PersonalBolsa ca"))+" where ca.");
                        if(copiado.containsKey("altura")){
                            copiado.put("altura","alumnoId.altura=:altura");
                        }
                        if(copiado.containsKey("idioma")){
                            copiado.put("idioma","alumnoId.idioma like:idioma");
                        }
                        if(copiado.containsKey("formacion")){
                            copiado.put("formacion","alumnoId.titulacion like:titulacion");
                        }
                        if(copiado.containsKey("fecha")){
                            copiado.put("fecha","alumnoId.fechaRegistro=:fecha");
                        }
                        if(copiado.containsKey("sexo")){
                            copiado.put("sexo","alumnoId.sexo=:sexo");
                        }
                        sentencia.put("sentencia","select ca.alumnoId from "+(copiado.containsKey("curso")?"AlumnoCurso ca":"PersonalBolsa ca")+" where ca."+
                                ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):"")))))));
                    }else if(comboCurso.getValue()==null){
                        sentencia.put("sentencia","select ca from "+(copiado.containsKey("curso")?"AlumnoCurso ca":"PersonalBolsa ca")+" where ca."+
                                ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):"")))))));
                    }
                    System.out.println(sentencia);
                    copiado.clear();
                    copiado.putAll(copiadoViceversa);
                    personalBBDD=(ArrayList<PersonalBolsa>) (new AlumnoCursoDAOImplement()).getFiltradoTodos(fechas,sentencia,parametro);
                    filtrado.addAll(personalBBDD);
                    tabla.setItems(filtrado);
                    break;
                case 2:
                    sentencia.put("sentencia","select ca from "+(copiado.containsKey("curso")?"AlumnoCurso ca":"PersonalBolsa ca")+" where ca."+
                            ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("calificacion")?copiado.get("calificacion"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):""))))))));
                    String contiene= sentencia.get("sentencia");
                    if(comboCurso.getValue()!=null&&comboCurso.getValue().getNombre().equals("Sin Curso")){
                        sentencia.put("sentencia","select distinct ca from PersonalBolsa ca left join AlumnoCurso ac on ca.id = ac.alumnoId.id where ac."+copiado.get("curso"));
                        compruebaCase=true;
                    }
                    if(compruebaCase){
                        copiado.remove("curso");
                    }

                    if(contiene.contains("PersonalBolsa")&&comboCurso.getValue()!=null&&!comboCurso.getValue().getNombre().equals("Sin Curso")){
                        sentencia.put("sentencia","select ca from "+(copiado.containsKey("curso")?"AlumnoCurso ca":"PersonalBolsa ca")+" where ca."+
                                ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("calificacion")?copiado.get("calificacion"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):""))))))));
                    }else if(comboCurso.getValue()!=null&&!comboCurso.getValue().getNombre().equals("Sin Curso")){
                        sentencia.put("sentencia","select ca.alumnoId from "+(copiado.containsKey("curso")?"AlumnoCurso ca":"PersonalBolsa ca")+" where ca.");
                        if(copiado.containsKey("altura")){
                            copiado.put("altura","alumnoId.altura"+comboSigno.getValue()+":altura");
                        }
                        if(copiado.containsKey("idioma")){
                            copiado.put("idioma","alumnoId.idioma like:idioma");
                        }
                        if(copiado.containsKey("formacion")){
                            copiado.put("formacion","alumnoId.titulacion like:titulacion");
                        }
                        if(copiado.containsKey("fecha")){
                            copiado.put("fecha","alumnoId.fechaRegistro=:fecha");
                        }
                        if(copiado.containsKey("sexo")){
                            copiado.put("sexo","alumnoId.sexo=:sexo");
                        }
                        sentencia.put("sentencia","select ca.alumnoId from "+(copiado.containsKey("curso")?"AlumnoCurso ca":"PersonalBolsa ca")+" where ca."+
                                ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("calificacion")?copiado.get("calificacion"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):""))))))));
                    }else if(comboCurso.getValue()==null){
                        sentencia.put("sentencia","select ca from "+(copiado.containsKey("curso")?"AlumnoCurso ca":"PersonalBolsa ca")+" where ca."+
                                ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):"")))))));
                    }
                    if(contiene.contains("ca.cursoId")){
                        copiado.remove("curso");
                    }else if(contiene.contains("ca.altura")||contiene.contains("ca.alumnoId.altura")){
                        copiado.remove("altura");
                    }else if(contiene.contains("ca.idioma")||contiene.contains("ca.alumnoId.idioma")) {
                        copiado.remove("idioma");
                    }else if(contiene.contains("ca.titulacion")||contiene.contains("ca.alumnoId.titulacion")) {
                        copiado.remove("formacion");
                    }else if(contiene.contains("ca.alumnoId.titulacion")) {
                        copiado.remove("calificacion");
                    }else if(contiene.contains("ca.fechaRegistro")||contiene.contains("ca.alumnoId.fechaRegistro")) {
                        copiado.remove("fecha");
                    }else if(contiene.contains("ca.sexo")||contiene.contains("ca.alumnoId.sexo")){
                        copiado.remove("sexo");
                    }
                    String hecho=" and ca."+  ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("calificacion")?copiado.get("calificacion"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):"")))))));
                    sentencia.put("sentencia",sentencia.get("sentencia")+hecho);
                    System.out.println(sentencia);
                    copiado.clear();
                    copiado.putAll(copiadoViceversa);
                    personalBBDD=(ArrayList<PersonalBolsa>) (new AlumnoCursoDAOImplement()).getFiltradoTodos(fechas,sentencia,parametro);
                    filtrado.addAll(personalBBDD);
                    tabla.setItems(filtrado);
                    break;


                case 3:
                    sentencia.put("sentencia","select ca from "+(copiado.containsKey("curso")?"AlumnoCurso ca":"PersonalBolsa ca")+" where ca." +
                            ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("calificacion")?copiado.get("calificacion"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):""))))))));
                    String contiene1= sentencia.get("sentencia");
                    if(comboCurso.getValue()!=null&&comboCurso.getValue().getNombre().equals("Sin Curso")){
                        sentencia.put("sentencia","select distinct ca from PersonalBolsa ca left join AlumnoCurso ac on ca.id = ac.alumnoId.id where ac."+copiado.get("curso"));
                        compruebaCase=true;
                    }
                    if(compruebaCase){
                        copiado.remove("curso");
                    }
                    if(contiene1.contains("PersonalBolsa")&&comboCurso.getValue()!=null&&!comboCurso.getValue().getNombre().equals("Sin Curso")){
                        sentencia.put("sentencia","select ca from "+(copiado.containsKey("curso")?"AlumnoCurso ca":"PersonalBolsa ca")+" where ca."+
                                ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("calificacion")?copiado.get("calificacion"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):""))))))));
                    }else if(comboCurso.getValue()!=null&&!comboCurso.getValue().getNombre().equals("Sin Curso")){
                        sentencia.put("sentencia","select ca.alumnoId from "+(copiado.containsKey("curso")?"AlumnoCurso ca":"PersonalBolsa ca")+" where ca.");
                        if(copiado.containsKey("altura")){
                            copiado.put("altura","alumnoId.altura"+comboSigno.getValue()+":altura");
                        }
                        if(copiado.containsKey("idioma")){
                            copiado.put("idioma","alumnoId.idioma like:idioma");
                        }
                        if(copiado.containsKey("formacion")){
                            copiado.put("formacion","alumnoId.titulacion like:titulacion");
                        }
                        if(copiado.containsKey("fecha")){
                            copiado.put("fecha","alumnoId.fechaRegistro=:fecha");
                        }
                        if(copiado.containsKey("sexo")){
                            copiado.put("sexo","alumnoId.sexo=:sexo");
                        }
                        sentencia.put("sentencia","select ca.alumnoId from "+(copiado.containsKey("curso")?"AlumnoCurso ca":"PersonalBolsa ca")+" where ca."+
                                ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("calificacion")?copiado.get("calificacion"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):""))))))));
                    }else if(comboCurso.getValue()==null){
                        sentencia.put("sentencia","select ca from "+(copiado.containsKey("curso")?"AlumnoCurso ca":"PersonalBolsa ca")+" where ca."+
                                ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):"")))))));
                    }
                    if(contiene1.contains("ca.cursoId")){
                        copiado.remove("curso");
                    }else if(contiene1.contains("ca.altura")||contiene1.contains("ca.alumnoId.altura")){
                        copiado.remove("altura");
                    }else if(contiene1.contains("ca.idioma")||contiene1.contains("ca.alumnoId.idioma")) {
                        copiado.remove("idioma");
                    }else if(contiene1.contains("ca.titulacion")||contiene1.contains("ca.alumnoId.titulacion")) {
                        copiado.remove("formacion");
                    }else if(contiene1.contains("ca.alumnoId.titulacion")) {
                        copiado.remove("calificacion");
                    }else if(contiene1.contains("ca.fechaRegistro")||contiene1.contains("ca.alumnoId.fechaRegistro")) {
                        copiado.remove("fecha");
                    }else if(contiene1.contains("ca.sexo")||contiene1.contains("ca.alumnoId.sexo")){
                        copiado.remove("sexo");
                    }
                    String hecho1=" and ca."+ ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("calificacion")?copiado.get("calificacion"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):"")))))));
                    sentencia.put("sentencia",sentencia.get("sentencia")+hecho1);
                    String contiene2= sentencia.get("sentencia");
                    if(contiene2.contains("ca.curso")&&copiado.containsKey("curso")){
                        copiado.remove("curso");
                    } else if((contiene2.contains("ca.altura")||contiene2.contains("ca.alumnoId.altura"))&&copiado.containsKey("altura")){
                        copiado.remove("altura");
                    }else if((contiene2.contains("ca.idioma")||contiene2.contains("ca.alumnoId.idioma"))&&copiado.containsKey("idioma")) {
                        copiado.remove("idioma");
                    }else if(contiene2.contains("ca.titulacion")||contiene2.contains("ca.alumnoId.titulacion")&&copiado.containsKey("formacion")) {
                        copiado.remove("formacion");
                    }else if(contiene2.contains("ca.notaCurso")&&copiado.containsKey("calificacion")) {
                        copiado.remove("calificacion");
                    }else if((contiene2.contains("ca.fechaRegistro")||contiene2.contains("ca.alumnoId.fechaRegistro"))&&copiado.containsKey("fecha")) {
                        copiado.remove("fecha");
                    }else if(contiene2.contains("ca.sexo")||contiene2.contains("ca.alumnoId.sexo")&&copiado.containsKey("sexo")){
                        copiado.remove("sexo");
                    }
                    String hecho2=" and ca."+  ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("calificacion")?copiado.get("calificacion"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):"")))))));
                    sentencia.put("sentencia",sentencia.get("sentencia")+hecho2);
                    System.out.println(sentencia);
                    copiado.clear();
                    copiado.putAll(copiadoViceversa);
                    personalBBDD=(ArrayList<PersonalBolsa>) (new AlumnoCursoDAOImplement()).getFiltradoTodos(fechas,sentencia,parametro);
                    filtrado.addAll(personalBBDD);
                    tabla.setItems(filtrado);
                    break;
                case 4:
                    sentencia.put("sentencia","select ca from "+(copiado.containsKey("curso")?"AlumnoCurso ca":"PersonalBolsa ca")+" where ca."+
                            ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("calificacion")?copiado.get("calificacion"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):""))))))));
                    String contiene3= sentencia.get("sentencia");
                    if(comboCurso.getValue()!=null&&comboCurso.getValue().getNombre().equals("Sin Curso")){
                        sentencia.put("sentencia","select distinct ca from PersonalBolsa ca left join AlumnoCurso ac on ca.id = ac.alumnoId.id where ac."+copiado.get("curso"));
                        compruebaCase=true;
                    }
                    if(compruebaCase){
                        copiado.remove("curso");
                    }
                    if(contiene3.contains("PersonalBolsa")&&comboCurso.getValue()!=null&&!comboCurso.getValue().getNombre().equals("Sin Curso")){
                        sentencia.put("sentencia","select ca from "+(copiado.containsKey("curso")?"AlumnoCurso ca":"PersonalBolsa ca")+" where ca."+
                                ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("calificacion")?copiado.get("calificacion"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):""))))))));
                    }else if(comboCurso.getValue()!=null&&!comboCurso.getValue().getNombre().equals("Sin Curso")){
                        sentencia.put("sentencia","select ca.alumnoId from "+(copiado.containsKey("curso")?"AlumnoCurso ca":"PersonalBolsa ca")+" where ca.");
                        if(copiado.containsKey("altura")){
                            copiado.put("altura","alumnoId.altura"+comboSigno.getValue()+":altura");
                        }
                        if(copiado.containsKey("idioma")){
                            copiado.put("idioma","alumnoId.idioma like:idioma");
                        }
                        if(copiado.containsKey("formacion")){
                            copiado.put("formacion","alumnoId.titulacion like:titulacion");
                        }
                        if(copiado.containsKey("fecha")){
                            copiado.put("fecha","alumnoId.fechaRegistro=:fecha");
                        }
                        if(copiado.containsKey("sexo")){
                            copiado.put("sexo","alumnoId.sexo=:sexo");
                        }
                        sentencia.put("sentencia","select ca.alumnoId from "+(copiado.containsKey("curso")?"AlumnoCurso ca":"PersonalBolsa ca")+" where ca."+
                                ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("calificacion")?copiado.get("calificacion"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):""))))))));
                    }else if(comboCurso.getValue()==null){
                        sentencia.put("sentencia","select ca from "+(copiado.containsKey("curso")?"AlumnoCurso ca":"PersonalBolsa ca")+" where ca."+
                                ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):"")))))));
                    }
                    if(contiene3.contains("ca.cursoId")){
                        copiado.remove("curso");
                    }else if(contiene3.contains("ca.altura")||contiene3.contains("ca.alumnoId.altura")){
                        copiado.remove("altura");
                    }else if(contiene3.contains("ca.idioma")||contiene3.contains("ca.alumnoId.idioma")) {
                        copiado.remove("idioma");
                    }else if(contiene3.contains("ca.titulacion")||contiene3.contains("ca.alumnoId.titulacion")) {
                        copiado.remove("formacion");
                    }else if(contiene3.contains("ca.alumnoId.titulacion")) {
                        copiado.remove("calificacion");
                    }else if(contiene3.contains("ca.fechaRegistro")||contiene3.contains("ca.alumnoId.fechaRegistro")) {
                        copiado.remove("fecha");
                    }else if(contiene3.contains("ca.sexo")||contiene3.contains("ca.alumnoId.sexo")){
                        copiado.remove("sexo");
                    }
                    String hecho3=" and ca."+  ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("calificacion")?copiado.get("calificacion"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):"")))))));
                    sentencia.put("sentencia",sentencia.get("sentencia")+hecho3);
                    String contiene4= sentencia.get("sentencia");
                    if(contiene4.contains("ca.curso")&&copiado.containsKey("curso")){
                        copiado.remove("curso");
                    } else if((contiene4.contains("ca.altura")||contiene4.contains("ca.alumnoId.altura"))&&copiado.containsKey("altura")){
                        copiado.remove("altura");
                    }else if((contiene4.contains("ca.idioma")||contiene4.contains("ca.alumnoId.idioma"))&&copiado.containsKey("idioma")) {
                        copiado.remove("idioma");
                    }else if(contiene4.contains("ca.titulacion")||contiene4.contains("ca.alumnoId.titulacion")&&copiado.containsKey("formacion")) {
                        copiado.remove("formacion");
                    }else if(contiene4.contains("ca.notaCurso")&&copiado.containsKey("calificacion")) {
                        copiado.remove("calificacion");
                    }else if(contiene4.contains("ca.fechaRegistro")||contiene4.contains("ca.alumnoId.fechaRegistro")&&copiado.containsKey("fecha")) {
                        copiado.remove("fecha");
                    }else if(contiene4.contains("ca.sexo")||contiene4.contains("ca.alumnoId.sexo")&&copiado.containsKey("sexo")){
                        copiado.remove("sexo");
                    }
                    String hecho4=" and ca."+  ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("calificacion")?copiado.get("calificacion"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):"")))))));
                    sentencia.put("sentencia",sentencia.get("sentencia")+hecho4);


                    String contiene5= sentencia.get("sentencia");
                    if(contiene5.contains("ca.curso")&&copiado.containsKey("curso")){
                        copiado.remove("curso");
                    } else if((contiene5.contains("ca.altura")||contiene5.contains("ca.alumnoId.altura"))&&copiado.containsKey("altura")){
                        copiado.remove("altura");
                    }else if((contiene5.contains("ca.idioma")||contiene5.contains("ca.alumnoId.idioma"))&&copiado.containsKey("idioma")) {
                        copiado.remove("idioma");
                    }else if(contiene5.contains("ca.titulacion")||contiene5.contains("ca.alumnoId.titulacion")&&copiado.containsKey("formacion")) {
                        copiado.remove("formacion");
                    }else if(contiene5.contains("ca.notaCurso")&&copiado.containsKey("calificacion")) {
                        copiado.remove("calificacion");
                    }else if(contiene5.contains("ca.fechaRegistro")||contiene5.contains("ca.alumnoId.fechaRegistro")&&copiado.containsKey("fecha")) {
                        copiado.remove("fecha");
                    }else if(contiene5.contains("ca.sexo")||contiene5.contains("ca.alumnoId.sexo")&&copiado.containsKey("sexo")){
                        copiado.remove("sexo");
                    }
                    String hecho5=" and ca."+  ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("calificacion")?copiado.get("calificacion"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):"")))))));
                    sentencia.put("sentencia",sentencia.get("sentencia")+hecho5);
                    System.out.println(sentencia);
                    copiado.clear();
                    copiado.putAll(copiadoViceversa);
                    personalBBDD=(ArrayList<PersonalBolsa>) (new AlumnoCursoDAOImplement()).getFiltradoTodos(fechas,sentencia,parametro);
                    filtrado.addAll(personalBBDD);
                    tabla.setItems(filtrado);
                    break;
                case 5:
                    sentencia.put("sentencia","select ca from "+(copiado.containsKey("curso")?"AlumnoCurso ca":"PersonalBolsa ca")+" where ca."+
                            ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("calificacion")?copiado.get("calificacion"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):""))))))));
                    String contiene6= sentencia.get("sentencia");
                    if(comboCurso.getValue()!=null&&comboCurso.getValue().getNombre().equals("Sin Curso")){
                        sentencia.put("sentencia","select distinct ca from PersonalBolsa ca left join AlumnoCurso ac on ca.id = ac.alumnoId.id where ac."+copiado.get("curso"));
                        compruebaCase=true;
                    }
                    if(compruebaCase){
                        copiado.remove("curso");
                    }
                    if(contiene6.contains("PersonalBolsa")&&comboCurso.getValue()!=null&&!comboCurso.getValue().getNombre().equals("Sin Curso")){
                        sentencia.put("sentencia","select ca from "+(copiado.containsKey("curso")?"AlumnoCurso ca":"PersonalBolsa ca")+" where ca."+
                                ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("calificacion")?copiado.get("calificacion"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):""))))))));
                    }else if(comboCurso.getValue()!=null&&!comboCurso.getValue().getNombre().equals("Sin Curso")){
                        sentencia.put("sentencia","select ca.alumnoId from "+(copiado.containsKey("curso")?"AlumnoCurso ca":"PersonalBolsa ca")+" where ca.");
                        if(copiado.containsKey("altura")){
                            copiado.put("altura","alumnoId.altura"+comboSigno.getValue()+":altura");
                        }
                        if(copiado.containsKey("idioma")){
                            copiado.put("idioma","alumnoId.idioma like:idioma");
                        }
                        if(copiado.containsKey("formacion")){
                            copiado.put("formacion","alumnoId.titulacion like:titulacion");
                        }
                        if(copiado.containsKey("fecha")){
                            copiado.put("fecha","alumnoId.fechaRegistro=:fecha");
                        }
                        if(copiado.containsKey("sexo")){
                            copiado.put("sexo","alumnoId.sexo=:sexo");
                        }
                        sentencia.put("sentencia","select ca.alumnoId from "+(copiado.containsKey("curso")?"AlumnoCurso ca":"PersonalBolsa ca")+" where ca."+
                                ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("calificacion")?copiado.get("calificacion"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):""))))))));
                    }else if(comboCurso.getValue()==null){
                        sentencia.put("sentencia","select ca from "+(copiado.containsKey("curso")?"AlumnoCurso ca":"PersonalBolsa ca")+" where ca."+
                                ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):"")))))));
                    }
                    if(contiene6.contains("ca.cursoId")){
                        copiado.remove("curso");
                    }else if(contiene6.contains("ca.altura")||contiene6.contains("ca.alumnoId.altura")){
                        copiado.remove("altura");
                    }else if(contiene6.contains("ca.idioma")||contiene6.contains("ca.alumnoId.idioma")) {
                        copiado.remove("idioma");
                    }else if(contiene6.contains("ca.titulacion")||contiene6.contains("ca.alumnoId.titulacion")) {
                        copiado.remove("formacion");
                    }else if(contiene6.contains("ca.alumnoId.titulacion")) {
                        copiado.remove("calificacion");
                    }else if(contiene6.contains("ca.fechaRegistro")||contiene6.contains("ca.alumnoId.fechaRegistro")) {
                        copiado.remove("fecha");
                    }else if(contiene6.contains("ca.sexo")||contiene6.contains("ca.alumnoId.sexo")){
                        copiado.remove("sexo");
                    }
                    String hecho6=" and ca."+  ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("calificacion")?copiado.get("calificacion"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):"")))))));
                    sentencia.put("sentencia",sentencia.get("sentencia")+hecho6);
                    String contiene7= sentencia.get("sentencia");
                    if(contiene7.contains("ca.curso")&&copiado.containsKey("curso")){
                        copiado.remove("curso");
                    } else if((contiene7.contains("ca.altura")||contiene7.contains("ca.alumnoId.altura"))&&copiado.containsKey("altura")){
                        copiado.remove("altura");
                    }else if((contiene7.contains("ca.idioma")||contiene7.contains("ca.alumnoId.idioma"))&&copiado.containsKey("idioma")) {
                        copiado.remove("idioma");
                    }else if(contiene7.contains("ca.titulacion")||contiene7.contains("ca.alumnoId.titulacion")&&copiado.containsKey("formacion")) {
                        copiado.remove("formacion");
                    }else if(contiene7.contains("ca.notaCurso")&&copiado.containsKey("calificacion")) {
                        copiado.remove("calificacion");
                    }else if(contiene7.contains("ca.fechaRegistro")||contiene7.contains("ca.alumnoId.fechaRegistro")&&copiado.containsKey("fecha")) {
                        copiado.remove("fecha");
                    }else if(contiene7.contains("ca.sexo")||contiene7.contains("ca.alumnoId.sexo")&&copiado.containsKey("sexo")){
                        copiado.remove("sexo");
                    }
                    String hecho7=" and ca."+  ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("calificacion")?copiado.get("calificacion"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):"")))))));
                    sentencia.put("sentencia",sentencia.get("sentencia")+hecho7);


                    String contiene8= sentencia.get("sentencia");
                    if(contiene8.contains("ca.curso")&&copiado.containsKey("curso")){
                        copiado.remove("curso");
                    } else if((contiene8.contains("ca.altura")||contiene8.contains("ca.alumnoId.altura"))&&copiado.containsKey("altura")){
                        copiado.remove("altura");
                    }else if((contiene8.contains("ca.idioma")||contiene8.contains("ca.alumnoId.idioma"))&&copiado.containsKey("idioma")) {
                        copiado.remove("idioma");
                    }else if(contiene8.contains("ca.titulacion")||contiene8.contains("ca.alumnoId.titulacion")&&copiado.containsKey("formacion")) {
                        copiado.remove("formacion");
                    }else if(contiene8.contains("ca.notaCurso")&&copiado.containsKey("calificacion")) {
                        copiado.remove("calificacion");
                    }else if(contiene8.contains("ca.fechaRegistro")||contiene8.contains("ca.alumnoId.fechaRegistro")&&copiado.containsKey("fecha")) {
                        copiado.remove("fecha");
                    }else if(contiene8.contains("ca.sexo")||contiene8.contains("ca.alumnoId.sexo")&&copiado.containsKey("sexo")){
                        copiado.remove("sexo");
                    }
                    String hecho8=" and ca."+  ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("calificacion")?copiado.get("calificacion"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):"")))))));
                    sentencia.put("sentencia",sentencia.get("sentencia")+hecho8);

                    String contiene9= sentencia.get("sentencia");
                    if(contiene9.contains("ca.curso")&&copiado.containsKey("curso")){
                        copiado.remove("curso");
                    } else if((contiene9.contains("ca.altura")||contiene9.contains("ca.alumnoId.altura"))&&copiado.containsKey("altura")){
                        copiado.remove("altura");
                    }else if((contiene9.contains("ca.idioma")||contiene9.contains("ca.alumnoId.idioma"))&&copiado.containsKey("idioma")) {
                        copiado.remove("idioma");
                    }else if(contiene9.contains("ca.titulacion")||contiene9.contains("ca.alumnoId.titulacion")&&copiado.containsKey("formacion")) {
                        copiado.remove("formacion");
                    }else if(contiene9.contains("ca.notaCurso")&&copiado.containsKey("calificacion")) {
                        copiado.remove("calificacion");
                    }else if(contiene9.contains("ca.fechaRegistro")||contiene9.contains("ca.alumnoId.fechaRegistro")&&copiado.containsKey("fecha")) {
                        copiado.remove("fecha");
                    }else if(contiene9.contains("ca.sexo")||contiene9.contains("ca.alumnoId.sexo")&&copiado.containsKey("sexo")){
                        copiado.remove("sexo");
                    }
                    String hecho9=" and ca."+  ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("calificacion")?copiado.get("calificacion"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):"")))))));
                    sentencia.put("sentencia",sentencia.get("sentencia")+hecho9);
                    System.out.println(sentencia);
                    copiado.clear();
                    copiado.putAll(copiadoViceversa);
                    personalBBDD=(ArrayList<PersonalBolsa>) (new AlumnoCursoDAOImplement()).getFiltradoTodos(fechas,sentencia,parametro);
                    filtrado.addAll(personalBBDD);
                    tabla.setItems(filtrado);
                    break;
                case 6:
                    sentencia.put("sentencia","select ca from "+(copiado.containsKey("curso")?"AlumnoCurso ca":"PersonalBolsa ca")+" where ca."+
                            ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("calificacion")?copiado.get("calificacion"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):""))))))));
                    String contiene10= sentencia.get("sentencia");
                    if(comboCurso.getValue()!=null&&comboCurso.getValue().getNombre().equals("Sin Curso")){
                        sentencia.put("sentencia","select distinct ca from PersonalBolsa ca left join AlumnoCurso ac on ca.id = ac.alumnoId.id where ac."+copiado.get("curso"));
                        compruebaCase=true;
                    }
                    if(compruebaCase){
                        copiado.remove("curso");
                    }
                    if(contiene10.contains("PersonalBolsa")&&comboCurso.getValue()!=null&&!comboCurso.getValue().getNombre().equals("Sin Curso")){
                        sentencia.put("sentencia","select ca from "+(copiado.containsKey("curso")?"AlumnoCurso ca":"PersonalBolsa ca")+" where ca."+
                                ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("calificacion")?copiado.get("calificacion"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):""))))))));
                    }else if(comboCurso.getValue()!=null&&!comboCurso.getValue().getNombre().equals("Sin Curso")){
                        sentencia.put("sentencia","select ca.alumnoId from "+(copiado.containsKey("curso")?"AlumnoCurso ca":"PersonalBolsa ca")+" where ca.");
                        if(copiado.containsKey("altura")){
                            copiado.put("altura","alumnoId.altura"+comboSigno.getValue()+":altura");
                        }
                        if(copiado.containsKey("idioma")){
                            copiado.put("idioma","alumnoId.idioma like:idioma");
                        }
                        if(copiado.containsKey("formacion")){
                            copiado.put("formacion","alumnoId.titulacion like:titulacion");
                        }
                        if(copiado.containsKey("fecha")){
                            copiado.put("fecha","alumnoId.fechaRegistro=:fecha");
                        }
                        if(copiado.containsKey("sexo")){
                            copiado.put("sexo","alumnoId.sexo=:sexo");
                        }
                        sentencia.put("sentencia","select ca.alumnoId from "+(copiado.containsKey("curso")?"AlumnoCurso ca":"PersonalBolsa ca")+" where ca."+
                                ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("calificacion")?copiado.get("calificacion"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):""))))))));
                    }else if(comboCurso.getValue()==null){
                        sentencia.put("sentencia","select ca from "+(copiado.containsKey("curso")?"AlumnoCurso ca":"PersonalBolsa ca")+" where ca."+
                                ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):"")))))));
                    }
                    if(contiene10.contains("ca.cursoId")){
                        copiado.remove("curso");
                    }else if(contiene10.contains("ca.altura")||contiene10.contains("ca.alumnoId.altura")){
                        copiado.remove("altura");
                    }else if(contiene10.contains("ca.idioma")||contiene10.contains("ca.alumnoId.idioma")) {
                        copiado.remove("idioma");
                    }else if(contiene10.contains("ca.titulacion")||contiene10.contains("ca.alumnoId.titulacion")) {
                        copiado.remove("formacion");
                    }else if(contiene10.contains("ca.alumnoId.titulacion")) {
                        copiado.remove("calificacion");
                    }else if(contiene10.contains("ca.fechaRegistro")||contiene10.contains("ca.alumnoId.fechaRegistro")) {
                        copiado.remove("fecha");
                    }else if(contiene10.contains("ca.sexo")||contiene10.contains("ca.alumnoId.sexo")){
                        copiado.remove("sexo");
                    }
                    String hecho10=" and ca."+  ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("calificacion")?copiado.get("calificacion"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):"")))))));
                    sentencia.put("sentencia",sentencia.get("sentencia")+hecho10);
                    String contiene11= sentencia.get("sentencia");
                    if(contiene11.contains("ca.curso")&&copiado.containsKey("curso")){
                        copiado.remove("curso");
                    } else if((contiene11.contains("ca.altura")||contiene11.contains("ca.alumnoId.altura"))&&copiado.containsKey("altura")){
                        copiado.remove("altura");
                    }else if((contiene11.contains("ca.idioma")||contiene11.contains("ca.alumnoId.idioma"))&&copiado.containsKey("idioma")) {
                        copiado.remove("idioma");
                    }else if(contiene11.contains("ca.titulacion")||contiene11.contains("ca.alumnoId.titulacion")&&copiado.containsKey("formacion")) {
                        copiado.remove("formacion");
                    }else if(contiene11.contains("ca.notaCurso")&&copiado.containsKey("calificacion")) {
                        copiado.remove("calificacion");
                    }else if(contiene11.contains("ca.fechaRegistro")||contiene11.contains("ca.alumnoId.fechaRegistro")&&copiado.containsKey("fecha")) {
                        copiado.remove("fecha");
                    }else if(contiene11.contains("ca.sexo")||contiene11.contains("ca.alumnoId.sexo")&&copiado.containsKey("sexo")){
                        copiado.remove("sexo");
                    }
                    String hecho11=" and ca."+  ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("calificacion")?copiado.get("calificacion"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):"")))))));
                    sentencia.put("sentencia",sentencia.get("sentencia")+hecho11);


                    String contiene12= sentencia.get("sentencia");
                    if(contiene12.contains("ca.curso")&&copiado.containsKey("curso")){
                        copiado.remove("curso");
                    } else if((contiene12.contains("ca.altura")||contiene12.contains("ca.alumnoId.altura"))&&copiado.containsKey("altura")){
                        copiado.remove("altura");
                    }else if((contiene12.contains("ca.idioma")||contiene12.contains("ca.alumnoId.idioma"))&&copiado.containsKey("idioma")) {
                        copiado.remove("idioma");
                    }else if(contiene12.contains("ca.titulacion")||contiene12.contains("ca.alumnoId.titulacion")&&copiado.containsKey("formacion")) {
                        copiado.remove("formacion");
                    }else if(contiene12.contains("ca.notaCurso")&&copiado.containsKey("calificacion")) {
                        copiado.remove("calificacion");
                    }else if(contiene12.contains("ca.fechaRegistro")||contiene12.contains("ca.alumnoId.fechaRegistro")&&copiado.containsKey("fecha")) {
                        copiado.remove("fecha");
                    }else if(contiene12.contains("ca.sexo")||contiene12.contains("ca.alumnoId.sexo")&&copiado.containsKey("sexo")){
                        copiado.remove("sexo");
                    }
                    String hecho12=" and ca."+  ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("calificacion")?copiado.get("calificacion"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):"")))))));
                    sentencia.put("sentencia",sentencia.get("sentencia")+hecho12);

                    String contiene13= sentencia.get("sentencia");
                    if(contiene13.contains("ca.curso")&&copiado.containsKey("curso")){
                        copiado.remove("curso");
                    } else if((contiene13.contains("ca.altura")||contiene13.contains("ca.alumnoId.altura"))&&copiado.containsKey("altura")){
                        copiado.remove("altura");
                    }else if((contiene13.contains("ca.idioma")||contiene13.contains("ca.alumnoId.idioma"))&&copiado.containsKey("idioma")) {
                        copiado.remove("idioma");
                    }else if(contiene13.contains("ca.titulacion")||contiene13.contains("ca.alumnoId.titulacion")&&copiado.containsKey("formacion")) {
                        copiado.remove("formacion");
                    }else if(contiene13.contains("ca.notaCurso")&&copiado.containsKey("calificacion")) {
                        copiado.remove("calificacion");
                    }else if(contiene13.contains("ca.fechaRegistro")||contiene13.contains("ca.alumnoId.fechaRegistro")&&copiado.containsKey("fecha")) {
                        copiado.remove("fecha");
                    }else if(contiene13.contains("ca.sexo")||contiene13.contains("ca.alumnoId.sexo")&&copiado.containsKey("sexo")){
                        copiado.remove("sexo");
                    }
                    String hecho13=" and ca."+  ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("calificacion")?copiado.get("calificacion"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):"")))))));
                    sentencia.put("sentencia",sentencia.get("sentencia")+hecho13);
                    String contiene14= sentencia.get("sentencia");
                    if(contiene14.contains("ca.curso")&&copiado.containsKey("curso")){
                        copiado.remove("curso");
                    } else if((contiene14.contains("ca.altura")||contiene14.contains("ca.alumnoId.altura"))&&copiado.containsKey("altura")){
                        copiado.remove("altura");
                    }else if((contiene14.contains("ca.idioma")||contiene14.contains("ca.alumnoId.idioma"))&&copiado.containsKey("idioma")) {
                        copiado.remove("idioma");
                    }else if(contiene14.contains("ca.titulacion")||contiene14.contains("ca.alumnoId.titulacion")&&copiado.containsKey("formacion")) {
                        copiado.remove("formacion");
                    }else if(contiene14.contains("ca.notaCurso")&&copiado.containsKey("calificacion")) {
                        copiado.remove("calificacion");
                    }else if(contiene14.contains("ca.fechaRegistro")||contiene14.contains("ca.alumnoId.fechaRegistro")&&copiado.containsKey("fecha")) {
                        copiado.remove("fecha");
                    }else if(contiene14.contains("ca.sexo")||contiene14.contains("ca.alumnoId.sexo")&&copiado.containsKey("sexo")){
                        copiado.remove("sexo");
                    }
                    String hecho14=" and ca."+  ((copiado.containsKey("curso"))?copiado.get("curso"):(copiado.containsKey("calificacion")?copiado.get("calificacion"):(copiado.containsKey("altura")?copiado.get("altura"):(copiado.containsKey("idioma")?copiado.get("idioma"):(copiado.containsKey("formacion")?copiado.get("formacion"):(copiado.containsKey("fecha")?copiado.get("fecha"):(copiado.containsKey("sexo")?copiado.get("sexo"):"")))))));
                    sentencia.put("sentencia",sentencia.get("sentencia")+hecho14);
                    System.out.println(sentencia);
                    copiado.clear();
                    copiado.putAll(copiadoViceversa);
                    personalBBDD=(ArrayList<PersonalBolsa>) (new AlumnoCursoDAOImplement()).getFiltradoTodos(fechas,sentencia,parametro);
                    filtrado.addAll(personalBBDD);
                    tabla.setItems(filtrado);

                    break;
                case 7:
                    if(comboCurso.getValue().getNombre().equals("Sin Curso")){
                        sentencia.put("sentencia","SELECT DISTINCT instructor FROM PersonalIIP instructor LEFT JOIN InstructorCurso ic ON instructor.id = ic.instructor.id WHERE ic.curso IS NULL" +
                                " and "+copiado.get("altura")+" and "+copiado.get("idioma")+" and "+copiado.get("formacion")+" and "+copiado.get("fecha")+" and "+copiado.get("sexo"));
                    }else{
                        sentencia.put("sentencia","select ca.alumnoId from AlumnoCurso where ca.curso.id=:idCurso and ca."+copiado.get("calificacion")+" and ca.alumnoId.altura"+comboSigno.getValue()+":altura and ca.alumnoId.idioma LIKE:idioma and ca.alumnoId.titulacion LIKE:titulacion and ca.alumnoId.fechaRegistro=:fecha and ca.alumnoId.sexo=:sexo");
                    }

                    System.out.println(sentencia);
                    copiado.clear();
                    copiado.putAll(copiadoViceversa);
                    System.out.println(parametro.size());
                    personalBBDD=(ArrayList<PersonalBolsa>) (new AlumnoCursoDAOImplement()).getFiltradoTodos(fechas,sentencia,parametro);
                    filtrado.addAll(personalBBDD);
                    tabla.setItems(filtrado);
                    break;
            }
        }


    }
    public static List<String> obtenerFechas(LocalDate desde, LocalDate hasta) {
        List<String> listaFechas = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (!desde.isAfter(hasta)) {
            // Formatear y agregar a la lista
            listaFechas.add(desde.format(formatter));
            // Incrementar un día
            desde = desde.plusDays(1);
        }

        return listaFechas;
    }
}
