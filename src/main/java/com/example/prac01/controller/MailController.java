package com.example.prac01.controller;

import com.example.prac01.dto.MailSenderDTO;
import com.example.prac01.service.MailService;
import com.example.prac01.task.DailyResetTask;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RequiredArgsConstructor
@RequestMapping("/api/v2")
@RestController
public class MailController {
    private final MailService mailService;
    private final DailyResetTask dailyResetTask;

    @GetMapping("/count")
    public ResponseEntity<String> getDailyCount(){
        return new ResponseEntity<>(dailyResetTask.getDailyCount()
                + " has been left for " + ZonedDateTime.now(ZoneId.of("Asia/Seoul")), HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        mailService.sendSimpleMail();
        return new ResponseEntity<>("Success!", HttpStatus.OK);
    }

    @GetMapping("/mail")
    public ResponseEntity<String> simpleMail(@RequestBody MailSenderDTO mailSenderDTO){
        mailService.sendMail(mailSenderDTO);
        return new ResponseEntity<>("Success!", HttpStatus.OK);
    }
}
