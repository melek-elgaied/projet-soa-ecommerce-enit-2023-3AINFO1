package com.SOA.mailing.Controller;

import com.SOA.mailing.DTO.EmailTemplateDTO;
import com.SOA.mailing.Exception.EntityException;
import com.SOA.mailing.Model.EmailTemplate;
import com.SOA.mailing.Service.EmailTemplateService;
import jakarta.validation.Valid;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/api/mailing/template")
public class EmailTemplateController {
    @Autowired
    private EmailTemplateService emailTemplateService;

    @PostMapping("/add")
    public ResponseEntity<?> addTemplate(@RequestBody @Valid EmailTemplateDTO template) {
        try {
            return new ResponseEntity<>(emailTemplateService.saveTemplate(template), HttpStatus.CREATED);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (EntityException e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllTemplates() {
        try {
            return new ResponseEntity<>(emailTemplateService.getAllTemplates(), HttpStatus.OK);
        } catch (EntityException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteTemplate(@PathVariable Long id) {
        if (id == null) {
            throw new IllegalArgumentException("The given id must not be null");
        }
        try {
            emailTemplateService.deleteTemplate(id);
            return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
        } catch (EntityException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTemplate(@RequestBody @Valid EmailTemplateDTO template) {
        try {
            return new ResponseEntity<>(emailTemplateService.updateTemplate(template), HttpStatus.OK);
        } catch (EntityException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getTemplateById(@PathVariable Long id) {
        if (id == null) {
            throw new IllegalArgumentException("The given id must not be null");
        }
        try {
            return new ResponseEntity<>(emailTemplateService.getTemplateById(id),HttpStatus.OK);
        }
        catch (EntityException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/get/{name}")
    public ResponseEntity<?> getTemplateByName(@PathVariable String name) {
        if (name== null) {
            throw new IllegalArgumentException("The given name must not be null");
        }
        try {
            return new ResponseEntity<>(emailTemplateService.getTemplateByName(name),HttpStatus.OK);
        }
        catch (EntityException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
