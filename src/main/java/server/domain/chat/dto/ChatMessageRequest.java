package server.domain.chat.dto;

import java.time.LocalDateTime;

public record ChatMessageRequest(String username, String content, LocalDateTime sendAt) {
}
