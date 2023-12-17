package com.SOA.mailing.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;;
import org.thymeleaf.templatemode.TemplateMode;




@Configuration
public class ThymeleafConfig {


    @Bean
    public SpringTemplateEngine springTemplateEngine(DatabaseTemplateResolver databaseTemplateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        databaseTemplateResolver.setTemplateMode(TemplateMode.HTML);
        templateEngine.addTemplateResolver(databaseTemplateResolver);
        return templateEngine;
    }



    }

