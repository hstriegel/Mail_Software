package org.example;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Session;
import javax.mail.Transport;

public class Main {
    public static void main(String[] args) {
        String to = "hunter.b.striegel@gmail.com";
        Properties p = new Properties();
        p.put("mail.smtp.username", "marketplaceproj45@gmail.com");
        p.put("mail.smtp.password", "eblbvymahnzcdkge");
        p.put("mail.smtp.host", "smtp.gmail.com");
        p.put("mail.smtp.port", "587"); //gmail tls port num
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.starttls.enable", "true");
        Session mailSession = Session.getInstance(p, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(p.getProperty("mail.smtp.username"), p.getProperty("mail.smtp.password"));
            }
        });
        MimeMessage message = new MimeMessage(mailSession);
        try {
            message.setFrom(new InternetAddress("marketplaceproj45@gmail.com"));
            message.setSubject("Email Validation Code");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            message.setText("Hello ");
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            Transport.send(message);
        } catch (Exception e) {
            //if message fails to send, just log them in cause they got username and password right
            e.printStackTrace();

        }
    }
}