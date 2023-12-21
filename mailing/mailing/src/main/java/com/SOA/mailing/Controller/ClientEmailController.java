package com.SOA.mailing.Controller;

import com.SOA.mailing.Model.ClientEmail;
import com.SOA.mailing.Service.ClientEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mailing/emails")
public class ClientEmailController {
    private final ClientEmailService clientEmailService;
    @Autowired
    public ClientEmailController(ClientEmailService clientEmailService){
        this.clientEmailService = clientEmailService;
    }

    @GetMapping
    public List<ClientEmail> getAllEmails(){
        return clientEmailService.getAllEmails();
    }

    @PostMapping("/add")
    public ClientEmail saveClientEmail(@RequestBody ClientEmail clientEmail){
        return clientEmailService.saveClientEmail(clientEmail);
    }

    @GetMapping("/{id}")
    public ClientEmail getClientEmailById(@PathVariable Long id){
        return clientEmailService.getClientEmailById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteClientEmail(@PathVariable Long id){
        clientEmailService.deleteClientEmail(id);
    }
}
