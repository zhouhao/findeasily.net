package net.findeasily.website.service;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.NonNull;
import net.findeasily.website.domain.User;
import net.findeasily.website.domain.UserExt;
import net.findeasily.website.domain.form.UserCreateForm;
import net.findeasily.website.mapper.UserMapper;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Hao Zhou
 * @since 2018-05-22
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserByEmail(String email) {
        User user = new User();
        user.setEmail(email);
        return this.getOne(new QueryWrapper<>(user));
    }

    public User getUserByName(String name) {
        User user = new User();
        user.setUsername(name);
        return this.getOne(new QueryWrapper<>(user));
    }

    public User getUserById(String id) {
        return getById(id);
    }

    public User create(UserCreateForm form) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setEmail(form.getEmail());
        user.setUsername(form.getUsername());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        return save(user) ? getUserById(user.getId()) : null;
    }

    public boolean updatePassword(@NonNull String userId, @NonNull String newPwd) {
        User user = getUserById(userId);
        user.setPassword(passwordEncoder.encode(newPwd));
        return updateById(user);
    }

    public List<User> getAllUsers() {
        return list(null);
    }

    public boolean activate(@NotBlank String userId) {
        User user = getById(userId);
        if (user == null) {
            return false;
        }
        user.setActivated(true); // todo: check user whether already activated or not
        return updateById(user);
    }

    public boolean lock(@NotBlank String userId, @NotNull Integer lockCode) {
        User user = getById(userId);
        if (user == null) {
            return false;
        }
        user.setLockStatus(lockCode);
        return updateById(user);
    }

    public boolean updateSelfIntro(String selfIntro, String userId) {
        UserExt userExt = baseMapper.selectExtByUserId(userId);
        if (userExt == null) {
            userExt = new UserExt();
            userExt.setUserId(userId);
            userExt.setDescription(selfIntro);
            return userExt.insert();
        }
        userExt.setDescription(selfIntro);
        return userExt.updateById();
    }

    public UserExt getUserExt(String userId) {
        return baseMapper.selectExtByUserId(userId);
    }
}
