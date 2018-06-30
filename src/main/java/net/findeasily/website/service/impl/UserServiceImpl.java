package net.findeasily.website.service.impl;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import net.findeasily.website.domain.User;
import net.findeasily.website.domain.UserCreateForm;
import net.findeasily.website.mapper.UserMapper;
import net.findeasily.website.service.UserService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Hao Zhou
 * @since 2018-05-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = new User();
        user.setEmail(email);
        return this.selectOne(new EntityWrapper<>(user));
    }

    @Override
    public User getUserByName(String name) {
        User user = new User();
        user.setUsername(name);
        return this.selectOne(new EntityWrapper<>(user));
    }

    @Override
    public User getUserById(String id) {
        return selectById(id);
    }

    @Override
    public User create(UserCreateForm form) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setEmail(form.getEmail());
        user.setUsername(form.getUsername());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        return insert(user) ? getUserById(user.getId()) : null;
    }

    @Override
    public List<User> getAllUsers() {
        return selectList(null);
    }

    @Override
    public boolean activate(@NotBlank String userId) {
        User user = selectById(userId);
        if (user == null) {
            return false;
        }
        user.setActivated(true); // todo: check user whether already activated or not
        return updateById(user);
    }

    @Override
    public boolean Lock(@NotBlank String userId, @NotNull Integer lockCode) {
        User user = selectById(userId);
        if (user == null) {
            return false;
        }
        user.setLockStatus(lockCode);
        return updateById(user);
    }
}
