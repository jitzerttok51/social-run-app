package org.jitzerttok51.social.run.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jitzerttok51.social.run.model.Sex;
import org.jitzerttok51.social.run.validation.*;
import org.jitzerttok51.social.run.validation.groups.Group1;
import org.jitzerttok51.social.run.validation.groups.Group2;
import org.jitzerttok51.social.run.validation.groups.Group3;

import java.time.LocalDate;

// TODO: Add javadoc
@NoArgsConstructor
@Data
@PasswordMatch(message = "{user.registration.confirmPassword.match}", groups = Group3.class)
public class UserRegisterDTO implements PasswordsMatch {

    @NotBlank(message = "{user.registration.username.required}", groups = Group1.class)
    @Size(min = 3, max = 16, message = "{user.registration.username.invalid}", groups = Group2.class)
    @UsernameTaken(message = "{user.registration.username.taken}", groups = Group3.class)
    private String username;

    @NotBlank(message = "{user.registration.email.required}", groups = Group1.class)
    @Size(min = 3, max = 32, message = "{user.registration.email.invalid}", groups = Group2.class)
    @Email(message = "{user.registration.email.invalid2}", groups = Group3.class)
    @EmailTaken(message = "{user.registration.email.taken}", groups = Group3.class)
    private String email;

    @NotBlank(message = "{user.registration.password.required}", groups = Group1.class)
    @ValidPassword(groups = Group2.class)
    private String password;

    @NotBlank(message = "{user.registration.password.required}", groups = Group1.class)
    @ValidPassword(groups = Group2.class)
    private String confirmPassword;

    @NotBlank(message = "{user.registration.firstName.required}", groups = Group1.class)
    @Size(min = 2, max = 16, message = "{user.registration.firstName.invalid}", groups = Group2.class)
    private String firstName = "";

    @NotBlank(message = "{user.registration.lastName.required}", groups = Group1.class)
    @Size(min = 2, max = 16, message = "{user.registration.lastName.invalid}", groups = Group2.class)
    private String lastName = "";

    @NotNull(message = "{user.registration.sex.required}", groups = Group1.class)
    private Sex sex;

    @NotNull(message = "{user.registration.dateOfBirth.required}", groups = Group1.class)
    @IsOverSpecificAge(minAge = 13, message = "{user.registration.dateOfBirth.invalid}", groups = Group2.class)
    private LocalDate dateOfBirth;
}
