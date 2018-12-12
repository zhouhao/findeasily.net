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

import static org.junit.Assert.assertFalse;
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
    private String username;

    @Before
    public void setUp() throws Exception {
        UserCreateForm ucf = new UserCreateForm();
        ucf.setEmail(faker.internet().emailAddress());
        String pwd = RandomStringUtils.randomAlphanumeric(6);
        ucf.setPassword(pwd);
        ucf.setPasswordRepeated(pwd);
        ucf.setRole(Role.USER);
        username = faker.name().username();
        ucf.setUsername(username);
        user = userService.create(ucf);
        assertNotNull(user);
    }

    @Test
    public void getUserByEmailTest() {
        assertNotNull(userService.getUserByEmail(user.getEmail()));
        assertNull(userService.getUserByEmail(user.getEmail() + "dummySuffix"));
    }

    @Test
    public void getUserByNameTest() {
        assertNotNull(userService.getUserByName(username));
        assertNull(userService.getUserByName(username + "dummySuffix"));
    }

    @Test
    public void activateTest() {
        assertTrue(userService.activate(user.getId()));
        assertFalse(userService.activate(user.getId() + 100));
    }

    @After
    public void tearDown() throws Exception {
        assertTrue(userService.removeById(user.getId()));
    }
}