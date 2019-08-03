package net.findeasily.website.schedule.start;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.findeasily.website.domain.Role;
import net.findeasily.website.domain.form.UserCreateForm;
import net.findeasily.website.entity.User;
import net.findeasily.website.service.UserService;

@Component
@Profile({"dev", "test"})
@Slf4j
public class DevProfileStartup implements CommandLineRunner {
    private final UserService userService;

    @Autowired
    public DevProfileStartup(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        createDevUser();
    }

    private void createDevUser() {
        User user = userService.getUserByEmail("test@test.com");
        if (user != null) {
            return;
        }

        String testPwd = "test123!";
        String testEmail = "test@test.com";
        UserCreateForm ucf = new UserCreateForm();
        ucf.setEmail(testEmail);
        ucf.setPassword(testPwd);
        ucf.setPasswordRepeated(testPwd);
        ucf.setRole(Role.USER);
        ucf.setUsername("Tester_" + RandomStringUtils.randomAlphabetic(3));
        user = userService.create(ucf);
        if (user == null) {
            log.error("failed to create test user: {}", ucf);
            return;
        }
        userService.activate(user.getId());
        log.info("Dev user is created now, you can login with `{}` with password `{}`", testEmail, testPwd);
    }
}
