package server.domain.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.domain.auth.presentation.dto.request.SignInRequestDto;
import server.domain.auth.presentation.dto.response.TokenResponseDto;
import server.domain.auth.service.SignInService;
import server.domain.user.entity.User;
import server.domain.user.repository.UserRepository;
import server.global.exception.GlobalException;
import server.global.security.jwt.JwtProvider;

@Service
@Transactional
@RequiredArgsConstructor
public class SignInServiceImpl implements SignInService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public TokenResponseDto execute(SignInRequestDto dto) {
        User user = userRepository.findByNickname(dto.getNickname())
                .orElseThrow(() -> new GlobalException("존재하지 않는 유저입니다.", 404));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword()))
            throw new GlobalException("비밀번호가 일치하지 않습니다.", 401);

        TokenResponseDto responseDto = jwtProvider.generateTokenDto(user.getNickname());


    }


}
