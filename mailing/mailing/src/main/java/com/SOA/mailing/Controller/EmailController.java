package com.SOA.mailing.Controller;


import com.SOA.mailing.Exception.EntityException;
import com.SOA.mailing.Model.Email;
import com.SOA.mailing.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;



@RestController
@RequestMapping("api/mailing")
public class EmailController {
    @Autowired
    private EmailService emailService;
    @PostMapping("/sending-email/{templateType}")
    public ResponseEntity<?> sendEmail(@RequestBody Email email, @PathVariable String templateType) throws EntityException {
        Context context = new Context();
        //context.setVariable("subject",);
        //context.setVariable("ClientEmail",email.getEmailTo());


       return new ResponseEntity<>(emailService.sendEmail(email,templateType,context), HttpStatus.CREATED);}


    }
