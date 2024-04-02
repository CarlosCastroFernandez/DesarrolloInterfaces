package com.example.ininprotec;

import Util.Utilidad;
import clase.PersonalBolsa;
import implement.PersonalBolsaDAOImplement;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EntradaCursoController implements Initializable {
    @javafx.fxml.FXML
    private TableView<PersonalBolsa> tablaCurso;
    @javafx.fxml.FXML
    private TableColumn<PersonalBolsa, String> cModulo1;
    @javafx.fxml.FXML
    private TableColumn<PersonalBolsa, String> cModulo2;
    @javafx.fxml.FXML
    private TableColumn<PersonalBolsa, String> cModulo3;
    @javafx.fxml.FXML
    private TableColumn<PersonalBolsa, String> cModulo4;
    @javafx.fxml.FXML
    private TableColumn<PersonalBolsa, String> cModulo5;
    @javafx.fxml.FXML
    private Label labelTitutlo;
    @javafx.fxml.FXML
    private TextField textBuscador;
    @javafx.fxml.FXML
    private TableColumn<PersonalBolsa, String> cNombre;
    @javafx.fxml.FXML
    private TableColumn<PersonalBolsa, String> cModulo6;
    @javafx.fxml.FXML
    private TableColumn<PersonalBolsa, String> cModulo7;
    @javafx.fxml.FXML
    private Label labelM1;
    @javafx.fxml.FXML
    private Spinner spM1;
    @javafx.fxml.FXML
    private Label labelM2;
    @javafx.fxml.FXML
    private Spinner spM2;
    @javafx.fxml.FXML
    private Label labelM3;
    @javafx.fxml.FXML
    private Spinner spM3;
    @javafx.fxml.FXML
    private Label labelM4;
    @javafx.fxml.FXML
    private Spinner spM4;
    @javafx.fxml.FXML
    private Label labelM5;
    @javafx.fxml.FXML
    private Spinner spM5;
    @javafx.fxml.FXML
    private Label labelM6;
    @javafx.fxml.FXML
    private Spinner spM6;
    @javafx.fxml.FXML
    private Label labelM7;
    @javafx.fxml.FXML
    private Spinner spM7;
    @javafx.fxml.FXML
    private Label labelInfo;
    @javafx.fxml.FXML
    private Button botonGuardad;
    private PersonalBolsa personalElegido;
    @javafx.fxml.FXML
    private ImageView imagenActualizar;
    @javafx.fxml.FXML
    private ImageView imagenAddAlumno;
    private ObservableList<PersonalBolsa>alumnos;
    private ObservableList<PersonalBolsa>filtroAlumnos=FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image imagen=new Image(EntradaCursoController.class.getClassLoader().getResource("imagenes/recargar.png").toExternalForm());
        Image imagenAgregar=new Image(EntradaCursoController.class.getClassLoader().getResource("imagenes/agregar.png").toExternalForm());
        imagenAddAlumno.setImage(imagenAgregar);
        imagenActualizar.setImage(imagen);
        imagenActualizar.setOnMouseClicked(evento->{
            alumnos.clear();
            alumnos.addAll(new PersonalBolsaDAOImplement().getAllByCursoId(Utilidad.getCurso()));
        });
        switch (Utilidad.getCurso().getModulos().size()) {
            case 1:
                labelM1.setText(Utilidad.getCurso().getModulos().get(0).getNombre());
                spM1.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                cModulo1.setText(Utilidad.getCurso().getModulos().get(0).getNombre());
                labelM2.setVisible(false);
                labelM3.setVisible(false);
                labelM4.setVisible(false);
                labelM5.setVisible(false);
                labelM6.setVisible(false);
                labelM7.setVisible(false);
                spM2.setVisible(false);
                spM3.setVisible(false);
                spM4.setVisible(false);
                spM5.setVisible(false);
                spM6.setVisible(false);
                spM7.setVisible(false);
                cModulo2.setVisible(false);
                cModulo3.setVisible(false);
                cModulo4.setVisible(false);
                cModulo5.setVisible(false);
                cModulo6.setVisible(false);
                cModulo7.setVisible(false);
                cNombre.setCellValueFactory((alumno) -> {
                    String nombre = alumno.getValue().getNombre()+" "+alumno.getValue().getApellido1()+" "+alumno.getValue().getApellido2();
                    return new SimpleStringProperty(nombre);
                });
                cModulo1.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModulos().size(); i++) {
                        if (alumno.getValue().getModulos().get(i).getNombre().contains(cModulo1.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModulos().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota==null?"":nota));
                });
                break;
            case 2:
                labelM1.setText(Utilidad.getCurso().getModulos().get(0).getNombre());
                labelM2.setText(Utilidad.getCurso().getModulos().get(1).getNombre());
                spM1.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM2.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                labelM3.setVisible(false);
                labelM4.setVisible(false);
                labelM5.setVisible(false);
                labelM6.setVisible(false);
                labelM7.setVisible(false);
                spM3.setVisible(false);
                spM4.setVisible(false);
                spM5.setVisible(false);
                spM6.setVisible(false);
                spM7.setVisible(false);
                cModulo3.setVisible(false);
                cModulo4.setVisible(false);
                cModulo5.setVisible(false);
                cModulo6.setVisible(false);
                cModulo7.setVisible(false);
                cModulo1.setText(Utilidad.getCurso().getModulos().get(0).getNombre());
                cModulo2.setText(Utilidad.getCurso().getModulos().get(1).getNombre());
                cNombre.setCellValueFactory((alumno) -> {
                    String nombre = alumno.getValue().getNombre() + " " + alumno.getValue().getApellido1() + " " + alumno.getValue().getApellido2();
                    return new SimpleStringProperty(nombre);
                });
                cModulo1.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModulos().size(); i++) {
                        if (alumno.getValue().getModulos().get(i).getNombre().contains(cModulo1.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModulos().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota==null?"":nota));
                });
                cModulo2.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModulos().size(); i++) {
                        if (alumno.getValue().getModulos().get(i).getNombre().contains(cModulo2.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModulos().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota==null?"":nota));
                });
                break;
            case 3:
                labelM1.setText(Utilidad.getCurso().getModulos().get(0).getNombre());
                labelM2.setText(Utilidad.getCurso().getModulos().get(1).getNombre());
                labelM3.setText(Utilidad.getCurso().getModulos().get(2).getNombre());
                spM1.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM2.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM3.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                labelM4.setVisible(false);
                labelM5.setVisible(false);
                labelM6.setVisible(false);
                labelM7.setVisible(false);
                spM4.setVisible(false);
                spM5.setVisible(false);
                spM6.setVisible(false);
                spM7.setVisible(false);
                cModulo4.setVisible(false);
                cModulo5.setVisible(false);
                cModulo6.setVisible(false);
                cModulo7.setVisible(false);
                cModulo1.setText(Utilidad.getCurso().getModulos().get(0).getNombre());
                cModulo3.setText(Utilidad.getCurso().getModulos().get(2).getNombre());
                cModulo2.setText(Utilidad.getCurso().getModulos().get(1).getNombre());

                cNombre.setCellValueFactory((alumno) -> {
                    String nombre = alumno.getValue().getNombre() + " " + alumno.getValue().getApellido1() + " " + alumno.getValue().getApellido2();
                    return new SimpleStringProperty(nombre);
                });
                cModulo1.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModulos().size(); i++) {
                        if (alumno.getValue().getModulos().get(i).getNombre().contains(cModulo1.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModulos().get(posicion).getNotaModulo());

                    return new SimpleStringProperty((nota.equals("null")?"":nota));
                });
                cModulo2.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModulos().size(); i++) {
                        if (alumno.getValue().getModulos().get(i).getNombre().contains(cModulo2.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModulos().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null")?"":nota));
                });
                cModulo3.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModulos().size(); i++) {
                        if (alumno.getValue().getModulos().get(i).getNombre().contains(cModulo3.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModulos().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null")?"":nota));
                });
                break;
            case 4:
                labelM1.setText(Utilidad.getCurso().getModulos().get(0).getNombre());
                labelM2.setText(Utilidad.getCurso().getModulos().get(1).getNombre());
                labelM3.setText(Utilidad.getCurso().getModulos().get(2).getNombre());
                labelM4.setText(Utilidad.getCurso().getModulos().get(3).getNombre());
                spM4.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM1.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM2.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM3.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                labelM5.setVisible(false);
                labelM6.setVisible(false);
                labelM7.setVisible(false);
                spM5.setVisible(false);
                spM6.setVisible(false);
                spM7.setVisible(false);
                cModulo5.setVisible(false);
                cModulo6.setVisible(false);
                cModulo7.setVisible(false);
                cModulo1.setText(Utilidad.getCurso().getModulos().get(0).getNombre());
                cModulo2.setText(Utilidad.getCurso().getModulos().get(1).getNombre());
                cModulo3.setText(Utilidad.getCurso().getModulos().get(2).getNombre());
                cModulo4.setText(Utilidad.getCurso().getModulos().get(3).getNombre());
                cNombre.setCellValueFactory((alumno) -> {
                    String nombre = alumno.getValue().getNombre() + " " + alumno.getValue().getApellido1() + " " + alumno.getValue().getApellido2();
                    return new SimpleStringProperty(nombre);
                });
                cModulo1.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModulos().size(); i++) {
                        if (alumno.getValue().getModulos().get(i).getNombre().contains(cModulo1.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModulos().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null")?"":nota));
                });
                cModulo2.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModulos().size(); i++) {
                        if (alumno.getValue().getModulos().get(i).getNombre().contains(cModulo2.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModulos().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null")?"":nota));
                });
                cModulo3.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModulos().size(); i++) {
                        if (alumno.getValue().getModulos().get(i).getNombre().contains(cModulo3.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModulos().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null")?"":nota));
                });
                cModulo4.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModulos().size(); i++) {
                        if (alumno.getValue().getModulos().get(i).getNombre().contains(cModulo4.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModulos().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null")?"":nota));
                });
                break;
            case 5:
                labelM1.setText(Utilidad.getCurso().getModulos().get(0).getNombre());
                labelM2.setText(Utilidad.getCurso().getModulos().get(1).getNombre());
                labelM3.setText(Utilidad.getCurso().getModulos().get(2).getNombre());
                labelM4.setText(Utilidad.getCurso().getModulos().get(3).getNombre());
                labelM5.setText(Utilidad.getCurso().getModulos().get(4).getNombre());
                spM5.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM4.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM1.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM2.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM3.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                labelM6.setVisible(false);
                labelM7.setVisible(false);
                spM6.setVisible(false);
                spM7.setVisible(false);

                cModulo6.setVisible(false);
                cModulo7.setVisible(false);
                cModulo1.setText(Utilidad.getCurso().getModulos().get(0).getNombre());
                cModulo2.setText(Utilidad.getCurso().getModulos().get(1).getNombre());
                cModulo3.setText(Utilidad.getCurso().getModulos().get(2).getNombre());
                cModulo4.setText(Utilidad.getCurso().getModulos().get(3).getNombre());
                cModulo5.setText(Utilidad.getCurso().getModulos().get(4).getNombre());
                cNombre.setCellValueFactory((alumno) -> {
                    String nombre = alumno.getValue().getNombre() + " " + alumno.getValue().getApellido1() + " " + alumno.getValue().getApellido2();
                    return new SimpleStringProperty(nombre);
                });
                cModulo1.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModulos().size(); i++) {
                        if (alumno.getValue().getModulos().get(i).getNombre().contains(cModulo1.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModulos().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null")?"":nota));
                });
                cModulo2.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModulos().size(); i++) {
                        if (alumno.getValue().getModulos().get(i).getNombre().contains(cModulo2.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModulos().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null")?"":nota));
                });
                cModulo3.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModulos().size(); i++) {
                        if (alumno.getValue().getModulos().get(i).getNombre().contains(cModulo3.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModulos().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null")?"":nota));
                });
                cModulo4.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModulos().size(); i++) {
                        if (alumno.getValue().getModulos().get(i).getNombre().contains(cModulo4.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModulos().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null")?"":nota));
                });
                cModulo5.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModulos().size(); i++) {
                        if (alumno.getValue().getModulos().get(i).getNombre().contains(cModulo5.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModulos().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null")?"":nota));
                });
                break;
            case 6:
                labelM1.setText(Utilidad.getCurso().getModulos().get(0).getNombre());
                labelM2.setText(Utilidad.getCurso().getModulos().get(1).getNombre());
                labelM3.setText(Utilidad.getCurso().getModulos().get(2).getNombre());
                labelM4.setText(Utilidad.getCurso().getModulos().get(3).getNombre());
                labelM5.setText(Utilidad.getCurso().getModulos().get(4).getNombre());
                labelM6.setText(Utilidad.getCurso().getModulos().get(5).getNombre());
                spM6.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM5.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM4.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM1.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM2.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM3.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                labelM7.setVisible(false);
                spM7.setVisible(false);
                cModulo7.setVisible(false);
                cModulo1.setText(Utilidad.getCurso().getModulos().get(0).getNombre());
                cModulo2.setText(Utilidad.getCurso().getModulos().get(1).getNombre());
                cModulo3.setText(Utilidad.getCurso().getModulos().get(2).getNombre());
                cModulo4.setText(Utilidad.getCurso().getModulos().get(3).getNombre());
                cModulo5.setText(Utilidad.getCurso().getModulos().get(4).getNombre());
                cModulo6.setText(Utilidad.getCurso().getModulos().get(5).getNombre());
                cNombre.setCellValueFactory((alumno) -> {
                    String nombre = alumno.getValue().getNombre() + " " + alumno.getValue().getApellido1() + " " + alumno.getValue().getApellido2();
                    return new SimpleStringProperty(nombre);
                });
                cModulo1.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModulos().size(); i++) {
                        if (alumno.getValue().getModulos().get(i).getNombre().contains(cModulo1.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModulos().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null")?"":nota));
                });
                cModulo2.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModulos().size(); i++) {
                        if (alumno.getValue().getModulos().get(i).getNombre().contains(cModulo2.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModulos().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null")?"":nota));
                });
                cModulo3.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModulos().size(); i++) {
                        if (alumno.getValue().getModulos().get(i).getNombre().contains(cModulo3.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModulos().get(posicion).getNotaModulo());

                    return new SimpleStringProperty((nota.equals("null")?"":nota));
                });
                cModulo4.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModulos().size(); i++) {
                        if (alumno.getValue().getModulos().get(i).getNombre().contains(cModulo4.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModulos().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null")?"":nota));
                });
                cModulo5.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModulos().size(); i++) {
                        if (alumno.getValue().getModulos().get(i).getNombre().contains(cModulo5.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModulos().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null")?"":nota));
                });
                cModulo6.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModulos().size(); i++) {
                        if (alumno.getValue().getModulos().get(i).getNombre().contains(cModulo6.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModulos().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null")?"":nota));
                });
                break;
            case 7:
                labelM1.setText(Utilidad.getCurso().getModulos().get(0).getNombre());
                labelM2.setText(Utilidad.getCurso().getModulos().get(1).getNombre());
                labelM3.setText(Utilidad.getCurso().getModulos().get(2).getNombre());
                labelM4.setText(Utilidad.getCurso().getModulos().get(3).getNombre());
                labelM5.setText(Utilidad.getCurso().getModulos().get(4).getNombre());
                labelM6.setText(Utilidad.getCurso().getModulos().get(5).getNombre());
                labelM7.setText(Utilidad.getCurso().getModulos().get(6).getNombre());
                spM7.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM6.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM5.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM4.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM1.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM2.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                spM3.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10.0, 0.0, 0.1));
                cModulo1.setText(Utilidad.getCurso().getModulos().get(0).getNombre());
                cModulo2.setText(Utilidad.getCurso().getModulos().get(1).getNombre());
                cModulo3.setText(Utilidad.getCurso().getModulos().get(2).getNombre());
                cModulo4.setText(Utilidad.getCurso().getModulos().get(3).getNombre());
                cModulo5.setText(Utilidad.getCurso().getModulos().get(4).getNombre());
                cModulo6.setText(Utilidad.getCurso().getModulos().get(5).getNombre());
                cModulo7.setText(Utilidad.getCurso().getModulos().get(6).getNombre());
                cNombre.setCellValueFactory((alumno) -> {
                    String nombre = alumno.getValue().getNombre() + " " + alumno.getValue().getApellido1() + " " + alumno.getValue().getApellido2();
                    return new SimpleStringProperty(nombre);
                });
                cModulo1.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModulos().size(); i++) {
                        if (alumno.getValue().getModulos().get(i).getNombre().contains(cModulo1.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModulos().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null")?"":nota));
                });
                cModulo2.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModulos().size(); i++) {
                        if (alumno.getValue().getModulos().get(i).getNombre().contains(cModulo2.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModulos().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null")?"":nota));
                });
                cModulo3.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModulos().size(); i++) {
                        if (alumno.getValue().getModulos().get(i).getNombre().contains(cModulo3.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModulos().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null")?"":nota));
                });
                cModulo4.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModulos().size(); i++) {
                        if (alumno.getValue().getModulos().get(i).getNombre().contains(cModulo4.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModulos().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null")?"":nota));
                });
                cModulo5.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModulos().size(); i++) {
                        if (alumno.getValue().getModulos().get(i).getNombre().contains(cModulo5.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModulos().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null")?"":nota));
                });
                cModulo6.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModulos().size(); i++) {
                        if (alumno.getValue().getModulos().get(i).getNombre().contains(cModulo6.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModulos().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null")?"":nota));
                });
                cModulo7.setCellValueFactory((alumno) -> {
                    Integer posicion = 0;
                    for (int i = 0; i < alumno.getValue().getModulos().size(); i++) {
                        if (alumno.getValue().getModulos().get(i).getNombre().contains(cModulo7.getText())) {
                            posicion = i;
                        }
                    }
                    String nota = String.valueOf(alumno.getValue().getModulos().get(posicion).getNotaModulo());
                    return new SimpleStringProperty((nota.equals("null")?"":nota));
                });
                break;


        }
        alumnos=FXCollections.observableArrayList();
        alumnos.addAll(Utilidad.getCurso().getAlumnos());
        tablaCurso.setItems(alumnos);

        tablaCurso.getSelectionModel().selectedItemProperty().addListener((observableValue, personalBolsa, t1) -> {
           if(!tablaCurso.getItems().isEmpty()){
               personalElegido = t1;
               labelInfo.setText("Alumno Seleccionado: "+personalElegido.getNombre() + " " + personalElegido.getApellido1() + " " + personalElegido.getApellido2());

           }

        });
        tablaCurso.setRowFactory(tv -> {
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

    @javafx.fxml.FXML
    public void guardarNotas(ActionEvent actionEvent) {
        if (personalElegido != null) {
            switch (Utilidad.getCurso().getModulos().size()) {
                case 1:
                    for (int i = 0; i < personalElegido.getModulos().size(); i++) {
                        if (personalElegido.getModulos().get(i).getNombre().contains(labelM1.getText())) {
                            personalElegido.getModulos().get(i).setNotaModulo(Double.valueOf(spM1.getValue().toString()));
                        }

                    }
                    break;
                case 2:
                    for (int i = 0; i < personalElegido.getModulos().size(); i++) {
                        if (personalElegido.getModulos().get(i).getNombre().contains(labelM1.getText())) {
                            personalElegido.getModulos().get(i).setNotaModulo(Double.valueOf(spM1.getValue().toString()));
                        } else if (personalElegido.getModulos().get(i).getNombre().contains(labelM2.getText())) {
                            personalElegido.getModulos().get(i).setNotaModulo(Double.valueOf(spM2.getValue().toString()));
                        }

                    }
                    break;
                case 3:
                    for (int i = 0; i < personalElegido.getModulos().size(); i++) {
                        if (personalElegido.getModulos().get(i).getNombre().contains(labelM1.getText())) {
                            personalElegido.getModulos().get(i).setNotaModulo(Double.valueOf(spM1.getValue().toString()));
                        } else if (personalElegido.getModulos().get(i).getNombre().contains(labelM2.getText())) {
                            personalElegido.getModulos().get(i).setNotaModulo(Double.valueOf(spM2.getValue().toString()));
                        } else if (personalElegido.getModulos().get(i).getNombre().contains(labelM3.getText())) {
                            personalElegido.getModulos().get(i).setNotaModulo(Double.valueOf(spM3.getValue().toString()));
                        }

                    }
                    break;
                case 4:
                    for (int i = 0; i < personalElegido.getModulos().size(); i++) {
                        if (personalElegido.getModulos().get(i).getNombre().contains(labelM1.getText())) {
                            personalElegido.getModulos().get(i).setNotaModulo(Double.valueOf(spM1.getValue().toString()));
                        } else if (personalElegido.getModulos().get(i).getNombre().contains(labelM2.getText())) {
                            personalElegido.getModulos().get(i).setNotaModulo(Double.valueOf(spM2.getValue().toString()));
                        } else if (personalElegido.getModulos().get(i).getNombre().contains(labelM3.getText())) {
                            personalElegido.getModulos().get(i).setNotaModulo(Double.valueOf(spM3.getValue().toString()));
                        } else if (personalElegido.getModulos().get(i).getNombre().contains(labelM4.getText())) {
                            personalElegido.getModulos().get(i).setNotaModulo(Double.valueOf(spM4.getValue().toString()));

                        }
                    }
                    break;
                case 5:

                    for (int i = 0; i < personalElegido.getModulos().size(); i++) {
                        if (personalElegido.getModulos().get(i).getNombre().contains(labelM1.getText())) {
                            personalElegido.getModulos().get(i).setNotaModulo(Double.valueOf(spM1.getValue().toString()));
                        } else if (personalElegido.getModulos().get(i).getNombre().contains(labelM2.getText())) {
                            personalElegido.getModulos().get(i).setNotaModulo(Double.valueOf(spM2.getValue().toString()));
                        } else if (personalElegido.getModulos().get(i).getNombre().contains(labelM3.getText())) {
                            personalElegido.getModulos().get(i).setNotaModulo(Double.valueOf(spM3.getValue().toString()));
                        } else if (personalElegido.getModulos().get(i).getNombre().contains(labelM4.getText())) {
                            personalElegido.getModulos().get(i).setNotaModulo(Double.valueOf(spM4.getValue().toString()));

                        } else if (personalElegido.getModulos().get(i).getNombre().contains(labelM5.getText())) {
                            personalElegido.getModulos().get(i).setNotaModulo(Double.valueOf(spM5.getValue().toString()));

                        }
                    }
                    break;
                case 6:
                    for (int i = 0; i < personalElegido.getModulos().size(); i++) {
                        if (personalElegido.getModulos().get(i).getNombre().contains(labelM1.getText())) {
                            personalElegido.getModulos().get(i).setNotaModulo(Double.valueOf(spM1.getValue().toString()));
                        } else if (personalElegido.getModulos().get(i).getNombre().contains(labelM2.getText())) {
                            personalElegido.getModulos().get(i).setNotaModulo(Double.valueOf(spM2.getValue().toString()));
                        } else if (personalElegido.getModulos().get(i).getNombre().contains(labelM3.getText())) {
                            personalElegido.getModulos().get(i).setNotaModulo(Double.valueOf(spM3.getValue().toString()));
                        } else if (personalElegido.getModulos().get(i).getNombre().contains(labelM4.getText())) {
                            personalElegido.getModulos().get(i).setNotaModulo(Double.valueOf(spM4.getValue().toString()));

                        } else if (personalElegido.getModulos().get(i).getNombre().contains(labelM5.getText())) {
                            personalElegido.getModulos().get(i).setNotaModulo(Double.valueOf(spM5.getValue().toString()));

                        } else if (personalElegido.getModulos().get(i).getNombre().contains(labelM6.getText())) {
                            personalElegido.getModulos().get(i).setNotaModulo(Double.valueOf(spM6.getValue().toString()));

                        }
                    }
                    break;
                case 7:
                    for (int i = 0; i < personalElegido.getModulos().size(); i++) {
                        if (personalElegido.getModulos().get(i).getNombre().contains(labelM1.getText())) {
                            personalElegido.getModulos().get(i).setNotaModulo(Double.valueOf(spM1.getValue().toString()));
                        } else if (personalElegido.getModulos().get(i).getNombre().contains(labelM2.getText())) {
                            personalElegido.getModulos().get(i).setNotaModulo(Double.valueOf(spM2.getValue().toString()));
                        } else if (personalElegido.getModulos().get(i).getNombre().contains(labelM3.getText())) {
                            personalElegido.getModulos().get(i).setNotaModulo(Double.valueOf(spM3.getValue().toString()));
                        } else if (personalElegido.getModulos().get(i).getNombre().contains(labelM4.getText())) {
                            personalElegido.getModulos().get(i).setNotaModulo(Double.valueOf(spM4.getValue().toString()));

                        } else if (personalElegido.getModulos().get(i).getNombre().contains(labelM5.getText())) {
                            personalElegido.getModulos().get(i).setNotaModulo(Double.valueOf(spM5.getValue().toString()));

                        } else if (personalElegido.getModulos().get(i).getNombre().contains(labelM6.getText())) {
                            personalElegido.getModulos().get(i).setNotaModulo(Double.valueOf(spM6.getValue().toString()));

                        } else if (personalElegido.getModulos().get(i).getNombre().contains(labelM7.getText())) {
                            personalElegido.getModulos().get(i).setNotaModulo(Double.valueOf(spM7.getValue().toString()));

                        }
                    }
                    break;


            }
            System.out.println(personalElegido);
            (new PersonalBolsaDAOImplement()).modNotas(personalElegido);
            labelInfo.setText("");
           alumnos.clear();
           alumnos.addAll(new PersonalBolsaDAOImplement().getAllByCursoId(Utilidad.getCurso()));
        }
    }

    @javafx.fxml.FXML
    public void agregarAlumno(Event event) {
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("todos-alumnos-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 800, 600);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Alumnos");
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void filtrar(Event event) {
        String filtro=textBuscador.getText();
        if(filtro.isEmpty()){
            tablaCurso.setItems(alumnos);
        }else{
            filtroAlumnos.clear();
            for(PersonalBolsa a:alumnos){
                String porBusqueda= a.getNombre().toLowerCase()+" "+a.getApellido1().toLowerCase()+" "+a.getApellido2().toLowerCase();
                if(porBusqueda.contains(filtro.toLowerCase())){
                    filtroAlumnos.add(a);
                }
            }
            tablaCurso.setItems(filtroAlumnos);
        }
    }
}

