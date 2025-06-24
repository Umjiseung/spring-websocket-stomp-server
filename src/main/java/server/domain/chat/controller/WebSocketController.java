package server.domain.chat.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import server.domain.chat.dto.ChatMessageRequest;
import server.domain.chat.dto.ChatMessageResponse;
import server.domain.chat.dto.CreateChatRoomRequest;
import server.domain.chat.service.CreateChatRoomService;
import server.domain.chat.service.SaveChatService;

import java.time.LocalDateTime;


@RestController
@RequiredArgsConstructor
public class WebSocketController {

    private static final Logger log = LoggerFactory.getLogger(WebSocketController.class);
    private final CreateChatRoomService createChatRoomService;
    private final SaveChatService saveChatService;

    @MessageMapping("/chat.{chatRoomId}")
    @SendTo("/subscribe/chat.{chatRoomId}")
    public ChatMessageResponse sendMessage(ChatMessageRequest request, @DestinationVariable Long chatRoomId) {

        saveChatService.execute(chatRoomId, request);

        return new ChatMessageResponse(request.username(), request.content());
    }

    @PostMapping("/chat")
    public void createChatRoom(@RequestBody CreateChatRoomRequest dto) {
        createChatRoomService.execute(dto);
    }



    @MessageExceptionHandler
    public void handleException(RuntimeException e) {
        log.info("Exception: {}", e.getMessage());
    }

}
