package com.SOA.mailing.Config;

import com.SOA.mailing.Model.EmailTemplate;
import com.SOA.mailing.Repository.EmailTemplateRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.templateresolver.StringTemplateResolver;
import org.thymeleaf.templateresource.ITemplateResource;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;


@Component
public class DatabaseTemplateResolver extends StringTemplateResolver {


    private static final Logger logger = getLogger(DatabaseTemplateResolver.class);




    private final  EmailTemplateRepository emailTemplateRepository;

    public DatabaseTemplateResolver(EmailTemplateRepository emailTemplateRepository) {
        this.emailTemplateRepository=emailTemplateRepository;
        this.setResolvablePatterns(Collections.singleton("tp-*"));
        this.setCacheTTLMs(5*60*1000L);
        this.setCacheable(true);
    }

    @Override
    protected ITemplateResource computeTemplateResource(IEngineConfiguration configuration,
                                                        String ownerTemplate,
                                                        String templateName,
                                                        Map<String, Object> templateResolutionAttributes) {
        logger.info("Loading template named {} from DB", templateName);
        Optional<EmailTemplate> template = emailTemplateRepository.findEmailTemplateByName(templateName);

        if (!template.isPresent()) {
            return null;
        }
        logger.info(template.get().getBody());

        return super
                .computeTemplateResource(configuration, ownerTemplate, template.get().getBody(), templateResolutionAttributes);
    }
}
