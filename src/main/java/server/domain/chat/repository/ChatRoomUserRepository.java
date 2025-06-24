package server.domain.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.chat.entity.ChatRoomUser;

public interface ChatRoomUserRepository extends JpaRepository<ChatRoomUser, Long> {
}
