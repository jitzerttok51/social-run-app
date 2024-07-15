package org.jitzerttok51.social.run.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jitzerttok51.social.run.model.Sex;
import org.jitzerttok51.social.run.validation.IsOverSpecificAge;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class UserRegisterDTO {

    @Size(min = 3, max = 16, message = "{user.registration.username.invalid}")
    private String username;

    @Size(min = 3, max = 16, message = "{user.registration.email.invalid}")
    private String email = "";

    private String password;
    private String confirmPassword;

    @Size(min = 2, max = 16, message = "{user.registration.firstName.invalid}")
    private String firstName = "";

    @Size(min = 2, max = 16, message = "{user.registration.lastName.invalid}")
    private String lastName = "";

    @NotNull(message = "{user.registration.sex.required}")
    private Sex sex;

    @NotNull(message = "{user.registration.dateOfBirth.required}")
    @IsOverSpecificAge(minAge = 13, message = "{user.registration.dateOfBirth.invalid}")
    private LocalDate dateOfBirth;
}
