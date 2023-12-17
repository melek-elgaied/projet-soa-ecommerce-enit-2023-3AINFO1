package com.SOA.mailing.Service;

import com.SOA.mailing.Config.DatabaseTemplateResolver;
import com.SOA.mailing.Enum.StatusEmail;
import com.SOA.mailing.Exception.EntityException;
import com.SOA.mailing.Model.Email;
import com.SOA.mailing.Repository.EmailRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.time.LocalDateTime;

@Service
public class EmailService {
    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    public Email sendEmail(Email email , String templateName, Context context) throws EntityException {
        email.setSendDateEmail(LocalDateTime.now());
        try {
            String htmlContent = springTemplateEngine.process(templateName,context);
            //SimpleMailMessage message = new SimpleMailMessage();
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(email.getEmailTo());
            helper.setSubject(email.getSubject());
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

}
