package server.domain.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.domain.auth.presentation.dto.request.SignUpRequestDto;
import server.domain.auth.service.SignUpService;
import server.domain.user.entity.User;
import server.domain.user.repository.UserRepository;
import server.global.exception.GlobalException;

@Service
@Transactional
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void execute(SignUpRequestDto dto) {
        if (userRepository.existsByNickname(dto.getNickname()))
            throw new GlobalException("이미 존재하는 닉네임입니다.", 409);

        saveUser(dto);
    }

    private void saveUser(SignUpRequestDto dto) {
        userRepository.save(
            User.builder()
                .name(dto.getName())
                .nickname(dto.getNickname())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build()
        );
    }


}
