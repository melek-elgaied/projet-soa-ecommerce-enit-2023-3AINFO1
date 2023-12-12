package com.SOA.mailing.Service;

import com.SOA.mailing.Enum.TemplateType;
import com.SOA.mailing.Model.EmailTemplate;
import com.SOA.mailing.Repository.EmailTemplateRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmailTemplateService {
    @Autowired
    private EmailTemplateRepository emailTemplateRepository;
    public EmailTemplate saveTemplate(EmailTemplate emailTemplate) {
        EmailTemplate template=EmailTemplate.builder()
                .TemplateId(emailTemplate.getTemplateId())
                .type(emailTemplate.getType())
                .subject(emailTemplate.getSubject())
                .body(emailTemplate.getBody())
                .build();
         return emailTemplateRepository.save(template);
        //log.info("Template"+ template.getTemplateId()+"is saved");


    }
    public void deleteTemplate(Long id){
        Optional<EmailTemplate> templateToDel=emailTemplateRepository.findById(id);
        if(templateToDel.isPresent())
        {
            emailTemplateRepository.deleteById(id);
        }
        else {
            throw new EntityNotFoundException("Entity not found for template ID: " +id);
        }
    }
    public EmailTemplate updateTemplate(EmailTemplate emailTemplate)
    {
       Optional<EmailTemplate> templateToMod=emailTemplateRepository.findById(emailTemplate.getTemplateId());
       if(templateToMod.isPresent())
       {
               EmailTemplate updateTemplate =templateToMod.get();
               updateTemplate.setSubject(emailTemplate.getSubject());
               updateTemplate.setBody(emailTemplate.getBody());
              return emailTemplateRepository.save(updateTemplate);


           }
       else
       {
           throw new EntityNotFoundException("Entity not found for template ID: " + emailTemplate.getTemplateId());
       }

       }
      public List<EmailTemplate> getAllTemplates()
      {
          return emailTemplateRepository.findAll();



      }
      public EmailTemplate getTemplateById(Long id) {
          Optional<EmailTemplate> template = emailTemplateRepository.findById(id);
          if (template.isPresent()) {
              return template.get();
          } else {
              throw new EntityNotFoundException("Entity not found for template ID: " + id);
          }
      }

}
