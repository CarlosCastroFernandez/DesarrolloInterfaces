package com.example.ininprotec;

import Util.EnvioCorreoElectronico;
import Util.Utilidad;
import clase.*;
import implement.CursoDAOImplement;
import implement.PersonalIIPDAOImplement;
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
import javafx.util.StringConverter;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class TodosInstructorController implements Initializable {
    @javafx.fxml.FXML
    private TableView<PersonalIIP>  tabla;
    @javafx.fxml.FXML
    private TableColumn <PersonalIIP,String> cNombre;
    @javafx.fxml.FXML
    private TableColumn<PersonalIIP,String>  cDNI;
    @javafx.fxml.FXML
    private TableColumn<PersonalIIP,String>  cCorreo;
    @javafx.fxml.FXML
    private TableColumn <PersonalIIP,String> cTelefono;
    @javafx.fxml.FXML
    private TableColumn <PersonalIIP,String> cCurso;
    @javafx.fxml.FXML
    private Button botonBorrar;
    @javafx.fxml.FXML
    private TextField textBuscar;
    @javafx.fxml.FXML
    private ComboBox <Curso>comboCurso;
    @javafx.fxml.FXML
    private Button botonQuitar;
    private ChangeListener<Curso> listenerCurso;
    private ObservableList<PersonalIIP> filtroCurso= FXCollections.observableArrayList();
    private Curso cursoSeleccionado;
    private PersonalIIP instructorSeleccionado;
    private ObservableList<PersonalIIP> instructores= FXCollections.observableArrayList();
    private ObservableList<PersonalIIP> filtroInstructores= FXCollections.observableArrayList();
    @javafx.fxml.FXML
    private ImageView imagenFlecha;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image imagenFlechia=new Image(CreacionCursoController.class.getClassLoader().getResource("imagenes/flechita.png").toExternalForm());
        imagenFlecha.setImage(imagenFlechia);
        imagenFlecha.setOnMouseClicked(mouseEvent -> {
            HelloApplication.cambioVentana("principal-view.fxml");
        });

        Curso sinCurso=new Curso();
        sinCurso.setNombre("Sin Curso");
        comboCurso.setConverter(new StringConverter<Curso>() {
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
        comboCurso.getItems().add(sinCurso);
        listenerCurso=((observableValue, s, t1) -> {
            filtroCurso.clear();

            cursoSeleccionado=t1;
            if(cursoSeleccionado!=null){
                if(!cursoSeleccionado.getNombre().equals("Sin Curso")){
                    filtroCurso.addAll((new PersonalIIPDAOImplement()).instructorByCursoId(cursoSeleccionado));
                    tabla.setItems(filtroCurso);
                }else{
                    filtroCurso.addAll((new PersonalIIPDAOImplement()).instructorSinCursoId());
                    tabla.setItems(filtroCurso);
                }


            }
        });
        comboCurso.getSelectionModel().selectedItemProperty().addListener(listenerCurso);
        tabla.setRowFactory(tv -> {
            TableRow<PersonalIIP> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                    PersonalIIP clickedRow = row.getItem();
                    if(clickedRow.getInstructor()!=0L){
                        Utilidad.setInstructor(clickedRow);
                        HelloApplication.cambioVentana("registroInstructor-view.fxml");
                    }else{
                        Alert alerta = new Alert(Alert.AlertType.ERROR);
                        alerta.setTitle("ERROR");
                        alerta.setHeaderText("Permisos Insuficientes");
                        alerta.setContentText("No obtienes los permisos suficientes.");
                        alerta.showAndWait();
                    }

                }
            });
            return row;
        });
        cNombre.setCellValueFactory((instructor)->{
            String nombre=instructor.getValue().getNombre()+" "+instructor.getValue().getApellido1()+" "+instructor.getValue().getApellido2();
            return new SimpleStringProperty(nombre);
        });
        cCurso.setCellValueFactory((instructor)->{
            String curso="";
            if(!instructor.getValue().getCursoInstructor().isEmpty()){
                for(InstructorCurso c:instructor.getValue().getCursoInstructor()){
                    curso+=c.getCursoId().getNombre()+", ";
                }
                curso=curso.substring(0,curso.lastIndexOf(","));
                return new SimpleStringProperty(curso);
            }else{
                curso="Sin Curso";
                return new SimpleStringProperty(curso);
            }

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
            instructorSeleccionado=t1;
        });
        instructores.addAll((new PersonalIIPDAOImplement().getAllInstructores()));

        tabla.setItems(instructores);

    }

    @javafx.fxml.FXML
    public void borrar(ActionEvent actionEvent) {
        if(instructorSeleccionado!=null&&instructorSeleccionado.getInstructor()!=0L){
            try {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("INFORMACIÓN!");
                alerta.setHeaderText("Borrado de Instructor");
                alerta.setContentText("¿Seguro deseas borrar al Instructor/a?");
                Optional<ButtonType> tipo = alerta.showAndWait();
                if (tipo.get() == ButtonType.OK) {
                    (new PersonalIIPDAOImplement()).borrar(instructorSeleccionado);
                    Alert alerta2 = new Alert(Alert.AlertType.CONFIRMATION);
                    alerta2.setTitle("OK!");
                    alerta2.setHeaderText("Borrado Con Éxito");
                    alerta2.setContentText("El instructor con nombre " + instructorSeleccionado.getNombre() + " y\n" +
                            "DNI: " + instructorSeleccionado.getDni() + " Ha sido BORRADO");
                    alerta2.showAndWait();
                    tabla.getItems().remove(instructorSeleccionado);
                }
            }catch (Exception e){

            }

        }else if(instructorSeleccionado==null){
            Alert alerta=new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText("Instructor No Seleccionado");
            alerta.setContentText("Por favor seleccione un instructor de la tabla y luego pulsa borrar");
            alerta.showAndWait();
        }else{
            Alert alerta=new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText("Sin Permisos");
            alerta.setContentText("Lo sentimos pero no dispones de permisos suficientes para borrar a esta persona");
            alerta.showAndWait();
        }
    }


    @javafx.fxml.FXML
    public void filtro(Event event) {
        String filtro=textBuscar.getText();
        if(filtro.isEmpty()&&comboCurso.getValue()!=null){
            tabla.setItems(filtroCurso);

        }else if(filtro.isEmpty()&&comboCurso.getValue()==null){
            tabla.setItems(instructores);
        }

        else{
          filtroInstructores.clear();
            for(PersonalIIP a:instructores){
                String porBusqueda= a.getNombre().toLowerCase()+" "+a.getApellido1().toLowerCase()+" "+a.getApellido2().toLowerCase()+" "+a.getDni().toLowerCase()
                        +" "+a.getTelefono().toLowerCase()+" "+a.getCorreo().toLowerCase();
                if(porBusqueda.contains(filtro.toLowerCase())){
                    filtroInstructores.add(a);
                }
            }
            tabla.setItems(filtroInstructores);
        }
    }

    @javafx.fxml.FXML
    public void quitar(ActionEvent actionEvent) {
        if(comboCurso.getValue()!=null){
            instructores.clear();
            instructores.addAll((new PersonalIIPDAOImplement().getAllInstructores()));
            tabla.setItems(instructores);
            comboCurso.getSelectionModel().select(null);
        }

    }

}
