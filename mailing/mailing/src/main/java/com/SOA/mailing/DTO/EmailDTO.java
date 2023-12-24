package com.SOA.mailing.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmailDTO {

    @NotBlank
    @Email

    private String emailTo;
    private String subject;
    private Map<String, Object> dataReplacement;
    private String attachment;

}
