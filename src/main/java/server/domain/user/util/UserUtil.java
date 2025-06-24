package server.domain.user.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import server.domain.user.entity.User;
import server.domain.user.repository.UserRepository;
import server.global.exception.GlobalException;

@Component
@RequiredArgsConstructor
public class UserUtil {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        String nickname = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByNickname(nickname)
                .orElseThrow(() -> new GlobalException("User not found with nickname: " + nickname, 222));
    }
}
