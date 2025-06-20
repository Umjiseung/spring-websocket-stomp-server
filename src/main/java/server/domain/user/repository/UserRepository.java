package server.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.user.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByNickname(String nickname);
    Optional<User> findByNickname(String nickname);
}
