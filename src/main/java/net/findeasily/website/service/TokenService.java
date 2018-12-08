package net.findeasily.website.service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.tuple.Pair;

import com.baomidou.mybatisplus.extension.service.IService;
import net.findeasily.website.domain.Token;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Hao Zhou
 * @since 2018-05-25
 */
public interface TokenService extends IService<Token> {

    boolean match(@NotNull Token token, String val);

    Token generate(@NotEmpty String userId);

    String getTokenStr(@NotNull Token token);

    Token parse(String hash);

    int deleteFromHourBefore(int hour);
}
