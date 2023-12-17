package com.SOA.mailing.Repository;

import com.SOA.mailing.Model.EmailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailTemplateRepository extends JpaRepository<EmailTemplate,Long>
{
  Optional<EmailTemplate> findEmailTemplateByName(String templateName);


}
