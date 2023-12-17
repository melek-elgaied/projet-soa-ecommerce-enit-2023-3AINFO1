package com.SOA.mailing.Service;

import com.SOA.mailing.DTO.EmailTemplateDTO;
import com.SOA.mailing.Exception.EntityException;
import com.SOA.mailing.Model.EmailTemplate;
import com.SOA.mailing.Repository.EmailTemplateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmailTemplateService {
    @Autowired
    private EmailTemplateRepository emailTemplateRepository;

    public EmailTemplate saveTemplate(EmailTemplateDTO templateDTO) throws EntityException {
        Optional<EmailTemplate> templateOptional= emailTemplateRepository.findEmailTemplateByName(templateDTO.getName());
        if(templateOptional.isPresent())
        {
           throw new EntityException(EntityException.TemplateAlreadyExists(templateDTO.getName()));
        }
        else {
            EmailTemplate template = EmailTemplate.builder()
                    .name(templateDTO.getName())
                    .body(templateDTO.getBody())
                    .build();
            return emailTemplateRepository.save(template);


    } }

    public void deleteTemplate(Long id) throws EntityException {
        Optional<EmailTemplate> templateToDel = emailTemplateRepository.findById(id);
        if (templateToDel.isPresent()) {
            emailTemplateRepository.deleteById(id);
        } else {
            throw new EntityException(EntityException.NotFoundException(id));
        }
    }

    public EmailTemplate updateTemplate(EmailTemplateDTO emailTemplate) throws EntityException {
        Optional<EmailTemplate> templateToMod = emailTemplateRepository.findEmailTemplateByName(emailTemplate.getName());
        if (templateToMod.isPresent()) {
            EmailTemplate updateTemplate = templateToMod.get();
            updateTemplate.setBody(emailTemplate.getBody());
            return emailTemplateRepository.save(updateTemplate);


        } else {
            throw new EntityException(EntityException.NotFoundNameException(emailTemplate.getName()));
        }

    }

    public List<EmailTemplate> getAllTemplates() throws EntityException {
        List<EmailTemplate> templates = emailTemplateRepository.findAll();
        if (templates == null || templates.isEmpty()) {
            throw new EntityException(EntityException.EmptyEntityCollection());
        }
        return templates;


    }

    public EmailTemplate getTemplateById(Long id) throws EntityException {
        Optional<EmailTemplate> template = emailTemplateRepository.findById(id);
        if (template.isPresent()) {
            return template.get();
        } else {
            throw new EntityException(EntityException.NotFoundException(id));
        }

    }

    public String getTemplateBody(Long id) {
        return emailTemplateRepository.findById(id)
                .map(EmailTemplate::getBody)
                .orElse(null);
    }

    public EmailTemplate getTemplateByName(String name) throws EntityException
    { Optional<EmailTemplate> template=emailTemplateRepository.findEmailTemplateByName(name);
       if(template.isPresent())
       {
           return template.get();
       }
       else
       {
           throw new EntityException(EntityException.InvalidTemplateType());
       }

    }
}