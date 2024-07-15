package org.jitzerttok51.social.run;

import org.jitzerttok51.social.run.exceptions.ValidationException;
import org.jitzerttok51.social.run.model.dto.UserRegisterDTO;
import org.jitzerttok51.social.run.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        var dto = new UserRegisterDTO();
        try {
            userService.registerUser(dto);
        } catch (ValidationException e) {
            System.out.println(e);
        }
    }
}
