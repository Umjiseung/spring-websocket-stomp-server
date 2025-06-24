package server.domain.chat.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import server.domain.chat.dto.ChatMessageRequest;
import server.domain.chat.service.SaveChatService;

@RequiredArgsConstructor
@Service
public class SaveChatServiceImpl implements SaveChatService {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    public void execute(Long chatRoomId, ChatMessageRequest request) {
        try {
            String key = "chat:" + chatRoomId;
            String json = objectMapper.writeValueAsString(request);
            redisTemplate.opsForList().rightPush(key, json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("채팅 메시지 직렬화 실패", e);
        }
    }
}
