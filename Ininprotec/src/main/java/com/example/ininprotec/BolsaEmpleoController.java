package com.example.ininprotec;

import Util.Utilidad;
import clase.AlumnoCurso;
import clase.PersonalBolsa;
import implement.PersonalBolsaDAOImplement;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class BolsaEmpleoController implements Initializable {
    @javafx.fxml.FXML
    private TextField textBuscador;
    @javafx.fxml.FXML
    private TableView <PersonalBolsa>tabla;
    @javafx.fxml.FXML
    private TableColumn <PersonalBolsa,String>cPosicion;
    @javafx.fxml.FXML
    private TableColumn <PersonalBolsa,String>cNombre;
    @javafx.fxml.FXML
    private TableColumn <PersonalBolsa,String>cDNI;
    @javafx.fxml.FXML
    private TableColumn<PersonalBolsa,String> cCorreo;
    @javafx.fxml.FXML
    private TableColumn<PersonalBolsa,String> cSexo;
    @javafx.fxml.FXML
    private TableColumn <PersonalBolsa,String>cFormacion;
    @javafx.fxml.FXML
    private TableColumn<PersonalBolsa,String> cIdioma;
    @javafx.fxml.FXML
    private TableColumn <PersonalBolsa,String>cNota;
    private ObservableList<PersonalBolsa> obs= FXCollections.observableArrayList();
    private ObservableList<PersonalBolsa>obsFiltro=FXCollections.observableArrayList();
    @javafx.fxml.FXML
    private ImageView imagenFlecha;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image imagenFlechia=new Image(BolsaEmpleoController.class.getClassLoader().getResource("imagenes/flechita.png").toExternalForm());
        imagenFlecha.setImage(imagenFlechia);
        imagenFlecha.setOnMouseClicked(mouseEvent -> {
            HelloApplication.cambioVentana("principal-view.fxml");
        });
        List<PersonalBolsa> alumnos=(new PersonalBolsaDAOImplement().getAllBolsa());
        List<PersonalBolsa>copiaSinNotaCurso=new ArrayList<>();
        for(PersonalBolsa alumno:alumnos){
            Iterator<AlumnoCurso> it=alumno.getCursosAlumnos().iterator();
            while (it.hasNext()){
                AlumnoCurso alumnoCurso=it.next();
                if(alumnoCurso.getNotaCurso()==null){
                    it.remove();
                }
            }
            copiaSinNotaCurso.add(alumno);
        }
        Integer contador=0;

        copiaSinNotaCurso.sort(PersonalBolsa::compareTo);
        for(PersonalBolsa alumno:copiaSinNotaCurso){
            contador++;
            alumno.setPosicion(contador);
            (new PersonalBolsaDAOImplement()).modPosicion(alumno);
        }
        System.out.println(copiaSinNotaCurso);

        cPosicion.setCellValueFactory(alumno->{
            String posicion=String.valueOf(alumno.getValue().getPosicion()) ;
            return new SimpleStringProperty(posicion);
        });
        cNombre.setCellValueFactory(alumno->{
            String nombre=alumno.getValue().getNombre()+" "+alumno.getValue().getApellido1()+" "+alumno.getValue().getApellido2();
            return new SimpleStringProperty(nombre);
        });
        cDNI.setCellValueFactory(alumno->{
            String nombre=alumno.getValue().getDni();
            return new SimpleStringProperty(nombre);
        });
        cCorreo.setCellValueFactory(alumno->{
            String nombre=alumno.getValue().getCorreo();
            return new SimpleStringProperty(nombre);
        });
        cSexo.setCellValueFactory(alumno->{
            String sexo=null;
            if(alumno.getValue().getSexo()=='m'){
                sexo="Mujer";
            }else{
                sexo="Hombre";
            }
            String nombre=sexo;
            return new SimpleStringProperty(nombre);
        });
        cFormacion.setCellValueFactory(alumno->{
            String nombre=alumno.getValue().getTitulacion();
            return new SimpleStringProperty(nombre);
        });
        cIdioma.setCellValueFactory(alumno->{
            String nombre=alumno.getValue().getIdioma();
            return new SimpleStringProperty(nombre);
        });
        cNota.setCellValueFactory(alumno->{
            String nombre= String.valueOf(alumno.getValue().getNotaFinal()) ;
            return new SimpleStringProperty(nombre);
        });
        obs.addAll(copiaSinNotaCurso);
        tabla.setItems(obs);

    }

    @javafx.fxml.FXML
    public void filtrar(Event event) {
        String filtro=textBuscador.getText().toLowerCase();
         filtro= filtro.replace("á","a");
        filtro= filtro.replace("é","e");
        filtro= filtro.replace("í","i");
        filtro= filtro.replace("ó","o");
        filtro= filtro.replace("ú","u");
        if(filtro.isEmpty()){
            tabla.setItems(obs);
        }else{
            obsFiltro.clear();
            for(PersonalBolsa alumno:obs){
                String busqueda=""+alumno.getPosicion()+" "+alumno.getNombre()+" "+alumno.getApellido1()+" "+
                        alumno.getApellido2()+" "+alumno.getDni()+" "+(alumno.getSexo()=='h'?"hombre":"mujer")+
                        " "+alumno.getTitulacion()+" "+alumno.getIdioma()+" "+alumno.getNotaFinal();
                busqueda=busqueda.toLowerCase();
                busqueda= busqueda.replace("á","a");
                busqueda= busqueda.replace("é","e");
                busqueda= busqueda.replace("í","i");
                busqueda= busqueda.replace("ó","o");
                busqueda= busqueda.replace("ú","u");
                if(busqueda.contains(filtro)){
                    obsFiltro.add(alumno);
                }
            }
            tabla.setItems(obsFiltro);

        }

    }
}
