package com.company.studio.database;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaMailUtil {
    public static void sendMail(String recipient, String pass) throws MessagingException {
        //System.out.println("Preparing to send message to "+recipient);
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        String myAccountEmail = "devmaks666@gmail.com";
        String password = "devmaksim";
        /*String myAccountEmail = "872303bsuir@gmail.com";
        String password = "872303pass";*/


        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message = prepareMessage(session, myAccountEmail, recipient, pass);

        Transport.send(message);
        System.out.println("Message sent successfully to email: "+recipient);
    }
    private static Message prepareMessage(Session session, String myAccountEmail, String recepient, String pass){
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Don't forget your password!");
            message.setText("Dear user! Your password is: "+pass);
            return message;
        }catch (Exception ex){
            Logger.getLogger(com.company.studio.database.JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
