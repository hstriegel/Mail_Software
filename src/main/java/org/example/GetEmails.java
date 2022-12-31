package org.example;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;

public class GetEmails {
    public static void main(String[] args) {
        // Calling checkMail method to check received emails
        String host = "imap.gmail.com";
        String username = "marketplaceproj45@gmail.com";
        String password = "chzflevpdlkugciy";
        Properties props = new Properties();
        props.setProperty("mail.imap.ssl.enable", "true");
        // set any other needed mail.imap.* properties here
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);

            }
        });
        try {
            Store store = session.getStore("imap");
            store.connect(host, username, password);

            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            //4) retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.getMessages();
            for (int i = 0; i < messages.length; i++) {
                Message message = messages[i];
                System.out.println("---------------------------------");
                System.out.println("Email Number " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Text: " + getTextFromMessage(message));
                System.out.println(message.getReceivedDate());
            }

            emailFolder.close(false);
            store.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getTextFromMessage(Message message) throws MessagingException, IOException {
        if (message.isMimeType("text/plain")) {
            return message.getContent().toString();
        }
        if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            return getTextFromMimeMultipart(mimeMultipart);
        }
        return "";
    }

    private static String getTextFromMimeMultipart(
            MimeMultipart mimeMultipart)  throws MessagingException, IOException{
        String result = "";
        for (int i = 0; i < mimeMultipart.getCount(); i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                return result + "\n" + bodyPart.getContent(); // without return, same text appears twice in my tests
            }
            result += parseBodyPart(bodyPart);
        }
        return result;
    }

    private static String parseBodyPart(BodyPart bodyPart) throws MessagingException, IOException {
        if (bodyPart.isMimeType("text/html")) {
            return "\n" + org.jsoup.Jsoup
                    .parse(bodyPart.getContent().toString())
                    .text();
        }
        if (bodyPart.getContent() instanceof MimeMultipart){
            return getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
        }

        return "";
    }
}
