package com.SOA.mailing.Repository;

import com.SOA.mailing.Enum.TemplateType;
import com.SOA.mailing.Model.EmailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailTemplateRepository extends JpaRepository<EmailTemplate,Long> {



}
