package net.findeasily.website.service;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.NonNull;
import net.findeasily.website.domain.form.UserCreateForm;
import net.findeasily.website.entity.User;
import net.findeasily.website.entity.UserExt;
import net.findeasily.website.repository.UserExtRepository;
import net.findeasily.website.repository.UserRepository;

/**
 * @author Hao Zhou
 * @since 2018-05-22
 */
@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserExtRepository userExtRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, UserExtRepository userExtRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userExtRepository = userExtRepository;
    }

    public User getUserByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    public User getUserByName(String name) {
        return userRepository.getByUsername(name);
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public User create(UserCreateForm form) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setEmail(form.getEmail());
        user.setUsername(form.getUsername());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        return userRepository.save(user);
    }

    public boolean updatePassword(@NonNull String userId, @NonNull String newPwd) {
        User user = getUserById(userId);
        user.setPassword(passwordEncoder.encode(newPwd));
        return updateById(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean activate(@NotBlank String userId) {
        User user = getUserById(userId);
        if (user == null) {
            return false;
        }
        user.setActivated(true);
        return updateById(user);
    }

    public boolean lock(@NotBlank String userId, @NotNull Integer lockCode) {
        User user = getUserById(userId);
        if (user == null) {
            return false;
        }
        user.setLockStatus(lockCode);
        return updateById(user);
    }

    public void updateSelfIntro(String selfIntro, String userId) {
        UserExt userExt = userExtRepository.findById(userId).orElse(null);
        if (userExt == null) {
            userExt = new UserExt();
            userExt.setUserId(userId);
            userExt.setDescription(selfIntro);
        }
        userExt.setDescription(selfIntro);
        userExtRepository.save(userExt);
    }

    public UserExt getUserExt(String userId) {
        return userExtRepository.findById(userId).orElse(null);
    }

    public boolean updateById(User user) {
        if (user == null || StringUtils.isBlank(user.getId())) {
            return false;
        }
        userRepository.save(user);
        return true;
    }

    public boolean removeById(String id) {
        userRepository.deleteById(id);
        return true;
    }
}
