package com.SOA.mailing.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailTemplateDTO {

    private String type;
    private String subject;
    private String body;
}
