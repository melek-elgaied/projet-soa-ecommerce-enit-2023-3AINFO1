package com.SOA.mailing.Model;

import com.SOA.mailing.Enum.StatusEmail;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.mapping.TypeDef;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@Entity
@Table(name="TB_EMAIL")

public class Email implements Serializable {
    private static final long serialVersionID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "email_id")
    private Long emailId;
    private String emailTo;
    private String subject;
    private LocalDateTime sendDateEmail;
    private StatusEmail statusEmail;

    // ajouter eplacement data pour permettre au services clients de remplacer les placeholders des template
    // avec les données appropriés de chaque service (payment/ shipping)
    @Column(name = "replacement_data", columnDefinition = "jsonb")
    private String replacementData;
    private String attachment;
}