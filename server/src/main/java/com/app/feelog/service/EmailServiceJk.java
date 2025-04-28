package com.app.feelog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceJk {

    private final JavaMailSender mailSender;

    public void sendTicketEmail(String fromEmail, String subject, String description) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("feelogfl@gmail.com");
        message.setSubject("[문의 접수] " + subject);
        message.setText(
                "보낸 사람 이메일: " + fromEmail + "\n\n" +
                        "문의 제목: " + subject + "\n\n" +
                        "문의 내용:\n" + description
        );
        mailSender.send(message);
    }
}