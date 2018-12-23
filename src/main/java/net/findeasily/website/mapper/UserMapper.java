package net.findeasily.website.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.findeasily.website.domain.User;
import net.findeasily.website.domain.UserExt;


/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Hao Zhou
 * @since 2018-05-22
 */
public interface UserMapper extends BaseMapper<User> {
    UserExt selectExtByUserId(@Param("userId") String userId);
}
