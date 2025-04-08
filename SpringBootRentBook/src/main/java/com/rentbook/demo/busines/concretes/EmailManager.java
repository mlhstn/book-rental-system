package com.rentbook.demo.busines.concretes;

import com.rentbook.demo.busines.abstracts.IEmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailManager implements IEmailService {

    private JavaMailSender mailSender;

    public EmailManager(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendLateReturnEmail(String to, String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("noreply@library.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);

    }
}
