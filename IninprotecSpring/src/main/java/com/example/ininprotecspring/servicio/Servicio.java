package com.example.ininprotecspring.servicio;

import com.example.ininprotecspring.clase.PersonalBolsa;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service

public class Servicio {
    private static  Connection conexion;
    private static PersonalBolsa alumno;

    public static PersonalBolsa getAlumno() {
        return alumno;
    }

    public static void setAlumno(PersonalBolsa alumno) {
        Servicio.alumno = alumno;
    }

    public  String cifrado(String password){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");

            md.update(password.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] digest = md.digest();
        return bytesToHex(digest);
    }
    public String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    public byte[] generateReport(PersonalBolsa alumno) throws JRException {
        // Cargar el archivo Jasper diseñado con Jaspersoft Studio


        // Obtener los datos, probablemente necesites ajustar esto según tus necesidades
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("idAlumno",alumno.getId());
        parameters.put("nombreAlumno",alumno.getNombre()+" "+alumno.getApellido1()+" "+alumno.getApellido2());

        // Rellenar el reporte
        JasperPrint jasperPrint = JasperFillManager.fillReport("cursos.jasper", parameters, getConexion());

        // Exportar a PDF como ejemplo
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
    public static Connection getConexion(){
        InputStream is = Servicio.class.getClassLoader().getResourceAsStream("mysql.properties");
        Properties p = new Properties();
        try {
            p.load(is);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            conexion= DriverManager.getConnection("jdbc:mysql://" + p.getProperty("url") + ":" + p.getProperty("port") + "/" + p.getProperty("nombreBD"), p.getProperty("user"), p.getProperty("pass"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conexion;

    }
    public void enviarVerificacion(String correo,String verificacion){
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
        try {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo,true));
            message.setSubject("Prueba Verficacion");
            message.setText("Has solicitado una contraseña, su código de verificación es "+verificacion);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }
}

