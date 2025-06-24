package server.domain.chat.service;

import server.domain.chat.dto.CreateChatRoomRequest;

public interface CreateChatRoomService {
    void execute(CreateChatRoomRequest dto);
}
