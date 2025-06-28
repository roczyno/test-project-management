package com.roczyno.project_management.service;

import jakarta.mail.MessagingException;


public interface EmailService {
    void sendEmailWithToken(String userEmail, String subject, String link) throws MessagingException;
}
