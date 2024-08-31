package com.example.prac01.service;

import com.example.prac01.dto.MailSenderDTO;
import com.example.prac01.task.DailyResetTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MailService {
    private final JavaMailSender mailSender;
    private final DailyResetTask dailyResetTask;
    @Value("${spring.mail.username}")
    private String email;

    public void sendSimpleMail(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email);
        message.setTo(email);
        message.setSubject("Test");
        message.setText("hello! gmail!");

        mailSender.send(message);
        dailyResetTask.waste();
        log.info("Email Sent Successfully");
    }

    public void sendMail(MailSenderDTO mailSenderDTO){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email);
        message.setTo(mailSenderDTO.getSendTo());
        message.setSubject(mailSenderDTO.getSubject());
        message.setText(mailSenderDTO.getText());

        mailSender.send(message);
        dailyResetTask.waste();
        log.info("Email Sent to " + mailSenderDTO.getSendTo());
    }
}
