package com.roczyno.project_management.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    @Override
    @Async
    public void sendEmailWithToken(String userEmail, String subject, String link) throws MessagingException {
        MimeMessage mimeMessage= mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"utf-8");
         subject="Join project team invitation";
         String text="Click the link to join project team:" +link;
         helper.setSubject(subject);
         helper.setText(text);
         helper.setTo(userEmail);
         try {
             mailSender.send(mimeMessage);
         }catch (MailSendException e) {
             throw new MailSendException("failed to send email");
         }
    }
}
