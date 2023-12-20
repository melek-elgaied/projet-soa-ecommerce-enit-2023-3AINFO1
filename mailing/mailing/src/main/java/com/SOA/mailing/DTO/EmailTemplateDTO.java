package com.SOA.mailing.DTO;

import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailTemplateDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String body;
}
