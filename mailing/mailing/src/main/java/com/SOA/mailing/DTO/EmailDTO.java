package com.SOA.mailing.DTO;

import com.SOA.mailing.Enum.StatusEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmailDTO {

    @NotBlank
    @Email

    private String emailTo;
    private String subject;

}
