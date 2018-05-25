package net.findeasily.website.service.impl;

import net.findeasily.website.domain.Token;
import net.findeasily.website.mapper.TokenMapper;
import net.findeasily.website.service.TokenService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Hao Zhou
 * @since 2018-05-25
 */
@Service
public class TokenServiceImpl extends ServiceImpl<TokenMapper, Token> implements TokenService {

}
