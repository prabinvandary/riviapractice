package com.example.demo.Controller;

import com.example.demo.pojo.ApiResponse;
import com.example.demo.service.EmailService;
import org.apache.commons.mail.EmailException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/email/")
public class EmailController extends ApiResponse {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("send/")
    public ApiResponse sendEmailToUSer() throws EmailException {
        emailService.sendEmailToUser();
        return success("Email sent successfully", null);
    }
}