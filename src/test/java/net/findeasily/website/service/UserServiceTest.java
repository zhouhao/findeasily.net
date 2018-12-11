package net.findeasily.website.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.javafaker.Faker;
import net.findeasily.website.domain.Role;
import net.findeasily.website.domain.User;
import net.findeasily.website.domain.UserCreateForm;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

    @Autowired
    private UserService userService;
    private Faker faker = new Faker();

    private User user;

    @Before
    public void setUp() throws Exception {
        UserCreateForm ucf = new UserCreateForm();
        ucf.setEmail(faker.internet().emailAddress());
        String pwd = RandomStringUtils.randomAlphanumeric(6);
        ucf.setPassword(pwd);
        ucf.setPasswordRepeated(pwd);
        ucf.setRole(Role.USER);
        ucf.setUsername(faker.name().username());
        user = userService.create(ucf);
        assertNotNull(user);
    }

    @Test
    public void getUserByEmailValid() {
        assertNotNull(userService.getUserByEmail(user.getEmail()));
    }

    @Test
    public void getUserByEmailInvalid() {
        assertNull(userService.getUserByEmail(user.getEmail() + "dummySuffix"));
    }

    @After
    public void tearDown() throws Exception {
        assertTrue(userService.removeById(user.getId()));
    }
}