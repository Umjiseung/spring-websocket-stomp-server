package server.domain.chat.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.domain.chat.dto.CreateChatRoomRequest;
import server.domain.chat.entity.ChatRoom;
import server.domain.chat.entity.ChatRoomUser;
import server.domain.chat.entity.ChatType;
import server.domain.chat.repository.ChatRoomRepository;
import server.domain.chat.repository.ChatRoomUserRepository;
import server.domain.chat.service.CreateChatRoomService;
import server.domain.user.entity.User;
import server.domain.user.repository.UserRepository;
import server.domain.user.util.UserUtil;
import server.global.exception.GlobalException;

@Transactional
@Service
@RequiredArgsConstructor
public class CreateChatRoomServiceImpl implements CreateChatRoomService {

    private final UserUtil userUtil;
    private final UserRepository userRepository;
    private final ChatRoomUserRepository chatRoomUserRepository;
    private final ChatRoomRepository chatRoomRepository;

    public void execute(CreateChatRoomRequest dto) {
        User user = userUtil.getCurrentUser();

        User userToChatWith = userRepository.findById(dto.getUserIdToChat())
                .orElseThrow(() -> new GlobalException("User not found with ID: " + dto.getUserIdToChat(), 404));

        Long chatRoomId = saveChatRoom();

        saveChatRoomUser(user, userToChatWith, chatRoomId);
    }

    private Long saveChatRoom() {
        ChatRoom chatRoom = ChatRoom.builder()
                .chatType(ChatType.STANDARD)
                .build();

        return chatRoomRepository.save(chatRoom).getId();
    }

    private void saveChatRoomUser(User user, User userToChatWith, Long chatRoomId) {
        ChatRoomUser chatRoomUser = ChatRoomUser.builder()
                .chatRoomId(chatRoomId)
                .userId(user.getId())
                .build();

        ChatRoomUser chatRoomUserToChatWith = ChatRoomUser.builder()
                .chatRoomId(chatRoomId)
                .userId(userToChatWith.getId())
                .build();

        chatRoomUserRepository.save(chatRoomUser);
        chatRoomUserRepository.save(chatRoomUserToChatWith);
    }
}
