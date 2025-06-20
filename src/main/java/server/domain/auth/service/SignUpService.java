package server.domain.auth.service;

import server.domain.auth.presentation.dto.request.SignUpRequestDto;

public interface SignUpService {
    void execute(SignUpRequestDto dto);
}
