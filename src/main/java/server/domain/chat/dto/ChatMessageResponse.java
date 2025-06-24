package server.domain.chat.dto;

import java.time.LocalDateTime;

public record ChatMessageResponse(String username, String content) {
}
