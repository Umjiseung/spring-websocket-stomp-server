package server.domain.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.chat.entity.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
