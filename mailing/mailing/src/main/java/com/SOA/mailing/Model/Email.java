package com.SOA.mailing.Model;

import com.SOA.mailing.Enum.StatusEmail;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name="TB_EMAIL")


public class Email implements Serializable {
    private static final long serialVersionID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long emailId;
    private String emailTo;
    private String subject;
    private LocalDateTime sendDateEmail;
    private StatusEmail statusEmail;

}