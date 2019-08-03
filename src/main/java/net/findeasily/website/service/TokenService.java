package net.findeasily.website.service;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.hash.HashFunction;
import io.sentry.Sentry;
import lombok.extern.slf4j.Slf4j;
import net.findeasily.website.entity.Token;
import net.findeasily.website.repository.TokenRepository;

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
public class TokenService {

    private final HashFunction hashFunction;
    private final TokenRepository tokenRepository;

    @Autowired
    public TokenService(HashFunction hashFunction, TokenRepository tokenRepository) {
        this.hashFunction = hashFunction;
        this.tokenRepository = tokenRepository;
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
        token.setCreateTime(new Timestamp(System.currentTimeMillis()));
        token.setVal(UUID.randomUUID().toString());
        return tokenRepository.save(token);
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

            Optional<Token> token = tokenRepository.findById(tokenId);
            if (!token.isPresent() || !match(token.get(), splits[1])) {
                return null;
            }
            return token.get();
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
