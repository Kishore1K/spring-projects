package com.mail.service;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


@Service
public class EmailService {
    public boolean sendEmail(String subject, String message, String to){
        boolean flag=false;
        String from="test@gmail.com";
//        variable for host
        String host = "smtp.gmail.com";

//        system properties

        Properties properties = System.getProperties();
        System.out.println("properties = " + properties);


//        setting important info to properties object
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", true);
        properties.put("mail.smtp.auth", true);

//        stp1 : get the session object

        Session session = Session.getInstance(properties, new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return  new PasswordAuthentication("test@gmail.com", "*************");
            }
        });
        session.setDebug(true);

//        compose message [txt, multimedia]
        MimeMessage m = new MimeMessage(session);

        try{
//            from mail
            m.setFrom(from);
//            adding recipient
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//            adding subject

            m.setSubject(subject);
//            adding text to content
            m.setText(message);

//            send
            Transport.send(m);
            flag=true;

            System.out.println("Sent Success");


        }catch (Exception e){
            System.out.println(e.getMessage());

        }
        return  flag;




    }
}
