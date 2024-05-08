package com.example.ininprotec;

import Util.EnvioCorreoElectronico;
import Util.HashPassword;
import Util.Utilidad;
import clase.PersonalIIP;
import implement.PersonalIIPDAOImplement;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.*;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Properties;
import java.util.ResourceBundle;

public class LogInController implements Initializable {
    @javafx.fxml.FXML
    private TextField textCorreo;
    @javafx.fxml.FXML
    private PasswordField textContraseña;
    @javafx.fxml.FXML
    private ImageView imageEscudo;
    @javafx.fxml.FXML
    private Button botonIncio;
    @javafx.fxml.FXML
    private ProgressIndicator indicator;
    @javafx.fxml.FXML
    private CheckBox checkContraseña;
    @javafx.fxml.FXML
    private Label labelOlvido;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image=new Image(LogInController.class.getClassLoader().getResource("imagenes/escudo.png").toExternalForm());
        imageEscudo.setImage(image);
        indicator.setVisible(false);
        InputStream is= null;
        try {
            is = new FileInputStream("check.properties");
            Properties config=new Properties();
            config.load(is);
            if(!config.isEmpty()){
                checkContraseña.setSelected(true);
                textCorreo.setText(config.getProperty("correo"));
                textContraseña.setText(descifrado(config.getProperty("contraseña")));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @javafx.fxml.FXML
    public void inicioSesion(ActionEvent actionEvent) {
        if(!textContraseña.getText().isEmpty()&&!textCorreo.getText().isEmpty()){
            String correo=textCorreo.getText();
            String contraseñaReal=cifrar(textContraseña.getText());
            try {
                String contraseña= HashPassword.hashPassword(textContraseña.getText());

                PersonalIIP personal=(new PersonalIIPDAOImplement().login(correo));


                if(personal!=null){
                    if(personal.getContraseña().equals(contraseña)){
                        Utilidad.setLogPersonal(personal);
                        Alert alerta=new Alert(Alert.AlertType.CONFIRMATION);
                        alerta.setTitle("OK!");
                        alerta.setHeaderText("Inicio De Sesión Correcto");
                        alerta.showAndWait();
                        if(checkContraseña.isSelected()){
                            InputStream is=new FileInputStream("check.properties");
                            Properties config=new Properties();
                            config.load(is);
                            is.close();
                            config.clear();
                            config.put("correo",textCorreo.getText());
                            config.put("contraseña",contraseñaReal);
                            OutputStream os = new FileOutputStream("check.properties");
                            config.store(os, "Configuración actualizada");
                            os.close();

                        }else{
                            InputStream is=new FileInputStream("check.properties");
                            Properties config=new Properties();
                            config.load(is);
                            is.close();
                            config.clear();
                            OutputStream os = new FileOutputStream("check.properties");
                            config.store(os, "Propiedades borradas");
                            os.close();
                        }
                        HelloApplication.cambioVentana("principal-view.fxml");
                    }else{
                        Alert alerta=new Alert(Alert.AlertType.ERROR);
                        alerta.setTitle("ERROR");
                        alerta.setHeaderText("Inicio De Sesión Incorrecto");
                        alerta.setContentText("Contraseña Incorrecta");
                        alerta.showAndWait();

                    }
                }else{
                    Alert alerta=new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("ERROR");
                    alerta.setHeaderText("Inicio De Sesión Incorrecto");
                    alerta.setContentText("Correo Electrónico Incorrecto");
                    alerta.showAndWait();
                }

            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }

    @javafx.fxml.FXML
    public void recuerdoContraseña(ActionEvent actionEvent) {

    }

    @javafx.fxml.FXML
    public void olvidaContraseña(Event event) {
        Dialog<HashMap<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Introducir Datos");
        dialog.setHeaderText("Introduce todos los datos:");

        // Configurar tipos de botones
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField textField1 = new TextField();
        textField1.setPromptText("E-MAIL");
        grid.add(new Label("E-MAIL:"), 0, 0);
        grid.add(textField1, 1, 0);
        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(dialogButton -> {
            if(dialogButton==ButtonType.OK&&!textField1.getText().isEmpty()){
                PersonalIIP personal=(new PersonalIIPDAOImplement().login(textField1.getText()));
                if(personal!=null){
                    SecureRandom ran=new SecureRandom();
                    String concatenado="";
                    for(int i =0;i<7;i++){
                        concatenado+=(char)ran.nextInt('A','Z'+1);
                    }

                    EnvioCorreoElectronico.enviarVerificacion(textCorreo.getText(),concatenado);
                    String finalConcatenado = concatenado;
                    Platform.runLater(() -> segundoDialog(finalConcatenado, dialog,personal));

                    return null;

                }else{
                    Alert alerta=new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("ERROR");
                    alerta.setHeaderText("Correo Electrónico Invalido");
                    alerta.setContentText("Es imposible encontrar este correo en nuestro sistema");
                    alerta.showAndWait();
                    dialog.close();
                    return null;
                }
            }else{
                return null;
            }

        });
        dialog.showAndWait();
    }
    public String cifrar(String contraseña){
        String cifrado="";
        for(int i=0;i<contraseña.length();i++){
            cifrado+=(char)(contraseña.charAt(i)+3);
            System.out.println(cifrado);
        }
        return cifrado;
    }
    public String descifrado(String cifrado){
        String descifrado="";
        for(int i=0;i<cifrado.length();i++){
            descifrado+=(char)(cifrado.charAt(i)-3);
            System.out.println(descifrado);
        }
        return descifrado;
    }
    public void segundoDialog(String codigo,Dialog dialog1,PersonalIIP personal){
        dialog1.close();
        Dialog<HashMap<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Introducir Datos");
        dialog.setHeaderText("Introduce todos los datos:");

        // Configurar tipos de botones
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        PasswordField textField1 = new PasswordField();
        textField1.setPromptText("Contraseña Nueva");
        grid.add(new Label("Contraseña Nueva:"), 0, 0);
        grid.add(textField1, 1, 0);
        TextField textField2=new TextField();
        textField2.setPromptText("Coódigo de Verificación");
        grid.add(new Label("Código"),0,1);
        grid.add(textField2,1,1);
        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(dialogButton -> {
            if(dialogButton==ButtonType.OK&&!textField1.getText().isEmpty()&&!textField2.getText().isEmpty()){
                if(textField2.getText().equals(codigo)){
                    try {
                        personal.setContraseña(HashPassword.hashPassword(textField1.getText()));
                        (new PersonalIIPDAOImplement()).cambioPassword(personal);
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }
                    return null;
                }else {
                    Alert alerta=new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("ERROR");
                    alerta.setHeaderText("Código Incorrecto");
                    alerta.setContentText("El código es incorrecto");
                    alerta.showAndWait();
                    dialog.close();
                    return null;
                }
            }else{

                return null;
            }
        });
        dialog.showAndWait();




    }
}
