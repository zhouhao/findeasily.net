package net.findeasily.website.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

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

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public User getUserById(String id) {
        return null;
    }

    @Override
    public boolean create(UserCreateForm form) {
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }
}
