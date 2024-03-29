package com.example.ininprotec;

import clase.Curso;
import clase.Modulo;
import clase.PersonalIIP;
import implement.CursoDAOImplement;
import implement.PersonalIIPDAOImplement;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreacionCursoController implements Initializable {
    @javafx.fxml.FXML
    private TextField textNombreCurso;
    @javafx.fxml.FXML
    private ComboBox<PersonalIIP> comboInstructor;
    @javafx.fxml.FXML
    private Button botonGuardar;
    @javafx.fxml.FXML
    private Button botonCancelar;
    @javafx.fxml.FXML
    private TextField textModulo1;
    @javafx.fxml.FXML
    private TextField textModulo2;
    @javafx.fxml.FXML
    private TextField textModulo3;
    @javafx.fxml.FXML
    private TextField textModulo4;
    @javafx.fxml.FXML
    private TextField textModulo5;
    @javafx.fxml.FXML
    private ComboBox<PersonalIIP>  comboInstructor2;
    @javafx.fxml.FXML
    private ComboBox <PersonalIIP> comboInstructor3;
    @javafx.fxml.FXML
    private ComboBox<PersonalIIP>  comboInstructor4;
    @javafx.fxml.FXML
    private ComboBox<PersonalIIP>  comboInstructor5;
    @javafx.fxml.FXML
    private TextField textModulo6;
    @javafx.fxml.FXML
    private ComboBox <PersonalIIP> comboInstructor6;
    @javafx.fxml.FXML
    private TextField textModulo7;
    @javafx.fxml.FXML
    private ComboBox <PersonalIIP> comboInstructor7;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboInstructor.setConverter(new StringConverter<PersonalIIP>() {
            @Override
            public String toString(PersonalIIP personalIIP) {
                if(personalIIP!=null){
                    return personalIIP.getNombre();
                }else{
                    return "";
                }

            }

            @Override
            public PersonalIIP fromString(String s) {
                return null;
            }
        });
        comboInstructor2.setConverter(new StringConverter<PersonalIIP>() {
            @Override
            public String toString(PersonalIIP personalIIP) {
                if(personalIIP!=null){
                    return personalIIP.getNombre();
                }else{
                    return "";
                }

            }

            @Override
            public PersonalIIP fromString(String s) {
                return null;
            }
        });
        comboInstructor3.setConverter(new StringConverter<PersonalIIP>() {
            @Override
            public String toString(PersonalIIP personalIIP) {
                if(personalIIP!=null){
                    return personalIIP.getNombre();
                }else{
                    return "";
                }

            }

            @Override
            public PersonalIIP fromString(String s) {
                return null;
            }
        });
        comboInstructor4.setConverter(new StringConverter<PersonalIIP>() {
            @Override
            public String toString(PersonalIIP personalIIP) {
                if(personalIIP!=null){
                    return personalIIP.getNombre();
                }else{
                    return "";
                }

            }

            @Override
            public PersonalIIP fromString(String s) {
                return null;
            }
        });
        comboInstructor5.setConverter(new StringConverter<PersonalIIP>() {
            @Override
            public String toString(PersonalIIP personalIIP) {
                if(personalIIP!=null){
                    return personalIIP.getNombre();
                }else{
                    return "";
                }

            }

            @Override
            public PersonalIIP fromString(String s) {
                return null;
            }
        });
        comboInstructor6.setConverter(new StringConverter<PersonalIIP>() {
            @Override
            public String toString(PersonalIIP personalIIP) {
                if(personalIIP!=null){
                    return personalIIP.getNombre();
                }else{
                    return "";
                }

            }

            @Override
            public PersonalIIP fromString(String s) {
                return null;
            }
        });
        comboInstructor7.setConverter(new StringConverter<PersonalIIP>() {
            @Override
            public String toString(PersonalIIP personalIIP) {
                if(personalIIP!=null){
                    return personalIIP.getNombre();
                }else{
                    return "";
                }

            }

            @Override
            public PersonalIIP fromString(String s) {
                return null;
            }
        });
        PersonalIIPDAOImplement dao=new PersonalIIPDAOImplement();
        List<PersonalIIP>lista=dao.getAll();
        comboInstructor.getItems().addAll(lista);
        comboInstructor2.getItems().addAll(lista);
        comboInstructor3.getItems().addAll(lista);
        comboInstructor4.getItems().addAll(lista);
        comboInstructor5.getItems().addAll(lista);
        comboInstructor6.getItems().addAll(lista);
        comboInstructor7.getItems().addAll(lista);
        System.out.println(lista);

    }

    @javafx.fxml.FXML
    public void guardar(ActionEvent actionEvent) {
        Curso curso=new Curso();
        List<Modulo>modulos=new ArrayList<>();
        Modulo modulo1=new Modulo();
        Modulo modulo2=new Modulo();
        Modulo modulo3=new Modulo();
        Modulo modulo4=new Modulo();
        Modulo modulo5=new Modulo();
        Modulo modulo6=new Modulo();
        Modulo modulo7=new Modulo();
        if(!textModulo1.getText().isEmpty()){
            modulo1.setNombre(textModulo1.getText());

            modulo1.setCurso(curso);
            modulo1.setInstructor(comboInstructor.getValue());
            comboInstructor.getValue().getModulos().add(modulo1);
            modulos.add(modulo1);
       }
        if(!textModulo2.getText().isEmpty()){
            modulo2.setNombre(textModulo2.getText());

            modulo2.setCurso(curso);
            modulo2.setInstructor(comboInstructor2.getValue());
            comboInstructor2.getValue().getModulos().add(modulo2);
            modulos.add(modulo2);
        }
        if(!textModulo3.getText().isEmpty()){
            modulo3.setNombre(textModulo3.getText());

            modulo3.setCurso(curso);
            modulo3.setInstructor(comboInstructor3.getValue());
            comboInstructor3.getValue().getModulos().add(modulo3);
            modulos.add(modulo3);
        }
        if(!textModulo4.getText().isEmpty()){
            modulo4.setNombre(textModulo4.getText());

            modulo4.setCurso(curso);
            modulo4.setInstructor(comboInstructor4.getValue());
          comboInstructor4.getValue().getModulos().add(modulo4);
            modulos.add(modulo4);
        }
        if(!textModulo5.getText().isEmpty()){
            modulo5.setNombre(textModulo5.getText());

            modulo5.setCurso(curso);
            modulo5.setInstructor( comboInstructor5.getValue());
          comboInstructor5.getValue().getModulos().add(modulo5);
            modulos.add(modulo5);

        }
        if(!textModulo6.getText().isEmpty()){
            modulo6.setNombre(textModulo6.getText());

            modulo6.setCurso(curso);
            modulo6.setInstructor( comboInstructor6.getValue());
          comboInstructor6.getValue().getModulos().add(modulo5);
            modulos.add(modulo5);

        }
        if(!textModulo7.getText().isEmpty()){
            modulo7.setNombre(textModulo5.getText());

            modulo7.setCurso(curso);
            modulo7.setInstructor( comboInstructor7.getValue());
            comboInstructor7.getValue().getModulos().add(modulo5);
            modulos.add(modulo5);

        }
        curso.setNombre(textNombreCurso.getText());
        curso.setModulos(modulos);
        CursoDAOImplement dao=new CursoDAOImplement();
        dao.subir(curso);

    }

    @javafx.fxml.FXML
    public void botonCancelar(ActionEvent actionEvent) {

    }


}
