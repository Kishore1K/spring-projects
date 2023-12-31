package com.mail.controller;

import com.mail.model.EmailRequest;
import com.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;
    @GetMapping({"/",""})
    public String Welcome(){
        return "Welcome";
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request){
        System.out.println(request);
        boolean res = emailService.sendEmail(request.getSubject(), request.getMessage(), request.getTo());
        if(!res){
            return  ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(request);
    }
}
