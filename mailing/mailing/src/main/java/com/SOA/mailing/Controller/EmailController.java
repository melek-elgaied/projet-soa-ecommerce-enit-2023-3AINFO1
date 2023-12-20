package com.SOA.mailing.Controller;


import com.SOA.mailing.DTO.EmailDTO;
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
    @PostMapping("/sending-email/{templatename}")
    public ResponseEntity<?> sendEmail(@RequestBody EmailDTO emailDTO, @PathVariable String templateName) throws EntityException {
        Context context = new Context();
        //context.setVariable("subject",);
        //context.setVariable("ClientEmail",email.getEmailTo());


       return new ResponseEntity<>(emailService.sendSimpleEmail(emailDTO,templateName,context), HttpStatus.CREATED);}



    @GetMapping("/getAllEmails")
    public  ResponseEntity<?> getAllEmails()
    {
        try {
            return new ResponseEntity<>(emailService.getAllEmails(), HttpStatus.OK);
        } catch (EntityException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("getEmail/{id}")
    public ResponseEntity<?> getEmailById (@PathVariable Long id)
    {   if (id== null) {
        throw new IllegalArgumentException("The given id must not be null");
    }
        try {
            return new ResponseEntity<>(emailService.getEmailById(id),HttpStatus.OK);
        }
        catch (EntityException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("deleteEmail/{id}")
    public ResponseEntity<?> deleteEmailById(@PathVariable Long id)
    {
        if (id == null) {
            throw new IllegalArgumentException("The given id must not be null");
        }
        try {
           emailService.deleteEmail(id);
            return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
        } catch (EntityException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
