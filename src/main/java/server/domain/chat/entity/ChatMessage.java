package server.domain.chat.entity;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import server.global.security.jwt.JwtProvider;

@RedisHash(value = "websocket_message", timeToLive = 60L * 60 * 7)
@Builder
@Getter
public class ChatMessage {
    @Id
    private String chatRoomId;

    private String message;
}
