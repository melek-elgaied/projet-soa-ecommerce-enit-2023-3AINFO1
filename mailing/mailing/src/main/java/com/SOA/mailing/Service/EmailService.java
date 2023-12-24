package com.SOA.mailing.Service;

import com.SOA.mailing.DTO.EmailDTO;
import com.SOA.mailing.Enum.StatusEmail;
import com.SOA.mailing.Exception.EntityException;
import com.SOA.mailing.Model.Email;
import com.SOA.mailing.Repository.EmailRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmailService {
    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;


    public Email sendSimpleEmail(EmailDTO emailDTO , String templateName, Context context) throws EntityException {
        Email email=new Email();
        email.setSendDateEmail(LocalDateTime.now());
        try {
            Map<String, Object> dataReplacement = emailDTO.getDataReplacement();
            //Convertir Map en json
            ObjectMapper objectMapper = new ObjectMapper();
            String dataReplacementJson = objectMapper.writeValueAsString(dataReplacement);
            email.setReplacementData(dataReplacementJson);
            System.out.println(dataReplacementJson);
            String htmlContent= processTemplate(templateName, dataReplacement);
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(emailDTO.getEmailTo());
            email.setEmailTo(emailDTO.getEmailTo());
            helper.setSubject(emailDTO.getSubject());
            //System.out.println("Set subject done");
            email.setSubject(emailDTO.getSubject());
            //System.out.println("Set replacement data done !");
            System.out.println(emailDTO.getDataReplacement());
            System.out.println(emailDTO);

            helper.setText(htmlContent,true);
            emailSender.send(message);
            email.setStatusEmail(StatusEmail.SENT);
            System.out.println(email);

        } catch (MailException | JsonProcessingException ex) {
            email.setStatusEmail(StatusEmail.ERROR);
            ex.printStackTrace();

        } finally {
            return emailRepository.save(email);

        }
    }
    public Email sendEmailWithAttachment(EmailDTO emailDTO , String templateName , Context context)
    {   //System.out.println("send email entred !");
        Email email=new Email();
        email.setSendDateEmail(LocalDateTime.now());

        try {
            Map<String, Object> dataReplacement = emailDTO.getDataReplacement();
            ObjectMapper objectMapper = new ObjectMapper();
            String dataReplacementJson = objectMapper.writeValueAsString(dataReplacement);
            String htmlContent= processTemplate(templateName, dataReplacement);
            email.setReplacementData(dataReplacementJson);
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setTo(emailDTO.getEmailTo());
            email.setEmailTo(emailDTO.getEmailTo());
            helper.setSubject(email.getSubject());
            //System.out.println("Set subject done");

            email.setSubject(emailDTO.getSubject());
            //System.out.println("Set replacement data done !");

            helper.setText(htmlContent,true);
            FileSystemResource file = new FileSystemResource(new File(emailDTO.getAttachment()));
            email.setAttachment(emailDTO.getAttachment());
            String fileName=file.getFilename();
            helper.addAttachment(fileName, file);



            emailSender.send(message);
            email.setStatusEmail(StatusEmail.SENT);

        } catch (MailException | JsonProcessingException ex) {
            email.setStatusEmail(StatusEmail.ERROR);
            ex.printStackTrace();

        } finally {
            return emailRepository.save(email);

        }

    }
    public void deleteEmail(Long id) throws EntityException {
        Optional<Email> emailToDel = emailRepository.findById(id);
        if (emailToDel.isPresent()) {
            emailRepository.deleteById(id);
        } else {
            throw new EntityException(EntityException.NotFoundException(id));
        }
    }
    public List<Email> getAllEmails() throws EntityException {
        List<Email> emails = emailRepository.findAll();
        if (emails == null || emails.isEmpty()) {
            throw new EntityException(EntityException.EmptyEntityCollection());
        }
        return emails;
    }
    public Email getEmailById(Long id) throws EntityException {
        Optional<Email> email = emailRepository.findById(id);
        if (email.isPresent()) {
            return email.get();
        } else {
            throw new EntityException(EntityException.NotFoundException(id));
        }
    }
    public List<Email> getEmailPerReciever(String emailTo) throws EntityException {
        List<Email> emails=emailRepository.findByEmailTo(emailTo);
        if(emails==null|| emails.isEmpty())
        {
            throw new EntityException(EntityException.EmptyEntityCollection());
        }
        return emails;
    }

    private String processTemplate(String templateName, Map<String, Object> replacementData){
        Context context = new Context();
        for (Map.Entry<String, Object> entry : replacementData.entrySet()) {
            context.setVariable(entry.getKey(), entry.getValue());
        }
        return springTemplateEngine.process(templateName, context);
    }
}
