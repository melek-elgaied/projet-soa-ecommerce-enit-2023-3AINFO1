package com.SOA.mailing.Service;

import com.SOA.mailing.Config.DatabaseTemplateResolver;
import com.SOA.mailing.DTO.EmailDTO;
import com.SOA.mailing.Enum.StatusEmail;
import com.SOA.mailing.Exception.EntityException;
import com.SOA.mailing.Model.Email;
import com.SOA.mailing.Model.EmailTemplate;
import com.SOA.mailing.Repository.EmailRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
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
            String htmlContent = springTemplateEngine.process(templateName,context);
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(emailDTO.getEmailTo());
            email.setEmailTo(emailDTO.getEmailTo());
            helper.setSubject(email.getSubject());
            email.setSubject(emailDTO.getSubject());
            helper.setText(htmlContent,true);
            emailSender.send(message);
            email.setStatusEmail(StatusEmail.SENT);

        } catch (MailException ex) {
            email.setStatusEmail(StatusEmail.ERROR);
            System.out.println(ex.fillInStackTrace());

    } finally {
            return emailRepository.save(email);

        }
    }
    public Email sendEmailWithAttachment(EmailDTO emailDTO , String templateName , Context context, String fileToAttach)
    { Email email=new Email();
        email.setSendDateEmail(LocalDateTime.now());
        try {
            String htmlContent = springTemplateEngine.process(templateName,context);
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setTo(emailDTO.getEmailTo());
            email.setEmailTo(emailDTO.getEmailTo());
            helper.setSubject(email.getSubject());
            email.setSubject(emailDTO.getSubject());
            helper.setText(htmlContent,true);
            FileSystemResource file = new FileSystemResource(new File(fileToAttach));
            String fileName=file.getFilename();
            helper.addAttachment(fileName, file);



            emailSender.send(message);
            email.setStatusEmail(StatusEmail.SENT);

        } catch (MailException ex) {
            email.setStatusEmail(StatusEmail.ERROR);
            System.out.println(ex.fillInStackTrace());

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

}
