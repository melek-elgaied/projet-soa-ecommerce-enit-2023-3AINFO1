package com.SOA.mailing.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="TEMP_EMAIL")
public class EmailTemplate implements Serializable {
    private static final long serialVersionID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long TemplateId;
    @Column(unique = true)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String body;
}
