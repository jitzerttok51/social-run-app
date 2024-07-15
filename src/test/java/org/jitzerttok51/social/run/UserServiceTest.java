package org.jitzerttok51.social.run;

import jakarta.validation.ConstraintViolation;
import org.jitzerttok51.social.run.exceptions.ValidationException;
import org.jitzerttok51.social.run.model.Sex;
import org.jitzerttok51.social.run.model.dto.UserRegisterDTO;
import org.jitzerttok51.social.run.model.entity.UserEntity;
import org.jitzerttok51.social.run.repository.UserRepository;
import org.jitzerttok51.social.run.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest extends AbstractIntegrationTest {

    public static final String TOO_SHORT_TEXT = "W";
    public static final String TOO_LONG_TEXT = "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW";
    public static final String NORMAL_TEXT_2 = "WW";
    public static final String NORMAL_TEXT_3 = "WWW";
    public static final LocalDate VALID_DATE_OF_BIRTH = LocalDate.now().minusYears(14);
    public static final LocalDate INVALID_DATE_OF_BIRTH = LocalDate.now().minusYears(10);
    public static final String takenUsername = "myUsername";
    public static final String takenEmail = "taken_email@abv.bg";

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository repository;

    private static final Set<String> REQUIRED_MESSAGES = Set.of(
            "{user.registration.username.required}",
            "{user.registration.email.required}",
            "{user.registration.firstName.required}",
            "{user.registration.lastName.required}",
            "{user.registration.sex.required}",
            "{user.registration.dateOfBirth.required}"
    );

    private static final Set<String> ALL_FIELDS_SIZE_MESSAGES = Set.of(
            "{user.registration.username.invalid}",
            "{user.registration.email.invalid}",
            "{user.registration.firstName.invalid}",
            "{user.registration.lastName.invalid}"
    );

    private static final Set<String> USERNAME_AND_EMAIL_SIZE_MESSAGES = Set.of(
            "{user.registration.username.invalid}",
            "{user.registration.email.invalid}"
    );

    private static final Set<String> INVALID_EMAIL_MESSAGES = Set.of(
            "{user.registration.email.invalid2}"
    );

    private static final Set<String> USERNAME_AND_EMAIL_TAKEN_MESSAGES = Set.of(
            "{user.registration.email.taken}",
            "{user.registration.username.taken}"
    );

    private static final Set<String> MIN_AGE_MESSAGES = Set.of(
            "{user.registration.dateOfBirth.invalid}"
    );

    @Test
    public void testRequiredValues() {
        var dto = new UserRegisterDTO();
        assertValidation(REQUIRED_MESSAGES, dto);
    }

    @Test
    public void testMinSizeRequirements() {
        var dto = new UserRegisterDTO();
        dto.setUsername(TOO_SHORT_TEXT);
        dto.setEmail(TOO_SHORT_TEXT);
        dto.setFirstName(TOO_SHORT_TEXT);
        dto.setLastName(TOO_SHORT_TEXT);
        dto.setSex(Sex.MALE);
        dto.setDateOfBirth(VALID_DATE_OF_BIRTH);
        assertValidation(ALL_FIELDS_SIZE_MESSAGES, dto);
    }

    @Test
    public void testMaxSizeRequirements() {
        var dto = new UserRegisterDTO();
        dto.setUsername(TOO_LONG_TEXT);
        dto.setEmail(TOO_LONG_TEXT);
        dto.setFirstName(TOO_LONG_TEXT);
        dto.setLastName(TOO_LONG_TEXT);
        dto.setSex(Sex.MALE);
        dto.setDateOfBirth(VALID_DATE_OF_BIRTH);
        assertValidation(ALL_FIELDS_SIZE_MESSAGES, dto);
    }

    @Test
    public void testUsernameAndEmailSizeRequirements() {
        var dto = new UserRegisterDTO();
        dto.setUsername(NORMAL_TEXT_2);
        dto.setEmail(NORMAL_TEXT_2);
        dto.setFirstName(NORMAL_TEXT_2);
        dto.setLastName(NORMAL_TEXT_2);
        dto.setSex(Sex.MALE);
        dto.setDateOfBirth(VALID_DATE_OF_BIRTH);
        assertValidation(USERNAME_AND_EMAIL_SIZE_MESSAGES, dto);
    }

    @Test
    public void testValidEmailRequirements() {
        var dto = new UserRegisterDTO();
        dto.setUsername(NORMAL_TEXT_3);
        dto.setEmail(NORMAL_TEXT_3);
        dto.setFirstName(NORMAL_TEXT_2);
        dto.setLastName(NORMAL_TEXT_2);
        dto.setSex(Sex.MALE);
        dto.setDateOfBirth(VALID_DATE_OF_BIRTH);
        assertValidation(INVALID_EMAIL_MESSAGES, dto);
    }

    @Test
    public void testUsernameAndEmailTaken() {
        repository.deleteAll();
        repository.save(dummyUser(takenUsername, takenEmail));
        var dto = new UserRegisterDTO();
        dto.setUsername(takenUsername);
        dto.setEmail(takenEmail);
        dto.setFirstName(NORMAL_TEXT_2);
        dto.setLastName(NORMAL_TEXT_2);
        dto.setSex(Sex.MALE);
        dto.setDateOfBirth(VALID_DATE_OF_BIRTH);
        assertValidation(USERNAME_AND_EMAIL_TAKEN_MESSAGES, dto);
    }

    @Test
    public void testAgeRequirement() {
        repository.deleteAll();
        var dto = new UserRegisterDTO();
        dto.setUsername(takenUsername);
        dto.setEmail(takenEmail);
        dto.setFirstName(NORMAL_TEXT_2);
        dto.setLastName(NORMAL_TEXT_2);
        dto.setSex(Sex.MALE);
        dto.setDateOfBirth(INVALID_DATE_OF_BIRTH);
        assertValidation(MIN_AGE_MESSAGES, dto);
    }

    private void assertValidation(Set<String> expectedMessages, UserRegisterDTO dto) {
        try {
            userService.registerUser(dto);
            Assertions.fail("Did not detect error");
        } catch (ValidationException e) {
            var messages = getMessageTemplates(e);
            Assertions.assertEquals(expectedMessages, messages);
        }
    }

    private UserEntity dummyUser(String username, String email) {
        UserEntity entity = new UserEntity();
        entity.setUsername(username);
        entity.setEmail(email);
        entity.setFirstName(TOO_SHORT_TEXT);
        entity.setLastName(TOO_SHORT_TEXT);
        entity.setSex(Sex.MALE);
        entity.setHash("Test");
        entity.setDateOfBirth(VALID_DATE_OF_BIRTH);
        return entity;
    }

    private Set<String> getMessageTemplates(ValidationException e) {
        return e
                .getViolations()
                .stream()
                .map(ConstraintViolation::getMessageTemplate)
                .collect(Collectors.toSet());
    }
}
