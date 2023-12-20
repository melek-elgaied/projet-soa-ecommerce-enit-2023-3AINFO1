package com.SOA.mailing.Service;

import com.SOA.mailing.Model.ClientEmail;
import com.SOA.mailing.Repository.ClientEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientEmailService {
    private final ClientEmailRepository clientEmailRepository;

    @Autowired
    public ClientEmailService(ClientEmailRepository clientEmailRepository){
        this.clientEmailRepository = clientEmailRepository;
    }

    public List<ClientEmail> getAllEmails(){
        return clientEmailRepository.findAll();
    }

    public ClientEmail saveClientEmail(ClientEmail clientEmail){
        return clientEmailRepository.save(clientEmail);
    }

    public ClientEmail getClientEmailById(Long id){
        return clientEmailRepository.findById(id).orElse(null);
    }

    public void deleteClientEmail(Long id){
        clientEmailRepository.deleteById(id);
    }
}
