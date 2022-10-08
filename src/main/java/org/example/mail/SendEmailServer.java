package org.example.mail;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailServer {
    public static void sendEmail(String recipient) {
        System.out.println("");
        System.out.println(" Preparing send email...");
        System.out.println("");
        // Properties
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true");

        String adminEmail = "idrissaithadou@gmail.com";
        String adminPassword = "pass";

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(adminEmail, adminPassword);
            }
        });
        Message message = prepareMessage(session, adminEmail, recipient);
        try {
            Transport transport = session.getTransport("smtp");
            transport.connect("idrissaithadou@gmail.com", "gyribnelqvmglgzc");
            transport.sendMessage(message, message.getAllRecipients());
            System.out.println("Sent message successfully....");
            transport.close();
        } catch (Exception ex) {
            System.out.println(" Email doesn't sent !!!");
        }

    }

    public static Message prepareMessage(Session session, String adminEmail, String recipient) {
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(adminEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("New breif");
            message.setText("Hi Dear; \n you have a new breis");
            return message;
        } catch (Exception ex) {
            System.out.println(" error message");
        }
        return null;
    }
}
