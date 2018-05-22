package com.sbzhouhao.example.service.user;

import java.util.Collection;
import java.util.Optional;

import com.sbzhouhao.example.domain.User;
import com.sbzhouhao.example.domain.UserCreateForm;

public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);

}
