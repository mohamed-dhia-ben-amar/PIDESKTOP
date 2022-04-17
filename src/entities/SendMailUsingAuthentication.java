/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import java.io.*;

/**
 *
 * @author MSI
 */
public class SendMailUsingAuthentication {

    private static final String SMTP_HOST_NAME = "gemini.jvmhost.com"; //or simply "localhost"
    private static final String SMTP_AUTH_USER = "mohameddhia.benamar@esprit.tn";
    private static final String SMTP_AUTH_PWD = "191JMT0647";
    private static final String emailMsgTxt = "Body";
    private static final String emailSubjectTxt = "Subject";
    private static final String emailFromAddress = "mohameddhia.benamar@esprit.tn";

    // Add List of Email address to who email needs to be sent to
    private static final String[] emailList = {"Rawaa.blh@gmail.com"};

    public static void main(String args[]) throws Exception {
        SendMailUsingAuthentication smtpMailSender = new SendMailUsingAuthentication();
        smtpMailSender.postMail(emailList, emailSubjectTxt, emailMsgTxt, emailFromAddress);
        System.out.println("Sucessfully Sent mail to All Users");
    }

    public void postMail(String recipients[], String subject,
            String message, String from) throws MessagingException, AuthenticationFailedException {
        boolean debug = false;

        //Set the host smtp address
        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.smtp.auth", "true");
        Authenticator auth = new SMTPAuthenticator();
        Session session = Session.getDefaultInstance(props, auth);

        session.setDebug(debug);

        // create a message
        Message msg = new MimeMessage(session);

        // set the from and to address
        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);

        InternetAddress[] addressTo = new InternetAddress[recipients.length];
        for (int i = 0; i < recipients.length; i++) {
            addressTo[i] = new InternetAddress(recipients[i]);
        }
        msg.setRecipients(Message.RecipientType.TO, addressTo);

        // Setting the Subject and Content Type
        msg.setSubject(subject);
        msg.setContent(message, "text/plain");
        Transport.send(msg);
    }

    private class SMTPAuthenticator extends javax.mail.Authenticator {

        public PasswordAuthentication getPasswordAuthentication() {
            String username = SMTP_AUTH_USER;
            String password = SMTP_AUTH_PWD;
            return new PasswordAuthentication(username, password);
        }
    }
}
