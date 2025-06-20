package server.domain.auth.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.domain.auth.presentation.dto.request.SignInRequestDto;
import server.domain.auth.presentation.dto.request.SignUpRequestDto;
import server.domain.auth.presentation.dto.response.TokenResponseDto;
import server.domain.auth.service.SignInService;
import server.domain.auth.service.SignUpService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final SignUpService signUpService;
    private final SignInService signInService;

    @PostMapping
    public ResponseEntity<Void> signUp(@RequestBody @Valid SignUpRequestDto dto) {
        signUpService.execute(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/signin")
    public ResponseEntity<TokenResponseDto> signIn(@RequestBody @Valid SignInRequestDto dto) {
        TokenResponseDto result = signInService.execute(dto);
        return ResponseEntity.ok(result);
    }
}
