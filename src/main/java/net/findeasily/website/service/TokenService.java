package net.findeasily.website.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.hash.HashFunction;
import io.sentry.Sentry;
import lombok.extern.slf4j.Slf4j;
import net.findeasily.website.domain.Token;
import net.findeasily.website.mapper.TokenMapper;

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
public class TokenService extends ServiceImpl<TokenMapper, Token> {

    private final HashFunction hashFunction;

    @Autowired
    public TokenService(HashFunction hashFunction) {
        this.hashFunction = hashFunction;
    }

    public boolean match(Token token, String val) {
        if (token == null || StringUtils.isBlank(val)) {
            return false;
        }
        String generatedHash = getTokenStr(token);
        return val.equalsIgnoreCase(generatedHash);
    }

    public Token generate(String userId) {
        Token token = new Token();
        token.setUserId(userId);
        token.setCreateTime(new Date());
        token.setVal(UUID.randomUUID().toString());
        return save(token) ? token : null;
    }

    public String getTokenStr(@NotNull Token token) {
        return hashFunction.hashString(token.getUserId() + token.getVal(), StandardCharsets.UTF_8)
                .toString();
    }

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

    public int deleteFromHourBefore(int hour) {
        return baseMapper.deleteFromHourBefore(hour);
    }


}
