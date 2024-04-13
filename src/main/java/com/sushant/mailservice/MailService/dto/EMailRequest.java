package com.sushant.mailservice.MailService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EMailRequest {
    private  String toEmail;
    private String emailSubject;
    private  String emailBody;


}
