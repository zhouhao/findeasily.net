package net.findeasily.website.service;

import java.util.List;
import java.util.UUID;

import com.baomidou.mybatisplus.service.IService;
import net.findeasily.website.domain.User;
import net.findeasily.website.domain.UserCreateForm;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Hao Zhou
 * @since 2018-05-22
 */
public interface UserService extends IService<User> {

    User getUserByEmail(String email);

    User getUserById(String id);

    boolean create(UserCreateForm form);

    List<User> getAllUsers();
}
