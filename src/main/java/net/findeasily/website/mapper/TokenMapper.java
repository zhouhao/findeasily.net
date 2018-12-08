package net.findeasily.website.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.findeasily.website.domain.Token;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Hao Zhou
 * @since 2018-05-25
 */
public interface TokenMapper extends BaseMapper<Token> {

    int deleteFromHourBefore(@Param("hour") int hour);
}
