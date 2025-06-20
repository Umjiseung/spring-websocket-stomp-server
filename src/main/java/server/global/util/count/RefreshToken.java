package server.global.util.count;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;
import server.global.security.jwt.JwtProvider;

@RedisHash(value = "websocket_refreshToken", timeToLive = JwtProvider.REFRESH_TOKEN_TIME)
@Builder
@Getter
public class RefreshToken {
    @Id
    private String nickname;
    @Indexed
    private String token;
}