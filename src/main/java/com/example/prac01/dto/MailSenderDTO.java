package com.example.prac01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MailSenderDTO {

    private String sendTo;
    private String subject;
    private String text;
}
