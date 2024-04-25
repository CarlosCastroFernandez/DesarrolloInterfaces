package Util;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EnvioCorreoElectronico {

    public static void enviar(){
        final String EMAILFROM="bubachico@gmail.com";
        final String PASSWORDFROM="ryxu zohb jtco fsqr";
        String emailTo;
        String subject;
        String content;
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
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("bubachico@gmail.com",true));
            message.setSubject("prueba");
            message.setText("BLABLABÑA");
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
