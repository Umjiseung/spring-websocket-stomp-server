package server.domain.chat.service;

import server.domain.chat.dto.ChatMessageRequest;

public interface SaveChatService {
    void execute(Long chatRoomId, ChatMessageRequest request);
}
