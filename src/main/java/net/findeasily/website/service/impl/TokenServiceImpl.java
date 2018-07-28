package net.findeasily.website.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.hash.HashFunction;
import io.sentry.Sentry;
import lombok.extern.slf4j.Slf4j;
import net.findeasily.website.domain.Token;
import net.findeasily.website.mapper.TokenMapper;
import net.findeasily.website.service.TokenService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Hao Zhou
 * @since 2018-05-25
 */
@Service
@Slf4j
public class TokenServiceImpl extends ServiceImpl<TokenMapper, Token> implements TokenService {

    private final HashFunction hashFunction;

    @Autowired
    public TokenServiceImpl(HashFunction hashFunction) {
        this.hashFunction = hashFunction;
    }

    @Override
    public boolean match(Token token, String val) {
        if (token == null || StringUtils.isBlank(val)) {
            return false;
        }
        String generatedHash = getTokenStr(token);
        return val.equalsIgnoreCase(generatedHash);
    }

    @Override
    public Token generate(String userId) {
        Token token = new Token();
        token.setUserId(userId);
        token.setCreateTime(new Date());
        token.setVal(UUID.randomUUID().toString());
        return insert(token) ? token : null;
    }

    @Override
    public String getTokenStr(@NotNull Token token) {
        return hashFunction.hashString(token.getUserId() + token.getVal(), StandardCharsets.UTF_8)
                .toString();
    }

    @Override
    public Token parse(String hash) {
        try {
            if (StringUtils.isBlank(hash)) {
                return null;
            }
            String decodedHash = new String(Base64.getDecoder().decode(hash));
            if (StringUtils.isBlank(decodedHash) || !decodedHash.contains(":")) {
                return null;
            }
            String[] splits = decodedHash.split(":");
            if (splits.length != 2) {
                return null;
            }

            int tokenId = NumberUtils.toInt(splits[0], 0);
            if (tokenId < 1) {
                return null;
            }

            Token token = baseMapper.selectById(tokenId);
            if (token == null || !match(token, splits[1])) {
                return null;
            }
            return token;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            Sentry.capture(e);
            return null;
        }
    }

    @Override
    public int deleteFromHourBefore(int hour) {
        return baseMapper.deleteFromHourBefore(hour);
    }


}
