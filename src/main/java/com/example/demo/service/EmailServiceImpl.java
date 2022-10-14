package com.example.demo.service;


import com.example.demo.service.EmailService;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmailServiceImpl implements EmailService {
    @Override
    public void sendEmailToUser() throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(587);
        email.setAuthenticator(new DefaultAuthenticator("prabinbhandari2056@gmail.com", "qoyrfnjznxqfkgup"));
        email.setSSLOnConnect(true);
        email.setFrom("no-reply@gmail.com");
        email.setSubject("This mail is for testing");
        email.setMsg("Hello bro, This is auto generated message from java code.");
        email.addCc("radhikabhandari2028@gmail.com");
        email.getHeaders();
        email.addHeader("Hello","Hey Bro  what's up");
        email.addTo("prabininwork@gmail.com");
        email.send();
    }
}