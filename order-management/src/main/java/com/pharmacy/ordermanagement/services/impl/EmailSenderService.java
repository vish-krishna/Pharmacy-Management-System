package com.pharmacy.ordermanagement.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    public String sendSimpleEmail(String toEmail,
                                  String subject,
                                  String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("pharmacymanagement132@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("Mail Send...");

        return "New Order has been created. An email has been send to your mailId.";
    }
}
