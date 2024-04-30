package Util;

import javafx.scene.control.Alert;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import javax.activation.FileDataSource;
import javax.activation.DataSource;

public class EnvioCorreoElectronico {

    public static void enviar(String correo,String nombre){
        final String EMAILFROM="bubachico@gmail.com";
        final String PASSWORDFROM="ryxu zohb jtco fsqr";

        Properties prop=new Properties();
        prop.put("mail.smtp.host","smtp.gmail.com");
        prop.put("mail.smtp.port","587");
        prop.put("mail.smtp.auth","true");
        prop.put("mail.smtp.starttls.enable","true");
        Session session=Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAILFROM,PASSWORDFROM);
            }
        });

        MimeMessage message=new MimeMessage(session);
        String concatenado="";
        try(BufferedReader read=new BufferedReader(new FileReader("./textoCorreo.txt"))){

            String linea;
            while ((linea=read.readLine())!=null){
                concatenado+=linea+"\n";
                if(concatenado.contains("$nombre")){
                    concatenado=concatenado.replace("$nombre",nombre);
                }
            }
            concatenado=concatenado.replace("\n","<br>");
            System.out.println(concatenado);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
/*
        try {

           message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo,true));
            message.setSubject("prueba");
            MimeBodyPart textPart=new MimeBodyPart();
            textPart.setContent(concatenado,"text/html; charset=utf-8");
            MimeBodyPart attachmentPart = new MimeBodyPart();
            DataSource source = new FileDataSource("./listadoAlumnos.pdf");
            attachmentPart.setDataHandler(new DataHandler( source));
            attachmentPart.setFileName("listadoAlumnos.pdf");

            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);
            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Mensaje enviado con éxito");
        } catch (Exception e) {
            Alert alerta=new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setHeaderText("Correo No Enviado");
            alerta.setContentText("Asegurese de que tenga conexión a internet y " +
                    "que el correo del cliente exista y que la ruta de la plantilla sea correcta.");
            alerta.showAndWait();
        }*/

    }
}
