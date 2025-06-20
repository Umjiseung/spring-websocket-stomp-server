package server.domain.auth.service;


import server.domain.auth.presentation.dto.request.SignInRequestDto;
import server.domain.auth.presentation.dto.response.TokenResponseDto;

public interface SignInService {
    TokenResponseDto execute(SignInRequestDto dto);
}
