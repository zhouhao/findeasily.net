package net.findeasily.website.service.user;

import java.util.Collection;
import java.util.Optional;

import net.findeasily.website.domain.User;
import net.findeasily.website.domain.UserCreateForm;

public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);

}
