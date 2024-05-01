package com.example.ininprotec;

import Util.Utilidad;
import clase.Curso;
import clase.InstructorCurso;
import clase.Modulo;
import clase.PersonalIIP;
import error.CursoSinNombre;
import error.FaltaDeCampoCurso;
import implement.CursoDAOImplement;
import implement.InstructorCursoDAOImplement;
import implement.PersonalIIPDAOImplement;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
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
    @javafx.fxml.FXML
    private ImageView imagenFlecha;
    @javafx.fxml.FXML
    private Button botonM1;
    @javafx.fxml.FXML
    private VBox vBoxM2;
    @javafx.fxml.FXML
    private Button botonM2;
    @javafx.fxml.FXML
    private Button botonP2;
    @javafx.fxml.FXML
    private VBox vBoxM3;
    @javafx.fxml.FXML
    private Button botonM3;
    @javafx.fxml.FXML
    private Button botonP3;
    @javafx.fxml.FXML
    private VBox vBoxM4;
    @javafx.fxml.FXML
    private Button botonM4;
    @javafx.fxml.FXML
    private Button botonP4;
    @javafx.fxml.FXML
    private VBox vBoxM5;
    @javafx.fxml.FXML
    private Button botonM5;
    @javafx.fxml.FXML
    private Button botonP5;
    @javafx.fxml.FXML
    private VBox vBoxM6;
    @javafx.fxml.FXML
    private Button botonM6;
    @javafx.fxml.FXML
    private Button botonP6;
    @javafx.fxml.FXML
    private VBox vBoxM7;
    @javafx.fxml.FXML
    private Button botonP7;

    private List<Modulo>modulosBorrados=new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image imagenFlechia=new Image(CreacionCursoController.class.getClassLoader().getResource("imagenes/flechita.png").toExternalForm());
        imagenFlecha.setImage(imagenFlechia);
        imagenFlecha.setOnMouseClicked(mouseEvent -> {
            Utilidad.setCurso(null);
            HelloApplication.cambioVentana("principal-view.fxml");
        });


        vBoxM2.setVisible(false);
        vBoxM3.setVisible(false);
        vBoxM4.setVisible(false);
        vBoxM5.setVisible(false);
        vBoxM6.setVisible(false);
        vBoxM7.setVisible(false);

        botonM1.setOnAction(actionEvent -> {
            vBoxM2.setVisible(true);

        });
        botonM2.setOnAction(actionEvent -> {
            vBoxM3.setVisible(true);

        });
        botonM3.setOnAction(actionEvent -> {
            vBoxM4.setVisible(true);

        });
        botonM4.setOnAction(actionEvent -> {
            vBoxM5.setVisible(true);

        });
        botonM5.setOnAction(actionEvent -> {
            vBoxM6.setVisible(true);

        });
        botonM6.setOnAction(actionEvent -> {
            vBoxM7.setVisible(true);

        });
        botonP2.setOnAction(actionEvent -> {
            if(!vBoxM3.isVisible()&&!vBoxM4.isVisible()&&!vBoxM5.isVisible()&&!vBoxM6.isVisible()&&!vBoxM7.isVisible()){
                textModulo2.clear();
                comboInstructor2.getSelectionModel().select(null);
                vBoxM2.setVisible(false);
                if(Utilidad.getCurso()!=null&&Utilidad.getCurso().getModulos().size()-1>=1){
                    textModulo2.setText(Utilidad.getCurso().getModulos().get(1).getNombre());
                    for(int i=0;i<Utilidad.getCurso().getModulos().size();i++){
                        if(Utilidad.getCurso().getModulos().get(i).getNombre().equals(textModulo2.getText())){
                            modulosBorrados.add(Utilidad.getCurso().getModulos().get(i));
                        }
                    }
                    textModulo2.clear();
                    System.out.println(modulosBorrados);
                    System.out.println(Utilidad.getCurso());
                }
            }else{
                Alert alerta=new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("ERROR");
                alerta.setHeaderText("Error de ELIMINADO");
                alerta.setContentText("Por favro elimine primero los modulos posteriores para poder eliminar este");
                alerta.showAndWait();
            }

        });
        botonP3.setOnAction(actionEvent -> {
            if(!vBoxM4.isVisible()&&!vBoxM5.isVisible()&&!vBoxM6.isVisible()&&!vBoxM7.isVisible()){
                textModulo3.clear();
                comboInstructor3.getSelectionModel().select(null);
                vBoxM3.setVisible(false);
                if(Utilidad.getCurso()!=null&&Utilidad.getCurso().getModulos().size()-1>=2){
                    textModulo3.setText(Utilidad.getCurso().getModulos().get(2).getNombre());
                    for(int i=0;i<Utilidad.getCurso().getModulos().size();i++){
                        if(Utilidad.getCurso().getModulos().get(i).getNombre().equals(textModulo3.getText())){
                            modulosBorrados.add(Utilidad.getCurso().getModulos().get(i));
                        }
                    }
                    textModulo3.clear();
                }
            }else{
                Alert alerta=new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("ERROR");
                alerta.setHeaderText("Error de ELIMINADO");
                alerta.setContentText("Por favro elimine primero los modulos posteriores para poder eliminar este");
                alerta.showAndWait();
            }

        });
        botonP4.setOnAction(actionEvent -> {
            if(!vBoxM5.isVisible()&&!vBoxM6.isVisible()&&!vBoxM7.isVisible()){
                textModulo4.clear();
                comboInstructor4.getSelectionModel().select(null);
                vBoxM4.setVisible(false);
                if(Utilidad.getCurso()!=null&&Utilidad.getCurso().getModulos().size()-1>=3){
                    textModulo4.setText(Utilidad.getCurso().getModulos().get(3).getNombre());
                    for(int i=0;i<Utilidad.getCurso().getModulos().size();i++){
                        if(Utilidad.getCurso().getModulos().get(i).getNombre().equals(textModulo4.getText())){
                            modulosBorrados.add(Utilidad.getCurso().getModulos().get(i));
                        }
                    }
                    textModulo4.clear();
                }
            }else{
                Alert alerta=new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("ERROR");
                alerta.setHeaderText("Error de ELIMINADO");
                alerta.setContentText("Por favro elimine primero los modulos posteriores para poder eliminar este");
                alerta.showAndWait();
            }

        });
        botonP5.setOnAction(actionEvent -> {
            if(!vBoxM6.isVisible()&&!vBoxM7.isVisible()){
                textModulo5.clear();
                comboInstructor5.getSelectionModel().select(null);
                vBoxM5.setVisible(false);
                if(Utilidad.getCurso()!=null&&Utilidad.getCurso().getModulos().size()-1>=4){
                    textModulo5.setText(Utilidad.getCurso().getModulos().get(4).getNombre());
                    for(int i=0;i<Utilidad.getCurso().getModulos().size();i++){
                        if(Utilidad.getCurso().getModulos().get(i).getNombre().equals(textModulo5.getText())){
                            modulosBorrados.add(Utilidad.getCurso().getModulos().get(i));
                        }
                    }
                    textModulo5.clear();
                }
            }else{
                Alert alerta=new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("ERROR");
                alerta.setHeaderText("Error de ELIMINADO");
                alerta.setContentText("Por favro elimine primero los modulos posteriores para poder eliminar este");
                alerta.showAndWait();
            }

        });
        botonP6.setOnAction(actionEvent -> {
            if(!vBoxM7.isVisible()){
                textModulo6.clear();
                comboInstructor6.getSelectionModel().select(null);
                vBoxM6.setVisible(false);
                if(Utilidad.getCurso()!=null&&Utilidad.getCurso().getModulos().size()-1>=5){
                    textModulo6.setText(Utilidad.getCurso().getModulos().get(5).getNombre());
                    for(int i=0;i<Utilidad.getCurso().getModulos().size();i++){
                        if(Utilidad.getCurso().getModulos().get(i).getNombre().equals(textModulo6.getText())){
                            modulosBorrados.add(Utilidad.getCurso().getModulos().get(i));
                        }
                    }
                    textModulo6.clear();
                }
            }else{
                Alert alerta=new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("ERROR");
                alerta.setHeaderText("Error de ELIMINADO");
                alerta.setContentText("Por favro elimine primero los modulos posteriores para poder eliminar este");
                alerta.showAndWait();
            }

        });
        botonP7.setOnAction(actionEvent -> {
                textModulo7.clear();
                comboInstructor7.getSelectionModel().select(null);
                vBoxM7.setVisible(false);
            if(Utilidad.getCurso()!=null&&Utilidad.getCurso().getModulos().size()-1>=6){
                textModulo7.setText(Utilidad.getCurso().getModulos().get(6).getNombre());
                for(int i=0;i<Utilidad.getCurso().getModulos().size();i++){
                    if(Utilidad.getCurso().getModulos().get(i).getNombre().equals(textModulo7.getText())){
                        modulosBorrados.add(Utilidad.getCurso().getModulos().get(i));
                    }
                }
                textModulo7.clear();
            }

        });

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
        List<PersonalIIP>lista=dao.getAllInstructores();
        comboInstructor.getItems().addAll(lista);
        comboInstructor2.getItems().addAll(lista);
        comboInstructor3.getItems().addAll(lista);
        comboInstructor4.getItems().addAll(lista);
        comboInstructor5.getItems().addAll(lista);
        comboInstructor6.getItems().addAll(lista);
        comboInstructor7.getItems().addAll(lista);
        vBoxM2.setVisible(false);
        vBoxM3.setVisible(false);
        vBoxM4.setVisible(false);
        vBoxM5.setVisible(false);
        vBoxM6.setVisible(false);
        vBoxM7.setVisible(false);
        if(Utilidad.getCurso()!=null){
            switch (Utilidad.getCurso().getModulos().size()){
                case 1:
                    vBoxM2.setVisible(false);
                    vBoxM3.setVisible(false);
                    vBoxM4.setVisible(false);
                    vBoxM5.setVisible(false);
                    vBoxM6.setVisible(false);
                    vBoxM7.setVisible(false);

                    break;
                case 2:
                    vBoxM2.setVisible(true);
                    vBoxM3.setVisible(false);
                    vBoxM4.setVisible(false);
                    vBoxM5.setVisible(false);
                    vBoxM6.setVisible(false);
                    vBoxM7.setVisible(false);

                    break;
                case 3:
                    vBoxM2.setVisible(true);
                    vBoxM3.setVisible(true);
                    vBoxM4.setVisible(false);
                    vBoxM5.setVisible(false);
                    vBoxM6.setVisible(false);
                    vBoxM7.setVisible(false);

                    break;
                case 4:
                    vBoxM2.setVisible(true);
                    vBoxM3.setVisible(true);
                    vBoxM4.setVisible(true);
                    vBoxM5.setVisible(false);
                    vBoxM6.setVisible(false);
                    vBoxM7.setVisible(false);
                    break;
                case 5:
                    vBoxM2.setVisible(true);
                    vBoxM3.setVisible(true);
                    vBoxM4.setVisible(true);
                    vBoxM5.setVisible(true);
                    vBoxM6.setVisible(false);
                    vBoxM7.setVisible(false);
                    break;
                case 6:
                    vBoxM2.setVisible(true);
                    vBoxM3.setVisible(true);
                    vBoxM4.setVisible(true);
                    vBoxM5.setVisible(true);
                    vBoxM6.setVisible(true);
                    vBoxM7.setVisible(false);
                    break;
                case 7:
                    vBoxM2.setVisible(true);
                    vBoxM3.setVisible(true);
                    vBoxM4.setVisible(true);
                    vBoxM5.setVisible(true);
                    vBoxM6.setVisible(true);
                    vBoxM7.setVisible(true);
                    break;
            }
            textNombreCurso.setText(Utilidad.getCurso().getNombre());
            Byte numeroModulos=(byte)(Utilidad.getCurso().getModulos().size()-1);
            if(0<=numeroModulos){
                textModulo1.setText(Utilidad.getCurso().getModulos().get(0).getNombre());
                comboInstructor.getSelectionModel().select(Utilidad.getCurso().getModulos().get(0).getInstructor());
            }
            if(1<=numeroModulos){
                textModulo2.setText(Utilidad.getCurso().getModulos().get(1).getNombre());
                comboInstructor2.getSelectionModel().select(Utilidad.getCurso().getModulos().get(1).getInstructor());
            }
            if(2<=numeroModulos){
                textModulo3.setText(Utilidad.getCurso().getModulos().get(2).getNombre());
                comboInstructor3.getSelectionModel().select(Utilidad.getCurso().getModulos().get(2).getInstructor());
            }
            if(3<=numeroModulos){
                textModulo4.setText(Utilidad.getCurso().getModulos().get(3).getNombre());
                comboInstructor4.getSelectionModel().select(Utilidad.getCurso().getModulos().get(3).getInstructor());
            }
            if(4<=numeroModulos){
                textModulo5.setText(Utilidad.getCurso().getModulos().get(4).getNombre());
                comboInstructor5.getSelectionModel().select(Utilidad.getCurso().getModulos().get(4).getInstructor());
            }
            if(5<=numeroModulos){
                textModulo6.setText(Utilidad.getCurso().getModulos().get(5).getNombre());
                comboInstructor6.getSelectionModel().select(Utilidad.getCurso().getModulos().get(5).getInstructor());
            }
            if(6<=numeroModulos){
                textModulo7.setText(Utilidad.getCurso().getModulos().get(6).getNombre());
                comboInstructor7.getSelectionModel().select(Utilidad.getCurso().getModulos().get(6).getInstructor());
            }


        }

    }

    @javafx.fxml.FXML
    public void guardar(ActionEvent actionEvent) {
        try{
        if(Utilidad.getCurso()==null){
            HashSet<PersonalIIP>instructoresRep=new HashSet<>();
            Curso curso=new Curso();
            List<Modulo>modulos=new ArrayList<>();
            Modulo modulo1=new Modulo();
            Modulo modulo2=new Modulo();
            Modulo modulo3=new Modulo();
            Modulo modulo4=new Modulo();
            Modulo modulo5=new Modulo();
            Modulo modulo6=new Modulo();
            Modulo modulo7=new Modulo();

                if(textNombreCurso.getText().isEmpty()){
                    throw new CursoSinNombre("Sin nombre de curso");
                }
                if(!textModulo1.getText().isEmpty()){
                    if(comboInstructor.getValue()!=null){
                        InstructorCurso instructorCurso=new InstructorCurso();
                        modulo1.setNombre(textModulo1.getText()+" ("+textNombreCurso.getText()+")");
                        modulo1.setCurso(curso);
                        modulo1.setInstructor(comboInstructor.getValue());
                        comboInstructor.getValue().getModulos().add(modulo1);
                        modulos.add(modulo1);
                        if(curso.getCursoInstructor()==null){
                            curso.setCursoInstructor(new ArrayList<>());
                        }
                        instructorCurso.setInstructorId(comboInstructor.getValue());
                        curso.getCursoInstructor().add(instructorCurso);
                        instructorCurso.setCursoId(curso);
                        if(comboInstructor.getValue().getCursoInstructor()==null){
                            comboInstructor.getValue().setCursoInstructor(new ArrayList<>());
                        }
                        comboInstructor.getValue().getCursoInstructor().add(instructorCurso);
                        instructoresRep.add(comboInstructor.getValue());
                    }else{
                        throw new FaltaDeCampoCurso("Campo Sin Rellenar");
                    }

                }else if(textModulo1.getText().isEmpty()&&comboInstructor.getValue()!=null){
                    throw new FaltaDeCampoCurso("Campo Sin Rellenar");
                }else if(textModulo1.getText().isEmpty()&&comboInstructor.getValue()!=null){
                    throw new FaltaDeCampoCurso("Campo Sin Rellenar");
                }
                if(!textModulo2.getText().isEmpty()){
                    if(comboInstructor2.getValue()!=null){
                        InstructorCurso instructorCurso=new InstructorCurso();
                        modulo2.setNombre(textModulo2.getText()+" ("+textNombreCurso.getText()+")");
                        modulo2.setCurso(curso);
                        modulo2.setInstructor(comboInstructor2.getValue());
                        comboInstructor2.getValue().getModulos().add(modulo2);
                        modulos.add(modulo2);
                        if(curso.getCursoInstructor()==null){
                            curso.setCursoInstructor(new ArrayList<>());
                        }
                        if(comboInstructor2.getValue().getCursoInstructor()==null){
                            comboInstructor2.getValue().setCursoInstructor(new ArrayList<>());
                        }
                        if(!instructoresRep.contains(comboInstructor2.getValue())){
                            instructorCurso.setInstructorId(comboInstructor2.getValue());
                            curso.getCursoInstructor().add(instructorCurso);
                            instructorCurso.setCursoId(curso);
                            comboInstructor2.getValue().getCursoInstructor().add(instructorCurso);
                            instructoresRep.add(comboInstructor2.getValue());
                        }


                    }else{
                        throw new FaltaDeCampoCurso("Campo Sin Rellenar");
                    }

                }else if(textModulo2.getText().isEmpty()&&comboInstructor2.getValue()!=null){
                    throw new FaltaDeCampoCurso("Campo Sin Rellenar");
                }
                if(!textModulo3.getText().isEmpty()){
                    if(comboInstructor3.getValue()!=null){
                        InstructorCurso instructorCurso=new InstructorCurso();
                        modulo3.setNombre(textModulo3.getText()+" ("+textNombreCurso.getText()+")");
                        modulo3.setCurso(curso);
                        modulo3.setInstructor(comboInstructor3.getValue());
                        comboInstructor3.getValue().getModulos().add(modulo3);
                        modulos.add(modulo3);
                        if(curso.getCursoInstructor()==null){
                            curso.setCursoInstructor(new ArrayList<>());
                        }

                        if(comboInstructor3.getValue().getCursoInstructor()==null){
                            comboInstructor3.getValue().setCursoInstructor(new ArrayList<>());
                        }
                        if(!instructoresRep.contains(comboInstructor3.getValue())){
                            instructorCurso.setInstructorId(comboInstructor3.getValue());
                            curso.getCursoInstructor().add(instructorCurso);
                            instructorCurso.setCursoId(curso);
                            comboInstructor3.getValue().getCursoInstructor().add(instructorCurso);
                            instructoresRep.add(comboInstructor3.getValue());
                        }
                    }else{
                        throw new FaltaDeCampoCurso("Campo Sin Rellenar");
                    }

                }else if(textModulo3.getText().isEmpty()&&comboInstructor3.getValue()!=null) {
                    throw new FaltaDeCampoCurso("Campo Sin Rellenar");
                }
                if(!textModulo4.getText().isEmpty()){
                    if(comboInstructor4.getValue()!=null){
                        InstructorCurso instructorCurso=new InstructorCurso();
                        modulo4.setNombre(textModulo4.getText()+" ("+textNombreCurso.getText()+")");
                        modulo4.setCurso(curso);
                        modulo4.setInstructor(comboInstructor4.getValue());
                        comboInstructor4.getValue().getModulos().add(modulo4);
                        modulos.add(modulo4);
                        if(curso.getCursoInstructor()==null){
                            curso.setCursoInstructor(new ArrayList<>());
                        }
                        instructorCurso.setInstructorId(comboInstructor4.getValue());
                        curso.getCursoInstructor().add(instructorCurso);
                        instructorCurso.setCursoId(curso);
                        if(comboInstructor4.getValue().getCursoInstructor()==null){
                            comboInstructor4.getValue().setCursoInstructor(new ArrayList<>());
                        }
                        if(!instructoresRep.contains(comboInstructor4.getValue())){
                            instructorCurso.setInstructorId(comboInstructor4.getValue());
                            curso.getCursoInstructor().add(instructorCurso);
                            instructorCurso.setCursoId(curso);
                            comboInstructor4.getValue().getCursoInstructor().add(instructorCurso);
                            instructoresRep.add(comboInstructor4.getValue());
                        }
                    }else{
                        throw new FaltaDeCampoCurso("Campo Sin Rellenar");
                    }

                }else if(textModulo4.getText().isEmpty()&&comboInstructor4.getValue()!=null){
                    throw new FaltaDeCampoCurso("Campo Sin Rellenar");

                }
                if(!textModulo5.getText().isEmpty()){
                    if(comboInstructor5.getValue()!=null){
                        InstructorCurso instructorCurso=new InstructorCurso();
                        modulo5.setNombre(textModulo5.getText()+" ("+textNombreCurso.getText()+")");
                        modulo5.setCurso(curso);
                        modulo5.setInstructor( comboInstructor5.getValue());
                        comboInstructor5.getValue().getModulos().add(modulo5);
                        modulos.add(modulo5);
                        if(curso.getCursoInstructor()==null){
                            curso.setCursoInstructor(new ArrayList<>());
                        }
                        instructorCurso.setInstructorId(comboInstructor5.getValue());
                        curso.getCursoInstructor().add(instructorCurso);
                        instructorCurso.setCursoId(curso);
                        if(comboInstructor5.getValue().getCursoInstructor()==null){
                            comboInstructor5.getValue().setCursoInstructor(new ArrayList<>());
                        }
                        if(!instructoresRep.contains(comboInstructor5.getValue())){
                            instructorCurso.setInstructorId(comboInstructor5.getValue());
                            curso.getCursoInstructor().add(instructorCurso);
                            instructorCurso.setCursoId(curso);
                            comboInstructor5.getValue().getCursoInstructor().add(instructorCurso);
                            instructoresRep.add(comboInstructor5.getValue());
                        }
                    }else{
                        throw new FaltaDeCampoCurso("Campo Sin Rellenar");
                    }


                }else if(textModulo5.getText().isEmpty()&&comboInstructor5.getValue()!=null){
                    throw new FaltaDeCampoCurso("Campo Sin Rellenar");

                }
                if(!textModulo6.getText().isEmpty()){
                    if(comboInstructor6.getValue()!=null){
                        InstructorCurso instructorCurso=new InstructorCurso();
                        modulo6.setNombre(textModulo6.getText()+" ("+textNombreCurso.getText()+")");
                        modulo6.setCurso(curso);
                        modulo6.setInstructor( comboInstructor6.getValue());
                        comboInstructor6.getValue().getModulos().add(modulo5);
                        modulos.add(modulo6);
                        if(curso.getCursoInstructor()==null){
                            curso.setCursoInstructor(new ArrayList<>());
                        }
                        instructorCurso.setInstructorId(comboInstructor6.getValue());
                        curso.getCursoInstructor().add(instructorCurso);
                        instructorCurso.setCursoId(curso);
                        if(comboInstructor6.getValue().getCursoInstructor()==null){
                            comboInstructor6.getValue().setCursoInstructor(new ArrayList<>());
                        }
                        if(!instructoresRep.contains(comboInstructor6.getValue())){
                            instructorCurso.setInstructorId(comboInstructor6.getValue());
                            curso.getCursoInstructor().add(instructorCurso);
                            instructorCurso.setCursoId(curso);
                            comboInstructor6.getValue().getCursoInstructor().add(instructorCurso);
                            instructoresRep.add(comboInstructor6.getValue());
                        }
                    }else{
                        throw new FaltaDeCampoCurso("Campo Sin Rellenar");
                    }


                }else if(textModulo6.getText().isEmpty()&&comboInstructor6.getValue()!=null){
                    throw new FaltaDeCampoCurso("Campo Sin Rellenar");

                }
                if(!textModulo7.getText().isEmpty()){
                    if(comboInstructor7.getValue()!=null){
                        InstructorCurso instructorCurso=new InstructorCurso();
                        modulo7.setNombre(textModulo7.getText()+" ("+textNombreCurso.getText()+")");
                        modulo7.setCurso(curso);
                        modulo7.setInstructor( comboInstructor7.getValue());
                        comboInstructor7.getValue().getModulos().add(modulo7);
                        modulos.add(modulo7);
                        if(curso.getCursoInstructor()==null){
                            curso.setCursoInstructor(new ArrayList<>());
                        }
                        instructorCurso.setInstructorId(comboInstructor7.getValue());
                        curso.getCursoInstructor().add(instructorCurso);
                        instructorCurso.setCursoId(curso);
                        if(comboInstructor7.getValue().getCursoInstructor()==null){
                            comboInstructor7.getValue().setCursoInstructor(new ArrayList<>());
                        }
                        if(!instructoresRep.contains(comboInstructor7.getValue())){
                            instructorCurso.setInstructorId(comboInstructor7.getValue());
                            curso.getCursoInstructor().add(instructorCurso);
                            instructorCurso.setCursoId(curso);
                            comboInstructor7.getValue().getCursoInstructor().add(instructorCurso);
                            instructoresRep.add(comboInstructor7.getValue());
                        }
                    }else{
                        throw new FaltaDeCampoCurso("Campo Sin Rellenar");
                    }

                } else if(textModulo7.getText().isEmpty()&&comboInstructor7.getValue()!=null){
                    throw new FaltaDeCampoCurso("Campo Sin Rellenar");

                }
                curso.setNombre(textNombreCurso.getText());
                curso.setModulos(modulos);
                CursoDAOImplement dao=new CursoDAOImplement();
                dao.subir(curso);

                Alert alerta=new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("OK!");
                alerta.setHeaderText("Curso Realizado con Éxito");
                alerta.setContentText("El curso "+curso.getNombre()+" ha sido creado.");
                alerta.showAndWait();
                HelloApplication.cambioVentana("principal-view.fxml");
        }else{

            List<Modulo>modulos=new ArrayList<>();
            Modulo modulo1=new Modulo();
            Modulo modulo2=new Modulo();
            Modulo modulo3=new Modulo();
            Modulo modulo4=new Modulo();
            Modulo modulo5=new Modulo();
            Modulo modulo6=new Modulo();
            Modulo modulo7=new Modulo();
            Utilidad.getCurso().setCursoInstructor(new ArrayList<>());
            Utilidad.getCurso().setModulos(new ArrayList<>());
            if(!textNombreCurso.getText().isEmpty()){
                Utilidad.getCurso().setNombre(textNombreCurso.getText());
            }else{
                throw new CursoSinNombre("Curso sin nombre");
            }
            if(!textModulo1.getText().isEmpty()){
                if(comboInstructor.getValue()!=null){
                    InstructorCurso instructorCurso=new InstructorCurso();
                    modulo1.setNombre((textModulo1.getText().contains("("))?textModulo1.getText():textModulo1.getText()+" ("+textNombreCurso.getText()+")");
                    modulo1.setCurso(Utilidad.getCurso());
                    modulo1.setInstructor(comboInstructor.getValue());
                    for(int i=0;i<Utilidad.getCurso().getModulos().size();i++){
                        comboInstructor.getValue().getModulos().remove(Utilidad.getCurso().getModulos().get(i));
                    }
                    comboInstructor.getValue().getModulos().add(modulo1);
                    modulos.add(modulo1);

                    if(comboInstructor.getValue().getCursoInstructor()==null){
                        comboInstructor.getValue().setCursoInstructor(new ArrayList<>());
                    }
                    if(!(new InstructorCursoDAOImplement().comprobacion(comboInstructor.getValue(),Utilidad.getCurso()))){
                        instructorCurso.setInstructorId(comboInstructor.getValue());
                        Utilidad.getCurso().getCursoInstructor().add(instructorCurso);
                        instructorCurso.setCursoId(Utilidad.getCurso());
                        comboInstructor.getValue().getCursoInstructor().add(instructorCurso);
                    }
                }else{
                    throw new FaltaDeCampoCurso("Campo Sin Rellenar");
                }

            }else if(textModulo1.getText().isEmpty()&&comboInstructor.getValue()!=null){
                throw new FaltaDeCampoCurso("Campo Sin Rellenar");
            }
            if(!textModulo2.getText().isEmpty()){
                if(comboInstructor2.getValue()!=null){
                    InstructorCurso instructorCurso=new InstructorCurso();
                    modulo2.setNombre((textModulo2.getText().contains("("))?textModulo2.getText():textModulo2.getText()+" ("+textNombreCurso.getText()+")");
                    modulo2.setCurso(Utilidad.getCurso());
                    modulo2.setInstructor(comboInstructor2.getValue());
                    for(int i=0;i<Utilidad.getCurso().getModulos().size();i++){
                        comboInstructor2.getValue().getModulos().remove(Utilidad.getCurso().getModulos().get(i));
                    }
                    comboInstructor2.getValue().getModulos().add(modulo2);
                    modulos.add(modulo2);
                    instructorCurso.setInstructorId(comboInstructor2.getValue());
                    Utilidad.getCurso().getCursoInstructor().add(instructorCurso);
                    instructorCurso.setCursoId(Utilidad.getCurso());
                    if(comboInstructor2.getValue().getCursoInstructor()==null){
                        comboInstructor2.getValue().setCursoInstructor(new ArrayList<>());
                    }
                    if(!(new InstructorCursoDAOImplement().comprobacion(comboInstructor2.getValue(),Utilidad.getCurso()))){
                        instructorCurso.setInstructorId(comboInstructor2.getValue());
                        Utilidad.getCurso().getCursoInstructor().add(instructorCurso);
                        instructorCurso.setCursoId(Utilidad.getCurso());
                        comboInstructor2.getValue().getCursoInstructor().add(instructorCurso);
                    }
                }else{
                    throw new FaltaDeCampoCurso("Campo Sin Rellenar");
                }

            }else if(textModulo2.getText().isEmpty()&&comboInstructor2.getValue()!=null){
                throw new FaltaDeCampoCurso("Campo Sin Rellenar");
            }
            if(!textModulo3.getText().isEmpty()){
                if(comboInstructor3.getValue()!=null){
                    InstructorCurso instructorCurso=new InstructorCurso();
                    modulo3.setNombre((textModulo3.getText().contains("("))?textModulo3.getText():textModulo3.getText()+" ("+textNombreCurso.getText()+")");
                    modulo3.setCurso(Utilidad.getCurso());
                    modulo3.setInstructor(comboInstructor3.getValue());
                    for(int i=0;i<Utilidad.getCurso().getModulos().size();i++){
                        comboInstructor3.getValue().getModulos().remove(Utilidad.getCurso().getModulos().get(i));
                    }
                    comboInstructor3.getValue().getModulos().add(modulo3);
                    modulos.add(modulo3);
                    instructorCurso.setInstructorId(comboInstructor3.getValue());
                    Utilidad.getCurso().getCursoInstructor().add(instructorCurso);
                    instructorCurso.setCursoId(Utilidad.getCurso());
                    if(comboInstructor3.getValue().getCursoInstructor()==null){
                        comboInstructor3.getValue().setCursoInstructor(new ArrayList<>());
                    }
                    if(!(new InstructorCursoDAOImplement().comprobacion(comboInstructor3.getValue(),Utilidad.getCurso()))){
                        instructorCurso.setInstructorId(comboInstructor3.getValue());
                        Utilidad.getCurso().getCursoInstructor().add(instructorCurso);
                        instructorCurso.setCursoId(Utilidad.getCurso());
                        comboInstructor3.getValue().getCursoInstructor().add(instructorCurso);
                    }
                }else{
                    throw new FaltaDeCampoCurso("Campo Sin Rellenar");
                }

            }else if(textModulo3.getText().isEmpty()&&comboInstructor3.getValue()!=null) {
                throw new FaltaDeCampoCurso("Campo Sin Rellenar");
            }
            if(!textModulo4.getText().isEmpty()){
                if(comboInstructor4.getValue()!=null){
                    InstructorCurso instructorCurso=new InstructorCurso();
                    modulo4.setNombre((textModulo4.getText().contains("("))?textModulo4.getText():textModulo4.getText()+" ("+textNombreCurso.getText()+")");
                    modulo4.setCurso(Utilidad.getCurso());
                    modulo4.setInstructor(comboInstructor4.getValue());
                    for(int i=0;i<Utilidad.getCurso().getModulos().size();i++){
                        comboInstructor4.getValue().getModulos().remove(Utilidad.getCurso().getModulos().get(i));
                    }
                    comboInstructor4.getValue().getModulos().add(modulo4);
                    modulos.add(modulo4);
                    instructorCurso.setInstructorId(comboInstructor4.getValue());
                    Utilidad.getCurso().getCursoInstructor().add(instructorCurso);
                    instructorCurso.setCursoId(Utilidad.getCurso());
                    if(comboInstructor4.getValue().getCursoInstructor()==null){
                        comboInstructor4.getValue().setCursoInstructor(new ArrayList<>());
                    }
                    if(!(new InstructorCursoDAOImplement().comprobacion(comboInstructor4.getValue(),Utilidad.getCurso()))){
                        instructorCurso.setInstructorId(comboInstructor4.getValue());
                        Utilidad.getCurso().getCursoInstructor().add(instructorCurso);
                        instructorCurso.setCursoId(Utilidad.getCurso());
                        comboInstructor4.getValue().getCursoInstructor().add(instructorCurso);
                    }
                }else{
                    throw new FaltaDeCampoCurso("Campo Sin Rellenar");
                }

            }else if(textModulo4.getText().isEmpty()&&comboInstructor4.getValue()!=null){
                throw new FaltaDeCampoCurso("Campo Sin Rellenar");
            }
            if(!textModulo5.getText().isEmpty()){
                if(comboInstructor5.getValue()!=null){
                    InstructorCurso instructorCurso=new InstructorCurso();
                    modulo5.setNombre((textModulo5.getText().contains("("))?textModulo5.getText():textModulo5.getText()+" ("+textNombreCurso.getText()+")");
                    modulo5.setCurso(Utilidad.getCurso());

                    modulo5.setInstructor( comboInstructor5.getValue());
                    for(int i=0;i<Utilidad.getCurso().getModulos().size();i++){
                        comboInstructor5.getValue().getModulos().remove(Utilidad.getCurso().getModulos().get(i));
                    }
                    comboInstructor5.getValue().getModulos().add(modulo5);
                    modulos.add(modulo5);
                    instructorCurso.setInstructorId(comboInstructor5.getValue());
                    Utilidad.getCurso().getCursoInstructor().add(instructorCurso);
                    instructorCurso.setCursoId(Utilidad.getCurso());
                    if(comboInstructor5.getValue().getCursoInstructor()==null){
                        comboInstructor5.getValue().setCursoInstructor(new ArrayList<>());
                    }
                    if(!(new InstructorCursoDAOImplement().comprobacion(comboInstructor5.getValue(),Utilidad.getCurso()))){
                        instructorCurso.setInstructorId(comboInstructor5.getValue());
                        Utilidad.getCurso().getCursoInstructor().add(instructorCurso);
                        instructorCurso.setCursoId(Utilidad.getCurso());
                        comboInstructor5.getValue().getCursoInstructor().add(instructorCurso);
                    }
                }else{
                    throw new FaltaDeCampoCurso("Campo Sin Rellenar");
                }


            }else if(textModulo5.getText().isEmpty()&&comboInstructor5.getValue()!=null){
                throw new FaltaDeCampoCurso("Campo Sin Rellenar");
            }
            if(!textModulo6.getText().isEmpty()){
                if(comboInstructor6.getValue()!=null){
                    InstructorCurso instructorCurso=new InstructorCurso();
                    modulo6.setNombre((textModulo6.getText().contains("("))?textModulo6.getText():textModulo6.getText()+" ("+textNombreCurso.getText()+")");
                    modulo6.setCurso(Utilidad.getCurso());
                    modulo6.setInstructor( comboInstructor6.getValue());
                    for(int i=0;i<Utilidad.getCurso().getModulos().size();i++){
                        comboInstructor6.getValue().getModulos().remove(Utilidad.getCurso().getModulos().get(i));
                    }
                    comboInstructor6.getValue().getModulos().add(modulo5);
                    modulos.add(modulo6);
                    instructorCurso.setInstructorId(comboInstructor6.getValue());
                    Utilidad.getCurso().getCursoInstructor().add(instructorCurso);
                    instructorCurso.setCursoId(Utilidad.getCurso());
                    if(comboInstructor6.getValue().getCursoInstructor()==null){
                        comboInstructor6.getValue().setCursoInstructor(new ArrayList<>());
                    }
                    if(!(new InstructorCursoDAOImplement().comprobacion(comboInstructor6.getValue(),Utilidad.getCurso()))){
                        instructorCurso.setInstructorId(comboInstructor6.getValue());
                        Utilidad.getCurso().getCursoInstructor().add(instructorCurso);
                        instructorCurso.setCursoId(Utilidad.getCurso());
                        comboInstructor6.getValue().getCursoInstructor().add(instructorCurso);
                    }
                }else{
                    throw new FaltaDeCampoCurso("Campo Sin Rellenar");
                }


            }else if(textModulo6.getText().isEmpty()&&comboInstructor6.getValue()!=null){
                throw new FaltaDeCampoCurso("Campo Sin Rellenar");
            }
            if(!textModulo7.getText().isEmpty()){
                if(comboInstructor7.getValue()!=null){
                    InstructorCurso instructorCurso=new InstructorCurso();
                    modulo7.setNombre((textModulo7.getText().contains("("))?textModulo7.getText():textModulo7.getText()+" ("+textNombreCurso.getText()+")");
                    modulo7.setCurso(Utilidad.getCurso());
                    modulo7.setInstructor( comboInstructor7.getValue());
                    for(int i=0;i<Utilidad.getCurso().getModulos().size();i++){
                        comboInstructor7.getValue().getModulos().remove(Utilidad.getCurso().getModulos().get(i));
                    }
                    comboInstructor7.getValue().getModulos().add(modulo7);
                    modulos.add(modulo7);
                    instructorCurso.setInstructorId(comboInstructor7.getValue());
                    Utilidad.getCurso().getCursoInstructor().add(instructorCurso);
                    instructorCurso.setCursoId(Utilidad.getCurso());
                    if(comboInstructor7.getValue().getCursoInstructor()==null){
                        comboInstructor7.getValue().setCursoInstructor(new ArrayList<>());
                    }
                    if(!(new InstructorCursoDAOImplement().comprobacion(comboInstructor7.getValue(),Utilidad.getCurso()))){
                        instructorCurso.setInstructorId(comboInstructor7.getValue());
                        Utilidad.getCurso().getCursoInstructor().add(instructorCurso);
                        instructorCurso.setCursoId(Utilidad.getCurso());
                        comboInstructor7.getValue().getCursoInstructor().add(instructorCurso);
                    }
                }else{
                    throw new FaltaDeCampoCurso("Campo Sin Rellenar");
                }


            } else if(textModulo7.getText().isEmpty()&&comboInstructor7.getValue()!=null){
                throw new FaltaDeCampoCurso("Campo Sin Rellenar");
            }

            Utilidad.getCurso().setNombre(textNombreCurso.getText());
            Utilidad.getCurso().setModulos(modulos);
            System.out.println("AQUIIIII "+Utilidad.getCurso());
            (new CursoDAOImplement()).editCurso(Utilidad.getCurso(),modulosBorrados);
            modulosBorrados.clear();
            Alert alerta=new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("OK!");
            alerta.setHeaderText("Curso Modificado con Éxito");
            alerta.setContentText("El curso ha sido modifiaco");
            alerta.showAndWait();

        }

        }catch (FaltaDeCampoCurso e){
            Alert alerta=new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText("Campo Sin Rellenar");
            alerta.setContentText("Por favor asegurese de rellenar el modulo con su respectivo instructor o viceversa.");
            alerta.showAndWait();
        }catch (CursoSinNombre c){
            Alert alerta=new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText("Campo Sin Rellenar");
            alerta.setContentText("Por favor asegurese de rellenar el nombre del curso.");
            alerta.showAndWait();
        }

    }



    @javafx.fxml.FXML
    public void botonM2(ActionEvent actionEvent) {
    }
}
