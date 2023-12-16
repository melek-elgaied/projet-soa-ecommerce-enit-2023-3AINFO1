package com.SOA.mailing.Controller;

import com.SOA.mailing.Model.EmailTemplate;
import com.SOA.mailing.Service.EmailTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mailing/template")
public class EmailTemplateController {
    @Autowired
    private EmailTemplateService emailTemplateService;
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTemplate(@RequestBody EmailTemplate template) {
        emailTemplateService.saveTemplate(template);


    }
    @GetMapping("/getall")
    @ResponseStatus(HttpStatus.OK)
    public List<EmailTemplate> getAllTemplates(){
        return emailTemplateService.getAllTemplates();

    }
    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTemplate(@PathVariable Long id)
    {
        emailTemplateService.deleteTemplate(id);
    }
    @PutMapping ("/update/")
    @ResponseStatus(HttpStatus.OK)
    public EmailTemplate updateTemplate(@RequestBody EmailTemplate template)
    {
        return emailTemplateService.updateTemplate(template);
    }

}
